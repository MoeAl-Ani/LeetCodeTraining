import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MaximumNumbersOfBallsInBox {
    public int countBalls(int lowLimit, int highLimit) {
        AtomicInteger boxNumber = new AtomicInteger();
        AtomicInteger maxBallCount = new AtomicInteger();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = lowLimit; i <= highLimit; i++) {
            int ball = i;
            int digitSum = 0;
            while (ball > 0) {
                digitSum += ball%10;
                ball = ball/10;
            }
            if (map.containsKey(digitSum)) {
                Integer ballsCount = map.get(digitSum);
                map.put(digitSum, ballsCount+1);
                if (map.get(digitSum) >= maxBallCount.get()) {
                    maxBallCount.set(ballsCount+1);
                    boxNumber.set(digitSum);
                }
            } else {
                map.putIfAbsent(digitSum, 1);
            }
        }

        return map.getOrDefault(boxNumber.get(),1);
    }

    public static void main(String[] args) {
        System.out.println("expected 2 : " + new MaximumNumbersOfBallsInBox().countBalls(1,10));
        System.out.println("expected 2 : " + new MaximumNumbersOfBallsInBox().countBalls(19,28));
        System.out.println("expected 2 : " + new MaximumNumbersOfBallsInBox().countBalls(5,15));
        System.out.println("expected 1 : " + new MaximumNumbersOfBallsInBox().countBalls(8,16));
        System.out.println("expected 9 : " + new MaximumNumbersOfBallsInBox().countBalls(11,104));
    }
}
