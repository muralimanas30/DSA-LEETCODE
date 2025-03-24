class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int count = 0;
        int rows = matrix.length, cols = matrix[0].length;

        for (int i = 1; i < cols; i++)  
            matrix[0][i] += matrix[0][i - 1];

        for (int i = 1; i < rows; i++)  
            matrix[i][0] += matrix[i - 1][0];

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                matrix[i][j] += matrix[i - 1][j] + matrix[i][j - 1] - matrix[i - 1][j - 1];
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = i; k < rows; k++) {
                    for (int l = j; l < cols; l++) {
                        int value = matrix[k][l];

                        if (i > 0) value -= matrix[i - 1][l];
                        if (j > 0) value -= matrix[k][j - 1];
                        if (i > 0 && j > 0) value += matrix[i - 1][j - 1];

                        if (value == target) count++;
                    }
                }
            }
        }

        return count;
    }
}
