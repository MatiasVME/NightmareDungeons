package mc.nightmarephoenix.nightmaredungeons;

import mc.nightmarephoenix.nightmaredungeons.commands.NDCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class NightmareDungeons extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"NightmareDungeons plugin has been activated!");
        /**
         * Loading commands
         */
        this.getCommand("nd").setExecutor(new NDCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
