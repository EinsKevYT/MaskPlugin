package TabCompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MoneyCompleter implements TabCompleter {

    List<String> list = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            list.clear();
            Player p = (Player) commandSender;
            if (strings.length == 1){
                if (p.hasPermission("server.money.set") || p.hasPermission("server.*")){
                    list.add("set");
                }
                if (p.hasPermission("server.money.remove") || p.hasPermission("server.*")){
                    list.add("remove");
                }
                if (p.hasPermission("server.money.add") || p.hasPermission("server.*")){
                    list.add("add");
                }
                if (p.hasPermission("server.money.reset") || p.hasPermission("server.*")){
                    list.add("reset");
                }
                if (p.hasPermission("server.money.other.money") || p.hasPermission("server.*")){
                    for (int i = 0; i < Bukkit.getOnlinePlayers().toArray().length; i++){
                        Player t = (Player) Bukkit.getOnlinePlayers().toArray()[i];
                        list.add(p.getPlayer().getName());
                    }
                }
            } else if (strings.length == 2){
                list.clear();
                for (int i = 0; i < Bukkit.getOnlinePlayers().toArray().length; i++){
                    Player t = (Player) Bukkit.getOnlinePlayers().toArray()[i];
                    list.add(t.getPlayer().getName());
                }
            }
        }

        return list;
    }
}
