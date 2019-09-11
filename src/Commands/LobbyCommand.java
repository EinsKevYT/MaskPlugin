package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("server.lobby") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                if (Main.getPlugin().getConfig().contains("Server.Lobby")) {
                    Location loc = (Location) (Main.getPlugin().getConfig().get("Server.Lobby"));
                    p.getPlayer().teleport(loc);
                } else {
                    p.sendMessage(Main.PREFIX + "§cDer Lobby-Spawn ist noch nicht gesetzt!");
                }
            } else p.sendMessage(Main.NO_PERM);
        }


        return false;
    }
}
