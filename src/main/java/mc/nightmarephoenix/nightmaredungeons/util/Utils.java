package mc.nightmarephoenix.nightmaredungeons.util;

import org.bukkit.ChatColor;

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

}