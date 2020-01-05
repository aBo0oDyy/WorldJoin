package net.aboodyy.worldjoin;

import me.clip.placeholderapi.PlaceholderAPI;
import me.mattstudios.mf.base.CommandManager;
import net.aboodyy.worldjoin.actions.ActionsManager;
import net.aboodyy.worldjoin.commands.ReloadCommand;
import net.aboodyy.worldjoin.commands.WorldJoinCommand;
import net.aboodyy.worldjoin.commands.specialactions.ActionsList;
import net.aboodyy.worldjoin.commands.specialactions.AddAction;
import net.aboodyy.worldjoin.commands.specialactions.ClearActions;
import net.aboodyy.worldjoin.commands.specialactions.RemoveAction;
import net.aboodyy.worldjoin.datahandler.PlayersData;
import net.aboodyy.worldjoin.datahandler.WorldsData;
import net.aboodyy.worldjoin.updatechecker.SpigotUpdateChecker;
import net.aboodyy.worldjoin.listeners.WorldChangeListener;
import net.aboodyy.worldjoin.listeners.WorldJoinListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.aboodyy.worldjoin.Utils.color;


public class WorldJoin extends JavaPlugin {

    public static WorldJoin plugin;

    private WorldsData worldsData;

    private PlayersData playersData;

    private ActionsManager actionsManager;

    private CommandManager commandManager;

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

            List<String> types = Arrays.asList("console", "player", "message", "broadcast");
            commandManager = new CommandManager(this);

            commandManager.getCompletionHandler().register("#worlds", input ->
                    Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.toList())
            );

            commandManager.getCompletionHandler().register("#actiontypes", input -> types);

            commandManager.getMessageHandler().register("cmd.no.permission", sender -> {
                if (sender instanceof Player)
                    sender.sendMessage(PlaceholderAPI.setPlaceholders((Player) sender, getConfig().getString("messages.no_permission")));
                else
                    sender.sendMessage(color(getConfig().getString("messages.no_permission")));
            });

            commandManager.getMessageHandler().register("cmd.no.exists", sender ->
                    sender.sendMessage(color("&cIncorrect usage. &7/WorldJoin help")));

            commandManager.getMessageHandler().register("cmd.wrong.usage", sender ->
                    sender.sendMessage(color("&cIncorrect usage. &7/WorldJoin help")));

            commandManager.register(new WorldJoinCommand());
            commandManager.register(new ReloadCommand());

            commandManager.register(new ActionsList());
            commandManager.register(new AddAction());
            commandManager.register(new RemoveAction());
            commandManager.register(new ClearActions());

            commandManager.hideTabComplete(true);

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

    public CommandManager getCommandManager() {
        return commandManager;
    }
}