package me.aBooDyy.WorldJoin.listeners;

import me.aBooDyy.WorldJoin.actions.ActionsManager;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.aBooDyy.WorldJoin.WorldJoin.plugin;

public class WorldJoinListener implements Listener {


    @EventHandler
    public void joinWorld(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        World world = p.getLocation().getWorld();

        FileConfiguration config = plugin.getConfig();
        ActionsManager actionsManager = plugin.getActionsManager();

        if (config.get("worlds." + world.getName()) != null) {
            if (config.getBoolean("worlds." + world.getName() + ".run_on_join")) {
                List<String> actions = config.getStringList("worlds." + world.getName() + ".actions");

                if (plugin.getWorldsData().isFirstJoin(world.getName(), p.getUniqueId()))
                    actions = config.getStringList("worlds." + world.getName() + ".first_join_actions");

                for (String action : actions) {
                    Matcher actionType = Pattern.compile("\\[([^)]+)]").matcher(action);
                    String type = actionType.find() ? actionType.group(1).toLowerCase() : "";

                    switch (type) {
                        case "console":
                            actionsManager.getConsoleAction().console(p, world, world, action);
                            break;
                        case "player":
                            actionsManager.getPlayerAction().player(p, world, world, action);
                            break;
                        case "message":
                            actionsManager.getMessageAction().message(p, world, world, action);
                            break;
                        case "broadcast":
                            actionsManager.getMessageAction().broadcast(p, world, world, action);
                            break;
                    }
                }
            }
        }

        if (plugin.getWorldsData().isFirstJoin(world.getName(), p.getUniqueId()))
            plugin.getWorldsData().addPlayer(world.getName(), p.getUniqueId());
    }
}
