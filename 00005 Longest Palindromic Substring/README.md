# 00005 - Longest Palindromic Substring

**Language:** Java  
**Runtime:** 96 ms (Beats 38.01% of users)  
**Memory:** 46.4 MB (Beats 11.88% of users)  

---

## 📎 External Link

- [LeetCode Problem 5: Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

---

## 📝 Problem Statement

Given a string `s`, return the longest palindromic substring in `s`.  
A palindrome is a sequence that reads the same forwards and backwards (e.g., "madam", "racecar").

---

## 📚 Example Inputs & Outputs

| Input         | Output | Explanation                                                                 |
|---------------|--------|-----------------------------------------------------------------------------|
| `s = "babad"` | `"bab"` or `"aba"` | Both "bab" and "aba" are valid longest palindromic substrings.      |
| `s = "cbbd"`  | `"bb"` | "bb" is the longest palindromic substring.                                  |
| `s = "a"`     | `"a"`  | Single character is always a palindrome.                                    |
| `s = "ac"`    | `"a"` or `"c"` | Both are valid, as no longer palindrome exists.                      |

---

## 🏆 Solution Overview

This problem is solved using **Dynamic Programming**.  
We use a 2D boolean table `dp` where `dp[i][j]` is `true` if the substring `s[i...j]` is a palindrome.

**Why this approach?**
- Dynamic programming efficiently checks all possible substrings and avoids redundant computations.
- It allows us to build up solutions for longer substrings using results from shorter ones.

---

## 🧩 Variables & Data Structures

| Name        | Type            | Purpose                                                                 |
|-------------|-----------------|-------------------------------------------------------------------------|
| `dp`        | `boolean[][]`   | `dp[i][j]` is true if `s[i...j]` is a palindrome.                      |
| `arr`       | `char[]`        | Character array of the input string.                                    |
| `n`         | `int`           | Length of the input string.                                             |
| `maxLength` | `int`           | Length of the longest palindromic substring found so far.               |
| `start`     | `int`           | Starting index of the longest palindromic substring.                    |
| `i, j`      | `int`           | Indices for substring boundaries.                                       |
| `length`    | `int`           | Current substring length being checked.                                 |

---

## 🛠️ Step-by-Step Algorithm

1. Convert the input string to a character array.
2. Initialize a 2D boolean array `dp` of size `(n+1) x (n+1)`.
3. Set all substrings of length 1 as palindromes (`dp[i][i] = true`).
4. For substring lengths from 2 to `n`:
    - For each possible starting index `i`, calculate ending index `j = i + length - 1`.
    - If `arr[i] == arr[j]` and (length ≤ 2 or `dp[i+1][j-1]` is true), set `dp[i][j] = true`.
    - If a palindrome is found and its length is greater than `maxLength`, update `maxLength` and `start`.
5. Return the substring from `start` to `start + maxLength`.

---

## 🎨 Visual Diagram

### Example: `s = "babad"`

```
String indices:  0 1 2 3 4
Characters:      b a b a d

DP Table (T = true, F = false):

      0   1   2   3   4
    +---+---+---+---+---+
 0| T | F | T | F | F |
 1|   | T | F | T | F |
 2|   |   | T | F | F |
 3|   |   |   | T | F |
 4|   |   |   |   | T |
```

- Diagonal: All substrings of length 1 are palindromes.
- `dp[0][2]` ("bab") and `dp[1][3]` ("aba") are true (palindromes of length 3).

---

## 🧮 Step-by-Step Walkthrough

**Input:** `s = "babad"`

1. **Initialization:**  
   - `dp[i][i] = true` for all `i`.
   - `maxLength = 1`, `start = 0`.

2. **Length = 2:**  
   - Check "ba", "ab", "ba", "ad" — none are palindromes.

3. **Length = 3:**  
   - "bab": `s[0] == s[2]` and `dp[1][1]` is true ⇒ palindrome. Update `maxLength = 3`, `start = 0`.
   - "aba": `s[1] == s[3]` and `dp[2][2]` is true ⇒ palindrome. (No update since length not greater than current max.)

4. **Lengths 4 and 5:**  
   - No new palindromes found.

5. **Result:**  
   - Return substring from `start = 0` of length `maxLength = 3` ⇒ `"bab"`.

---

## 💻 Java Code Implementation

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

---

## 🛠️ Programming Workflow

### Logical Flow (Numbered List)

1. Convert input string to character array.
2. Initialize DP table and set all substrings of length 1 as palindromes.
3. For each substring length from 2 to n:
    - For each possible start index:
        - Check if substring is palindrome using DP.
        - Update max length and start if needed.
4. Return the longest palindromic substring.

### Flowchart (ASCII Art)

```
+-----------------------------------+
| 1. Convert string to char array   |
+-------------------+---------------+
                    |
                    v
+-----------------------------------+
| 2. Initialize DP table            |
|    - dp[i][i] = true              |
+-------------------+---------------+
                    |
                    v
+-----------------------------------+
| 3. For length = 2 to n:           |
|    For i = 0 to n-length:         |
|      j = i + length - 1           |
|      If arr[i]==arr[j] &&         |
|         (length<=2 || dp[i+1][j-1])|
|        dp[i][j]=true              |
|        Update maxLength/start     |
+-------------------+---------------+
                    |
                    v
+-----------------------------------+
| 4. Return s.substring(start,      |
|    start+maxLength)               |
+-----------------------------------+
```

---

## ⏱️ Complexity Analysis

- **Time Complexity:** O(n²)  
  - Two nested loops over the string length.
- **Space Complexity:** O(n²)  
  - For the DP table.

---

## 📚 References

- [LeetCode Problem 5: Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)
- [Dynamic Programming (GeeksforGeeks)](https://www.geeksforgeeks.org/dynamic-programming/)
- [Palindrome (Wikipedia)](https://en.wikipedia.org/wiki/Palindrome)