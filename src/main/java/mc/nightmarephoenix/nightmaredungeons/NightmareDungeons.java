package mc.nightmarephoenix.nightmaredungeons;

import mc.nightmarephoenix.nightmaredungeons.bosses.Boss;
import mc.nightmarephoenix.nightmaredungeons.bosses.BossManager;
import mc.nightmarephoenix.nightmaredungeons.commands.CommandManager;
import mc.nightmarephoenix.nightmaredungeons.enemies.EnemiesManager;
import mc.nightmarephoenix.nightmaredungeons.enemies.Enemy;
import mc.nightmarephoenix.nightmaredungeons.events.BossHurtEvent;
import mc.nightmarephoenix.nightmaredungeons.events.EnemyDeath;
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
        this.getCommand("dungeons").setExecutor(new CommandManager());


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
        Global.enemies  = EnemiesManager.getAllEnemies();
        Global.bosses   = BossManager.getAllBosses();
        Global.dungeons = DungeonsManager.getAllDungeons();
        Global.messages = Messages.getConfig();

        /**
         * Events
         */
        getServer().getPluginManager().registerEvents(new BossHurtEvent(), this);
        getServer().getPluginManager().registerEvents(new EnemyDeath(), this);


    }

    @Override
    public void onDisable() {
        try {
            Logger.sendMessage("Killing all bosses and enemies.");
            for(Enemy e : Global.spawnedEnemies) e.getEntity().remove();
            for(Boss e : Global.spawnedBosses) e.getEntity().remove();

            Logger.sendMessage("Removing all bossBars.");
            for(BossBar b : Global.bossBars) b.removeAll();
        } catch (Exception e) {
            System.out.println("Error unloading NightmareDungeons plugin.");
        }

    }
}
