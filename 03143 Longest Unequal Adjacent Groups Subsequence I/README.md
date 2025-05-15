# 03143 - Longest Unequal Adjacent Groups Subsequence I
    
**Language:** Java  
**Runtime:** 1 ms (Beats 96.15% of users)  
**Memory:** 44.8 MB (Beats 77.53% of users)  

## 💡 **Problem Explanation**

The problem asks us to find the longest subsequence of words from a given array, such that no two adjacent words in the subsequence belong to the same group.  We are given an array of `words` and a corresponding array of `groups` where `groups[i]` represents the group that `words[i]` belongs to.

**Example:**

```
words = ["a", "b", "c", "d"]
groups = [1, 0, 1, 1]

Output: ["a", "b", "c"]

Explanation:
- "a" (group 1) can be the first word in the subsequence.
- "b" (group 0) can follow "a" since their groups are different.
- "c" (group 1) can follow "b" since their groups are different.
- "d" (group 1) cannot follow "c" since they belong to the same group.
```

Another Example:

```
words = ["a", "b", "c", "d", "e"]
groups = [1, 0, 0, 1, 0]

Output: ["a", "b", "d", "e"] or ["a", "b", "d"]
```

## 📊 **Algorithm**

*   Initialize an empty list `result` to store the subsequence.
*   Add the first word from the `words` array to the `result` list.
*   Store the group of the first word in `lastGroup`.
*   Iterate through the remaining words and their groups.
*   If the current group is different from `lastGroup`, add the current word to the `result` list and update `lastGroup`.
*   Return the `result` list.

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

## 🚀 **Time & Space Complexity**

The **time complexity** of the `getLongestSubsequence` method is **O(n)**, where n is the length of the `words` array. This is because we iterate through the array once.

The **space complexity** of the `getLongestSubsequence` method is **O(n)** in the worst case, where n is the length of the `words` array.  This occurs when all the groups alternate, and the `result` list stores almost all the words. In the best case (where all groups are same), space complexity can be O(1) since only the first word will be added to the list.
    