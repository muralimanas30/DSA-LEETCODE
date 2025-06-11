# 00091 - Decode Ways
    
**Language:** Java  
**Runtime:** 0 ms (Beats 100.00% of users)  
**Memory:** 41.6 MB (Beats 87.31% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 91 | Decode Ways | [LeetCode Problem](https://leetcode.com/problems/decode-ways/) |

---

## 💡 **Problem Explanation**

The "Decode Ways" problem asks you to determine the number of ways a given string of digits can be decoded as a sequence of letters. Each digit maps to a letter: 'A' maps to 1, 'B' to 2, ..., 'Z' to 26.  The challenge arises from the fact that two digits can potentially be combined to represent a single letter (e.g., "12" can be decoded as "AB" or "L").

**Example:**

*   **Input:** `s = "12"`
*   **Output:** `2`  ("AB" or "L")

*   **Input:** `s = "226"`
*   **Output:** `3` ("BZ", "VF", "BBF")

*   **Input:** `s = "06"`
*   **Output:** `0` (because "06" cannot be mapped to any valid character since '0' is invalid. Leading zero is invalid)

---

## 📊 **Algorithm**

*   Initialize a `dp` array to store the number of ways to decode the string up to each index. This array will use memoization to avoid redundant computations.
*   Implement a recursive function `decode(arr, dp, idx)` that takes the character array `arr`, the `dp` array, and the current index `idx` as input.
*   **Base Cases:**
    *   If `idx` reaches the end of the array, return 1 (one valid decoding).
    *   If the current character is '0', return 0 (invalid decoding).
    *   If `dp[idx]` is already computed, return its value.
*   Recursively calculate the number of ways by decoding the next single digit (`decode(arr, dp, idx + 1)`).
*   If possible, recursively calculate the number of ways by decoding the next two digits if they form a number between 10 and 26 (inclusive) and add it to the total ways (`decode(arr, dp, idx + 2)`).
*   Store the result in `dp[idx]` and return it.

---

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

class Solution {

    public int numDecodings(String s) {
        char[] arr = s.toCharArray();
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        return decode(arr, dp, 0);
    }

    public int decode(char[] arr, int[] dp, int idx) {
        if (idx == arr.length) return 1;
        if (arr[idx] == '0') return 0;
        if (dp[idx] != -1) return dp[idx];

        int ways = decode(arr, dp, idx + 1);

        if (idx + 1 < arr.length) {
            int num = (arr[idx] - '0') * 10 + (arr[idx + 1] - '0');
            if (num >= 10 && num <= 26) {
                ways += decode(arr, dp, idx + 2);
            }
        }

        dp[idx] = ways;
        return dp[idx];
    }
}
```

---

## 📊 **ASCII Representation**

This problem doesn't directly involve grids or trees.

---

## 📊 **TABLE Representation**

Let's consider the input string `s = "226"`.  The `dp` array will store the number of ways to decode the string starting from each index.

| Index | Character | `dp` Value | Explanation                                                                         |
|-------|-----------|------------|-------------------------------------------------------------------------------------|
| 0     | 2         | 3          | 2 can be decoded alone (B), or with the next character (22 = V)                     |
| 1     | 2         | 2          | 2 can be decoded alone (B), or with the next character (26 = Z)                     |
| 2     | 6         | 1          | 6 can only be decoded alone (F)                                                     |

---

## 📊 **WORKING**

Let's trace the `decode` function for the input `s = "226"`:

1. `decode(arr, dp, 0)`:
   *   `arr[0]` is '2', not '0'.
   *   `dp[0]` is -1 (not computed yet).
   *   `ways = decode(arr, dp, 1)`
2. `decode(arr, dp, 1)`:
   *   `arr[1]` is '2', not '0'.
   *   `dp[1]` is -1 (not computed yet).
   *   `ways = decode(arr, dp, 2)`
3. `decode(arr, dp, 2)`:
   *   `arr[2]` is '6', not '0'.
   *   `dp[2]` is -1 (not computed yet).
   *   `ways = decode(arr, dp, 3)`
4. `decode(arr, dp, 3)`:
   *   `idx == arr.length`, return 1.
   *   `dp[2] = 1` (ways to decode "6" is 1: "F").
   *   Return 1.
5. Back in `decode(arr, dp, 1)`:
   *   `ways = 1`
   *   `idx + 1 < arr.length` is true (1 < 3).
   *   `num = 26`. `num >= 10 && num <= 26` is true.
   *   `ways += decode(arr, dp, 3)`
   *   `decode(arr, dp, 3)` returns 1
   *   `ways = 1 + 1 = 2`.
   *   `dp[1] = 2` (ways to decode "26" is 2: "BF", "Z")
   *   Return 2.
6. Back in `decode(arr, dp, 0)`:
   *   `ways = 2`
   *   `idx + 1 < arr.length` is true (0 < 3).
   *   `num = 22`. `num >= 10 && num <= 26` is true.
   *   `ways += decode(arr, dp, 2)`
   *   `decode(arr, dp, 2)` returns 1
   *   `ways = 2 + 1 = 3`.
   *   `dp[0] = 3` (ways to decode "226" is 3: "BZ", "VF", "BBF").
   *   Return 3.

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:**  **O(N)**, where N is the length of the string `s`, due to memoization.  Each index is visited at most once.
*   **Space Complexity:** **O(N)**, for the `dp` array and the recursion call stack.
    