class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        TreeMap<Integer,Integer> freqT = new TreeMap<>();
        for(int capacity : trainers)
            freqT.put(capacity,freqT.getOrDefault(capacity,0)+1);
        int count = 0;
        for(int player : players){
            Integer key = freqT.ceilingKey(player);
            if(key==null)
                continue;
            if(freqT.get(key)==1)
                freqT.remove(key);
            else
                freqT.put(key,freqT.get(key)-1);
            count++;
        }
        return count;
    }
}
