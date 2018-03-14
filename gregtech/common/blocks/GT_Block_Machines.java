package gregtech.common.blocks;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IDebugableBlock;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.items.GT_Generic_Block;
import gregtech.api.metatileentity.BaseMetaPipeEntity;
import gregtech.api.metatileentity.BaseMetaTileEntity;
import gregtech.api.metatileentity.BaseTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_TieredMachineBlock;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_Utility;
import gregtech.common.render.GT_Renderer_Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GT_Block_Machines extends GT_Generic_Block implements IDebugableBlock, ITileEntityProvider {

    public GT_Block_Machines() {
        super(GT_Item_Machines.class, "gt.blockmachines", new GT_Material_Machines());
        GregTech_API.registerMachineBlock(this, -1);
        func_149711_c(1.0F);
        func_149752_b(10.0F);
        func_149672_a(SoundType.field_185852_e);
        func_149647_a(GregTech_API.TAB_GREGTECH);
        this.field_149758_A = true;
    }

    public String getHarvestTool(IBlockState blockState) {
        switch (blockState.func_177229_b(METADATA) / 4) {
            case 0:
                return "wrench";
            case 1:
                return "wrench";
            case 2:
                return "cutter";
            case 3:
                return "axe";
        }
        return "wrench";
    }

    @Override
    public int getHarvestLevel(IBlockState aMeta) {
        return aMeta.func_177229_b(METADATA) % 4;
    }

    @Override
    protected boolean func_149700_E() {
        return false;
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        TileEntity tTileEntity = world.func_175625_s(pos);
        if ((tTileEntity instanceof BaseTileEntity)) {
            ((BaseTileEntity) tTileEntity).onAdjacentBlockChange(neighbor.func_177958_n(), neighbor.func_177956_o(), neighbor.func_177952_p());
        }
    }

    @Override
    public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
        if(GregTech_API.isMachineBlock(this, state.func_177229_b(METADATA))) {
            GregTech_API.causeMachineUpdate(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        }
    }

    @Override
    public String func_149739_a() {
        return "gt.blockmachines";
    }

    @Override
    public String func_149732_F() {
        return GT_LanguageManager.getTranslation(func_149739_a() + ".name");
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 0;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return (GregTech_API.sMachineFlammable) && (world.func_180495_p(pos).func_177229_b(METADATA) == 0) ? 100 : 0;
    }

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return (GregTech_API.sMachineFlammable) && (world.func_180495_p(pos).func_177229_b(METADATA) == 0);
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        return (GregTech_API.sMachineFlammable) && (world.func_180495_p(pos).func_177229_b(METADATA) == 0);
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    /**
     * NB: Vanilla redstone behavior is that wires will connect to redstone emitters but not consumers.
     * Thus we only say we can connect if we are an emitter. (This is usually delegated to a cover behavior.)
     *
     */
    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        IGregTechTileEntity gregTechTileEntity = getGregTile(world, pos);
        return gregTechTileEntity != null && gregTechTileEntity.canOutputRedstone((byte) side.func_176734_d().func_176745_a());
    }

    @Override
    public boolean func_149744_f(IBlockState state) {
        return true;
    }

    @Override
    public boolean func_149721_r(IBlockState state) {
        return false;
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean func_176214_u() {
        return false;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return func_149915_a(world, state.func_177229_b(METADATA));
    }

    public IGregTechTileEntity getGregTile(IBlockAccess world, BlockPos blockPos) {
        TileEntity tileEntity = world.func_175625_s(blockPos);
        if(tileEntity instanceof IGregTechTileEntity) {
            IGregTechTileEntity gregTechTile = (IGregTechTileEntity) tileEntity;
            if(gregTechTile.getMetaTileEntity() != null) return gregTechTile;
        }
        return null;
    }

    @Override
    public boolean func_189539_a(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        TileEntity tTileEntity = worldIn.func_175625_s(pos);
        return tTileEntity != null && tTileEntity.func_145842_c(id, param);
    }

    @Override
    public void func_185477_a(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
        IGregTechTileEntity greg = getGregTile(worldIn, pos);
        if(greg != null) {
            greg.addCollisionBoxesToList(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), entityBox, collidingBoxes, entityIn);
            return;
        }
        super.func_185477_a(state, worldIn, pos, entityBox, collidingBoxes, entityIn);
    }

    @Nullable
    @Override
    public AxisAlignedBB func_180646_a(IBlockState blockState, World worldIn, BlockPos pos) {
        IGregTechTileEntity greg = getGregTile(worldIn, pos);
        if(greg != null) {
            return greg.getCollisionBoundingBoxFromPool(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        }
        return super.func_180646_a(blockState, worldIn, pos);
    }

    @Override
    public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        IGregTechTileEntity greg = getGregTile(worldIn, pos);
        if(greg != null) {
            greg.onEntityCollidedWithBlock(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), entityIn);
        }
        super.func_180634_a(worldIn, pos, state, entityIn);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        IGregTechTileEntity greg = getGregTile(world, pos);
        if(greg != null) {
            ArrayList<ItemStack> drops = greg.getDrops();
            if(!drops.isEmpty()) return drops.get(0);
        }
        return null;
    }



    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap aIconRegister) {
        if (GregTech_API.sPostloadFinished) {
            GT_Log.out.println("GT_Mod: Registering MetaTileEntity specific Textures");
            for (IMetaTileEntity tMetaTileEntity : GregTech_API.METATILEENTITIES) {
                try {
                    if (tMetaTileEntity != null) {
                        tMetaTileEntity.registerIcons(aIconRegister);
                    }
                } catch (Throwable e) {
                    e.printStackTrace(GT_Log.err);
                }
            }
        }
    }

    @Override
    public float func_180647_a(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
        IGregTechTileEntity gregTile = getGregTile(worldIn, pos);
        if(gregTile != null && gregTile instanceof BaseMetaTileEntity) {
            BaseMetaTileEntity metaTile = (BaseMetaTileEntity) gregTile;
            if(metaTile.privateAccess() && !metaTile.getOwnerName().equals(player.func_70005_c_())) {
                return - 1.0F;
            }
        }

        return super.func_180647_a(state, player, worldIn, pos);
    }

    @Override
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        IGregTechTileEntity gregTechTileEntity = getGregTile(worldIn, pos);
        if (gregTechTileEntity == null) {
            return false;
        }
        if (playerIn.func_70093_af()) {
            ItemStack handItem = playerIn.field_71071_by.func_70448_g();
            return handItem != null && GT_Utility.isStackInList(handItem, GregTech_API.sScrewdriverList);
        }
        if (gregTechTileEntity.getTimer() < 50L) {
            return false;
        }
        if (gregTechTileEntity.func_70300_a(playerIn)) {
            return gregTechTileEntity.onRightclick(playerIn, (byte) side.func_176745_a(), hitX, hitY, hitZ, EnumHand.MAIN_HAND);
        }
        return false;
    }



    @Override
    public void func_180649_a(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        IGregTechTileEntity gregTechTileEntity = getGregTile(worldIn, pos);
        if(gregTechTileEntity != null) {
            gregTechTileEntity.onLeftclick(playerIn);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getParticleSprite(IBlockAccess worldObj, BlockPos aPos, EnumFacing side) {
        IGregTechTileEntity tileEntity = getGregTile(worldObj, aPos);
        if(tileEntity != null) {
            IMetaTileEntity metaTileEntity = tileEntity.getMetaTileEntity();
            if(metaTileEntity instanceof GT_MetaTileEntity_TieredMachineBlock) {
                return Textures.BlockIcons.MACHINECASINGS_TOP[((GT_MetaTileEntity_TieredMachineBlock) metaTileEntity).mTier].getIcon();
            } else {
                return Textures.BlockIcons.MACHINECASINGS_TOP[1].getIcon();
            }

        }
        return null;
    }

    @Override
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
        IGregTechTileEntity gregTechTileEntity = getGregTile(world, pos);
        if(gregTechTileEntity != null && gregTechTileEntity instanceof BaseMetaTileEntity) {
            ((BaseMetaTileEntity) gregTechTileEntity).doEnergyExplosion();
        }
        super.onBlockExploded(world, pos, explosion);
    }

    @Override
    public void func_180657_a(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
        player.func_71029_a(StatList.func_188055_a(this));
        player.func_71020_j(0.025F);

        if(te instanceof IGregTechTileEntity) {
            List<ItemStack> itemstacks = getDrops((IGregTechTileEntity) te);
            for(ItemStack itemStack : itemstacks) {
                func_180635_a(worldIn, pos, itemStack);
            }
        }
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        return Collections.EMPTY_LIST;
    }

    public List<ItemStack> getDrops(IGregTechTileEntity tGregTechTileEntity) {
        ArrayList<ItemStack> aDrops = new ArrayList<>();
        for (int i = 0; i < tGregTechTileEntity.func_70302_i_(); i++) {
            ItemStack tItem = tGregTechTileEntity.func_70301_a(i);
            if ((tItem != null) && (tItem.field_77994_a > 0) && (tGregTechTileEntity.isValidSlot(i))) {
                aDrops.add(tItem);
                tGregTechTileEntity.func_70299_a(i, null);
            }
        }
        aDrops.addAll(tGregTechTileEntity.getDrops());
        return aDrops;
    }

    @Override
    public int func_180641_l(IBlockState blockState, World worldIn, BlockPos pos) {
        IGregTechTileEntity gregTechTileEntity = (IGregTechTileEntity) worldIn.func_175625_s(pos);
        if(gregTechTileEntity != null) {
            return gregTechTileEntity.getComparatorValue((byte) 0);
        }
        return 0;
    }

    @Override
    public int func_180656_a(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IGregTechTileEntity gregTechTileEntity = getGregTile(blockAccess, pos);
        if(gregTechTileEntity != null) {
            return gregTechTileEntity.getOutputRedstoneSignal((byte) side.func_176734_d().func_176745_a());
        }
        return 0;
    }

    @Override
    public int func_176211_b(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IGregTechTileEntity gregTechTileEntity = getGregTile(blockAccess, pos);
        if(gregTechTileEntity != null) {
            return gregTechTileEntity.getStrongOutputRedstoneSignal((byte) side.func_176734_d().func_176745_a());
        }
        return 0;
    }


    @Override
    public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        if(!worldIn.field_72995_K) {
            IGregTechTileEntity gregTechTileEntity = getGregTile(worldIn, pos);
            if(gregTechTileEntity instanceof BaseMetaTileEntity && GregTech_API.sMachineNonWrenchExplosions)
                ((BaseMetaTileEntity) gregTechTileEntity).doEnergyExplosion();
        }
    }

    @Override
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        if (base_state.func_177229_b(METADATA) == 0) {
            return true;
        }
        TileEntity tTileEntity = world.func_175625_s(pos);
        if (tTileEntity != null) {
            if ((tTileEntity instanceof BaseMetaTileEntity)) {
                return true;
            }
            if (((tTileEntity instanceof BaseMetaPipeEntity)) && ((((BaseMetaPipeEntity) tTileEntity).mConnections & 0xFFFFFFC0) != 0)) {
                return true;
            }
            if (((tTileEntity instanceof ICoverable)) && (((ICoverable) tTileEntity).getCoverIDAtSide((byte) side.func_176745_a()) != 0)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
        IGregTechTileEntity gregTechTileEntity = getGregTile(world, pos);
        if(gregTechTileEntity != null) {
            return gregTechTileEntity.getLightOpacity();
        }
        return state.func_177229_b(METADATA) == 0 ? 255 : 0;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        IGregTechTileEntity gregTechTileEntity = getGregTile(world, pos);
        if(gregTechTileEntity instanceof BaseMetaTileEntity) {
            return ((BaseMetaTileEntity) gregTechTileEntity).getLightValue();
        }
        return 0;
    }

    @Override
    public TileEntity func_149915_a(World aWorld, int aMeta) {
        if (aMeta < 4) {
            return GregTech_API.constructBaseMetaTileEntity();
        }
        return new BaseMetaPipeEntity();
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
        IGregTechTileEntity gregTechTileEntity = getGregTile(world, pos);
        if(gregTechTileEntity != null) {
            return gregTechTileEntity.getBlastResistance((byte) 6);
        }
        return 10.0F;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
        for (int i = 1; i < GregTech_API.METATILEENTITIES.length; i++) {
            if (GregTech_API.METATILEENTITIES[i] != null) {
                par3List.add(new ItemStack(par1, 1, i));
            }
        }
    }

    @Override
    public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        IGregTechTileEntity gregTechTileEntity = getGregTile(worldIn, pos);
        if(gregTechTileEntity != null) {
            if(placer == null) {
                gregTechTileEntity.setFrontFacing((byte) 1);
            } else {
                int var7 = MathHelper.func_76128_c(placer.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
                int var8 = Math.round(placer.field_70125_A);
                if ((var8 >= 65) && (gregTechTileEntity.isValidFacing((byte) 1))) {
                    gregTechTileEntity.setFrontFacing((byte) 1);
                } else if ((var8 <= -65) && (gregTechTileEntity.isValidFacing((byte) 0))) {
                    gregTechTileEntity.setFrontFacing((byte) 0);
                } else {
                    switch (var7) {
                        case 0:
                            gregTechTileEntity.setFrontFacing((byte) 2);
                            break;
                        case 1:
                            gregTechTileEntity.setFrontFacing((byte) 5);
                            break;
                        case 2:
                            gregTechTileEntity.setFrontFacing((byte) 3);
                            break;
                        case 3:
                            gregTechTileEntity.setFrontFacing((byte) 4);
                    }
                }
            }
        }
    }

    @Override
    public ArrayList<String> getDebugInfo(EntityPlayer placer, int aX, int aY, int aZ, int aLogLevel) {
        TileEntity tTileEntity = placer.field_70170_p.func_175625_s(new BlockPos(aX, aY, aZ));
        if ((tTileEntity instanceof BaseMetaTileEntity)) {
            return ((BaseMetaTileEntity) tTileEntity).getDebugInfo(placer, aLogLevel);
        }
        if ((tTileEntity instanceof BaseMetaPipeEntity)) {
            return ((BaseMetaPipeEntity) tTileEntity).getDebugInfo(placer, aLogLevel);
        }
        return null;
    }

    @Override
    public boolean recolorBlock(World aWorld, BlockPos blockPos, EnumFacing aSide, EnumDyeColor colorDye) {
        int aColor = colorDye.func_176765_a();
        TileEntity tTileEntity = aWorld.func_175625_s(blockPos);
        if ((tTileEntity instanceof IGregTechTileEntity)) {
            if (((IGregTechTileEntity) tTileEntity).getColorization() == (byte) ((~aColor) & 0xF)) {
                return false;
            }
            ((IGregTechTileEntity) tTileEntity).setColorization((byte) ((~aColor) & 0xF));
            return true;
        }
        return false;
    }

    @Override
    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType func_149645_b(IBlockState state) {
        return GT_Renderer_Block.INSTANCE.renderType;
    }

    @Override
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.CUTOUT;
    }
}
