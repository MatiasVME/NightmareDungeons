package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.bosses.BossManager;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonsManager;
import mc.nightmarephoenix.nightmaredungeons.enemies.EnemiesManager;
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

        // Global variables
        Global.dungeons = DungeonsManager.getAllDungeons();
        Global.enemies  = EnemiesManager.getAllEnemies();
        Global.bosses   = BossManager.getAllBosses();

        Utils.sendConfigMessage("reload-message", sender);
    }
}
