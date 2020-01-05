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
public class AddAction extends CommandBase {

    @SubCommand("addspecial")
    @Permission("worldjoin.special.add")
    @Completion({"#players", "#worlds", "#actiontypes"})
    public void onAdd(CommandSender sender, String player, String world, String actionType, String[] action) {

        OfflinePlayer p = Bukkit.getOfflinePlayer(player);

        if (p == null) {
            sender.sendMessage(color("&cCouldn't find a player with the name of &f" + player + "&c."));
            return;
        }

        plugin.getPlayersData().addSpecialAction(
                world,
                p.getUniqueId(),
                actionType,
                String.join(" ", action)
        );
        sender.sendMessage(color("&aSpecial action has been successfully added for &f" + player + "&a."));
    }

}
