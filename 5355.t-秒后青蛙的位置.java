/*
 * @lc app=leetcode.cn id=5355 lang=java
 *
 * [5355] T 秒后青蛙的位置
 *
 * https://leetcode-cn.com/problems/frog-position-after-t-seconds/description/
 *
 * algorithms
 * Hard (23.51%)
 * Likes:    5
 * Dislikes: 0
 * Total Accepted:    1.1K
 * Total Submissions: 4.4K
 * Testcase Example:  '7\n[[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]]\n2\n4'
 *
 * 给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：
 * 
 * 
 * 在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
 * 青蛙无法跳回已经访问过的顶点。
 * 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
 * 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
 * 
 * 
 * 无向树的边用数组 edges 描述，其中 edges[i] = [fromi, toi] 意味着存在一条直接连通 fromi 和 toi
 * 两个顶点的边。
 * 
 * 返回青蛙在 t 秒后位于目标顶点 target 上的概率。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
 * 输出：0.16666666666666666 
 * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2 的概率跳到顶点
 * 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
 * 输出：0.3333333333333333
 * 解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。 
 * 
 * 
 * 示例 3：
 * 
 * 输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 20, target = 6
 * 输出：0.16666666666666666
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 100
 * edges.length == n-1
 * edges[i].length == 2
 * 1 <= edges[i][0], edges[i][1] <= n
 * 1 <= t <= 50
 * 1 <= target <= n
 * 与准确值误差在 10^-5 之内的结果将被判定为正确。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 首先根据边集合构建树，然后深度遍历树，每往下走一步，时间t减1。如果t=0或者到达叶子节点，就判断节点是否是target，返回此时的概率。
     *
     * 注意建树时，边的双向都要添加，(u,v)和(v,u)。
     * 注意遍历时，父节点不能再遍历。
     * 小技巧：在节点1的孩子中添加-1，作为1的父亲。在计算概率时，统一减1即可。
     * 小技巧：求概率为每个节点的孩子数量分之一，可以直接求孩子数量的乘积，最后用1.0除以该积得到概率。
     */
    private Map<Integer, List<Integer>> tree;
    private double ans;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        if (n == 1) return 1.0;
        tree = new HashMap<>();
        ans = -1;
        for (int[] edge : edges) {
            tree.computeIfAbsent(edge[0], k -> new ArrayList<>());
            tree.computeIfAbsent(edge[1], k -> new ArrayList<>());
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
        tree.get(1).add(-1);

        dfs(1, -1, 1, target, t);
        return ans != -1 ? ans : 0;
    }

    private void dfs(int id, int fa, int product, int target, int time) {
        if (time < 0) {
            return;
        }

        if (id == target && (time == 0 || tree.get(id).size() == 1)) {
            ans = 1.0 / product;
            return;
        }

        List<Integer> children = tree.get(id);
        for (int child : children) {
            if (fa == child) continue;
            dfs(child, id, product * (children.size() - 1), target, time - 1);
        }
    }
}
// @lc code=end

