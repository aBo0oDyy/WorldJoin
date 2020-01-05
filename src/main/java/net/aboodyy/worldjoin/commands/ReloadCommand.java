package net.aboodyy.worldjoin.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.mattstudios.mf.annotations.Alias;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.aboodyy.worldjoin.Utils.color;
import static net.aboodyy.worldjoin.WorldJoin.plugin;

@Command("worldjoin")
@Alias("wjoin")
public class ReloadCommand extends CommandBase {

    @SubCommand("reload")
    @Permission("worldjoin.reload")
    public void onReload(CommandSender sender) {
        plugin.reloadConfig();

        plugin.getCommandManager().getMessageHandler().register("cmd.no.permission", commandSender -> {
            if (sender instanceof Player)
                sender.sendMessage(PlaceholderAPI.setPlaceholders((Player) sender, plugin.getConfig().getString("messages.no_permission")));
            else
                sender.sendMessage(color(plugin.getConfig().getString("messages.no_permission")));
        });

        sender.sendMessage(color("&aWorld&7Join &fhas been reloaded successfully."));
    }

}
