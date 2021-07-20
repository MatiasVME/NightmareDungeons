package mc.nightmarephoenix.nightmaredungeons.events;

import mc.nightmarephoenix.nightmaredungeons.bosses.Boss;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BossDeath implements Listener {

    @EventHandler
    public void mobDeath(EntityDeathEvent event) {

        for(Boss boss : Global.spawnedBosses) {
            if(boss.getEntity().equals(event.getEntity())) {
                ArrayList<ItemStack> drops = boss.getDrops();
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
