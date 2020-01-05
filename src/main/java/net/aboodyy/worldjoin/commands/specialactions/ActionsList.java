package net.aboodyy.worldjoin.commands.specialactions;

import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import static net.aboodyy.worldjoin.Utils.color;
import static net.aboodyy.worldjoin.WorldJoin.plugin;

@Command("worldjoin")
@Alias("wjoin")
public class ActionsList extends CommandBase {

    @SubCommand("speciallist")
    @Permission("worldjoin.special.list")
    @Completion({"#players", "#worlds"})
    public void onList(CommandSender sender, String player, String world) {

        OfflinePlayer p = Bukkit.getOfflinePlayer(player);

        if (p == null) {
            sender.sendMessage(color("&cCouldn't find a player with the name of &f" + player + "&c."));
            return;
        }

        if (!plugin.getPlayersData().hasSpecialActions(world, p.getUniqueId())) {
            sender.sendMessage(color("&f" + player + " &7doesn't have any special actions."));
            return;
        }

        sender.sendMessage(color("&f" + player + " &ahas the following special actions on &f" + world + " &aworld:"));
        sender.sendMessage(color(
                " &8- &7" + String.join("\n &8- &7", plugin.getPlayersData().getSpecialActions(world, p.getUniqueId()))
                ));
    }

}
