package mc.nightmarephoenix.nightmaredungeons.util;

import mc.nightmarephoenix.nightmaredungeons.storage.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Utils {

    /**
     * Translates the color codes.
     * @param str
     * @return
     */
    public static String Color(String str) {
        return ChatColor.translateAlternateColorCodes('§', str.replace("&", "§"));
    }

    /**
     * Same as above but with a list.
     * @param strList
     * @return
     */
    public static List<String> Color(List<String> strList) {
        for(String string: strList) {
            strList.set(strList.indexOf(string), Color(string));
        }
        return strList;
    }

    public static String configMessage(String str) {
        return ChatColor.translateAlternateColorCodes(
                '§',
                Color(Messages.getConfig().getString("plugin-prefix")) + Messages.getConfig().getString(str).replace("&", "§")
        );
    }

    public static void sendConfigMultilineMessage(String message, CommandSender sender) {
        for(String line: Messages.getConfig().getStringList(message)) {
            sender.sendMessage(Utils.Color(line));
        }
    }

}
