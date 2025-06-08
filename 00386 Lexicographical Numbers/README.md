# 00386 - Lexicographical Numbers
    
**Language:** Java  
**Runtime:** 3 ms (Beats 72.93% of users)  
**Memory:** 48.3 MB (Beats 81.22% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 386 | LEXICOGRAPHICAL NUMBERS | [LeetCode Problem](https://leetcode.com/problems/lexicographical-numbers/) |

---

## 💡 **Problem Explanation**

The problem asks us to generate a list of numbers from 1 to `n` in lexicographical order.  Lexicographical order is essentially dictionary order. So, if `n = 13`, the lexicographical order would be `[1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]`.

Here's a breakdown:

*   We start with '1'.
*   Then we look for numbers starting with '1' and append '0', '1', '2', '3', ... to '1' as long as the resulting number is less than or equal to `n`. So, '10', '11', '12', '13' come next.
*   Once we can't extend '1' further, we move to '2', '3', and so on until '9', and repeat the extension process.

**Example:**

Input: `n = 13`
Output: `[1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]`

Input: `n = 2`
Output: `[1, 2]`

## 📊 **Algorithm**

*   Initialize an empty list `al` to store the lexicographically ordered numbers.
*   Iterate from `i = 1` to `n`.
*   For each `i`, call a Depth-First Search (DFS) function to generate numbers in lexicographical order starting with `i`.
*   In the DFS function:
    *   If the current number `number` is greater than `limit` or the list `res` is full, return.
    *   Add the current number `number` to the list `res`.
    *   Iterate from `nextDigit = 0` to `9`.
    *   Calculate the next number `nextNum` by appending `nextDigit` to `number`.
    *   If `nextNum` is less than or equal to `limit`, recursively call the DFS function with `nextNum`.
    *   If `nextNum` exceeds `limit`, break the inner loop, as further numbers will also exceed the limit.
*   Return the list `al`.

## 🔥 **Code Implementation**

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> lexicalOrder(int n) {
        ArrayList<Integer> al = new ArrayList<>();
        for(int i=1;i<=n;i++){
            dfs(i,n,al);
        }
        return al;
    }
    public void dfs(int number,int limit,ArrayList<Integer> res){
        if(number>limit || res.size()==limit)    return;
        res.add(number);
        // dfs(number*10,limit,res);
        for(int nextDigit = 0;nextDigit<=9;nextDigit++){
            int nextNum = number*10 + nextDigit;
            if(nextNum<=limit)  dfs(nextNum,limit,res);
            else    break;
        }
    }
}
```

## 📊 **ASCII Representation**

This problem doesn't directly involve grids or trees that benefit from ASCII diagrams.  It's more about the order of number generation.

## 📊 **TABLE Representation**

This problem is solved using DFS rather than Dynamic Programming. So, a table representation is not relevant.

## 📊 **WORKING**

Let's trace the execution for `n = 13`:

1.  `lexicalOrder(13)` is called.
2.  The outer loop iterates from `i = 1` to `13`.
3.  First, `dfs(1, 13, al)` is called.
    *   `1 <= 13`, so `1` is added to `al`. `al = [1]`
    *   Inner loop from `nextDigit = 0` to `9`:
        *   `nextNum = 10`. `10 <= 13`, so `dfs(10, 13, al)` is called.
            *   `10 <= 13`, so `10` is added to `al`. `al = [1, 10]`
            *   Inner loop from `nextDigit = 0` to `9`:
                *   `nextNum = 100`. `100 > 13`, so the inner loop breaks.
            *   `dfs(10, 13, al)` returns.
        *   `nextNum = 11`. `11 <= 13`, so `dfs(11, 13, al)` is called.
            *   `11 <= 13`, so `11` is added to `al`. `al = [1, 10, 11]`
            *   Inner loop from `nextDigit = 0` to `9`:
                *   `nextNum = 110`. `110 > 13`, so the inner loop breaks.
            *   `dfs(11, 13, al)` returns.
        *   `nextNum = 12`. `12 <= 13`, so `dfs(12, 13, al)` is called.
            *   `12 <= 13`, so `12` is added to `al`. `al = [1, 10, 11, 12]`
            *   Inner loop from `nextDigit = 0` to `9`:
                *   `nextNum = 120`. `120 > 13`, so the inner loop breaks.
            *   `dfs(12, 13, al)` returns.
        *   `nextNum = 13`. `13 <= 13`, so `dfs(13, 13, al)` is called.
            *   `13 <= 13`, so `13` is added to `al`. `al = [1, 10, 11, 12, 13]`
            *   Inner loop from `nextDigit = 0` to `9`:
                *   `nextNum = 130`. `130 > 13`, so the inner loop breaks.
            *   `dfs(13, 13, al)` returns.
        *   For `nextDigit > 3`, all `nextNum` will be greater than 13, therefore inner loop breaks.
    *   `dfs(1, 13, al)` returns.
4.  The outer loop continues for `i = 2`, `i = 3`, ..., `i = 9`.
5.  Finally, `al` contains `[1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(n).  Although there is recursion involved, each number from 1 to n is effectively visited once in the DFS.
*   **Space Complexity:** O(n). The space is primarily used to store the output list `al`, which contains up to `n` elements. The recursion depth is also bounded by the number of digits in `n`, which is logarithmic in `n`, and therefore less significant than O(n).
    