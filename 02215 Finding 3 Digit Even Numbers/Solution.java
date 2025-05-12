class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int[] digitCount = new int[10];
        for (int d : digits) digitCount[d]++;
        
        List<Integer> result = new ArrayList<>();
        
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;
            int d2 = (num / 10) % 10;
            int d3 = num % 10;
            
            digitCount[d1]--; digitCount[d2]--; digitCount[d3]--;
            
            if (digitCount[d1] >= 0 && digitCount[d2] >= 0 && digitCount[d3] >= 0) {
                result.add(num);
            }
            
            digitCount[d1]++; digitCount[d2]++; digitCount[d3]++;
        }

        int[] resArr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) resArr[i] = result.get(i);
        return resArr;
    }
}
