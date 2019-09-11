package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("server.world.setspawn") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                Location loc = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY() + 0.5, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                Main.getPlugin().getConfig().set("Server.Spawn." + p.getLocation().getWorld().getName(), loc);

                Main.getPlugin().saveConfig();

                if (Main.getPlugin().getConfig().contains("Server.Spawn." + p.getLocation().getWorld().getName())){
                    p.sendMessage(Main.PREFIX + "§aDer Spawn wurde erfolgreich gesetzt!");
                } else {
                    p.sendMessage(Main.PREFIX + "§4Es ist etwas schiefgelaufen. :´( \n§cBitte wende dich an den Dev ~> DieMaskeLP.");
                }
            } else {
                p.sendMessage(Main.NO_PERM);
            }
        }


        return false;
    }
}
