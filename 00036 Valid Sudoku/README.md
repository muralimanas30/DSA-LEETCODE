# 00036 - Valid Sudoku
    
**Language:** Java  
**Runtime:** 14 ms (Beats 17.22% of users)  
**Memory:** 45.1 MB (Beats 13.32% of users)  

## 📝 **LeetCode Problem**
| 🔢 Problem Number | 📌 Title | 🔗 Link |
|------------------|--------------------------|--------------------------|
| 36 | VALID SUDOKU | [LeetCode Problem](https://leetcode.com/problems/valid-sudoku/) |

---

## 💡 **Problem Explanation**

The "Valid Sudoku" problem asks us to determine if a given 9x9 Sudoku board is valid.  A valid Sudoku board satisfies these conditions:

1.  Each row must contain the digits 1-9 without repetition.
2.  Each column must contain the digits 1-9 without repetition.
3.  Each of the nine 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

**Note:**

*   A Sudoku board (partially filled) could be valid but is not necessarily solvable.
*   Only the filled cells need to be validated according to the mentioned rules.

**Example 1:**

**Input:**

```
board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
```

**Output:** `true`

**Example 2:**

**Input:**

```
board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
```

**Output:** `false` (because the number '8' is repeated in the top left 3x3 block)

## 📊 **Algorithm**

*   Initialize a HashSet to store the numbers encountered in each row, column, and 3x3 sub-box.
*   Iterate through each cell of the board.
*   For each cell, if it contains a number (not '.'), check if the number already exists in the corresponding row, column, and 3x3 sub-box using the HashSet.
*   If the number already exists in any of the row, column, or sub-box, return `false`.
*   If the number doesn't exist, add it to the HashSet with identifiers for row, column, and sub-box.
*   After iterating through all the cells, if no duplicates were found, return `true`.

## 🔥 **Code Implementation**

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> hs = new HashSet<>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]!='.')
                {
                    char temp = board[i][j];
                    if( !hs.add(temp+"in row "+i) ||
                        !hs.add(temp+"in col "+j) || 
                        !hs.add(temp+"in box "+i/3+" "+j/3))
                        return false;
                }    
            }   
        }
    return true;
    }
}
```

## 📊 **ASCII Representation**

Since this problem involves validating a 2D grid (Sudoku board), we can represent a sample board using ASCII:

```
+-------+-------+-------+
| 5 3 . | . 7 . | . . . |
| 6 . . | 1 9 5 | . . . |
| . 9 8 | . . . | . 6 . |
+-------+-------+-------+
| 8 . . | . 6 . | . . 3 |
| 4 . . | 8 . 3 | . . 1 |
| 7 . . | . 2 . | . . 6 |
+-------+-------+-------+
| . 6 . | . . . | 2 8 . |
| . . . | 4 1 9 | . . 5 |
| . . . | . 8 . | . 7 9 |
+-------+-------+-------+
```

## 📊 **WORKING**

Let's take a simplified example to demonstrate how the code works:

```
board =
[["5","3",".",],
 ["6",".",".",],
 [".","9","8"]]
```

1.  **Initialization:** `hs = {}`
2.  **Loop 1 (i=0, j=0):** `board[0][0] = '5'`. We add `"5in row 0"`, `"5in col 0"`, and `"5in box 0 0"` to `hs`. `hs = {"5in row 0", "5in col 0", "5in box 0 0"}`.
3.  **Loop 2 (i=0, j=1):** `board[0][1] = '3'`. We add `"3in row 0"`, `"3in col 1"`, and `"3in box 0 0"` to `hs`. `hs = {"5in row 0", "5in col 0", "5in box 0 0", "3in row 0", "3in col 1"}`.
4.  **Loop 3 (i=0, j=2):** `board[0][2] = '.'`. The code skips because the cell is empty.
5.  **Loop 4 (i=1, j=0):** `board[1][0] = '6'`. We add `"6in row 1"`, `"6in col 0"`, and `"6in box 0 0"` to `hs`. `hs = {"5in row 0", "5in col 0", "5in box 0 0", "3in row 0", "3in col 1", "6in row 1", "6in col 0", "6in box 0 0"}`.

And so on.  If at any point we try to add a duplicate entry (e.g., `"5in row 0"` again), `hs.add()` will return `false`, and the function will immediately return `false`, indicating that the Sudoku board is invalid. If the code completes iterating through the whole board without returning `false`, it means the Sudoku is valid and returns `true`.

## 🚀 **Time & Space Complexity**

*   **Time Complexity:** **O(1)** -  The algorithm iterates through each of the 81 cells in the Sudoku board exactly once. Because the board size is fixed, we consider it constant time.

*   **Space Complexity:** **O(1)** - The HashSet `hs` will, in the worst case, store all the unique numbers present in the Sudoku board across all rows, columns, and 3x3 sub-boxes. The maximum number of unique entries can be limited by a constant, so we treat this as constant space.
    