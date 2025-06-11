class Solution {
    public static int maxDifference(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        int maxOdd = Integer.MIN_VALUE, minEven = Integer.MAX_VALUE;
        for (int f : freq) {
            if (f > 0) {
                if (f % 2 == 0) minEven = Math.min(minEven, f);
                else maxOdd = Math.max(maxOdd, f);
            }
        }
        return maxOdd - minEven;
    }
}