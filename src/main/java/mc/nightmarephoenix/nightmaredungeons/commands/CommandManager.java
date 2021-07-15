package mc.nightmarephoenix.nightmaredungeons.commands;

import mc.nightmarephoenix.nightmaredungeons.commands.subcommands.Help;
import mc.nightmarephoenix.nightmaredungeons.commands.subcommands.Reload;
import mc.nightmarephoenix.nightmaredungeons.commands.subcommands.SubCommands;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandManager implements CommandExecutor {

    ArrayList<SubCommands> adminSubCommands = new ArrayList<>();
    ArrayList<SubCommands> usersSubCommands = new ArrayList<>();

    public CommandManager() {
        usersSubCommands.add(new Help());
        adminSubCommands.add(new Reload());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        AtomicBoolean found = new AtomicBoolean(false);
        if(args.length >= 1) {
            if(sender.hasPermission("nightmaredungeons.admin")) {
                adminSubCommands.forEach((cmd) -> {
                    if(args[0].equalsIgnoreCase(cmd.getName())) {
                        cmd.perform(sender, args);
                        found.set(true);
                    }
                });
            }
            if(sender.hasPermission("nightmaredungeons.player") || sender.hasPermission("nightmaredungeons.admin")) {
                usersSubCommands.forEach((cmd) -> {
                    if(args[0].equalsIgnoreCase(cmd.getName())) {
                        cmd.perform(sender, args);
                        found.set(true);
                    }
                });
            }
            if(!found.get())
                Utils.sendConfigMessage("unknown-command", sender);
        } else {
            new Help().perform(sender, args);
        }
        return true;
    }
}
