package me.gregorsomething.veryusefullmod.sneakInMorse;

import me.gregorsomething.veryusefullmod.VeryUsefullModClient;
import me.gregorsomething.veryusefullmod.util.Chat;

public class MorseMain {

    public static void msgInMorse(String contents) {
        if (!canUse()) return;
        if (!contents.matches("[.\\- ]*")) return;
        String translated = Translator.morseToString(contents);
        Chat.sendMsgClient("&c&l[VUM]&7 From morse:&f " + translated);
    }

    public static void sendMsg(String args[]) {
        if (!canUse()) return;
        if (args.length < 3) {
            Chat.sendMsgClient("&c&l[VUM]&7 ?morse <user> <message>");
            return;
        }
        String target = args[1];
        StringBuilder message = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            message.append(" ").append(args[i]);
        }
        StringBuilder part = new StringBuilder();
        for (String word : Translator.stringToMorse(message.toString()).split("\\.\\.--\\.\\.\\.")) {
            if ((word.length() + part.length()) <= 230) {
                if (!part.toString().equals("")) part.append("..--...");
                part.append(word);
            } else {
                sendMsgToOther(target, part.toString());
                part = new StringBuilder(word);
            }
        }
        if (!part.toString().equals("")) sendMsgToOther(target, part.toString());

    }

    public static void sendMsgToOther(String target, String content) {
        assert VeryUsefullModClient.mc.player != null;
        VeryUsefullModClient.mc.player.sendChatMessage("/msg " + target + " " + content);
    }

    private static boolean canUse() {
            return true;
    }
}
