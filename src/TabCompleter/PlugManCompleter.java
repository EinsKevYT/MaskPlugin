package TabCompleter;

import Commands.PlugManCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.SimplePluginManager;

import java.util.ArrayList;
import java.util.List;

public class PlugManCompleter implements TabCompleter {
    List<String> tab = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 1) {
            tab.clear();
            tab.add("get");
            tab.add("enable");
            tab.add("disable");
            tab.add("load");
            tab.add("reloadconfig");
            tab.add("reload");
        }


        if (strings.length == 2){
            tab.clear();
            for (int i2 = 0; PlugManCommand.plugman.getPlugins().length > i2; i2++){
                tab.add(PlugManCommand.plugman.getPlugins()[i2].getDescription().getName());
            }

        }


        return tab;
    }
}
