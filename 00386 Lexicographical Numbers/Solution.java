class Solution {
    public List<Integer> lexicalOrder(int n) {
        ArrayList<Integer> al = new ArrayList<>();
        for(int i=1;i<=n;i++){
            dfs(i,n,al);
        }
        return al;
    }
    public void dfs(int number,int limit,ArrayList<Integer> res){
        if(number>limit || res.size()==limit)    return;
        res.add(number);
        // dfs(number*10,limit,res);
        for(int nextDigit = 0;nextDigit<=9;nextDigit++){
            int nextNum = number*10 + nextDigit;
            if(nextNum<=limit)  dfs(nextNum,limit,res);
            else    break;
        }
    }
}