package me.aBooDyy.WorldJoin.datahandler;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static me.aBooDyy.WorldJoin.WorldJoin.plugin;

public class WorldsData {

    private File file;

    private FileConfiguration conf;

    public WorldsData() {
        file = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "worlds_data.yml");
        file.mkdirs();

        conf = YamlConfiguration.loadConfiguration(file);
    }

    public void addPlayer(String world, UUID uuid) {
        List<String> players = new ArrayList<>();

        if (conf.contains(world + ".players"))
            players = conf.getStringList(world + ".players");

        players.add(uuid.toString());
        conf.set(world + ".players", players);
        save();
    }

    public boolean containsPlayer(String world, UUID uuid) {
        if (!conf.contains(world + ".players"))
            return false;

        return conf.getStringList(world + ".players").contains(uuid.toString());
    }

    private void save() {
        try {
            conf.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("Couldn't save world_data.yml file.");
        }
    }
}
