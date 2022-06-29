package com.spiritlight.packetlistener;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MODID = "packetlistener";
    public static final String NAME = "PacketListener";
    public static final String VERSION = "1.0";
    public static boolean doSendIngameMessage = true;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new ConnectionEvent());
        ClientCommandHandler.instance.registerCommand(new Command());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}
