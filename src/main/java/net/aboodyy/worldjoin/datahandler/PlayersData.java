/*
    WorldJoin - A plugin to run commands and send messages on world join.
    Copyright (C) 2019-2020 aBooDyy

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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

    public void addSpecialAction(String world, UUID uuid, String type, String action) {
        List<String> current = conf.getStringList(world + ".special_actions." + uuid.toString());
        current.add("[" + type.toLowerCase() + "] " + action);

        conf.set(world + ".special_actions." + uuid.toString(), current);
        save();
    }

    public void removeSpecialAction(String world, UUID uuid, int index) {
        conf = YamlConfiguration.loadConfiguration(file);
        List<String> actions = conf.getStringList(world + ".special_actions." + uuid.toString());

        actions.remove(index);
        conf.set(world + ".special_actions." + uuid.toString(), actions.isEmpty() ? null : actions);
        save();
    }

    public boolean hasSpecialActions(String world, UUID uuid) {
        conf = YamlConfiguration.loadConfiguration(file);
        return conf.isList(world + ".special_actions." + uuid.toString());
    }

    public List<String> getSpecialActions(String world, UUID uuid) {
        conf = YamlConfiguration.loadConfiguration(file);

        return conf.getStringList(world + ".special_actions." + uuid.toString());
    }

    public void clearSpecialActions(String world, UUID uuid) {
        conf = YamlConfiguration.loadConfiguration(file);

        conf.set(world + ".special_actions." + uuid.toString(), null);
        save();
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
