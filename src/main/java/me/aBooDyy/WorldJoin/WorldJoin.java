package me.aBooDyy.WorldJoin;

import me.aBooDyy.WorldJoin.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class WorldJoin extends JavaPlugin {

    public static WorldJoin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.getConfig().options().copyDefaults(true);
            this.saveDefaultConfig();

            this.getCommand("worldjoin").setExecutor(new WorldJoinCommands(this));

            WorldJoinEvent WorldJoinEvent = new WorldJoinEvent(this);
            WorldChangeEvent WorldChangeEvent = new WorldChangeEvent(this);
            Bukkit.getPluginManager().registerEvents(WorldJoinEvent, this);
            Bukkit.getPluginManager().registerEvents(WorldChangeEvent, this);
        } else {
            throw new RuntimeException("Could not find PlaceholderAPI!! WorldJoin can not work without it!");
        }
    }
}