class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        char[] colours = new char[n];

        for (int i = 0; i < n; i++) {
            if (colours[i] == '\0') {
                if (!dfs(graph, i, 'R', colours)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int node, char colour, char[] colours) {
        if (colours[node] != '\0') {
            return colours[node] == colour;
        }

        colours[node] = colour;

        for (int neighbor : graph[node]) {
            if (!dfs(graph, neighbor, colour == 'R' ? 'B' : 'R', colours)) {
                return false;
            }
        }
        return true;
    }
}
