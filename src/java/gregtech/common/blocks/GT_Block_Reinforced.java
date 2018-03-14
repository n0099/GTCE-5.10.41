package gregtech.common.blocks;

import com.google.common.collect.Lists;
import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.Textures;
import gregtech.api.items.GT_Generic_Block;
import gregtech.api.objects.ItemData;
import gregtech.api.objects.MaterialStack;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class GT_Block_Reinforced extends GT_Generic_Block {

    private TextureAtlasSprite COAL_BLOCK_ICON_DATA;

    public GT_Block_Reinforced(String aName) {
        super(GT_Item_Storage.class, aName, new GT_Material_Reinforced());
        func_149672_a(SoundType.field_185851_d);
        func_149647_a(GregTech_API.TAB_GREGTECH);
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".0.name", "Bronzeplate Reinforced Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".1.name", "Iridium-Tungstensteel Reinforced Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".2.name", "Plascrete Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".3.name", "Tungstensteel Reinforced Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".4.name", "Brittle Charcoal");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".5.name", "Powderbarrel");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".6.name", "Solid Super Fuel");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".7.name", "Magic Solid Super Fuel");
        ItemList.Block_BronzePlate.set(new ItemStack(this.func_149711_c(60.0f).func_149752_b(150.0f), 1, 0));
        ItemList.Block_IridiumTungstensteel.set(new ItemStack(this.func_149711_c(200.0f).func_149752_b(600.0f), 1, 1));
        ItemList.Block_Plascrete.set(new ItemStack(this.func_149711_c(80.0f).func_149752_b(350.0f), 1, 2));
        ItemList.Block_TungstenSteelReinforced.set(new ItemStack(this.func_149711_c(100.0f).func_149752_b(400.0f), 1, 3));
        ItemList.Block_BrittleCharcoal.set(new ItemStack(this.func_149711_c(0.5f).func_149752_b(8.0f), 1, 4));
        ItemList.Block_Powderbarrel.set(new ItemStack(this.func_149711_c(2.5f).func_149752_b(2.0f), 1, 5));
        ItemList.Block_SSFUEL.set(new ItemStack(this.func_149711_c(2.5f).func_149752_b(2.0f), 1, 6));
        ItemList.Block_MSSFUEL.set(new ItemStack(this.func_149711_c(2.5f).func_149752_b(2.0f), 1, 7));
        GT_ModHandler.addCraftingRecipe(ItemList.Block_BronzePlate.get(1L),GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"hP ", "PBP", " P ", 'P', OrePrefixes.plate.get(Materials.Bronze), 'B', OrePrefixes.stone.get(Materials.GraniteBlack)});
        GT_ModHandler.addCraftingRecipe(ItemList.Block_BronzePlate.get(1L),GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"hP ", "PBP", " P ", 'P', OrePrefixes.plate.get(Materials.Bronze), 'B', OrePrefixes.stone.get(Materials.GraniteRed)});
        GT_ModHandler.addCraftingRecipe(ItemList.Block_IridiumTungstensteel.get(1L),GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"hBP", 'P', OrePrefixes.plate.get(Materials.Iridium), 'B', ItemList.Block_TungstenSteelReinforced.get(1L)});
        GT_OreDictUnificator.setItemData(ItemList.Block_IridiumTungstensteel.get(1), new ItemData(new MaterialStack(Materials.Iridium, OrePrefixes.plate.mMaterialAmount), new MaterialStack(Materials.TungstenSteel, 2*OrePrefixes.plate.mMaterialAmount),new MaterialStack(Materials.Concrete, OrePrefixes.dust.mMaterialAmount)));
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Items.field_151044_h, 1, 1), new Object[]{ItemList.Block_BrittleCharcoal.get(1)});
        GT_ModHandler.addCraftingRecipe(ItemList.Block_Powderbarrel.get(1L),GT_ModHandler.RecipeBits.REVERSIBLE, new Object[]{"WSW","GGG","WGW", 'W', OrePrefixes.plank.get(Materials.Wood), 'G', new ItemStack(Items.field_151016_H,1),'S',new ItemStack(Items.field_151007_F ,1)});

    }

    @Override
    public String getHarvestTool(IBlockState state) {
        int aMeta = state.func_177229_b(METADATA);
        if (aMeta == 5 || aMeta == 4 || aMeta == 6 || aMeta == 7) return "axe";
        return "pickaxe";
    }

    @Override
    public int getHarvestLevel(IBlockState blockState) {
        int aMeta = blockState.func_177229_b(METADATA);
        if (aMeta == 4||aMeta == 5 || aMeta == 6 || aMeta == 7) return 1;
        return 4;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getIcon(EnumFacing aSide, int aMeta) {
        if ((aMeta >= 0) && (aMeta < 16)) {
            switch (aMeta) {
                case 0:
                    return Textures.BlockIcons.BLOCK_BRONZEPREIN.getIcon();
                case 1:
                    return Textures.BlockIcons.BLOCK_IRREIN.getIcon();
                case 2:
                    return Textures.BlockIcons.BLOCK_PLASCRETE.getIcon();
                case 3:
                    return Textures.BlockIcons.BLOCK_TSREIN.getIcon();
                case 4:
                    return COAL_BLOCK_ICON_DATA;
                case 5:
                	return Textures.BlockIcons.COVER_WOOD_PLATE.getIcon();
                case 6:
                case 7:
                	return COAL_BLOCK_ICON_DATA;
            }
        }
        return Textures.BlockIcons.MACHINE_CASING_SOLID_STEEL.getIcon();
    }

    @Override
    public void registerIcons(TextureMap map) {
        COAL_BLOCK_ICON_DATA = map.func_174942_a(new ResourceLocation("minecraft:blocks/coal_block"));
    }

    @Override
    public float func_176195_g(IBlockState blockState, World worldIn, BlockPos pos) {
        int tMeta = blockState.func_177229_b(METADATA);
        if (tMeta == 0) {
            return 60.0F;
        }
        if (tMeta == 1) {
            return 200.0F;
        }
        if (tMeta == 2) {
            return 80.0F;
        }
        if (tMeta == 3) {
            return 100.0F;
        }
        if (tMeta == 4||tMeta == 5 || tMeta == 6 || tMeta == 7) {
            return 0.5F;
        }
        return Blocks.field_150339_S.func_176195_g(blockState, worldIn, pos);
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
        int tMeta = world.func_180495_p(pos).func_177229_b(METADATA);
        if (tMeta == 0) {
            return 150.0F;
        }
        if (tMeta == 1) {
            return 600.0F;
        }
        if (tMeta == 2) {
            return 350.0F;
        }
        if (tMeta == 3) {
            return 400.0F;
        }
        if (tMeta == 4 || tMeta == 6 || tMeta == 7) {
            return 8.0F;
        }
        if (tMeta == 5) {
            return 1.0F;
        }
        return Blocks.field_150339_S.getExplosionResistance(world, pos, exploder, explosion);
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
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        return Lists.newArrayList(new ItemStack(this, 1, state.func_177229_b(METADATA)));
    }

    @Override
    public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        if(state.func_177229_b(METADATA) == 4) {
            func_180635_a(worldIn, pos, new ItemStack(Items.field_151044_h, worldIn.field_73012_v.nextInt(2) + 1, 1));
        } else  {
            super.func_180653_a(worldIn, pos, state, chance, fortune);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item aItem, CreativeTabs par2CreativeTabs, List<ItemStack> aList) {
        for (int i = 0; i < 16; i++) {
            ItemStack aStack = new ItemStack(aItem, 1, i);
            if (!aStack.func_82833_r().contains(".name")) aList.add(aStack);
        }
    }
}
