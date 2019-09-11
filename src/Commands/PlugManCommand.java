package Commands;

import Helper.PlayerHelper;
import Main.Main;
import com.sun.javaws.exceptions.ErrorCodeResponseException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

import java.io.File;

public class PlugManCommand implements CommandExecutor {

    public static SimplePluginManager plugman = (SimplePluginManager) Bukkit.getPluginManager();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
         Player p = (Player) commandSender;
            if (p.hasPermission("server.plugman") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                switch (strings.length){
                    case 2:
                        switch (strings[0]){
                            case "get":
                                if (plugman.getPlugin(strings[1]) != null){
                                    Plugin plugin = plugman.getPlugin(strings[1]);
                                    String authors = "";
                                    for (int i = 0; i < plugin.getDescription().getAuthors().size(); i++){
                                        String Trennzeichen = ", ";
                                        if (i == plugin.getDescription().getAuthors().size() - 1) {
                                            Trennzeichen = "";
                                        }
                                        authors = authors + plugin.getDescription().getAuthors().get(i).concat(Trennzeichen);
                                    }

                                    p.sendMessage(Main.PREFIX + "§aName: §6" + plugman.getPlugin(strings[1]).getName() + "\n" + Main.PREFIX + "§aReiner Name: §6" + plugin.getDescription().getRawName() + "\n" + Main.PREFIX + "§aVersion:§6 " + plugin.getDescription().getVersion() + "\n" + Main.PREFIX + "§aPrefix: §6" + plugin.getDescription().getPrefix() + "\n" + Main.PREFIX + "§aAuthors: §6" + authors + "\n" + Main.PREFIX + "§aWebsite: §6" + plugin.getDescription().getWebsite());
                                } else p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin gibt es nicht.");
                                break;

                            case "enable":
                                if (plugman.getPlugin(strings[1]) != null){
                                    Plugin plugin = plugman.getPlugin(strings[1]);
                                    if (!plugman.isPluginEnabled(plugin)){
                                        plugman.enablePlugin(plugin);
                                        if (!plugman.isPluginEnabled(plugin)){
                                            p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin wurde nicht aktiviert!");
                                        } else p.sendMessage(Main.PREFIX + "§6Das Plugin wurde erfolgreich aktiviert!");
                                    } else p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin ist bereits aktiviert!");
                                } else p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin gibt es nicht.");

                                break;
                            case "disable":
                                if (plugman.getPlugin(strings[1]) != null){
                                    Plugin plugin = plugman.getPlugin(strings[1]);
                                    if (plugman.isPluginEnabled(plugin)){
                                        plugman.disablePlugin(plugin);
                                        if (plugman.isPluginEnabled(plugin)){
                                            p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin wurde nicht deaktiviert!");
                                        } else p.sendMessage(Main.PREFIX + "§6Das Plugin wurde erfolgreich deaktiviert!");
                                    } else p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin ist bereits deaktiviert!");
                                } else p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin gibt es nicht.");
                                break;
                            case "load":
                                File pluginFile = new File("plugins", strings[1] + ".jar");
                                try {
                                    if (plugman.loadPlugin(pluginFile) != null){
                                        Plugin plugin = plugman.loadPlugin(pluginFile);
                                        plugman.enablePlugin(plugin);
                                        if (plugman.isPluginEnabled(strings[1])){
                                            p.sendMessage(Main.PREFIX + "§6Das Plugin wurde erfolgreich aktiviert!");
                                        } else p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin wurde nicht aktiviert!");
                                    } else p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin wurde nicht gefunden!");
                                } catch (InvalidPluginException e) {
                                    p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin wurde nicht gefunden!");
                                }
                                break;
                            case "reloadconfig":
                                if (plugman.getPlugin(strings[1]) != null){
                                    Plugin plugin = plugman.getPlugin(strings[1]);
                                    plugin.reloadConfig();
                                    p.sendMessage(Main.PREFIX + "§6Plugin-Config wurde geladen!");
                                } else p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin gibt es nicht.");
                                break;
                            case "reload":
                                if (plugman.getPlugin(strings[1]) != null){
                                    Plugin plugin = plugman.getPlugin(strings[1]);
                                    p.sendMessage(Main.PREFIX + "§eDeaktiviere §6" + plugin.getDescription().getName() + " §ev§6" + plugin.getDescription().getVersion() + "§e...");
                                    plugman.disablePlugin(plugin);
                                    plugman.enablePlugin(plugin);
                                    p.sendMessage(Main.PREFIX + "§eAktiviere §6" + plugin.getDescription().getName() + " §ev§6" + plugin.getDescription().getVersion() + "§e...");
                                    if (plugman.isPluginEnabled(plugin)){
                                        p.sendMessage(Main.PREFIX + "§6Das Plugin wurde erfolgreich aktiviert!");
                                    } else p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin wurde nicht aktiviert!");
                                } else p.sendMessage(Main.ERROR_PREFIX + "§cDas Plugin gibt es nicht.");
                                break;
                            default:
                                p.sendMessage(Main.ERROR_PREFIX + "§cDer Command wurde nicht gefunden!");
                                break;
                        }
                        break;

                    default:
                        p.sendMessage(Main.ERROR_PREFIX + "§cDer Command wurde nicht gefunden!");
                        break;
                }

            } else p.sendMessage(Main.NO_PERM);
        }



        return false;
    }
}
