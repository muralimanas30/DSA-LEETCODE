class Solution {
    public int countPaths(int n, int[][] roads) {
        int[][] graph = new int[n][n];
        for (int[] edge : roads) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }

        long[] distances = new long[n];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[0] = 0;

        long[] ways = new long[n];
        ways[0] = 1;

        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        heap.add(new long[]{0, 0});

        perform(heap, distances, graph, ways);

        return (int) (ways[n - 1] % (1000000007));
    }

    public void perform(PriorityQueue<long[]> heap, long[] distances, int[][] graph, long[] ways) {
        while (!heap.isEmpty()) {
            long[] sub = heap.poll();
            int currNode = (int) sub[1];
            long distFromSrc = sub[0];

            if (distFromSrc > distances[currNode]) continue;

            for (int i = 0; i < graph.length; i++) {
                if (graph[currNode][i] > 0) {
                    long newDistance = graph[currNode][i] + distFromSrc;
                    if (newDistance < distances[i]) {
                        distances[i] = newDistance;
                        ways[i] = ways[currNode];
                        heap.add(new long[]{newDistance, i});
                    } else if (newDistance == distances[i]) {
                        ways[i] = (ways[i] + ways[currNode]) % (1000000007);
                    }
                }
            }
        }
    }
}

    // public void dfs(int[][] graph,boolean[] visited,int currNode,int end,int
    // currTime,int[] min){
    // if(visited[currNode]) return;
    // if(currTime>min[0]) return;
    // if(end==currNode){
    // if(currTime<min[0]){
    // min[0] = currTime;
    // min[1] = 1;
    // }
    // else
    // min[1]++;
    // }
    // visited[currNode] = true;
    // for(int i=0;i<graph.length;i++){
    // if(!visited[i] && graph[currNode][i]>0){
    // dfs(graph,visited,i,end,currTime+graph[currNode][i],min);
    // }
    // }
    // visited[currNode] = false;
    // }
