package gregtech.common.blocks;

import com.google.common.collect.Lists;
import gregtech.api.GregTech_API;
import gregtech.api.items.GT_Generic_Block;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public abstract class GT_Block_Casings_Abstract extends GT_Generic_Block {


    public GT_Block_Casings_Abstract(Class<? extends ItemBlock> aItemClass, String aName, Material aMaterial) {
        super(aItemClass, aName, aMaterial);
        func_149672_a(SoundType.field_185852_e);
        func_149647_a(GregTech_API.TAB_GREGTECH);
        GregTech_API.registerMachineBlock(this, -1);
        GT_LanguageManager.addStringLocalization(func_149739_a() + "." + 32767 + ".name", "Any Sub Block of this");
    }

    @Override
    public String getHarvestTool(IBlockState state) {
        return "wrench";
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return 2;
    }

    @Override
    public float func_176195_g(IBlockState blockState, World worldIn, BlockPos pos) {
        return Blocks.field_150339_S.func_176195_g(blockState, worldIn, pos);
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
        return Blocks.field_150339_S.getExplosionResistance(world, pos, exploder, explosion);
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return false;
    }

    protected boolean func_149700_E() {
        return false;
    }

    @Override
    public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
        if(GregTech_API.isMachineBlock(this, state.func_177229_b(METADATA))) {
            GregTech_API.causeMachineUpdate(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        }
    }

    @Override
    public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
        if(GregTech_API.isMachineBlock(this, state.func_177229_b(METADATA))) {
            GregTech_API.causeMachineUpdate(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        }
    }

    @Override
    public String func_149739_a() {
        return this.mUnlocalizedName;
    }

    @Override
    public String func_149732_F() {
        return GT_LanguageManager.getTranslation(this.mUnlocalizedName + ".name");
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        return Lists.newArrayList(new ItemStack(this, 1, state.func_177229_b(METADATA)));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void func_149666_a(Item aItem, CreativeTabs par2CreativeTabs, List<ItemStack> aList) {
        for (int i = 0; i < 16; i++) {
            ItemStack aStack = new ItemStack(aItem, 1, i);
            if (!aStack.func_82833_r().contains(".name")) aList.add(aStack);
        }
    }

}
