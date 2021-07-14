package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.CommandSender;

public class Help extends SubCommands {
    @Override
    public String getDescription() {
        return "Shows the help";
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender.hasPermission("nightmaredungeons.admin"))
            Utils.sendConfigMultilineMessage("help-message-admin", sender);
        else
            Utils.sendConfigMultilineMessage("help-message", sender);

    }
}
