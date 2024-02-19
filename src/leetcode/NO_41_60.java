package leetcode;
import java.util.*;

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

    // 151.反转字符串中的单词 还可以从后向前遍历，找到单词开始与结束的位置加入StringBuilder
    public String reverseWords(String s) {
        StringBuilder str = removeSpace(s);
        reverseStr(str, 0, str.length() - 1);
        reverseWord(str);
        return str.toString();
    }
    public StringBuilder removeSpace(String s){
        StringBuilder str = new StringBuilder();
        int start = 0, end = s.length() - 1;
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;
        while (start <= end){
            char temp = s.charAt(start);
            if (temp != ' ' || s.charAt(start - 1) != ' '){
                str.append(temp);
            }
            start++;
        }
        return str;
    }

    public void reverseStr(StringBuilder str, int start, int end){
        while (start < end){
            char temp = str.charAt(start);
            str.setCharAt(start++, str.charAt(end));
            str.setCharAt(end--, temp);
        }
    }
    public void reverseWord(StringBuilder str){
        int start = 0, end = 0;
        while (end < str.length()){
            if (str.charAt(end) != ' '){
                end++;
            }
            if(end == str.length() || str.charAt(end) == ' '){
                reverseStr(str, start, end - 1);
                start = end + 1;
                end++;
            }
        }
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

    // 344.反转字符串
    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j){
            char temp = s[i];
            s[i++] = s[j];
            s[j--] = temp;
        }
    }

    // 454.四数相加 2
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < nums1.length; i++){
            for (int j = 0; j < nums2.length; j++){
                int temp = nums1[i] + nums2[j];
                if (map.containsKey(temp)){
                    map.put(temp, map.get(temp) + 1);
                }else {
                    map.put(temp, 1);
                }
            }
        }
        for (int i = 0; i < nums3.length; i++){
            for (int j = 0; j < nums4.length; j++){
                int target = nums3[i] + nums4[j];
                if (map.containsKey(-target)){
                    result += map.get(-target);
                }
            }
        }
        return result;
    }

    // 514.反转字符串 2
    public String reverseStr(String s, int k) {
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i += 2 * k){
            int start = i;
            int end = Math.min(s.length() - 1, start + k - 1);
            while (start < end){
                char temp = str[start];
                str[start++] = str[end];
                str[end--] = temp;
            }
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        NO_41_60 test = new NO_41_60();
        test.reverseWords("  hello   world  ");
    }
}
