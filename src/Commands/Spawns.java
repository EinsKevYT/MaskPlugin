package Commands;

import Helper.ConfigHelper;
import Helper.PlayerHelper;
import Main.Main;
import com.avaje.ebeaninternal.server.transaction.BulkEventListenerMap;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.security.krb5.Config;

public class Spawns implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender p, Command command, String s, String[] strings) {
        if (p.hasPermission("server.getspawns") || PlayerHelper.hasPermissonIgnoreOP((Player) p, "server.*")){
            String end = ", ";
            String worlds = "";
            for (int i = 0; i < Bukkit.getWorlds().size(); i++){
                if (ConfigHelper.existData("Server.Spawn." + Bukkit.getWorlds().get(i).getName())){
                    if (i == Bukkit.getWorlds().size() -1){
                        end = "";
                    }
                    worlds = worlds + Bukkit.getWorlds().get(i).getName() + end;
                }
            }
            p.sendMessage(Main.PREFIX + "§bHier sind alle Verfügbaren Spawns aufgelisted: \n§6" + worlds);
        } else p.sendMessage(Main.NO_PERM);

        return false;
    }
}
