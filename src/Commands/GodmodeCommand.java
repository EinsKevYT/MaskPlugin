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

public class GodmodeCommand implements CommandExecutor {

    public static List<String> gods = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (strings.length == 0){
                if (p.hasPermission("server.own.god") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                    if (gods.contains(p.getName())){
                        gods.remove(p.getName());
                        p.sendMessage(Main.PREFIX + "§6Godmode wurde §cdeaktiviert!");
                    } else {
                        gods.add(p.getName());
                        p.sendMessage(Main.PREFIX + "§6Godmode wurde §aaktiviert!");
                    }
                } else p.sendMessage(Main.NO_PERM);
            } else {
                if (p.hasPermission("server.other.god") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                    if (PlayerHelper.existPlayer(Bukkit.getPlayer(strings[0]))){
                        Player t = Bukkit.getPlayer(strings[0]);
                        if (gods.contains(t.getName())){
                            gods.remove(t.getName());
                            p.sendMessage(Main.PREFIX + "§6Godmode wurde für §e" + t.getName() + " §cdeaktiviert!");
                            t.sendMessage(Main.PREFIX + "§6Godmode wurde §cdeaktiviert!");
                        } else {
                            gods.add(t.getName());
                            p.sendMessage(Main.PREFIX + "§6Godmode wurde für §e" + t.getName() + " §aaktiviert!");
                            t.sendMessage(Main.PREFIX + "§6Godmode wurde §aaktiviert!");
                        }
                    } else p.sendMessage(Main.ERROR_PREFIX + "§cDer Spieler ist anscheinend offline.");
                } else p.sendMessage(Main.NO_PERM);
            }
        }


        return false;
    }
}
