package com.spiritlight.packetlistener;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class message {
    public static void send(String s) {
        if(!Main.doSendIngameMessage) return;
        try {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(s));
        } catch (NullPointerException ignored) {}
    }
}
