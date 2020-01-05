package net.aboodyy.worldjoin.commands.specialactions;

import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import net.aboodyy.worldjoin.datahandler.PlayersData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import static net.aboodyy.worldjoin.Utils.color;
import static net.aboodyy.worldjoin.WorldJoin.plugin;

@Command("worldjoin")
@Alias("wjoin")
public class RemoveAction extends CommandBase {

    @SubCommand("removespecial")
    @Permission("worldjoin.special.remove")
    @Completion({"#players", "#worlds", "#range:1-5"})
    public void onRemove(CommandSender sender, String player, String world, int index) {
        PlayersData data = plugin.getPlayersData();

        if (index < 0) {
            sender.sendMessage(color("&cIncorrect usage. &7/WorldJoin removespecial <Player> <World> <Index>"));
            return;
        }

        OfflinePlayer p = Bukkit.getOfflinePlayer(player);

        if (p == null) {
            sender.sendMessage(color("&cCouldn't find a player with the name of &f" + player + "&c."));
            return;
        }

        if (!data.hasSpecialActions(world, p.getUniqueId())) {
            sender.sendMessage(color("&f" + player + " &7doesn't have any special actions."));
            return;
        }

        if (data.getSpecialActions(world, p.getUniqueId()).size() < index) {
            sender.sendMessage(color("&f" + player + " &7doesn't have an action with the index of &f" + index + "&7."));
            return;
        }

        data.removeSpecialAction(world, p.getUniqueId(), index - 1);
        sender.sendMessage(color("&7Action &f#" + index + " &7has been removed from &f" + player + "&7."));
    }

}
