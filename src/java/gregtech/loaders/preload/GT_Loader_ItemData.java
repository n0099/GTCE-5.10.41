package gregtech.loaders.preload;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.objects.ItemData;
import gregtech.api.objects.MaterialStack;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import ic2.core.item.type.NuclearResourceType;
import ic2.core.ref.ItemName;
import ic2.core.ref.TeBlock;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GT_Loader_ItemData
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Loading Item Data Tags");
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("TwilightForest", "item.giantPick", 1, 0), new ItemData(Materials.Stone, 696729600L, new MaterialStack(Materials.Wood, 464486400L)));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("TwilightForest", "item.giantSword", 1, 0), new ItemData(Materials.Stone, 464486400L, new MaterialStack(Materials.Wood, 232243200L)));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("TwilightForest", "tile.GiantLog", 1, 32767), new ItemData(Materials.Wood, 232243200L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("TwilightForest", "tile.GiantCobble", 1, 32767), new ItemData(Materials.Stone, 232243200L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("TwilightForest", "tile.GiantObsidian", 1, 32767), new ItemData(Materials.Obsidian, 232243200L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("TwilightForest", "item.minotaurAxe", 1, 0), new ItemData(Materials.Diamond, 14515200L, new MaterialStack(Materials.Wood, OrePrefixes.stick.mMaterialAmount * 2L)));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("TwilightForest", "item.armorShards", 1, 0), new ItemData(Materials.Knightmetal, 403200L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("TwilightForest", "item.shardCluster", 1, 0), new ItemData(Materials.Knightmetal, 3628800L));
        GT_OreDictUnificator.addItemData(ItemList.TF_LiveRoot.get(1), new ItemData(Materials.LiveRoot, 3628800L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 10), new ItemData(Materials.CertusQuartz, 1814400L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 11), new ItemData(Materials.NetherQuartz, 1814400L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 12), new ItemData(Materials.Fluix, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150371_ca, 1, 32767), new ItemData(Materials.NetherQuartz, 14515200L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "tile.BlockQuartz", 1, 32767), new ItemData(Materials.CertusQuartz, 14515200L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "tile.BlockQuartzPillar", 1, 32767), new ItemData(Materials.CertusQuartz, 14515200L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("appliedenergistics2", "tile.BlockQuartzChiseled", 1, 32767), new ItemData(Materials.CertusQuartz, 14515200L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151015_O, 1, 32767), new ItemData(Materials.Wheat, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150407_cf, 1, 32767), new ItemData(Materials.Wheat, 32659200L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151126_ay, 1, 32767), new ItemData(Materials.Snow, 907200L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150433_aE, 1, 32767), new ItemData(Materials.Snow, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150426_aN, 1, 32767), new ItemData(Materials.Glowstone, 14515200L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150379_bu, 1, 32767), new ItemData(Materials.Glowstone, 14515200L, new MaterialStack(Materials.Redstone, OrePrefixes.dust.mMaterialAmount * 4L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150374_bv, 1, 32767), new ItemData(Materials.Glowstone, 14515200L, new MaterialStack(Materials.Redstone, OrePrefixes.dust.mMaterialAmount * 4L)));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("Forestry", "craftingMaterial", 1, 5), new ItemData(Materials.Ice, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150432_aD, 1, 32767), new ItemData(Materials.Ice, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150403_cj, 1, 32767), new ItemData(Materials.Ice, 7257600L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151119_aD, 1, 32767), new ItemData(Materials.Clay, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150435_aG, 1, 32767), new ItemData(Materials.Clay, 7257600L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150405_ch, 1, 32767), new ItemData(Materials.Clay, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150406_ce, 1, 32767), new ItemData(Materials.Clay, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150336_V, 1, 32767), new ItemData(Materials.Clay, 3628800L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getIC2Item(ItemName.nuclear, NuclearResourceType.uranium_238, 1), new ItemData(Materials.Uranium, 3628800L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getIC2Item(ItemName.nuclear, NuclearResourceType.uranium_235, 1), new ItemData(Materials.Uranium235, 3628800L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getIC2Item(ItemName.nuclear, NuclearResourceType.plutonium, 1), new ItemData(Materials.Plutonium, 3628800L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getIC2Item(ItemName.nuclear, NuclearResourceType.small_uranium_235, 1), new ItemData(Materials.Uranium235, 403200L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getIC2Item(ItemName.nuclear, NuclearResourceType.small_plutonium, 1), new ItemData(Materials.Plutonium, 403200L));
        GT_OreDictUnificator.addItemData(ItemList.IC2_Item_Casing_Iron.get(1), new ItemData(Materials.Iron, 1814400L));
        GT_OreDictUnificator.addItemData(ItemList.IC2_Item_Casing_Gold.get(1), new ItemData(Materials.Gold, 1814400L));
        GT_OreDictUnificator.addItemData(ItemList.IC2_Item_Casing_Bronze.get(1), new ItemData(Materials.Bronze, 1814400L));
        GT_OreDictUnificator.addItemData(ItemList.IC2_Item_Casing_Copper.get(1), new ItemData(Materials.Copper, 1814400L));
        GT_OreDictUnificator.addItemData(ItemList.IC2_Item_Casing_Tin.get(1), new ItemData(Materials.Tin, 1814400L));
        GT_OreDictUnificator.addItemData(ItemList.IC2_Item_Casing_Lead.get(1), new ItemData(Materials.Lead, 1814400L));
        GT_OreDictUnificator.addItemData(ItemList.IC2_Item_Casing_Steel.get(1), new ItemData(Materials.Steel, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151122_aG, 1, 32767), new ItemData(Materials.Paper, 10886400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151164_bB, 1, 32767), new ItemData(Materials.Paper, 10886400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151099_bA, 1, 32767), new ItemData(Materials.Paper, 10886400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151134_bR, 1, 32767), new ItemData(Materials.Paper, 10886400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151153_ao, 1, 1), new ItemData(Materials.Gold, OrePrefixes.block.mMaterialAmount * 8L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151153_ao, 1, 0), new ItemData(Materials.Gold, OrePrefixes.ingot.mMaterialAmount * 8L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151074_bl, 1, 0), new ItemData(Materials.Gold, OrePrefixes.nugget.mMaterialAmount * 8L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151060_bw, 1, 0), new ItemData(Materials.Gold, OrePrefixes.nugget.mMaterialAmount * 8L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151143_au, 1), new ItemData(Materials.Iron, 18144000L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151066_bu, 1), new ItemData(Materials.Iron, 25401600L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150411_aY, 8, 32767), new ItemData(Materials.Iron, 10886400L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getIC2TEItem(TeBlock.iron_furnace, 1), new ItemData(Materials.Iron, 18144000L));
        GT_OreDictUnificator.addItemData(ItemList.IC2_Food_Can_Empty.get(1), new ItemData(Materials.Tin, 1814400L));
        GT_OreDictUnificator.addItemData(ItemList.IC2_Fuel_Rod_Empty.get(1), new ItemData(Materials.Iron, 3628800L));
        GT_OreDictUnificator.addItemData(ItemList.IC2_Fuel_Can_Empty.get(1), new ItemData(Materials.Tin, 25401600L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150445_bS, 1, 32767), new ItemData(Materials.Gold, 7257600L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150443_bT, 1, 32767), new ItemData(Materials.Iron, 7257600L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("Railcraft", "tile.railcraft.anvil", 1, 0), new ItemData(Materials.Steel, 108864000L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("Railcraft", "tile.railcraft.anvil", 1, 1), new ItemData(Materials.Steel, 72576000L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("Railcraft", "tile.railcraft.anvil", 1, 2), new ItemData(Materials.Steel, 36288000L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150467_bQ, 1, 0), new ItemData(Materials.Iron, 108864000L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150467_bQ, 1, 1), new ItemData(Materials.Iron, 72576000L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150467_bQ, 1, 2), new ItemData(Materials.Iron, 36288000L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150438_bZ, 1, 32767), new ItemData(Materials.Iron, 18144000L, new MaterialStack(Materials.Wood, 29030400L)));
        GT_OreDictUnificator.addItemData(ItemList.Cell_Universal_Fluid.get(1), new ItemData(Materials.Tin, 7257600L, new MaterialStack(Materials.Glass, 1360800L)));
        GT_OreDictUnificator.addItemData(ItemList.Cell_Empty.get(1), new ItemData(Materials.Tin, 7257600L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150479_bC, 1, 32767), new ItemData(Materials.Iron, OrePrefixes.ring.mMaterialAmount * 2L, new MaterialStack(Materials.Wood, 3628800L)));
        GT_OreDictUnificator.addItemData(ItemList.Bottle_Empty.get(1), new ItemData(Materials.Glass, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151068_bn, 1, 32767), new ItemData(Materials.Glass, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150399_cn, 1, 32767), new ItemData(Materials.Glass, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150359_w, 1, 32767), new ItemData(Materials.Glass, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150397_co, 1, 32767), new ItemData(Materials.Glass, 1360800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150410_aZ, 1, 32767), new ItemData(Materials.Glass, 1360800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151113_aN, 1, 32767), new ItemData(Materials.Gold, 14515200L, new MaterialStack(Materials.Redstone, 3628800L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151111_aL, 1, 32767), new ItemData(Materials.Iron, 14515200L, new MaterialStack(Materials.Redstone, 3628800L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151138_bX, 1, 32767), new ItemData(Materials.Iron, 29030400L, new MaterialStack(Materials.Leather, 21772800L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151136_bY, 1, 32767), new ItemData(Materials.Gold, 29030400L, new MaterialStack(Materials.Leather, 21772800L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151058_ca, 1, 32767), new ItemData(Materials.Diamond, 29030400L, new MaterialStack(Materials.Leather, 21772800L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151116_aA, 1, 32767), new ItemData(Materials.Leather, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150461_bJ, 1, 32767), new ItemData(Materials.NetherStar, 3628800L, new MaterialStack(Materials.Obsidian, 10886400L), new MaterialStack(Materials.Glass, 18144000L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150381_bn, 1, 32767), new ItemData(Materials.Diamond, 7257600L, new MaterialStack(Materials.Obsidian, 14515200L), new MaterialStack(Materials.Paper, 10886400L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150477_bB, 1, 32767), new ItemData(Materials.EnderEye, 3628800L, new MaterialStack(Materials.Obsidian, 29030400L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150342_X, 1, 32767), new ItemData(Materials.Paper, 32659200L, new MaterialStack(Materials.Wood, 21772800L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150442_at, 1, 32767), new ItemData(Materials.Stone, 3628800L, new MaterialStack(Materials.Wood, 1814400L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150432_aD, 1, 32767), new ItemData(Materials.Ice, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150403_cj, 1, 32767), new ItemData(Materials.Ice, 7257600L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150433_aE, 1, 32767), new ItemData(Materials.Snow, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151126_ay, 1, 32767), new ItemData(Materials.Snow, 907200L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150431_aC, 1, 32767), new ItemData(Materials.Snow, -1));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150354_m, 1, 32767), new ItemData(Materials.Sand, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150322_A, 1, 32767), new ItemData(Materials.Sand, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150333_U, 1, 0), new ItemData(Materials.Stone, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150333_U, 1, 8), new ItemData(Materials.Stone, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150334_T, 1, 0), new ItemData(Materials.Stone, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150334_T, 1, 8), new ItemData(Materials.Stone, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150333_U, 1, 1), new ItemData(Materials.Sand, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150333_U, 1, 9), new ItemData(Materials.Sand, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150334_T, 1, 1), new ItemData(Materials.Sand, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150334_T, 1, 9), new ItemData(Materials.Sand, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150333_U, 1, 2), new ItemData(Materials.Wood, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150333_U, 1, 10), new ItemData(Materials.Wood, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150334_T, 1, 2), new ItemData(Materials.Wood, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150334_T, 1, 10), new ItemData(Materials.Wood, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150333_U, 1, 3), new ItemData(Materials.Stone, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150333_U, 1, 11), new ItemData(Materials.Stone, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150334_T, 1, 3), new ItemData(Materials.Stone, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150334_T, 1, 11), new ItemData(Materials.Stone, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150333_U, 1, 5), new ItemData(Materials.Stone, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150333_U, 1, 13), new ItemData(Materials.Stone, 1814400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150334_T, 1, 5), new ItemData(Materials.Stone, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150334_T, 1, 13), new ItemData(Materials.Stone, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150460_al, 1, 32767), new ItemData(Materials.Stone, 29030400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150470_am, 1, 32767), new ItemData(Materials.Stone, 29030400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150417_aV, 1, 32767), new ItemData(Materials.Stone, 3628800L));

        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150348_b, 1, 0), new ItemData(Materials.Stone, 3628800L));
        //added automatically in Loader_OreDictionary
        //GT_OreDictUnificator.addItemData(new ItemStack(Blocks.STONE, 1, 1), new ItemData(Materials.GraniteBlack, 3628800L));
        //GT_OreDictUnificator.addItemData(new ItemStack(Blocks.STONE, 1, 2), new ItemData(Materials.GraniteBlack, 3628800L));
        //GT_OreDictUnificator.addItemData(new ItemStack(Blocks.STONE, 1, 5), new ItemData(Materials.Diorite, 3628800L));
        //GT_OreDictUnificator.addItemData(new ItemStack(Blocks.STONE, 1, 6), new ItemData(Materials.Diorite, 3628800L));
        //GT_OreDictUnificator.addItemData(new ItemStack(Blocks.STONE, 1, 3), new ItemData(Materials.Andesite, 3628800L));
        //GT_OreDictUnificator.addItemData(new ItemStack(Blocks.STONE, 1, 4), new ItemData(Materials.Andesite, 3628800L));

        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150347_e, 1, 32767), new ItemData(Materials.Stone, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150341_Y, 1, 32767), new ItemData(Materials.Stone, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150430_aB, 1, 32767), new ItemData(Materials.Stone, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150456_au, 1, 32767), new ItemData(Materials.Stone, 7257600L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150468_ap, 1, 32767), new ItemData(Materials.Wood, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150471_bO, 1, 32767), new ItemData(Materials.Wood, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150452_aw, 1, 32767), new ItemData(Materials.Wood, 7257600L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_180407_aO, 1, 32767), new ItemData(Materials.Wood, 5443200L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151054_z, 1, 32767), new ItemData(Materials.Wood, 3628800L));
        GT_OreDictUnificator.addItemData(new ItemStack(Items.field_151155_ap, 1, 32767), new ItemData(Materials.Wood, 7257600L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150486_ae, 1, 32767), new ItemData(Materials.Wood, 29030400L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150447_bR, 1, 32767), new ItemData(Materials.Wood, 32659200L, new MaterialStack(Materials.Iron, OrePrefixes.ring.mMaterialAmount * 2L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150429_aA, 1, 32767), new ItemData(Materials.Wood, 1814400L, new MaterialStack(Materials.Redstone, 3628800L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150437_az, 1, 32767), new ItemData(Materials.Wood, 1814400L, new MaterialStack(Materials.Redstone, 3628800L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150323_B, 1, 32767), new ItemData(Materials.Wood, 29030400L, new MaterialStack(Materials.Redstone, 3628800L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150421_aI, 1, 32767), new ItemData(Materials.Wood, 29030400L, new MaterialStack(Materials.Diamond, 3628800L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150462_ai, 1, 32767), new ItemData(Materials.Wood, 14515200L));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150331_J, 1, 32767), new ItemData(Materials.Stone, 14515200L, new MaterialStack(Materials.Wood, 10886400L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150320_F, 1, 32767), new ItemData(Materials.Stone, 14515200L, new MaterialStack(Materials.Wood, 10886400L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150367_z, 1, 32767), new ItemData(Materials.Stone, 25401600L, new MaterialStack(Materials.Redstone, 3628800L)));
        GT_OreDictUnificator.addItemData(new ItemStack(Blocks.field_150409_cd, 1, 32767), new ItemData(Materials.Stone, 25401600L, new MaterialStack(Materials.Redstone, 3628800L)));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("Thaumcraft", "ItemNuggetChicken", 1, 32767), new ItemData(Materials.MeatCooked, 403200L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("Thaumcraft", "ItemNuggetBeef", 1, 32767), new ItemData(Materials.MeatCooked, 403200L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("Thaumcraft", "ItemNuggetPork", 1, 32767), new ItemData(Materials.MeatCooked, 403200L));
        GT_OreDictUnificator.addItemData(GT_ModHandler.getModItem("Thaumcraft", "ItemNuggetFish", 1, 32767), new ItemData(Materials.MeatCooked, 403200L));
        for (ItemStack tItem : new ItemStack[]{
                GT_ModHandler.getModItem("TwilightForest", "item.meefRaw", 1, 0),
                GT_ModHandler.getModItem("TwilightForest", "item.venisonRaw", 1, 0),
                new ItemStack(Items.field_151147_al),
                new ItemStack(Items.field_151082_bd),
                new ItemStack(Items.field_151076_bf),
                new ItemStack(Items.field_151115_aP)}) {
            if (tItem != null) {
                GT_OreDictUnificator.addItemData(GT_Utility.copyMetaData(OreDictionary.WILDCARD_VALUE, tItem),
                        new ItemData(Materials.MeatRaw, 3628800L,
                                new MaterialStack(Materials.Bone, 403200L)));
            }
        }
        for (ItemStack tItem : new ItemStack[]{
                GT_ModHandler.getModItem("TwilightForest", "item.meefSteak", 1, 0),
                GT_ModHandler.getModItem("TwilightForest", "item.venisonCooked", 1, 0),
                new ItemStack(Items.field_151157_am),
                new ItemStack(Items.field_151083_be),
                new ItemStack(Items.field_151077_bg),
                new ItemStack(Items.field_179566_aV)}) {
            if (tItem != null) {
                GT_OreDictUnificator.addItemData(
                        GT_Utility.copyMetaData(OreDictionary.WILDCARD_VALUE, tItem),
                        new ItemData(Materials.MeatCooked, 3628800L, new MaterialStack(Materials.Bone, 403200L)));
            }
        }
    }
}
