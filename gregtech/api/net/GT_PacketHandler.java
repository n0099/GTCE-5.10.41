package gregtech.api.net;

import gregtech.GT_Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class GT_PacketHandler extends SimpleNetworkWrapper {

    public GT_PacketHandler() {
        super("gregtech_network");
        registerMessage(0, GT_Packet_TileEntity.class, Side.CLIENT);
        registerMessage(2, GT_Packet_Sound.class, Side.CLIENT);
        registerMessage(3, GT_Packet_Block_Event.class, Side.CLIENT);
    }

    public void registerMessage(int discriminator, Class packet, Side handleSide) {
        if(GT_Packet.class.isAssignableFrom(packet)) {
            registerMessage(GT_MessageHandler.class, packet, discriminator, handleSide);
        } else {
            throw new IllegalArgumentException("Tried to register non-packet class " + packet.getName());
        }
    }

    public void sendToAllAround(World world, GT_Packet packet, int x, int y, int z) {
        sendToAllAround(packet, new NetworkRegistry.TargetPoint(world.field_73011_w.getDimension(), x, y, z, 64));
    }

    public static class GT_MessageHandler implements IMessageHandler<GT_Packet, GT_Packet> {

        @Override
        public GT_Packet onMessage(GT_Packet message, MessageContext ctx) {
            IThreadListener listener;
            World world;
            if(ctx.side == Side.SERVER) {
                world = ctx.getServerHandler().field_147369_b.field_70170_p;
                listener = world.func_73046_m();
            } else {
                world = GT_Mod.gregtechproxy.getThePlayer().field_70170_p;
                listener = Minecraft.func_71410_x();
            }
            listener.func_152344_a(() -> {
                message.process(world);
            });

            return null;
        }
    }

}
