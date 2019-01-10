package me.aBooDyy.WorldJoin;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldJoinCommands implements CommandExecutor {

    private WorldJoin plugin;
    public WorldJoinCommands(WorldJoin pl) {
        plugin = pl;
    }

    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        if (sender.hasPermission("worldjoin.admin")) {
            if (command.getName().equalsIgnoreCase("worldjoin")) {
                if (args.length == 0 || args.length == 1 && args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(plugin.color("&aWorld&7Join &fv1.0.0"));
                    sender.sendMessage(plugin.color("&a/WorldJoin help &f- &7About the plugin"));
                    sender.sendMessage(plugin.color("&a/WorldJoin reload &f- &7To reload the config file"));
                    sender.sendMessage(" ");
                    sender.sendMessage(plugin.color("&aSupport: &7https://aBooDyy.net/discord"));
                    sender.sendMessage(plugin.color("&aby: &7aBooDyy"));
                } else if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    sender.sendMessage(plugin.color("&aWorld&7Join &fhas successfully reloaded."));
                } else {
                    sender.sendMessage(plugin.color("&cUnknown command. &7/WorldJoin help"));
                }
            }
        } else {
            Player p = (Player) sender;
            String noPermMSG = plugin.color(plugin.getConfig().getString("messages.no_permission"));
            String withPlaceholdersSet = PlaceholderAPI.setPlaceholders(p, noPermMSG);
            sender.sendMessage(withPlaceholdersSet);
        }
        return true;
    }
}
