class Solution {
    ArrayList<String> res = new ArrayList<>();
    String[] hm = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return res;
        backtrack(0, new StringBuilder(), digits);
        return res;
    }

    public void backtrack(int index, StringBuilder str, String digits) {
        if (index == digits.length()) {
            res.add(str.toString());
            return;
        }
        String x = hm[digits.charAt(index) - '0'];
        for (int i = 0; i < x.length(); i++) {
            str.append(x.charAt(i));
            backtrack(index + 1, str, digits);
            str.setLength(str.length() - 1);
        }
    }
}
