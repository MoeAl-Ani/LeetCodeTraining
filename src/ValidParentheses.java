import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ValidParentheses {

    private boolean isValid = false;
    public boolean isValid(String input) {
        final Map<Character, Character> openingClosingMap = new HashMap<>();
        openingClosingMap.put('{', '}');
        openingClosingMap.put('(', ')');
        openingClosingMap.put('[', ']');

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                if (num1 < num2) return 1;
                else if (num1 > num2) return -1;
                return 0;
            }
        });

        char[] chars = input.toCharArray();
        if (chars.length == 0) return true;
        if (chars.length % 2 != 0) return false;
        if (isClosing(chars[0], openingClosingMap)) return false;

        for (int i = 0; i < chars.length; i++) {
            if (isClosing(chars[i], openingClosingMap) && !pq.isEmpty()) {
                Integer index = pq.remove();
                if (openingClosingMap.get(chars[index]) != chars[i]) {
                    return false;
                } else {
                    isValid = true;
                }
            } else {
                isValid = false;
                pq.offer(i);
            }
        }
        return isValid;
    }

    private boolean isClosing(Character ch, Map<Character, Character> openingClosingMap) {
        return openingClosingMap.containsValue(ch);
    }

    public static void main(String[] args) {
        String[] inputs = {"}}", "{}", "{}()", "{()}", "[]", "[()]", "{", "{)", "{[}", "()[]{}", "([)]", "(([]){})", "(("};
        for (String input : inputs) {
            System.out.println("input = " + input);
            System.out.println(new ValidParentheses().isValid(input));

        }
    }
}
