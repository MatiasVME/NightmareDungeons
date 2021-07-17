package mc.nightmarephoenix.nightmaredungeons.dungeons;

import mc.nightmarephoenix.nightmaredungeons.bosses.Boss;
import mc.nightmarephoenix.nightmaredungeons.enemies.Enemy;
import mc.nightmarephoenix.nightmaredungeons.storage.DungeonsStorage;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DungeonsManager {

    public static ArrayList<Dungeon> getAllDungeons() {
        ArrayList<Dungeon> dungeons = new ArrayList<>();

        DungeonsStorage.getConfigs().forEach((dungeon) -> {
            ArrayList<DungeonRules> rules = new ArrayList<>();
            ArrayList<Enemy> enemies = new ArrayList<>();
            Boss dungeonBoss = null;


            /**
             * Iterates over all enemies spawns and assigns the enemy to the arraylist.
             */
            Set<String> spawnsStrings = dungeon.getConfigurationSection("mobs-spawn").getKeys(false);
            spawnsStrings.forEach((spawn) -> {
                for(Enemy e: Global.enemies) {
                    if(dungeon.getString("mobs-spawn." + spawn + ".enemy-name").equalsIgnoreCase(e.getName())) {
                        Enemy e1 = e;
                        List<Integer> coords = dungeon.getIntegerList("mobs-spawn." + spawn + ".coords");
                        e1.setSpawnLocation(
                                new Location(
                                        Bukkit.getWorld(dungeon.getString("world-name")),
                                        coords.get(0),
                                        coords.get(1),
                                        coords.get(2)
                                ));
                        enemies.add(e1);
                        break;
                    }
                }
            });

            /**
             * Loading the boss.
             */
            for(Boss boss: Global.bosses) {
                if(dungeon.getString("boss-spawn.spawn.boss-name").equalsIgnoreCase(boss.getName())) {
                    List<Integer> coords = dungeon.getIntegerList("boss-spawn.spawn.coords");
                    boss.setSpawnLocation(
                            new Location(
                                    Bukkit.getWorld(dungeon.getString("world-name")),
                                    coords.get(0),
                                    coords.get(1),
                                    coords.get(2)
                            ));
                    dungeonBoss = boss;
                    break;
                }
            }

            dungeons.add(new Dungeon(
                    dungeon.getString("dungeon-name"),
                    dungeon.getIntegerList("door-coords.pos1"),
                    dungeon.getIntegerList("door-coords.pos2"),
                    rules,
                    dungeon.getStringList("rewards.top-damager-commands.1"),
                    dungeon.getStringList("rewards.top-damager-commands.2"),
                    dungeon.getStringList("rewards.top-damager-commands.3"),
                    dungeonBoss,
                    enemies
            ));
        });
        return dungeons;
    }

    public static Dungeon getDungeonByName() {
        return null;
    }


}
