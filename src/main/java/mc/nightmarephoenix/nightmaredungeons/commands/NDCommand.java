package mc.nightmarephoenix.nightmaredungeons.commands;

import mc.nightmarephoenix.nightmaredungeons.storage.BossesStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.DungeonsStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.EnemiesStorage;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NDCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // --- Regular users commands --- //


        // --- Admin commands --- //
        if(sender.hasPermission("nightmaredungeons.admin")) {

            if(args.length > 0) {
                if(args[0].equals("reload")) {

                    Global.plugin.reloadConfig();
                    EnemiesStorage.reloadConfig();
                    DungeonsStorage.reloadConfig();
                    BossesStorage.reloadConfig();

                    sender.sendMessage(Utils.Color("&aConfig reloaded."));

                }

            }

        }

        return true;
    }
}
