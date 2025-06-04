# 00044 - Wildcard Matching
    
**Language:** Java  
**Runtime:** 12 ms (Beats 91.23% of users)  
**Memory:** 46.3 MB (Beats 42.22% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 44 | WILDCARD MATCHING | [LeetCode Problem](https://leetcode.com/problems/wildcard-matching/) |

---

## 💡 **Problem Explanation**

The Wildcard Matching problem involves determining if a given string `s` matches a pattern `p` containing wildcard characters. The wildcard characters are:

-   `?`: Matches any single character.
-   `*`: Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string `s` (not partial matching).

**Example 1:**

```
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```

**Example 2:**

```
Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
```

**Example 3:**

```
Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
```

## 💡 Algorithm

*   Initialize a 2D boolean array `dp` where `dp[i][j]` represents whether the first `i` characters of string `s` match the first `j` characters of pattern `p`.
*   The base case `dp[0][0]` is true because an empty string matches an empty pattern.
*   Handle the case where the string `s` is empty but the pattern `p` may contain `*`. If `p[j-1]` is `*`, then `dp[0][j] = dp[0][j-1]`.
*   Iterate through the string `s` and pattern `p`:
    *   If `p[j-1]` is `?` or `p[j-1]` is equal to `s[i-1]`, then `dp[i][j] = dp[i-1][j-1]`.
    *   If `p[j-1]` is `*`, then `dp[i][j]` is true if either `dp[i][j-1]` is true (i.e., `*` matches an empty sequence) or `dp[i-1][j]` is true (i.e., `*` matches one or more characters).
    *   Otherwise, `dp[i][j]` is false.
*   Return `dp[s.length][p.length]`.

## 🔥 **Code Implementation**

```java
class Solution {
    public boolean isMatch(String s, String p) {

        char[] str = s.toCharArray();
        char[] ptr = p.toCharArray();

        boolean[][] dp = new boolean[str.length + 1][ptr.length + 1];
        dp[0][0] = true;

        for (int j = 1; j <= ptr.length; j++) {
            if (ptr[j - 1] == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= str.length; i++) {
            for (int j = 1; j <= ptr.length; j++) {
                if (ptr[j - 1] == '?' || ptr[j - 1] == str[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (ptr[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[str.length][ptr.length];
    }
}
```

## 📊 **ASCII Representation**

Not applicable for this problem.

## 📊 **TABLE Representation**

Let's consider `s = "adceb"` and `p = "*a*b"`

|       |   | *   | a   | *   | b   |
| :---- | :-: | :-: | :-: | :-: | :-: |
|   | T | T | F | F | F |
| a | F | T | T | T | F |
| d | F | T | F | T | F |
| c | F | T | F | T | F |
| e | F | T | F | T | F |
| b | F | T | F | T | T |

## 📊 **WORKING**

Let's take the example `s = "adceb"` and `p = "*a*b"`. We'll trace the `dp` array.

1.  **Initialization:** `dp[0][0] = true`. The first row is initialized based on `*`.

2.  **Iteration:**

    *   For `s[0] = 'a'`, `p[0] = '*'`: `dp[1][1] = dp[0][1] || dp[1][0] = true`
    *   For `s[0] = 'a'`, `p[1] = 'a'`: `dp[1][2] = dp[0][1] = true`
    *   For `s[0] = 'a'`, `p[2] = '*'`: `dp[1][3] = dp[1][2] || dp[0][3] = true`
    *   For `s[0] = 'a'`, `p[3] = 'b'`: `dp[1][4] = false`

    *   For `s[1] = 'd'`, `p[0] = '*'`: `dp[2][1] = dp[1][1] || dp[2][0] = true`
    *   For `s[1] = 'd'`, `p[1] = 'a'`: `dp[2][2] = false`
    *   For `s[1] = 'd'`, `p[2] = '*'`: `dp[2][3] = dp[2][2] || dp[1][3] = true`
    *   For `s[1] = 'd'`, `p[3] = 'b'`: `dp[2][4] = false`

    And so on. The final result is `dp[5][4] = true`.

## 🚀 **Time & Space Complexity**

The **time complexity** is **O(m\*n)** where `m` is the length of the string `s` and `n` is the length of the pattern `p` due to the nested loops.

The **space complexity** is **O(m\*n)** because we are using a 2D boolean array `dp` of size `(m+1)x(n+1)`.
    