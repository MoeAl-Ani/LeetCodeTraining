public class BestTimeToBuyAndSellStockTwo {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i] < prices[i+1]) {
                // buy at i and sell at i+1
                maxProfit += prices[i+1] - prices[i];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(new BestTimeToBuyAndSellStockTwo().maxProfit(prices));
    }
}
