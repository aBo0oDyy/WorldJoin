package me.aBooDyy.WorldJoin.events;

import me.aBooDyy.WorldJoin.WorldJoin;
import org.bukkit.World;
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

public class WorldChangeEvent implements Listener {

    private WorldJoin pl;
    public WorldChangeEvent(WorldJoin plugin) {
        pl = plugin;
    }

    @EventHandler
    public void changeWorld(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        World from = e.getFrom().getWorld();
        World to = e.getTo().getWorld();

        if (pl.getConfig().get("worlds." + to.getName()) != null) {
            if (e.getFrom().getWorld() != e.getTo().getWorld()) {
                List<String> actions = pl.getConfig().getStringList("worlds." + to.getName() + ".actions");
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
