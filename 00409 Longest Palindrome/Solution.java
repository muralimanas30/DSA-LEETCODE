class Solution {
    public int longestPalindrome(String s) {
        int[] arr = new int[58];
        for (char x : s.toCharArray())
            arr[x - 'A']++;
        int no = 0;
        boolean singleCounted = false;
        for (int f : arr) {
            if ((f & 1) == 1) {
                no += f / 2 * 2;
                if (!singleCounted) {
                    no += 1;
                    singleCounted = true;
                }
            } else {
                no += f;
            }
        }
        return no;
    }
}
