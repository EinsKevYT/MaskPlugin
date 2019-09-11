package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Food implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (strings.length != 1){
                if (p.hasPermission("server.own.givefood") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                    p.setFoodLevel(20);
                    p.sendMessage(Main.PREFIX + "§6Dein Hunger wurde gestillt!");
                } else {
                    p.sendMessage(Main.NO_PERM);
                }
            } else {
                if (p.hasPermission("server.other.givefood") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                    Player t = Bukkit.getPlayer(strings[0]);
                    t.sendMessage(Main.PREFIX + "§6Dein Hunger wurde gestillt!");
                    t.setFoodLevel(20);
                } else {
                    p.sendMessage(Main.NO_PERM);
                }
            }
        }
        return false;
    }
}
