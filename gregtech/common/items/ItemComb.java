package gregtech.common.items;

import com.google.common.collect.ImmutableMap;
import forestry.api.core.Tabs;
import forestry.api.recipes.RecipeManagers;
import gregtech.GT_Mod;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.items.GT_Generic_Item;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import gregtech.loaders.materialprocessing.ProcessingModSupport;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemComb extends GT_Generic_Item {

    @SideOnly(Side.CLIENT)
	private TextureAtlasSprite itemIcon, secondIcon;

	public ItemComb() {
		super("gt.comb", "Comb", "");
		this.func_77637_a(Tabs.tabApiculture);
		this.func_77627_a(true);
	}

	public ItemStack getStackForType(CombType type) {
		return new ItemStack(this, 1, type.ordinal());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
		for (CombType type : CombType.values()) {
			if (type.showInList) {
				list.add(this.getStackForType(type));
			}
		}
	}

	@Override
	public int getRenderPasses(ItemStack itemStack) {
		return 1;
	}

	@SideOnly(Side.CLIENT)
    @Override
	public void registerIcons(TextureMap par1IconRegister) {
		this.itemIcon = par1IconRegister.func_174942_a(new ResourceLocation("forestry:beeCombs.0"));
		this.secondIcon = par1IconRegister.func_174942_a(new ResourceLocation("forestry:beeCombs.1"));
	}

	@Override
    @SideOnly(Side.CLIENT)
	public TextureAtlasSprite getIcon(ItemStack stack, int pass) {
		return (pass == 0) ? itemIcon : secondIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass) {
		int meta = Math.max(0, Math.min(CombType.values().length - 1, stack.func_77952_i()));
		int colour = CombType.values()[meta].getColours()[0];

		if (pass >= 1) {
			colour = CombType.values()[meta].getColours()[1];
		}

		return colour;
	}

	@Override
	public String func_77653_i(ItemStack stack) {
		return CombType.values()[stack.func_77952_i()].getName();
	}

	public void initCombsRecipes() {
		ItemStack tComb;

	    //Organic
		tComb = getStackForType(CombType.LIGNIE);
		addSpecialCent(tComb,GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Lignite, 1), 90);
		addProcess(tComb, Materials.Lignite, 100);
		tComb = getStackForType(CombType.COAL);
		addSpecialCent(tComb,GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Coal, 1), 40);
		addProcess(tComb, Materials.Coal, 100);
		tComb = getStackForType(CombType.STICKY);
		addSpecialCent(tComb, ItemList.IC2_Resin.get(1), 70);
		tComb = getStackForType(CombType.OIL);
		addSpecialCent(tComb, ItemList.Crop_Drop_OilBerry.get(2), 70);
		addProcess(tComb, Materials.Oilsands, 100);
		
	    //Gem Line
		tComb = getStackForType(CombType.STONE);
		addSpecialCent(tComb,GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Stone, 1), 70,GT_OreDictUnificator.get(OrePrefixes.dust,Materials.Salt,1),20,GT_OreDictUnificator.get(OrePrefixes.dust,Materials.RockSalt,1),20);
		addProcess(tComb, Materials.Soapstone, 100);
		addProcess(tComb, Materials.Talc, 100);
		addProcess(tComb, Materials.Apatite, 100);
		addProcess(tComb, Materials.Phosphate, 100);
		addProcess(tComb, Materials.Phosphorus, 100);
		tComb = getStackForType(CombType.CERTUS);
		addProcess(tComb, Materials.CertusQuartz, 100);
		addProcess(tComb, Materials.Quartzite, 100);
		addProcess(tComb, Materials.Barite, 100);
		tComb = getStackForType(CombType.REDSTONE);
		addProcess(tComb, Materials.Redstone, 100);
		addProcess(tComb, Materials.Cinnabar, 100);
		tComb = getStackForType(CombType.LAPIS);
		addProcess(tComb, Materials.Lapis, 100);
		addProcess(tComb, Materials.Sodalite, 100);
		addProcess(tComb, Materials.Lazurite, 100);
		addProcess(tComb, Materials.Calcite, 100);
		tComb = getStackForType(CombType.RUBY);
		addProcess(tComb, Materials.Ruby, 100);
		addProcess(tComb, Materials.Redstone, 100);
		tComb = getStackForType(CombType.SAPPHIRE);
		addProcess(tComb, Materials.Sapphire, 100);
		addProcess(tComb, Materials.GreenSapphire, 100);
		addProcess(tComb, Materials.Almandine, 100);
		addProcess(tComb, Materials.Pyrope, 100);
		tComb = getStackForType(CombType.DIAMOND);
		addProcess(tComb, Materials.Diamond, 100);
		addProcess(tComb, Materials.Graphite, 100);
		tComb = getStackForType(CombType.OLIVINE);
		addProcess(tComb, Materials.Olivine, 100);
		addProcess(tComb, Materials.Bentonite, 100);
		addProcess(tComb, Materials.Magnesite, 100);
		addProcess(tComb, Materials.Glauconite, 100);
		tComb = getStackForType(CombType.EMERALD);
		addProcess(tComb, Materials.Emerald, 100);
		addProcess(tComb, Materials.Beryllium, 100);
		addProcess(tComb, Materials.Thorium, 100);

//	    // Metals Line
		tComb = getStackForType(CombType.SLAG);
		addSpecialCent(tComb, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Stone, 1), 50,GT_OreDictUnificator.get(OrePrefixes.dust, Materials.GraniteBlack, 1), 20,GT_OreDictUnificator.get(OrePrefixes.dust, Materials.GraniteRed, 1), 20);
		addProcess(tComb, Materials.Salt, 100);
		addProcess(tComb, Materials.RockSalt, 100);
		addProcess(tComb, Materials.Lepidolite, 100);
		addProcess(tComb, Materials.Spodumene, 100);
		addProcess(tComb, Materials.Monazite, 100);
		tComb = getStackForType(CombType.COPPER);
		addSpecialCent(tComb, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Copper, 1), 70);
		addProcess(tComb, Materials.Copper, 100);
		addProcess(tComb, Materials.Tetrahedrite, 100);
		addProcess(tComb, Materials.Chalcopyrite, 100);
		addProcess(tComb, Materials.Malachite, 100);
		addProcess(tComb, Materials.Pyrite, 100);
		addProcess(tComb, Materials.Stibnite, 100);
		tComb = getStackForType(CombType.TIN);
		addSpecialCent(tComb, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tin, 1), 60);
		addProcess(tComb, Materials.Tin, 100);
		addProcess(tComb, Materials.Cassiterite, 100);
		tComb = getStackForType(CombType.LEAD);
		addSpecialCent(tComb, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Lead, 1), 45);
		addProcess(tComb, Materials.Lead, 100);
		addProcess(tComb, Materials.Galena, 100);
		tComb = getStackForType(CombType.IRON);
		addProcess(tComb, Materials.Iron, 100);
		addProcess(tComb, Materials.Magnetite, 100);
		addProcess(tComb, Materials.BrownLimonite, 100);
		addProcess(tComb, Materials.YellowLimonite, 100);
		addProcess(tComb, Materials.VanadiumMagnetite, 100);
		addProcess(tComb, Materials.BandedIron, 100);
		addProcess(tComb, Materials.Pyrite, 100);
		if (ProcessingModSupport.aEnableGCMarsMats)
			addProcess(tComb, Materials.MeteoricIron, 100);
		tComb = getStackForType(CombType.STEEL);
		addProcess(tComb, Materials.Iron, Materials.Steel, 100);
		addProcess(tComb, Materials.Magnetite, Materials.Steel, 100);
		addProcess(tComb, Materials.BrownLimonite, Materials.Steel, 100);
		addProcess(tComb, Materials.YellowLimonite, Materials.Steel, 100);
		addProcess(tComb, Materials.VanadiumMagnetite, Materials.VanadiumSteel, 100);
		addProcess(tComb, Materials.BandedIron, Materials.Steel, 100);
		addProcess(tComb, Materials.Pyrite, Materials.Steel, 100);
		if (ProcessingModSupport.aEnableGCMarsMats)
			addProcess(tComb, Materials.MeteoricIron, Materials.MeteoricSteel, 100);
		addProcess(tComb, Materials.Molybdenite, 100);
		addProcess(tComb, Materials.Molybdenum, 100);
		tComb = getStackForType(CombType.NICKEL);
		addProcess(tComb, Materials.Nickel, 100);
		addProcess(tComb, Materials.Garnierite, 100);
		addProcess(tComb, Materials.Pentlandite, 100);
		addProcess(tComb, Materials.Cobaltite, 100);
		addProcess(tComb, Materials.Wulfenite, 100);
		addProcess(tComb, Materials.Powellite, 100);
		tComb = getStackForType(CombType.ZINC);
		addProcess(tComb, Materials.Zinc, 100);
		addProcess(tComb, Materials.Sphalerite, 100);
		addProcess(tComb, Materials.Sulfur, 100);
		tComb = getStackForType(CombType.SILVER);
		addSpecialCent(tComb, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Silver, 1), 30);
		addProcess(tComb, Materials.Silver, 100);
		addProcess(tComb, Materials.Galena, 100);
		tComb = getStackForType(CombType.GOLD);
		addProcess(tComb, Materials.Gold, 100);
		addProcess(tComb, Materials.Magnetite, Materials.Gold, 100);

	    // Rare Metals Line    
		tComb = getStackForType(CombType.ALUMINIUM);
		addProcess(tComb,Materials.Aluminium,60);
		addProcess(tComb,Materials.Bauxite,100);
		tComb = getStackForType(CombType.MANGANESE);
		addProcess(tComb,Materials.Manganese,30);
		addProcess(tComb,Materials.Grossular,100);
		addProcess(tComb,Materials.Spessartine,100);
		addProcess(tComb,Materials.Pyrolusite,100);
		addProcess(tComb,Materials.Tantalite,100);
		tComb = getStackForType(CombType.TITANIUM);
		addProcess(tComb,Materials.Titanium,100);
		addProcess(tComb,Materials.Ilmenite,100);
		addProcess(tComb,Materials.Bauxite,100);
		tComb = getStackForType(CombType.CHROME);
		addProcess(tComb,Materials.Chrome,50);
		addProcess(tComb,Materials.Ruby,100);
		addProcess(tComb,Materials.Chromite,50);
		addProcess(tComb,Materials.Redstone,100);
		addProcess(tComb, Materials.Neodymium, 100);
		addProcess(tComb, Materials.Bastnasite, 100);
		tComb = getStackForType(CombType.TUNGSTEN);
		addProcess(tComb,Materials.Tungstate,100);
		addProcess(tComb,Materials.Scheelite,100);
		addProcess(tComb,Materials.Lithium,100);
		tComb = getStackForType(CombType.PLATINUM);
		addProcess(tComb,Materials.Platinum,40);
		addProcess(tComb,Materials.Cooperite,40);
		addProcess(tComb,Materials.Palladium,40);
		tComb = getStackForType(CombType.IRIDIUM);
		addProcess(tComb,Materials.Iridium,20);
		addProcess(tComb,Materials.Osmium,20);

	    // Radioactive Line
		tComb = getStackForType(CombType.URANIUM);
		addProcess(tComb,Materials.Uranium,50);
		addProcess(tComb,Materials.Pitchblende,50);
		addProcess(tComb,Materials.Uraninite,50);
		addProcess(tComb,Materials.Uranium235,50);
		tComb = getStackForType(CombType.PLUTONIUM);
		addProcess(tComb,Materials.Plutonium,10);
		addProcess(tComb,Materials.Uranium235,Materials.Plutonium,5);
		tComb = getStackForType(CombType.NAQUADAH);
		addProcess(tComb,Materials.Naquadah,10);
		addProcess(tComb,Materials.NaquadahEnriched,10);
		addProcess(tComb,Materials.Naquadria,10);
	}
	public void addSpecialCent(ItemStack tComb, ItemStack aOutput, int chance){
		GT_Values.RA.addCentrifugeRecipe(tComb, GT_Values.NI, GT_Values.NF, GT_Values.NF, aOutput,	ItemList.FR_Wax.get(1, new Object[0]), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[] { chance * 100, 3000 }, 128, 5);
		RecipeManagers.centrifugeManager.addRecipe(40, tComb, ImmutableMap.of(aOutput, chance * 0.01f, ItemList.FR_Wax.get(1, new Object[0]), 0.3f));
	}
	
	public void addSpecialCent(ItemStack tComb, ItemStack aOutput, int chance, ItemStack aOutput2, int chance2){
		GT_Values.RA.addCentrifugeRecipe(tComb, GT_Values.NI, GT_Values.NF, GT_Values.NF, aOutput,	ItemList.FR_Wax.get(1, new Object[0]), aOutput2, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[] { chance * 100, 3000, chance2 * 100 }, 128, 5);
		RecipeManagers.centrifugeManager.addRecipe(40, tComb, ImmutableMap.of(aOutput, chance * 0.01f, ItemList.FR_Wax.get(1, new Object[0]), 0.3f,aOutput2,chance2 * 0.01f));
	}
	
	public void addSpecialCent(ItemStack tComb, ItemStack aOutput, int chance, ItemStack aOutput2, int chance2, ItemStack aOutput3, int chance3){
		GT_Values.RA.addCentrifugeRecipe(tComb, GT_Values.NI, GT_Values.NF, GT_Values.NF, aOutput,	ItemList.FR_Wax.get(1, new Object[0]), aOutput2, aOutput3, GT_Values.NI, GT_Values.NI, new int[] { chance * 100, 3000, chance2 * 100, chance3*100 }, 128, 5);
		RecipeManagers.centrifugeManager.addRecipe(40, tComb, ImmutableMap.of(aOutput, chance * 0.01f, ItemList.FR_Wax.get(1, new Object[0]), 0.3f,aOutput2,chance2 * 0.01f,aOutput3,chance3*0.01f));
	}
	
	public void addProcess(ItemStack tComb, Materials aMaterial, int chance){
		if(GT_Mod.gregtechproxy.mNerfedCombs){
			GT_Values.RA.addChemicalRecipe(GT_Utility.copyAmount(9, tComb), GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial, 1), Materials.Water.getFluid(1000), aMaterial.mOreByProducts.isEmpty() ? null : aMaterial.mOreByProducts.get(0).getMolten(144), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterial, 4), 96, 24);
			GT_Values.RA.addAutoclaveRecipe(GT_Utility.copyAmount(16, tComb), Materials.UUMatter.getFluid(Math.max(1, ((aMaterial.getMass()+9)/10))), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aMaterial, 1), 10000, (int) (aMaterial.getMass() * 128), 384);
		}else{
			GT_Values.RA.addCentrifugeRecipe(tComb, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dustTiny, aMaterial, 1),	ItemList.FR_Wax.get(1, new Object[0]), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[] { chance * 100, 3000 }, 128, 5);
			RecipeManagers.centrifugeManager.addRecipe(40, tComb, ImmutableMap.of(GT_OreDictUnificator.get(OrePrefixes.dustTiny, aMaterial, 1), chance * 0.01f, ItemList.FR_Wax.get(1, new Object[0]), 0.3f));
		}
	}
	
	public void addProcess(ItemStack tComb, Materials aInMaterial, Materials aOutMaterial, int chance){
		if(GT_Mod.gregtechproxy.mNerfedCombs){
			GT_Values.RA.addChemicalRecipe(GT_Utility.copyAmount(9, tComb), GT_OreDictUnificator.get(OrePrefixes.crushed, aInMaterial, 1), Materials.Water.getFluid(1000), aInMaterial.mOreByProducts.isEmpty() ? null : aInMaterial.mOreByProducts.get(0).getMolten(144), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aOutMaterial, 4), 96, 24);
			GT_Values.RA.addAutoclaveRecipe(GT_Utility.copyAmount(16, tComb), Materials.UUMatter.getFluid(Math.max(1, ((aOutMaterial.getMass()+9)/10))), GT_OreDictUnificator.get(OrePrefixes.crushedPurified, aOutMaterial, 1), 10000, (int) (aOutMaterial.getMass() * 128), 384);
		}else{
			GT_Values.RA.addCentrifugeRecipe(tComb, GT_Values.NI, GT_Values.NF, GT_Values.NF, GT_OreDictUnificator.get(OrePrefixes.dustTiny, aOutMaterial, 1),	ItemList.FR_Wax.get(1, new Object[0]), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[] { chance * 100, 3000 }, 128, 5);
			RecipeManagers.centrifugeManager.addRecipe(40, tComb, ImmutableMap.of(GT_OreDictUnificator.get(OrePrefixes.dustTiny, aOutMaterial, 1), chance * 0.01f, ItemList.FR_Wax.get(1, new Object[0]), 0.3f));
		}
	}
	
}