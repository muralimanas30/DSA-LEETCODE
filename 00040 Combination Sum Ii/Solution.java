import java.util.*;

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(res, 0, new ArrayList<Integer>(), 0, candidates, target);
        return res;
    }

    public void backtrack(List<List<Integer>> res, int indx, List<Integer> temp, int sum, int[] candidates, int target) {
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (sum > target || indx == candidates.length) return;

        for (int i = indx; i < candidates.length; i++) {
            if (i > indx && candidates[i] == candidates[i - 1]) continue;

            temp.add(candidates[i]);
            backtrack(res, i + 1, temp, sum + candidates[i], candidates, target);
            temp.remove(temp.size() - 1);
        }
    }
}
