package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

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
    public String syntax() {
        return "/nd";
    }

    @Override
    public List<String> getSubCommandsArgs(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender.hasPermission("nightmaredungeons.admin"))
            Utils.sendConfigMultilineMessage("help-message-admin", sender);
        else
            Utils.sendConfigMultilineMessage("help-message", sender);
    }
}
