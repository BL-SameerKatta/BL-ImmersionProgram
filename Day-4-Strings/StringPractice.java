import java.util.HashMap;
import java.util.Map;

public class StringPractice {

    public static void main(String[] args) {

        String secretMessage = "madam";

        reverseString(secretMessage);

        System.out.println("Palindrome: " + isPalindrome(secretMessage));

        countVowelsAndConsonants("BridgeLabz");

        System.out.println("Anagram Check: " +
                isAnagram("listen", "silent"));

        System.out.println("First Non-Repeating Character: " +
                firstNonRepeatingCharacter("swiss"));
    }

    public static void reverseString(String str) {
        StringBuilder reversed = new StringBuilder(str);
        System.out.println("Reversed String: " + reversed.reverse());
    }

    public static boolean isPalindrome(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equalsIgnoreCase(reversed);
    }

    public static void countVowelsAndConsonants(String str) {
        int vowels = 0;
        int consonants = 0;

        str = str.toLowerCase();

        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                if ("aeiou".indexOf(ch) != -1)
                    vowels++;
                else
                    consonants++;
            }
        }

        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
    }

    public static boolean isAnagram(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        if (s1.length() != s2.length())
            return false;

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        java.util.Arrays.sort(arr1);
        java.util.Arrays.sort(arr2);

        return java.util.Arrays.equals(arr1, arr2);
    }

    public static char firstNonRepeatingCharacter(String str) {
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (char ch : str.toCharArray()) {
            if (map.get(ch) == 1)
                return ch;
        }

        return '-';
    }
}
