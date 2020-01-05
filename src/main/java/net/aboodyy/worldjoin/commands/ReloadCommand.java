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

package net.aboodyy.worldjoin.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.mattstudios.mf.annotations.Alias;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.aboodyy.worldjoin.Utils.color;
import static net.aboodyy.worldjoin.WorldJoin.plugin;

@Command("worldjoin")
@Alias("wjoin")
public class ReloadCommand extends CommandBase {

    @SubCommand("reload")
    @Permission("worldjoin.reload")
    public void onReload(CommandSender sender) {
        plugin.reloadConfig();

        plugin.getCommandManager().getMessageHandler().register("cmd.no.permission", commandSender -> {
            if (sender instanceof Player)
                sender.sendMessage(PlaceholderAPI.setPlaceholders((Player) sender, plugin.getConfig().getString("messages.no_permission")));
            else
                sender.sendMessage(color(plugin.getConfig().getString("messages.no_permission")));
        });

        sender.sendMessage(color("&aWorld&7Join &fhas been reloaded successfully."));
    }

}
