package gregtech.common;

import gregtech.GT_Mod;
import gregtech.api.objects.XSTR;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import java.util.ArrayList;
import java.util.List;

public class GT_Pollution {
	/**
	 * Pollution dispersion until effects start:
	 * Calculation: ((Limit * 0.01) + 2000) * (4 <- spreading rate)
	 *
	 * SMOG(500k) 466.7 pollution/sec
	 * Poison(750k) 633,3 pollution/sec
	 * Dying Plants(1mio) 800 pollution/sec
	 * Sour Rain(1.5mio) 1133.3 pollution/sec
	 *
	 * Pollution producers (pollution/sec)
	 * Bronze Boiler(20)
	 * Lava Boiler(20)
	 * High Pressure Boiler(20)
	 * Bronze Blast Furnace(50)
	 * Diesel Generator(14/28/75)
	 * Gas Turbine(7/14/37)
	 * Charcoal Pile(100)
	 *
	 * Large Diesel Generator(300)
	 * Electric Blast Furnace(100)
	 * Implosion Compressor(2000)
	 * Large Boiler(240)
	 * Large Gas Turbine(160)
	 * Multi Smelter(100)
	 * Pyrolyse Oven(400)
	 *
	 * Machine Explosion(100,000)
	 *
	 * Muffler Hatch Pollution reduction:
	 * LV (0%), MV (30%), HV (52%), EV (66%), IV (76%), LuV (84%), ZPM (89%), UV (92%), MAX (95%)
	 */

	static List<ChunkPos> tList = null;
	static int loops = 1;
	static XSTR tRan = new XSTR();

	public static void onWorldTick(World aWorld, int aTick){
		if(!GT_Mod.gregtechproxy.mPollution)return;
		if(aTick == 0 || (tList==null && GT_Proxy.chunkData!=null)){
			tList = new ArrayList<>(GT_Proxy.chunkData.keySet());
			loops = (tList.size()/1200) + 1;
//			System.out.println("new Pollution loop"+aTick);
		}
		if(tList!=null && tList.size() > 0){
			int i = 0;
			for(; i < loops ; i++){
				if(tList.size()>0){
					ChunkPos tPos = tList.get(0);
					tList.remove(0);
					if(tPos!=null && GT_Proxy.chunkData.containsKey(tPos)){
						int tPollution = GT_Proxy.chunkData.get(tPos)[1];
//				System.out.println("process: "+tPos.chunkPosX+" "+tPos.chunkPosZ+" "+tPollution);
						//Reduce pollution in chunk
						tPollution = (int)(0.99f*tPollution);
						tPollution -= 2000;
						if(tPollution<=0){tPollution = 0;}else{
							//Spread Pollution
							if(tPollution>50000){
								List<ChunkPos> tNeighbor = new ArrayList<>();
								tNeighbor.add(new ChunkPos(tPos.field_77276_a + 1, tPos.field_77275_b));
								tNeighbor.add(new ChunkPos(tPos.field_77276_a - 1, tPos.field_77275_b));
								tNeighbor.add(new ChunkPos(tPos.field_77276_a, tPos.field_77275_b + 1));
								tNeighbor.add(new ChunkPos(tPos.field_77276_a, tPos.field_77275_b - 1));
								for(ChunkPos tNPos : tNeighbor){
									if(GT_Proxy.chunkData.containsKey(tNPos)){
										int tNPol = GT_Proxy.chunkData.get(tNPos)[1];
										if(tNPol<tPollution && tNPol*12 < tPollution*10){
											int tDiff = tPollution - tNPol;
											tDiff = tDiff/10;
											tNPol += tDiff;
											tPollution -= tDiff;
											GT_Proxy.chunkData.get(tNPos)[1] = tNPol;
										}
									}else{
										GT_Utility.getUndergroundOil(aWorld,tNPos.field_77276_a <<4, tNPos.field_77275_b<<4);
									}
								}}
							int[] tArray = GT_Proxy.chunkData.get(tPos);
							tArray[1] = tPollution;
							GT_Proxy.chunkData.remove(tPos);
							GT_Proxy.chunkData.put(tPos, tArray);
							//Create Pollution effects
//				Smog filter TODO
							if(tPollution > GT_Mod.gregtechproxy.mPollutionSmogLimit){
								AxisAlignedBB chunk = new AxisAlignedBB(tPos.field_77276_a<<4, 0, tPos.field_77275_b<<4, (tPos.field_77276_a<<4)+16, 256, (tPos.field_77275_b<<4)+16);
								List<EntityLivingBase> tEntitys = aWorld.func_72872_a(EntityLivingBase.class, chunk);
								for(EntityLivingBase tEnt : tEntitys){
									if(!GT_Utility.isWearingFullGasHazmat(tEnt) && tRan.nextInt(tPollution/2000) > 40){
										int ran = tRan.nextInt(3);
										if(ran==0)tEnt.func_70690_d(new PotionEffect(MobEffects.field_76437_t,  Math.min(tPollution/2500,1000), tPollution/400000));
										if(ran==1)tEnt.func_70690_d(new PotionEffect(MobEffects.field_76419_f,  Math.min(tPollution/2500,1000), tPollution/400000));
										if(ran==2)tEnt.func_70690_d(new PotionEffect(MobEffects.field_76421_d,  Math.min(tPollution/2500,1000), tPollution/400000));
									}
								}
//				Poison effects
								if(tPollution > GT_Mod.gregtechproxy.mPollutionPoisonLimit){
									for(EntityLivingBase tEnt : tEntitys){
										if(!GT_Utility.isWearingFullGasHazmat(tEnt) && tRan.nextInt(tPollution/2000) > 20){
											int ran = tRan.nextInt(3);
											if(ran==0)tEnt.func_70690_d(new PotionEffect(MobEffects.field_76436_u, Math.min(tPollution/2500,1000), tPollution/500000));
											if(ran==1)tEnt.func_70690_d(new PotionEffect(MobEffects.field_76431_k, Math.min(tPollution/2500,1000), 1));
											if(ran==2)tEnt.func_70690_d(new PotionEffect(MobEffects.field_76440_q, Math.min(tPollution/2500,1000), 1));
                                        }
									}
//				killing plants
									if(tPollution > GT_Mod.gregtechproxy.mPollutionVegetationLimit){
										int f = 20;
										for(;f<(tPollution/25000);f++){
											int x =tPos.field_77276_a<<4+(tRan.nextInt(16));;
											int y =60 +(-f+tRan.nextInt(f*2+1));
											int z =tPos.field_77275_b<<4+(tRan.nextInt(16));
											damageBlock(new BlockPos(x, y, z), tPollution > GT_Mod.gregtechproxy.mPollutionSourRainLimit);
										}}}}
						}
					}
				}
			}}
	}

	public static void damageBlock(BlockPos pos, boolean sourRain){
		World world = DimensionManager.getWorld(0);
		if (world.field_72995_K)	return;
		IBlockState tBlockState = world.func_180495_p(pos);
		if (tBlockState.func_177230_c() == Blocks.field_150350_a || tBlockState.func_177230_c() == Blocks.field_150348_b || tBlockState.func_177230_c() == Blocks.field_150354_m|| tBlockState.func_177230_c() == Blocks.field_150330_I)return;

		if (tBlockState.func_177230_c() == Blocks.field_150362_t || tBlockState.func_177230_c() == Blocks.field_150361_u || tBlockState.func_185904_a() == Material.field_151584_j)
			world.func_175698_g(pos);
		if (tBlockState.func_177230_c() == Blocks.field_150436_aH) {
			tBlockState.func_177230_c().func_176226_b(world, pos, tBlockState, 0);
			world.func_175698_g(pos);
		}
		if (tBlockState.func_177230_c() == Blocks.field_150329_H)
			world.func_175656_a(pos, Blocks.field_150330_I.func_176223_P());
        if(tBlockState.func_177230_c() == Blocks.field_150398_cm) {
            if(tBlockState.func_177229_b(BlockDoublePlant.field_176492_b) == BlockDoublePlant.EnumBlockHalf.LOWER) {
                world.func_175656_a(pos, Blocks.field_150330_I.func_176223_P());
                world.func_175698_g(pos.func_177984_a());
            }
        }

		if (tBlockState.func_177230_c() == Blocks.field_150395_bd) {
			tBlockState.func_177230_c().func_176226_b(world, pos, tBlockState, 0);
			world.func_175698_g(pos);
		}
		if (tBlockState.func_177230_c() == Blocks.field_150392_bi || tBlockState.func_177230_c() == Blocks.field_150464_aj || tBlockState.func_177230_c() == Blocks.field_150434_aF ||
				tBlockState.func_185904_a() == Material.field_151570_A || tBlockState.func_177230_c() == Blocks.field_150440_ba || tBlockState.func_177230_c() == Blocks.field_150394_bc) {
			tBlockState.func_177230_c().func_176226_b(world, pos, tBlockState, 0);
			world.func_175698_g(pos);
		}
		if (tBlockState.func_177230_c() == Blocks.field_150328_O || tBlockState.func_177230_c() == Blocks.field_150327_N || tBlockState.func_177230_c() == Blocks.field_150459_bM ||
				tBlockState.func_177230_c() == Blocks.field_150469_bN || tBlockState.func_177230_c() == Blocks.field_150423_aK || tBlockState.func_177230_c() == Blocks.field_150393_bb) {
			tBlockState.func_177230_c().func_176226_b(world, pos, tBlockState, 0);
			world.func_175698_g(pos);
		}
		if (tBlockState.func_177230_c() == Blocks.field_150345_g || tBlockState.func_185904_a() == Material.field_151585_k)
			world.func_175656_a(pos, Blocks.field_150330_I.func_176223_P());
		if (tBlockState.func_177230_c() == Blocks.field_150375_by) {
			tBlockState.func_177230_c().func_176226_b(world, pos, tBlockState, 0);
			world.func_175698_g(pos);
		}
		if (tBlockState.func_177230_c() == Blocks.field_150341_Y)
			world.func_175656_a(pos, Blocks.field_150347_e.func_176223_P());
		if (tBlockState.func_177230_c() == Blocks.field_150349_c || tBlockState.func_185904_a() == Material.field_151577_b)
			world.func_175656_a(pos, Blocks.field_150346_d.func_176223_P().func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.COARSE_DIRT));
		if(tBlockState.func_177230_c() == Blocks.field_150458_ak || tBlockState.func_177230_c() == Blocks.field_150346_d){
			world.func_175656_a(pos, Blocks.field_150354_m.func_176223_P());
		}

		if(sourRain && world.func_72896_J() && (tBlockState.func_177230_c() == Blocks.field_150348_b || tBlockState.func_177230_c() == Blocks.field_150351_n || tBlockState.func_177230_c() == Blocks.field_150347_e) &&
				world.func_180495_p(pos.func_177982_a(0, 1, 0)) == Blocks.field_150350_a && world.func_175710_j(pos)){
			if(tBlockState.func_177230_c() == Blocks.field_150348_b){world.func_175656_a(pos, Blocks.field_150347_e.func_176223_P());	}
			else if(tBlockState.func_177230_c() == Blocks.field_150347_e){world.func_175656_a(pos, Blocks.field_150351_n.func_176223_P());	}
			else if(tBlockState.func_177230_c() == Blocks.field_150351_n){world.func_175656_a(pos, Blocks.field_150354_m.func_176223_P());	}
		}
	}

	public static void addPollution(BlockPos aPos, int aPollution){
		if(!GT_Mod.gregtechproxy.mPollution)return;
		try{
			int[] tData = new int[2];
            ChunkPos chunkPos = new ChunkPos(aPos.func_177958_n() / 16, aPos.func_177952_p() / 16);
			if(GT_Proxy.chunkData.containsKey(chunkPos)){
				tData = GT_Proxy.chunkData.get(chunkPos);
				if(tData.length>1){
					tData[1] += aPollution;
				}
			}else{
				tData[1] += aPollution;
				GT_Proxy.chunkData.put(chunkPos, tData);
			}
		}catch(Exception e){
		}
	}
}
