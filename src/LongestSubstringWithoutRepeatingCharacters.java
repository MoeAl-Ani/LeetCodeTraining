import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammed Al-Ani
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) return 1;
        int max = 0;
        Map<Character, Integer> indexMap = new HashMap<>();
        int i = 0;
        while (i < s.length()) {
         if (indexMap.containsKey(s.charAt(i))) {
             // conflicts
             i = indexMap.get(s.charAt(i)) + 1;
             indexMap.clear();
         }
            indexMap.put(s.charAt(i), i);
         max = Math.max(max, indexMap.size());
            i++;
        }
        return max;
    }

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            int c = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(args[i]);
            System.out.println("input = " + args[i]);
            System.out.println("result = " + c);
        }
    }

}
