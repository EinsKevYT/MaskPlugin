package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import java.util.HashMap;


public class MSGCommand implements CommandExecutor {
    public static HashMap<Player, Player> lastMSG = new HashMap();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("server.msg") || PlayerHelper.isAdmin(p)){
                if (strings.length>1){
                    Player t = Bukkit.getPlayer(strings[0]);
                    if (PlayerHelper.existPlayer(t)){
                        String msg = "";
                        String end = " ";
                        for (int i = 1; i < strings.length; i++){
                            if (i == strings.length -1){
                                end = "";
                            }
                            msg = msg + strings[i] + end;
                        }
                        lastMSG.put(p, t);
                        lastMSG.put(t, p);
                        t.sendMessage(Main.MSG_BEGIN + "§e" + p.getName() + "> §6" + msg);
                    } else p.sendMessage(Main.OFFLINE_PLAYER);
                } else p.sendMessage(Main.ERROR_PREFIX + "§cBitte benutze: §6/msg <SPIELER> <NACHRICHT>");
            } else p.sendMessage(Main.NO_PERM);
        }



        return false;
    }
}
