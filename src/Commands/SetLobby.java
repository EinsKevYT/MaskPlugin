package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("server.world.setlobby") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                Location loc = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY() + 0.5, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                Main.getPlugin().getConfig().set("Server.Lobby", loc);

                Main.getPlugin().saveConfig();

                if (Main.getPlugin().getConfig().get("Server.Lobby").equals(loc)){
                    p.sendMessage(Main.PREFIX + "§aDer Lobby-Spawn wurde erfolgreich gesetzt!");
                } else {
                    p.sendMessage(Main.PREFIX + "§4Es ist etwas schiefgelaufen. :´( \n§cBitte wende dich an den Dev ~> DieMaskeLP.");
                }
            }
        }


        return false;
    }
}
