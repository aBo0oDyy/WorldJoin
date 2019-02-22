package me.aBooDyy.WorldJoin;

import me.aBooDyy.WorldJoin.actions.*;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorldJoinEvent implements Listener {

    private WorldJoin pl;
    private PlayerAction pa;
    private ConsoleAction ca;
    private MessageAction ma;
    public WorldJoinEvent(WorldJoin plugin) {
        pl = plugin;
        pa = new PlayerAction(plugin);
        ca = new ConsoleAction(plugin);
        ma = new MessageAction(plugin);
    }

    @EventHandler
    public void changeWorld(PlayerTeleportEvent e) {
        World world = e.getTo().getWorld();

        if (pl.getConfig().get("worlds." + world.getName()) != null) {
            if (e.getFrom().getWorld() != e.getTo().getWorld()) {
                List<String> actions = pl.getConfig().getStringList("worlds." + world.getName() + ".actions");
                for (String cmd : actions) {
                    Matcher action = Pattern.compile("[([^)]+)]") .matcher(cmd);
                    if (cmd.toLowerCase().startsWith("[console] ")) {
                        ca.consoleAction(e, cmd);
                    } else if (cmd.toLowerCase().startsWith("[player] ")) {
                        pa.playerAction(e, cmd);
                    } else if (cmd.toLowerCase().startsWith("[message] ")) {
                        ma.messageAction(e, cmd);
                    }
                }
            }
        }
    }
}
