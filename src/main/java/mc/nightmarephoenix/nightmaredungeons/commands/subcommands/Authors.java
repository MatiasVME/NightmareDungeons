package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.CommandSender;

public class Authors extends SubCommands {
    @Override
    public String getDescription() {
        return "Gives the NightmareDungeons authors";
    }

    @Override
    public String getName() {
        return "authors";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        sender.sendMessage(Utils.Color("&7&m----------&r &4&lNightmareDungeons &7&m----------"));
        sender.sendMessage(Utils.Color("&ePlugin made by: &fMatiasME, asessinerx10 and DadoGamer13"));
        sender.sendMessage(Utils.Color("&eGithub:&f https://github.com/MatiasVME/NightmareDungeons"));
        sender.sendMessage(Utils.Color("&7&m---------------------------------------"));

    }
}
