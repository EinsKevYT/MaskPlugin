package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;

public class CommandSpyCommand implements CommandExecutor {

    public static List<String> cmdSpyList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("server.cmdspy") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                if (cmdSpyList.contains(p.getName())){
                    cmdSpyList.remove(p.getName());
                    p.sendMessage(Main.PREFIX + "§6CommandSpy ist nun §cDeaktiviert!");
                } else {
                    cmdSpyList.add(p.getName());
                    p.sendMessage(Main.PREFIX + "§6CommandSpy ist nun §aAktiviert!");
                }
            } else p.sendMessage(Main.NO_PERM);
        }



        return false;
    }
}
