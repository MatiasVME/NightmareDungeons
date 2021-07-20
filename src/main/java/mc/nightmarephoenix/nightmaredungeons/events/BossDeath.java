package mc.nightmarephoenix.nightmaredungeons.events;

import mc.nightmarephoenix.nightmaredungeons.bosses.Boss;
import mc.nightmarephoenix.nightmaredungeons.enemies.Enemy;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class BossDeath implements Listener {

    @EventHandler
    public void mobDeath(EntityDeathEvent event) {

        for(Boss boss : Global.spawnedBosses) {
            if(boss.getEntity().equals(event.getEntity())) {
                event.getDrops().clear();
                event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Material.OAK_BOAT));


                break;
            }
        }
    }

}
