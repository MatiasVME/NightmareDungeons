package mc.nightmarephoenix.nightmaredungeons.util;

import org.bukkit.Bukkit;

public class Logger {
    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(Utils.Color("[NightmareDungeons INFO] " + message));
    }

    public static void warn(String message) {
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&e[NightmareDungeons WARNING] " + message));
    }

    public static void error(String message) {
        Bukkit.getConsoleSender().sendMessage(Utils.Color("&4[NightmareDungeons ERROR] " + message));
    }
}
