package at.yedel.forgemodapiexample;



import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;



public class ModApiCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "modapi";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        String type = args[0];
        ForgeModAPIExample.instance.sendHypixelPacket(type);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
