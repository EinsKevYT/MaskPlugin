package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    public static int dur;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        dur = Main.reload_duration;
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("server.reload") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){

                Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {

                        if (dur > 0){
                            Bukkit.broadcastMessage(Main.PREFIX + "§cDer Server wird in §6§l" + dur + " Sekunden §creloaded!");
                            dur--;
                        } else {
                            Bukkit.getScheduler().cancelAllTasks();
                            Bukkit.reload();
                            Bukkit.broadcastMessage(Main.PREFIX + "§bDer Server wurde erfolgreich reloaded!");
                        }
                    }
                }, 0, 20);

            } else p.sendMessage(Main.NO_PERM);
        }


        return false;
    }
}
