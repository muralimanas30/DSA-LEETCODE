# 03143 - Longest Unequal Adjacent Groups Subsequence I
    
**Language:** Java  
**Runtime:** 1 ms (Beats 96.15% of users)  
**Memory:** 44.8 MB (Beats 77.53% of users)  

## 💡 **Problem Explanation**

The problem asks us to find the longest subsequence of words from a given array `words`, such that no two adjacent words in the subsequence belong to the same group, as specified by the `groups` array. Essentially, we need to filter the `words` array based on the `groups` array, ensuring that adjacent elements in the resulting subsequence have different group IDs.

**Example:**

**Input:** `words = ["a", "b", "c", "d"]`, `groups = [1, 0, 1, 1]`
**Output:** `["a", "b", "c"]`

*   We start with "a" (group 1).
*   Then, we can add "b" (group 0) since it's different from the last group (1).
*   Next, we add "c" (group 1) since it's different from the last group (0).
*   Finally, we skip "d" (group 1) because it's the same as the last group (1).

**Input:** `words = ["aa","bb","cc","dd","ee"]`, `groups = [0,0,1,1,0]`
**Output:** `["aa","cc","ee"]`

*   We start with "aa" (group 0).
*   "bb" is skipped because it's also group 0
*   "cc" is added since it's group 1
*   "dd" is skipped since it's also group 1
*   "ee" is added since it's group 0
## 📊 **Algorithm**

*   Initialize an empty list called `result` to store the longest subsequence.
*   If the input `words` array is empty, return the empty `result` list.
*   Add the first word from the `words` array to the `result` list.
*   Store the group ID of the first word in a variable called `lastGroup`.
*   Iterate through the remaining words in the `words` array, starting from the second word.
*   For each word, check if its group ID is different from `lastGroup`.
*   If the group ID is different, add the word to the `result` list and update `lastGroup` with the current word's group ID.
*   After iterating through all the words, return the `result` list.

## 🔥 **Code Implementation**

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> result = new ArrayList<>();
        if (words.length == 0) return result;

        result.add(words[0]);
        int lastGroup = groups[0];

        for (int i = 1; i < words.length; i++) {
            if (groups[i] != lastGroup) {
                result.add(words[i]);
                lastGroup = groups[i];
            }
        }

        return result;
    }
}
```

## 📊 **ASCII Representation**

This problem doesn't directly involve grids, trees or movements in a 2D space, so an ASCII representation isn't the most suitable way to visualize it.

## 📊 **WORKING**

Let's trace the execution with the input `words = ["aa","bb","cc","dd","ee"]`, `groups = [0,0,1,1,0]`:

1.  `result = []`
2.  `words.length` is 5, so we don't return.
3.  `result.add("aa")`, so `result = ["aa"]`
4.  `lastGroup = groups[0] = 0`
5.  Loop starts from `i = 1`:
    *   `i = 1`: `groups[1] = 0`.  `groups[1] != lastGroup` is `0 != 0`, which is `false`.  We skip "bb".
    *   `i = 2`: `groups[2] = 1`.  `groups[2] != lastGroup` is `1 != 0`, which is `true`.  `result.add("cc")`, so `result = ["aa", "cc"]`. `lastGroup = 1`.
    *   `i = 3`: `groups[3] = 1`.  `groups[3] != lastGroup` is `1 != 1`, which is `false`. We skip "dd".
    *   `i = 4`: `groups[4] = 0`.  `groups[4] != lastGroup` is `0 != 1`, which is `true`.  `result.add("ee")`, so `result = ["aa", "cc", "ee"]`. `lastGroup = 0`.
6.  Loop finishes.
7.  Return `result`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The algorithm iterates through the input arrays `words` and `groups` once. Therefore, the time complexity is **O(n)**, where n is the length of the `words` array.
*   **Space Complexity:** The algorithm uses a list called `result` to store the subsequence. In the worst case, the length of the subsequence can be equal to the length of the input array `words`. Therefore, the space complexity is **O(n)**, where n is the length of the `words` array.
    