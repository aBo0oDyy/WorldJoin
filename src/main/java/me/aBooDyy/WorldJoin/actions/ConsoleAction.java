package me.aBooDyy.WorldJoin.actions;

import me.aBooDyy.WorldJoin.WorldJoin;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleAction {

    private WorldJoin pl;
    public ConsoleAction(WorldJoin plugin) {
        pl = plugin;
    }
    public void consoleAction(PlayerTeleportEvent e, String action) {
        World world = e.getTo().getWorld();

        String consoleCMD = action.replace("[console] ", "")
                .replaceAll("\\{world_from}", e.getFrom().getWorld().getName())
                .replaceAll("\\{world_to}", world.getName());
        final String consoleCMDWP = PlaceholderAPI.setPlaceholders(e.getPlayer(), consoleCMD);

        Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(consoleCMDWP);
        final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;

        pl.getServer().getScheduler().runTaskLater(pl, new Runnable() {
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), consoleCMDWP.replaceFirst("<delay=" + ticks + ">", ""));
            }
        }, ticks);
    }
}
