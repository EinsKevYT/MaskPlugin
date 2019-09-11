package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeTimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            switch (command.getName()){
                case "day":
                    if (p.hasPermission("server.time.day") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                        World world = Bukkit.getWorld(p.getLocation().getWorld().getName());
                        world.setTime(1000);
                    } else p.sendMessage(Main.NO_PERM);
                    break;
                case "night":
                    if (p.hasPermission("server.time.night") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                        World world = Bukkit.getWorld(p.getLocation().getWorld().getName());
                        world.setTime(20000);
                    } else p.sendMessage(Main.NO_PERM);
                    break;
                case "noon":
                    if (p.hasPermission("server.time.noon") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                        World world = Bukkit.getWorld(p.getLocation().getWorld().getName());
                        world.setTime(11000);
                    } else p.sendMessage(Main.NO_PERM);
                    break;
                case "sun":
                    if (p.hasPermission("server.weather.sun") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                        World world = Bukkit.getWorld(p.getLocation().getWorld().getName());
                        world.setStorm(false);
                        world.setThundering(false);
                    } else p.sendMessage(Main.NO_PERM);
                    break;
                case "thunder":
                    if (p.hasPermission("server.weather.thunder")||PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                        World world = Bukkit.getWorld(p.getLocation().getWorld().getName());
                        world.setThundering(true);
                        world.setStorm(false);
                    } else p.sendMessage(Main.NO_PERM);
                    break;
                case "storm":
                    if (p.hasPermission("server.weather.storm")||PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                        World world = Bukkit.getWorld(p.getLocation().getWorld().getName());
                        world.setStorm(true);
                        world.setThundering(false);
                    } else p.sendMessage(Main.NO_PERM);
                    break;
            }

        }



        return false;
    }
}
