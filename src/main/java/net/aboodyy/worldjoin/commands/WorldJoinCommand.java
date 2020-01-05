package net.aboodyy.worldjoin.commands;

import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.aboodyy.worldjoin.Utils.*;
import static net.aboodyy.worldjoin.WorldJoin.plugin;

@Command("worldjoin")
@Alias("wjoin")
public class WorldJoinCommand extends CommandBase {

    @Default
    @Permission("worldjoin.help")
    public void onUnknown(CommandSender sender) {
        sender.sendMessage(color("&cIncorrect usage. &7/WorldJoin help"));
    }

    @SubCommand("help")
    @Permission("worldjoin.help")
    public void onHelp(CommandSender sender) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            p.spigot().sendMessage(getHeader());
            p.sendMessage("");

            p.spigot().sendMessage(getMessage(" &a/WorldJoin Help",
                    "/worldjoin help",
                    "\n &fDisplays all the plugin's command. \n\n" +
                            " &2Usage: &f/WJoin Help \n" +
                            " &2Permission: &fWorldJoin.Help \n"
            ));

            p.spigot().sendMessage(getMessage(" &a/WorldJoin Reload",
                    "/worldjoin reload",
                    "\n &fReloads the plugin's config file. \n\n" +
                            " &2Usage: &f/WJoin Reload \n" +
                            " &2Permission: &fWorldJoin.Reload \n"
            ));
            p.sendMessage("");

            p.spigot().sendMessage(getMessage(" &a/WorldJoin SpecialList",
                    "/worldjoin speciallist",
                    "\n &fDisplays the current special actions\n for the specified player in the specified world. \n\n" +
                            " &2Usage: &f/WJoin SpecialList <Player> <World> \n" +
                            " &2Permission: &fWorldJoin.Special.List \n"
            ));

            p.spigot().sendMessage(getMessage(" &a/WorldJoin AddSpecial",
                    "/worldjoin addspecial",
                    "\n &fAdds a special action. \n\n" +
                            " &2Usage: &f/WJoin AddSpecial <Player> <World> <ActionType> <Action> \n" +
                            " &2Permission: &fWorldJoin.Special.Add \n"
            ));

            p.spigot().sendMessage(getMessage(" &a/WorldJoin RemoveSpecial",
                    "/worldjoin removespecial",
                    "\n &fRemoves a special action. \n\n" +
                            " &2Usage: &f/WJoin RemoveSpecial <Player> <World> <Index> \n" +
                            " &2Permission: &fWorldJoin.Special.Remove \n"
            ));

            p.spigot().sendMessage(getMessage(" &a/WorldJoin ClearSpecial",
                    "/worldjoin clearspecial",
                    "\n &fClears/Removes all the special actions. \n\n" +
                            " &2Usage: &f/WJoin ClearSpecial <Player> <World> \n" +
                            " &2Permission: &fWorldJoin.Special.Clear \n"
            ));
            p.sendMessage("");

            p.sendMessage(color("&7&m                                                  "));
            return;
        }

        sender.sendMessage("");
        sender.sendMessage(color("&2WorldJoin &fv" + plugin.getDescription().getVersion()));
        sender.sendMessage("");
        sender.sendMessage(color("&a/WorldJoin Help"));
        sender.sendMessage(color("&a/WorldJoin Reload"));
        sender.sendMessage(" ");
        sender.sendMessage(color("&a/WorldJoin SpecialList"));
        sender.sendMessage(color("&a/WorldJoin AddSpecial"));
        sender.sendMessage(color("&a/WorldJoin RemoveSpecial"));
        sender.sendMessage(color("&a/WorldJoin ClearSpecial"));
        sender.sendMessage("");
    }

}
