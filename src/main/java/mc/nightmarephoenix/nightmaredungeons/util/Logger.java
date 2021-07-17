package mc.nightmarephoenix.nightmaredungeons.util;

import org.bukkit.Bukkit;

public class Logger {
    public static void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(Utils.Color(Global.messages.getString("plugin-prefix")) + message);
    }
}
