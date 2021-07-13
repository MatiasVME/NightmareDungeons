package mc.nightmarephoenix.nightmaredungeons.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Logger {
    private static Plugin plugin;

    public Logger(Plugin plugin) {
        this.plugin = plugin;
    }

    public static void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(Utils.Color(plugin.getConfig().getString("plugin-prefix")) + message);
    }
}
