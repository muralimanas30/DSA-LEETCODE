# 00010 - Regular Expression Matching
    
**Language:** Java  
**Runtime:** 15 ms (Beats 27.76% of users)  
**Memory:** 44.6 MB (Beats 12.31% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 10 | REGULAR EXPRESSION MATCHING | [LeetCode Problem](https://leetcode.com/problems/regular-expression-matching/) |

---

## 💡 **Problem Explanation**

The "Regular Expression Matching" problem asks you to implement regular expression matching with support for '.' and '*' where:

*   '.' Matches any single character.
*   '\*' Matches zero or more of the preceding element.

The matching should cover the **entire** input string (not partial).

**Example 1:**

```
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```

**Example 2:**

```
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
```

**Example 3:**

```
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
```

## 📊 **Algorithm**

*   Create a 2D boolean array `dp` of size `(s.length() + 1) x (p.length() + 1)` to store the matching results. `dp[i][j]` represents whether the first `i` characters of `s` match the first `j` characters of `p`.
*   Initialize `dp[0][0]` to `true` because an empty string matches an empty pattern.
*   Handle the case where `p` starts with `a*`, `b*`, `c*`... by initializing the first row of `dp`.  If `p[j-1]` is `*`, then `dp[0][j] = dp[0][j-2]` which means zero occurrences of the preceding character.
*   Iterate through the `dp` array starting from index `(1, 1)`.
    *   If `p[j-1]` is `.` or `p[j-1]` is equal to `s[i-1]`, then `dp[i][j] = dp[i-1][j-1]` because the current characters match, so the result depends on whether the previous characters matched.
    *   If `p[j-1]` is `*`, then there are two possibilities:
        *   Zero occurrences of the preceding character: `dp[i][j] = dp[i][j-2]`
        *   One or more occurrences of the preceding character: If `p[j-2]` is `.` or `p[j-2]` is equal to `s[i-1]`, then `dp[i][j] = dp[i][j] || dp[i-1][j]`
    *   If `p[j-1]` is neither `.`, `*`, nor equal to `s[i-1]`, then `dp[i][j] = false`.
*   Return `dp[s.length()][p.length()]`, which represents whether the entire string `s` matches the entire pattern `p`.

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

Let's consider the input `s = "aab"` and `p = "c*a*b"` to visualize the DP table.
```
      |   | c | * | a | * | b |
    --+---+---+---+---+---+---+
    ""| T | F | T | F | T | F |
    a | F | F | F | T | T | F |
    a | F | F | F | F | T | F |
    b | F | F | F | F | F | T |
```

## 📊 **WORKING**

Let's trace the execution with `s = "aab"` and `p = "c*a*b"`:

1.  **Initialization:** `dp[0][0] = true`. The first row is initialized based on the pattern `p`. Since `p = "c*a*b"`,  `dp[0][2]`, and `dp[0][4]` will be true.  This indicates that an empty string `""` can match `"c*"` and `"c*a*"`.

2.  **Iteration:** We populate the `dp` table based on the matching rules:

    *   `s[0] = 'a'`, `p[0] = 'c'`:  `dp[1][1] = false`

    *   `s[0] = 'a'`, `p[1] = '*'`: `dp[1][2] = dp[1][0] = false` (zero occurence of c)  OR `dp[1][2] = dp[0][2] = true`, so then `dp[1][2] = false`

    *   `s[0] = 'a'`, `p[2] = 'a'`:  `dp[1][3] = dp[0][2] = false`

    *   `s[0] = 'a'`, `p[3] = '*'`:  `dp[1][4] = dp[1][2] = false` (zero occurence of a)  OR `dp[1][4] = dp[0][4] = false`, then  `dp[1][4] = true`

    *   `s[0] = 'a'`, `p[4] = 'b'`:  `dp[1][5] = false`

    *   `s[1] = 'a'`, `p[0] = 'c'`: `dp[2][1] = false`
    *   `s[1] = 'a'`, `p[1] = '*'`: `dp[2][2] = false`
    *   `s[1] = 'a'`, `p[2] = 'a'`:  `dp[2][3] = dp[1][2] = false`
    *   `s[1] = 'a'`, `p[3] = '*'`: `dp[2][4] = true`
    *   `s[1] = 'a'`, `p[4] = 'b'`: `dp[2][5] = false`

    *   `s[2] = 'b'`, `p[0] = 'c'`: `dp[3][1] = false`
    *   `s[2] = 'b'`, `p[1] = '*'`: `dp[3][2] = false`
    *   `s[2] = 'b'`, `p[2] = 'a'`:  `dp[3][3] = dp[2][2] = false`
    *   `s[2] = 'b'`, `p[3] = '*'`: `dp[3][4] = false`
    *   `s[2] = 'b'`, `p[4] = 'b'`:  `dp[3][5] = dp[2][4] = true`

3.  **Result:**  Finally, `dp[3][5]` which is `true`, which means `"aab"` matches `"c*a*b"`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(m \* n)**, where `m` is the length of the string `s` and `n` is the length of the pattern `p`. This is because we iterate through each cell of the `dp` array once.

*   **Space Complexity:** **O(m \* n)**, as we use a 2D boolean array `dp` of size `(m + 1) x (n + 1)` to store the matching results.
    