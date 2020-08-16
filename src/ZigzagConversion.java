import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammed Al-Ani
 **/
public class ZigzagConversion {
    public ZigzagConversion() {
        //
    }

    public String convert(String s, int numRows) {
        if (s.length() == 0) return s;
        if (numRows == 1) return s;
        StringBuilder sb = new StringBuilder();
        int charIndex = 0;
        boolean downward = true;
        Map<Integer, StringBuilder> m = new HashMap<>();
        for (int row = 0; row < numRows; row++) {
            m.put(row, new StringBuilder());
        }
        m.get(0).append(s.charAt(charIndex++));

        while (charIndex < s.length()) {
            if (downward) {
                for (int rowD = 1; rowD < numRows && charIndex < s.length(); rowD++) {
                    m.get(rowD).append(s.charAt(charIndex++));
                }
            } else {
                for (int rowU = numRows-2; charIndex < s.length() && rowU >= 0; rowU--) {
                    m.get(rowU).append(s.charAt(charIndex++));
                }
            }
            downward = !downward;
        }

        for (int row = 0; row < numRows; row++) {
            sb.append(m.get(row).toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ZigzagConversion zigzagConversion = new ZigzagConversion();
        System.out.println("input = " + args[0]);
        System.out.println("rows = " + args[1]);
        System.out.println("zigzag = " + zigzagConversion.convert(args[0], Integer.parseInt(args[1])));
    }

    public String toString() {
        return super.toString();
    }
}
