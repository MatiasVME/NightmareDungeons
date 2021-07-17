package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import mc.nightmarephoenix.nightmaredungeons.util.Utils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

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
    public String syntax() {
        return "/nd authors";
    }

    @Override
    public List<String> getSubCommandsArgs(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        sender.sendMessage(Utils.Color("&7&m----------&r &4&lNightmareDungeons &7&m----------"));
        sender.sendMessage(Utils.Color("&ePlugin made by: &fMatiasME, asessinerx10 and DadoGamer13"));
        sender.sendMessage(Utils.Color("&eGithub:&f https://github.com/MatiasVME/NightmareDungeons"));
        sender.sendMessage(Utils.Color("&7&m---------------------------------------"));

    }
}
