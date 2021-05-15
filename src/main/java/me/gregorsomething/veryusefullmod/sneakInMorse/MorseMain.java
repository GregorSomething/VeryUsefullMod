package me.gregorsomething.veryusefullmod.sneakInMorse;

import me.gregorsomething.veryusefullmod.VeryUsefullModClient;
import me.gregorsomething.veryusefullmod.util.Chat;

import java.util.ArrayList;

public class MorseMain {

    public static void msgInMorse(String contents) {
        if (!contents.matches("[.\\- ]*")) return;
        String trasnlated = Translator.morseToString(contents);
        Chat.sendMsgClient("&c&l[VUM]&7 From morse:&f " + trasnlated);
    }

    public static void sendMsg(String args[]) {
        if (args.length < 3) {
            Chat.sendMsgClient("&c&l[VUM]&7 ?morse <user> <message>");
            return;
        }
        String target = args[1];
        String message = "";
        for (int i = 2; i < args.length; i++) {
            message = message + " " + args[i];
        }
        String part = "";
        int length = 0;
        for (String word : Translator.stringToMorse(message).split("\\.\\.--\\.\\.\\.")) {
            if ((word.length() + part.length()) <= 230) {
                if (!part.equals("")) part = part + "..--...";
                part = part + word;
            } else {
                sendMsgToOther(target, part);
                part = word;
            }
        }
        if (!part.equals("")) sendMsgToOther(target, part);

    }

    public static void sendMsgToOther(String target, String content) {
        VeryUsefullModClient.mc.player.sendChatMessage("/msg " + target + " " + content);
    }
}
