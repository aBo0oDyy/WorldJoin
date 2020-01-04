package net.aboodyy.worldjoin;

import net.aboodyy.worldjoin.actions.ActionsManager;
import net.aboodyy.worldjoin.datahandler.PlayersData;
import net.aboodyy.worldjoin.datahandler.WorldsData;
import net.aboodyy.worldjoin.updatechecker.SpigotUpdateChecker;
import net.aboodyy.worldjoin.listeners.WorldChangeListener;
import net.aboodyy.worldjoin.listeners.WorldJoinListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class WorldJoin extends JavaPlugin {

    public static WorldJoin plugin;

    private WorldsData worldsData;

    private PlayersData playersData;

    private ActionsManager actionsManager;

    @Override
    public void onEnable() {
        plugin = this;

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.getConfig().options().copyDefaults(true);
            this.saveDefaultConfig();

            new SpigotUpdateChecker();
            new Metrics(this);

            worldsData = new WorldsData();
            playersData = new PlayersData();
            actionsManager = new ActionsManager();

            this.getCommand("worldjoin").setExecutor(new WorldJoinCommands(this));

            Bukkit.getPluginManager().registerEvents(new WorldJoinListener(), this);
            Bukkit.getPluginManager().registerEvents(new WorldChangeListener(), this);
            return;
        }

        throw new RuntimeException("Could not find PlaceholderAPI!! WorldJoin can not work without it!");
    }

    public WorldsData getWorldsData() {
        return worldsData;
    }

    public ActionsManager getActionsManager() {
        return actionsManager;
    }

    public PlayersData getPlayersData() {
        return playersData;
    }
}