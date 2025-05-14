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