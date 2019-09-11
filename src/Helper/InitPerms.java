package Helper;

import Main.Main;
import java.util.List;

public class InitPerms {

    static List perms = Main.perms;
    public static void initPerms(){
        addPerm("server.change.own.fly");
        addPerm("server.change.other.fly");
        addPerm("server.spawn.world");
        addPerm("server.getspawns");
        addPerm("server.world.spawn2world");
        addPerm("server.world.setspawn");
        addPerm("server.world.setlobby");
        addPerm("server.remove.home");
        addPerm("server.set.home");
        addPerm("server.tp.home");
        addPerm("server.reload");
        addPerm("server.plugman");
        addPerm("server.money.pay");
        addPerm("server.money.reset");
        addPerm("server.money.remove");
        addPerm("server.money.add");
        addPerm("server.money.set");
        addPerm("server.money.own.money");
        addPerm("server.money.other.money");
        addPerm("server.lobby");
        addPerm("server.own.heal");
        addPerm("server.own.god");
        addPerm("server.other.god");
        addPerm("server.change.other.gm.spectator");
        addPerm("server.change.other.gm.adventure");
        addPerm("server.change.other.gm.creativ");
        addPerm("server.change.other.gm.survival");
        addPerm("server.change.own.gm.spectator");
        addPerm("server.change.own.gm.adventure");
        addPerm("server.change.own.gm.creativ");
        addPerm("server.change.own.gm.survival");
        addPerm("server.own.givefood");
        addPerm("server.other.givefood");
        addPerm("server.get.permissions");
        addPerm("server.*");
        addPerm("server.time.day");
        addPerm("server.time.night");
        addPerm("server.time.noon");
        addPerm("server.weather.sun");
        addPerm("server.weather.thunder");
        addPerm("server.weather.storm");
        addPerm("server.cmdspy");
        addPerm("server.vanish.see");
        addPerm("server.own.vanish");
        addPerm("server.other.vanish");
        addPerm("server.msg");
    }

    private static void addPerm(String perm){
        perms.add(perm);
    }
}
