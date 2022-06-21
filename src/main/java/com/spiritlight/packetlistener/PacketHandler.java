package com.spiritlight.packetlistener;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEntityVelocity;

import java.util.ArrayList;
import java.util.List;
public class PacketHandler extends ChannelDuplexHandler {
    protected static final List<String> listenPacketR = new ArrayList<>();
    protected static final List<String> listenPacketW = new ArrayList<>();
    protected static final List<String> discardPacketR = new ArrayList<>();
    protected static final List<String> discardPacketW = new ArrayList<>();
    public static final String READ = "read";
    public static final String WRITE = "write";
    @Override // Receive packet
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        if(obj instanceof Packet) {
            final List<String> lpr = new ArrayList<>(listenPacketR);
            final List<String> dpr = new ArrayList<>(discardPacketR);
            for(String s : lpr) {
                if(obj.getClass().getName().contains(s)) {
                    System.out.println("Caught received packet " + obj.getClass().getName());
                    message.send("Packet caught: " + obj.getClass().getName());
                    break;
                }
            }
            for(String s : dpr) {
                if(obj.getClass().getName().contains(s)) {
                    dpr.remove(s);
                    return;
                }
            }
        }
        super.channelRead(ctx, obj);
    }

    @Override // Send packet
    public void write(ChannelHandlerContext ctx, Object obj, ChannelPromise promise) throws Exception {
        if(obj instanceof Packet) {
            final List<String> lpw = new ArrayList<>(listenPacketW);
            final List<String> dpw = new ArrayList<>(discardPacketW);
                for(String s : lpw) {
                    if(obj.getClass().getName().contains(s)) {
                        System.out.println("Caught sent packet " + obj.getClass().getName());
                        message.send("Packet caught: " + obj.getClass().getName());
                        break;
                    }
                }
            for(String s : dpw) {
                if(obj.getClass().getName().contains(s)) {
                    dpw.remove(s);
                    return;
                }
            }
            }
        super.write(ctx, obj, promise);
    }

    public static boolean listen(String packetName, String type) throws IllegalArgumentException {
        // true = operation success; otherwise fail
        if(!packetName.contains("Packet")) throw new IllegalArgumentException("Name must relate to 'Packet'");
        try {
            switch (type) {
                case READ:
                    if(listenPacketR.contains(packetName)) {
                        listenPacketR.remove(packetName);
                        message.send("Removed " + packetName + " from listener.");
                    } else {
                        listenPacketR.add(packetName);
                        message.send("Added " + packetName + " to listener.");
                    }
                    break;
                case WRITE:
                    if(listenPacketW.contains(packetName)) {
                        listenPacketW.remove(packetName);
                        message.send("Removed " + packetName + " from listener.");

                    } else {
                        listenPacketW.add(packetName);
                        message.send("Added " + packetName + " to listener.");
                    }
                    break;
                default:
                    return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            message.send("Exception caught: " + ex.getMessage() + "\nMore info in console logs...");
            return false;
        }
        return true;
    }

    public static boolean discard(String packetName, String type) throws IllegalArgumentException {
        // true = operation success; otherwise fail
        if(!packetName.contains("Packet")) throw new IllegalArgumentException("Name must relate to 'Packet'");
        try {
            switch (type) {
                case READ:
                    if(discardPacketR.contains(packetName)) {
                        discardPacketR.remove(packetName);
                        message.send("Removed " + packetName + " from remover.");
                    } else {
                        discardPacketR.add(packetName);
                        message.send("Added " + packetName + " to remover.");
                    }
                    break;
                case WRITE:
                    if(discardPacketW.contains(packetName)) {
                        discardPacketW.remove(packetName);
                        message.send("Removed " + packetName + " from remover.");

                    } else {
                        discardPacketW.add(packetName);
                        message.send("Added " + packetName + " to remover.");
                    }
                    break;
                default:
                    return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            message.send("Exception caught: " + ex.getMessage() + "\nMore info in console logs...");
            return false;
        }
        return true;
    }
}
