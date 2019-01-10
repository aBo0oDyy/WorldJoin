package me.aBooDyy.WorldJoin;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;

public class WorldJoinEvent implements Listener {

    private WorldJoin plugin;
    public WorldJoinEvent(WorldJoin pl) {
        plugin = pl;
    }
    @EventHandler
    public void changeWorld(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        World world = e.getTo().getWorld();

        if (plugin.getConfig().get("worlds." + world.getName()) != null) {
            List<String> actions = plugin.getConfig().getStringList("worlds." + world.getName() + ".actions");
            for (String cmd : actions) {
                if (cmd.startsWith("[console] ")) {
                    String consoleCMD = cmd.replace("[console] ", "")
                            .replaceAll("\\{world_from}", e.getFrom().getWorld().getName())
                            .replaceAll("\\{world_to}", world.getName());
                    String withPlaceholdersSet = PlaceholderAPI.setPlaceholders(e.getPlayer(), consoleCMD);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), withPlaceholdersSet);
                } else if (cmd.startsWith("[player] ")) {
                    String playerCMD = cmd.replace("[player] ", "")
                            .replaceAll("\\{world_from}", e.getFrom().getWorld().getName())
                            .replaceAll("\\{world_to}", world.getName());
                    String withPlaceholdersSet = PlaceholderAPI.setPlaceholders(e.getPlayer(), playerCMD);
                    p.performCommand(withPlaceholdersSet);
                } else if (cmd.startsWith("[message] ")) {
                    String message = plugin.color( cmd.replace("[message] ", ""))
                            .replaceAll("\\{world_from}", e.getFrom().getWorld().getName())
                            .replaceAll("\\{world_to}", world.getName());
                    String withPlaceholdersSet = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    p.sendMessage(withPlaceholdersSet);
                }
            }
        }
    }
}
