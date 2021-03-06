package mc.nightmarephoenix.nightmaredungeons;

import com.tchristofferson.configupdater.ConfigUpdater;
import mc.nightmarephoenix.nightmaredungeons.bosses.Boss;
import mc.nightmarephoenix.nightmaredungeons.bosses.BossManager;
import mc.nightmarephoenix.nightmaredungeons.commands.CommandManager;
import mc.nightmarephoenix.nightmaredungeons.dungeons.Dungeon;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonStatus;
import mc.nightmarephoenix.nightmaredungeons.enemies.EnemiesManager;
import mc.nightmarephoenix.nightmaredungeons.enemies.Enemy;
import mc.nightmarephoenix.nightmaredungeons.events.BossDeath;
import mc.nightmarephoenix.nightmaredungeons.events.BossHurtEvent;
import mc.nightmarephoenix.nightmaredungeons.events.EnemyDeath;
import mc.nightmarephoenix.nightmaredungeons.events.EnemyHurtEvent;
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
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public final class NightmareDungeons extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(Utils.Color("&aNightmareDungeons has been activated!"));

        Global.plugin = this;

        // // Loading commands // //
        this.getCommand("nd").setExecutor(new CommandManager());
        this.getCommand("dungeons").setExecutor(new CommandManager());

        // // Default config files and check for updates // //
        this.saveDefaultConfig();
        File configFile = new File(getDataFolder(), "config.yml");
        try {
            ConfigUpdater.update(this, "config.yml", configFile, Arrays.asList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.reloadConfig();

        BossesStorage.saveDefaultConfig();
        BossesStorage.checkUpdate();

        EnemiesStorage.saveDefaultConfig();
        EnemiesStorage.checkUpdate();

        DungeonsStorage.saveDefaultConfig();
        DungeonsStorage.checkUpdate();

        Messages.saveDefaultConfig();
        Messages.checkUpdate();


        // // Caches all dungeons // //
        Global.enemies  = EnemiesManager.getAllEnemies();
        Global.bosses   = BossManager.getAllBosses();
        Global.dungeons = DungeonsManager.getAllDungeons();
        Global.messages = Messages.getConfig();

        // // Events // //
        getServer().getPluginManager().registerEvents(new BossHurtEvent(), this);
        getServer().getPluginManager().registerEvents(new EnemyHurtEvent(), this);
        getServer().getPluginManager().registerEvents(new EnemyDeath(), this);
        getServer().getPluginManager().registerEvents(new BossDeath(), this);

        // // Update checker // //
//        new UpdateChecker(0).getVersion(version -> {
//            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
//                Logger.sendMessage("There is not a new update available.");
//            } else {
//                Logger.sendMessage("There is a new update available.");
//            }
//        });

    }

    @Override
    public void onDisable() {
        try {
            Logger.info("Killing all bosses and enemies.");
            for(Dungeon dungeon : Global.dungeons) {
                if(dungeon.getStatus().equals(DungeonStatus.READY) || dungeon.getStatus().equals(DungeonStatus.IN_PROGRESS)) {
                    for(Enemy e : dungeon.getEnemies()) {
                        e.getEntity().remove();
                    }
                    if(dungeon.getBoss().getEntity() != null)
                        dungeon.getBoss().getEntity().remove();
                }
            }

            Logger.info("Removing all bossBars.");
            for(BossBar b : Global.bossBars) b.removeAll();
        } catch (Exception e) {
            Logger.error("Error unloading NightmareDungeons plugin.");
        }

    }
}
