package mc.nightmarephoenix.nightmaredungeons.commands.subcommands;

import org.bukkit.command.CommandSender;

public abstract class SubCommands {

    public abstract String getDescription();

    public abstract String getName();

    public abstract void perform(CommandSender sender, String[] args);

}
