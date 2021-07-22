package mc.nightmarephoenix.nightmaredungeons.dungeons;

import mc.nightmarephoenix.nightmaredungeons.bosses.Boss;
import mc.nightmarephoenix.nightmaredungeons.enemies.Enemy;
import java.util.ArrayList;
import java.util.List;

public class Dungeon {

    public Dungeon(String name, List<Integer> doorCoordinates1, List<Integer> doorCoordinates2, ArrayList<DungeonRules> rules, List<String> top1DamagerCommands, List<String> top2DamagerCommands, List<String> top3DamagerCommands, Boss boss, ArrayList<Enemy> enemies) {

        this.name = name;
        this.doorCoordinates1 = doorCoordinates1;
        this.doorCoordinates2 = doorCoordinates2;
        this.rules = rules;
        this.top1DamagerCommands = top1DamagerCommands;
        this.top2DamagerCommands = top2DamagerCommands;
        this.top3DamagerCommands = top3DamagerCommands;
        this.boss = boss;
        this.enemies = enemies;
        this.status = DungeonStatus.COOL_DOWN;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getTop1DamagerCommands() {
        return top1DamagerCommands;
    }

    public List<String> getTop2DamagerCommands() {
        return top2DamagerCommands;
    }

    public List<String> getTop3DamagerCommands() {
        return top3DamagerCommands;
    }

    public Boss getBoss() {
        return boss;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public DungeonStatus getStatus() {
        return status;
    }

    public void setStatus(DungeonStatus status) {
        this.status = status;
    }

    private String                  name;
    private List<Integer>           doorCoordinates1, doorCoordinates2;
    private ArrayList<DungeonRules> rules;
    private List<String>            top1DamagerCommands;
    private List<String>            top2DamagerCommands;
    private List<String>            top3DamagerCommands;
    private Boss                    boss;
    private ArrayList<Enemy>        enemies;
    private DungeonStatus           status;

}
