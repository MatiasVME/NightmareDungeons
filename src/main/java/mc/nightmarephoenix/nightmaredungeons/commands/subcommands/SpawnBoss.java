package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.bosses.Boss;
import mc.nightmarephoenix.nightmaredungeons.bosses.BossManager;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnBoss extends SubCommands {
    @Override
    public String getDescription() {
        return "Spawns a boss.";
    }

    @Override
    public String getName() {
        return "spawnBoss";
    }

    @Override
    public String syntax() {
        return "/nd spawnBoss [bossName]";
    }

    @Override
    public List<String> getSubCommandsArgs(CommandSender sender, String[] args) {
        if(args.length == 2) {
            ArrayList<String> bosses = new ArrayList<>();
            Global.bosses.forEach((boss -> {
                bosses.add(boss.getName());
            }));
            return bosses;
        }
        return new ArrayList<>();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(args.length == 2) {
                boolean found = false;
                for(Boss boss: Global.bosses) {
                    if(args[1].equalsIgnoreCase(boss.getName())) {
                        BossManager.spawnBoss(boss, p.getLocation());
                        found = true;
                        sender.sendMessage(Utils.Color("&aBoss spawned!"));
                        break;
                    }
                }
                if(!found) sender.sendMessage(Utils.Color(Global.messages.getString("entity-not-found")));
            } else {
                sender.sendMessage(Utils.Color(Global.messages.getString("command-usage") + syntax()));
            }
        } else {
            Utils.sendConfigMessage("not-a-player", sender);
        }
    }
}
