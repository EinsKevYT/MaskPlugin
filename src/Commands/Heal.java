package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (strings.length != 1){
                if (p.hasPermission("server.own.heal") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                    p.setHealth(p.getMaxHealth());
                    p.sendMessage(Main.PREFIX + "§6Du wurdest geheilt!");
                } else {
                    p.sendMessage(Main.NO_PERM);
                }
            } else {
                if (p.hasPermission("mask.other.heal") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                    Player t = Bukkit.getPlayer(strings[0]);
                    t.sendMessage(Main.PREFIX + "§6Du wurdest geheilt!");
                    t.setHealth(t.getMaxHealth());
                } else {
                    p.sendMessage(Main.NO_PERM);
                }
            }
        }





        return false;
    }
}
