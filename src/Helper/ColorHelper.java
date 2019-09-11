package Helper;

public class ColorHelper {

    public static String replaceToChatColor(String msg){
        return msg.replace("&", "§");
    }
    public static String replaceToConfigColor(String msg){
        return msg.replace("§", "&");
    }

}
