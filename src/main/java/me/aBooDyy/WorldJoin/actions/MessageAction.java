package me.aBooDyy.WorldJoin.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.aBooDyy.WorldJoin.Utils.*;

public class MessageAction {
    public static void messageAction(final Player p, World from, World to, String action) {
        String message = color(placeholders(from, to, action.replaceFirst("(?i)" + "\\[message] ", "")));
        final String messageWP = PlaceholderAPI.setPlaceholders(p, message);

        Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(messageWP);
        final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;
        Bukkit.getServer().getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("WorldJoin"), new Runnable() {
            public void run() {
                p.sendMessage(messageWP.replaceFirst("<delay=" + ticks + ">", ""));
            }
        }, ticks);
    }

    public static void broadcastAction(final Player p,World from, World to, String action) {
        String broadcast = color(placeholders(from, to, action.replaceFirst("(?i)" + "\\[broadcast] ", "")));
        final String broadcastWP = PlaceholderAPI.setPlaceholders(p, broadcast);

        Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(broadcastWP);
        final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;
        Bukkit.getServer().getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("WorldJoin"), new Runnable() {
            public void run() {
                Bukkit.broadcastMessage(broadcastWP.replaceFirst("<delay=" + ticks + ">", ""));
            }
        }, ticks);
    }
}
