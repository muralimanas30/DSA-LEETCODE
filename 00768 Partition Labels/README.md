# 00768 - Partition Labels
    
**Language:** Java  
**Runtime:** 5 ms (Beats 79.31% of users)  
**Memory:** 42.3 MB (Beats 60.91% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 763 | Partition Labels | [LeetCode Problem](https://leetcode.com/problems/partition-labels/) |

---

## 💡 **Problem Explanation**

You are given a string `s`. You need to partition this string into as many parts as possible such that each letter appears in at most one part.  Return a list of integers representing the size of these parts.

**Example:**

```
Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
```

The goal is to find the largest possible chunks where each character appears only in that chunk.

## 📊 **Algorithm**

*   Create an array to store the last occurrence of each character in the string.
*   Iterate through the string:
    *   Keep track of the maximum last occurrence seen so far.
    *   If the current index matches the maximum last occurrence, it means we've reached the end of a partition.
    *   Add the size of the partition to the result list.

## 🔥 **Code Implementation**

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] lastOccurrence = new int[26];
        List<Integer> partitionSizes = new ArrayList<>();
        
        // Store the last index of each character
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }

        int maxLastIndex = 0, lastPartitionEnd = -1;

        // Iterate through the string to find partitions
        for (int i = 0; i < s.length(); i++) {
            maxLastIndex = Math.max(maxLastIndex, lastOccurrence[s.charAt(i) - 'a']);
            
            // If we've reached the end of a partition
            if (i == maxLastIndex) {
                partitionSizes.add(i - lastPartitionEnd);
                lastPartitionEnd = i;
            }
        }

        return partitionSizes;
    }
}
```

## 📊 **ASCII Representation**

This problem doesn't directly involve grids or trees, so an ASCII representation isn't the most relevant visualization. However, we can conceptualize it as identifying segments within a line (the string).

## 📊 **WORKING**

Let's trace the execution with the example input `s = "ababcbacadefegdehijhklij"`:

1.  **`lastOccurrence` Initialization**:

    The `lastOccurrence` array stores the last index of each character. For example:

    *   `lastOccurrence['a' - 'a'] = 8`
    *   `lastOccurrence['b' - 'a'] = 5`
    *   `lastOccurrence['c' - 'a'] = 7`
    *   `lastOccurrence['d' - 'a'] = 14`
    *   `lastOccurrence['e' - 'a'] = 15`
    *   ... and so on.

2.  **Iteration and Partitioning**:

    *   `i = 0`, `s.charAt(i) = 'a'`, `maxLastIndex = 8`
    *   `i = 1`, `s.charAt(i) = 'b'`, `maxLastIndex = 8`
    *   ...
    *   `i = 8`, `s.charAt(i) = 'a'`, `maxLastIndex = 8`. Now, `i == maxLastIndex`.  A partition is found.  `partitionSizes.add(8 - (-1)) = 9`.  `lastPartitionEnd = 8`.

    *   `i = 9`, `s.charAt(i) = 'd'`, `maxLastIndex = 14`
    *   `i = 10`, `s.charAt(i) = 'e'`, `maxLastIndex = 15`
    *   ...
    *   `i = 15`, `s.charAt(i) = 'e'`, `maxLastIndex = 15`. Now, `i == maxLastIndex`. A partition is found.  `partitionSizes.add(15 - 8) = 7`. `lastPartitionEnd = 15`.

    *   `i = 16`, `s.charAt(i) = 'h'`, `maxLastIndex = 22`
    *   ...
    *   `i = 22`, `s.charAt(i) = 'j'`, `maxLastIndex = 22`. Now, `i == maxLastIndex`. A partition is found. `partitionSizes.add(22 - 15) = 8`.  `lastPartitionEnd = 22`.

3.  **Result**:

    The final result is `[9, 7, 8]`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(N)**, where N is the length of the string `s`. We iterate through the string twice (once to populate `lastOccurrence` and once to find the partitions).
*   **Space Complexity:** **O(1)** because the `lastOccurrence` array has a fixed size of 26 (for the English alphabet), regardless of the input string's length.
    