package crypticlib.util;

import crypticlib.CrypticLib;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MsgUtil {

    private static final Pattern colorPattern = Pattern.compile("&#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})");

    /**
     * 发送文本给一个对象，此文本会处理颜色代码和papi变量
     * @param sender 发送到的对象
     * @param msg 发送的消息
     */
    public static void sendMsg(CommandSender sender, String msg) {
        sendMsg(sender, msg, new HashMap<>());
    }

    /**
     * 发送文本给一个对象，此文本会处理颜色代码和papi变量，并根据replaceMap的内容替换源文本
     * @param sender 发送到的对象
     * @param msg 发送的消息
     * @param replaceMap 需要替换的文本
     */
    public static void sendMsg(CommandSender sender, String msg, Map<String, String> replaceMap) {
        if (sender == null) {
            return;
        }
        for (String formatStr : replaceMap.keySet()) {
            msg = msg.replace(formatStr, replaceMap.get(formatStr));
        }
        if (sender instanceof Player)
            msg = placeholder((Player) sender, msg);
        sender.spigot().sendMessage(component(color(msg)));
    }

    /**
     * 给玩家发送Title
     * @param player 发送的玩家
     * @param title 发送的Title
     * @param subTitle 发送的Subtitle
     * @param fadeIn Title的淡入时间
     * @param stay Title的停留时间
     * @param fadeOut Title的淡出时间
     */
    public static void sendTitle(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        if (title == null) {
            title = "";
        }
        if (subTitle == null) {
            subTitle = "";
        }
        title = color(placeholder(player, title));
        subTitle = color(placeholder(player, subTitle));
        player.sendTitle(title, subTitle, fadeIn, stay, fadeOut);
    }

    /**
     * 给玩家发送Title
     * @param player 发送的玩家
     * @param title 发送的Title
     * @param subTitle 发送的Subtitle
     */
    public static void sendTitle(Player player, String title, String subTitle) {
        sendTitle(player, title, subTitle, 10, 70, 20);
    }

    public static void sendActionBar(Player player, String text) {
        text = color(placeholder(player, text));
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component(text));
    }

    /**
     * 根据传入的配置文件，发送一条语言文本
     * @param sender 发送到的对象
     * @param langConfig 语言文本的来源文件
     * @param msgKey 发送消息的key
     * @param replaceMap 需要替换的文本
     */
    public static void sendLang(CommandSender sender, FileConfiguration langConfig, String msgKey, Map<String, String> replaceMap) {
        String msg = langConfig.getString(msgKey, msgKey);
        sendMsg(sender, msg, replaceMap);
    }

    /**
     * 根据传入的配置文件，发送一条语言文本
     * @param sender 发送到的对象
     * @param langConfig 语言文本的来源文件
     * @param msgKey 发送消息的key
     */
    public static void sendLang(CommandSender sender, FileConfiguration langConfig, String msgKey) {
        String msg = langConfig.getString(msgKey, msgKey);
        sendMsg(sender, msg);
    }

    /**
     * 将一个文本的颜色代码进行处理
     * @param text 需要处理的文本
     * @return 处理完颜色代码的文本
     */
    public static String color(String text) {
        if (CrypticLib.minecraftVersion() >= 16) {
            StringBuilder strBuilder = new StringBuilder(text);
            Matcher matcher = colorPattern.matcher(strBuilder);
            while (matcher.find()) {
                String colorCode = matcher.group();
                String colorStr = ChatColor.of(colorCode.substring(1)).toString();
                strBuilder.replace(matcher.start(), matcher.start() + colorCode.length(), colorStr);
                matcher = colorPattern.matcher(strBuilder);
            }
            text = strBuilder.toString();
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static BaseComponent component(String text) {
        BaseComponent[] baseComponents = TextComponent.fromLegacyText(text);
        TextComponent textComponent = new TextComponent();
        for (BaseComponent baseComponent : baseComponents) {
            textComponent.addExtra(baseComponent);
        }
        return textComponent;
    }

    /**
     * 给控制台发送一条文本，此文本会处理颜色代码
     * @param msg 发送的文本
     */
    public static void info(String msg) {
        sendMsg(Bukkit.getConsoleSender(), msg);
    }

    /**
     * 给控制台发送一条文本，此文本会处理颜色代码，并根据replaceMap的内容替换源文本
     * @param msg 发送的文本
     * @param replaceMap 需要替换的文本
     */
    public static void info(String msg, Map<String, String> replaceMap) {
        sendMsg(Bukkit.getConsoleSender(), msg, replaceMap);
    }

    /**
     * 将一条文本的papi变量进行处理，如果没有PlaceholderAPI插件，将会返回源文本
     * @param player papi变量的玩家
     * @param source 源文本
     * @return 处理完成的文本
     */
    public static String placeholder(Player player, String source) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            source = PlaceholderAPI.setPlaceholders(player, source);
        return source;
    }

}
