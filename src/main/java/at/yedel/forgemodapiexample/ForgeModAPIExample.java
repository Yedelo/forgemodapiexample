package com.example;



import java.util.Objects;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;



@Mod(modid = "examplemod", useMetadata = true)
public class ExampleMod {
    @Mod.Instance
    public static ExampleMod instance;
    private static final PacketBuffer versionBuffer = new PacketBuffer(Unpooled.buffer(1).writeByte(1)); // for now, this is the only thing that is sent in the packet

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new ModAPICommand());
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void sendHypixelPacket(String type) {
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C17PacketCustomPayload("hypixel:" + type, versionBuffer));
    }

    @SubscribeEvent
    public void onHypixelPacket(FMLNetworkEvent.ClientCustomPacketEvent event) {
        String channel = event.packet.channel();
        if (Objects.equals(channel, "hypixel:ping")) handleHypixelPing(event.packet.payload());
        else if (Objects.equals(channel, "hypixel:location")) handleHypixelLocation(event.packet.payload());
        else if (Objects.equals(channel, "hypixel:party_info")) handleHypixelPartyInfo(event.packet.payload());
        else if (Objects.equals(channel, "hypixel:player_info")) handleHypixelPlayerInfo(event.packet.payload());
    }

    public void handleHypixelPing(ByteBuf response) {

    }
    public void handleHypixelLocation(ByteBuf response) {

    }
    public void handleHypixelPartyInfo(ByteBuf response) {

    }
    public void handleHypixelPlayerInfo(ByteBuf response) {

    }
}
