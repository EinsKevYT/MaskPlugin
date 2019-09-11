package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishCommand implements CommandExecutor {

    public static List<Player> vanisheds = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (strings.length==0){
                if (p.hasPermission("server.own.vanish") || PlayerHelper.isAdmin(p)){
                    if (vanisheds.contains(p)){
                        vanisheds.remove(p);
                        p.sendMessage(Main.PREFIX + "§6Vanish wurde §cDeaktiviert");
                        for (Player all : Bukkit.getOnlinePlayers()){
                                all.showPlayer(p);
                        }
                    } else {
                        vanisheds.add(p);
                        p.sendMessage(Main.PREFIX + "§6Vanish wurde §aAktiviert");
                        for (Player all : Bukkit.getOnlinePlayers()){
                            if (!all.hasPermission("server.vanish.see") || PlayerHelper.hasPermissonIgnoreOP(all, "server.*")){
                                all.hidePlayer(p);
                            }
                        }
                    }
                } else p.sendMessage(Main.NO_PERM);
            } else if (strings.length==1){
                if (p.hasPermission("server.other.vanish") || PlayerHelper.isAdmin(p)){
                    Player t = Bukkit.getPlayer(strings[0]);
                    if (PlayerHelper.existPlayer(t)){
                        if (vanisheds.contains(t)){
                            vanisheds.remove(t);
                            p.sendMessage(Main.PREFIX + "§6Vanish wurde für §e" + t.getName() + " §cDeaktiviert");
                            t.sendMessage(Main.PREFIX + "§6Vanish wurde §cDeaktiviert");
                            for (Player all : Bukkit.getOnlinePlayers()){
                                all.showPlayer(t);
                            }
                        } else {
                            vanisheds.add(t);
                            p.sendMessage(Main.PREFIX + "§6Vanish wurde für §e" + t.getName() + " §aAktiviert");
                            t.sendMessage(Main.PREFIX + "§6Vanish wurde §aAktiviert");
                            for (Player all : Bukkit.getOnlinePlayers()){
                                if (!all.hasPermission("server.vanish.see") || PlayerHelper.hasPermissonIgnoreOP(all, "server.*")){
                                    all.hidePlayer(t);
                                }
                            }
                        }
                    } else p.sendMessage(Main.OFFLINE_PLAYER);
                } else p.sendMessage(Main.NO_PERM);
            } else p.sendMessage(Main.ERROR_PREFIX + "§cBitte benutze §6/v [<SPIELER>]");
        }



        return false;
    }
}
