import java.util.ArrayList;
import java.util.List;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;
        List<Integer> nums = new ArrayList<>();
        int xTemp = x;
        int value = xTemp % 10;
        while (xTemp > 0) {
            nums.add(value);
            xTemp = xTemp / 10;
            value = xTemp % 10;
        }
        if (nums.size() == 0)
            return false;

        for (int i = 0, j = nums.size()-1; i < nums.size() / 2; i++, j--) {
                if (!nums.get(i).equals(nums.get(j)))
                    return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeNumber palindromeNumber = new PalindromeNumber();
        System.out.println(palindromeNumber.isPalindrome(121));
        System.out.println(palindromeNumber.isPalindrome(-121));
        System.out.println(palindromeNumber.isPalindrome(0));
        System.out.println(palindromeNumber.isPalindrome(-0));
        System.out.println(palindromeNumber.isPalindrome(10));
        System.out.println(palindromeNumber.isPalindrome(101));
        System.out.println(palindromeNumber.isPalindrome(10022201));
    }
}
