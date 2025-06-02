class Solution {
    public void solveSudoku(char[][] board) {
        HashSet<String> used = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    char c = board[i][j];
                    String row = i + "row" + c;
                    String col = j + "col" + c;
                    int boxIndex = (i / 3) * 3 + (j / 3);
                    String box = boxIndex + "box" + c;
                    used.add(row);
                    used.add(col);
                    used.add(box);
                }
            }
        }

        backtrack(board, used, new boolean[]{false});
    }

    private void backtrack(char[][] board, HashSet<String> used, boolean[] solved) {
        if (solved[0]) return;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (int k = 1; k <= 9; k++) {
                        if (solved[0]) return;

                        char c = (char) (k + '0');
                        String row = i + "row" + c;
                        String col = j + "col" + c;
                        int boxIndex = (i / 3) * 3 + (j / 3);
                        String box = boxIndex + "box" + c;

                        if (!used.contains(row) && !used.contains(col) && !used.contains(box)) {
                            board[i][j] = c;
                            used.add(row);
                            used.add(col);
                            used.add(box);

                            backtrack(board, used, solved);

                            if (solved[0]) return;

                            
                            board[i][j] = '.';
                            used.remove(row);
                            used.remove(col);
                            used.remove(box);
                        }
                    }
                    return; 
                }
            }
        }
        solved[0] = true;
    }
}
