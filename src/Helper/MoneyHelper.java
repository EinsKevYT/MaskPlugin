package Helper;

import Main.Main;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;


public class MoneyHelper {

    public static void setMoney(double amount, Player player){
        Configuration config = Main.getPlugin().getConfig();
        config.set("Player." + player.getUniqueId() + ".Money", amount);
        Main.getPlugin().saveConfig();
    }

    public static void addMoney(double amount, Player player){
        ConfigHelper.setData("Player." + player.getUniqueId() + ".Money",  getMoney(player) + amount);
        ConfigHelper.saveConfig();
    }

    public static boolean hasEnoughMoney(double amount, Player player){
        return getMoney(player) > amount || getMoney(player) == amount;
    }

    public static void removeMoney(double amount, Player player){
        ConfigHelper.setData("Player." + player.getUniqueId() + ".Money", getMoney(player) - amount);
        ConfigHelper.saveConfig();
    }

    public static double getMoney(Player player){
        return ConfigHelper.getConfig().getDouble("Player." + player.getUniqueId() + ".Money");
    }

}
