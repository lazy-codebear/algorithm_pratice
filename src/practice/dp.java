package practice;

public class dp {
    public int climbStairs(int n) {
        if (n < 3){
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    public int maxProfit(int[] prices) {
        if (prices.length < 2){
            return 0;
        }
        int min = prices[0];
        int max_profit = 0;
        for (int i = 0; i < prices.length; i++){
            min = Math.min(min, prices[i]);
            max_profit = Math.max(prices[i] - min, max_profit);
        }
        return max_profit;
    }
    public int maxProfit_dp(int[] prices){
        if (prices.length < 2){
            return 0;
        }
        int[][] dp = new int[prices.length][2];     //pratice.practice.dp[i][0]代表第i天持有这只股票，pratice.practice.dp[i][1]代表第i天不持有这只股票
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1;i < prices.length; i++){
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
        }
        return dp[prices.length - 1][1];
    }
    public int maxSubArray_answer(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++){
            dp[i] = nums[i] + Math.max(0, dp[i - 1]);       //以i作为序列的头 or 将i加入之前的序列
            max = Math.max(dp[i], max);
        }
        return max;
    }
    public int maxSubArray(int[] nums) {
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i <nums.length; i++){

            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = Math.max(nums[i], dp[i - 1][1] + nums[i]);
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
    public int rob(int[] nums) {
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = 0;
        for (int i = 1; i < nums.length; i++){
            dp[i][0] = dp[i - 1][1] + nums[i];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }

    public static void main(String[] args) {
        int[] prices = {-2,1,-3,4,-1,2,1,-5,4};

    }
}
