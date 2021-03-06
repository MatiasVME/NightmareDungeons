package mc.nightmarephoenix.nightmaredungeons.events;

import mc.nightmarephoenix.nightmaredungeons.bosses.Boss;
import mc.nightmarephoenix.nightmaredungeons.dungeons.Dungeon;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonStatus;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BossHurtEvent implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        // // Command spawned boss // //
        for(Boss b : Global.bosses) {
            if(event.getEntity().equals(b.getEntity())) {
                LivingEntity boss = (LivingEntity)b.getEntity();
                event.setDamage(event.getDamage() * (2048 / b.getHealth()));
            }
        }

        // // Dungeon spawned boss // //
        for(Dungeon dungeon : Global.dungeons) {
            if(dungeon.getStatus().equals(DungeonStatus.READY) || dungeon.getStatus().equals(DungeonStatus.IN_PROGRESS)) {
                if(event.getEntity().equals(dungeon.getBoss().getEntity())) {
                    event.setDamage(event.getDamage() * (2048 / dungeon.getBoss().getHealth()));
                }
            }
        }
    }
}
