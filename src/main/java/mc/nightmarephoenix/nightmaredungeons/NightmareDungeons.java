package mc.nightmarephoenix.nightmaredungeons;

import mc.nightmarephoenix.nightmaredungeons.bosses.BossManager;
import mc.nightmarephoenix.nightmaredungeons.commands.CommandManager;
import mc.nightmarephoenix.nightmaredungeons.enemies.EnemiesManager;
import mc.nightmarephoenix.nightmaredungeons.storage.BossesStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.DungeonsStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.EnemiesStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.Messages;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonsManager;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Logger;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

public final class NightmareDungeons extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(Utils.Color("&aNightmareDungeons has been activated!"));

        Global.plugin = this;

        /**
         * Loading commands
         */
        this.getCommand("nd").setExecutor(new CommandManager());

        /**
         * Default config files
         */
        this.saveDefaultConfig();
        BossesStorage.saveDefaultConfig();
        EnemiesStorage.saveDefaultConfig();
        DungeonsStorage.saveDefaultConfig();
        Messages.saveDefaultConfig();


        /**
         * Caches all dungeons.
         */
        Global.dungeons = DungeonsManager.getAllDungeons();
        Global.enemies  = EnemiesManager.getAllEnemies();
        Global.bosses   = BossManager.getAllBosses();
        Global.messages = Messages.getConfig();

    }

    @Override
    public void onDisable() {

        Logger.sendMessage("Killing all bosses and enemies.");
        for(Entity e : Global.spawnedEnemies) e.remove();
        for(Entity e : Global.spawnedBosses) e.remove();

        Logger.sendMessage("Removing all bossBars.");
        for(BossBar b : Global.bossBars) b.removeAll();

    }
}
