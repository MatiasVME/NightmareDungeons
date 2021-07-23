package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.dungeons.Dungeon;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonStatus;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonsManager;
import mc.nightmarephoenix.nightmaredungeons.enemies.Enemy;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StopDungeon extends SubCommands {
    @Override
    public String getDescription() {
        return "Forces the stop of a dungeon.";
    }

    @Override
    public String getName() {
        return "stopDungeon";
    }

    @Override
    public String syntax() {
        return "/nd stopDungeon [dungeon]";
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
        if(args.length == 2) {
            Dungeon dungeon = DungeonsManager.getDungeonByName(args[1]);
            if(dungeon != null) {
                if(dungeon.getStatus().equals(DungeonStatus.IN_PROGRESS) || dungeon.getStatus().equals(DungeonStatus.READY)) {
                    for(Enemy e : dungeon.getEnemies()) {
                        e.getEntity().remove();
                    }
                    if(dungeon.getBoss().getEntity() != null)
                        dungeon.getBoss().getEntity().remove();
                }

                HashMap<String, String> placeholder = new HashMap<>();
                placeholder.put("%dungeonName%", dungeon.getName());
                Utils.sendConfigMessage("dungeon-force-stop", sender, placeholder);

                dungeon.setStatus(DungeonStatus.COOL_DOWN);

            } else {
                Utils.sendConfigMessage("invalid-dungeon", sender);
            }
        } else {
            sender.sendMessage(Utils.Color("&eUsage: &f" + syntax()));
        }

    }
}
