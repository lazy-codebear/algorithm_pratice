package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NO_21_40 {
    // NO.22 括号生成
    public List<String> generateParenthesis(int n) {
        int length = n * 2;
        int count_0 = 1, count_1 = 0;
        List<String> result = new ArrayList<>();
        validParenthesis(count_0, count_1, length, n, "(", result);
        return result;
    }
    public void validParenthesis(int count_0, int count_1, int length, int n, String str, List<String> result){
        if (str.length() == length){
            result.add(str);
            return;
        }
        if (count_0 < n){       // 下一位取'0'
            validParenthesis(count_0 + 1, count_1, length, n, str + '(', result);
        }
        if (count_1 < count_0){      // 下一位取'1'
            validParenthesis(count_0, count_1 + 1, length, n, str + ')', result);
        }
    }


    // NO.23 合并k个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0){
            return null;
        }
        int length = lists.length;;
        PriorityQueue<ListNode> heap = new PriorityQueue<>(length, Comparator.comparingInt(o -> o.val));
        ListNode head = new ListNode();
        ListNode temp = head;
        for (int i = 0; i < length; i++){
            if (lists[i] != null) {
                heap.add(lists[i]);
                lists[i] = lists[i].next;
            }
        }
        while (!heap.isEmpty()){
            ListNode root = heap.poll();
            temp.next = new ListNode(root.val);
            temp = temp.next;
            if (root.next != null){
                heap.add(root.next);
            }
        }
        return head.next;
    }

    // NO.24 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode new_head = new ListNode();
        new_head.next = head;
        ListNode node1 = head, node2 = node1.next, prev = new_head;
        while (node2 != null){
            swap(node1, node2);
            prev.next = node2;
            prev = node1;
            node1 = node1.next;
            if (node1 != null){
                node2 = node1.next;
            }else {
                break;
            }
        }
        return new_head.next;
    }
    public void swap(ListNode node1, ListNode node2){
        node1.next = node2.next;
        node2.next = node1;
    }
    public static void main(String[] args) {
        NO_21_40 test = new NO_21_40();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        test.swapPairs(node1);

    }
}
