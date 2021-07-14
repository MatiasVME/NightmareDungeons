package mc.nightmarephoenix.nightmaredungeons.storage.dungeons;

import mc.nightmarephoenix.nightmaredungeons.util.Spawns;

import java.util.ArrayList;

public class Dungeon {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getDoorCoordinates() {
        return doorCoordinates;
    }

    public void setDoorCoordinates(double[] doorCoordinates) {
        this.doorCoordinates = doorCoordinates;
    }

    public ArrayList<Spawns> getMobsSpawns() {
        return mobsSpawns;
    }

    public void setMobsSpawns(ArrayList<Spawns> mobsSpawns) {
        this.mobsSpawns = mobsSpawns;
    }

    public DungeonRules getRules() {
        return rules;
    }

    public void setRules(DungeonRules rules) {
        this.rules = rules;
    }

    private String name;
    private double[] doorCoordinates;
    private ArrayList<Spawns> mobsSpawns;
    private DungeonRules rules;
}
