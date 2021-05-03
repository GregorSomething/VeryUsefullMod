package me.gregorsomething.veryusefullmod.util;

import me.gregorsomething.veryusefullmod.VeryUsefullModClient;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.text.*;

public class Chat {

    public static void sendMsgClient(String msg) {
        VeryUsefullModClient.mc.inGameHud.getChatHud().addMessage(new LiteralText(ChatColor.translateAlternateColorCodes('&', msg)));
    }
    public static Text getMsgWithExtra(String start, String end, String hover, String click, ClickEvent.Action action) {
        BaseText startText = new LiteralText(ChatColor.translateAlternateColorCodes('&', start));
        BaseText endText = new LiteralText(ChatColor.translateAlternateColorCodes('&', end));
        endText.setStyle(endText.getStyle().withClickEvent(new ClickEvent(action, click)));
        endText.setStyle(endText.getStyle().withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new LiteralText(ChatColor.translateAlternateColorCodes('&', hover)))));
        return startText.append(endText);
    }
    public static void send(Text text) {
        VeryUsefullModClient.mc.inGameHud.getChatHud().addMessage(text);
    }
}
