package me.gregorsomething.veryusefullmod.sneakInMorse;

public class Translator {

    private static final char[] normalLetters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            ',', '.', '?', '_' };

    private static final String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            "-----", "--..--", ".-.-.-", "..--..", "..--..." };

    public static String stringToMorse(String text) {
        char[] chars = text.toLowerCase().replace(" ", "_").toCharArray();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < chars.length; i++){
            for (int j = 0; j < normalLetters.length; j++){
                if (normalLetters[j] == chars[i]){
                    out.append(morse[j]).append(" ");
                }
            }
        }
        return out.toString();
    }

    public static String morseToString(String text) {
        String[] input = text.split(" ");
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < morse.length; j++) {
                if (morse[j].equals(input[i])) {
                    out.append(normalLetters[j]);
                }
            }
        }
        return out.toString().replace("_", " ");
    }
}

