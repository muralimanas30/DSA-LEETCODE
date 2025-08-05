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

Given a string `s`, find the longest palindromic substring in `s`. A palindromic substring is a string that reads the same forwards and backward.

**Example:**

Input: `s = "babad"`
Output: `"bab"` or `"aba"` (Both are valid longest palindromes)

Input: `s = "cbbd"`
Output: `"bb"`

Input: `s = "a"`
Output: `"a"`

---

## 📊 **Algorithm**

*   Initialize a 2D boolean array `dp` where `dp[i][j]` will store whether the substring `s[i...j]` is a palindrome.
*   All single characters are palindromes (`dp[i][i] = true`).
*   Iterate through substrings of increasing length (from 2 to n).
*   For each length, iterate through all possible starting positions `i`.
*   Calculate the ending position `j` as `i + length - 1`.
*   If `s[i]` and `s[j]` are equal and either the substring has length <= 2 or the substring `s[i+1...j-1]` is a palindrome (as stored in `dp[i+1][j-1]`), then `dp[i][j]` is true.
*   Keep track of the starting position and length of the longest palindrome found so far.
*   Finally, return the longest palindromic substring using the stored starting position and length.

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

Let's consider the input `s = "babad"` to visualize the `dp` table.

|       | 0 (b) | 1 (a) | 2 (b) | 3 (a) | 4 (d) |
| :---- | :---- | :---- | :---- | :---- | :---- |
| **0 (b)** | true  | false | true  | false | false |
| **1 (a)** |       | true  | false | true  | false |
| **2 (b)** |       |       | true  | false | false |
| **3 (a)** |       |       |       | true  | false |
| **4 (d)** |       |       |       |       | true  |

## 📊 **WORKING**

Let's trace the execution with the input "babad".

1.  Initialize `dp` table. `dp[i][i] = true` for all `i`. `maxLength = 1`, `start = 0`.

2.  `length = 2`:
    *   `i = 0`, `j = 1`: `s[0] != s[1]` (`b != a`), `dp[0][1] = false`.
    *   `i = 1`, `j = 2`: `s[1] != s[2]` (`a != b`), `dp[1][2] = false`.
    *   `i = 2`, `j = 3`: `s[2] != s[3]` (`b != a`), `dp[2][3] = false`.
    *   `i = 3`, `j = 4`: `s[3] != s[4]` (`a != d`), `dp[3][4] = false`.

3.  `length = 3`:
    *   `i = 0`, `j = 2`: `s[0] == s[2]` (`b == b`) and `dp[1][1]` is true, `dp[0][2] = true`. `maxLength = 3`, `start = 0`.  Longest Palindrome found is "bab".
    *   `i = 1`, `j = 3`: `s[1] == s[3]` (`a == a`) and `dp[2][2]` is true, `dp[1][3] = true`. `maxLength = 3`, `start = 1`.  Longest Palindrome found is "aba".
    *   `i = 2`, `j = 4`: `s[2] != s[4]` (`b != d`), `dp[2][4] = false`.

4.  `length = 4`:
    *   `i = 0`, `j = 3`: `s[0] != s[3]` (`b != a`), `dp[0][3] = false`.
    *   `i = 1`, `j = 4`: `s[1] != s[4]` (`a != d`), `dp[1][4] = false`.

5.  `length = 5`:
    *   `i = 0`, `j = 4`: `s[0] != s[4]` (`b != d`), `dp[0][4] = false`.

Finally, the substring `s.substring(start, start + maxLength)` is returned.  Since `start` is `1` and `maxLength` is `3`, "aba" is returned.  "bab" is another possible solution since there are 2 palindromes with same length.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(n^2), where n is the length of the input string `s`.  This is due to the nested loops required to fill the `dp` table.
*   **Space Complexity:** O(n^2), due to the `dp` table of size (n+1)x(n+1).
    