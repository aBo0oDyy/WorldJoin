package net.aboodyy.worldjoin.commands;

import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;

import static net.aboodyy.worldjoin.Utils.color;
import static net.aboodyy.worldjoin.WorldJoin.plugin;

@Command("worldjoin")
@Alias("wjoin")
public class WorldJoinCommand extends CommandBase {

    @Default
    @Permission("worldjoin.help")
    public void onUnknown(CommandSender sender) {
        sender.sendMessage(color("&cIncorrect usage. &7/WorldJoin help"));
    }

    @SubCommand("help")
    @Permission("worldjoin.help")
    public void onHelp(CommandSender sender) {
        sender.sendMessage(color("&aWorld&7Join &fv" + plugin.getDescription().getVersion()));
        sender.sendMessage(color("&a/WorldJoin help &f- &7About the plugin"));
        sender.sendMessage(color("&a/WorldJoin reload &f- &7To reload the config file"));
        sender.sendMessage(" ");
        sender.sendMessage(color("&aSupport: &7https://aBooDyy.net/discord"));
        sender.sendMessage(color("&aby: &7aBooDyy"));
    }

}
