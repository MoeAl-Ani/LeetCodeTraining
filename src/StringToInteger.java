import java.util.Arrays;
import java.util.List;

public class StringToInteger {

    public int myAtoi(String s) {
        int result = 0;
        boolean sign = false;
        List<Character> digits = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        boolean startWithDigit = false;
        if (s == null) return result;
        if (s.isEmpty()) return result;
        int index = 0;
        if (s.charAt(index) != '+' && s.charAt(index) != '-' && s.charAt(index) != ' ' && !digits.contains(s.charAt(index)))
            return 0;
        if (digits.contains(s.charAt(index)))
            startWithDigit = true;
        boolean repeat = true;
        boolean hasAtLeastADigit = false;
        boolean hasASignChar = false;
        int signCount = 0;
        StringBuilder builder = new StringBuilder();
        while (repeat && index < s.length()) {
            char v = s.charAt(index);
            switch (v) {
                case ' ':
                    if (hasAtLeastADigit || hasASignChar) {
                        repeat = false;
                        break;
                    }
                    index++;
                    break;
                case '-':
                    hasASignChar = true;
                    if (startWithDigit || hasAtLeastADigit) {
                        repeat = false;
                        break;
                    }

                    sign = true;
                    index++;
                    signCount++;
                    break;
                case '+':
                    hasASignChar = true;
                    if (startWithDigit || hasAtLeastADigit) {
                        repeat = false;
                        break;
                    }
                    sign = false;
                    index++;
                    signCount++;
                    break;
                default:
                    if (digits.contains(v)) {
                        builder.append(v);
                        hasAtLeastADigit = true;
                        index++;
                    } else {
                        repeat = false;
                    }
                    break;
            }
        }
        if (signCount > 1) return result;
        String strValue = builder.toString();
        if (strValue.isEmpty()) return result;
        return parse(strValue, sign);
    }

    private int parse(String value, boolean sign) {
        int maxValue = Integer.MAX_VALUE;
        int minValue = Integer.MIN_VALUE;
        if (sign) {
            try {
                return Integer.parseInt(value) * -1;
            } catch (Exception e) {
                // out of range
                return minValue;
            }
        } else {
            try {
                return Integer.parseInt(value);
            } catch (Exception e) {
                // out of range
                return maxValue;
            }
        }
    }

    public static void main(String[] args) {
        StringToInteger stringToInteger = new StringToInteger();
        System.out.println("input = (42), parsed = " + stringToInteger.myAtoi("42"));
        System.out.println("input = (   -42), parsed = " + stringToInteger.myAtoi("   -42"));
        System.out.println("input = (4193 with words), parsed = " + stringToInteger.myAtoi("4193 with words"));
        System.out.println("input = (words and 987), parsed = " + stringToInteger.myAtoi("words and 987"));
        System.out.println("input = (-91283472332), parsed = " + stringToInteger.myAtoi("-91283472332"));
        System.out.println("input = (+1), parsed = " + stringToInteger.myAtoi("+1"));
        System.out.println("input = (+-12), parsed = " + stringToInteger.myAtoi("+-12"));
        System.out.println("input = (00000-42a1234), parsed = " + stringToInteger.myAtoi("00000-42a1234"));
        System.out.println("input = (   +0 123), parsed = " + stringToInteger.myAtoi("   +0 123"));
        System.out.println("input = (-5-), parsed = " + stringToInteger.myAtoi("-5-"));
        System.out.println("input = (+5+), parsed = " + stringToInteger.myAtoi("+5+"));
        System.out.println("input = (  +  413), parsed = " + stringToInteger.myAtoi("  +  413"));
    }
}
