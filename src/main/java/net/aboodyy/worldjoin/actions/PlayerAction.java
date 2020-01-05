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

package net.aboodyy.worldjoin.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.aboodyy.worldjoin.Utils.placeholders;

public class PlayerAction {
    public void player(final Player p, World from, World to, String action) {
        String playerCMD = placeholders(from, to, action.replaceFirst("(?i)" + "\\[player] ", ""));
        final String playerCMDWP = PlaceholderAPI.setPlaceholders(p, playerCMD);

        Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(playerCMDWP);
        final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;

        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("WorldJoin"), () ->
                p.performCommand(playerCMDWP.replaceFirst("<delay=" + ticks + ">", "")), ticks);
    }
}
