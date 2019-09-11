package Helper;

import org.bukkit.entity.Player;

public class PlayerHelper {

    public static boolean existPlayer(Player player){
        return player != null;
    }

    public static boolean hasPermissonIgnoreOP(Player player, String permission){
        if (existPlayer(player)){
            if (player.isOp()){
                player.setOp(false);
                if (player.hasPermission(permission)){
                    player.setOp(true);
                    return true;
                } else{
                    player.setOp(true);
                    return false;
                }
            } else if (player.hasPermission(permission)){
                return true;
            } else return false;
        } else return false;
    }


    public static boolean isAdmin(Player player){
        return hasPermissonIgnoreOP(player, "server.*");
    }

}
