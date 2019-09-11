package Main;

import Commands.*;
import Helper.ConfigHelper;
import Helper.InitPerms;
import Helper.TranslatorHelper;
import Listener.CommandListener;
import Listener.OnDamageListener;
import Listener.PlayerListener;
import TabCompleter.MSGCompleter;
import TabCompleter.MoneyCompleter;
import TabCompleter.PlugManCompleter;
import TabCompleter.SpawnsTabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Main extends JavaPlugin {



    public static String PREFIX = "§8[§aMask-Plugin§8] §r", MSG_BEGIN = "§6[MSG] §r", ERROR_PREFIX = "§8[§cMask-Plugin§8] §r", NO_PERM = ERROR_PREFIX + "§cDu hast dazu keine Rechte!", MONEY_ICON = " ✪", OFFLINE_PLAYER = ERROR_PREFIX + "§cDer Spieler ist Offline.";
    public static Main plugin;
    public static String VERSION;
    public static int reload_duration = 4;
    public static List<String> perms = new ArrayList<>();
    public static File transConfig;
    public static File transFile = new File("plugins/MaskPlugin","Translation.yml");

    @Override
    public void onEnable(){
        VERSION = Bukkit.getPluginManager().getPlugin(this.getName()).getDescription().getVersion();
        plugin = this;


        if (Bukkit.getPlayer("DieMaskeLP") != null || Bukkit.getPlayer("FirebaseDatabase") != null){
            if (Bukkit.getPlayer("DieMaskeLP") != null){
                Bukkit.getPlayer("DieMaskeLP").sendMessage("§aMaskPlugin §6v" + Main.VERSION + " §a enabled!");
            } else if (Bukkit.getPlayer("FirebaseDatabase") != null){
                Bukkit.getPlayer("FirebaseDatabase").sendMessage("§aMaskPlugin §6v" + Main.VERSION + " §a enabled!");
            }
        }

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("gm").setExecutor(new GamemodeChanger());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("spawn").setTabCompleter(new SpawnsTabCompleter());
        getCommand("l").setExecutor(new LobbyCommand());
        getCommand("setlobby").setExecutor(new SetLobby());
        getCommand("fly").setExecutor(new Fly());
        getCommand("feed").setExecutor(new Food());
        getCommand("heal").setExecutor(new Heal());
        getCommand("pay").setExecutor(new PayCommand());
        getCommand("money").setExecutor(new MoneyCommand());
        getCommand("home").setExecutor(new SetHomeCommand());
        getCommand("gamemode").setExecutor(new GamemodeChanger());
        getCommand("lobby").setExecutor(new LobbyCommand());
        getCommand("plugin").setExecutor(new PlugManCommand());
        getCommand("plugin").setTabCompleter(new PlugManCompleter());
        getCommand("money").setTabCompleter(new MoneyCompleter());
        getCommand("reload").setExecutor(new ReloadCommand());
        getCommand("god").setExecutor(new GodmodeCommand());
        getCommand("godmode").setExecutor(new GodmodeCommand());
        Bukkit.getPluginManager().registerEvents(new OnDamageListener(), this);
        getCommand("spawns").setExecutor(new Spawns());
        getCommand("spawns").setTabCompleter(new SpawnsTabCompleter());
        getCommand("getperms").setExecutor(new GetPermsCommand());
        getCommand("day").setExecutor(new ChangeTimeCommand());
        getCommand("night").setExecutor(new ChangeTimeCommand());
        getCommand("noon").setExecutor(new ChangeTimeCommand());
        getCommand("sun").setExecutor(new ChangeTimeCommand());
        getCommand("storm").setExecutor(new ChangeTimeCommand());
        getCommand("thunder").setExecutor(new ChangeTimeCommand());
        getCommand("cmdspy").setExecutor(new CommandSpyCommand());
        Bukkit.getPluginManager().registerEvents(new CommandListener(), this);
        getCommand("setpluginprefix").setExecutor(new SetPluginPrefixCommand());
        getCommand("addadmin").setExecutor(new AddAdminCommand());
        getCommand("removeadmin").setExecutor(new RemoveAdminCommand());
        getCommand("v").setExecutor(new VanishCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("isop").setExecutor(new IsOpCommand());
        getCommand("isadmin").setExecutor(new IsAdminCommand());
        getCommand("msg").setExecutor(new MSGCommand());
        getCommand("r").setExecutor(new ReCommand());
        getCommand("msg").setTabCompleter(new MSGCompleter());
        getCommand("r").setTabCompleter(new MSGCompleter());
        InitPerms.initPerms();
        if  (ConfigHelper.existData("-Plugin.Prefix")){
            PREFIX = replaceColor(ConfigHelper.getConfig().getString("-Plugin.Prefix"));
        } else {
            ConfigHelper.setData("-Plugin.Prefix", "&8[&aMask-Plugin&8] &r");
            ConfigHelper.saveConfig();
        }
        if (ConfigHelper.existData("-Plugin.Error_Prefix")){
            ERROR_PREFIX = replaceColor(ConfigHelper.getConfig().getString("-Plugin.Error_Prefix"));
        } else {
            ConfigHelper.setData("-Plugin.Error_Prefix", "&8[&cMask-Plugin&8] &r");
            ConfigHelper.saveConfig();
        }
        if (ConfigHelper.existData("-Plugin.No_Perm")){
            NO_PERM = replaceColor(ConfigHelper.getConfig().getString("-Plugin.No_Perm"));
        } else {
            ConfigHelper.setData("-Plugin.No_Perm", "&cDu hast dazu keine Rechte!");
            ConfigHelper.saveConfig();
        }
        if (ConfigHelper.existData("-Plugin.MSG_Begin")){
            MSG_BEGIN = replaceColor(ConfigHelper.getConfig().getString("-Plugin.MSG_Begin"));
        } else {
            ConfigHelper.setData("-Plugin.MSG_Begin", "&6[MSG] &r");
            ConfigHelper.saveConfig();
        }



    }




public static String replaceColor(String msg){
        msg = msg.replace("&", "§");
        return msg;
}

    public static Main getPlugin(){
        return plugin;
    }

}
