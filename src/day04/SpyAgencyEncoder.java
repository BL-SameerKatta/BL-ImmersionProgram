/**
 * Day 4 - Core Programming
 * Concepts: Strings, StringBuilder, immutability, palindrome, anagram, character frequency
 *
 * Story Problem:
 * A spy agency encodes messages. Reverse a secret message, check if it is a palindrome,
 * count vowels/consonants, and verify if two intercepts are anagrams.
 * Extend: find the first non-repeating character in a surveillance log.
 */
public class SpyAgencyEncoder {

    // --- Reverse a string using StringBuilder ---
    static String reverseMessage(String msg) {
        return new StringBuilder(msg).reverse().toString();
    }

    // --- Check palindrome (case-insensitive, ignoring spaces) ---
    static boolean isPalindrome(String msg) {
        String clean = msg.replaceAll("\\s+", "").toLowerCase();
        String rev   = new StringBuilder(clean).reverse().toString();
        return clean.equals(rev);
    }

    // --- Count vowels and consonants ---
    static void countVowelsConsonants(String msg) {
        int vowels = 0, consonants = 0;
        String lower = msg.toLowerCase();
        for (char c : lower.toCharArray()) {
            if (Character.isLetter(c)) {
                if ("aeiou".indexOf(c) >= 0) vowels++;
                else consonants++;
            }
        }
        System.out.printf("  Vowels: %d | Consonants: %d%n", vowels, consonants);
    }

    // --- Check if two strings are anagrams (case-insensitive, ignoring spaces) ---
    static boolean areAnagrams(String a, String b) {
        char[] ca = a.replaceAll("\\s+", "").toLowerCase().toCharArray();
        char[] cb = b.replaceAll("\\s+", "").toLowerCase().toCharArray();
        java.util.Arrays.sort(ca);
        java.util.Arrays.sort(cb);
        return java.util.Arrays.equals(ca, cb);
    }

    // --- Find first non-repeating character in a log string ---
    static char firstNonRepeating(String log) {
        int[] freq = new int[256];
        for (char c : log.toCharArray()) freq[c]++;
        for (char c : log.toCharArray()) {
            if (freq[c] == 1) return c;
        }
        return '_'; // sentinel: all characters repeat
    }

    // --- Print character frequency map ---
    static void charFrequency(String msg) {
        int[] freq = new int[256];
        for (char c : msg.toCharArray()) if (c != ' ') freq[c]++;
        System.out.print("  Freq map: { ");
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) System.out.print((char) i + ":" + freq[i] + " ");
        }
        System.out.println("}");
    }

    public static void main(String[] args) {

        String secret = "RACECAR";
        System.out.println("=== SPY AGENCY — MESSAGE ENCODER ===");
        System.out.println("  Original message  : " + secret);
        System.out.println("  Reversed message  : " + reverseMessage(secret));
        System.out.println("  Is palindrome?    : " + isPalindrome(secret));
        System.out.print("  ");
        countVowelsConsonants(secret);
        charFrequency(secret);

        System.out.println();
        String msg2 = "HELLO SPY";
        System.out.println("  Original message  : " + msg2);
        System.out.println("  Reversed message  : " + reverseMessage(msg2));
        System.out.println("  Is palindrome?    : " + isPalindrome(msg2));
        System.out.print("  ");
        countVowelsConsonants(msg2);

        // Anagram check
        System.out.println("\n=== INTERCEPT ANAGRAM VERIFICATION ===");
        String intercept1 = "LISTEN";
        String intercept2 = "SILENT";
        String intercept3 = "ENLIST";
        String intercept4 = "DANGER";
        System.out.printf("  \"%s\" vs \"%s\" → Anagram? %b%n", intercept1, intercept2, areAnagrams(intercept1, intercept2));
        System.out.printf("  \"%s\" vs \"%s\" → Anagram? %b%n", intercept1, intercept3, areAnagrams(intercept1, intercept3));
        System.out.printf("  \"%s\" vs \"%s\" → Anagram? %b%n", intercept1, intercept4, areAnagrams(intercept1, intercept4));

        // First non-repeating character
        System.out.println("\n=== SURVEILLANCE LOG — First Non-Repeating Char ===");
        String[] logs = {"aabbcddeff", "programming", "aabb", "swiss"};
        for (String log : logs) {
            char result = firstNonRepeating(log);
            if (result == '_') {
                System.out.printf("  Log: \"%-15s\" → All chars repeat%n", log);
            } else {
                System.out.printf("  Log: \"%-15s\" → First unique: '%c'%n", log, result);
            }
        }

        // StringBuilder vs String immutability demo
        System.out.println("\n=== IMMUTABILITY DEMO ===");
        String immutable = "Agent";
        immutable.concat(" Zero"); // no effect on original
        System.out.println("  String after concat (unchanged): \"" + immutable + "\"");
        StringBuilder mutable = new StringBuilder("Agent");
        mutable.append(" Zero");
        System.out.println("  StringBuilder after append    : \"" + mutable + "\"");
    }
}
