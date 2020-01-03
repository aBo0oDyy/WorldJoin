package me.aBooDyy.WorldJoin.datahandler;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static me.aBooDyy.WorldJoin.WorldJoin.plugin;

public class WorldsData {

    private File file;

    private FileConfiguration conf;

    public WorldsData() {
        file = new File(plugin.getDataFolder() + File.separator + "data", "worlds_data.yml");
        conf = YamlConfiguration.loadConfiguration(file);

        save();
    }

    public void addPlayer(String world, UUID uuid) {
        conf.set(world + ".joined_before." + uuid.toString(), true);
        save();
    }

    public boolean isFirstJoin(String world, UUID uuid) {
        return !conf.getBoolean(world + ".joined_before." + uuid.toString());
    }

    private void save() {
        try {
            conf.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("Couldn't save world_data.yml file.");
        }

        conf = YamlConfiguration.loadConfiguration(file);
    }
}
