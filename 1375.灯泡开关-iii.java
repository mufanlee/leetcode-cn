/*
 * @lc app=leetcode.cn id=1375 lang=java
 *
 * [1375] 灯泡开关 III
 *
 * https://leetcode-cn.com/problems/bulb-switcher-iii/description/
 *
 * algorithms
 * Medium (42.53%)
 * Likes:    2
 * Dislikes: 0
 * Total Accepted:    2.3K
 * Total Submissions: 5.5K
 * Testcase Example:  '[2,1,3,5,4]'
 *
 * 房间中有 n 枚灯泡，编号从 1 到 n，自左向右排成一排。最初，所有的灯都是关着的。
 * 
 * 在 k  时刻（ k 的取值范围是 0 到 n - 1），我们打开 light[k] 这个灯。
 * 
 * 灯的颜色要想 变成蓝色 就必须同时满足下面两个条件：
 * 
 * 
 * 灯处于打开状态。
 * 排在它之前（左侧）的所有灯也都处于打开状态。
 * 
 * 
 * 请返回能够让 所有开着的 灯都 变成蓝色 的时刻 数目 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：light = [2,1,3,5,4]
 * 输出：3
 * 解释：所有开着的灯都变蓝的时刻分别是 1，2 和 4 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：light = [3,2,4,1,5]
 * 输出：2
 * 解释：所有开着的灯都变蓝的时刻分别是 3 和 4（index-0）。
 * 
 * 
 * 示例 3：
 * 
 * 输入：light = [4,1,2,3]
 * 输出：1
 * 解释：所有开着的灯都变蓝的时刻是 3（index-0）。
 * 第 4 个灯在时刻 3 变蓝。
 * 
 * 
 * 示例 4：
 * 
 * 输入：light = [2,1,4,3,6,5]
 * 输出：3
 * 
 * 
 * 示例 5：
 * 
 * 输入：light = [1,2,3,4,5,6]
 * 输出：6
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == light.length
 * 1 <= n <= 5 * 10^4
 * light 是 [1, 2, ..., n] 的一个排列。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 每次开一个灯，从头遍历找到最后一个连续开着的灯，从尾遍历找到最前一个连续关着的灯，
     * 比较这两个位置是否相邻，相邻则所有开着的灯变为蓝色。
     */
    /*public int numTimesAllBlue(int[] light) {
        int ans = 0;
        boolean[] dp = new boolean[light.length];
        for (int id : light) {
            dp[id - 1] = true;
            int j = 0, k = light.length - 1;
            for (; j < light.length && dp[j]; j++) ;
            for (; k >= 0 && !dp[k]; k--) ;
            if ((j - 1) + 1 == k + 1) {
                ans++;
            }
        }
        return ans;
    }*/

    /**
     * 如果某一时刻都是蓝灯，等价于所有亮着的灯都连续排列在数组最左边，没有间断。
     * 所以只需要判断当前时刻亮灯的最大编号是否等于亮灯的数量即可。
     */
    public int numTimesAllBlue(int[] light) {
        int ans = 0, max = -1;
        for (int i = 0; i < light.length; i++) {
            max = Math.max(max, light[i]);
            if (max == i + 1) {
                ans++;
            }
        }
        return ans;
    }
}
// @lc code=end

