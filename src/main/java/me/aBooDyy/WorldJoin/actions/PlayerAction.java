package me.aBooDyy.WorldJoin.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.aBooDyy.WorldJoin.Utils.placeholders;

public class PlayerAction {
    public static void playerAction(final Player p, World from, World to, String action) {
        String playerCMD = placeholders(from, to, action.replaceFirst("(?i)" + "\\[player] ", ""));
        final String playerCMDWP = PlaceholderAPI.setPlaceholders(p, playerCMD);

        Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(playerCMDWP);
        final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;

        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("WorldJoin"), new Runnable() {
            public void run() {
                p.performCommand(playerCMDWP.replaceFirst("<delay=" + ticks + ">", ""));
            }
        }, ticks);
    }
}
