package com.minecraft.sandbox.utils;

import org.bukkit.ChatColor;

public final class ChatUtils {
    /**
     * Transforms a string in order to display it in a rainbow color pattern.
     * @param text Text to transform
     * @return Transformed text
     */
    public static String printRainbowText(String text) {
        ChatColor[] rainbowColors = {ChatColor.RED, ChatColor.GOLD, ChatColor.GREEN, ChatColor.BLUE, ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE};
        int rainbowIndex = 0;
        StringBuilder fullText = new StringBuilder();

        for (int charIndex = 0; charIndex < text.length(); charIndex++) {
            if (charIndex > rainbowColors.length) rainbowIndex = 0; // failsafe if string length exceeds color array length
            fullText.append(rainbowColors[rainbowIndex]).append(text.charAt(charIndex));
            rainbowIndex++;
        }
        return fullText.toString().trim();
    }

    // -----------------------

    private ChatUtils() { throw new IllegalStateException(ChatUtils.class.getName() + " is an utility class and should not be instantiated."); }
}
