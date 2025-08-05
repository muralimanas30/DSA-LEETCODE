# 03477 - Fruits Into Baskets Ii
    
**Language:** Java  
**Runtime:** 2 ms (Beats 61.24% of users)  
**Memory:** 44.4 MB (Beats 80.83% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title                   | 🔗 Link                                                                 |
|-------------------|----------------------------|--------------------------------------------------------------------------|
| 3477              | Fruits Into Baskets II     | [LeetCode Problem](https://leetcode.com/problems/fruits-into-baskets-ii/) |

---

## 💡 **Problem Explanation**

Imagine you're a fruit picker, and you have a line of fruit trees (`fruits` array), and a set of baskets (`baskets` array). Each fruit has a size, and each basket has a capacity.  You want to put as many fruits into baskets as possible.  Each basket can hold at most one fruit.  The goal is to find out how many fruits you *cannot* place in a basket.

**Example:**

`fruits = [1, 2, 3, 2, 1]`
`baskets = [2, 2, 3]`

The optimal strategy is as follows:

1.  Put the fruit of size `1` into the first basket of size `2`.
2.  Put the fruit of size `2` into the second basket of size `2`.
3.  Put the fruit of size `3` into the basket of size `3`.
4.  Put the fruit of size `2` into the a basket no longer available
5.  Put the fruit of size `1` into the a basket no longer available

Therefore, all fruits can be placed and the answer is 0.

Another Example:

`fruits = [5,4,3,3]`
`baskets = [1,2,5,5]`

The optimal strategy is as follows:

1.  Put the fruit of size `5` into the third basket of size `5`.
2.  Cannot place the fruit of size `4` into the a basket
3.  Put the fruit of size `3` into the forth basket of size `5`.
4.  Cannot place the fruit of size `3` into the a basket

Therefore,  2 fruits cannot be placed and the answer is 2.

---

## 📊 **Algorithm**

*   Iterate through each fruit in the `fruits` array.
*   For each fruit, iterate through the `baskets` array.
*   If a basket's capacity is greater than or equal to the fruit's size and the basket is not already used (`!=-1`), place the fruit in the basket.
    *   Mark the fruit as placed by setting its value in the `fruits` array to `0`.
    *   Mark the basket as used by setting its value in the `baskets` array to `-1`.
    *   Break the inner loop as fruit is placed.
*   After iterating through all fruits, count the number of fruits that are still not placed (i.e., their value is not `0`).
*   Return the count of unplaced fruits.

---

## 🔥 **Code Implementation**

```java
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        for(int i=0;i<fruits.length;i++){
            for(int j=0;j<baskets.length;j++){
                if(baskets[j]>=fruits[i] && baskets[j]!=-1){
                    fruits[i]=0;
                    baskets[j]=-1;
                    break;
                }
            }
        }
        int sum = 0;
            for(int  x : fruits) if(x!=0) sum += 1; return sum;
    }
}
```

---

## 📊 **WORKING**

Let's trace the code with the example:

`fruits = [5,4,3,3]`
`baskets = [1,2,5,5]`

1.  **First Fruit (5):**
    *   Loop through `baskets`.
    *   `baskets[0] = 1 < 5`, `baskets[1] = 2 < 5`, `baskets[2] = 5 >= 5`.
    *   Place fruit `5` in `baskets[2]`. `fruits` becomes `[0, 4, 3, 3]`, `baskets` becomes `[1, 2, -1, 5]`.

2.  **Second Fruit (4):**
    *   Loop through `baskets`.
    *   `baskets[0] = 1 < 4`, `baskets[1] = 2 < 4`, `baskets[2] = -1 < 4`.
    *   No available basket found.

3.  **Third Fruit (3):**
    *   Loop through `baskets`.
    *   `baskets[0] = 1 < 3`, `baskets[1] = 2 < 3`, `baskets[2] = -1 < 3`,`baskets[3] = 5 >= 3`
    *   Place fruit `3` in `baskets[3]`. `fruits` becomes `[0, 4, 0, 3]`, `baskets` becomes `[1, 2, -1, -1]`.

4.  **Forth Fruit (3):**
    *   Loop through `baskets`.
    *   `baskets[0] = 1 < 3`, `baskets[1] = 2 < 3`, `baskets[2] = -1 < 3`,`baskets[3] = -1 < 3`
    *   No available basket found.

Finally, `fruits = [0, 4, 0, 3]`. Two fruits (4 and 3) were not placed.  The function returns 2.

---

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** The code has nested loops. The outer loop iterates through the `fruits` array (length `n`), and the inner loop iterates through the `baskets` array (length `m`). Therefore, the time complexity is **O(n*m)**.

*   **Space Complexity:** The code modifies the input arrays in place. It uses a single extra variable `sum`. Therefore, the space complexity is **O(1)** (constant).
    