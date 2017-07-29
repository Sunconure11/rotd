package com.TheRPGAdventurer.server.cmd;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandDragonTame extends CommandBase implements IDragonModifier {
    
    @Override
    public String getCommandName() {
        return "tame";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return String.format("%s [username]", getCommandName());
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayerMP) {
            EntityPlayerMP player;
            if (args.length > 0) {
                player = getPlayer(server, sender, args[0]);
            } else {
                player = (EntityPlayerMP) sender;    
            }

            applyModifier(server, sender, dragon -> dragon.tamedFor(player, true));
        } else {
            // console can't tame dragons
            throw new CommandException("commands.dragon.canttame");
        }
    }
}