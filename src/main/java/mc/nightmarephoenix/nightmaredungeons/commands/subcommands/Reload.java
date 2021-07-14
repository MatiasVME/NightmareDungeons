package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.storage.BossesStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.DungeonsStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.EnemiesStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.Messages;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.CommandSender;

public class Reload extends SubCommands {
    @Override
    public String getDescription() {
        return "Reloads the plugin.";
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Global.plugin.reloadConfig();
        EnemiesStorage.reloadConfig();
        DungeonsStorage.reloadConfig();
        BossesStorage.reloadConfig();
        Messages.reloadConfig();

        Utils.sendConfigMessage("reload-message", sender);
    }
}