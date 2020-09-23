public class questions {

    public class ListNode {
        int val;
        ListNode next;
        ListNode () {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) { this.val = val; this.next = next;}
    }

    // Normal middle node of LinkedList
    public ListNode middleNode_(ListNode head) {

        if(head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null) {

            slow = slow.next;
            fast = fast.next.next;

        }
        return slow;
    }

    // Leetcode 876
    // https://leetcode.com/problems/middle-of-the-linked-list/submissions/

    public ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }   
        return slow;
    }

    // Leetcode 206 Reverse Linked List
    // https://leetcode.com/problems/reverse-linked-list/

    // 1. LevelUp Code
    public ListNode reverseList(ListNode head) {
        
        if(head == null || head.next == null) return head;
        
        ListNode prev = null;
        ListNode curr = head;
        ListNode fwd = null;
        
        while(curr != null) {
            
            fwd = curr.next; // backup

            curr.next = prev; // change connection

            prev = curr; // movement to next node
            curr = fwd;
        }
        
        return prev;
    }

    // My approach
    public ListNode reverseList(ListNode head) {
        
        if(head == null || head.next == null) return head;
        
        ListNode prev = null;
        ListNode curr = head;
        ListNode fwd = curr.next;
        
        while(fwd != null) {
            
            curr.next = prev;
            prev = curr;
            curr  = fwd;
            fwd = fwd.next;
        }
        curr.next = prev;
        head = curr;
        
        return head;
    }

}