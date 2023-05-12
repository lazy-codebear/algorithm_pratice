package leetcode;

import java.util.HashMap;

public class NO_1_50 {
    // NO.2
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
    public static int lengthOfLongestSubstring(String s) {
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

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(9);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(9);
        ListNode node6 = new ListNode(9);
        ListNode node7 = new ListNode(9);
        ListNode node8 = new ListNode(9);
        ListNode node9 = new ListNode(9);
        ListNode node10 = new ListNode(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node9.next = node10;
        lengthOfLongestSubstring("dvdf");
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
