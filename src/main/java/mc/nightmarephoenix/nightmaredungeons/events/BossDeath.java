package mc.nightmarephoenix.nightmaredungeons.events;

import mc.nightmarephoenix.nightmaredungeons.dungeons.Dungeon;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonStatus;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;

public class BossDeath implements Listener {

    @EventHandler
    public void mobDeath(EntityDeathEvent event) {
        for(Dungeon dungeon : Global.dungeons) {
            if(dungeon.getStatus().equals(DungeonStatus.READY) || dungeon.getStatus().equals(DungeonStatus.IN_PROGRESS)) {
                if(dungeon.getBoss().getEntity().equals(event.getEntity())) {
                    ArrayList<ItemStack> drops = dungeon.getBoss().getDrops();
                    if(!drops.isEmpty()) {
                        event.getDrops().clear();

                        for(ItemStack is : drops) {
                            event.getEntity().getLocation().getWorld().dropItem(
                                    event.getEntity().getLocation(),
                                    is
                            );
                        }
                    }

                    for(String cmd : dungeon.getBoss().getDeathCommands()) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);
                    }

                    for(String line : dungeon.getBoss().getDeathMessage()) {
                        Bukkit.getServer().broadcastMessage(Utils.Color(line.replaceAll("%killer%", event.getEntity().getKiller().getName())));
                    }

                    break;
                }
            }
        }
    }
}
