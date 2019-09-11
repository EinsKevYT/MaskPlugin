package TabCompleter;

import Helper.ConfigHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnsTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List tab = new ArrayList();
        if (commandSender instanceof Player){
            if (strings.length == 1){
                for (int i = 0; i < Bukkit.getWorlds().size(); i++){
                    if (ConfigHelper.existData("Server.Spawn." + Bukkit.getWorlds().get(i).getName())){
                        tab.add(Bukkit.getWorlds().get(i).getName());
                    }
                }
            }
        }


        return tab;
    }
}
