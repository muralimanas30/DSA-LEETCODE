# 00768 - Partition Labels
    
**Language:** Java  
**Runtime:** 5 ms (Beats 79.31% of users)  
**Memory:** 42 MB (Beats 80.49% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 768 | PARTITION LABELS | [LeetCode Problem](https://leetcode.com/problems/partition-labels/) |

---

## 💡 **Problem Explanation**

Given a string `s` consisting of lowercase English letters, partition `s` into as many parts as possible such that each letter appears in at most one part. Return a list of integers representing the size of these parts.

**Example 1:**
```
Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
```

**Example 2:**
```
Input: s = "eccbbbbdec"
Output: [10]
```

## 📊 **Algorithm**
* Initialize an array to store the last occurrence of each character in the string.
* Iterate through the string to populate the last occurrence array.
* Initialize variables to track the maximum last index and the end of the last partition.
* Iterate through the string again:
    * Update the maximum last index seen so far.
    * If the current index matches the maximum last index, it means we've reached the end of a partition.
    * Record the size of the partition and update the last partition end.
* Return the list of partition sizes.

## 🔥 **Code Implementation**

```java
class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] lastOccurrence = new int[26];
        List<Integer> partitionSizes = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }

        int maxLastIndex = 0, lastPartitionEnd = -1;

        for (int i = 0; i < s.length(); i++) {
            maxLastIndex = Math.max(maxLastIndex, lastOccurrence[s.charAt(i) - 'a']);
            
            if (i == maxLastIndex) {
                partitionSizes.add(i - lastPartitionEnd);
                lastPartitionEnd = i;
            }
        }

        return partitionSizes;
    }
}

/*
   Optimized Solution:
   - Uses an `int[26]` instead of a HashMap to store the last occurrence of each character.
   - Reduces storage overhead and improves lookup speed.
   - Iterates through the string twice (O(N) time complexity).
   - Uses O(1) extra space (apart from the output list).
   - Efficient, space-optimized, and fast!
*/
```

## 📊 **ASCII Representation**
N/A (This problem doesn't involve grids or trees.)

## 📊 **WORKING**

Let's walk through the example `s = "ababcbacadefegdehijhklij"`.

1.  **Last Occurrence Array:**
    After the first loop, `lastOccurrence` will contain the last index of each character:

    ```
    a: 8, b: 5, c: 7, d: 14, e: 15, f: 11, g: 13, h: 19, i: 22, j: 23, k: 20, l: 21
    ```

2.  **Partitioning:**

    *   **i = 0:** `maxLastIndex = 8` (last occurrence of 'a').
    *   **i = 1:** `maxLastIndex = max(8, 5) = 8`.
    *   ...
    *   **i = 7:** `maxLastIndex = max(8, 7) = 8`.
    *   **i = 8:** `maxLastIndex = max(8, 8) = 8`. `i == maxLastIndex`.  Partition size = `8 - (-1) = 9`. `lastPartitionEnd = 8`.  `partitionSizes = [9]`.
    *   **i = 9:** `maxLastIndex = 14` (last occurrence of 'd').
    *   ...
    *   **i = 15:** `maxLastIndex = 15`. `i == maxLastIndex`. Partition size = `15 - 8 = 7`. `lastPartitionEnd = 15`. `partitionSizes = [9, 7]`.
    *   **i = 16:** `maxLastIndex = 22` (last occurrence of 'h').
    *   ...
    *   **i = 23:** `maxLastIndex = 23`. `i == maxLastIndex`. Partition size = `23 - 15 = 8`. `lastPartitionEnd = 23`. `partitionSizes = [9, 7, 8]`.

    Final Result: `[9, 7, 8]`

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(N)**, where N is the length of the string `s`.  We iterate through the string twice.
*   **Space Complexity:** **O(1)**, as the `lastOccurrence` array has a fixed size of 26, independent of the input string length.  The space used by the output `partitionSizes` list is not considered auxiliary space.
    