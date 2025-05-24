class Solution {
    public List<Integer> findWordsContaining(String[] words, char letter) {
        List<Integer> al  = new ArrayList<>();
        for(int i=0;i<words.length;i++)
            if(words[i].indexOf(letter)!=-1)
                al.add(i);
        return al;
    }
}