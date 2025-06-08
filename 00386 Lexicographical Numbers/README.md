# 00386 - Lexicographical Numbers
    
**Language:** Java  
**Runtime:** 3 ms (Beats 72.93% of users)  
**Memory:** 48.1 MB (Beats 94.28% of users)  

## 💡 **Problem Explanation**

The "Lexicographical Numbers" problem asks us to generate a list of numbers from 1 to `n` in lexicographical (dictionary) order. This means we should order the numbers as if they were strings.  For instance, 1 comes before 10, which comes before 11, and so on.

**Example:**

*   **Input:** `n = 13`
*   **Output:** `[1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]`

**Explanation of the Output:**

We generate numbers in the following order:

1.  Start with 1.
2.  Append digits to 1 to get 10, 11, 12, 13.
3.  Move to the next single-digit number 2, then 3, and so on, up to 9.

## 📊 **Algorithm**

Here's a breakdown of the algorithm used in the code:

*   **DFS (Depth-First Search) Approach (Method 1):**

    1.  **Initialization:** Create an empty list `res` to store the lexicographically sorted numbers.
    2.  **Iteration:** Iterate from `i = 1` to `n`.  For each `i`, initiate a depth-first search starting with `i`.
    3.  **DFS Function (`dfs`)**:
        *   **Base Case:** If the current number `number` is greater than the limit `n` or the list size is equals to limit then return.
        *   **Add to Result:** Add the current number `number` to the result list `res`.
        *   **Recursive Step:** Iterate through digits 0 to 9 (`nextDigit`).
            *   Calculate the next potential number: `nextNum = number * 10 + nextDigit`.
            *   If `nextNum` is within the limit `n`, recursively call `dfs` with `nextNum`.
            *   If `nextNum` exceeds the limit `n`, stop exploring further digits (optimization).
*   **Sorting Approach (Method 2):**
    1.  Convert each number from 1 to n to a string and store in string array.
    2.  Sort the string array.
    3.  Convert the string array to integer array and return.

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

## 📊 **ASCII Representation**

(Not applicable, as this problem doesn't directly involve grids or trees.)

## 📊 **TABLE Representation**

(Not applicable, as this problem doesn't directly involve DP or array traversals in a way that benefits from tabular representation.)

## 📊 **WORKING**

Let's trace the execution for `n = 13` using the DFS method (Method 1):

1.  **Initial call:** `lexicalOrder(13)`
2.  **Loop (i = 1):** `dfs(1, 13, [])`
    *   `res.add(1)`  `res` is now `[1]`
    *   Loop (nextDigit = 0): `dfs(10, 13, [1])`
        *   `res.add(10)` `res` is now `[1, 10]`
        *   Loop (nextDigit = 0): `dfs(100, 13, [1, 10])` - returns immediately (100 > 13)
        *   Loop (nextDigit = 1): `dfs(101, 13, [1, 10])` - returns immediately (101 > 13)
        *   ...and so on
    *   Loop (nextDigit = 1): `dfs(11, 13, [1, 10])`
        *   `res.add(11)` `res` is now `[1, 10, 11]`
        *   ...
    *   Loop (nextDigit = 2): `dfs(12, 13, [1, 10, 11])`
        *   `res.add(12)` `res` is now `[1, 10, 11, 12]`
        *   ...
    *   Loop (nextDigit = 3): `dfs(13, 13, [1, 10, 11, 12])`
        *   `res.add(13)` `res` is now `[1, 10, 11, 12, 13]`
        *   ...
    *   Loop (nextDigit = 4): `dfs(14, 13, [1, 10, 11, 12, 13])` - returns immediately (14 > 13)
    *   ...
3.  **Loop (i = 2):** `dfs(2, 13, [1, 10, 11, 12, 13])`
    *   `res.add(2)` `res` is now `[1, 10, 11, 12, 13, 2]`
    *   ... and so on

## 🚀 **Time & Space Complexity**

**Method 1 (DFS):**

*   **Time Complexity:**  **O(N)**.  In the worst case, we visit each number from 1 to `n`.
*   **Space Complexity:** **O(N)**.  The depth of the recursion can be up to log10(N), and the `res` list stores up to `n` integers.

**Method 2 (Sorting):**

*   **Time Complexity:** **O(N log N)** due to the sorting step.
*   **Space Complexity:** **O(N)** to store the strings and the result list.
    