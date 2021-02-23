public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minimumSeenPrice = Integer.MAX_VALUE;


        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minimumSeenPrice){
                minimumSeenPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minimumSeenPrice);
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        //int[] prices = {1,2,3,4,5};

        System.out.println(new BestTimeToBuyAndSellStock().maxProfit(prices));
    }
}
