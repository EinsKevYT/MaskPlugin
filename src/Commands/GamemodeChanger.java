package Commands;

import Helper.PlayerHelper;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeChanger implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0){
                p.sendMessage("§cBitte gib einen Spielmodus von 0-3 an!");
            } else {
                if (args.length == 1){
                    switch (args[0]){
                        case "0":
                            if (p.hasPermission("server.change.own.gm.survival") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                p.setGameMode(GameMode.SURVIVAL);
                            } else {
                                p.sendMessage(Main.PREFIX + "§cDu hast dazu keine Rechte!");
                            }
                            break;
                        case "1":
                            if (p.hasPermission("server.change.own.gm.creativ") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                p.setGameMode(GameMode.CREATIVE);
                            } else {
                                p.sendMessage(Main.PREFIX + "§cDu hast dazu keine Rechte!");
                            }
                            break;
                        case "2":
                            if (p.hasPermission("server.change.own.gm.adventure") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                p.setGameMode(GameMode.ADVENTURE);
                            } else {
                                p.sendMessage(Main.PREFIX + "§cDu hast dazu keine Rechte!");
                            }
                            break;
                        case "3":
                            if (p.hasPermission("server.change.own.gm.spectator") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                p.setGameMode(GameMode.SPECTATOR);
                            } else {
                                p.sendMessage(Main.PREFIX + "§cDu hast dazu keine Rechte!");
                            }
                            break;
                        default:
                            p.sendMessage("§cBitte gib einen Spielmodus von 0-3 an!");
                            break;
                    }
                } else {
                    Player t = Bukkit.getPlayer(args[1]);
                    if (PlayerHelper.existPlayer(t)){
                        switch (args[0]){
                            case "0":
                                if (p.hasPermission("server.change.other.gm.survival") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                    t.setGameMode(GameMode.SURVIVAL);
                                    p.sendMessage("§6Du hast den Spielmodus von §c" + t.getPlayer().getName() + " §6auf §aSurvival §6gesetzt.");
                                } else {
                                    p.sendMessage(Main.PREFIX + "§cDu hast dazu keine Rechte!");
                                }
                                break;
                            case "1":
                                if (p.hasPermission("server.change.other.gm.creativ") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                    t.setGameMode(GameMode.CREATIVE);
                                    p.sendMessage("§6Du hast den Spielmodus von §c" + t.getPlayer().getName() + " §6auf §aCreativ §6gesetzt.");
                                } else {
                                    p.sendMessage(Main.PREFIX + "§cDu hast dazu keine Rechte!");
                                }

                                break;
                            case "2":
                                if (p.hasPermission("server.change.other.gm.adventure") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                    t.setGameMode(GameMode.ADVENTURE);
                                    p.sendMessage("§6Du hast den Spielmodus von §c" + t.getPlayer().getName() + " §6auf §aAdventure §6gesetzt.");
                                } else {
                                    p.sendMessage(Main.PREFIX + "§cDu hast dazu keine Rechte!");
                                }

                                break;
                            case "3":
                                if (p.hasPermission("server.change.other.gm.spectator") || PlayerHelper.hasPermissonIgnoreOP(p, "server.*")){
                                    t.setGameMode(GameMode.SPECTATOR);
                                    p.sendMessage("§6Du hast den Spielmodus von §c" + t.getPlayer().getName() + " §6auf §aSpectator §6gesetzt.");
                                } else {
                                    p.sendMessage(Main.PREFIX + "§cDu hast dazu keine Rechte!");
                                }


                                break;
                            default:
                                p.sendMessage("§cBitte gib einen Spielmodus von 0-3 an!");
                                break;
                        }
                    } else p.sendMessage(Main.OFFLINE_PLAYER);
                }
            }
        }

        return false;
    }
}
