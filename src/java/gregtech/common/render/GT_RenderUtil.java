package gregtech.common.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class GT_RenderUtil {

    public static void renderItemIcon(TextureAtlasSprite icon, double size, double z, float nx, float ny, float nz) {
        renderItemIcon(icon, 0.0D, 0.0D, size, size, z, nx, ny, nz);
    }

    public static void renderItemIcon(TextureAtlasSprite icon, double xStart, double yStart, double xEnd, double yEnd, double z, float nx, float ny, float nz) {
        if (icon == null) {
            return;
        }
        Tessellator tessellator = Tessellator.func_178181_a();
        VertexBuffer buffer = tessellator.func_178180_c();
        buffer.func_181668_a(GL11.GL_QUADS, DefaultVertexFormats.field_176599_b);
        if (nz > 0.0F) {
            addVertexWithUV(buffer, nx, ny, nz, xStart, yStart, z, icon.func_94209_e(), icon.func_94206_g());
            addVertexWithUV(buffer, nx, ny, nz, xEnd, yStart, z, icon.func_94212_f(), icon.func_94206_g());
            addVertexWithUV(buffer, nx, ny, nz, xEnd, yEnd, z, icon.func_94212_f(), icon.func_94210_h());
            addVertexWithUV(buffer, nx, ny, nz, xStart, yEnd, z, icon.func_94209_e(), icon.func_94210_h());
        } else {
            addVertexWithUV(buffer, nx, ny, nz, xStart, yEnd, z, icon.func_94209_e(), icon.func_94210_h());
            addVertexWithUV(buffer, nx, ny, nz, xEnd, yEnd, z, icon.func_94212_f(), icon.func_94210_h());
            addVertexWithUV(buffer, nx, ny, nz, xEnd, yStart, z, icon.func_94212_f(), icon.func_94206_g());
            addVertexWithUV(buffer, nx, ny, nz, xStart, yStart, z, icon.func_94209_e(), icon.func_94206_g());
        }
        tessellator.func_78381_a();
    }
    
    private static void addVertexWithUV(VertexBuffer buffer, double nX, double nY, double nZ, double x, double y, double z, double u, double v) {
        buffer.func_181662_b(x, y, z).func_181666_a(1.0f, 1.0f, 1.0f, 1.0f).func_187315_a(u, v).func_181663_c((float)nX, (float)nY, (float)nZ).func_181675_d();
    }
    
}
