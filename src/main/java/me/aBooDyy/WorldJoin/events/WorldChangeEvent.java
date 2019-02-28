package me.aBooDyy.WorldJoin.events;

import me.aBooDyy.WorldJoin.WorldJoin;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;

import static me.aBooDyy.WorldJoin.actions.ConsoleAction.consoleAction;
import static me.aBooDyy.WorldJoin.actions.MessageAction.messageAction;
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
                    if (action.toLowerCase().startsWith("[console] ")) {
                        consoleAction(p, from, to, action);
                    } else if (action.toLowerCase().startsWith("[player] ")) {
                        playerAction(p, from, to, action);
                    } else if (action.toLowerCase().startsWith("[message] ")) {
                        messageAction(p, from, to, action);
                    }
                }
            }
        }
    }
}
