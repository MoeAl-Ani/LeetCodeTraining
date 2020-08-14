/**
 * @author Mohammed Al-Ani
 **/
public class LongestPalindromicSubString {
    public LongestPalindromicSubString() {
        //
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            LongestPalindromicSubString longestPalindromicSubString = new LongestPalindromicSubString();
            for (String data : args) {
                long start = System.currentTimeMillis();
                System.out.println("input = " + data);
                System.out.println("longest = " + longestPalindromicSubString.longestPalindrome(data));
                System.out.println("time elapsed = " + (System.currentTimeMillis() - start) + "ms");
                System.out.println("----------------");
            }
        }
    }

    public String longestPalindrome(String data) {
        if (data == null || data.length() == 0) {
            return "";
        }
        if (data.length() < 2) return data;
        if (isPalindrome(data)) return data;

        int startIndex = 0;
        int currentIndex = 1;
        String res = "";
        while (startIndex < data.length()) {

            String subData = data.substring(startIndex, currentIndex + 1);
            if (isPalindrome(subData)) {
                if (res.length() < subData.length()) res = subData;
            }
            if (res.length() >= data.length() - startIndex) return res;
            currentIndex++;

            if (currentIndex == data.length()) {
                startIndex++;
                if (startIndex == data.length() -1) currentIndex = startIndex;
                else currentIndex = startIndex + 1;
            }
        }
        return res;
    }

    private boolean isPalindrome(String input) {
        boolean isPalindrome = true;

        for (int i = 0, j = input.length() - 1; i < j; i++, j--) {
            if (input.charAt(i) != input.charAt(j)) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }
}
