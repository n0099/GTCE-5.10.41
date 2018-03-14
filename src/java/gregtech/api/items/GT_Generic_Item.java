package gregtech.api.items;

import gregtech.api.GregTech_API;
import gregtech.api.enums.SubTag;
import gregtech.api.interfaces.IProjectileItem;
import gregtech.api.util.GT_Config;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import net.minecraftforge.fml.common.registry.PersistentRegistryManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Method;
import java.util.List;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RES_PATH_ITEM;

/**
 * Extended by most Items, also used as a fallback Item, to prevent the accidental deletion when Errors occur.
 */
public class GT_Generic_Item extends Item implements IProjectileItem {

    private final String mName, mTooltip;

    @SideOnly(Side.CLIENT)
    protected TextureAtlasSprite mIcon;

    public GT_Generic_Item(String aUnlocalized, String aEnglish, String aEnglishTooltip) {
        this(aUnlocalized, aEnglish, aEnglishTooltip, true);
    }

    public GT_Generic_Item(String aUnlocalized, String aEnglish, String aEnglishTooltip, boolean aWriteToolTipIntoLangFile) {
        super();
        mName = "gt." + aUnlocalized;
        GT_LanguageManager.addStringLocalization(mName + ".name", aEnglish);
        if (GT_Utility.isStringValid(aEnglishTooltip))
            GT_LanguageManager.addStringLocalization(mTooltip = mName + ".tooltip_main", aEnglishTooltip, aWriteToolTipIntoLangFile);
        else mTooltip = null;
        func_77637_a(GregTech_API.TAB_GREGTECH);
        setRegistryName(MOD_ID, mName);
        GameRegistry.register(this);
        BlockDispenser.field_149943_a.func_82595_a(this, new GT_Item_Dispense());
    }

    @Override
    public final Item func_77655_b(String aName) {
        return this;
    }

    @Override
    public final String func_77658_a() {
        return mName;
    }

    @Override
    public String func_77667_c(ItemStack aStack) {
        return func_77614_k() ? mName + "." + getDamage(aStack) : mName;
    }

    @Override
    public String func_77657_g(ItemStack stack) {
        return func_77667_c(stack);
    }

    public int getTier(ItemStack aStack) {
        return 0;
    }

    @Override
    public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
        if (func_77612_l() > 0 && !func_77614_k())
            aList.add((aStack.func_77958_k() - getDamage(aStack)) + " / " + aStack.func_77958_k());
        if (mTooltip != null) aList.add(GT_LanguageManager.getTranslation(mTooltip));
        if (GT_ModHandler.isElectricItem(aStack)) aList.add("Tier: " + getTier(aStack));
        addAdditionalToolTips(aList, aStack, aPlayer);
    }

    protected void addAdditionalToolTips(List<String> aList, ItemStack aStack, EntityPlayer aPlayer) {
        //
    }

    @Override
    public void func_77622_d(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        isItemStackUsable(aStack);
    }

    public boolean isItemStackUsable(ItemStack aStack) {
        return true;
    }

    public ItemStack onDispense(IBlockSource aSource, ItemStack aStack) {
        IBlockState state = Blocks.field_150367_z.func_176203_a(aStack.func_77952_i());
        EnumFacing enumfacing = state.func_177229_b(BlockDispenser.field_176441_a);
        IPosition iposition = BlockDispenser.func_149939_a(aSource);
        ItemStack itemstack1 = aStack.func_77979_a(1);
        BehaviorDefaultDispenseItem.func_82486_a(aSource.func_82618_k(), itemstack1, 6, enumfacing, iposition);
        return aStack;
    }

    @Override
    public EntityArrow getProjectile(SubTag aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
        return null;
    }

    @Override
    public EntityArrow getProjectile(SubTag aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed) {
        return null;
    }

    @Override
    public boolean hasProjectile(SubTag aProjectileType, ItemStack aStack) {
        return false;
    }

    @Override
    public ItemStack getContainerItem(ItemStack aStack) {
        return null;
    }

    @Override
    public boolean hasContainerItem(ItemStack aStack) {
        return getContainerItem(aStack) != null;
    }


    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap aIconRegister) {
        mIcon = aIconRegister.func_174942_a(new ResourceLocation(RES_PATH_ITEM + (GT_Config.troll ? "troll" : mName)));
    }

    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getIcon(ItemStack stack, int pass) {
        return mIcon;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderPasses(ItemStack stack) {
        return 1;
    }

    public int getColorFromItemStack(ItemStack itemStack, int pass) {
        return 0xFFFFFFFF;
    }

    public boolean isHandheld(ItemStack itemStack) {
        return false;
    }

    public static class GT_Item_Dispense extends BehaviorProjectileDispense {
        @Override
        public ItemStack func_82487_b(IBlockSource aSource, ItemStack aStack) {
            return ((GT_Generic_Item) aStack.func_77973_b()).onDispense(aSource, aStack);
        }

        @Override
        protected IProjectile func_82499_a(World aWorld, IPosition aPosition, ItemStack stack) {
            return null;
        }

    }

    public static <T> int id(IForgeRegistryEntry<T> registryEntry) {
        try {
            Method findRegistry = PersistentRegistryManager.class.getDeclaredMethod("findRegistry", IForgeRegistryEntry.class);
            findRegistry.setAccessible(true);
            FMLControlledNamespacedRegistry registry = (FMLControlledNamespacedRegistry) findRegistry.invoke(null, registryEntry);
            return registry.getId(registryEntry);
        } catch (ReflectiveOperationException fail) {
            throw new RuntimeException(fail);
        }
    }

    public void invokeOnClient(Runnable runnable) {
        if(FMLCommonHandler.instance().getSide().isClient()) {
            runnable.run();
        }
    }

}
