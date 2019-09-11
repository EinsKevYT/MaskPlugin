package TabCompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MSGCompleter implements TabCompleter {
    private List<String> lst = new ArrayList();

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 1){

        } else {
            lst.clear();
            for (Player p : Bukkit.getOnlinePlayers()){
                lst.add(p.getName());
            }
        }


        return lst;
    }
}
