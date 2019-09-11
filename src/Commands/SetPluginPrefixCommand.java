package Commands;

import Helper.ConfigHelper;
import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPluginPrefixCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (PlayerHelper.hasPermissonIgnoreOP((Player) p, "server.*")){
                if (strings.length > 0){
                    String prefix = "";
                    String end = " ";
                    for (int i = 0; i < strings.length; i++){
                        if (i == strings.length - 1){
                            end = "";
                        }
                        prefix = Main.replaceColor(prefix + strings[i] + end);
                    }
                    Main.PREFIX = prefix;
                    ConfigHelper.setData("-Plugin.Prefix", prefix);
                    ConfigHelper.saveConfig();
                    p.sendMessage(Main.PREFIX + "§aDer neue Prefix ist nun: §r" + prefix);
                } else p.sendMessage(Main.ERROR_PREFIX + "§cBitte gib einen neuen Prefix an.");
            } else p.sendMessage(Main.NO_PERM);
        } else {
                if (strings.length > 0){
                    String prefix = "";
                    String end = " ";
                    for (int i = 0; i < strings.length; i++){
                        if (i == strings.length -1){
                            end = "";
                        }
                        prefix = Main.replaceColor(prefix + strings[i] + end);
                    }
                    Main.PREFIX = prefix;
                    ConfigHelper.setData("-Plugin.Prefix", prefix);
                    ConfigHelper.saveConfig();
                    Bukkit.getConsoleSender().sendMessage(Main.PREFIX + "§aDer neue Prefix ist nun: §r" + prefix);
                } else Bukkit.getConsoleSender().sendMessage(Main.ERROR_PREFIX + "§cBitte gib einen neuen Prefix an.");
        }



        return false;
    }
}
