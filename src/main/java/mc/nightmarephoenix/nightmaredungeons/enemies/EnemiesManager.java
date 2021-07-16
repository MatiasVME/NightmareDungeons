package mc.nightmarephoenix.nightmaredungeons.enemies;

import mc.nightmarephoenix.nightmaredungeons.storage.EnemiesStorage;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class EnemiesManager {

    public static ArrayList<Enemy> getAllEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        EnemiesStorage.getConfigs().forEach((enemyFile) -> {
            enemies.add(new Enemy(
                    enemyFile.getString("name"),
                    EntityType.valueOf(enemyFile.getString("mob-base")),
                    enemyFile.getDouble("health"),
                    enemyFile.getDouble("damage"),
                    enemyFile.getDouble("speed")
            ));
        });
        return enemies;
    }

}
