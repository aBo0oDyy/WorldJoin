package net.aboodyy.worldjoin.datahandler;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static net.aboodyy.worldjoin.WorldJoin.plugin;

public class PlayersData {

    private File file;

    private FileConfiguration conf;

    public PlayersData() {
        file = new File(plugin.getDataFolder() + File.separator + "data", "players_data.yml");
        conf = YamlConfiguration.loadConfiguration(file);

        save();
    }

    public void addUniqueAction(String world, UUID uuid, String type, String action) {
        List<String> current = conf.getStringList(world + ".unique_actions." + uuid.toString());
        current.add("[" + type.toLowerCase() + "] " + action);

        conf.set(world + ".unique_actions." + uuid.toString(), current);
        save();
    }

    public boolean hasUniqueActions(String world, UUID uuid) {
        conf = YamlConfiguration.loadConfiguration(file);
        return conf.isList(world + ".unique_actions." + uuid.toString());
    }

    public List<String> getUniqueActions(String world, UUID uuid) {
        conf = YamlConfiguration.loadConfiguration(file);
        List<String> actions = conf.getStringList(world + ".unique_actions." + uuid.toString());

        conf.set(world + ".unique_actions." + uuid.toString(), null);
        save();

        return actions;
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
