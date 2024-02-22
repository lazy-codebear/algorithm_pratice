package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TraversalTree {
    // 迭代法中序遍历二叉树
    List<Integer> inorderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()){
            TreeNode temp = stack.peek();
            if (temp != null){
                // 将该节点弹出，避免重复操作，下面将右、中、左节点添加到栈中
                stack.pop();
                if (temp.right != null) stack.push(temp.right);
                stack.push(temp);
                stack.push(null);       //中节点访问过但未处理，加入null进行标记
                if (temp.left != null) stack.push(temp.left);
            }else {
                stack.pop();
                temp = stack.pop();
                result.add(temp.val);
            }
        }
        return result;
    }

    // 迭代法实现前序遍历
    List<Integer> preorderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()){
            TreeNode temp = stack.peek();
            if (temp != null){
                stack.pop();
                if (temp.right != null) stack.push(temp.right);     // 右
                if (temp.left != null) stack.push(temp.left);       // 左
                stack.push(temp);                                   // 中
                stack.push(null);
            }else {
                stack.pop();
                temp = stack.pop();
                result.add(temp.val);
            }
        }
        return result;
    }

    // 迭代法实现后序遍历
    List<Integer> postorderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()){
            TreeNode temp = stack.peek();
            if (temp != null){
                stack.pop();
                stack.push(temp);                                   // 中
                stack.push(null);
                if (temp.right != null) stack.push(temp.right);     // 右
                if (temp.left != null) stack.push(temp.left);       // 左
            }else {
                stack.pop();
                temp = stack.pop();
                result.add(temp.val);
            }
        }
        return result;
    }
}
