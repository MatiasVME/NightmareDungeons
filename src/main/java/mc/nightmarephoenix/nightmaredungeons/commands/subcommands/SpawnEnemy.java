package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.enemies.EnemiesManager;
import mc.nightmarephoenix.nightmaredungeons.enemies.Enemy;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnEnemy extends SubCommands{
    @Override
    public String getDescription() {
        return "Spawns an enemy.";
    }

    @Override
    public String getName() {
        return "spawnEnemy";
    }

    @Override
    public String syntax() {
        return "/nd spawnEnemy [enemyName]";
    }

    @Override
    public List<String> getSubCommandsArgs(CommandSender sender, String[] args) {
        if(args.length == 2) {
            ArrayList<String> enemies = new ArrayList<>();

            Global.enemies.forEach((enemy -> {
                enemies.add(enemy.getName());
            }));
            return enemies;
        }
        return new ArrayList<>();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(args.length == 2) {
                boolean found = false;
                for(Enemy enemy: Global.enemies) {
                    if(args[1].equalsIgnoreCase(enemy.getName())) {
                        EnemiesManager.spawnEnemy(enemy, p.getLocation());
                        found = true;
                        break;
                    }
                }
                if(!found) sender.sendMessage(Utils.Color(Global.messages.getString("entity-not-found")));
            } else {
                sender.sendMessage(Utils.Color(Global.messages.getString("command-usage") + syntax()));
            }
        } else {
            Utils.Color(Global.messages.getString("not-a-player"));
        }
    }
}
