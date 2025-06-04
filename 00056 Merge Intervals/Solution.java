class Solution {
    public int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] interval : intervals) {
            pq.offer(interval);
        }
        
        ArrayList<int[]> arrs = new ArrayList<>();
        
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (!pq.isEmpty()) {
                int[] second = pq.peek();
                if (first[1] >= second[0]) {
                    pq.poll();
                    pq.offer(new int[]{first[0], Math.max(first[1], second[1])});
                } else {
                    arrs.add(first);
                }
            } else {
                arrs.add(first);
            }
        }
        
        return arrs.toArray(new int[0][]);
    }
}
