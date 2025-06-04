/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Pair<Integer, Integer>, ArrayList<Integer>> map = new TreeMap<>(
                (a, b) -> {
                    if (!a.getValue().equals(b.getValue()))
                        return a.getValue() - b.getValue();
                    else
                        return a.getKey() - b.getKey();
                });
        dfs(root, 0, 0, map);
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> colMap = new TreeMap<>();

        for (Map.Entry<Pair<Integer, Integer>, ArrayList<Integer>> entry : map.entrySet()) {
            Pair<Integer, Integer> key = entry.getKey();
            int col = key.getValue();
            ArrayList<Integer> vals = entry.getValue();
            Collections.sort(vals);

            colMap.putIfAbsent(col, new ArrayList<>());
            colMap.get(col).addAll(vals);
        }

        res.addAll(colMap.values());
        return res;
    }

    private void dfs(TreeNode node, int row, int col, TreeMap<Pair<Integer, Integer>, ArrayList<Integer>> map) {
        if (node == null)
            return;
        Pair<Integer, Integer> key = new Pair<>(row, col);
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(node.val);

        dfs(node.left, row + 1, col - 1, map);
        dfs(node.right, row + 1, col + 1, map);
    }
}