package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.dungeons.Dungeon;
import mc.nightmarephoenix.nightmaredungeons.dungeons.DungeonStatus;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;

public class List extends SubCommands {
    @Override
    public String getDescription() {
        return "Lists all dungeons.";
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String syntax() {
        return "/nd list";
    }

    @Override
    public java.util.List<String> getSubCommandsArgs(CommandSender sender, String[] args) {
        ArrayList<String> dungeonNames = new ArrayList<>();
        for(Dungeon dungeon : Global.dungeons) {
            dungeonNames.add(dungeon.getName());
        }
        return dungeonNames;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        String dungeons = "";
        HashMap<String, String> placeholder = new HashMap<>();
        int i = 0;

        for(Dungeon dungeon : Global.dungeons) {
            String status = "";
            if(dungeon.getStatus().equals(DungeonStatus.COOL_DOWN))
                status = "&c&lIN COOLDOWN";
            else if(dungeon.getStatus().equals(DungeonStatus.READY))
                status = "&a&lREADY";
            else if(dungeon.getStatus().equals(DungeonStatus.IN_PROGRESS))
                status = "&6&lIN PROGRESS";
            i++;
            dungeons += "   " + dungeon.getName() + " &7- " + status + ((i < Global.dungeons.size()) ? "\n&r" : "");
        }
        placeholder.put("%dungeons%", dungeons);
        Utils.sendConfigMultilineMessage("list-all-dungeons", sender, placeholder);
    }
}
