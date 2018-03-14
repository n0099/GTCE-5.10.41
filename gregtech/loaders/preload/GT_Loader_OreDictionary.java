package gregtech.loaders.preload;

import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OreDictNames;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import ic2.core.block.BlockTexGlass;
import ic2.core.block.type.ResourceBlock;
import ic2.core.block.wiring.CableType;
import ic2.core.item.block.ItemCable;
import ic2.core.item.type.CraftingItemType;
import ic2.core.item.type.IngotResourceType;
import ic2.core.item.type.PlateResourceType;
import ic2.core.ref.BlockName;
import ic2.core.ref.FluidName;
import ic2.core.ref.ItemName;
import ic2.core.ref.TeBlock;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;

public class GT_Loader_OreDictionary
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: Register OreDict Entries of Non-GT-Items.");
        GT_OreDictUnificator.set(OrePrefixes.cell, Materials.Empty, ItemList.Cell_Empty.get(1));
        GT_OreDictUnificator.set(OrePrefixes.cell, Materials.Lava, ItemList.Cell_Lava.get(1));
        GT_OreDictUnificator.set(OrePrefixes.cell, Materials.Lava, GT_ModHandler.getIC2Item(ItemName.fluid_cell, FluidRegistry.LAVA.getName(), 1));
        GT_OreDictUnificator.set(OrePrefixes.cell, Materials.Water, ItemList.Cell_Water.get(1));
        GT_OreDictUnificator.set(OrePrefixes.cell, Materials.Water, GT_ModHandler.getIC2Item(ItemName.fluid_cell, FluidRegistry.WATER.getName(), 1));
        GT_OreDictUnificator.set(OrePrefixes.cell, Materials.Creosote, GT_ModHandler.getModItem("Railcraft", "fluid.creosote.cell", 1));


        GT_OreDictUnificator.set(OrePrefixes.cell, Materials.UUMatter, GT_ModHandler.getIC2Item(ItemName.fluid_cell, FluidName.uu_matter, 1));
        GT_OreDictUnificator.set(OrePrefixes.cell, Materials.ConstructionFoam, GT_ModHandler.getIC2Item(ItemName.fluid_cell, FluidName.construction_foam, 1));

        GT_OreDictUnificator.set(OrePrefixes.bucket, Materials.Empty, new ItemStack(Items.field_151133_ar, 1, 0));
        GT_OreDictUnificator.set(OrePrefixes.bucket, Materials.Water, new ItemStack(Items.field_151131_as, 1, 0));
        GT_OreDictUnificator.set(OrePrefixes.bucket, Materials.Lava, new ItemStack(Items.field_151129_at, 1, 0));
        GT_OreDictUnificator.set(OrePrefixes.bucket, Materials.Milk, new ItemStack(Items.field_151117_aB, 1, 0));

        GT_OreDictUnificator.set(OrePrefixes.bottle, Materials.Empty, new ItemStack(Items.field_151069_bo, 1, 0));
        GT_OreDictUnificator.set(OrePrefixes.bottle, Materials.Water, new ItemStack(Items.field_151068_bn, 1, 0));

        GT_OreDictUnificator.set(OrePrefixes.plateAlloy, Materials.Iridium, GT_ModHandler.getIC2Item(ItemName.crafting, CraftingItemType.iridium, 1));
        GT_OreDictUnificator.set(OrePrefixes.plateAlloy, Materials.Advanced, GT_ModHandler.getIC2Item(ItemName.crafting, CraftingItemType.alloy, 1));
        GT_OreDictUnificator.set(OrePrefixes.plateAlloy, Materials.Carbon, GT_ModHandler.getIC2Item(ItemName.crafting, CraftingItemType.carbon_plate, 1));

        GT_OreDictUnificator.set(OrePrefixes.ore, Materials.Coal, new ItemStack(Blocks.field_150365_q, 1));
        GT_OreDictUnificator.set(OrePrefixes.ore, Materials.Iron, new ItemStack(Blocks.field_150366_p, 1));
        GT_OreDictUnificator.set(OrePrefixes.ore, Materials.Lapis, new ItemStack(Blocks.field_150369_x, 1));
        GT_OreDictUnificator.set(OrePrefixes.ore, Materials.Redstone, new ItemStack(Blocks.field_150450_ax, 1));
        GT_OreDictUnificator.set(OrePrefixes.ore, Materials.Redstone, new ItemStack(Blocks.field_150439_ay, 1));
        GT_OreDictUnificator.set(OrePrefixes.ore, Materials.Gold, new ItemStack(Blocks.field_150352_o, 1));
        GT_OreDictUnificator.set(OrePrefixes.ore, Materials.Diamond, new ItemStack(Blocks.field_150482_ag, 1));
        GT_OreDictUnificator.set(OrePrefixes.ore, Materials.Emerald, new ItemStack(Blocks.field_150412_bA, 1));
        GT_OreDictUnificator.set(OrePrefixes.ore, Materials.NetherQuartz, new ItemStack(Blocks.field_150449_bY, 1));
        GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Copper, GT_ModHandler.getIC2Item(ItemName.ingot, IngotResourceType.copper, 1));
        GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Tin, GT_ModHandler.getIC2Item(ItemName.ingot, IngotResourceType.tin, 1));
        GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Lead, GT_ModHandler.getIC2Item(ItemName.ingot, IngotResourceType.lead, 1));
        GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Bronze, GT_ModHandler.getIC2Item(ItemName.ingot, IngotResourceType.bronze, 1));
        GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Silver, GT_ModHandler.getIC2Item(ItemName.ingot, IngotResourceType.silver, 1));
        GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Lapis, new ItemStack(Items.field_151100_aR, 1, 4));
        GT_OreDictUnificator.set(OrePrefixes.gem, Materials.EnderEye, new ItemStack(Items.field_151061_bv, 1));
        GT_OreDictUnificator.set(OrePrefixes.gem, Materials.EnderPearl, new ItemStack(Items.field_151079_bi, 1));
        GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Diamond, new ItemStack(Items.field_151045_i, 1));
        GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Emerald, new ItemStack(Items.field_151166_bC, 1));
        GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Coal, new ItemStack(Items.field_151044_h, 1, 0));
        GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Charcoal, new ItemStack(Items.field_151044_h, 1, 1));
        GT_OreDictUnificator.set(OrePrefixes.gem, Materials.NetherQuartz, new ItemStack(Items.field_151128_bU, 1));
        GT_OreDictUnificator.set(OrePrefixes.gem, Materials.NetherStar, new ItemStack(Items.field_151156_bN, 1));
        GT_OreDictUnificator.set(OrePrefixes.nugget, Materials.Gold, new ItemStack(Items.field_151074_bl, 1));
        GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Gold, new ItemStack(Items.field_151043_k, 1));
        GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Iron, new ItemStack(Items.field_151042_j, 1));
        GT_OreDictUnificator.set(OrePrefixes.plate, Materials.Paper, new ItemStack(Items.field_151121_aF, 1));
        GT_OreDictUnificator.set(OrePrefixes.dust, Materials.Sugar, new ItemStack(Items.field_151102_aT, 1));
        GT_OreDictUnificator.set(OrePrefixes.dust, Materials.Bone, ItemList.Dye_Bonemeal.get(1));
        GT_OreDictUnificator.set(OrePrefixes.stick, Materials.Wood, new ItemStack(Items.field_151055_y, 1));
        GT_OreDictUnificator.set(OrePrefixes.dust, Materials.Redstone, new ItemStack(Items.field_151137_ax, 1));
        GT_OreDictUnificator.set(OrePrefixes.dust, Materials.Gunpowder, new ItemStack(Items.field_151016_H, 1));
        GT_OreDictUnificator.set(OrePrefixes.dust, Materials.Glowstone, new ItemStack(Items.field_151114_aO, 1));
        GT_OreDictUnificator.set(OrePrefixes.dust, Materials.Blaze, new ItemStack(Items.field_151065_br, 1));
        GT_OreDictUnificator.set(OrePrefixes.stick, Materials.Blaze, new ItemStack(Items.field_151072_bj, 1));
        GT_OreDictUnificator.set(OrePrefixes.block, Materials.Iron, new ItemStack(Blocks.field_150339_S, 1, 0));
        GT_OreDictUnificator.set(OrePrefixes.block, Materials.Gold, new ItemStack(Blocks.field_150340_R, 1, 0));
        GT_OreDictUnificator.set(OrePrefixes.block, Materials.Diamond, new ItemStack(Blocks.field_150484_ah, 1, 0));
        GT_OreDictUnificator.set(OrePrefixes.block, Materials.Emerald, new ItemStack(Blocks.field_150475_bE, 1, 0));
        GT_OreDictUnificator.set(OrePrefixes.block, Materials.Lapis, new ItemStack(Blocks.field_150368_y, 1, 0));
        GT_OreDictUnificator.set(OrePrefixes.block, Materials.Coal, new ItemStack(Blocks.field_150402_ci, 1, 0));
        GT_OreDictUnificator.set(OrePrefixes.block, Materials.Redstone, new ItemStack(Blocks.field_150451_bX, 1, 0));
        if (Blocks.field_150477_bB != null) {
            GT_OreDictUnificator.registerOre(OreDictNames.enderChest, new ItemStack(Blocks.field_150477_bB, 1));
        }
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.GraniteBlack, new ItemStack(Blocks.field_150348_b, 1, 1));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.GraniteBlack, new ItemStack(Blocks.field_150348_b, 1, 2));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Andesite, new ItemStack(Blocks.field_150348_b, 1, 5));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Andesite, new ItemStack(Blocks.field_150348_b, 1, 6));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Diorite, new ItemStack(Blocks.field_150348_b, 1, 3));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Diorite, new ItemStack(Blocks.field_150348_b, 1, 4));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingAnvil, new ItemStack(Blocks.field_150467_bQ, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingAnvil, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.anvil", 1, 0));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingIndustrialDiamond, ItemList.IC2_Industrial_Diamond.get(1));
        GT_OreDictUnificator.registerOre(OrePrefixes.dust, Materials.Wood, GT_ModHandler.getModItem("ThermalExpansion", "sawdust", 1));
        GT_OreDictUnificator.registerOre(OrePrefixes.glass, Materials.Reinforced, GT_ModHandler.getIC2Item(BlockName.glass, BlockTexGlass.GlassType.reinforced, 1));
        GT_OreDictUnificator.registerOre(OrePrefixes.glass, Materials.Reinforced, GT_ModHandler.getModItem("ThermalExpansion", "glassHardened", 1));

        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.cube", 1, 6));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.cube", 1, 7));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.brick.abyssal", 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, GT_ModHandler.getModItem("Railcraft", "tile.railcraft.brick.quarried", 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Obsidian, new ItemStack(Blocks.field_150343_Z, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.stoneMossy, new ItemStack(Blocks.field_150341_Y, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.stoneCobble, new ItemStack(Blocks.field_150341_Y, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.stoneCobble, new ItemStack(Blocks.field_150347_e, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.stoneSmooth, new ItemStack(Blocks.field_150348_b, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.stoneBricks, new ItemStack(Blocks.field_150417_aV, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.stoneMossy, new ItemStack(Blocks.field_150417_aV, 1, 1));
        GT_OreDictUnificator.registerOre(OrePrefixes.stoneCracked, new ItemStack(Blocks.field_150417_aV, 1, 2));
        GT_OreDictUnificator.registerOre(OrePrefixes.stoneChiseled, new ItemStack(Blocks.field_150417_aV, 1, 3));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Sand, new ItemStack(Blocks.field_150322_A, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Netherrack, new ItemStack(Blocks.field_150424_aL, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.NetherBrick, new ItemStack(Blocks.field_150385_bj, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Endstone, new ItemStack(Blocks.field_150377_bs, 1, 32767));

        GT_OreDictUnificator.registerOre("paperResearchFragment", GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 1, 9));
        GT_OreDictUnificator.registerOre("itemCertusQuartz", GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 1));
        GT_OreDictUnificator.registerOre("itemCertusQuartz", GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 10));
        GT_OreDictUnificator.registerOre("itemNetherQuartz", GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 11));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingQuartz, GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingQuartz, GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 10));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingQuartz, GT_ModHandler.getModItem("appliedenergistics2", "item.ItemMultiMaterial", 1, 11));
        GT_OreDictUnificator.registerOre("cropLemon", ItemList.FR_Lemon.get(1));
        GT_OreDictUnificator.registerOre("cropCoffee", ItemList.IC2_CoffeeBeans.get(1));
        GT_OreDictUnificator.registerOre("cropPotato", ItemList.Food_Raw_Potato.get(1));
        GT_OreDictUnificator.registerOre("calclavia:BATTERY", GT_ModHandler.getIC2Item(ItemName.re_battery, 1));
        GT_OreDictUnificator.registerOre("calclavia:BATTERY", GT_ModHandler.getIC2Item(ItemName.re_battery, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Basic, GT_ModHandler.getIC2Item(ItemName.re_battery, 1));
        GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Basic, GT_ModHandler.getIC2Item(ItemName.re_battery, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Advanced, GT_ModHandler.getIC2Item(ItemName.advanced_re_battery, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Elite, GT_ModHandler.getIC2Item(ItemName.energy_crystal, 1, 32767));
        GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Master, GT_ModHandler.getIC2Item(ItemName.lapotron_crystal, 1, 32767));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingWireCopper, ItemCable.getCable(CableType.copper, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingWireGold, ItemCable.getCable(CableType.gold, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingWireIron, ItemCable.getCable(CableType.iron, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingWireTin, ItemCable.getCable(CableType.tin, 1));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingRedstoneTorch, new ItemStack(Blocks.field_150429_aA, 1, 32767));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingRedstoneTorch, new ItemStack(Blocks.field_150437_az, 1, 32767));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingWorkBench, new ItemStack(Blocks.field_150462_ai, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingWorkBench, new ItemStack(GregTech_API.sBlockMachines, 1, 16));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingPiston, new ItemStack(Blocks.field_150331_J, 1, 32767));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingPiston, new ItemStack(Blocks.field_150320_F, 1, 32767));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingSafe, new ItemStack(GregTech_API.sBlockMachines, 1, 45));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingSafe, GT_ModHandler.getIC2TEItem(TeBlock.personal_chest, 1));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingChest, new ItemStack(Blocks.field_150486_ae, 1, 32767));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingChest, new ItemStack(Blocks.field_150447_bR, 1, 32767));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingFurnace, new ItemStack(Blocks.field_150460_al, 1, 32767));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingFurnace, new ItemStack(Blocks.field_150470_am, 1, 32767));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingPump, GT_ModHandler.getIC2TEItem(TeBlock.pump, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingElectromagnet, GT_ModHandler.getIC2TEItem(TeBlock.magnetizer, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingTeleporter, GT_ModHandler.getIC2TEItem(TeBlock.teleporter, 1));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingMacerator, GT_ModHandler.getIC2TEItem(TeBlock.macerator, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingMacerator, new ItemStack(GregTech_API.sBlockMachines, 1, 50));


        GT_OreDictUnificator.registerOre(OreDictNames.craftingExtractor, GT_ModHandler.getIC2TEItem(TeBlock.extractor, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingExtractor, new ItemStack(GregTech_API.sBlockMachines, 1, 51));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingCompressor, GT_ModHandler.getIC2TEItem(TeBlock.compressor, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingCompressor, new ItemStack(GregTech_API.sBlockMachines, 1, 52));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingRecycler, GT_ModHandler.getIC2TEItem(TeBlock.recycler, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingRecycler, new ItemStack(GregTech_API.sBlockMachines, 1, 53));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingIronFurnace, GT_ModHandler.getIC2TEItem(TeBlock.iron_furnace, 1));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingCentrifuge, new ItemStack(GregTech_API.sBlockMachines, 1, 62));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingInductionFurnace, GT_ModHandler.getIC2TEItem(TeBlock.induction_furnace, 1));


        GT_OreDictUnificator.registerOre(OreDictNames.craftingElectricFurnace, GT_ModHandler.getIC2TEItem(TeBlock.electric_furnace, 1));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingElectricFurnace, new ItemStack(GregTech_API.sBlockMachines, 1, 54));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingGenerator, GT_ModHandler.getIC2TEItem(TeBlock.generator, 1));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingGeothermalGenerator, GT_ModHandler.getIC2TEItem(TeBlock.geo_generator, 1));

        GT_OreDictUnificator.registerOre(OreDictNames.craftingFeather, new ItemStack(Items.field_151008_G, 1, 32767));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingFeather, GT_ModHandler.getModItem("TwilightForest", "item.tfFeather", 1, 32767));

        GT_OreDictUnificator.registerOre("itemWheat", new ItemStack(Items.field_151015_O, 1, 32767));
        GT_OreDictUnificator.registerOre("paperEmpty", new ItemStack(Items.field_151121_aF, 1, 32767));
        GT_OreDictUnificator.registerOre("paperMap", new ItemStack(Items.field_151148_bJ, 1, 32767));
        GT_OreDictUnificator.registerOre("paperMap", new ItemStack(Items.field_151098_aY, 1, 32767));
        GT_OreDictUnificator.registerOre("bookEmpty", new ItemStack(Items.field_151122_aG, 1, 32767));
        GT_OreDictUnificator.registerOre("bookWritable", new ItemStack(Items.field_151099_bA, 1, 32767));
        GT_OreDictUnificator.registerOre("bookWritten", new ItemStack(Items.field_151164_bB, 1, 32767));
        GT_OreDictUnificator.registerOre("bookEnchanted", new ItemStack(Items.field_151134_bR, 1, 32767));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingBook, new ItemStack(Items.field_151122_aG, 1, 32767));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingBook, new ItemStack(Items.field_151099_bA, 1, 32767));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingBook, new ItemStack(Items.field_151164_bB, 1, 32767));
        GT_OreDictUnificator.registerOre(OreDictNames.craftingBook, new ItemStack(Items.field_151134_bR, 1, 32767));

        GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Basic, GT_ModHandler.getIC2Item(ItemName.crafting, CraftingItemType.circuit, 1));
        GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Advanced, GT_ModHandler.getIC2Item(ItemName.crafting, CraftingItemType.advanced_circuit, 1));
    }
}
