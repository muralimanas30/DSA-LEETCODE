# 00010 - Regular Expression Matching
    
**Language:** Java  
**Runtime:** 15 ms (Beats 27.76% of users)  
**Memory:** 44.6 MB (Beats 12.31% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title                      | 🔗 Link                                                                    |
|------------------|-------------------------------|----------------------------------------------------------------------------|
| 10               | Regular Expression Matching | [LeetCode Problem](https://leetcode.com/problems/regular-expression-matching/) |

---

## 💡 **Problem Explanation**

Given an input string `s` and a pattern `p`, implement regular expression matching with support for `.` and `*`.

*   `.` Matches any single character.
*   `*` Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

**Examples:**

1.  **Input:** `s = "aa", p = "a"`
    **Output:** `false`
    **Explanation:** "a" does not match the entire string "aa".

2.  **Input:** `s = "aa", p = "a*"`
    **Output:** `true`
    **Explanation:** '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

3.  **Input:** `s = "ab", p = ".*"`
    **Output:** `true`
    **Explanation:** ".*" means "zero or more (*) of any character (.)".

---

## 📊 **Algorithm**

*   Initialize a 2D boolean array `dp` of size `(s.length() + 1) x (p.length() + 1)` to store matching results. `dp[i][j]` represents whether the first `i` characters of `s` match the first `j` characters of `p`.
*   Set `dp[0][0]` to `true` because an empty string matches an empty pattern.
*   Handle the case when `s` is empty and `p` contains `*`. If `p[j-1]` is `*`, then `dp[0][j]` is `dp[0][j-2]` (zero occurrences of the preceding character).
*   Iterate through the `dp` array starting from index 1. For each `s[i-1]` and `p[j-1]`:
    *   If `p[j-1]` is `.` or `p[j-1]` is equal to `s[i-1]`, then `dp[i][j] = dp[i-1][j-1]`.
    *   If `p[j-1]` is `*`:
        *   `dp[i][j] = dp[i][j-2]` (zero occurrences).
        *   If the preceding character of `*` in `p` matches `s[i-1]` or is `.`, then `dp[i][j] = dp[i][j] || dp[i-1][j]` (one or more occurrences).
    *   Otherwise, `dp[i][j] = false`.
*   Return `dp[s.length()][p.length()]`.

---

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

## 📊 **ASCII Representation**

Let's consider s = "aab" and p = "c*a*b"

The `dp` table is built as follows:

```
      ""  c   *   a   *   b
""  T   F   T   F   T   F
a   F   F   F   T   T   F
a   F   F   F   F   T   F
b   F   F   F   F   F   T

```

## 📊 **WORKING**

Consider `s = "aab"` and `p = "c*a*b"`

1.  **Initialization:** `dp[0][0] = true` (Empty string matches empty pattern)
2.  **Handling `*` in pattern:**
    *   `p[1] == 'c'`, `p[2] == '*'`, then `dp[0][2] = dp[0][0] = true` (Zero 'c's)
    *   `p[3] == 'a'`, `p[4] == '*'`, then `dp[0][4] = dp[0][2] = true` (Zero 'a's)
3.  **Filling the table:**

    *   `s[0] = 'a'`, `p[0] = 'c'`, no match. `dp[1][1] = false`
    *   `s[0] = 'a'`, `p[1] = '*'`, `p[-1] not exist` so, `dp[1][2] = dp[1][0] = false`
    *   `s[0] = 'a'`, `p[2] = 'a'`, match, `dp[1][3] = dp[0][2] = true`
    *   `s[0] = 'a'`, `p[3] = '*'`, `dp[1][4] = dp[1][2] || dp[0][4] = false`

    Continue filling the table similarly.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(m * n)**, where `m` is the length of the input string `s` and `n` is the length of the pattern `p`. This is due to the nested loops used to fill the `dp` table.
*   **Space Complexity:** **O(m * n)**, as we are using a 2D boolean array `dp` of size `(m+1) x (n+1)` to store the intermediate matching results.
    