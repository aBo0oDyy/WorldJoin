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

package net.aboodyy.worldjoin.listeners;

import net.aboodyy.worldjoin.actions.ActionsManager;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.aboodyy.worldjoin.WorldJoin.plugin;

public class WorldChangeListener implements Listener {

    @EventHandler
    public void changeWorld(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        World from = e.getFrom().getWorld();
        World to = e.getTo().getWorld();

        FileConfiguration config = plugin.getConfig();
        ActionsManager actionsManager = plugin.getActionsManager();
        List<String> actions = new ArrayList<>();

        if (e.getFrom().getWorld() == e.getTo().getWorld())
            return;

        if (plugin.getPlayersData().hasSpecialActions(to.getName(), p.getUniqueId())) {
            actions = plugin.getPlayersData().getSpecialActions(to.getName(), p.getUniqueId());
            plugin.getPlayersData().clearSpecialActions(to.getName(), p.getUniqueId());
        }

        if (config.get("worlds." + to.getName()) != null) {
            List<String> joinActions = config.getStringList("worlds." + to.getName() + ".actions");

            if (plugin.getWorldsData().isFirstJoin(to.getName(), p.getUniqueId()) &&
                    config.contains("worlds." + to.getName() + ".first_join_actions"))
                joinActions = config.getStringList("worlds." + to.getName() + ".first_join_actions");

            actions.addAll(joinActions);
        }

        for (String action : actions) {
            Matcher actionType = Pattern.compile("\\[([^)]+)]").matcher(action);
            String type = actionType.find() ? actionType.group(1).toLowerCase() : "";
            switch (type) {
                case "console":
                    actionsManager.getConsoleAction().console(p, from, to, action);
                    break;
                case "player":
                    actionsManager.getPlayerAction().player(p, from, to, action);
                    break;
                case "message":
                    actionsManager.getMessageAction().message(p, from, to, action);
                    break;
                case "broadcast":
                    actionsManager.getMessageAction().broadcast(p, from, to, action);
                    break;
            }
        }

        if (plugin.getWorldsData().isFirstJoin(to.getName(), p.getUniqueId()))
            plugin.getWorldsData().addPlayer(to.getName(), p.getUniqueId());
    }
}
