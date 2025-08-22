class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> seen = new HashSet<>();
        long[] factors = {2, 3, 5};

        minHeap.add(1L);
        seen.add(1L);

        long uglyNumber = 1L;
        for (int i = 0; i < n; i++) {
            uglyNumber = minHeap.poll();
            for (long factor : factors) {
                long next = uglyNumber * factor;
                if (seen.add(next)) {
                    minHeap.add(next);
                }
            }
        }

        return (int) uglyNumber;  
    }
}
