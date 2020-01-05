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

import static net.aboodyy.worldjoin.Utils.*;

public class MessageAction {
    public void message(final Player p, World from, World to, String action) {
        String message = color(placeholders(from, to, action.replaceFirst("(?i)" + "\\[message] ", "")));
        final String messageWP = PlaceholderAPI.setPlaceholders(p, message);

        Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(messageWP);
        final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;
        Bukkit.getServer().getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("WorldJoin"), () ->
                p.sendMessage(messageWP.replaceFirst("<delay=" + ticks + ">", "")), ticks);
    }

    public void broadcast(final Player p,World from, World to, String action) {
        String broadcast = color(placeholders(from, to, action.replaceFirst("(?i)" + "\\[broadcast] ", "")));
        final String broadcastWP = PlaceholderAPI.setPlaceholders(p, broadcast);

        Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(broadcastWP);
        final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;
        Bukkit.getServer().getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("WorldJoin"), () ->
                Bukkit.broadcastMessage(broadcastWP.replaceFirst("<delay=" + ticks + ">", "")), ticks);
    }
}
