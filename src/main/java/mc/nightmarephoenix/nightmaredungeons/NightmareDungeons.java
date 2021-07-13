package mc.nightmarephoenix.nightmaredungeons;

import mc.nightmarephoenix.nightmaredungeons.commands.NDCommand;
import mc.nightmarephoenix.nightmaredungeons.storage.BossesStorage;
import mc.nightmarephoenix.nightmaredungeons.storage.EnemiesStorage;
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
        this.getCommand("nd").setExecutor(new NDCommand());


        this.saveDefaultConfig();
        new BossesStorage().saveDefaultConfig();
        new EnemiesStorage().saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
