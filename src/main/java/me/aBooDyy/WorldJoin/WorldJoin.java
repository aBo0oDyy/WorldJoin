package me.aBooDyy.WorldJoin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class WorldJoin extends JavaPlugin implements Listener {

    public static WorldJoin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();

            getCommand("worldjoin").setExecutor(new WorldJoinCommands(this));

            Bukkit.getPluginManager().registerEvents(this, this);
            WorldJoinEvent WorldJoinEvent = new WorldJoinEvent(this);
            Bukkit.getPluginManager().registerEvents(WorldJoinEvent, this);
        } else {
            throw new RuntimeException("Could not find PlaceholderAPI!! WorldJoin can not work without it!");
        }
    }


    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}