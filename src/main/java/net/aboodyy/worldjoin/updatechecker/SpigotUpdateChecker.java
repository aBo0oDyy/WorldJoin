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

package net.aboodyy.worldjoin.updatechecker;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static net.aboodyy.worldjoin.Utils.color;
import static net.aboodyy.worldjoin.WorldJoin.plugin;

public class SpigotUpdateChecker implements Listener {

    public SpigotUpdateChecker() {
        checkForUpdates();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private void checkForUpdates() {
        if (!plugin.getConfig().getBoolean("check_updates"))
            return;

        if (isLatest()) {
            plugin.getLogger().info(" ");
            plugin.getLogger().info("You're using the latest version!");
            plugin.getLogger().info(" ");
            return;
        }

        plugin.getLogger().warning(" ");
        plugin.getLogger().warning("A New version is available at:");
        plugin.getLogger().warning("https://www.spigotmc.org/resources/63892/");
        plugin.getLogger().warning(" ");
        plugin.getLogger().warning("Download it to stay up to date!");
        plugin.getLogger().warning(" ");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!plugin.getConfig().getBoolean("check_updates") || isLatest())
            return;

        Player p = e.getPlayer();
        if (p.isOp() || p.hasPermission("worldjoin.admin")) {
            p.sendMessage(" ");
            p.sendMessage(color("&fA new version of &aWorldJoin &fis available!"));
            p.sendMessage(color("&fDownload it now from:"));
            p.sendMessage(color("&7https://www.spigotmc.org/resources/63892/"));
            p.sendMessage(" ");
        }
    }

    private String getLatestVersion() {
        URL url;
        URLConnection connection;
        BufferedReader bufferedReader;
        String latest;

        try {
            url = new URL("https://api.spigotmc.org/legacy/update.php?resource=63892");
            connection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            latest = bufferedReader.readLine();
        } catch (Exception e) {
            plugin.getLogger().warning(" ");
            plugin.getLogger().warning("Couldn't check for new updates.");
            plugin.getLogger().warning("Current version is " + plugin.getDescription().getVersion() + ".");
            plugin.getLogger().warning("Check https://www.spigotmc.org/resources/63892/ to stay up to date.");
            plugin.getLogger().warning(" ");
            return null;
        }

        return latest;
    }

    private boolean isLatest() {
        String latest = getLatestVersion();
        return plugin.getDescription().getVersion().equals(latest);
    }

}
