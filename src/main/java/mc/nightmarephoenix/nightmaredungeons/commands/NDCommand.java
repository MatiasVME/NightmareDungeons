package mc.nightmarephoenix.nightmaredungeons.commands;

import mc.nightmarephoenix.nightmaredungeons.NightmareDungeons;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NDCommand implements CommandExecutor {

    public NDCommand(NightmareDungeons plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage("hello world");

        return true;
    }

    private NightmareDungeons plugin;
}
