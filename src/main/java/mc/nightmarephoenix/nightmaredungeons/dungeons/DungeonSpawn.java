package mc.nightmarephoenix.nightmaredungeons.dungeons;

import java.util.List;

public class DungeonSpawn {

    public DungeonSpawn(String spawnName, List<Integer> coordinates, String enemyFile, int enemies_min, int enemies_max) {
        this.name = spawnName;
        this.coordinates = coordinates;
        this.file = enemyFile;
        this.enemies_max = enemies_max;
        this.enemies_min = enemies_min;
    }

    private String name;
    private List<Integer> coordinates;
    private String file;
    private int enemies_min;
    private int enemies_max;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getEnemies_min() {
        return enemies_min;
    }

    public void setEnemies_min(int enemies_min) {
        this.enemies_min = enemies_min;
    }

    public int getEnemies_max() {
        return enemies_max;
    }

    public void setEnemies_max(int enemies_max) {
        this.enemies_max = enemies_max;
    }
}
