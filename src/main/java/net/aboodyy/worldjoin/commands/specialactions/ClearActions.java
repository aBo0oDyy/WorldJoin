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
public class ClearActions extends CommandBase {

    @SubCommand("clearspecial")
    @Permission("worldjoin.special.clear")
    @Completion({"#players", "#worlds"})
    public void onClear(CommandSender sender, String player, String world) {

        OfflinePlayer p = Bukkit.getOfflinePlayer(player);

        if (p == null) {
            sender.sendMessage(color("&cCouldn't find a player with the name of &f" + player + "&c."));
            return;
        }

        if (!plugin.getPlayersData().hasSpecialActions(world, p.getUniqueId())) {
            sender.sendMessage(color("&f" + player + " &7doesn't have any special actions."));
            return;
        }

        plugin.getPlayersData().clearSpecialActions(world, p.getUniqueId());
        sender.sendMessage(color("&7Special actions has been cleared for &f" + player + " &7in &f" + world + " &7world."));
    }

}
