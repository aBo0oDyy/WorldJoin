package me.aBooDyy.WorldJoin;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorldJoinEvent implements Listener {

    private WorldJoin plugin;
    public WorldJoinEvent(WorldJoin pl) {
        plugin = pl;
    }
    @EventHandler
    public void changeWorld(PlayerTeleportEvent e) {
        final Player p = e.getPlayer();
        World world = e.getTo().getWorld();

        if (plugin.getConfig().get("worlds." + world.getName()) != null) {
            List<String> actions = plugin.getConfig().getStringList("worlds." + world.getName() + ".actions");
            for (String cmd : actions) {
                if (cmd.startsWith("[console] ")) {
                    String consoleCMD = cmd.replace("[console] ", "")
                            .replaceAll("\\{world_from}", e.getFrom().getWorld().getName())
                            .replaceAll("\\{world_to}", world.getName());
                    final String withPlaceholdersSet = PlaceholderAPI.setPlaceholders(e.getPlayer(), consoleCMD);
                    Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(withPlaceholdersSet);
                    final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;
                    plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
                        public void run() {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), withPlaceholdersSet.replaceFirst("<delay=" + ticks + ">", ""));
                        }
                    }, ticks);
                } else if (cmd.startsWith("[player] ")) {
                    String playerCMD = cmd.replace("[player] ", "")
                            .replaceAll("\\{world_from}", e.getFrom().getWorld().getName())
                            .replaceAll("\\{world_to}", world.getName());
                    final String withPlaceholdersSet = PlaceholderAPI.setPlaceholders(e.getPlayer(), playerCMD);
                    Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(withPlaceholdersSet);
                    final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;
                    plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
                        public void run() {
                            p.performCommand(withPlaceholdersSet.replaceFirst("<delay=" + ticks + ">", ""));
                        }
                    }, ticks);
                } else if (cmd.startsWith("[message] ")) {
                    String message = plugin.color( cmd.replace("[message] ", ""))
                            .replaceAll("\\{world_from}", e.getFrom().getWorld().getName())
                            .replaceAll("\\{world_to}", world.getName());
                    final String withPlaceholdersSet = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(withPlaceholdersSet);
                    final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;
                    plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
                        public void run() {
                            p.sendMessage(withPlaceholdersSet.replaceFirst("<delay=" + ticks + ">", ""));
                        }
                    }, ticks);
                }
            }
        }
    }
}
