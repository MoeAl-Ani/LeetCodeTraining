import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammed Al-Ani
 */
public class RomanToInt {

    public int romanToInt(String s) {
        Map<Character, Integer> romanNumMap = new HashMap<>();
        romanNumMap.put('I', 1);
        romanNumMap.put('V', 5);
        romanNumMap.put('X', 10);
        romanNumMap.put('L', 50);
        romanNumMap.put('C', 100);
        romanNumMap.put('D', 500);
        romanNumMap.put('M', 1000);
        int result = 0;
        if (s.length() == 1) {
            return romanNumMap.get(s.charAt(0));
        }
        if (s.length() == 2) {
            if (romanNumMap.get(s.charAt(0)) < romanNumMap.get(s.charAt(1))) {
                return romanNumMap.get(s.charAt(1)) - romanNumMap.get(s.charAt(0));
            }
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];

            if (i == chars.length -1)
                result+=romanNumMap.get(aChar);
            else if (romanNumMap.get(aChar) >= romanNumMap.get(chars[i+1])) {
                result+=romanNumMap.get(aChar);
            } else {
                result+= romanNumMap.get(chars[i+1]) - romanNumMap.get(aChar);
                i++;
            }
        }
        return Math.abs(result);
    }

    public static void main(String[] args) {
        System.out.println(new RomanToInt().romanToInt("MCMXCIV"));
        System.out.println(new RomanToInt().romanToInt("MCMXCVI"));
        System.out.println(new RomanToInt().romanToInt("IX"));
        System.out.println(new RomanToInt().romanToInt("III"));
        System.out.println(new RomanToInt().romanToInt("LVIII"));
    }
}
