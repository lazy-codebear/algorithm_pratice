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
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}