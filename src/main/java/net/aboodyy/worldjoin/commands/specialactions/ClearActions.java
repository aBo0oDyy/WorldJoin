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
