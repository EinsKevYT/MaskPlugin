package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (strings.length != 1){
                if (p.hasPermission("server.change.own.fly") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                    p.setAllowFlight(!p.getAllowFlight());
                    if (p.getAllowFlight()){
                        p.sendMessage(Main.PREFIX + "§eFly ist nun §aAktiviert!");
                    } else {
                        p.sendMessage(Main.PREFIX + "§eFly ist nun §cDeaktiviert!");
                    }
                } else {
                    p.sendMessage(Main.PREFIX + "§cDu hast dazu keine Rechte!");
                }
            } else {
                if (p.hasPermission("server.change.other.fly") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                    Player t = Bukkit.getPlayer(strings[0]);
                    if (PlayerHelper.existPlayer(t)){
                        t.setAllowFlight(!t.getAllowFlight());

                        if (t.getAllowFlight()){
                            p.sendMessage(Main.PREFIX + "§eFly ist für §6" + t.getName() + " §enun §aAktiviert!");
                            t.sendMessage(Main.PREFIX + "§eFly ist nun §aAktiviert!");
                        } else {
                            p.sendMessage(Main.PREFIX + "§eFly ist nun §cDeaktiviert!");
                            t.sendMessage(Main.PREFIX + "§eFly ist für §6" + t.getName() + " §enun §cDeaktiviert!");
                        }
                    }
                } else {
                    p.sendMessage(Main.ERROR_PREFIX + "§cDu hast dazu keine Rechte!");
                }
            }
        }


        return false;
    }
}
