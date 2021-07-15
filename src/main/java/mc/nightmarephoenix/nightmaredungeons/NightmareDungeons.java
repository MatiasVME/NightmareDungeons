package mc.nightmarephoenix.nightmaredungeons;

import mc.nightmarephoenix.nightmaredungeons.commands.CommandManager;
import mc.nightmarephoenix.nightmaredungeons.storage.BossesStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.DungeonsStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.EnemiesStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.Messages;
import mc.nightmarephoenix.nightmaredungeons.storage.dungeons.DungeonsManager;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NightmareDungeons extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(Utils.Color("&aNightmareDungeons has been activated!"));

        /**
         * Initializing global variables
         */
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


        DungeonsManager.getAllDungeons().forEach((dungeon) -> {
            System.out.println(dungeon.getName());
            System.out.println(dungeon.getDoorCoordinates1());
            System.out.println(dungeon.getDoorCoordinates2());
            System.out.println(dungeon.getMobsSpawns());
            System.out.println(dungeon.getRules());
            System.out.println("------------------");

        });


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
