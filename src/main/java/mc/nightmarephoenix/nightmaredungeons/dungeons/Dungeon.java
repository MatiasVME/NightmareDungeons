package mc.nightmarephoenix.nightmaredungeons.dungeons;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {

    public Dungeon(String name, List<Integer> doorCoordinates1, List<Integer> doorCoordinates2, ArrayList<DungeonSpawn> spawns, ArrayList<DungeonRules> rules) {

        this.name = name;
        this.doorCoordinates1 = doorCoordinates1;
        this.doorCoordinates2 = doorCoordinates2;
        this.mobsSpawns = spawns;
        this.rules = rules;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DungeonSpawn> getMobsSpawns() {
        return mobsSpawns;
    }

    public void setMobsSpawns(ArrayList<DungeonSpawn> mobsSpawns) {
        this.mobsSpawns = mobsSpawns;
    }

    public ArrayList<DungeonRules> getRules() {
        return rules;
    }

    public void setRules(ArrayList<DungeonRules> rules) {
        this.rules = rules;
    }

    public List<Integer> getDoorCoordinates1() {
        return doorCoordinates1;
    }

    public void setDoorCoordinates1(List<Integer> doorCoordinates1) {
        this.doorCoordinates1 = doorCoordinates1;
    }

    public List<Integer> getDoorCoordinates2() {
        return doorCoordinates2;
    }

    public void setDoorCoordinates2(List<Integer> doorCoordinates2) {
        this.doorCoordinates2 = doorCoordinates2;
    }

    private String name;
    private List<Integer> doorCoordinates1, doorCoordinates2;
    private ArrayList<DungeonSpawn> mobsSpawns;
    private ArrayList<DungeonRules> rules;
    private ArrayList<String> top1DamagerCommands, top2DamagerCommands, top3DamagerCommands;

}
