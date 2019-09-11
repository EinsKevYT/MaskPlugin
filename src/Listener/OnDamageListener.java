package Listener;

import Commands.GodmodeCommand;
import Helper.ConfigHelper;
import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;


public class OnDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (GodmodeCommand.gods.contains(e.getEntity().getName())){
            e.setCancelled(true);
        }

    }


}
