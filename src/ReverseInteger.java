/**
 * @author Mohammed Al-Ani
 **/
public class ReverseInteger {
    public ReverseInteger() {
        //
    }

    public int reverse(int x) {
        if(x == 0)return x;
        int result = 0;
        boolean negative = false;
        if (x < 0) {
            negative = true;
            x = Math.abs(x);
        }
        StringBuilder sb = new StringBuilder();
        while(x > 0) {
            sb.append(x % 10);
            x = x/10;
        }
        try {
            result = Integer.parseInt(sb.toString());
        } catch(Exception e) {
            return 0;
        }
        return negative ? result * -1 : result;
    }

    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInteger().reverse(123));
    }
}
