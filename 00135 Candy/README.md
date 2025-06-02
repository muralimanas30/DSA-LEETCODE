# 00135 - Candy
    
**Language:** Java  
**Runtime:** 7 ms (Beats 11.07% of users)  
**Memory:** 45.5 MB (Beats 90.86% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 135 | CANDY | [LeetCode Problem](https://leetcode.com/problems/candy/) |

---

## 💡 **Problem Explanation**

Imagine a line of children standing in a row, each with a rating score. You want to distribute candies to these children such that:

1.  Every child gets at least one candy.
2.  Children with a higher rating get more candies than their neighbors.

The goal is to find the minimum number of candies you need to distribute.

**Example 1:**

```
Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
```

**Example 2:**

```
Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.
```

## 📊 **Algorithm**

*   Initialize an array `candies` of the same length as `ratings`, filled with 1s. This ensures each child gets at least one candy.
*   Iterate from left to right: if a child has a higher rating than the child to their left, and their candy count is not higher, increment their candy count by 1.
*   Iterate from right to left: if a child has a higher rating than the child to their right, and their candy count is not higher, increment their candy count by 1.
*   Sum all the values in the `candies` array to get the total number of candies needed.

## 🔥 **Code Implementation**

```java
import java.util.Arrays;

class Solution {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies,1);
        for(int i=1;i<ratings.length;i++)
            if(ratings[i]>ratings[i-1] && candies[i]<=candies[i-1])
                candies[i] = candies[i-1]+1;
        for(int i=ratings.length-2;i>=0;i--)
            if(ratings[i]>ratings[i+1] && candies[i]<=candies[i+1])
                candies[i] = candies[i+1]+1;
        return Arrays.stream(candies).sum();
    }
}
```

## 📊 **ASCII Representation**

This problem doesn't directly involve grids or trees, so an ASCII representation isn't strictly necessary.  However, we can use a simple visualization to understand the ratings array.

```
Ratings:  [1, 0, 2, 3, 2]
Index:    [0, 1, 2, 3, 4]
```

## 📊 **WORKING**

Let's walk through the example `ratings = [1, 0, 2]`:

1.  **Initialization:** `candies = [1, 1, 1]`

2.  **Left-to-Right Iteration:**
    *   `i = 1`: `ratings[1] (0) > ratings[0] (1)` is false. `candies` remains `[1, 1, 1]`
    *   `i = 2`: `ratings[2] (2) > ratings[1] (0)` is true. `candies[2] = candies[1] + 1 = 2`. `candies` becomes `[1, 1, 2]`

3.  **Right-to-Left Iteration:**
    *   `i = 1`: `ratings[1] (0) > ratings[2] (2)` is false. `candies` remains `[1, 1, 2]`
    *   `i = 0`: `ratings[0] (1) > ratings[1] (0)` is true. `candies[0] = candies[1] + 1 = 2`. `candies` becomes `[2, 1, 2]`

4.  **Summation:** `2 + 1 + 2 = 5`

Therefore, the minimum number of candies is 5.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n)**, where n is the number of children (length of the `ratings` array). We iterate through the array twice.
*   **Space Complexity:** **O(n)**, as we use an auxiliary array `candies` of the same size as the input array to store the number of candies for each child.
    