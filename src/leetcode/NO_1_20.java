package leetcode;

import java.util.*;

public class NO_1_20 {
    // NO.2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode temp = head;
        int carry = 0;
        while (l1 != null && l2 != null){
            int sum = l1.val + l2.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            temp.next = new ListNode(val);
            temp = temp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null){
            int sum = l1.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            temp.next = new ListNode(val);
            temp = temp.next;
            l1 = l1.next;
        }
        while (l2 != null){
            int sum = l2.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            temp.next = new ListNode(val);
            temp = temp.next;
            l2 = l2.next;
        }
        if (carry == 1){
            temp.next =new ListNode(carry);
        }
        return head.next;
    }

    // NO.3
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> char_set = new HashMap<>();
        int max_length = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++){
            if (!char_set.containsKey(s.charAt(i))){
                char_set.put(s.charAt(i), i);
                length++;
            }else {
                if (length > max_length){
                    max_length = length;
                }
                length = 0;
                i = char_set.get(s.charAt(i));
                char_set.clear();
            }
        }
        return Math.max(max_length, length);
    }

    // NO.4
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int left1 = 0, left2 = 0, right1 = nums1.length - 1, right2 = nums2.length - 1;
        while (true) {
            int mid1 = (left1 + right1) / 2;
            int mid2 = (left2 + right2) / 2;
            double mid1_val = findMedian(nums1, mid1);
            double mid2_val = findMedian(nums2, mid2);
            if (left1 == right1 && left2 == right2) {
                return (mid1_val + mid2_val) / 2;
            }
            if (mid1_val == mid2_val) {
                return mid1_val;
            } else if (mid1_val > mid2_val) {
                right1 = mid1 - 1;
                left2 = mid2 + 1;
            } else {
                left1 = mid1 + 1;
                right2 = mid2 - 1;
            }
        }
    }
    public static double findMedian(int[] nums, int mid){
        if (nums.length % 2 == 0){
            return (double) (nums[mid] + nums[mid + 1]) / 2.0;
        }else {
            return nums[mid];
        }
    }
    // NO.5
    public static String longestPalindrome(String s) {
        int length = s.length();
        int begin = 0, end = length - 1;
        String result = "";
        for (int i = 0; i < length; i++){
            for (int j = length - 1; j > i; j--){
                if (s.charAt(i) == s.charAt(j)){
                    begin = i;
                    end = j;
                    if (end - begin < result.length()){
                        break;
                    }
                    boolean flag = true;
                    while (begin < end){
                        if (s.charAt(begin++) != s.charAt(end--)){
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }
        if (result.length() < 2){
            return s.substring(0, 1);
        }
        return result;
    }

    // NO.6
    public String convert(String s, int numRows) {
        if (s.length() <= numRows){
            return s;
        }
        String[] sub_str = new String[numRows];
        Arrays.fill(sub_str, "");
        int line = 0;
        boolean increase = true;
        for (int i = 0; i < s.length(); i++){
            sub_str[line] += s.charAt(i);
            if (line == 0){
                increase = true;
            } else if (line == numRows - 1) {
                increase = false;
            }
            if (increase){
                line++;
            }else {
                line--;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++){
            result.append(sub_str[i]);
        }
        return result.toString();
    }

    // NO.9
    public boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        int temp = x;
        int y = 0;
        while (temp != 0){
            y = y * 10 + temp % 10;
            temp /= 10;
        }
        return x == y;
    }

    // NO.10 正则表达式匹配 hard
    public boolean isMatch(String s, String p) {
        int length1 = s.length(), length2 = p.length();
        boolean[][] dp = new boolean[length1 + 1][length2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= length2; i++){
            char temp = p.charAt(i - 1);
            if ( i > 1){
                dp[0][i] = temp == '*' && dp[0][i - 2];
            }else {
                dp[0][i] = temp == '*';
            }
        }
        for (int i = 1; i <= length1; i++){
            for (int j = 1; j <= length2; j++){
                if (p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i][j - 2];
                    if (!dp[i][j]){
                        dp[i][j] = match(s.charAt(i - 1), p.charAt(j - 2)) && dp[i - 1][j];
                    }
                }else {
                    if (match(s.charAt(i - 1), p.charAt(j - 1))){
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[length1][length2];
    }
    public boolean match(char ch1, char ch2){
        if (ch2 == '.'){
            return true;
        }else return ch1 == ch2;
    }

    // NO.11 盛水最多的容器
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int max_area = 0;
        while (left < right){
            int area = (right - left) * Math.min(height[left], height[right]);
            max_area = Math.max(max_area, area);
            if (height[left] <= height[right]){
                left++;
            }else {
                right--;
            }
        }
        return max_area;
    }

    // NO.12 整数转罗马数字
    public static String intToRoman(int num) {
        int devider = 1000;
        StringBuilder result = new StringBuilder();
        while (devider > 0){
            int temp = num / devider;
            if (temp != 0){
                result.append(getRoman(temp, devider));
            }
            num -= temp * devider;
            devider /= 10;
        }
        return result.toString();
    }
    public static String getRoman(int num, int devider){
        StringBuilder result = new StringBuilder();
        char[] romans = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int index = -1;
        switch (devider){
            case 1000 -> index = 6;
            case 100 -> index = 4;
            case 10 -> index = 2;
            case 1 -> index = 0;
        }
        if (num >= 5){
            if (num < 9){
                result.append(romans[index + 1]);
                while (num > 5){
                    result.append(romans[index]);
                    num--;
                }
            }else {
                result.append(romans[index]);
                result.append(romans[index + 2]);
            }
        }else {
            if (num < 4){
                while (num > 0){
                    result.append(romans[index]);
                    num--;
                }
            }else {
                result.append(romans[index]);
                result.append(romans[index + 1]);
            }
        }
        return result.toString();
    }

    // NO.13 罗马数字转整数
    public int romanToInt(String s) {
        int length = s.length();
        if (length < 2){
            return getNum(s.charAt(0));
        }
        int result = 0;
        for (int i = 0; i < length; i++){
            int num1 = getNum(s.charAt(i));
            int num2 = 0;
            if (i + 1 < length){
                num2 = getNum(s.charAt(i + 1));
            }
            if (num1 < num2){
                result += num2 - num1;
                i++;
            }else {
                result += num1;
            }
        }
        return result;
    }
    public int getNum(char ch){
        return switch (ch) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

    //NO.14 最长公共前缀 practice/string/longestCommonPrefix

    //NO.15 三数之和
    public List<List<Integer>> threeSum(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; i++){
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for (int j = nums.length - 1; j >=0 &&nums[j] >= 0; j--){
                if (j < nums.length - 1 && nums[j] == nums[j + 1]){
                    continue;
                }
                int sum = nums[i] + nums[j];
                if (binarySearch(nums, i + 1, j - 1, -sum) != -1){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(-sum);
                    list.add(nums[j]);
                    result.add(list);
                }
            }
        }
        return result;
    }
    public int binarySearch(int[] nums, int left, int right, int target){
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    // NO.16 最接近的三数之和
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++){
            int left = i + 1, right = nums.length - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(result - target)){
                    result = sum;
                }
                if (sum > target){
                    right--;
                }else if (sum < target){
                    left++;
                }else {
                    return result;
                }
            }
        }
        return result;
    }

    // NO.17 电话号码的字母组合
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0){
            return new ArrayList<>();
        }
        char[][] table = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'},
                {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add("");
        for (int i = digits.length() - 1; i >= 0; i--){
            int num = digits.charAt(i) - 50;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String temp = queue.poll();
                for (int k = 0; k < table[num].length; k++) {
                    queue.add(table[num][k] + temp);
                }
            }
        }
        while (!queue.isEmpty()){
            result.add(queue.poll());
        }
        return result;
    }

    // NO.18 四数之和
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length; j++) {
                int left = j + 1, right = length - 1;
                while (left < right){
                    long sum = (long)nums[i] + (long)nums[j] + (long)nums[left] + (long)nums[right];
                    boolean flag = false;
                    if (sum == target){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        if (result.isEmpty()){
                            result.add(list);
                        }else {
                            for (List<Integer> l : result) {
                                List<Integer> temp1 = new ArrayList<>(list);
                                List<Integer> temp2 = new ArrayList<>(l);
                                temp1.retainAll(l);
                                temp2.removeAll(list);
                                if (temp1.size() == 4 && temp2.size() == 0) {
                                    flag = true;
                                }
                            }
                            if (!flag){
                                result.add(list);
                            }
                        }
                        left++;
                        right--;
                    }else if (sum < target){
                        left++;
                    }else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    // NO.20 有效的括号
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            char temp = s.charAt(i);
            if (temp == '(' || temp == '[' || temp == '{'){
                stack.add(temp);
            }else {
                if (stack.empty()){
                    return false;
                }
                char top = stack.pop();
                if (temp == ')' && temp - 1 != top){
                    return false;
                }else if ((temp == ']' || temp == '}') && temp - 2 != top){
                    return false;
                }
            }
        }
        return stack.empty();
    }
    public static void main(String[] args) {
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(9);
//        ListNode node3 = new ListNode(9);
//        ListNode node4 = new ListNode(9);
//        ListNode node5 = new ListNode(9);
//        ListNode node6 = new ListNode(9);
//        ListNode node7 = new ListNode(9);
//        ListNode node8 = new ListNode(9);
//        ListNode node9 = new ListNode(9);
//        ListNode node10 = new ListNode(9);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
//        node7.next = node8;
//        node9.next = node10;
        int[] nums1 = {1, 4, 7};
        int[] nums2 = {2, 3, 8};
        int[] nums = {1000000000,1000000000,1000000000,1000000000};
        NO_1_20 test = new NO_1_20();
        test.isValid("()");
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
