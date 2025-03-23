class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String , ArrayList<String>> map = new HashMap();
        for(String str : strs){
            String sortedStr = sortString(str);
            map.putIfAbsent(sortedStr,new ArrayList<String>());
            map.get(sortedStr).add(str);
        }
        return new ArrayList<>(map.values());
    }
    public String sortString(String str){
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
    
}