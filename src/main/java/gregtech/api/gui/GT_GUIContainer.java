package gregtech.api.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * <p/>
 * Main GUI-Container-Class which basically contains the Code needed to prevent crashes from improperly Coded Items.
 */
public class GT_GUIContainer extends GuiContainer {

    public boolean mCrashed = false;

    public ResourceLocation mGUIbackground;

    public String mGUIbackgroundPath;

    public GT_GUIContainer(Container aContainer, String aGUIbackground) {
        super(aContainer);
        mGUIbackground = new ResourceLocation(mGUIbackgroundPath = aGUIbackground);
    }

    public int getLeft() {
        return field_147003_i;
    }

    public int getTop() {
        return field_147009_r;
    }

    @Override
    protected void func_146979_b(int par1, int par2) {
        //
    }

    @Override
    protected void func_146976_a(float par1, int par2, int par3) {
        field_146297_k.field_71446_o.func_110577_a(mGUIbackground);
    }

}
