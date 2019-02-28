package me.aBooDyy.WorldJoin.events;

import me.aBooDyy.WorldJoin.WorldJoin;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

import static me.aBooDyy.WorldJoin.actions.ConsoleAction.consoleAction;
import static me.aBooDyy.WorldJoin.actions.MessageAction.messageAction;
import static me.aBooDyy.WorldJoin.actions.PlayerAction.playerAction;

public class WorldJoinEvent implements Listener {

    private WorldJoin pl;
    public WorldJoinEvent(WorldJoin plugin) {
        pl = plugin;
    }

    @EventHandler
    public void joinWorld(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        World world = p.getLocation().getWorld();
        if (pl.getConfig().get("worlds." + world.getName()) != null) {
            if (pl.getConfig().getBoolean("worlds." + world.getName() + ".run_on_join")) {
                List<String> actions = pl.getConfig().getStringList("worlds." + world.getName() + ".actions");
                for (String action : actions) {
                    if (action.toLowerCase().startsWith("[console] ")) {
                        consoleAction(p, world, world, action);
                    } else if (action.toLowerCase().startsWith("[player] ")) {
                        playerAction(p, world, world, action);
                    } else if (action.toLowerCase().startsWith("[message] ")) {
                        messageAction(p, world, world, action);
                    }
                }
            }
        }
    }
}
