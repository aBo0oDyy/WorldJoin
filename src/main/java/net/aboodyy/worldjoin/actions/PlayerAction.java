package net.aboodyy.worldjoin.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.aboodyy.worldjoin.Utils.placeholders;

public class PlayerAction {
    public void player(final Player p, World from, World to, String action) {
        String playerCMD = placeholders(from, to, action.replaceFirst("(?i)" + "\\[player] ", ""));
        final String playerCMDWP = PlaceholderAPI.setPlaceholders(p, playerCMD);

        Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(playerCMDWP);
        final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;

        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("WorldJoin"), () ->
                p.performCommand(playerCMDWP.replaceFirst("<delay=" + ticks + ">", "")), ticks);
    }
}
