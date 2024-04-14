package at.yedel.forgemodapiexample;



import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import net.hypixel.modapi.HypixelModAPI;
import net.hypixel.modapi.packet.impl.clientbound.ClientboundLocationPacket;
import net.hypixel.modapi.packet.impl.clientbound.ClientboundPartyInfoPacket;
import net.hypixel.modapi.packet.impl.clientbound.ClientboundPingPacket;
import net.hypixel.modapi.packet.impl.clientbound.ClientboundPlayerInfoPacket;
import net.hypixel.modapi.serializer.PacketSerializer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;



public class HypixelPacketHandler extends ChannelDuplexHandler {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof net.minecraft.network.play.server.S3FPacketCustomPayload) {
            S3FPacketCustomPayload packet = (S3FPacketCustomPayload) msg;
            HypixelModAPI.getInstance().handle(packet.getChannelName(), new PacketSerializer(packet.getBufferData()));
        }
        super.channelRead(ctx, msg);
    }
    public static void handleHypixelLocation(ClientboundLocationPacket packet) {
        // example:
        System.out.println("Mode: " + packet.getMode());
    }

    public static void handleHypixelPing(ClientboundPingPacket packet) {
        // example:
        System.out.println("Hypixel said pong!");
    }

    public static void handleHypixelPartyInfo(ClientboundPartyInfoPacket packet) {
        // example:
        System.out.println("In party: " + packet.isInParty());
    }

    public static void handleHypixelPlayerInfo(ClientboundPlayerInfoPacket packet) {
        // example:
        System.out.println("Player rank: " + packet.getPlayerRank());
    }
}
