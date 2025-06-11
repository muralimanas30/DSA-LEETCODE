# 00091 - Decode Ways

**Language:** Java  
**Runtime:** 0 ms (Beats 100.00% of users)  
**Memory:** 41.6 MB (Beats 87.31% of users)  

---

## 📝 Problem Statement

[LeetCode Problem 91: Decode Ways](https://leetcode.com/problems/decode-ways/)

Given a string `s` containing only digits, return the total number of ways to decode it. Each digit or pair of digits can be mapped to a letter as follows:  
- 'A' -> 1  
- 'B' -> 2  
- ...  
- 'Z' -> 26  

**Note:**  
- '0' cannot be mapped to any letter.
- Two consecutive digits can be combined only if they form a number between 10 and 26.

---

## 📥 Example Inputs & Outputs

| Input      | Output | Explanation                                  |
|------------|--------|----------------------------------------------|
| `s = "12"` | `2`    | "AB" (1 2), "L" (12)                        |
| `s = "226"`| `3`    | "BZ" (2 26), "VF" (22 6), "BBF" (2 2 6)     |
| `s = "06"` | `0`    | Invalid: '0' cannot be decoded              |

**Stats:**  
- **Language:** Java  
- **Runtime:** 0 ms (Beats 100.00% of users)  
- **Memory:** 41.6 MB (Beats 87.31% of users)  

---

## 🧠 Algorithm Overview

This solution uses **top-down dynamic programming with memoization**.  
- The recursive function explores all valid ways to decode the string, considering both single-digit and two-digit mappings.
- Memoization ensures each subproblem is solved only once, resulting in O(N) time complexity.

**Why this approach?**  
- The problem has overlapping subproblems and optimal substructure, making DP ideal.
- Recursion with memoization is intuitive and avoids redundant computation.

---

## 🗃️ Variables & Data Structures

| Name      | Type        | Purpose                                                                 |
|-----------|-------------|-------------------------------------------------------------------------|
| `arr`     | `char[]`    | Character array of the input string for easy access by index            |
| `dp`      | `int[]`     | Memoization array; `dp[i]` stores number of ways to decode from index i  |
| `idx`     | `int`       | Current index in the recursive function                                 |
| `ways`    | `int`       | Number of ways to decode from the current index                         |

---

## 🪜 Step-by-Step Algorithm

1. **Convert** the input string to a character array `arr`.
2. **Initialize** a DP array `dp` of length `arr.length`, filled with -1.
3. **Define** a recursive function `decode(arr, dp, idx)`:
    - If `idx == arr.length`, return 1 (reached end, valid decoding).
    - If `arr[idx] == '0'`, return 0 (invalid).
    - If `dp[idx] != -1`, return `dp[idx]` (already computed).
    - Set `ways = decode(arr, dp, idx + 1)` (single digit).
    - If `idx + 1 < arr.length` and `10 <= num <= 26` for `num = arr[idx..idx+1]`, add `decode(arr, dp, idx + 2)` to `ways`.
    - Store `ways` in `dp[idx]` and return it.

---

## 📊 Visual Diagram (ASCII)

```
s = "226"
Indexes: 0   1   2
         2   2   6

Recursion Tree:
decode(0)
├── decode(1)
│   ├── decode(2)
│   │   └── decode(3) → 1
│   └── decode(3) → 1
└── decode(2)
    └── decode(3) → 1
```

---

## 🧩 Step-by-Step Walkthrough (Sample Input: "226")

1. `decode(0)`:
    - arr[0]='2', not '0', dp[0]=-1
    - ways = decode(1)
2. `decode(1)`:
    - arr[1]='2', not '0', dp[1]=-1
    - ways = decode(2)
3. `decode(2)`:
    - arr[2]='6', not '0', dp[2]=-1
    - ways = decode(3)
4. `decode(3)`:
    - idx==arr.length, return 1
    - dp[2]=1
5. Back to `decode(1)`:
    - ways=1
    - arr[1..2]="26" (valid), ways += decode(3) → 1+1=2, dp[1]=2
6. Back to `decode(0)`:
    - ways=2
    - arr[0..1]="22" (valid), ways += decode(2) → 2+1=3, dp[0]=3

---

## 🔥 Code Implementation

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

## 🧭 Programming Workflow

### Numbered Steps

1. Convert input string to character array.
2. Initialize DP array with -1.
3. Call recursive decode function from index 0.
4. For each call:
    - If at end, return 1.
    - If current char is '0', return 0.
    - If already computed, return dp value.
    - Recurse for single digit.
    - If valid, recurse for two digits.
    - Store and return result.

### Flowchart

```
+-----------------------------+
| Start: numDecodings(s)      |
+-------------+---------------+
              |
              v
+-----------------------------+
| Convert s to arr, init dp[] |
+-------------+---------------+
              |
              v
+-----------------------------+
| decode(arr, dp, idx=0)      |
+-------------+---------------+
              |
              v
+-----------------------------+
| idx == arr.length?          |
+----+------------------------+
     | Yes                    | No
     v                        v
 Return 1           arr[idx] == '0'?
                         | Yes | No
                         v     v
                     Return 0  dp[idx] != -1?
                                 | Yes | No
                                 v     v
                             Return   Recurse
                             dp[idx]  for idx+1
                                 |
                                 v
                    idx+1 < arr.length and
                    10 <= num <= 26?
                         | Yes | No
                         v     v
                Recurse for idx+2
                         |
                         v
                Store result in dp[idx]
                         |
                         v
                      Return
```

---

## 🚀 Complexity Analysis

- **Time Complexity:** O(N), where N is the length of `s` (each index is computed once).
- **Space Complexity:** O(N), for the DP array and recursion stack.

---

## 📚 References

- [LeetCode Editorial](https://leetcode.com/problems/decode-ways/editorial/)
- [Dynamic Programming - Memoization](https://en.wikipedia.org/wiki/Memoization)
- [Top-Down DP vs Bottom-Up DP](https://www.geeksforgeeks.org/tabulation-vs-memoization/)