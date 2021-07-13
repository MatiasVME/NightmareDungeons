package mc.nightmarephoenix.nightmaredungeons.commands;

import mc.nightmarephoenix.nightmaredungeons.storage.EnemiesStorage;
import mc.nightmarephoenix.nightmaredungeons.util.Global;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class NDCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // --- Regular users commands --- //


        for (FileConfiguration f: EnemiesStorage.getConfigs()) {

            f.set("name", "oso");
            EnemiesStorage.saveConfig(f);

        }

        for(FileConfiguration f1: EnemiesStorage.getConfigs()) {
            System.out.println(f1.getString("name"));
        }



        // --- Admin commands --- //
        if(sender.hasPermission("nightmaredungeons.admin")) {

            if(args.length > 0) {
                if(args[0].equals("reload")) {

                    Global.plugin.reloadConfig();
                    EnemiesStorage.reloadConfig();


                    System.out.println("reload");

                }

            }

        }

        return true;
    }
}
