package mc.nightmarephoenix.nightmaredungeons.storage.dungeons;

import mc.nightmarephoenix.nightmaredungeons.storage.DungeonsStorage;
import mc.nightmarephoenix.nightmaredungeons.util.Spawns;
import java.util.ArrayList;
import java.util.Set;

public class DungeonsManager {

    public static ArrayList<Dungeon> getAllDungeons() {
        ArrayList<Dungeon> dungeons = new ArrayList<>();

        DungeonsStorage.getConfigs().forEach((dungeon) -> {
            ArrayList<Spawns> spawns = new ArrayList<>();
            ArrayList<DungeonRules> rules = new ArrayList<>();

            /**
             * Iterates over all spawns.
              */
            Set<String> spawnsStrings = dungeon.getConfigurationSection("mobs-spawn").getKeys(false);
            spawnsStrings.forEach((spawn) -> {
                spawns.add(new Spawns(
                        spawn,
                        dungeon.getIntegerList("mobs-spawn." + spawn + ".coords"),
                        dungeon.getString("mobs-spawn." + spawn + ".enemy-file"),
                        dungeon.getInt("mobs-spawn." + spawn + ".enemies-amount-min"),
                        dungeon.getInt("mobs-spawn." + spawn + ".enemies-amount-max")
                ));
            });

            dungeons.add(new Dungeon(

                    dungeon.getString("dungeon-name"),
                    dungeon.getIntegerList("door-coords.pos1"),
                    dungeon.getIntegerList("door-coords.pos2"),
                    spawns,
                    rules
            ));
        });
        return dungeons;
    }

    public static Dungeon getDungeonByName() {
        return null;
    }


}
