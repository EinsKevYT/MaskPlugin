package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class AddAdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender || PlayerHelper.hasPermissonIgnoreOP((Player) commandSender, "server.*")){
            Player t = Bukkit.getPlayer(strings[0]);
            if (PlayerHelper.existPlayer(t)){
                PermissionAttachment perm = t.addAttachment(Main.getPlugin());
                perm.setPermission("server.*", true);
                commandSender.sendMessage(Main.PREFIX + "§aAdmin wurde erfolgreich hinzugefügt!");
            } else commandSender.sendMessage(Main.OFFLINE_PLAYER);
        } else commandSender.sendMessage(Main.NO_PERM);


        return false;
    }
}
