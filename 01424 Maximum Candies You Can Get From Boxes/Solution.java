class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {

        Deque<Integer> toOpen = new LinkedList<>();
        boolean[] boxesOpened = new boolean[status.length];
        int candyCount = 0;

        for(int i = 0; i < initialBoxes.length; i++)
            toOpen.offer(initialBoxes[i]);

        while(!toOpen.isEmpty()) {

            boolean openedAny = false;
            int size = toOpen.size();

            for(int i = 0; i < size; i++) {
                int currentBoxIndex = toOpen.poll();

                if(status[currentBoxIndex] == 0 || boxesOpened[currentBoxIndex]) {
                    toOpen.offer(currentBoxIndex);
                    continue;
                }

                for(int x : keys[currentBoxIndex]) {
                    status[x] = 1;
                }

                candyCount += candies[currentBoxIndex];
                candies[currentBoxIndex] = 0;
                boxesOpened[currentBoxIndex] = true;
                openedAny = true;

                for(int subBoxIndex : containedBoxes[currentBoxIndex])
                    toOpen.offer(subBoxIndex);
            }

            if (!openedAny) break;
        }

        return candyCount;
    }
}
