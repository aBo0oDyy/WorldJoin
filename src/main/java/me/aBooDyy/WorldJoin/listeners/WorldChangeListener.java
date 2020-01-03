package me.aBooDyy.WorldJoin.listeners;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.aBooDyy.WorldJoin.actions.ConsoleAction.consoleAction;
import static me.aBooDyy.WorldJoin.actions.MessageAction.*;
import static me.aBooDyy.WorldJoin.actions.PlayerAction.playerAction;
import static me.aBooDyy.WorldJoin.WorldJoin.plugin;

public class WorldChangeListener implements Listener {

    @EventHandler
    public void changeWorld(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        World from = e.getFrom().getWorld();
        World to = e.getTo().getWorld();

        FileConfiguration config = plugin.getConfig();

        if (config.get("worlds." + to.getName()) != null) {
            if (e.getFrom().getWorld() != e.getTo().getWorld()) {
                List<String> actions = config.getStringList("worlds." + to.getName() + ".actions");

                if (!plugin.getWorldsData().containsPlayer(to.getName(), p.getUniqueId())) {
                    actions = config.getStringList("worlds." + to.getName() + ".first_join_actions");

                    plugin.getWorldsData().addPlayer(to.getName(), p.getUniqueId());
                }

                for (String action : actions) {
                    Matcher actionType = Pattern.compile("\\[([^)]+)]").matcher(action);
                    String type = actionType.find() ? actionType.group(1).toLowerCase() : "";
                    switch (type) {
                        case "console":
                            consoleAction(p, from, to, action);
                            break;
                        case "player":
                            playerAction(p, from, to, action);
                            break;
                        case "message":
                            messageAction(p, from, to, action);
                            break;
                        case "broadcast":
                            broadcastAction(p, from, to, action);
                            break;
                    }
                }
            }
        }
    }
}
