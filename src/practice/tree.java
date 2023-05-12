package practice;

import java.util.*;

public class tree {
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root.left==null && root.right==null){
            return 1;
        }
        int depth = 1;
        int left_depth = 0;
        int right_depth = 0;
        if (root.left != null){
            left_depth = maxDepth(root.left);
        }
        if (root.right != null){
            right_depth = maxDepth(root.right);
        }
        depth += Math.max(left_depth, right_depth);
        return depth;
    }
    public boolean isValidBST(TreeNode root) {
        if (root == null){
            return false;
        }
        ArrayList<Integer> list = new ArrayList<>();
        getArray(root, list);
        for (int i=1; i<list.size(); i++){
            if (list.get(i-1) >= list.get(i)){
                return false;
            }
        }
        return true;
    }
    public void getArray(TreeNode root, ArrayList<Integer> list){
        if (root.left==null && root.right==null){
            list.add(root.val);
            return;
        }
        if (root.left != null){
            getArray(root.left, list);
        }
        list.add(root.val);
        if (root.right != null){
            getArray(root.right, list);
        }
    }

    //对称二叉树
    public static boolean isSymmetric(TreeNode root) {
        if (root == null){
            return false;
        }
        ArrayList<Integer> left_list = new ArrayList<>();
        ArrayList<Integer> right_list = new ArrayList<>();
        getArray(root.left, left_list, true);
        getArray(root.right, right_list, false);
        if (left_list.size() != right_list.size()){
            return false;
        }
        for (int i=0; i < left_list.size(); i++){
            if (!Objects.equals(left_list.get(i), right_list.get(i))){
                return false;
            }
        }
        return true;
    }

    public static void getArray(TreeNode root, ArrayList<Integer> list, boolean left) {
        if (root == null) {
            list.add(-1000);
            return;
        }else if (root.left==null && root.right==null){
            list.add(root.val);
            return;
        }
        if (left) {
            getArray(root.left, list, true);
            list.add(root.val);
            getArray(root.right, list, false);
        } else {
            getArray(root.right, list, false);
            list.add(root.val);
            getArray(root.left, list, true);
        }
    }
    public boolean isSymmetric_Answer(TreeNode root) {
        if (root == null)
            return true;
        //从两个子节点开始判断
        return isSymmetricHelper(root.left, root.right);
    }

    public boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        //如果左右子节点都为空，说明当前节点是叶子节点，返回true
        if (left == null && right == null)
            return true;
        //如果当前节点只有一个子节点或者有两个子节点，但两个子节点的值不相同，直接返回false
        if (left == null || right == null || left.val != right.val)
            return false;
        //然后左子节点的左子节点和右子节点的右子节点比较，左子节点的右子节点和右子节点的左子节点比较
        return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null){
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> level_queue = new LinkedList<>();
        List<List<Integer>> levelOrder = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        queue.offer(root);
        level_queue.offer(1);
        int now_level = 1;
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            int level = level_queue.poll();
            if (level == now_level){
                list.add(temp.val);
            }else {
                levelOrder.add(list);
                list = new ArrayList<>();
                list.add(temp.val);
                now_level = level;
            }
            if (temp.left != null){
                queue.offer(temp.left);
                level_queue.offer(level+1);
            }
            if (temp.right != null){
                queue.offer(temp.right);
                level_queue.offer(level+1);
            }
        }
        levelOrder.add(list);
        return levelOrder;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length==0){
            return null;
        }
        return creatBST(nums, 0, nums.length-1);
    }
    public TreeNode creatBST(int[] nums, int left, int right){
        int mid =  (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (left != mid){
            root.left = creatBST(nums, left, mid - 1);
        }
        if (mid != right){
            root.right = creatBST(nums, mid + 1, right);
        }
        return root;
    }
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2_1 = new TreeNode(2);
        TreeNode node2_2 = new TreeNode(2);
        TreeNode node3_1 = new TreeNode(3);
        TreeNode node3_2 = new TreeNode(3);
        TreeNode node4_1 = new TreeNode(4);
        TreeNode node4_2 = new TreeNode(4);
        node1.left = node2_1;
        node1.right = node2_2;
//        node2_1.left = node3_1;
        node2_1.right = node3_1;
        node2_2.left = node3_2;
//        node2_2.right = node3_2;
        isSymmetric(node1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}