package Listener;

import Commands.CommandSpyCommand;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    @EventHandler
    public void Command(PlayerCommandPreprocessEvent e){
        Player t = e.getPlayer();
        for (int i = 0; i < Bukkit.getOnlinePlayers().toArray().length; i++){
            Player p = (Player) Bukkit.getOnlinePlayers().toArray()[i];
            if (CommandSpyCommand.cmdSpyList.contains(p.getName())){
                p.sendMessage(Main.PREFIX + "§e" + t.getName() + " §bhat den Command §6" + e.getMessage() + " §bausgeführt!");
            }
        }
    }

}
