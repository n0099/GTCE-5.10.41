package gregtech.common.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Vector3f;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Simple util for gathering transforms from external model jsons
 */
@SideOnly(Side.CLIENT)
public class ModelUtil {

    public static ItemCameraTransforms DEFAULT_TRANSFORMS = getTransformsFromModel("models/item/generated.json");
    public static ItemCameraTransforms HANDHELD_TRANSFORMS = getTransformsFromModel("models/item/handheld.json");
    public static ItemCameraTransforms BLOCK_TRANSFORMS = getTransformsFromModel("models/block/block.json");

    static {
        ItemTransformVec3f vec = HANDHELD_TRANSFORMS.field_181699_o;
        ItemTransformVec3f newVec = new ItemTransformVec3f(vec.field_178364_b, vec.field_178365_c, new Vector3f(0.5f, 0.5f, 0.5f));
        HANDHELD_TRANSFORMS = new ItemCameraTransforms(
                HANDHELD_TRANSFORMS.field_188036_k,
                HANDHELD_TRANSFORMS.field_188037_l,
                HANDHELD_TRANSFORMS.field_188038_m,
                HANDHELD_TRANSFORMS.field_188039_n,
                HANDHELD_TRANSFORMS.field_178353_d,
                HANDHELD_TRANSFORMS.field_178354_e,
                newVec,
                HANDHELD_TRANSFORMS.field_181700_p
        );
    }

    public static ItemCameraTransforms getTransformsFromModel(String path) {
        try {
            ResourceLocation location = new ResourceLocation(path);
            IResource resource = Minecraft.func_71410_x().func_110442_L().func_110536_a(location);
            try (InputStreamReader reader = new InputStreamReader(resource.func_110527_b())) {
                ModelBlock modelBlock = ModelBlock.func_178307_a(reader);
                return modelBlock.func_181682_g();
            }
        } catch (IOException notFound) {}
        return ItemCameraTransforms.field_178357_a;
    }

}
