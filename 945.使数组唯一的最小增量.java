import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=945 lang=java
 *
 * [945] 使数组唯一的最小增量
 *
 * https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/description/
 *
 * algorithms
 * Medium (42.75%)
 * Likes:    100
 * Dislikes: 0
 * Total Accepted:    19.4K
 * Total Submissions: 41.5K
 * Testcase Example:  '[1,2,2]'
 *
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * 
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 * 
 * 示例 1:
 * 
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 * 
 * 示例 2:
 * 
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 * 
 * 
 * 提示：
 * 
 * 
 * 0 <= A.length <= 40000
 * 0 <= A[i] < 40000
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 排序
     */
    public int minIncrementForUniqueSort1(int[] A) {
        int ans = 0;
        Arrays.sort(A);
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] >= A[i]) {
                ans += A[i - 1] - A[i] + 1;
                A[i] = A[i - 1] + 1;
            }
        }
        return ans;
    }

    public int minIncrementForUniqueSort2(int[] A) {
        Arrays.sort(A);
        int ans = 0, taken = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1]) {
                taken ++;
                ans -= A[i];
            } else {
                int cnt = Math.min(taken, A[i] - A[i - 1] - 1);
                ans += cnt * (cnt + 1) / 2 + cnt * A[i - 1];
                taken -= cnt;
            }
        }
        if (A.length > 0) {
            ans += taken * (taken + 1) / 2 + taken * A[A.length - 1];
        }
        return ans;
    }

    /**
     * 计数
     */
    public int minIncrementForUnique(int[] A) {
        int[] cnt = new int[80000];
        for(int x : A) {
            cnt[x]++;
        }

        int ans = 0, taken = 0;
        for (int i = 0; i < 80000; i++) {
            if (cnt[i] >= 2) {
                taken += cnt[i] - 1;
                ans -= (cnt[i] - 1) * i;
            } else if (taken > 0 && cnt[i] == 0) {
                taken --;
                ans += i;
            }
        }
        return ans;
    }
}
// @lc code=end

