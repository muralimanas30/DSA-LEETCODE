# 03477 - Fruits Into Baskets II

**Language:** Java  
**Runtime:** 2 ms (Beats 61.24% of users)  
**Memory:** 44.4 MB (Beats 80.83% of users)  

---

## 📎 External Link

- [LeetCode Problem 3477: Fruits Into Baskets II](https://leetcode.com/problems/fruits-into-baskets-ii/)

---

## 📝 Problem Statement

Given two integer arrays, `fruits` and `baskets`, where each element in `fruits` represents the size of a fruit and each element in `baskets` represents the capacity of a basket, determine how many fruits cannot be placed into baskets.  
- Each basket can hold at most one fruit, and each fruit can be placed in at most one basket.
- A fruit can only be placed in a basket if the basket's capacity is greater than or equal to the fruit's size.

Return the number of fruits that cannot be placed in any basket.

---

## 📚 Example Inputs & Outputs

| Fruits                | Baskets           | Output | Explanation                                                                                  |
|-----------------------|-------------------|--------|----------------------------------------------------------------------------------------------|
| `[1, 2, 3, 2, 1]`     | `[2, 2, 3]`       | `0`    | All fruits can be placed: 1→2, 2→2, 3→3, remaining 2 and 1 have no baskets left.             |
| `[5, 4, 3, 3]`        | `[1, 2, 5, 5]`    | `2`    | 5→5, 3→5, 4 and 3 cannot be placed.                                                         |
| `[2, 2, 2]`           | `[1, 1, 1]`       | `3`    | No basket can hold any fruit.                                                                |
| `[1, 2, 3]`           | `[3, 2, 1]`       | `0`    | 1→1, 2→2, 3→3. All fruits placed.                                                           |

---

## 🏆 Solution Overview

This problem is a variation of the bipartite matching problem, solved here using a greedy approach:
- For each fruit, try to find the first available basket that can hold it.
- Mark both the fruit and the basket as used once matched.
- Count the number of fruits that remain unmatched.

**Why this approach?**
- Simple and effective for small input sizes.
- Greedy matching ensures that each fruit is placed in the first available suitable basket, maximizing placements.

---

## 🧩 Variables & Data Structures

| Name      | Type      | Purpose                                                                 |
|-----------|-----------|-------------------------------------------------------------------------|
| `fruits`  | `int[]`   | Array of fruit sizes.                                                   |
| `baskets` | `int[]`   | Array of basket capacities.                                             |
| `sum`     | `int`     | Counter for the number of unplaced fruits.                              |
| `i, j`    | `int`     | Loop indices for iterating over fruits and baskets.                     |
| `x`       | `int`     | Used in the final loop to count unplaced fruits.                        |

---

## 🛠️ Step-by-Step Algorithm

1. Iterate through each fruit in the `fruits` array.
2. For each fruit, iterate through the `baskets` array:
    - If a basket's capacity is greater than or equal to the fruit's size and the basket is unused (`!=-1`):
        - Place the fruit in the basket.
        - Mark the fruit as placed (`fruits[i]=0`).
        - Mark the basket as used (`baskets[j]=-1`).
        - Break the inner loop.
3. After all fruits are processed, count the number of fruits that are not placed (`fruits[i] != 0`).
4. Return the count.

---

## 🎨 Visual Diagram

### Example: `fruits = [5,4,3,3]`, `baskets = [1,2,5,5]`

```
Initial State:
Fruits:   5   4   3   3
Baskets:  1   2   5   5

Matching Process:
- Fruit 5 → Basket 5 (third basket)
- Fruit 4 → (no available basket)
- Fruit 3 → Basket 5 (fourth basket)
- Fruit 3 → (no available basket)

Final State:
Fruits:   0   4   0   3
Baskets:  1   2  -1  -1

Unplaced Fruits: 4, 3  (Total: 2)
```

#### Table Representation

| Fruit | Basket Chosen | Basket Capacity | Fruits State | Baskets State |
|-------|---------------|----------------|-------------|---------------|
| 5     | Yes           | 5 (index 2)    | 0 4 3 3     | 1 2 -1 5      |
| 4     | No            | -              | 0 4 3 3     | 1 2 -1 5      |
| 3     | Yes           | 5 (index 3)    | 0 4 0 3     | 1 2 -1 -1     |
| 3     | No            | -              | 0 4 0 3     | 1 2 -1 -1     |

---

## 🧮 Step-by-Step Walkthrough

**Input:** `fruits = [5,4,3,3]`, `baskets = [1,2,5,5]`

1. **First Fruit (5):**
    - Baskets: 1 (too small), 2 (too small), 5 (fits, use it).
    - Mark fruit as placed, basket as used.
    - State: `fruits = [0,4,3,3]`, `baskets = [1,2,-1,5]`
2. **Second Fruit (4):**
    - Baskets: 1 (too small), 2 (too small), -1 (used), 5 (fits, but will be used for next 3).
    - No available basket.
    - State: `fruits = [0,4,3,3]`, `baskets = [1,2,-1,5]`
3. **Third Fruit (3):**
    - Baskets: 1 (too small), 2 (too small), -1 (used), 5 (fits, use it).
    - Mark fruit as placed, basket as used.
    - State: `fruits = [0,4,0,3]`, `baskets = [1,2,-1,-1]`
4. **Fourth Fruit (3):**
    - All baskets either too small or used.
    - State: `fruits = [0,4,0,3]`, `baskets = [1,2,-1,-1]`
5. **Count unplaced fruits:** 4 and 3 remain. **Return 2.**

---

## 💻 Java Code Implementation

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

## 🛠️ Programming Workflow

### Logical Flow (Numbered List)

1. For each fruit, try to find a suitable unused basket.
2. If found, mark both as used.
3. After all fruits, count the number of unplaced fruits.
4. Return the count.

### Flowchart (ASCII Art)

```
+-------------------------------+
| 1. For each fruit:            |
|   a. For each basket:         |
|      - If basket fits & unused|
|        - Place fruit          |
|        - Mark basket used     |
|        - Break                |
+---------------+---------------+
                |
                v
+-------------------------------+
| 2. Count unplaced fruits      |
+---------------+---------------+
                |
                v
+-------------------------------+
| 3. Return count               |
+-------------------------------+
```

---

## ⏱️ Complexity Analysis

- **Time Complexity:** O(n * m)  
  - n = number of fruits, m = number of baskets.
  - Each fruit may check every basket.
- **Space Complexity:** O(1)  
  - Modifies input arrays in place, uses only a constant amount of extra space.

---

## 📚 References

- [LeetCode Problem 3477: Fruits Into Baskets II](https://leetcode.com/problems/fruits-into-baskets-ii/)
- [Greedy Algorithms (GeeksforGeeks)](https://www.geeksforgeeks.org/greedy-algorithms/)
- [Bipartite Matching (Wikipedia)](https://en.wikipedia.org/wiki/Matching_(graph_theory))