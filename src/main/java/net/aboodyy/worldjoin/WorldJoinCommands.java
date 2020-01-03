package net.aboodyy.worldjoin;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.aboodyy.worldjoin.Utils.color;

public class WorldJoinCommands implements CommandExecutor {

    private WorldJoin pl;
    WorldJoinCommands(WorldJoin plugin) {
        pl = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        if (sender.hasPermission("worldjoin.admin")) {
            if (command.getName().equalsIgnoreCase("worldjoin")) {
                if (args.length == 0 || args.length == 1 && args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(color("&aWorld&7Join &fv1.0.0"));
                    sender.sendMessage(color("&a/WorldJoin help &f- &7About the plugin"));
                    sender.sendMessage(color("&a/WorldJoin reload &f- &7To reload the config file"));
                    sender.sendMessage(" ");
                    sender.sendMessage(color("&aSupport: &7https://aBooDyy.net/discord"));
                    sender.sendMessage(color("&aby: &7aBooDyy"));
                } else if (args[0].equalsIgnoreCase("reload")) {
                    pl.reloadConfig();
                    sender.sendMessage(color("&aWorld&7Join &fhas successfully reloaded."));
                } else {
                    sender.sendMessage(color("&cUnknown command. &7/WorldJoin help"));
                }
            }
        } else {
            Player p = (Player) sender;
            String noPermMSG = color(pl.getConfig().getString("messages.no_permission"));
            String withPlaceholdersSet = PlaceholderAPI.setPlaceholders(p, noPermMSG);
            sender.sendMessage(withPlaceholdersSet);
        }
        return true;
    }
}
