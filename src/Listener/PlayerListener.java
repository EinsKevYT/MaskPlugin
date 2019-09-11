package Listener;

import Commands.MSGCommand;
import Commands.VanishCommand;
import Helper.ConfigHelper;
import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlayerListener implements org.bukkit.event.Listener {


    @EventHandler
    public void onChat(PlayerChatEvent e) {
        e.setMessage(Main.replaceColor(e.getMessage()));
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (!e.getPlayer().hasPermission("server.vanish.see") || PlayerHelper.hasPermissonIgnoreOP(e.getPlayer(), "server.*")){
            for (int i = 0; i < VanishCommand.vanisheds.size(); i++){
                e.getPlayer().hidePlayer(VanishCommand.vanisheds.get(i));
            }
        }

        e.setJoinMessage(Main.PREFIX + "§bDer Spieler §a" + e.getPlayer().getDisplayName() + " §bist dem Servernetzwerk beigetreten.");
        if (Main.getPlugin().getConfig().contains("Server.Lobby")) {

            Location loc = (Location) (Main.getPlugin().getConfig().get("Server.Lobby"));
            e.getPlayer().teleport(loc);
        } else {
            e.getPlayer().sendMessage(Main.PREFIX + "§cDer Lobby-Spawn ist noch nicht gesetzt!");
        }

        switch (e.getPlayer().getName()){
            case "DieMaskeLP": case "FirebaseDatabase":
                e.getPlayer().sendMessage("§aMaskPlugin §6v" + Main.VERSION + " §a enabled!");
                break;
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        if (!Main.getPlugin().getConfig().contains("Player." + e.getPlayer().getUniqueId())){
            Configuration config = Main.getPlugin().getConfig();
            String path = "Player." + e.getPlayer().getUniqueId();
            config.set(path + ".Name", e.getPlayer().getName());
            config.set(path + ".lastAddress", e.getPlayer().getAddress());
            config.set(path + ".Money", 200.00);
            Main.getPlugin().saveConfig();
        }

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        e.setQuitMessage(Main.PREFIX + "§cDer Spieler §a" + e.getPlayer().getDisplayName() + " §chat das Servernetzwerk verlassen.");
        MSGCommand.lastMSG.remove(e.getPlayer());
    }

    @EventHandler
    public void onGamemodeChanged(PlayerGameModeChangeEvent e){
        e.getPlayer().sendMessage(Main.PREFIX + "§6Dein Spielmodus wurde auf §c" + e.getNewGameMode() + " §6gesetzt.");
    }

    @EventHandler
    public void onWorldJoined(PlayerChangedWorldEvent e){
        if (Main.getPlugin().getConfig().contains("Server.Spawn." + e.getPlayer().getLocation().getWorld().getName())){
            Location loc = (Location) (Main.getPlugin().getConfig().get( "Server.Spawn." + e.getPlayer().getLocation().getWorld().getName()));
            e.getPlayer().teleport(loc);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        e.setDeathMessage(Main.PREFIX + "§e" + e.getEntity().getName() + " §cwurde von §e" + e.getEntity().getKiller().getName() + " §cgetötet!");
        if (ConfigHelper.existData("Server.Spawn." + e.getEntity().getLocation().getWorld().getName())){
            Location loc = (Location) ConfigHelper.getConfig().get("Server.Spawn." + e.getEntity().getLocation().getWorld().getName());
            if (PlayerHelper.existPlayer(e.getEntity().getPlayer())){
                e.getEntity().getPlayer().teleport(loc);
            }
        }
    }





}
