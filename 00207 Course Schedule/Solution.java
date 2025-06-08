class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for(int i=0;i<numCourses;i++)
            graph.add(new ArrayList<>());
        for(int[] needed : prerequisites){
            int u = needed[0];
            int v = needed[1];
            indegree[v]++;
            graph.get(u).add(v);
        }
        int added = numCourses;
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++)
            if(indegree[i]==0){
                q.add(i);
                added--;
            }
        while(!q.isEmpty()){
            int destNode = q.poll();
            for(int adj : graph.get(destNode))
                if(--indegree[adj]==0){
                    q.add(adj);
                    added--;
                }
        }
        return added==0;
    }
}