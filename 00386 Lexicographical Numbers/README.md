# 00386 - Lexicographical Numbers
    
**Language:** Java  
**Runtime:** 3 ms (Beats 72.93% of users)  
**Memory:** 48.1 MB (Beats 94.28% of users)  

## 💡 **Problem Explanation**

The problem requires generating a list of numbers from 1 to `n` in lexicographical order. Lexicographical order is similar to alphabetical order but applied to numbers. For example, `1, 10, 11, 12, ..., 19, 2, 20, 21, ...` is the lexicographical order for numbers starting from 1.

**Example:**

*   **Input:** `n = 13`
*   **Output:** `[1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]`

**Example:**

*   **Input:** `n = 2`
*   **Output:** `[1, 2]`

## 💡 Algorithm

*   Initialize an empty list to store the results.
*   Iterate from 1 to `n`.
*   For each number `i` from 1 to `n`, perform a Depth-First Search (DFS) to generate lexicographically ordered numbers starting with `i`.
*   In the DFS function:
    *   Add the current number to the result list.
    *   For each digit from 0 to 9, calculate the next number by appending the digit to the current number.
    *   If the next number is within the limit `n`, recursively call DFS with the next number.
    *   If the next number exceeds the limit, break the loop as further numbers will also exceed the limit.
*   Return the result list.

## 🔥 **Code Implementation**

```java
//METHOD 1
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
        for(int nextDigit = 0;nextDigit<=9;nextDigit++){
            int nextNum = number*10 + nextDigit;
            if(nextNum<=limit)  dfs(nextNum,limit,res);
            else    break;
        }
    }
}
/*
METHOD 2
class Solution {
    public List<Integer> lexicalOrder(int n) {
        String[] strs = new String[n];
        for(int i=1;i<=n;i++){
            strs[i-1]=i+"";
        }
        Arrays.sort(strs);
        List<Integer> res = new ArrayList<>();
        for(String str : strs)
            res.add(Integer.parseInt(str));
        return res;
    }
}
*/
```

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(N), where N is the input number `n`.  The DFS function visits each number at most once.
*   **Space Complexity:** O(N), to store the resulting list of numbers in lexicographical order. The recursion stack in the DFS method has a depth proportional to the number of digits in `n`, but in the worst case, it scales with N, giving O(N) space complexity.
    