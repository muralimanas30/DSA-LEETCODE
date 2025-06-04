# 00040 - Combination Sum Ii
    
**Language:** Java  
**Runtime:** 5 ms (Beats 18.32% of users)  
**Memory:** 43.4 MB (Beats 53.68% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 40 | COMBINATION SUM II | [LeetCode Problem](https://leetcode.com/problems/combination-sum-ii/) |

---

## 💡 **Problem Explanation**

Given a collection of candidate numbers (`candidates`) and a target number (`target`), find all unique combinations in `candidates` where the candidate numbers sum to `target`. Each number in `candidates` may only be used once in one combination.

**Note:** The solution set must not contain duplicate combinations.

**Example 1:**

*   **Input:** `candidates = [10,1,2,7,6,1,5], target = 8`
*   **Output:** `[[1,1,6],[1,2,5],[1,7],[2,6]]`

**Example 2:**

*   **Input:** `candidates = [2,5,2,1,2], target = 5`
*   **Output:** `[[1,2,2],[5]]`

## 📊 **Algorithm**

*   Sort the `candidates` array to handle duplicates effectively.
*   Create a recursive `backtrack` function to explore all possible combinations.
*   In `backtrack`:
    *   If the current sum equals the `target`, add the current combination to the result.
    *   If the current sum exceeds the `target` or the index is out of bounds, return.
    *   Iterate through the `candidates` array starting from the given index.
    *   Skip duplicate numbers to avoid duplicate combinations.
    *   Add the current number to the temporary combination.
    *   Recursively call `backtrack` with the next index and updated sum.
    *   Remove the last added number to backtrack.

## 🔥 **Code Implementation**

```java
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
            // Skip duplicate numbers
            if (i > indx && candidates[i] == candidates[i - 1]) continue;

            temp.add(candidates[i]);
            // Recursive call with next index
            backtrack(res, i + 1, temp, sum + candidates[i], candidates, target);
            temp.remove(temp.size() - 1); // Backtrack
        }
    }
}
```

## 📊 **TABLE Representation**

Let's consider `candidates = [10,1,2,7,6,1,5]` and `target = 8`. After sorting, `candidates = [1,1,2,5,6,7,10]`. The backtracking algorithm explores different combinations, and the table below illustrates a simplified version of the combinations considered:

| Index | Candidate | Current Combination | Current Sum | Action                                                                                                                                   |
|-------|-----------|----------------------|-------------|------------------------------------------------------------------------------------------------------------------------------------------|
| 0     | 1         | \[1]                 | 1           | Add 1, explore further combinations                                                                                                      |
| 1     | 1         | \[1, 1]              | 2           | Add 1, explore further combinations                                                                                                      |
| 2     | 2         | \[1, 1, 2]           | 4           | Add 2, explore further combinations                                                                                                      |
| 3     | 5         | \[1, 1, 2, 5]        | 9           | Sum > Target, backtrack                                                                                                                  |
| 2     | 2         | \[1, 1, 2]           | 4           | Remove 5, try next candidate                                                                                                             |
| 3     | 5         |  skip as it led to > target backtracking, same element                                                                                                                                        |
| 4     | 6         | \[1, 1, 6]           | 8           | Sum == Target, add \[1, 1, 6] to result                                                                                                 |
| 0     | 1         | \[1]                 | 1           | Continue exploring other combinations from the first `1`                                                                                |
| ...   | ...       | ...                  | ...         | ...                                                                                                                                      |
| 1     | 2         | \[1, 2]              | 3           | Add 2, explore further combinations                                                                                                      |
| 2     | 5         | \[1, 2, 5]           | 8           | Sum == Target, add \[1, 2, 5] to result                                                                                                 |
| 3     | 7         | \[1, 7]              | 8           | Sum == Target, add \[1, 7] to result                                                                                                 |
| 4     | 2         | \[2]              | 2           | Start combination with 2, explore further combinations                                                                                                      |
| 5     | 6         | \[2, 6]              | 8           | Sum == Target, add \[2, 6] to result                                                                                                 |

## 🚀 **Time & Space Complexity**

*   **Time Complexity:**  **O(2<sup>n</sup>)** in the worst case, where *n* is the number of candidates. This is because, in the worst-case scenario, the algorithm may explore all possible combinations. However, the pruning due to the sorted array and skipping duplicates improves performance in practice.
*   **Space Complexity:** **O(n)** on average, where *n* is the depth of the recursion tree, which can be at most *n*. Additionally, we use **O(k)** space to store a combination of average length *k*, and since the result stores all unique combinations, the space complexity could potentially be higher if there are many combinations.
    