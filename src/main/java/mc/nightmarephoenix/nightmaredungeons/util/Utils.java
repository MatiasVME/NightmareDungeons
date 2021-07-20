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

    public static void sendConfigMessage(String str, CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes(
                '§',
                Color(Messages.getConfig().getString("plugin-prefix")) + Messages.getConfig().getString(str).replace("&", "§")
        ));
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
