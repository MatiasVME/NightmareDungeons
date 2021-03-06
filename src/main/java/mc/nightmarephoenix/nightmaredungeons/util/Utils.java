package mc.nightmarephoenix.nightmaredungeons.util;

import mc.nightmarephoenix.nightmaredungeons.storage.Messages;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Utils {

    /**
     * Translates the color codes.
     * @param str string with & passed
     * @return formatted string
     */
    public static String Color(String str) {
        return ChatColor.translateAlternateColorCodes('§', str.replace("&", "§"));
    }

    /**
     * Same as above but with a list.
     * @param strList list of strings with &
     * @return formatted list of strings
     */
    public static List<String> Color(List<String> strList) {
        for(String string: strList) {
            strList.set(strList.indexOf(string), Color(string));
        }
        return strList;
    }

    public static void sendConfigMessage(String str, CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '§',
                Color(Messages.getConfig().getString("plugin-prefix")) + Messages.getConfig().getString(str).replace("&", "§")
        ));
    }

    public static void sendMessage(String message, CommandSender sender, HashMap<String, String> placeholders, boolean prefix) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            String toReplace = entry.getKey();
            String target = entry.getValue();
            message = message.replaceAll(toReplace, target);
        }
        sender.sendMessage(
                ((prefix) ? Color(Messages.getConfig().getString("plugin-prefix")) : "") +
                        Color(message)
        );
    }

    public static void sendConfigMessage(String str, CommandSender sender, HashMap<String, String> placeholders) {
        String message = Messages.getConfig().getString(str);
        sendMessage(message, sender, placeholders, true);
    }

    public static void sendConfigMultilineMessage(String message, CommandSender sender, HashMap<String, String> placeholders) {
        for(String line: Messages.getConfig().getStringList(message)) {
            sendMessage(line, sender, placeholders, false);
        }
    }

    public static void sendConfigMultilineMessage(String message, CommandSender sender) {
        for(String line: Messages.getConfig().getStringList(message)) {
            sender.sendMessage(Utils.Color(line));
        }
    }

    public static BossBar bossbar(LivingEntity livingEntity, String title, BarColor color) {
        BossBar bossBar = Global.plugin.getServer().createBossBar(title, color, BarStyle.SOLID);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!livingEntity.isDead()) {
                    bossBar.setProgress(livingEntity.getHealth() / livingEntity.getMaxHealth());
                } else { // When the boss dies
                    // Remove the bossbar for all players.
                    for (Player player : bossBar.getPlayers()) {
                        bossBar.removePlayer(player);
                    }
                    bossBar.setVisible(false);
                    cancel();
                }
            }
        }.runTaskTimer(Global.plugin, 0, 1);
        return bossBar;
    }

}
