# 02215 - Finding 3 Digit Even Numbers
    
**Language:** Java  
**Runtime:** 7 ms (Beats 72.59% of users)  
**Memory:** 44.7 MB (Beats 82.46% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 2215 | Finding 3-Digit Even Numbers | [LeetCode Problem](https://leetcode.com/problems/finding-3-digit-even-numbers/) |

---

## 💡 **Problem Explanation**

Given an array of digits, `digits`, return an array of all the unique integers that follow the following criteria:

*   The integer consists of three digits.
*   The integer is even.
*   Each digit of the integer is in the `digits` array.

Return the integers in increasing order.

**Example 1:**

*   **Input:** `digits = [2,1,3,0]`
*   **Output:** `[102,120,130,210,230,302,310]`

**Example 2:**

*   **Input:** `digits = [2,2,8,8,2]`
*   **Output:** `[222,228,282,288,822,828,882,888]`

---

## 📊 **Algorithm**

*   Create a `digitCount` array to store the frequency of each digit in the input `digits` array.
*   Initialize an empty list, `result`, to store the valid 3-digit even numbers.
*   Iterate through all possible 3-digit even numbers from 100 to 998 (inclusive) with a step of 2.
*   For each number, extract its digits `d1`, `d2`, and `d3`.
*   Decrement the count of these digits in the `digitCount` array.
*   Check if all three digits are available (i.e., their counts are non-negative).
*   If all digits are available, add the number to the `result` list.
*   Restore the counts of the digits in the `digitCount` array.
*   Convert the `result` list to an integer array and return it.

## 🔥 **Code Implementation**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int[] digitCount = new int[10];
        for (int d : digits) digitCount[d]++;
        
        List<Integer> result = new ArrayList<>();
        
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;
            int d2 = (num / 10) % 10;
            int d3 = num % 10;
            
            digitCount[d1]--; digitCount[d2]--; digitCount[d3]--;
            
            if (digitCount[d1] >= 0 && digitCount[d2] >= 0 && digitCount[d3] >= 0) {
                result.add(num);
            }
            
            digitCount[d1]++; digitCount[d2]++; digitCount[d3]++;
        }

        int[] resArr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) resArr[i] = result.get(i);
        return resArr;
    }
}
```

## 📊 **ASCII Representation**

This problem doesn't directly involve grids, trees, or movements that would benefit from an ASCII representation. It's primarily a counting and digit manipulation problem.

## 📊 **WORKING**

Let's consider `digits = [2,1,3,0]`

1.  **Count Digits:**
    `digitCount = [1, 1, 1, 1, 0, 0, 0, 0, 0, 0]`
    (Meaning one `0`, one `1`, one `2`, one `3`)

2.  **Iterate through Even Numbers (100 to 998):**

    *   **num = 100:**
        *   `d1 = 1`, `d2 = 0`, `d3 = 0`
        *   Decrement `digitCount`: `[0, 0, 1, 1, 0, 0, 0, 0, 0, 0]` (Not enough `0`s)
        *   Increment `digitCount` (restore)
    *   **num = 102:**
        *   `d1 = 1`, `d2 = 0`, `d3 = 2`
        *   Decrement `digitCount`: `[0, 0, 0, 1, 0, 0, 0, 0, 0, 0]`
        *   All digits are available. Add 102 to the result.
        *   Increment `digitCount` (restore)
    *   The process continues for each even number...
3.  **Final Result:** The `result` list will contain `[102, 120, 130, 210, 230, 302, 310]`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The code iterates through all 3-digit even numbers from 100 to 998, which is a constant number of iterations. The digit extraction and count updates are also constant-time operations. Therefore, the overall time complexity is **O(1)**, or constant time.

*   **Space Complexity:** The `digitCount` array has a fixed size of 10, and the `result` list stores the valid numbers. In the worst case, the number of valid numbers can be significant, but since we're forming 3-digit numbers from the given digits, its length will be at most equal to number of all permutations of input `digits`, therefore the space complexity is **O(1)**, as its size is independent from input size. The integer array `resArr` stores the content of result and has same space complexity as the result variable.
    