# 00051 - N Queens
    
**Language:** Java  
**Runtime:** 2 ms (Beats 87.10% of users)  
**Memory:** 44.7 MB (Beats 88.07% of users)  

## 📝 **LeetCode Problem**

| 🔢 Problem Number | 📌 Title   | 🔗 Link                                  |
| ------------------ | ----------- | ---------------------------------------- |
| 51                 | N-Queens | [LeetCode Problem](https://leetcode.com/problems/n-queens/) |

---

## 💡 **Problem Explanation**

The N-Queens problem requires you to place N chess queens on an N×N chessboard such that no two queens threaten each other. This means no two queens can be in the same row, column, or diagonal. The task is to find all distinct solutions to this placement problem.

## 📊 **Algorithm**

*   Initialize an empty N×N chessboard.
*   Use backtracking to explore all possible placements of queens row by row.
*   For each row, iterate through each column and check if placing a queen in that cell is valid (i.e., not under attack by any other queen).
*   If a placement is valid, place the queen and recursively try to place queens in the next row.
*   If the recursive call finds a solution (all N queens are placed), add the board configuration to the result.
*   Backtrack by removing the queen from the current cell and try the next column.
*   The `isValid` function checks if placing a queen at board[row][col] is safe, by checking the column and diagonals of the board.

## 🔥 **Code Implementation**

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(board, 0, n, n);
        return new ArrayList<>(result);
    }

    public void backtrack(char[][] board, int row, int N, int queens) {
        if (queens == 0) {
            List<String> b = new ArrayList<>();

            for (char[] r : board) {
                b.add(new String(r));
            }
            result.add(b);
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                backtrack(board, row + 1, N, queens - 1);
                board[row][col] = '.'; // Backtrack
            }
        }
    }

    public boolean isValid(char[][] board, int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }
}
```

## 📊 **ASCII Representation**

For N = 4, one possible solution can be represented as:

```
.Q..
...Q
Q...
..Q.
```

## 📊 **WORKING**

Let's trace the execution for N = 4:

1.  **Initial board**:

    ```
    ....
    ....
    ....
    ....
    ```

2.  **First row (row 0)**: Try placing a queen in each column.

    *   `col = 0`: Not valid (later rows will conflict).
    *   `col = 1`: Valid. Place 'Q' at board[0][1].

        ```
        .Q..
        ....
        ....
        ....
        ```

3.  **Second row (row 1)**:

    *   `col = 0`: Valid. Place 'Q' at board[1][0].

        ```
        .Q..
        Q...
        ....
        ....
        ```

    *   **Third row (row 2)**:
        *   `col = 2`: Valid. Place 'Q' at board[2][2].

        ```
        .Q..
        Q...
        ..Q.
        ....
        ```

    *   **Fourth row (row 3)**:
        *   `col = 3`: Valid. Place 'Q' at board[3][3].

        ```
        .Q..
        Q...
        ..Q.
        ...Q
        ```
        This is not a valid configuation and gets discarded. Then it goes back to the third row and changes col to 3
    *   **Third row (row 2)**:
        *   `col = 3`: Valid. Place 'Q' at board[2][3].

        ```
        .Q..
        Q...
        ...Q
        ....
        ```

    *   **Fourth row (row 3)**:
        *   `col = 0`: Valid. Place 'Q' at board[3][0].

        ```
        .Q..
        Q...
        ...Q
        Q...
        ```
        This is not a valid configuration and gets discarded. Then it goes back to the second row and changes col to 1

4.  ... and so on until all valid boards are generated.

## 🚀 **Time & Space Complexity**

*   **Time Complexity**: **O(N!)** in the worst-case.  Although the algorithm prunes the search space with `isValid`, it still explores a tree-like structure of possible queen placements, and in the worst case, it could explore a significant portion of the possible permutations. More precisely, it is O(N^N).
*   **Space Complexity**: **O(N^2)** due to the board used for storing the intermediate states and the recursion stack depth. Additionally, the space to store the result which can have at max `N!` possible configurations.
    