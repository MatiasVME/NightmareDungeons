package mc.nightmarephoenix.nightmaredungeons.events;

import mc.nightmarephoenix.nightmaredungeons.dungeons.Dungeon;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonStatus;
import mc.nightmarephoenix.nightmaredungeons.enemies.Enemy;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EnemyDeath implements Listener {

    @EventHandler
    public void mobDeath(EntityDeathEvent event) {
        for(Dungeon dungeon : Global.dungeons) {
            if(dungeon.getStatus().equals(DungeonStatus.READY) || dungeon.getStatus().equals(DungeonStatus.IN_PROGRESS)) {
                for(Enemy enemy : dungeon.getEnemies()) {
                    if(enemy.getEntity().equals(event.getEntity())) {
                        ArrayList<ItemStack> drops = enemy.getDrops();
                        if(!drops.isEmpty()) {
                            event.getDrops().clear();

                            for(ItemStack is : drops) {
                                event.getEntity().getLocation().getWorld().dropItem(
                                        event.getEntity().getLocation(),
                                        is
                                );
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
}
