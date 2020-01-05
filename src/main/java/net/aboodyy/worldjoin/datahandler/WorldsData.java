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
import java.util.UUID;

import static net.aboodyy.worldjoin.WorldJoin.plugin;

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
        conf = YamlConfiguration.loadConfiguration(file);
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
