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

The "Regular Expression Matching" problem asks whether a given string `s` matches a regular expression pattern `p`. The regular expression supports two special characters:

*   `.` Matches any single character.
*   `*` Matches zero or more occurrences of the preceding element.

The matching should cover the entire input string `s` (not partial).

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

Here's the algorithm we'll use:

*   Initialize a 2D boolean array `dp` where `dp[i][j]` represents whether the first `i` characters of the string `s` match the first `j` characters of the pattern `p`.

*   The first row and column of `dp` are initialized based on the conditions where either the string or the pattern is empty.

*   Iterate through the `dp` array, filling it based on the following rules:

    *   If `p[j-1]` is `.` or `s[i-1]`, then `dp[i][j] = dp[i-1][j-1]`.
    *   If `p[j-1]` is `*`:
        *   If the character before `*` doesn't match `s[i-1]`, then `dp[i][j] = dp[i][j-2]` (zero occurrences).
        *   Otherwise, `dp[i][j] = dp[i][j-2]` (zero occurrences) or `dp[i-1][j]` (one or more occurrences).
    *   If none of the above, `dp[i][j] = false`.

*   The final result is stored in `dp[s.length()][p.length()]`.

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

Let's consider `s = "aab"` and `p = "c*a*b"`

The `dp` array can be visualized as follows:

```
      ""  c   *   a   *   b
""  T   F   T   F   T   F
a   F   F   F   T   T   F
a   F   F   F   F   T   F
b   F   F   F   F   F   T

```

## 📊 **WORKING**

Let's trace the execution for `s = "aab"` and `p = "c*a*b"`:

1.  **Initialization**: `dp[0][0] = true`.  Since `p[1]` is `*`, then `dp[0][2] = dp[0][0]` which is `true`. Since `p[3]` is `*`, then `dp[0][4] = dp[0][2]` which is `true`.

2.  **Iteration**:

    *   For `i = 1`, `j = 1`: `s[0] = 'a'`, `p[0] = 'c'`. No match, `dp[1][1] = false`.

    *   For `i = 1`, `j = 2`: `s[0] = 'a'`, `p[1] = '*'`.  `p[0] = 'c'` and `s[0] = 'a'` which do not match. So, `dp[1][2] = dp[1][0] = false`.

    *   For `i = 1`, `j = 3`: `s[0] = 'a'`, `p[2] = 'a'`. Match, `dp[1][3] = dp[0][2] = true`.

    *   For `i = 1`, `j = 4`: `s[0] = 'a'`, `p[3] = '*'`. `p[2] = 'a'` and `s[0] = 'a'` match.  So, `dp[1][4] = dp[1][2] || dp[0][4] = false || true = true`.

    *   For `i = 1`, `j = 5`: `s[0] = 'a'`, `p[4] = 'b'`. No match, `dp[1][5] = false`.

    *   For `i = 2`, `j = 1`: `s[1] = 'a'`, `p[0] = 'c'`. No match, `dp[2][1] = false`.

    *   For `i = 2`, `j = 2`: `s[1] = 'a'`, `p[1] = '*'`. `p[0] = 'c'` and `s[1] = 'a'` which do not match.  So, `dp[2][2] = dp[2][0] = false`.

    *   For `i = 2`, `j = 3`: `s[1] = 'a'`, `p[2] = 'a'`. Match, `dp[2][3] = dp[1][2] = false`.

    *   For `i = 2`, `j = 4`: `s[1] = 'a'`, `p[3] = '*'`. `p[2] = 'a'` and `s[1] = 'a'` match.  So, `dp[2][4] = dp[2][2] || dp[1][4] = false || true = true`.

    *   For `i = 2`, `j = 5`: `s[1] = 'a'`, `p[4] = 'b'`. No match, `dp[2][5] = false`.

    *   For `i = 3`, `j = 1`: `s[2] = 'b'`, `p[0] = 'c'`. No match, `dp[3][1] = false`.

    *   For `i = 3`, `j = 2`: `s[2] = 'b'`, `p[1] = '*'`. `p[0] = 'c'` and `s[2] = 'b'` which do not match.  So, `dp[3][2] = dp[3][0] = false`.

    *   For `i = 3`, `j = 3`: `s[2] = 'b'`, `p[2] = 'a'`. No match, `dp[3][3] = false`.

    *   For `i = 3`, `j = 4`: `s[2] = 'b'`, `p[3] = '*'`. `p[2] = 'a'` and `s[2] = 'b'` which do not match.  So, `dp[3][4] = dp[3][2] = false`.

    *   For `i = 3`, `j = 5`: `s[2] = 'b'`, `p[4] = 'b'`. Match, `dp[3][5] = dp[2][4] = true`.

3.  **Result**: `dp[3][5] = true`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(m\*n), where `m` is the length of the string `s` and `n` is the length of the pattern `p`, due to the nested loops in the dynamic programming approach.

*   **Space Complexity:** O(m\*n) for the `dp` array.
    