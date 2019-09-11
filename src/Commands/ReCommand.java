package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ReCommand implements CommandExecutor {
    private static HashMap<Player, Player> list = MSGCommand.lastMSG;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("server.msg") || PlayerHelper.isAdmin(p)){
                Player t = list.get(p);
                if (strings.length>0){
                    if (PlayerHelper.existPlayer(t)){
                        if (list.containsKey(p)){
                            String msg = "";
                            String end = " ";
                            for (int i = 0; i < strings.length; i++){
                                if (i == strings.length -1){
                                    end = "";
                                }
                                msg = msg + strings[i] + end;
                            }
                            list.put(p, t);
                            list.put(t, p);
                            t.sendMessage(Main.MSG_BEGIN + "§e" + p.getName() + "> §6" + msg);
                        } else p.sendMessage(Main.ERROR_PREFIX + "§cBitte schreibe zuerst mit jemandem oder werde angeschrieben.");
                    } else p.sendMessage(Main.OFFLINE_PLAYER);
                } else p.sendMessage(Main.ERROR_PREFIX + "§cBitte benutze: §6/r <NACHRICHT>");
            } else p.sendMessage(Main.NO_PERM);
        }



        return false;
    }
}
