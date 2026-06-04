import java.util.HashMap;
import java.util.Map;

/**
 * Tracks the most investigated suspects using a HashMap for frequency counting.
 */
public class DetectiveAgencyTracking {
    public static String findMostInvestigated(String[] suspects) {
        if (suspects == null || suspects.length == 0) return null;

        Map<String, Integer> frequencyMap = new HashMap<>();
        String mostFrequentSuspect = "";
        int maxCount = 0;

        for (String suspect : suspects) {
            int count = frequencyMap.getOrDefault(suspect, 0) + 1;
            frequencyMap.put(suspect, count);

            if (count > maxCount) {
                maxCount = count;
                mostFrequentSuspect = suspect;
            }
        }

        return mostFrequentSuspect;
    }
}
