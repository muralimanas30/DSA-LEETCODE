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

Given an input string `s` and a pattern `p`, implement regular expression matching with support for `'.'` and `'*'` where:

*   `.` Matches any single character.​​​​
*   `*` Matches zero or more of the preceding element.

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

*   Create a 2D boolean array `dp` of size `(s.length() + 1) x (p.length() + 1)` to store matching results. `dp[i][j]` will represent whether the first `i` characters of `s` match the first `j` characters of `p`.
*   Initialize `dp[0][0]` to `true` because an empty string matches an empty pattern.
*   Handle the case where the pattern starts with `a*`, `b*`, etc.  `dp[0][j] = dp[0][j-2]` if `p[j-1]` is `*`.  This is because `a*` can represent zero occurrences of `a`.
*   Iterate through the string `s` and pattern `p` using nested loops.
*   If `p[j-1]` is `.` or `p[j-1]` is equal to `s[i-1]`, then `dp[i][j] = dp[i-1][j-1]`.  This means the current characters match, so the result depends on the previous subproblem.
*   If `p[j-1]` is `*`, there are two possibilities:
    *   Zero occurrences: `dp[i][j] = dp[i][j-2]`.  The `*` and the preceding character are ignored.
    *   One or more occurrences:  `dp[i][j] = dp[i-1][j]` if `p[j-2]` is `.` or `p[j-2]` is equal to `s[i-1]`.  The current character of `s` matches the preceding character of `*`, so we check if the rest of `s` matches `p` up to the `*`.
*   If none of the above conditions are met, then `dp[i][j] = false`.
*   Return `dp[s.length()][p.length()]`.

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

N/A

## 📊 **TABLE Representation**

Let's visualize `dp` table for `s = "aab", p = "c*a*b"`

|       |   | c | * | a | * | b |
| :---- | :-: | :-: | :-: | :-: | :-: | :-: |
|   | T | F | T | F | T | F |
| a | F | F | F | T | T | F |
| a | F | F | F | F | T | F |
| b | F | F | F | F | F | T |

## 📊 **WORKING**

Let's trace the example `s = "aab", p = "c*a*b"`:

1.  **Initialization**: `dp[0][0] = true`. Fill the first row based on the `'*'` character.
2.  **`i = 1, j = 1`**: `s[0] = 'a', p[0] = 'c'`. No match. `dp[1][1] = false`.
3.  **`i = 1, j = 2`**: `s[0] = 'a', p[1] = '*'`. `dp[1][2] = dp[1][0] = false`.
4.  **`i = 1, j = 3`**: `s[0] = 'a', p[2] = 'a'`. Match. `dp[1][3] = dp[0][2] = true`.
5.  **`i = 1, j = 4`**: `s[0] = 'a', p[3] = '*'`. `dp[1][4] = dp[1][2] = false` or `dp[1][4] = dp[0][4] = true` then `dp[1][4] = true`
6.  **`i = 1, j = 5`**: `s[0] = 'a', p[4] = 'b'`. No match. `dp[1][5] = false`.
7.  **`i = 2, j = 1`**: `s[1] = 'a', p[0] = 'c'`. No match. `dp[2][1] = false`.

... and so on, until `dp[3][5]` which will be `true`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(m\*n)** where `m` is the length of the string `s` and `n` is the length of the pattern `p`. This is because we iterate through the `dp` table which has dimensions `(m+1) x (n+1)`.

*   **Space Complexity:** **O(m\*n)** because we use a 2D boolean array `dp` of size `(m+1) x (n+1)` to store the matching results.

![Time Complexity Graph](https://i.imgur.com/2yYQnQ3.png)
    