package Commands;

import Helper.ConfigHelper;
import Helper.MoneyHelper;
import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("server.money.pay") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                if (strings.length >= 2){
                    String targetPlayerName = strings[0];
                    Player t = Bukkit.getPlayer(targetPlayerName);
                    if (PlayerHelper.existPlayer(t)) {
                        if (MoneyHelper.hasEnoughMoney(Double.valueOf(strings[1]), p)) {
                            MoneyHelper.removeMoney(Double.valueOf(strings[1]), p);
                            MoneyHelper.addMoney(Double.valueOf(strings[1]), t);
                            p.sendMessage(Main.PREFIX + "§eDu hast §6" + Double.valueOf(strings[1]) + " " + Main.MONEY_ICON + " §ezu §a" + t.getName() + " §egesendet");
                         t.sendMessage(Main.PREFIX + "§eDu hast §6" + Double.valueOf(strings[1]) + " " + Main.MONEY_ICON + " §evom §a" + p.getName() + " §ebekommen");
                     } else p.sendMessage(Main.ERROR_PREFIX + "§cDu hast nicht genug Geld!");
                 } else p.sendMessage(Main.ERROR_PREFIX + "§cDen Spieler gibt es nicht auf dem Server!");
             } else p.sendMessage(Main.ERROR_PREFIX + "§cBitte benutze: §6/pay <SPIELER> <ANZAHL>");
            } else p.sendMessage(Main.NO_PERM);
        }


        return false;
    }
}
