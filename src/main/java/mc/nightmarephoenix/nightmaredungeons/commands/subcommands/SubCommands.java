package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommands {

    public abstract String getDescription();

    public abstract String getName();

    public abstract String syntax();

    public abstract List<String> getSubCommandsArgs(CommandSender sender, String[] args);

    public abstract void perform(CommandSender sender, String[] args);

}
