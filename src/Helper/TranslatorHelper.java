package Helper;


import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TranslatorHelper {

    public static Configuration trans;
    private static List<String> translation = new ArrayList(), path = new ArrayList();


   public static void initTranslations(){

       initAddingTranslations();
       for (int i = 0; i<path.size(); i++){
           if (!trans.contains(path.get(i))){
                trans.set(path.get(i), translation.get(i));
                try {
                    Main.getPlugin().getConfig().save(Main.transFile);
                } catch (IOException e){
                    e.printStackTrace();
                }
           }
       }
   }

   public static void addTranslation(String name, String standartTranslation){
       path.add(name);
       translation.add(standartTranslation);
   }

    public static void initAddingTranslations(){
       addTranslation("JoinMessage", "&bDer Spieler &a<PLAYER> &b ist dem Servernetzwerk beigetreten.");
       addTranslation("LeaveMessage",  ColorHelper.replaceToConfigColor("§cDer Spieler §a<PLAYER> §chat das Servernetzwerk verlassen."));
       addTranslation("GamemodeChanger", ColorHelper.replaceToConfigColor("§6Dein Spielmodus wurde auf §c<GAMEMODE> §6gesetzt."));
       addTranslation("DeathMessage", ColorHelper.replaceToConfigColor("§e<PLAYER> §cwurde von §e<KILLER> §cgetötet!"));
       addTranslation("LobbyNotSet", ColorHelper.replaceToConfigColor("§cDer Lobby-Spawn ist noch nicht gesetzt!"));
       addTranslation("CommandNotFound", ColorHelper.replaceToConfigColor("§cBefehl wurde nicht gefunden!"));
       addTranslation("AddedAdminMessage", ColorHelper.replaceToConfigColor("§e<PLAYER> §awurde als Admin erfolgreich hinzugefügt!"));
       addTranslation("NoPermission", ColorHelper.replaceToConfigColor("§cDu hast dazu keine Rechte!"));
       addTranslation("OfflinePlayer", ColorHelper.replaceToConfigColor("§cDer Spieler ist Offline."));
    }

}
