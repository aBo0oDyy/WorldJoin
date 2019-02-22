package me.aBooDyy.WorldJoin.actions;

import me.aBooDyy.WorldJoin.WorldJoin;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageAction {

    private WorldJoin pl;
    public MessageAction(WorldJoin plugin) {
        pl = plugin;
    }
    public void messageAction(PlayerTeleportEvent e, String action) {
        final Player p = e.getPlayer();
        World world = e.getTo().getWorld();

        String message = pl.color( action.replace("[message] ", ""))
                .replaceAll("\\{world_from}", e.getFrom().getWorld().getName())
                .replaceAll("\\{world_to}", world.getName());
        final String messageWP = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);

        Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(messageWP);
        final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;
        pl.getServer().getScheduler().runTaskLater(pl, new Runnable() {
            public void run() {
                p.sendMessage(messageWP.replaceFirst("<delay=" + ticks + ">", ""));
            }
        }, ticks);
    }
}
