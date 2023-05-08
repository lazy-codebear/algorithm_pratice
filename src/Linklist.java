import java.util.Stack;

public class Linklist {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null){
            return null;
        }
        int length = 0;
        int i = 1;
        ListNode temp = head;
        while (temp!=null){
            temp = temp.next;
            length++;
        }
        temp = head;
        int index = length-n;
        if (index == 0){
            head = head.next;
            return head;
        }
        while (i != index){
            temp = temp.next;
            i++;
        }
        temp.next = temp.next.next;
        return head;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode tail = new ListNode(head.val, null);
        ListNode temp = head.next;
        ListNode new_head = null;
        while (temp!=null){
            tail = new ListNode(temp.val, tail);
            temp = temp.next;
            if (temp == null){
                new_head = tail;
            }
        }
        return new_head;
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1==null && list2==null){
            return null;
        }else if (list1==null){
            return list2;
        }else if (list2==null){
            return list1;
        }
        ListNode temp1 = list1;
        ListNode temp2 = list2;
        ListNode head = new ListNode();
        ListNode temp = head;
        while (temp1!=null && temp2!=null){
            if (temp1.val <= temp2.val){
                temp.next = new ListNode(temp1.val);
                temp1 = temp1.next;
            }else {
                temp.next = new ListNode(temp2.val);
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1!=null){
            temp.next = temp1;
        }else {
            temp.next = temp2;
        }
        return head.next;
    }
    public boolean isPalindrome(ListNode head) {
        if (head == null){
            return false;
        }else if (head.next == null){
            return true;
        }
        ListNode temp = head;
        int length = 0;
        while (temp!=null){
            temp = temp.next;
            length++;
        }
        temp = head;
        int mid = length / 2;
        Stack<Integer> stack = new Stack<>();
        boolean pop = length % 2 != 0;
        while (temp!=null){
            if (mid > 0){
                stack.push(temp.val);
            }else {
                if (pop){
                    temp = temp.next;
                    pop = false;
                }
                if (stack.pop() != temp.val){
                    return false;
                }
            }
            temp = temp.next;
            mid--;
        }
        return true;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}