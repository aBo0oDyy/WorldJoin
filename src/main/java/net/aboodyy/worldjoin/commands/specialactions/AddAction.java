/*
    WorldJoin - A plugin to run commands and send messages on world join.
    Copyright (C) 2019-2020 aBooDyy

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.aboodyy.worldjoin.commands.specialactions;

import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import net.aboodyy.worldjoin.actions.ActionsManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
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

        if (p.isOnline() && p.getPlayer().getWorld().getName().equals(world)) {
            ActionsManager actionsManager = plugin.getActionsManager();
            World w = p.getPlayer().getWorld();
            String actionString = String.join(" ", action);

            switch (actionType.toLowerCase()) {
                case "console":
                    actionsManager.getConsoleAction().console(p.getPlayer(), w, w, "[console] " + actionString);
                    break;
                case "player":
                    actionsManager.getPlayerAction().player(p.getPlayer(), w, w, "[player] " + actionString);
                    break;
                case "message":
                    actionsManager.getMessageAction().message(p.getPlayer(), w, w, "[message] " + actionString);
                    break;
                case "broadcast":
                    actionsManager.getMessageAction().broadcast(p.getPlayer(), w, w, "[broadcast] " + actionString);
                    break;
            }

            sender.sendMessage(color("&aSpecial action has been successfully executed for &f" + player + "&a," +
                    "found on the requested world."));
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
