import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 *
 * https://leetcode-cn.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (37.93%)
 * Likes:    395
 * Dislikes: 0
 * Total Accepted:    46.7K
 * Total Submissions: 122.9K
 * Testcase Example:  '[1,2,5]\n11'
 *
 * 给定不同面额的硬币 coins 和一个总金额
 * amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 
 * 示例 1:
 * 
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3 
 * 解释: 11 = 5 + 5 + 1
 * 
 * 示例 2:
 * 
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * 
 */

// @lc code=start
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return core(coins, amount, new int[amount]);
    }

    private int core(int[] coins, int remain, int[] cnt) {
        if (remain < 0) return -1;
        if (remain == 0) return 0;
        if (cnt[remain - 1] != 0) return cnt[remain - 1];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int res = core(coins, remain - coins[i], cnt);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        cnt[remain - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return cnt[remain - 1];
    }
    
    /**
     * dp[i] 表示总金额为i时的最少硬币数。
     * dp[i] = Math.min(dp[i - coins[j]] + 1) j=[0,coins.length];
     */
    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for(int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
// @lc code=end

