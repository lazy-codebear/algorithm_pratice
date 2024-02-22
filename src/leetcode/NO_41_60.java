package leetcode;
import java.util.*;

public class NO_41_60 {
    // 42.接雨水 使用单调递减栈，栈头元素作为底部
    public int trap(int[] height) {
        int result = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < height.length; i++){
            if (!deque.isEmpty()){
                while (!deque.isEmpty() && height[deque.peekLast()] < height[i]){
                    int bottom_index = deque.pollLast();
                    if (!deque.isEmpty()){
                        int height1_index = deque.peekLast();
                        int height1 = height[height1_index];
                        int height2 = height[i];
                        if (height1 > height2){
                            result += (height2 - height[bottom_index]) * (i - height1_index - 1);
                        }else {
                            result += (height1 - height[bottom_index]) * (i - height1_index - 1);
                        }
                    }
                }
                while (!deque.isEmpty() && height[deque.peekLast()] <= height[i])
                    deque.pollLast();
                deque.offerLast(i);
            }else {
                deque.offerLast(i);
            }
        }
        return result;
    }

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

    //  71.简化路径
    public String simplifyPath(String path) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        stack.push(path.charAt(0));
        for (int i = 1; i < path.length(); i++){
            char temp = path.charAt(i);
            if (temp == '/' && !stack.isEmpty() && temp == stack.peek()){
                continue;
            } else if (temp == '.' && stack.peek() == '/'){
                int dot_count = 0;
                while (i + dot_count < path.length() && path.charAt(i + dot_count) == '.'){
                    dot_count++;
                }
                if (dot_count == 2 && (i + dot_count >= path.length() || path.charAt(i + dot_count) == '/')){
                    i += 1;
                    stack.pop();
                    while (!stack.isEmpty() && stack.pop() != '/');
                    continue;
                }else if (dot_count == 1 && (i + dot_count >= path.length() || path.charAt(i + dot_count) == '/')){
                    continue;
                }else {
                    for (int j = 0; j < dot_count; j++) {
                        stack.push('.');
                    }
                    i += dot_count - 1;
                    continue;
                }
            }
            stack.push(path.charAt(i));
        }
        if (!stack.isEmpty() && stack.peek() == '/' && stack.size() > 1){
            stack.pop();
        }else if (stack.isEmpty()){
            stack.push('/');
        }
        while (!stack.isEmpty()){
            result.insert(0, stack.pop());
        }
        return result.toString();
    }
    public String simplifyPath_ans(String path) {   // 官方题解，使用split函数分割字符串
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<String>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        StringBuffer ans = new StringBuffer();
        if (stack.isEmpty()) {
            ans.append('/');
        } else {
            while (!stack.isEmpty()) {
                ans.append('/');
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();
    }

    // 94.二叉树中序遍历   使用栈进行中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            if (cur != null){       // 直到遍历到左下方节点，最左下方节点子节点均为null
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }

    // 101.对称二叉树    相当于根节点的左后子树进行毕竟，左子树的左节点等于右子树的右节点，左子树的右节点等于右子树的左节点
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        return compare(root.left, root.right);
    }
    public boolean compare(TreeNode left, TreeNode right){
        if (left == null && right != null)return false;
        else if (left != null && right == null) return false;
        else if (left == null && right == null) return true;
        else if (left.val != right.val) return false;
        // 此时左右节点都不为空且数值相同
        boolean bool1 = compare(left.left, right.right);
        boolean bool2 = compare(left.right, right.left);
        return bool1 && bool2;
    }

    // 102.二叉树层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return result;
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp_result = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode temp = queue.poll();
                temp_result.add(temp.val);
                if (temp.left != null){
                    queue.add(temp.left);
                }
                if (temp.right != null){
                    queue.add(temp.right);
                }
            }
            result.add(temp_result);
        }
        return result;
    }
    // 144.二叉树前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }
    public void traversal(TreeNode node, List<Integer> result){
        if (node == null) return;

        traversal(node.left, result);
        result.add(node.val);
        traversal(node.right, result);

    }

    // 145.二叉树后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    // 150.逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++){
            String temp = tokens[i];
            if (temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/")){
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.add(compute(num1, num2, temp.charAt(0)));
            }else {
                stack.add(Integer.parseInt(temp));
            }
        }
        return stack.pop();
    }
    public int compute(int num1, int num2, char opt){
        if (opt == '+'){
            return num1 + num2;
        } else if (opt == '-') {
            return num1 - num2;
        }else if (opt == '*'){
            return num1 * num2;
        }else {
            return num1 / num2;
        }
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

    // 226.反转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return root;
        }
        if (root.left != null){
            invertTree(root.left);
        }
        if (root.right != null){
            invertTree(root.right);
        }
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        return root;
    }

    // 239.滑动窗口的最大值 使用Deque模拟一个单调队列，保持队列头始终为最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        NewQueue my_queue = new NewQueue();
        int count = 0;
        for (int i = 0; i < k; i++) {
            my_queue.push(nums[i]);
        }
        result[count++] = my_queue.front();
        for (int i = k; i < nums.length; i++){
            my_queue.pop(nums[i - k]);
            my_queue.push(nums[i]);
            result[count++] = my_queue.front();
        }
        return result;
    }
    class NewQueue{
        Deque<Integer> deque;
        public NewQueue(){
            deque = new LinkedList<>();
        }
        public void push(int a){
            if (deque.isEmpty()){
                deque.offerFirst(a);
            }else {
                while (!deque.isEmpty() && deque.peekLast() < a){
                    deque.pollLast();
                }
                deque.offerLast(a);
            }
        }
        public void pop(int value){
            if (!deque.isEmpty() && deque.peekFirst() == value)
                deque.pollFirst();
        }
        public int front(){
            return deque.peekFirst();
        }
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

    // 347.前K个高频元素
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> count_map = new HashMap<>();
        for (int num : nums) {
            if (count_map.containsKey(num)) {
                count_map.replace(num, count_map.get(num) + 1);
            } else {
                count_map.put(num, 1);
            }
        }
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);
        for (Map.Entry<Integer, Integer> entry: count_map.entrySet()) {
            priorityQueue.add(new Pair(entry.getKey(), entry.getValue()));
        }
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll().num;
        }
        return result;
    }
    class Pair{
        int num;
        int count;
        public Pair(int num, int count){
            this.num = num;
            this.count = count;
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

    // 459.重复的子字符串
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        int[] next = new int[len];
        getNext(next, s);
        int end = next[len - 1] + 1;
        if (end != 0 && len % (len - end) == 0){
            return true;
        }
        return false;
    }
    public void getNext(int[] next, String s){
        int j = -1;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) {
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)){
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j + 1)){
                j++;
            }
            next[i] = j;
        }
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
        TreeNode node = new TreeNode(1);
        test.isSymmetric(node);
    }
}

// 232.用栈实现队列
class MyQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int peek() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack2.isEmpty();
    }
}

// 235.用队列实现栈 可以仅使用一个队列实现，将队列中除最后一个元素外再次加入队列
class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.offer(x);
    }

    public int pop() {
        int res = 0;
        while (!queue1.isEmpty()){
            if (queue1.size() == 1){
                res = queue1.poll();
            }else {
                queue2.offer(queue1.poll());
            }
        }
        while (!queue2.isEmpty()){
            queue1.offer(queue2.poll());
        }
        return res;
    }

    public int top() {
        int res = 0;
        while (!queue1.isEmpty()){
            queue2.offer(queue1.poll());
            if (queue1.size() == 1){
                res = queue1.peek();
            }
        }
        while (!queue2.isEmpty()){
            queue1.offer(queue2.poll());
        }
        return res;
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}