# 00009 - Palindrome Number
    
**Language:** Java  
**Runtime:** 4 ms (Beats 100.00% of users)  
**Memory:** 43.8 MB (Beats 95.11% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 9 | Palindrome Number | [LeetCode Problem](https://leetcode.com/problems/palindrome-number/) |

---

## 💡 **Problem Explanation**

Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Let's break it down:

*   A palindrome reads the same forwards and backward.
*   We need to check if an integer `x` is a palindrome.

**Examples:**

1.  **Input:** `x = 121`
    **Output:** `true`  (Because 121 read backwards is 121)

2.  **Input:** `x = -121`
    **Output:** `false` (Because -121 read backwards is 121-. Therefore it is not a palindrome.)

3.  **Input:** `x = 10`
    **Output:** `false` (Because 10 read backwards is 01. Therefore it is not a palindrome.)

---

## 📊 **Algorithm**

*   Handle negative numbers: If `x` is negative, it's not a palindrome (return `false`).
*   Initialize variables: `n` to store the original number, `s` to store the reversed number.
*   Reverse the number:
    *   Extract the last digit of `n`.
    *   Append the last digit to `s`.
    *   Remove the last digit from `n`.
*   Compare: Check if the reversed number `s` is equal to the original number `x`.
*   Return `true` if they are equal, otherwise `false`.

---

## 🔥 **Code Implementation**

```java
class Solution {
    public boolean isPalindrome(int x) {
        // If x is negative, it cannot be a palindrome
        if(x<0){
            return false;
        }
        // Store the original number
        int n = x;
        // Initialize the reversed number
        int s = 0;
        // Reverse the number
        while(n>0){
            int r=n%10; // Extract the last digit
            s=s*10+r;   // Append the last digit to reversed number
            n=n/10;     // Remove the last digit from original number
        }
        // Check if the reversed number is equal to the original number
        return s==x;
    }
}
```

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(log<sub>10</sub>(x)).  The number of iterations in the `while` loop is proportional to the number of digits in the integer `x`.
*   **Space Complexity:** O(1).  We only use a few integer variables, so the space required doesn't scale with the input.
    