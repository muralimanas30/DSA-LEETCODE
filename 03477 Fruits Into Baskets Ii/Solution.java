class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        for(int i=0;i<fruits.length;i++){
            for(int j=0;j<baskets.length;j++){
                if(baskets[j]>=fruits[i] && baskets[j]!=-1){
                    fruits[i]=0;
                    baskets[j]=-1;
                    break;
                }
            }
        }
        int sum = 0;
            for(int  x : fruits) if(x!=0) sum += 1; return sum;
    }
}