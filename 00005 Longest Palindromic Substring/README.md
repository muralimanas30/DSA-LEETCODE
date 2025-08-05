# 00005 - Longest Palindromic Substring
    
**Language:** Java  
**Runtime:** 96 ms (Beats 38.01% of users)  
**Memory:** 46.4 MB (Beats 11.88% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 5 | LONGEST PALINDROMIC SUBSTRING | [LeetCode Problem](https://leetcode.com/problems/longest-palindromic-substring/) |

---

## 💡 **Problem Explanation**

The problem asks us to find the longest palindromic substring within a given string `s`. A palindromic substring is a sequence of characters that reads the same forwards and backward.

**Example 1:**

*   **Input:** `s = "babad"`
*   **Output:** `"bab"` (or `"aba"`)

**Explanation:** Both `"bab"` and `"aba"` are palindromes of length 3 within the string `"babad"`.

**Example 2:**

*   **Input:** `s = "cbbd"`
*   **Output:** `"bb"`

**Explanation:** The longest palindromic substring is `"bb"` of length 2.

---

## 📊 **Algorithm**

*   Initialize a 2D boolean array `dp` where `dp[i][j]` represents whether the substring `s[i...j]` is a palindrome.
*   All single characters are palindromes, so initialize the diagonal of `dp` to `true`.
*   Iterate through substrings of increasing length, starting from length 2.
*   For each substring, check if the characters at the start and end are equal. If they are, and either the substring length is less than or equal to 2, or the inner substring `s[i+1...j-1]` is also a palindrome (as indicated by `dp[i+1][j-1]`), then `s[i...j]` is a palindrome.
*   Keep track of the longest palindromic substring found so far.
*   Return the longest palindromic substring.

## 🔥 **Code Implementation**

```java
class Solution {
    int finalLow = 0;
    int finalHigh = 0;
    int ans = 0;

    public String longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][n+1];
        for(int i=0;i<=n;i++)   dp[i][i] = true;
        int maxLength = 1, start = 0;

        for(int length = 2; length <= n; length++){
            for(int i=0; i+length-1 < n; i++ ){
                int j = i+length-1;
                dp[i][j] = arr[i]==arr[j] && (length<=2 || dp[i+1][j-1]);
                if(dp[i][j] && length>maxLength){
                    maxLength = length;
                    start = i;
                }
            }
        }
        return s.substring(start,start+maxLength);
    }
}
```

## 📊 **TABLE Representation**

For the input `s = "babad"`, the `dp` table would evolve as follows:

|       | b (0) | a (1) | b (2) | a (3) | d (4) |
| :---- | :---- | :---- | :---- | :---- | :---- |
| **b (0)** | T     | F     | T     | F     | F     |
| **a (1)** |       | T     | F     | T     | F     |
| **b (2)** |       |       | T     | F     | F     |
| **a (3)** |       |       |       | T     | F     |
| **d (4)** |       |       |       |       | T     |

**Explanation:**

1.  **Initialization:** `dp[i][i]` is `true` for all `i`.
2.  **Length 2:**
    *   `dp[0][1] = false` (b != a)
    *   `dp[1][2] = false` (a != b)
    *   `dp[2][3] = false` (b != a)
    *   `dp[3][4] = false` (a != d)
3.  **Length 3:**
    *   `dp[0][2] = true` (b == b and dp[1][1] is true)
    *   `dp[1][3] = true` (a == a and dp[2][2] is true)
    *   `dp[2][4] = false` (b != d)
4.  **Length 4:**
    *   `dp[0][3] = false` (b != a)
    *   `dp[1][4] = false` (a != d)
5.  **Length 5:**
    *   `dp[0][4] = false` (b != d)

The longest palindrome is `"bab"` (or `"aba"`) with a length of 3, starting at index 0 (or 1).

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n^2)**, where `n` is the length of the string `s`, due to the nested loops used to populate the `dp` table.
*   **Space Complexity:** **O(n^2)**, to store the `dp` table.
    