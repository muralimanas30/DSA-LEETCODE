# 00073 - Set Matrix Zeroes
    
**Language:** Java  
**Runtime:** 1 ms (Beats 72.83% of users)  
**Memory:** 45.7 MB (Beats 46.88% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 73 | SET MATRIX ZEROES | [LeetCode Problem](https://leetcode.com/problems/set-matrix-zeroes/) |

---

## 💡 **Problem Explanation**

Given a `m x n` matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Essentially, we need to identify the rows and columns that contain zero, and then nullify the corresponding rows and columns.

**Example:**

```
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
```

In this case, `matrix[1][1]` is 0, so row 1 and column 1 should be set to 0.

```
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
```

Here, `matrix[0][0]` and `matrix[0][3]` are 0, so row 0 and columns 0 and 3 should be set to 0.

## 📊 **Algorithm**

*   Create boolean arrays `r` and `c` to store information about rows and columns containing zero, respectively.
*   Iterate through the matrix to identify cells containing zero. If `matrix[i][j] == 0`, set `r[i] = true` and `c[j] = true`.
*   Iterate through the matrix again. If `r[i]` or `c[j]` is true, set `matrix[i][j] = 0`.

## 🔥 **Code Implementation**

```java
class Solution {
    public void setZeroes(int[][] matrix) {

        boolean[] r = new boolean[matrix.length];
        boolean[] c = new boolean[matrix[0].length];
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    r[i]=true;
                    c[j]=true;
                }
            }
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix[0].length;j++){
                if(r[i] || c[j]){
                    matrix[i][j]=0;
                }
            }
    }
}
```

## 📊 **ASCII Representation**

Consider the input:

```
[[0,1,2,0],
 [3,4,5,2],
 [1,3,1,5]]
```

## 📊 **WORKING**

1.  **Initial State:**

    ```
    [[0,1,2,0],
     [3,4,5,2],
     [1,3,1,5]]
    ```

2.  **Mark Rows and Columns:**

    *   `r[0] = true` because row 0 contains 0.
    *   `c[0] = true` because column 0 contains 0.
    *   `c[3] = true` because column 3 contains 0.

3.  **Final State after setting zeroes:**

    ```
    [[0,0,0,0],
     [0,4,5,0],
     [0,3,1,0]]
    ```

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** O(m\*n), where m is the number of rows and n is the number of columns. We iterate through the matrix twice.
*   **Space Complexity:** O(m+n), because we use two boolean arrays of size m and n to store row and column information respectively.
    