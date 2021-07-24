package mc.nightmarephoenix.nightmaredungeons.events;

import mc.nightmarephoenix.nightmaredungeons.dungeons.Dungeon;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonStatus;
import mc.nightmarephoenix.nightmaredungeons.enemies.Enemy;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EnemyHurtEvent implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        // // Command spawned enemies // //
        for(Enemy e : Global.enemies) {
            if(event.getEntity().equals(e.getEntity())) {
                event.setDamage(event.getDamage() * (2048 / e.getHealth()));
            }
        }

        // // Dungeon spawned enemies // //
        for(Dungeon dungeon : Global.dungeons) {
            if(dungeon.getStatus().equals(DungeonStatus.READY) || dungeon.getStatus().equals(DungeonStatus.IN_PROGRESS)) {
                for(Enemy e : dungeon.getEnemies()) {
                    if(event.getEntity().equals(e.getEntity())) {
                        event.setDamage(event.getDamage() * (2048 / e.getHealth()));
                    }
                }
            }
        }
    }
}
