package Commands;

import Helper.ConfigHelper;
import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0){
                if (p.hasPermission("server.world.spawn2world") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                    if (Main.getPlugin().getConfig().contains("Server.Spawn." + p.getLocation().getWorld().getName())){
                        Location loc = (Location) (Main.getPlugin().getConfig().get( "Server.Spawn." + p.getLocation().getWorld().getName()));
                        p.teleport(loc);
                    }
                } else {
                    p.sendMessage(Main.NO_PERM);
                }
            } else {
                if (args.length == 1){
                    if (p.hasPermission("server.spawn.world") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                        if (ConfigHelper.existData("Server.Spawn." + args[0])){
                            p.teleport((Location) ConfigHelper.getConfig().get("Server.Spawn." + args[0]));
                        } else p.sendMessage(Main.ERROR_PREFIX + "§cBitte gib einen Gültigen Spawn an!");
                    } else p.sendMessage(Main.NO_PERM);
                }
            }


        }


        return false;
    }
}
