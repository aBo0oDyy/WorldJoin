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
