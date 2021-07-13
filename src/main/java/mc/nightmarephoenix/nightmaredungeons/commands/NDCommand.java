package mc.nightmarephoenix.nightmaredungeons.commands;

import mc.nightmarephoenix.nightmaredungeons.storage.BossesStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.DungeonsStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.EnemiesStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.Messages;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NDCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // --- Admin commands --- //
        if(sender.hasPermission("nightmaredungeons.admin")) {

            if(args.length == 0) {
                Utils.sendConfigMultilineMessage("help-message-admin", sender);
            } else if(args.length == 1) {
                if(args[0].equalsIgnoreCase("reload")) {

                    Global.plugin.reloadConfig();
                    EnemiesStorage.reloadConfig();
                    DungeonsStorage.reloadConfig();
                    BossesStorage.reloadConfig();
                    Messages.reloadConfig();

                    sender.sendMessage(Utils.configMessage("reload-message"));

                } else if(args[0].equalsIgnoreCase("help")) {
                    Utils.sendConfigMultilineMessage("help-message-admin", sender);
                } else {
                    sender.sendMessage(Utils.configMessage("unknown-command"));
                }
            }

        } else {
            // --- Regular users commands --- //
            if(args.length == 0) {
                Utils.sendConfigMultilineMessage("help-message", sender);
            } else if(args.length == 1) {
                if(args[0].equalsIgnoreCase("help")) {
                    Utils.sendConfigMultilineMessage("help-message", sender);
                } else {
                    sender.sendMessage(Utils.configMessage("unknown-command"));
                }
            }
        }



        return true;
    }
}
