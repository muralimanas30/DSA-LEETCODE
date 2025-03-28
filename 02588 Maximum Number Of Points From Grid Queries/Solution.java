class Solution {
    int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public int[] maxPoints(int[][] grid, int[] queries) {
        int[][] sortedQueries = new int[queries.length][2];
        for(int i = 0; i < queries.length; i++){
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (a,b) -> a[0]-b[0]);
        int[] resultant = new int[queries.length];
        int points = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        q.offer(new int[] {grid[0][0], 0, 0});
        for(int i = 0; i < sortedQueries.length; i++){
            int valQueried = sortedQueries[i][0];
            int pos = sortedQueries[i][1];
            while(!q.isEmpty() && q.peek()[0] < valQueried){
                points++;
                int[] info = q.poll();
                int cellVal = info[0];
                int row = info[1];
                int col = info[2];
                grid[row][col] = -1;
                for(int j = 0; j < dir.length; j++){
                    int newrow = row + dir[j][0];
                    int newcol = col + dir[j][1];
                    if(newrow < 0 || newcol < 0 || newrow >= grid.length || newcol >= grid[0].length){continue;}
                    if(grid[newrow][newcol] != -1){
                        q.offer(new int[] {grid[newrow][newcol], newrow, newcol});
                        grid[newrow][newcol] = -1;
                    }
                }
            }
            resultant[pos] = points;

        }

        return resultant;
    }
}