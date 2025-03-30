class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] lastOccurrence = new int[26];
        List<Integer> partitionSizes = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }

        int maxLastIndex = 0, lastPartitionEnd = -1;

        for (int i = 0; i < s.length(); i++) {
            maxLastIndex = Math.max(maxLastIndex, lastOccurrence[s.charAt(i) - 'a']);
            
            if (i == maxLastIndex) {
                partitionSizes.add(i - lastPartitionEnd);
                lastPartitionEnd = i;
            }
        }

        return partitionSizes;
    }
}
