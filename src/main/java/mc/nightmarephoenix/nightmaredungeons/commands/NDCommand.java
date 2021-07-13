package mc.nightmarephoenix.nightmaredungeons.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NDCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // --- Regular users commands --- //





        // --- Admin commands --- //
        if(sender.hasPermission("nightmaredungeons.admin")) {



        }

        return true;
    }
}
