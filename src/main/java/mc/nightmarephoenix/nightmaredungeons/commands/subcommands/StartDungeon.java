package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.bosses.Boss;
import mc.nightmarephoenix.nightmaredungeons.dungeons.Dungeon;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonStatus;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonsManager;
import mc.nightmarephoenix.nightmaredungeons.enemies.EnemiesManager;
import mc.nightmarephoenix.nightmaredungeons.enemies.Enemy;
import mc.nightmarephoenix.nightmaredungeons.storage.DungeonsStorage;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StartDungeon extends SubCommands {
    @Override
    public String getDescription() {
        return "Forces the start of a dungeon.";
    }

    @Override
    public String getName() {
        return "startDungeon";
    }

    @Override
    public String syntax() {
        return "/nd startDungeon [dungeon]";
    }

    @Override
    public List<String> getSubCommandsArgs(CommandSender sender, String[] args) {
        ArrayList<String> dungeonNames = new ArrayList<>();
        for(Dungeon dungeon : Global.dungeons) {
            dungeonNames.add(dungeon.getName());
        }
        return dungeonNames;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Dungeon dungeon = DungeonsManager.getDungeonByName(args[1]);
        if(dungeon != null) {
            if(!dungeon.getStatus().equals(DungeonStatus.COOL_DOWN)) {
                for(Enemy e : Global.spawnedEnemies) e.getEntity().remove();
                for(Boss e : Global.spawnedBosses) e.getEntity().remove();
            }
            EnemiesManager.spawnEnemies(dungeon.getEnemies());

            HashMap<String, String> placeholder = new HashMap<>();
            placeholder.put("%dungeonName%", dungeon.getName());
            Utils.sendConfigMessage("dungeon-force-start", sender, placeholder);

            dungeon.setStatus(DungeonStatus.READY);

        } else {
            Utils.sendConfigMessage("invalid-dungeon", sender);
        }



    }
}
