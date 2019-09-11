package Helper;

import Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class PermissionsHelper {

    public static void addPerm(Player target, String permission){
        PermissionAttachment perm = target.addAttachment(Main.getPlugin());
        perm.setPermission(permission, true);
    }



}
