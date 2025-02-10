package ru.job4j.algo.hash;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {

        Map<Character, Integer> charIndexMap = new HashMap<>();
        int start = 0;
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < str.length(); right++) {
            char currentChar = str.charAt(right);

            if (charIndexMap.containsKey(currentChar)) {
                left = Math.max(charIndexMap.get(currentChar) + 1, left);
            }

            charIndexMap.put(currentChar, right);

            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
            }
        }

        return str.substring(start, start + maxLength);
    }
}