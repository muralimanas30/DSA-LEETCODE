# 00010 - Regular Expression Matching
    
**Language:** Java  
**Runtime:** 15 ms (Beats 27.76% of users)  
**Memory:** 44.6 MB (Beats 12.31% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 10 | Regular Expression Matching | [LeetCode Problem](https://leetcode.com/problems/regular-expression-matching/) |

---

## 💡 **Problem Explanation**

The goal is to implement regular expression matching with support for '.' and '*' where:

*   '.' Matches any single character.
*   '\*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

**Examples:**

*   `s = "aa", p = "a"`  → `false` (because "a" doesn't match the entire string "aa")
*   `s = "aa", p = "a*"` → `true` (because '\*' means zero or more of preceding element, 'a'. Thus, by repeating 'a' once, it becomes "aa".)
*   `s = "ab", p = ".*"` → `true` (because ".\*" means "zero or more (*) of any character (.)")

---

## 📊 **Algorithm**

*   Initialize a 2D boolean array `dp` where `dp[i][j]` will store if `s[0...i-1]` matches `p[0...j-1]`.
*   Base case: `dp[0][0] = true` (empty string matches empty pattern).
*   Handle patterns starting with `*`.
*   Iterate through the string `s` and pattern `p`.
*   If `p[j-1]` is `.` or `s[i-1]`, then `dp[i][j] = dp[i-1][j-1]`.
*   If `p[j-1]` is `*`:
    *   If the preceding character of `p` (i.e., `p[j-2]`) does not match the character `s[i-1]`, then `dp[i][j] = dp[i][j-2]` (treat `a*` as empty).
    *   If the preceding character of `p` (i.e., `p[j-2]`) matches the character `s[i-1]` or is `.`, then `dp[i][j] = dp[i][j-2] || dp[i-1][j]` (treat `a*` as either empty or multiple `a`s).
*   If `p[j-1]` is neither `.` nor `*` and doesn't match `s[i-1]`, then `dp[i][j] = false`.
*   Return `dp[s.length][p.length]`.

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

class Solution {
    public boolean isMatch(String s, String p) {

        char[] str = s.toCharArray();
        char[] ptr = p.toCharArray();

        boolean[][] dp = new boolean[str.length + 1][ptr.length + 1];
        for (int i = 0; i <= ptr.length; i++)
            dp[0][i] = false;
        for (int i = 0; i <= str.length; i++)
            dp[i][0] = false;
        dp[0][0] = true;

        for (int j = 2; j <= ptr.length; j++) {
            if (ptr[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= str.length; i++) {
            for (int j = 1; j <= ptr.length; j++) {
                int strIndex = i - 1;
                int ptrIndex = j - 1;

                if (ptr[ptrIndex] == '.' || ptr[ptrIndex] == str[strIndex]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (ptr[ptrIndex] == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (ptrIndex - 1 >= 0 && (ptr[ptrIndex - 1] == '.' || ptr[ptrIndex - 1] == str[strIndex])) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        for (boolean[] a : dp)
            System.out.println(Arrays.toString(a));
        return dp[str.length][ptr.length];
    }
}
```

## 📊 **TABLE Representation**

Let's consider `s = "aab"` and `p = "c*a*b"` to visualize the `dp` table:

|       |   | c | * | a | * | b |
| :---- | :-: | :-: | :-: | :-: | :-: | :-: |
|   | T | F | T | F | T | F |
| a | F | F | F | T | T | F |
| a | F | F | F | F | T | F |
| b | F | F | F | F | F | T |

## 📊 **WORKING**

Let's trace the `dp` table construction for `s = "aab"` and `p = "c*a*b"`:

1.  **Initialization**:

    *   `dp[0][0] = true` (empty string matches empty pattern)
    *   `dp[0][2] = true` (c\*)
    *   `dp[0][4] = true` (c\*a\*)
2.  **First row `s = "a"`**:

    *   `p = "c"`: `dp[1][1] = false`
    *   `p = "c*"`: `dp[1][2] = false`
    *   `p = "c*a"`: `dp[1][3] = true`
    *   `p = "c*a*"`: `dp[1][4] = true`
    *   `p = "c*a*b"`: `dp[1][5] = false`
3.  **Second row `s = "aa"`**:

    *   `p = "c*a*"`: `dp[2][4] = true`
4.  **Third row `s = "aab"`**:

    *   `p = "c*a*b"`: `dp[3][5] = true`

Therefore the final result is `true`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(m \* n)**, where `m` is the length of the string `s` and `n` is the length of the pattern `p`, due to the nested loops to fill the `dp` table.
*   **Space Complexity:** **O(m \* n)**, to store the `dp` table.
    