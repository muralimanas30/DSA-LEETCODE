class Solution {
    public int uniquePathsIII(int[][] grid) {
        int[] count = new int[]{0};
        int rows = grid.length;
        int cols = grid[0].length;
        int startX = 0, startY = 0;
        int empty = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
                else if(grid[i][j]==0)
                    empty++;
            }
        }
        backtrack(grid, startX, startY, count, empty);
        return count[0];
    }
    
    public void backtrack(int[][] grid, int i, int j, int[] res, int empty) {
        if (!correctPosition(grid, i, j) || grid[i][j] == -1)  
            return;
        if (grid[i][j] == 2 && empty == 0) {
            res[0]++;
            return;
        }
        int temp = grid[i][j];
        grid[i][j] = -1;
        if (temp == 0) empty--;
        backtrack(grid, i, j + 1, res, empty);
        backtrack(grid, i + 1, j, res, empty);
        backtrack(grid, i, j - 1, res, empty);
        backtrack(grid, i - 1, j, res, empty);
        grid[i][j] = temp;
    }
    
    public boolean correctPosition(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }
}
