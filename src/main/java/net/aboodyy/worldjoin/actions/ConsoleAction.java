package net.aboodyy.worldjoin.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.aboodyy.worldjoin.Utils.placeholders;

public class ConsoleAction {
    public void console(final Player p, World from, World to, String action) {
        String consoleCMD = placeholders(from, to, action.replaceFirst("(?i)" + "\\[console] ", ""));
        final String consoleCMDWP = PlaceholderAPI.setPlaceholders(p, consoleCMD);

        Matcher delay = Pattern.compile("<delay=([^)]+)>").matcher(consoleCMDWP);
        final long ticks = delay.find() ? Long.parseLong(delay.group(1)) : 0;

        Bukkit.getServer().getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("WorldJoin"), () ->
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), consoleCMDWP.replaceFirst("<delay=" + ticks + ">", "")), ticks);
    }
}
