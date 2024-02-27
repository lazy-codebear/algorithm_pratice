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

    // 98.验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        List<Integer> val = new ArrayList<>();
        inOrderTraverse_98(root, val);
        for (int i = 1; i < val.size(); i++){
            if (val.get(i) <= val.get(i - 1)){
                return false;
            }
        }
        return true;
    }
    public void inOrderTraverse_98(TreeNode root, List<Integer> val){
        if (root == null) return;
        inOrderTraverse_98(root.left, val);
        val.add(root.val);
        inOrderTraverse_98(root.right, val);
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

    // 104.二叉树的最大深度
    public int maxDepth(TreeNode root) {
        int depth = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) return depth;
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++){
                TreeNode temp = queue.poll();
                if (temp.left != null){
                    queue.add(temp.left);
                }
                if (temp.right != null){
                    queue.add(temp.right);
                }
            }
        }
        return depth;
    }
    // 使用递归法
    public int maxDepth_ans(TreeNode root) {
        if (root == null)   return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        //左右子树的最大值+1
        return Math.max(left,right) + 1;
    }

    // 105.从前序和中序遍历序列构造二叉树
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if (len == 0) return null;
        return build2(preorder, 0, len, inorder, 0, len);
    }
    public TreeNode build2(int[] preorder, int pre_left, int pre_right, int[] inorder, int in_left, int in_right){
        if (pre_left == pre_right){
            return null;
        }
        TreeNode root = new TreeNode(preorder[pre_left]);
        int in_index = in_left;
        for (; in_index < inorder.length; in_index++){
            if (inorder[in_index] == root.val){
                break;
            }
        }
        root.left = build2(preorder, pre_left + 1, pre_left + 1 + (in_index - in_left), inorder, in_left, in_index);
        root.right = build2(preorder, pre_left + 1 + (in_index - in_left), pre_right, inorder, in_index + 1, in_right);
        return root;
    }

    // 106.从中序遍历和后序遍历构建二叉树
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        int len = postorder.length;
        if (len == 0) return null;
        return build1(inorder, 0, len, postorder, 0, len);
    }
    public TreeNode build1(int[] inorder, int in_left, int in_right, int[] postorder, int post_left, int post_right){
        if (post_left == post_right){
            return null;
        }
        TreeNode root = new TreeNode(postorder[post_right - 1]);
        int in_index = in_left;
        for (; in_index < in_right; in_index++){
            if (inorder[in_index] == root.val){
                break;
            }
        }
        root.left = build1(inorder, in_left, in_index, postorder, post_left, post_left + (in_index - in_left));
        root.right = build1(inorder, in_index + 1, in_right, postorder, post_left + (in_index - in_left), post_right - 1);

        return root;
    }

    // 110.平衡二叉树
    public boolean isBalanced(TreeNode root) {
        int result = getDepth(root);
        return result != -1;
    }
    public int getDepth(TreeNode root){
        if (root == null) return 0;

        int left_depth = getDepth(root.left);
        if (left_depth == -1) return -1;    // 保证出现-1时能够一直返回至根节点

        int right_depth = getDepth(root.right);
        if (right_depth == -1) return -1;

        if (Math.abs(left_depth - right_depth) > 1){
            return -1;
        }else {
            return 1 + Math.max(left_depth, right_depth);
        }
    }
    // 111.二叉树的最小深度
    public int minDepth(TreeNode root) {
        int depth = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) return depth;
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++){
                TreeNode temp = queue.poll();
                if (temp.left != null){
                    queue.add(temp.left);
                }
                if (temp.right != null){
                    queue.add(temp.right);
                }
                if (temp.left == null && temp.right == null){
                    return depth;
                }
            }
        }
        return depth;
    }

    // 112.路径总和
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (targetSum - root.val == 0 && root.right == null && root.left == null) return true;
        boolean left = false, right = false;
        if (root.left != null) {
            left = hasPathSum(root.left, targetSum - root.val);
        }
        if (root.right != null) {
            right = hasPathSum(root.right, targetSum - root.val);
        }
        return right || left;
    }

    // 113.路径总和 2
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        List<Integer> path = new ArrayList<>();
        findSumPaths(root, result, path, targetSum);
        return result;
    }
    public void findSumPaths(TreeNode root, List<List<Integer>> result, List<Integer> path, int targetSum){
        path.add(root.val);
        if (targetSum == root.val && root.left == null && root.right == null){
            List<Integer> temp = new ArrayList<>(path);
            result.add(temp);
            return;
        }
        if (root.left != null){
            findSumPaths(root.left, result, path, targetSum - root.val);
            path.remove(path.size() - 1);
        }
        if (root.right != null){
            findSumPaths(root.right, result, path, targetSum - root.val);
            path.remove(path.size() - 1);
        }
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

    // 257.二叉树的所有路径
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        List<Integer> path = new ArrayList<>();
        findPaths(root, result, path);
        return result;
    }
    public void findPaths(TreeNode root, List<String> result, List<Integer> path){
        path.add(root.val);
        if (root.left == null && root.right == null) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                str.append(path.get(i).toString());
                str.append("->");
            }
            str.append(path.get(path.size() - 1).toString());
            result.add(str.toString());
            return;
        }

        if (root.left != null) {
            findPaths(root.left, result, path);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            findPaths(root.right, result, path);
            path.remove(path.size() - 1);
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

    // 530.二叉搜索树的最小绝对差
    public int getMinimumDifference(TreeNode root) {
        List<Integer> val = new ArrayList<>();
        inorderTraverse_530(root, val);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < val.size(); i++){
            min = Math.min(val.get(i) - val.get(i - 1), min);
        }
        return min;
    }
    public void inorderTraverse_530(TreeNode root, List<Integer> val){
        if (root == null) return;
        inorderTraverse_530(root.left, val);
        val.add(root.val);
        inorderTraverse_530(root.right, val);
    }

    // 654.最大二叉树
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int len = nums.length;

        return getRoot_ans(nums, 0, len - 1);
    }

    public TreeNode getRoot(int[] nums, int left, int right, Map<Integer, Integer> map){    // 复杂解法
        int[] temp = Arrays.copyOfRange(nums, left, right);
        Arrays.sort(temp);
        TreeNode root = new TreeNode(temp[temp.length - 1]);
        if (left == right) return root;
        if (left != map.get(root.val)){
            root.left = getRoot(nums, left, map.get(root.val), map);
        }
        if (map.get(root.val) + 1 != right){
            root.right = getRoot(nums, map.get(root.val) + 1, right, map);
        }
        return  root;
    }

    public TreeNode getRoot_ans(int[] nums, int left, int right){
        if (left > right) return null;
        int index = left;
        int val = 0;
        for (int i = left; i <= right; i++){
            if (nums[i] > val){
                val = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(val);
        root.left = getRoot_ans(nums, left, index - 1);
        root.right = getRoot_ans(nums, index + 1, right);
        return root;
    }

    // 617.合并二叉树
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }

    // 700.二叉搜索书中的搜索
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val){
            return root;
        }
        if (root.val > val){
            return searchBST(root.left, val);
        }else if (root.val < val){
            return searchBST(root.right, val);
        }
        return root;
    }
    public static void main(String[] args) {
        NO_41_60 test = new NO_41_60();
        int[] num1 = {3,2,1,6,0,5};
        int[] num2 = {9,15,7,20,3};
        test.constructMaximumBinaryTree(num1);
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