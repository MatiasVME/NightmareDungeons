package mc.nightmarephoenix.nightmaredungeons.util;

import mc.nightmarephoenix.nightmaredungeons.NightmareDungeons;
import mc.nightmarephoenix.nightmaredungeons.bosses.Boss;
import mc.nightmarephoenix.nightmaredungeons.dungeons.Dungeon;
import mc.nightmarephoenix.nightmaredungeons.enemies.Enemy;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class Global {

    public static NightmareDungeons  plugin;
    public static FileConfiguration  messages;
    public static ArrayList<Dungeon> dungeons;
    public static ArrayList<Enemy>   enemies;
    public static ArrayList<Boss>    bosses;

}
