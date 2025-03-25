import java.util.Arrays;

class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        int[][] forY = rectangles.clone();
        Arrays.sort(rectangles, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(forY, (a, b) -> Integer.compare(a[1], b[1]));
        int prevX = 0, prevY = 0, countX = 0, countY = 0;
        for (int i = 0; i < rectangles.length; i++) {
            if (prevX <= rectangles[i][0]) {
                countX++;
                prevX = rectangles[i][2];
            } else {
                prevX = Math.max(prevX, rectangles[i][2]);
            }
            if (prevY <= forY[i][1]) {
                countY++;
                prevY = forY[i][3];
            } else {
                prevY = Math.max(prevY, forY[i][3]);
            }
            if (countX >= 3 || countY >= 3) return true;
        }
        return false;
    }
}
