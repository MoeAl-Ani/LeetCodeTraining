import java.util.Arrays;

/**
 * @author Mohammed Al-Ani
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            throw new IllegalArgumentException("both arrays can not be empty");
        }
        int[] pq = new int[nums1.length + nums2.length];
        int c = 0;
        for (int i = 0; i < nums1.length; i++) {
            pq[c++] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            pq[c++] = nums2[i];
        }
        Arrays.sort(pq);

        if (!isEven(pq.length)) {
            // take the middle of the both combined
            return (int) pq[(pq.length)/2] * 1.0;

        } else {
            // take the middle of two both combined
            int n1 = pq[(pq.length-1) / 2];
            int n2 = pq[(pq.length-1) / 2 + 1];
            return (n1+n2) / 2.0;
        }
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }


    public static void main(String[] args) {
        if (args.length != 2) return;
        String nS1 = args[0];
        String nS2 = args[1];
        int[] num1 = new int[nS1.length()];
        for (int i = 0; i < nS1.length(); i++) {
            num1[i] = Integer.parseInt(Character.toString(nS1.charAt(i)));
        }
        int[] num2 = new int[nS2.length()];
        for (int i = 0; i < nS2.length(); i++) {
            num2[i] = Integer.parseInt(Character.toString(nS2.charAt(i)));
        }

        System.out.println("Median = " + new MedianOfTwoSortedArrays().findMedianSortedArrays(num1, num2));
    }
}
