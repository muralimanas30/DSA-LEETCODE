# 01993 - Sum Of All Subset Xor Totals
    
**Language:** Java  
**Runtime:** 8 ms (Beats 14.51% of users)  
**Memory:** 41.9 MB (Beats 17.63% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title                        | 🔗 Link                                                                  |
| ------------------ | ------------------------------- | ------------------------------------------------------------------------- |
| 1993               | SUM OF ALL SUBSET XOR TOTALS | [LeetCode Problem](https://leetcode.com/problems/sum-of-all-subset-xor-totals/) |

---

## 💡 **Problem Explanation**

Given an integer array `nums`, calculate the sum of XOR totals for every subset of `nums`.

The XOR total of a subset is defined as the bitwise `XOR` of all its elements, or `0` if the subset is empty.

For example, the XOR total of the array `[2,5,6]` is `2 XOR 5 XOR 6 = 1 XOR 6 = 7`.

Consider the input `nums = [5, 1, 6]`. The subsets and their XOR totals are:

- `[]`: 0
- `[5]`: 5
- `[1]`: 1
- `[6]`: 6
- `[5, 1]`: 5 XOR 1 = 4
- `[5, 6]`: 5 XOR 6 = 3
- `[1, 6]`: 1 XOR 6 = 7
- `[5, 1, 6]`: 5 XOR 1 XOR 6 = 2

The sum of these XOR totals is `0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28`.

---

## 📊 **Algorithm**

*   Initialize a `HashMap` called `dp` to store the results of subproblems for memoization.
*   Call the `backtrack` helper function with the initial index `0`, initial XOR result `0`, the input array `nums`, and the `dp` map.
*   In the `backtrack` function:
    *   If the `index` reaches the end of the array, return the current `res` (XOR total).
    *   Create a key using the current `index` and `res` for memoization.
    *   If the key exists in `dp`, return the stored value.
    *   Recursively call `backtrack` twice:
        *   `consider`: Include the current element in the XOR total by performing `res ^ nums[index]`.
        *   `notConsider`: Exclude the current element from the XOR total by keeping `res` as is.
    *   Sum the results of `consider` and `notConsider`.
    *   Store the sum in the `dp` map with the current key and return the sum.

## 🔥 **Code Implementation**

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subsetXORSum(int[] nums) {
        Map<String, Integer> dp = new HashMap<>();
        return backtrack(nums, 0, dp, 0);
    }

    private int backtrack(int[] nums, int index, Map<String, Integer> dp, int res) {
        if (index == nums.length) {
            return res;
        }

        String key = index + "," + res;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int consider = backtrack(nums, index + 1, dp, res ^ nums[index]);
        int notConsider = backtrack(nums, index + 1, dp, res);

        int total = consider + notConsider;
        dp.put(key, total);
        return total;
    }
}
```

## 📊 **ASCII Representation**

N/A (This problem does not involve grids, trees, or movements)

## 📊 **WORKING**

Let's trace the execution with `nums = [5, 1, 6]`:

1.  `subsetXORSum([5, 1, 6])` calls `backtrack([5, 1, 6], 0, {}, 0)`
2.  `backtrack(0, 0)`:
    *   `consider`: `backtrack(1, 5)`
    *   `notConsider`: `backtrack(1, 0)`
3.  `backtrack(1, 5)`:
    *   `consider`: `backtrack(2, 5 ^ 1 = 4)`
    *   `notConsider`: `backtrack(2, 5)`
4.  `backtrack(1, 0)`:
    *   `consider`: `backtrack(2, 0 ^ 1 = 1)`
    *   `notConsider`: `backtrack(2, 0)`
5.  `backtrack(2, 4)`:
    *   `consider`: `backtrack(3, 4 ^ 6 = 2)`
    *   `notConsider`: `backtrack(3, 4)`
6.  `backtrack(2, 5)`:
    *   `consider`: `backtrack(3, 5 ^ 6 = 3)`
    *   `notConsider`: `backtrack(3, 5)`
7. `backtrack(2, 1)`:
    *   `consider`: `backtrack(3, 1 ^ 6 = 7)`
    *   `notConsider`: `backtrack(3, 1)`
8. `backtrack(2, 0)`:
    *   `consider`: `backtrack(3, 0 ^ 6 = 6)`
    *   `notConsider`: `backtrack(3, 0)`

9. `backtrack(3, 2)` returns 2
10. `backtrack(3, 4)` returns 4
11. `backtrack(3, 3)` returns 3
12. `backtrack(3, 5)` returns 5
13. `backtrack(3, 7)` returns 7
14. `backtrack(3, 1)` returns 1
15. `backtrack(3, 6)` returns 6
16. `backtrack(3, 0)` returns 0

The function utilizes memoization (`dp`) to store intermediate results, avoiding redundant calculations.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:**  O(N), where N is the length of the input array nums, because each element is considered for inclusion or exclusion in a subset once. The memoization avoids redundant calculations.
*   **Space Complexity:** O(N), where N is the length of the input array nums, because the maximum depth of the recursion stack is N and the size of the `dp` map can grow up to N.
    