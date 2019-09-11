package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetPermsCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender p, Command command, String s, String[] strings) {
        String perms = "";
        String end = "§c, §6\n";
        if (p.hasPermission("server.get.permissions") || PlayerHelper.hasPermissonIgnoreOP((Player) p, "server.*")){
            for (int i = 0; i < Main.perms.size(); i++){
                if (i == Main.perms.size() - 1){
                    end = "";
                }
                perms = perms + Main.perms.get(i) + end;

            }
            p.sendMessage(Main.PREFIX + "§aHier sind alle registrierten Permissions: §6" + perms.replace(".", "§b.§6"));
        } else p.sendMessage(Main.NO_PERM);
        return false;
    }
}
