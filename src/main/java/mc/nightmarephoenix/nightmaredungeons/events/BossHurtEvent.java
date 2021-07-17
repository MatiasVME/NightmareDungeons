package mc.nightmarephoenix.nightmaredungeons.events;

import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BossHurtEvent implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if(Global.spawnedBosses.contains(event.getEntity())) {



        }

    }

}
