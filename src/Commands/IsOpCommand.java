package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class IsOpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender p, Command command, String s, String[] strings) {
        if (p instanceof ConsoleCommandSender || p.hasPermission("server.see.op") || PlayerHelper.isAdmin((Player) p)){
            if (strings.length == 1){
                Player t = Bukkit.getPlayer(strings[0]);
                if (PlayerHelper.existPlayer(t)){
                    if (t.isOp()){
                        p.sendMessage(Main.PREFIX + "§e" + t.getName() + " §6ist §aein §6Operator");
                    } else p.sendMessage(Main.PREFIX + "§e" + t.getName() + " §6ist §ckein §6Operator");
                } else p.sendMessage(Main.OFFLINE_PLAYER);
            } else p.sendMessage(Main.ERROR_PREFIX + "§cBitte benutze §6/isop <SPIELER>");
        } else p.sendMessage(Main.NO_PERM);


        return false;
    }
}
