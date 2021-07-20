package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Version extends SubCommands {
    @Override
    public String getDescription() {
        return "Gives the plugin version.";
    }

    @Override
    public String getName() {
        return "version";
    }

    @Override
    public String syntax() {
        return "/nd version";
    }

    @Override
    public List<String> getSubCommandsArgs(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        sender.sendMessage(Utils.Color("&eNightmareDungeons version: &f" + Global.plugin.getDescription().getVersion()));
    }
}
