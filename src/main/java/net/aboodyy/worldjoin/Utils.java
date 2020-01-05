package net.aboodyy.worldjoin;

import net.md_5.bungee.api.chat.*;
import org.bukkit.ChatColor;
import org.bukkit.World;

import static net.aboodyy.worldjoin.WorldJoin.plugin;

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

    public static TextComponent getMessage(String display, String suggestion, String tooltip) {
        TextComponent message = new TextComponent(color(display));

        BaseComponent[] tt = new ComponentBuilder(color(tooltip)).create();
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tt));

        ClickEvent click = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, suggestion);
        message.setClickEvent(click);

        return message;
    }

    public static TextComponent getHeader() {
        TextComponent right = new TextComponent(color("&7&m                 "));
        TextComponent left = new TextComponent(color("&7&m                 "));
        TextComponent im = new TextComponent(color("&2 WorldJoin "));
        BaseComponent[] tooltip = new ComponentBuilder(
                color("\n &2Version: &f" + plugin.getDescription().getVersion() + " \n &2Author: &faBooDyy \n" +
                        " &2Contact: &faBooDyy.net/Discord \n &2Command aliases: &f/WorldJoin, /WJoin \n"))
                .create();

        im.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip));
        im.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/63892/"));

        right.addExtra(im);
        right.addExtra(left);

        return right;
    }
}
