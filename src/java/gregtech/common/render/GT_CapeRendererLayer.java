package gregtech.common.render;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;

@SideOnly(Side.CLIENT)
public class GT_CapeRendererLayer implements LayerRenderer<AbstractClientPlayer> {
    private static final ResourceLocation[] mCapes = {new ResourceLocation("gregtech:textures/BrainTechCape.png"), new ResourceLocation("gregtech:textures/GregTechCape.png"), new ResourceLocation("gregtech:textures/MrBrainCape.png"), new ResourceLocation("gregtech:textures/GregoriusCape.png")};

    private final Collection<String> mCapeList;
    private final RenderPlayer playerRenderer;

    public GT_CapeRendererLayer(Collection<String> mCapeList, RenderPlayer playerRendererIn) {
        this.playerRenderer = playerRendererIn;
        this.mCapeList = mCapeList;
    }

    public void func_177141_a(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ResourceLocation aCapeLocation = getLocationCape(entitylivingbaseIn);
        if (entitylivingbaseIn.func_152122_n() && !entitylivingbaseIn.func_82150_aj() && entitylivingbaseIn.func_175148_a(EnumPlayerModelParts.CAPE) && aCapeLocation != null) {
            ItemStack itemstack = entitylivingbaseIn.func_184582_a(EntityEquipmentSlot.CHEST);

            if (itemstack == null || itemstack.func_77973_b() != Items.field_185160_cR) {
                GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
                this.playerRenderer.func_110776_a(aCapeLocation);
                GlStateManager.func_179094_E();
                GlStateManager.func_179109_b(0.0F, 0.0F, 0.125F);
                double d0 = entitylivingbaseIn.field_71091_bM + (entitylivingbaseIn.field_71094_bP - entitylivingbaseIn.field_71091_bM) * (double) partialTicks - (entitylivingbaseIn.field_70169_q + (entitylivingbaseIn.field_70165_t - entitylivingbaseIn.field_70169_q) * (double) partialTicks);
                double d1 = entitylivingbaseIn.field_71096_bN + (entitylivingbaseIn.field_71095_bQ - entitylivingbaseIn.field_71096_bN) * (double) partialTicks - (entitylivingbaseIn.field_70167_r + (entitylivingbaseIn.field_70163_u - entitylivingbaseIn.field_70167_r) * (double) partialTicks);
                double d2 = entitylivingbaseIn.field_71097_bO + (entitylivingbaseIn.field_71085_bR - entitylivingbaseIn.field_71097_bO) * (double) partialTicks - (entitylivingbaseIn.field_70166_s + (entitylivingbaseIn.field_70161_v - entitylivingbaseIn.field_70166_s) * (double) partialTicks);
                float f = entitylivingbaseIn.field_70760_ar + (entitylivingbaseIn.field_70761_aq - entitylivingbaseIn.field_70760_ar) * partialTicks;
                double d3 = (double) MathHelper.func_76126_a(f * 0.017453292F);
                double d4 = (double) (-MathHelper.func_76134_b(f * 0.017453292F));
                float f1 = (float) d1 * 10.0F;
                f1 = MathHelper.func_76131_a(f1, -6.0F, 32.0F);
                float f2 = (float) (d0 * d3 + d2 * d4) * 100.0F;
                float f3 = (float) (d0 * d4 - d2 * d3) * 100.0F;

                if (f2 < 0.0F) {
                    f2 = 0.0F;
                }

                float f4 = entitylivingbaseIn.field_71107_bF + (entitylivingbaseIn.field_71109_bG - entitylivingbaseIn.field_71107_bF) * partialTicks;
                f1 = f1 + MathHelper.func_76126_a((entitylivingbaseIn.field_70141_P + (entitylivingbaseIn.field_70140_Q - entitylivingbaseIn.field_70141_P) * partialTicks) * 6.0F) * 32.0F * f4;

                if (entitylivingbaseIn.func_70093_af()) {
                    f1 += 25.0F;
                }

                GlStateManager.func_179114_b(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
                GlStateManager.func_179114_b(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.func_179114_b(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.func_179114_b(180.0F, 0.0F, 1.0F, 0.0F);
                this.playerRenderer.func_177087_b().func_178728_c(0.0625F);
                GlStateManager.func_179121_F();
            }
        }
    }

    public boolean func_177142_b() {
        return false;
    }

    public ResourceLocation getLocationCape(AbstractClientPlayer aPlayer) {
        String aDisplayName = aPlayer.func_145748_c_().func_150260_c();
        ResourceLocation tResource = null;
        if (aDisplayName.equals("Friedi4321")) {
            tResource = mCapes[0];
        }
        if (this.mCapeList.contains(aDisplayName)) {
            tResource = mCapes[1];
        }
        if (aDisplayName.equals("Mr_Brain")) {
            tResource = mCapes[2];
        }
        if (aDisplayName.equals("GregoriusT")) {
            tResource = mCapes[3];
        }
        return tResource;
    }

}
