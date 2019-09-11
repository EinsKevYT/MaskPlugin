package Commands;

import Helper.ConfigHelper;
import Helper.MoneyHelper;
import Helper.PlayerHelper;
import Main.Main;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.IntArrayData;
import javafx.beans.binding.DoubleExpression;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import sun.dc.pr.PRError;

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            switch (strings.length){
                case 1:
                    if (p.hasPermission("server.money.other.money") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                        Player t = Bukkit.getPlayer(strings[0]);
                        if (PlayerHelper.existPlayer(t)){
                            p.sendMessage(Main.PREFIX + "§e" + t.getName() + "§c´s Guthaben: §6" + MoneyHelper.getMoney(t) + " " + Main.MONEY_ICON);
                        } else p.sendMessage(Main.ERROR_PREFIX + "§cDer Spieler ist anscheinend Offline oder war noch nie auf dem Server");
                    } else p.sendMessage(Main.NO_PERM);
                    break;
                case 0:
                    if (p.hasPermission("server.money.own.money") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")) {
                        if (strings.length == 0) {
                            p.sendMessage(Main.PREFIX + "§cDein Guthaben: §6" + MoneyHelper.getMoney(p) + " " + Main.MONEY_ICON);
                        }
                    } else p.sendMessage(Main.NO_PERM);
                    break;
                case 3:
                    switch (strings[0]){
                        case "set":
                            if (p.hasPermission("server.money.set") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                Player t = Bukkit.getPlayer(strings[1]);
                                if (PlayerHelper.existPlayer(t)){
                                    MoneyHelper.setMoney(Double.valueOf(strings[2]), t);
                                    p.sendMessage(Main.PREFIX + "§cDu hast das Guthaben von §e" + t.getName() + " §cauf §6" + Double.valueOf(strings[2]) + " " + Main.MONEY_ICON + "§cgesetzt!");
                                    t.sendMessage(Main.PREFIX + "§cDein Guthaben wurde auf §6" + Double.valueOf(strings[2]) + " ✪" + Main.MONEY_ICON + "§cgesetzt!");
                                } else p.sendMessage(Main.ERROR_PREFIX + "§cDer Spieler ist anscheinend Offline oder war noch nie auf dem Server");
                            } else p.sendMessage(Main.NO_PERM);
                            break;

                        case "add": case "give":
                            if (p.hasPermission("server.money.add") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                Player t = Bukkit.getPlayer(strings[1]);
                                if (PlayerHelper.existPlayer(t)){
                                    MoneyHelper.addMoney(Double.valueOf(strings[2]), t);
                                    p.sendMessage(Main.PREFIX + "§cDu hast dem Guthaben von §e" + t.getName() + " §6" + Double.valueOf(strings[2]) + Main.MONEY_ICON + " §chinzugefügt!\n§cNeues Guthaben: §6" + MoneyHelper.getMoney(t) + " " + Main.MONEY_ICON);
                                    t.sendMessage(Main.PREFIX + "§cDeinem Guthaben wurden §6" + Double.valueOf(strings[2]) + Main.MONEY_ICON + " §chinzugefügt!\n§cNeues Guthaben: §6" + MoneyHelper.getMoney(t) + " " + Main.MONEY_ICON);
                                } else p.sendMessage(Main.ERROR_PREFIX + "§cDer Spieler ist anscheinend Offline oder war noch nie auf dem Server");
                            } else p.sendMessage(Main.NO_PERM);
                            break;
                        case "del": case "rem": case "remove": case "delete":
                            if (p.hasPermission("server.money.remove") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                Player t = Bukkit.getPlayer(strings[1]);
                                if (PlayerHelper.existPlayer(t)){
                                    MoneyHelper.removeMoney(Double.valueOf(strings[2]), t);
                                    p.sendMessage(Main.PREFIX + "§cDu hast dem Guthaben von §e" + t.getName() + " §6" + Double.valueOf(strings[2]) + Main.MONEY_ICON + " §cweggenommen!\n§cNeues Guthaben: §6" + MoneyHelper.getMoney(t) + " " + Main.MONEY_ICON);
                                    t.sendMessage(Main.PREFIX + "§cDeinem Guthaben wurden §6" + Double.valueOf(strings[2]) + Main.MONEY_ICON + " §centfernt!\n§cNeues Guthaben: §6" + MoneyHelper.getMoney(t) + " " + Main.MONEY_ICON);
                                } else p.sendMessage(Main.ERROR_PREFIX + "§cDer Spieler ist anscheinend Offline oder war noch nie auf dem Server");
                            } else p.sendMessage(Main.NO_PERM);
                            break;
                        case "reset":
                            if (p.hasPermission("server.money.reset") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                Player t = Bukkit.getPlayer(strings[1]);
                                if (PlayerHelper.existPlayer(t)){
                                    if (strings[2].equalsIgnoreCase("sure") || strings[2].equalsIgnoreCase("true")){
                                        MoneyHelper.removeMoney(MoneyHelper.getMoney(t), t);
                                        p.sendMessage(Main.PREFIX + "§cDu hast das Guthaben von §e" + t.getName() + " §cauf §60" + Main.MONEY_ICON + " §cgesetzt!");
                                        p.sendMessage(Main.PREFIX + "§cDein Guthaben wurde zurückgesetzt!\n§cNeues Guthaben: §6" + MoneyHelper.getMoney(t) + " " + Main.MONEY_ICON);
                                    }
                                } else p.sendMessage(Main.ERROR_PREFIX + "§cDer Spieler ist anscheinend Offline oder war noch nie auf dem Server");
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
