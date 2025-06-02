# 00135 - Candy
    
**Language:** Java  
**Runtime:** 3 ms (Beats 84.79% of users)  
**Memory:** 45.7 MB (Beats 70.42% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 135 | CANDY | [LeetCode Problem](https://leetcode.com/problems/candy/) |

---

## 💡 **Problem Explanation**

Imagine a line of children standing in a row, each with a rating representing their performance. You want to distribute candies to these children such that:

1.  Every child gets at least one candy.
2.  Children with a higher rating get more candies than their neighbors.

The goal is to find the minimum number of candies you need to distribute to satisfy these conditions.

**Example:**

*   **Input:** `ratings = [1,0,2]`
*   **Output:** `5`

**Explanation:** You can distribute the candies as follows:

*   Child 1 (rating 1): 2 candies
*   Child 2 (rating 0): 1 candy
*   Child 3 (rating 2): 2 candies

Another valid distribution is:

*   Child 1 (rating 1): 1 candy
*   Child 2 (rating 0): 1 candy
*   Child 3 (rating 2): 2 candies

Thus, the minimum number of candies required is 5.

*   **Input:** `ratings = [1,2,2]`
*   **Output:** `4`

**Explanation:** You can distribute the candies as follows:

*   Child 1 (rating 1): 1 candy
*   Child 2 (rating 2): 2 candies
*   Child 3 (rating 2): 1 candy

Thus, the minimum number of candies required is 4.

## 📊 **Algorithm**

*   Initialize an array `candies` of the same length as `ratings`, filled with 1s. This ensures each child gets at least one candy.
*   Traverse the `ratings` array from left to right. If a child has a higher rating than the child to their left, and the current child has fewer candies than or the same as their left neighbor, increment the number of candies for the current child by 1.
*   Traverse the `ratings` array from right to left. If a child has a higher rating than the child to their right, and the current child has fewer candies than or the same as their right neighbor, increment the number of candies for the current child by 1.
*   Sum up the number of candies in the `candies` array to get the minimum number of candies needed.

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
        int min = 0;
        for(int x : candies)
            min+=x;
        return min;
    }
}
```

## 📊 **ASCII Representation**

This problem doesn't involve a grid or a tree, so an ASCII representation isn't directly applicable.

## 📊 **WORKING**

Let's trace the algorithm with `ratings = [1, 0, 2]`.

1.  **Initialization:** `candies = [1, 1, 1]`
2.  **Left to Right Traversal:**
    *   `i = 1`: `ratings[1] (0) > ratings[0] (1)` is false. `candies` remains `[1, 1, 1]`.
    *   `i = 2`: `ratings[2] (2) > ratings[1] (0)` is true. `candies[2] <= candies[1]` i.e. `1 <= 1` is true, so `candies[2] = candies[1] + 1 = 2`. `candies` becomes `[1, 1, 2]`.
3.  **Right to Left Traversal:**
    *   `i = 1`: `ratings[1] (0) > ratings[2] (2)` is false. `candies` remains `[1, 1, 2]`.
    *   `i = 0`: `ratings[0] (1) > ratings[1] (0)` is true. `candies[0] <= candies[1]` i.e. `1 <= 1` is true, so `candies[0] = candies[1] + 1 = 2`. `candies` becomes `[2, 1, 2]`.
4.  **Summation:** `2 + 1 + 2 = 5`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(n)**, where n is the number of children (length of the `ratings` array), because we iterate through the `ratings` array twice.
*   **Space Complexity:** **O(n)**, where n is the number of children, because we use an auxiliary array `candies` of the same size as the input array to store the number of candies each child receives.
    