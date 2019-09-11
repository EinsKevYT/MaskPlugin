package Commands;

import Helper.ConfigHelper;
import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            switch (strings.length){
                case 1:
                    if (p.hasPermission("server.tp.home") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                        if (ConfigHelper.existData("Player." + p.getUniqueId() + ".Homes." + strings[0])){
                            p.teleport((Location) ConfigHelper.getConfig().get("Player." + p.getUniqueId() + ".Homes." + strings[0]));
                            p.sendMessage(Main.PREFIX + "§6Du wurdest teleportiert!");
                        } else p.sendMessage(Main.ERROR_PREFIX + "§cDieser Home-Name wurde nicht gefunden!");
                    } else p.sendMessage(Main.NO_PERM);
                    break;
                case 2:
                    switch (strings[0]){
                        case "set":
                            if (p.hasPermission("server.set.home") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                if (!ConfigHelper.existData("Player." + p.getUniqueId() + ".Homes." + strings[1])){
                                    ConfigHelper.setData("Player." + p.getUniqueId() + ".Homes." + strings[1], p.getLocation());
                                    if (ConfigHelper.existData("Player." + p.getUniqueId() + ".Homes." + strings[1])){
                                        p.sendMessage(Main.PREFIX + "§cDu hast den Home §6" + strings[1] + " §cerfolgreich gesetzt!");
                                    } else p.sendMessage(Main.ERROR_PREFIX + "§4Der Home wurde nicht gesetzt!");
                                } else p.sendMessage(Main.ERROR_PREFIX + "§cEs gibt bereits einen Home mit dem Namen!");
                            } else p.sendMessage(Main.NO_PERM);
                            break;
                        case "del": case "delete": case "rem": case "remove":
                            if (p.hasPermission("server.remove.home") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                if (ConfigHelper.existData("Player." + p.getUniqueId() + ".Homes." + strings[1])){
                                    ConfigHelper.setData("Player." + p.getUniqueId() + ".Homes." + strings[1], null);
                                    if (!ConfigHelper.existData("Player." + p.getUniqueId() + ".Homes." + strings[1])){
                                        p.sendMessage(Main.PREFIX + "§cDer Home §6" + strings[1] + " §cwurde erfolgreich entfernt!");
                                    } else p.sendMessage(Main.ERROR_PREFIX + "§cDer Home wurde nicht entfernt!");
                                } else p.sendMessage(Main.ERROR_PREFIX + "§cDieser Home-Name wurde nicht gefunden!");
                            } else p.sendMessage(Main.NO_PERM);
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
        }



        return false;
    }
}
