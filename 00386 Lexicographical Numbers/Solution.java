//METHOD 1
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
        for(int nextDigit = 0;nextDigit<=9;nextDigit++){
            int nextNum = number*10 + nextDigit;
            if(nextNum<=limit)  dfs(nextNum,limit,res);
            else    break;
        }
    }
}
/*
METHOD 2
class Solution {
    public List<Integer> lexicalOrder(int n) {
        String[] strs = new String[n];
        for(int i=1;i<=n;i++){
            strs[i-1]=i+"";
        }
        Arrays.sort(strs);
        List<Integer> res = new ArrayList<>();
        for(String str : strs)
            res.add(Integer.parseInt(str));
        return res;
    }
}
*/