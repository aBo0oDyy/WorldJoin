package me.aBooDyy.WorldJoin;

import org.bukkit.ChatColor;
import org.bukkit.World;

public class Utils {
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String placeholders(World from, World to, String text) {
         text = text
                .replaceAll("(?i)" + "\\{world_from}", from.getName())
                .replaceAll("(?i)" + "\\{world_to}", to.getName());
        return text;
    }
}
