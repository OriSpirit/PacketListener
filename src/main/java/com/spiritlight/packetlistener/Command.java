package com.spiritlight.packetlistener;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Locale;

@ParametersAreNonnullByDefault @MethodsReturnNonnullByDefault
public class Command extends CommandBase {
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getName() {
        return "packet";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/" + getName();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length == 0) {
            message.send("/packet s/r <name>");
            return;
        }
        switch(args[0].toLowerCase(Locale.ROOT)) {
            case "s":
                if(args.length == 1) {
                    message.send("Currently listening to packets matching these names:");
                    for(String s : PacketHandler.listenPacketW) {
                        message.send("- " + s);
                    }
                    break;
                }
                try {
                    PacketHandler.listen(args[1], PacketHandler.WRITE);
                } catch (IllegalArgumentException e) {
                    message.send(e.getMessage());
                }
                break;
            case "r":
                if(args.length == 1) {
                    message.send("Currently listening to packets matching these names:");
                    for(String s : PacketHandler.listenPacketR) {
                        message.send("- " + s);
                    }
                    break;
                }
                try {
                    PacketHandler.listen(args[1], PacketHandler.READ);
                } catch (IllegalArgumentException e) {
                    message.send(e.getMessage());
                }
                break;
            case "shutup":
                Main.doSendIngameMessage = !Main.doSendIngameMessage;
                message.send("Sending in game message is now " + Main.doSendIngameMessage);
                break;
            default:
                message.send("Invalid operation. s: packet sent; r: packet received.");
                break;
        }
    }
}
