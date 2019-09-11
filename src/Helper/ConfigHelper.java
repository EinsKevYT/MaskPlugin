package Helper;

import Main.Main;
import org.bukkit.configuration.Configuration;

public class ConfigHelper {

    public static Configuration getConfig(){
        return Main.getPlugin().getConfig();
    }

    public static void saveConfig(){
        Main.getPlugin().saveConfig();
    }

    public static boolean existData(String path){
        return Main.getPlugin().getConfig().contains(path);
    }

    public static void setData(String path, Object obj){
        getConfig().set(path, obj);
    }

}
