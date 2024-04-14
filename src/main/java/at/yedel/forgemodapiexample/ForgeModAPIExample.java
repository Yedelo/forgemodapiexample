package at.yedel.forgemodapiexample;



import io.netty.buffer.Unpooled;
import net.hypixel.modapi.HypixelModAPI;
import net.hypixel.modapi.handler.ClientboundPacketHandler;
import net.hypixel.modapi.handler.PacketHandler;
import net.hypixel.modapi.packet.HypixelPacket;
import net.hypixel.modapi.packet.HypixelPacketType;
import net.hypixel.modapi.packet.impl.clientbound.ClientboundLocationPacket;
import net.hypixel.modapi.packet.impl.clientbound.ClientboundPartyInfoPacket;
import net.hypixel.modapi.packet.impl.clientbound.ClientboundPingPacket;
import net.hypixel.modapi.packet.impl.clientbound.ClientboundPlayerInfoPacket;
import net.hypixel.modapi.serializer.PacketSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.CustomPacketEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;



@Mod(modid = "forgemodapiexample", useMetadata = true)
public class ForgeModAPIExample {

    @Mod.Instance
    public static ForgeModAPIExample instance;

    public static final Minecraft minecraft = Minecraft.getMinecraft();
    public static final PacketBuffer one = new PacketBuffer(Unpooled.buffer().writeByte(1));

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        HypixelModAPI.getInstance().registerHandler(new ClientboundPacketHandler() {
            @Override
            public void handle(HypixelPacket packet) {
                if (packet instanceof ClientboundLocationPacket) HypixelPacketHandler.handleHypixelLocation((ClientboundLocationPacket) packet);
                else if (packet instanceof ClientboundPingPacket) HypixelPacketHandler.handleHypixelPing((ClientboundPingPacket) packet);
                else if (packet instanceof ClientboundPartyInfoPacket) HypixelPacketHandler.handleHypixelPartyInfo((ClientboundPartyInfoPacket) packet);
                else if (packet instanceof ClientboundPlayerInfoPacket) HypixelPacketHandler.handleHypixelPlayerInfo((ClientboundPlayerInfoPacket) packet);
            }
        });
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new ModApiCommand());
    }

    @SubscribeEvent
    public void onServerConnect(ClientConnectedToServerEvent event) {
        event.manager.channel().pipeline().addBefore("packet_handler", "hypixel_packet_handler", new HypixelPacketHandler());
    }

    public void sendHypixelPacket(String type) {
        minecraft.getNetHandler().addToSendQueue(new C17PacketCustomPayload("hypixel:" + type, one));
    }
}
