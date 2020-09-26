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

    // 2nd approach
    public ListNode reverseList(ListNode head) {

        if(head == null || head.next == null) return head;

        ListNode nHead = null;
        ListNode curr = head;

        while(curr != null) {

            ListNode rn = curr;
            curr  = curr.next;
            rn.next = null;

            if(nHead == null) nHead = rn;
            else {

                rn.next = nHead;
                nHead = rn;
            }
        }
        return nHead;
    }

    // Leetcode 234. Palindrome Linked List
    // https://leetcode.com/problems/palindrome-linked-list/

    // In this mid we checked next and next.next condition bcz for odd list
    // we have to neglect middle elt to check palindrome
    public ListNode findMid(ListNode node) {
        
        ListNode slow = node;
        ListNode fast = node;
        
        while(fast.next != null && fast.next.next != null) {
            
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    public ListNode reverseList(ListNode node) {
        
        ListNode prev = null;
        ListNode curr = node;
        
        while(curr != null) {
            ListNode fwd = curr.next;
            curr.next = prev;
            
            prev = curr;
            curr = fwd;
        }
        
        return prev;
    }
    
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        
        ListNode mid = findMid(head);
        ListNode nHead = mid.next;
        mid.next = null;
        
        nHead = reverseList(nHead);
        
        ListNode curr1 = head;
        ListNode curr2 = nHead;
        
        while(curr1 != null && curr2 != null) {
            if(curr1.val != curr2.val) {
                return false; // not palindrome
            }
            
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        
        // correction of list
        nHead = reverseList(nHead);
        mid.next = nHead;
        
        return true;
    }

    // Leetcode 21. Merge Two Sorted Lists
    // https://leetcode.com/problems/merge-two-sorted-lists/

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return l1 != null ? l1 : l2;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        ListNode c1 = l1;
        ListNode c2 = l2;
        
        while(c1 != null && c2 != null) {
            
            if(c1.val < c2.val) {
                
                prev.next = c1;
                c1 = c1.next;
                
            } else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }
        
        if(c1 != null) prev.next = c1; // put whole leftout l1 list on prev 
        else if(c2 != null) prev.next = c2; // put whole leftout l1 list on prev 
        
        return dummy.next;
    }

    // Leetcode 328. Odd Even Linked List
    // https://leetcode.com/problems/odd-even-linked-list/

    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode eHead = head.next; // even head
        
        ListNode c1 = head; // odd index nodes
        ListNode c2 = head.next; // even index nodes
        
        while(c1.next != null && c2.next != null) {
            
            c1.next = c2.next;
            c1 = c1.next;
            
            c2.next = c1.next;
            c2 = c2.next;
        }
        c1.next = eHead;
        return head;
    }

    // https://www.geeksforgeeks.org/segregate-even-and-odd-elements-in-a-linked-list/

    // Segregate First Even elts then odd elts and return list
    ListNode OddEvenListByValue( ListNode head) {

        if(head == null || head.next == null) return head;

        ListNode oHead = new ListNode(-1);
        ListNode op = oHead; // odd elts pointer

        ListNode eHead = new ListNode(-1);
        ListNode ep = eHead; // even elts pointer

        ListNode curr = head;

        while(curr != null) {

            if(curr.val % 2 == 0) {
                ep.next = curr;
                ep = ep.next;

            } else {
                op.next = curr;
                op = op.next;
            }

            curr = curr.next;
        }

        op.next = null;
        ep.next = null;

        ep.next = oHead.next; // join the lists
        //  ohead.next bcz we have used dummy node also
        return eHead.next; // return even head's next bcz we used dummy value too 
    }

    // Leetcode 61. Rotate List
    // https://leetcode.com/problems/rotate-list/

    public ListNode rotateRight(ListNode head, int k) {
        if(k == 0 || head == null || head.next == null) return head ; // nothing to rotate
        
        ListNode c1 = head;
        ListNode c2 = head;
        
        int len = 0;
        while(c1 != null) {
            len++;
            c1 = c1.next;
        }
        
        c1 = head;
        k = k % len;
        
        // make a distance of k elts in c1 and c2
        while(k-- > 0) c2 = c2.next;
        
        // now move both points
        while(c2.next != null) {
            c1 = c1.next;
            c2 = c2.next;
        }
        
        // now you arrived at exact position (ie. c1 is at (k)th position and c2 at end)
        // so change the pointers
        c2.next = head;
        head = c1.next;
        c1.next = null; // to make it end point
        
        return head;
    }

    // Leetcode 19. Remove Nth Node From End of List
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return null;
        
        ListNode c1 = head, c2 = head;
        
        while(n-- > 0) c2 = c2.next;
        // make a distance of n b/w c1 and c2
        
        if(c2 == null) return head.next;
        // if k == list.size means we have to remove head
        
        while(c2.next != null) {
            // move to the exact point( ie. c1 will be the nth point from end)
            c1 = c1.next;
            c2 = c2.next;
        }
        
        
        c1.next = c1.next.next; // deleting nth node
        return head;
    }

    // Leetcode 143. Reorder List
    // https://leetcode.com/problems/reorder-list/

    // 1. Find the mid, Break into new list
    // 2. Reverse the list
    // 3. Merge both the lists

    public ListNode findMid(ListNode head) {
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    public ListNode reverseList(ListNode head) {
        
        ListNode prev = null;
        ListNode curr = head;
        
        while(curr != null) {
            ListNode fwd = curr.next;
            
            curr.next = prev;
            prev = curr;
            curr = fwd;
        }
        return prev;
    }
    
    public void reorderList(ListNode head) {
        if(head == null) return;
        
        ListNode mid = findMid(head);
        
        ListNode nHead = mid.next;
        mid.next = null;
        
        nHead = reverseList(nHead);
        
        ListNode c1 = head;
        ListNode c2 = nHead;
        
        while(c1 != null && c2 != null) {
            
            ListNode fwd1 = c1.next;
            ListNode fwd2 = c2.next;
            
            c1.next = c2;
            c2.next = fwd1;
            
            c1 = fwd1;
            c2 = fwd2;
        }
    }


    // Leetcode 148. Sort List (Aug-28)
    // https://leetcode.com/problems/sort-list/

    public ListNode sortList(ListNode head) {
        
        if(head == null || head.next == null) return head;
        
        ListNode mid = middleNode_(head);
        ListNode nHead = mid.next;
        mid.next = null;
        
        return mergeTwoLists(sortList(head), sortList(nHead));
    }

    // Leetcode 23. Merge k Sorted Lists

    // 1. Using PriorityQueue Time :- O(NLogK), Space :- O(N + k)
    public ListNode mergeKLists(ListNode[] lists) {
        
        PriorityQueue<ListNode> pq = new PriorityQueue<> ( (a,b) -> {
            return a.val - b.val;
        });
        
        for(ListNode l: lists) {
            
            if(l != null) pq.add(l);
        }
        ListNode nHead = new ListNode(-1);
        ListNode dummy = nHead;
        while(pq.size() != 0) {
            
            ListNode remNode = pq.poll();
            
            if(remNode.next != null) pq.add(remNode.next);
            
            dummy.next = remNode;
            dummy = dummy.next;
        }
        
        return nHead.next;
    }

    // 2. Using Merge Sort type

    // Here 1 index shows 1 list so in recursion we will merge 2 sorted lists
    // and then return one merged list and at last all lists will be merged

    // Time :- O(nlogk) , Space :- O(1)
    public ListNode mergeKLists_(ListNode[] lists, int si, int ei) {
        if(si == ei) return lists[si];
        
        int mid = (si + ei) / 2;
        
        // extract the lists and combine them using mergeList and return
        
        ListNode left = mergeKLists_(lists, si, mid); 
        ListNode right = mergeKLists_(lists, mid+1, ei);
        
        return mergeLists(left,right);
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        
        if(lists.length == 0) return null;
        
        return mergeKLists_(lists, 0, lists.length-1);
        // using merge sort type 
        // but here 1 index shows 1 list so combine the lists in recursion and return
    }

    // Leetcode 141. Linked List Cycle
    // https://leetcode.com/problems/linked-list-cycle/

    // See ScreenShot 6276 for derivation
    // LevelUP (29AUG)
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null) {
            
            slow = slow.next;
            fast = fast.next.next;
            
            if( slow == fast ) return true;
            // both met again so there is cycle
        }
        // no cycle found
        return false;
    }
    // Leetcode 142. Linked List Cycle II
    // https://leetcode.com/problems/linked-list-cycle-ii/

    // LevelUP (29Aug) See ss6278
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null) {
            
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow == fast) break;
        }
        
        if(slow != fast) return null;
        // no cycle is present
        
        // now find the starting point of cycle by putting slow at head
        // and move both fast and slow and where they meet will be the point
        slow = head;
        
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next; // only move by one step here
        }
        
        return slow;
        
    }

    // Leetcode 160. Intersection of Two Linked Lists
    // https://leetcode.com/problems/intersection-of-two-linked-lists/

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        
        ListNode tail = headA;
        
        while(tail.next != null) tail = tail.next;
        
        tail.next = headB; // connect the lists to make a cycle
        // now detect the cycle point ans return that point
        
        ListNode ans = detectCycle(headA);
        
        // now correct the nodes (ie. break the nodes again)
        tail.next = null;
        
        return ans;
    }

    // Leetcode 92. Reverse Linked List II (In range)
    // https://leetcode.com/problems/reverse-linked-list-ii/

    // 1. (FAR FAR EASY THAN LEVELUP'S CODE)------------------------
    ListNode nHead = null, nTail = null;
    public void addFirst(ListNode node) {
        
        if(nHead == null) {
            nHead = node;
            nTail = node;
            
        } else {
            node.next = nHead;
            nHead = node;
        }
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        
        if(head == null || head.next == null || m == n) return head;
        
        ListNode curr = head;
        ListNode prev = null;
        
        int i = 1;
        
        while(i < m) {
            i++;
            prev = curr;
            curr = curr.next;
        }
        
        while(i <= n) {
            ListNode next = curr.next;
            curr.next = null; // to break the connection
            addFirst(curr);
            curr = next; 
            i++;
        }
        
        if(prev != null) {
            prev.next = nHead;
            nTail.next = curr;
            
        } else {
            head = nHead;
            nTail.next = curr;
        }
        return head;
    }
    // ---------------------------------------------------------------- //
    // 2. LevelUP Code ( Aug29) 
    ListNode th = null, tt = null; // head and tail
    
    public void addFirst(ListNode node) {
        
        if(th == null) {
            th = node;
            tt = node;
        } else {
            node.next = th;
            th = node;
        }
        
    }
    public ListNode reverseBetween(ListNode head, int n, int m) {
        if (head == null || head.next == null || n == m)
            return head;

        int i = 1;
        ListNode curr = head, prev = null;
        
        while(curr!=null && i < n){
            prev = curr;
            curr = curr.next;
            i++;
        }
            
        while(i>=n && i<=m){
            ListNode next = curr.next;
            curr.next = null;

            addFirst(curr);
            curr = next;
            i++;
        }

        // now connect the list after changing
        if(prev!=null){
            
            prev.next = th; // head
            tt.next = curr; //tail
            
        }else{
            // if i == 1 means list is changed from starting
            head = th;
            tt.next = curr;
        }

            
        return head;
    }

    // Leetcode 138. Copy List with Random Pointer
    // https://leetcode.com/problems/copy-list-with-random-pointer/

    // 31stAug
    public void copyNodes(Node head) {
        Node curr = head;
        
        while(curr != null) {
            Node next = curr.next;
            Node newNode = new Node(curr.val);
            
            curr.next = newNode;
            newNode.next = next;
            
            curr = next;
        }
    }
    
    public void setRandom(Node head) {
        
        Node curr = head;
        
        while(curr != null) {
            if(curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
    }
    
    public Node extractList(Node head) {
        Node curr = head;
        
        Node dummy = new Node(-1);
        Node prev = dummy;
        
        while(curr != null) {
            Node next = curr.next.next;
            
            prev.next = curr.next;
            curr.next = next;
            
            prev = prev.next;
            curr = curr.next;
        }
        return dummy.next;
    }
    
    public Node copyRandomList(Node head) {
        copyNodes(head);
        setRandom(head);
        
        return extractList(head);
    }

}