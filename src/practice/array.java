package practice;

import java.util.*;

/*
    Leetcode 初级算法   数组
 */
public class array {
    /*
        题目：删除排序数组中的重复项
        给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素
        只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
        由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。
        更规范地说，如果在删除重复项之后有 k 个元素，那么nums的前 k 个元素应该保存最终结果。

     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[left] != nums[right]) {
                left++;
                nums[left] = nums[right];
            }
        }
        left++;
        return left;
    }
    /*
        题目：买卖股票的最佳时机
        给你一个整数数组prices ，其中prices[i]表示某支股票第i天的价格。
        在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有一股股票。你也可以先购买，然后在同一天出售。
        返回你能获得的最大利润
     */
    public int maxProfit(int[] prices) {
        if (prices.length==0|| prices==null){
            return 0;
        }
        int income = 0;
        for (int right=1; right<prices.length; right++){
            int left = right - 1;
            int profit = prices[right] - prices[left];
            if (profit > 0){
                income += prices[right] - prices[left];
            }
        }
        return income;
    }
    /*
        题目：旋转数组
        给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     */
    // my method
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (nums.length < 2 || nums == null) {
            return;
        }
        int key = nums.length - k;
        for (int i = 0; i < key / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[key - i - 1];
            nums[key - i - 1] = temp;
        }

        for (int i = 0; i < k / 2; i++) {
            int temp = nums[i + key];
            nums[i + key] = nums[nums.length - (i + 1)];
            nums[nums.length - (i + 1)] = temp;
        }
        for (int i = 0; i < (nums.length / 2); i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length - (i + 1)];
            nums[nums.length - (i + 1)] = temp;
        }
    }
    // answer
    public void rotate_answer(int[] nums, int k) {
        int length = nums.length;
        k %= length;
        reverse(nums, 0, length - 1);//先反转全部的元素
        reverse(nums, 0, k - 1);//在反转前k个元素
        reverse(nums, k, length - 1);//接着反转剩余的
    }
    //把数组中从[start，end]之间的元素两两交换,也就是反转
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    /*
        题目：存在重复元素
        给你一个整数数组nums。如果任一值在数组中出现至少两次，返回true；如果数组中每个元素互不相同，返回false 。
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i=1; i < nums.length; i++){
            if (nums[i-1]==nums[i]){
                return true;
            }
        }
        return false;
        /*
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                //因为集合set中不能有重复的元素，如果有重复的
                //元素添加，就会添加失败
                if (!set.add(num))
                    return true;
            }
            return false;
         */
    }
    /*
        题目：只出现一次的数字
        给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
        你的算法应该具有线性时间复杂度，不使用额外空间。

        思路：异或运算，相异为真，相同为假，所以 a^a = 0 ;  0^a = a
             因为异或运算 满足交换律 a^b^a = a^a^b = b 所以数组经过异或运算，单独的值就剩下了
     */
    public int singleNumber(int[] nums) {
        int output = 0;
        for(int num: nums){
            output = output ^ num;
        }
        return output;
    }

    /*

     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> output = new ArrayList<>();
        int i = 0, j = 0;
        while (i<nums1.length && j<nums2.length){
            if (nums1[i] > nums2[j]){
                j++;
            }else if (nums1[i] < nums2[j]){
                i++;
            }else {
                output.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] temp = new int[output.size()];
        for (int k=0; k < output.size(); k++){
            temp[k] = output.get(k);
        }
        return temp;
    }

    /*
        题目：加一
        给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
        最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
        你可以假设除了整数 0 之外，这个整数不会以零开头。
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len-1; i >= 0; i--) {
            // 考虑每一位是9和不是9两种情况
            if (digits[i] != 9){
                digits[i]++;
                return digits;
            }else {
                digits[i] = 0;
            }
        }
        // 每一位都是9，最终数组长度+1
        int[] out = new int[len+1];
        out[0] = 1;
        return out;
    }

    /*
        题目：移动0
        给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
        请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
    public void moveZeroes(int[] nums) {
        int j = 0;
        boolean flag = false;
        for (int i=0; i<nums.length; i++){
            if (nums[i] == 0 && !flag){
                j = i;
                flag = true;
            }else if (flag && nums[i] != 0){
                nums[j] = nums[i];
                j++;
            }
        }
        for (int i=j; i<nums.length && flag; i++){
            nums[i] = 0;
        }
    }
    public void moveZeroes_answer(int[] nums){
        if (nums == null || nums.length == 0)
            return;
        int index = 0;
        //一次遍历，把非零的都往前挪
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                nums[index++] = nums[i];
        }
        //后面的都是0,
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }

    /*
        题目：两数之和
        给定一个整数数组nums和一个整数目标值target，请你在该数组中找出 和为目标值target的那两个整数，并返回它们的数组下标。
        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
        你可以按任意顺序返回答案。
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i])!= null){
                int num2 = map.get(target-nums[i]);
                return new int[]{i, num2};
            }
            map.put(nums[i], i);
        }
        return new int[]{0, 0};
    }

    /*
        题目：有效的数独
        请你判断一个9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
        数字1-9在每一行只能出现一次。
        数字1-9在每一列只能出现一次。
        数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            int[] valid_row = new int[9];
            int[] valid_col = new int[9];
            int[] valid_box = new int[9];
            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.' && ++valid_row[board[i][j]-'0'-1]>1){
                    return false;
                }
                if (board[j][i] !='.' && ++valid_col[board[j][i]-'0'-1]>1){
                    return false;
                }
                int a = (i/3)*3 + j/3;
                int b = (i%3)*3 + j%3;
                if (board[a][b] != '.' && ++valid_box[board[a][b]-'0'-1]>1){
                    return false;
                }
            }
        }
        return true;
    }
    /*
        上面使用的是二维数组，实际上我们还可以使用一维数组，直接使用位运算来存储，因为在java中一个int类型是32位，用它来表示9个数字足够了。
     */
    public boolean isValidSudoku_answer(char[][] board) {
        int[] line = new int[9];
        int[] column = new int[9];
        int[] cell = new int[9];
        int shift = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //如果还没有填数字，直接跳过
                if (board[i][j] == '.')
                    continue;
                shift = 1 << (board[i][j] - '0');
                int k = (i / 3) * 3 + j / 3;
                //如果对应的位置只要有一个大于0，说明有冲突，直接返回false
                if ((column[i] & shift) > 0 || (line[j] & shift) > 0 || (cell[k] & shift) > 0)
                    return false;
                column[i] |= shift;
                line[j] |= shift;
                cell[k] |= shift;
            }
        }
        return true;
    }

    /*
        题目：旋转图像
        给定一个n×n的二维矩阵matrix表示一个图像。请你将图像顺时针旋转90度。
        你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     */
    public void rotate(int[][] matrix) {
        int size = matrix.length;
        for (int i=0; i<size/2; i++){
            for (int j = i; j < size - i - 1; j++) {
                int temp = matrix[i][j];
                int m = size - j - 1;
                int n = size - i - 1;
                matrix[i][j] = matrix[m][i];
                matrix[m][i] = matrix[n][m];
                matrix[n][m] = matrix[j][n];
                matrix[j][n] = temp;
            }
        }
    }
    public static void main(String[] args) {

    }
}