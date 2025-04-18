import java.util.ArrayList;
import java.util.List;

class Solution {
    private final List<String> results = new ArrayList<>();

    public Solution() {
        results.add(""); 
        results.add("1");
    }

    private void generateUpTo(int n) {
        while (results.size() <= n) {
            String prev = results.get(results.size() - 1);
            char[] chars = prev.toCharArray();
            StringBuilder curr = new StringBuilder();

            int count = 1;
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] == chars[i - 1]) {
                    count++;
                } else {
                    curr.append(count).append(chars[i - 1]);
                    count = 1;
                }
            }
            curr.append(count).append(chars[chars.length - 1]);
            results.add(curr.toString());
        }
    }

    public String countAndSay(int n) {
        generateUpTo(n);
        return results.get(n);
    }
}
