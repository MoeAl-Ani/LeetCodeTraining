import java.util.*;

/**
 * @author Mohammed Al-Ani
 */
public class LongestValidParentheses {

    final Map<Character, Character> openingClosingMap = new HashMap<>();
    public LongestValidParentheses() {
        openingClosingMap.put('(', ')');
    }

    public int longestValidParentheses(String input) {
        if (input.length() == 1) return 0;
        return countLongestValid(input, getInvalidIndexes(input));
    }

    private int countValid(char[] chars, int lo, int hi) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((num1, num2) -> {
            if (num1 < num2) return 1;
            else if (num1 > num2) return -1;
            return 0;
        });
        if (chars.length == 0) return 0;
        int validCount = 0;
        for (int i = lo; i <= hi; i++) {
            if (isClosing(chars[i], openingClosingMap) && !pq.isEmpty()) {
                Integer index = pq.remove();
                if (openingClosingMap.get(chars[index]) != null && openingClosingMap.get(chars[index]) == chars[i]) {
                    validCount+=2;
                }
            } else {
                pq.offer(i);
            }
        }
        return validCount;
    }

    private int countLongestValid(String input, List<Integer> indexes) {
        char[] chars = input.toCharArray();
        if (input.isEmpty()) return 0;
        if (indexes.isEmpty()) {
            return countValid(chars, 0, chars.length -1);
        }
        int i = 0;
        int lo = 0;
        int hi = indexes.get(i);
        int maxx = Integer.MIN_VALUE;
        while (lo < chars.length) {
            int v = countValid(chars, lo, hi);
            if (v >= maxx) maxx = v;
            lo = hi +1;
            if (i+1 < indexes.size()) hi = indexes.get(++i);
            else hi = chars.length -1;
        }
        return maxx;
    }

    private List<Integer> getInvalidIndexes(String input) {
        Stack<Integer> pq = new Stack<>();

        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (isClosing(chars[i], openingClosingMap) && !pq.isEmpty()) {
                Integer index = pq.peek();
                if (openingClosingMap.get(chars[index]) == null || openingClosingMap.get(chars[index]) != chars[i]) {
                    pq.push(i);
                } else {
                    pq.pop();
                }
            } else {
                pq.push(i);
            }
        }
        if (!pq.isEmpty()) {
            return Arrays.asList(pq.toArray(new Integer[pq.size()]));
        }
        return new ArrayList<>();
    }

    private boolean isClosing(Character ch, Map<Character, Character> openingClosingMap) {
        return openingClosingMap.containsValue(ch);
    }


    public static void main(String[] args) {
        String[] inputs = {
                "))))((()((",
                "(",
                ")(((((()())()()))()(()))(",
                "(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((()())))(())()())((((()())((())))((()))()())))()(()()()(()((()))()()()))()()()(()()((((())()(((()(((())((()))()((()(()))()())))))))))())()())(()()))((()()()()())))((()()((((()()))))(())())()()))))(())()(()))((((((()))(()()()()(())(()((()))(()(())(((()()))(()((((()((((()((((())(())))()(())))()))(()((((((((())()()((())((()())()))))())())()(((((()()(((((())()((()(((()))(()(()))(()(()())))())(()((((()((()(((((()()))((()(()((())()))))(()(()())((()((()((((())))(()())()))()())())()))))(())))(())()((())(()(()))))()())(((()(()(((((((((()(()()())))((()((()())())())(((((()(()))))()))()))))()())()(()(())))(()))))(()))(((()))))())))))(((())((())((((()((()))((())))()))(((()))())()))()()()((()()(()())(()))()()((()())))))())(()())(((())))))())(())()))()())())(()(()((())((()(()((())(()()()(()((()(((()(())()(((())))))()())))))(()((((()(()()))(((())(()))(()()))))(())()((()))()))()()))()((())(()())())())(()))(()()(())()(()((((()())(((())(()()())())(()()))())))(()((())(()()))))(()))((()()((((()())(()()))()())()())))()(()((((())())()(())()))()()(()(()))))))(((()()((()))(()((((()()((())))())())))()())))())))((())()()()))()((()((()))()()())))(())())(()(()(()(()))())()))(())((())()())(((()()(((())(()()))(()())(())))()))(((()()()())))())))(((()))())())())))(((()))()())())())))))()()()()(())))(()())))(()()())))()((((()()()((((()))()())))(()))()))))(()())()))(((((())()((())()))(()())()()()())()(((()(()(())))))(()(((()()))((((()()))()))(((())(()(()))()(())))()()(()))))()))))()())))()))((((((((()()())((()(()()()(((())())())))()()(())(())))()())()())))((()))((((())()()))(())(((())(()()(((((()()((()()(((()(()()(((())()))))()(()())(()((((()()())(((()))(())((())()))))())))))(()()()())))()))(())((()())()())()()))(())))((()))()()((()())()()))(()()(())()())(())))((()(((())))()))))((((()))((())())())()(())(()))((((((())()()(((((()))()())(((()(()(())()((()())))(((())(()(())))))(()(()(((()))(())((((())))((())((((((((()(((((()(())))((((((())(()((((()(())()()((())())())((((((((()))))(((())()))()()))(())(())()()())(()()((())(()))())(((())(()((())(())(())))))(()(()(()()(((()()()))())(()))(())())()(((()((())((()())()(((((()()(()))))(((())()()))(()(()(()(()((())))))))(())())()))()(()(()))))()()((((())()())(((())(()))((()())(()((())()()(())((((())))))(())())())(())(()()(()()))(((()((((())(((())))))(()()()()(((()((((())(()))((())()))()(((((((()(()())))((()()(()()((())()))()(())))((()()((((()()()))((())()))((())(((()(()()()(((()((())((())()())())))((()))))))))))(())()()(((()()())))(((()))(()))))(((()(()())(()))(())((()))(((()(()()(((((((()())((((()))((((()(()())())()(((()(()((()))))))))))()()(((()()((((((((((())))))((((())())((()(((()())()))()()(((((())(()())())(((()((())((((((())(((())(((()(()(((((((()(())()())(()))))(()(((()))))))()))(((())))(()(()())()))(()()(()(()((()())()(())((()()((()()()(()(()()))(((((())()(()())()((()())()))(((((()((())()((()((((()(((())())(()()(())()(())(()(())))))(()())((()((()()()())(()))(()))))))(()((())(())((())()())()()))(()((()))(()()))()())(())(()()(()))((())()((())((((((())()(()()(((((())(()())())())()()(()())))))()))()((())((((((()())((()))))))((()(()()(((((((())))))))((()))(())(((()(()(())()()()()(()(())()))))))())()))()(((((()(())(((()))((()))()))()()(()(()((())(()))))()())((()())))))))(()()(()()))()((()(())()((())(()()))())((()())())()()))))((((()()()))())(())()())))()))()))))()))((()(()())()))()))(((()()()()())))())()))((()()())((()())))(((()((()()())(())))()(())(()(()(())(()(((((()()()(((())()())(()((()())(()(((()(())((((()())()(())))(((((((()))))())())))(()))()()(((()())(()))()())(())()))()((())()((())((()((())()())(()()))(((((()()()((((((((()(()((()()((((((()())))((((((())))())(()(()((((()(()())())()()))()((())())(()((((()(((()())((())))))(()())(()()()(()))()())()()))((()((()())(())()()()((())()()))))())()))())))(()))(()))()))((())()((()((()))))))())(((()))))))()(((()((())))((()())())()))((()(()(()(()))((()()))())))(()())))())(()))(())(())))))()(())(()()))()))((())))(()))(()))))(())()())(()(()))())(()(())(())))(()))())(()())))())(()())((()))()()((()(()()()(((((()((()((())(()())(())))()))))))(((())())))()((((()))()((()))())()))()))(()(()((()()())()()(((()))())))))()((((()()))))()))())))()())))(((((()(())))())(((()))((()))(((()(())())()((()(((()))()())))))((((()))()(()((((((()(()()()())(())((()))()(()()))))))()(((())))(())()())))))((()))(())()))))(()(((()()((())(()))))(((((()))))())))()(())(()(()))()))()))(()((())(()((()())()(((()))))())(())()(())))((())(()(((()))(((((()))(()))())))(()((((((())()((((())())()))((())))))())(()(())())))))()()(((())()())))))()))()())))()(())())(())()()()(((())))(())(((()))(()(((()()))())((()))(((()()()()())()()))(()))))()()))))(((()()))))()()(()()))()()()())())()((())(((()())(((())(()((()(((()(()())()()()(()((())(()()(()()()))))))()((()))))()(()))()))(())()()())))()()(((()))((()()(((()())))((()()())((())))))()())()((())))())(()())()()()()((())((()()())((()()))())(())())()(()(((()))())(()))))(()()))(())))))))()())()((()())()()))()())))((()()(()())()(()))((())()))(((())))())))(((()()())())(",
                "(()(((()",
                ")()))",
                ")()())",
                "(()",
                "()(())",
                "()()",
                "()(()",
                "(()()",
                "(()(()(()"};
        long startTime = System.currentTimeMillis();
        for (String input : inputs) {
            System.out.println("input = " + input);
            System.out.println(new LongestValidParentheses().longestValidParentheses(input));
        }
        System.out.println("elapsed = " + (System.currentTimeMillis() - startTime) / 1000);
    }
}
