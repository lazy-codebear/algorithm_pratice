package leetcode;
import java.util.ArrayList;
import java.util.List;

public class NO_41_60 {
    // 54.螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int lengthX = matrix.length;
        int lengthY = matrix[0].length;
        int startX = 0, startY = 0;
        int offset = 1;
        int loop = Math.min(lengthX, lengthY) / 2;
        while (loop-- > 0){
            int i, j;
            for (j = startY; j < lengthY - offset; j++){
                result.add(matrix[startX][j]);
            }
            for (i = startX; i < lengthX - offset; i++){
                result.add(matrix[i][j]);
            }
            for (;j > startY; j--){
                result.add(matrix[i][j]);
            }
            for (;i > startX; i--){
                result.add(matrix[i][j]);
            }
            startX++;
            startY++;
            offset++;
        }
        if (Math.min(lengthX, lengthY) % 2 == 1){
            if (lengthX < lengthY){
                for (int i = startY; i <= lengthY - offset; i++){
                    result.add(matrix[startX][i]);
                }
            }else {
                for (int i = startX; i <= lengthX - offset; i++){
                    result.add(matrix[i][startY]);
                }
            }
        }
        return result;
    }
    // 59.螺旋矩阵_2
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int startX = 0, startY = 0;
        int loop = n / 2;
        int offset = 1;
        int count = 1;
        while (loop-- > 0){
            int i, j;
            for (j = startY; j < n - offset; j++){
                result[startX][j] = count++;
            }
            for (i = startX; i < n - offset; i++){
                result[i][j] = count++;
            }
            for (;j > startY; j--){
                result[i][j] = count++;
            }
            for (;i > startX; i--){
                result[i][j] = count++;
            }
            startX++;
            startY++;
            offset++;
        }
        if (n % 2 == 1){
            result[n/2][n/2] = count;
        }
        return result;
    }

    // 209.长度最小的子数组
    public int minSubArrayLen(int target, int[] nums) {
        int begin = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            while (sum >= target){
                result = Math.min(result, i - begin + 1);
                sum -= nums[begin++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
