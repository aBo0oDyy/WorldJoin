package me.aBooDyy.WorldJoin.actions;

import me.aBooDyy.WorldJoin.WorldJoin;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerAction {

    private WorldJoin pl;
    public PlayerAction(WorldJoin plugin) {
        pl = plugin;
    }
    public void playerAction(PlayerTeleportEvent e, String action) {
        final Player p = e.getPlayer();
        World world = e.getTo().getWorld();

        String playerCMD = action.replace("[player] ", "")
                .replaceAll("\\{world_from}", e.getFrom().getWorld().getName())
                .replaceAll("\\{world_to}", world.getName());
        final String playerCMDWP = PlaceholderAPI.setPlaceholders(e.getPlayer(), playerCMD);

        Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(playerCMDWP);
        final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;

        pl.getServer().getScheduler().runTaskLater(pl, new Runnable() {
            public void run() {
                p.performCommand(playerCMDWP.replaceFirst("<delay=" + ticks + ">", ""));
            }
        }, ticks);
    }
}
