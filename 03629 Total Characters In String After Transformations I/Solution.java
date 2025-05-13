class Solution {
    public int lengthAfterTransformations(String s, int t) {
        int[] arr = new int[26];
        int[] forNext = new int[26];
        for (char x : s.toCharArray()) forNext[x - 'a']++;
        int mod = 1000000007;
        while (t-- > 0) {
            arr = forNext;
            forNext = new int[26];
            for (int i = 0; i < 26; i++) {
                int count = arr[i];
                if (count > 0) {
                    if (i == 25) {
                        forNext[0] = (forNext[0] + count) % mod;
                        forNext[1] = (forNext[1] + count) % mod;
                    } else {
                        forNext[i + 1] = (forNext[i + 1] + count) % mod;
                    }
                }
            }
        }
        int len = 0;
        for (int x : forNext) len = (len + x) % mod;
        return len;
    }
}
