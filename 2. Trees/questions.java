
// Tree ScreenShots Starts from 4677
import java.util.*;

public class questions {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val = x;
        }
    }

    // Leetcode 112. Path Sum
    // Given a binary tree and a sum, determine if the tree has a root-to-leaf
    // path such that adding up all the values along the path equals the given sum.
    public boolean hasPathSum(TreeNode node, int sum) {

        if (node == null)
            return false;

        if (node.left == null && node.right == null && sum - node.val == 0) {
            return true;
            // if root node and sum == 0 that means we found rootToNodePath with given sum
        }

        return hasPathSum(node.left, sum - node.val) || hasPathSum(node.right, sum - node.val);
        // if left condition gets correct then it doesn't check right condition
    }

    // Leetcode 113. Path Sum II
    // Given a binary tree and a sum, find all root-to-leaf paths where each path's
    // sum equals the given sum

    public void pathSum_(TreeNode node, int sum, List<Integer> smallAns, List<List<Integer>> res) {

        if (node == null)
            return;

        if (node.left == null && node.right == null && sum - node.val == 0) {

            List<Integer> ans = new ArrayList<>(smallAns);
            // create new instance of al bcz when moving out of stack elts are removed from
            // stack and we stored the same reference of list due to which we can get empty
            // list
            ans.add(node.val);
            // add own value in list
            res.add(ans);
            return;
        }
        smallAns.add(node.val);
        // add current value

        pathSum_(node.left, sum - node.val, smallAns, res);
        pathSum_(node.right, sum - node.val, smallAns, res);

        smallAns.remove(smallAns.size() - 1);
        // remove current value when moving out of recursion
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        pathSum_(root, sum, smallAns, res);

        return res;
    }

    // Leetcode 124. Binary Tree Maximum Path Sum

    // Given a non-empty binary tree, find the maximum path sum.
    // a path is defined as any sequence of nodes from some starting node to any
    // node in the tree along the parent-child connections. The path must contain at
    // least one node and does not need to go through the root.

    // (You can start from any node till any node to give maximum sum)
    int NTNRes = -(int) 1e8; // Node to Node res (max value)

    public int maxPathSum_(TreeNode root) {
        if (root == null) {
            return 0;
            // if you return -(int) 1e8 then it is also valid because we compared values in
            // conditions
        }

        int lMax = maxPathSum_(root.left); // maximum sum of left subtree
        int rMax = maxPathSum_(root.right); // maximum sum of right subtree

        int max_ = Math.max(lMax, rMax) + root.val;
        // max subtree path + own value

        NTNRes = Math.max(Math.max(NTNRes, root.val), Math.max(max_, lMax + root.val + rMax));
        // max value can be root val, max_ or NtNRes

        return Math.max(max_, root.val);
        // maxPath value can be max_ or own value
    }

    public int maxPathSum(TreeNode root) {

        maxPathSum_(root);
        return NTNRes;
    }

    // Leetcode 863. All Nodes Distance K in Binary Tree
    // https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

        List<Integer> ans = new ArrayList<>();
        kFar(root, target, K, ans);
        return ans;

    }

    public void kDown(TreeNode node, TreeNode block, int k, List<Integer> ans) {

        if (node == null || node == block || k < 0)
            return;

        if (k == 0) {
            ans.add(node.val);
            return;
        }
        kDown(node.left, block, k - 1, ans);
        kDown(node.right, block, k - 1, ans);

    }

    public int kFar(TreeNode node, TreeNode target, int k, List<Integer> ans) {

        if (node == null) {
            return -1;
        }

        if (node == target) {

            kDown(node, null, k, ans);
            return 1; // found and for its parent k is at 1 distance
        }

        int ld = kFar(node.left, target, k, ans);
        if (ld != -1) {

            kDown(node, node.left, k - ld, ans);
            return ld + 1; // for its parent k is at ld + 1
        }

        int rd = kFar(node.right, target, k, ans);
        if (rd != -1) {

            kDown(node, node.right, k - rd, ans);
            return rd + 1;
        }

        return -1; // not found
    }

    // Leetcode 236. Lowest Common Ancestor of a Binary Tree
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

    TreeNode LCANode = null;
    public boolean lowestCommonAncestor_(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null) return false;

        boolean selfDone = false;
        if(root == p || root == q) selfDone = true;

        boolean leftDone = lowestCommonAncestor_(root.left, p, q);
        // if LCA is found then no need to go in recursion
        if(LCANode != null) return true;
        
        boolean rightDone = lowestCommonAncestor_(root.right, p, q);
        if(LCANode != null) return true;

        if((leftDone && rightDone) || (leftDone && selfDone) || (rightDone && selfDone)) {
            // if any 2 of them are true then curr node will be ancestor
            LCANode = root;
        }
        // return true if found p or q
        return selfDone || leftDone || rightDone;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        lowestCommonAncestor_(root, p, q);

        return LCANode;

    }

    // 21Aug
    // ----------------------------------------BST--------------------------

    // Leetcode 235 Lowest Common Ancestor of a Binary Search Tree
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        TreeNode curr = root;
        
        while(curr != null) {
            
            if(p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
                
            } else if( p.val < curr.val && q.val < curr.val ) {
                curr = curr.left;
                
            } else {
                // curr elt is lca
                return curr;
            }
        }
        return null;
        
    }

    // Leetcode 173. Binary Search Tree Iterator
    // https://leetcode.com/problems/binary-search-tree-iterator/

    Stack<TreeNode> st = new Stack<> ();
    public BSTIterator(TreeNode root) {
        
        pushAllNextElements(root);
    }
    
    public void pushAllNextElements(TreeNode node) {
        
        while(node != null) {
            st.push(node);
            node = node.left;
        } 
    }
    /** @return the next smallest number */
    public int next() {
        
        TreeNode rv = st.pop();
        
        if(rv.right != null) {
            // if smaller elts are there in right subtree of removing node
            // then push them in stack
            pushAllNextElements(rv.right);
            
        }
        return rv.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        
        return st.size() != 0;
    }

    // Binary Tree to Circular Doubly Linked List
    // https://practice.geeksforgeeks.org/problems/binary-tree-to-cdll/1

    Node bTreeToClist(Node root)
    {
        //your code here
        head = null;
        prev = null;
        
        bTreeToClist_(root);
        
        head.left = prev;
        prev.right = head;
        return head;
    }
    
    static Node head, prev;
    public void bTreeToClist_(Node node) {
        if(node == null) return;
        
        bTreeToClist_(node.left);
        
        if(head == null) head = node;
        
        else {
            // it does not give null error for leftmost node bcz
            // at that node we set our head in if condition and then prev
            // using prev = node line 
            node.left = prev;
            prev.right = node;
        }
        prev = node;
        
        bTreeToClist_(node.right);
    }

    // Leetcode 99. Recover Binary Search Tree
    // https://leetcode.com/problems/recover-binary-search-tree/

    TreeNode A = null, B = null;
    TreeNode prev = null;
    public boolean recoverTree_(TreeNode root) {
        
        if(root == null ) return false;
        
        if(recoverTree_(root.left)) return true;
        
        if(prev != null && prev.val > root.val) {
            B = root;
            
            if(A == null) A = prev;
            // first time setting the swapping point
            else return true;
            // both elts are set now swap them
        }
        prev = root;
        if(recoverTree_(root.right)) return true;
        return false;
    }
    public void recoverTree(TreeNode root) {
        
        recoverTree_(root);
        
        if(A != null ){
            int temp = A.val;
            A.val = B.val;
            B.val = temp;
        }
    }

    // Leetcode 285
    // Lintcode 448. Inorder Successor in BST
    // https://www.lintcode.com/problem/inorder-successor-in-bst/description

    public TreeNode inorderSuccessor(TreeNode curr, TreeNode p) {
        // write your code here
        if(curr == null) return null;
        TreeNode succ = null;
        
        while(curr != p) {
            
            if(p.val > curr.val) {
                
                curr = curr.right;
                
            } else if(p.val < curr.val) {
                succ = curr;
                curr = curr.left;
                
            } else break;
        }
        
        if(curr.right != null) {
            
            succ = curr.right;
            while(succ.left != null) {
                succ = succ.left;
            }
        }
        return succ;
    }

    // Leetcode 510 Inorder Seccessor in BST 2

    /*
    // Definition for a Node.
    class Node {
       public int val;
       public Node left;
       public Node right;
       public Node parent;
    };
    */

    // In this question you have access to parent too
    public Node inorderSuccessor2(Node node) {

        Node curr = node;
        Node succ = null;

        if(curr.right != null) {

            succ = curr.right;
            while(succ.left != null) succ = succ.left;

            return succ;
        }

        Node par = curr.parent; // parent

        while(par != null ) {
            curr = par;
            par = par.parent;

            if(par.left == curr) return par;

        }

        return succ; // not found so return null
    }

    // Leetcode 230 Kth Smallest Element in a BST
    // https://leetcode.com/problems/kth-smallest-element-in-a-bst/

    // 1. My approach
    public int kthSmallest(TreeNode root, int k) {
        
        int arr[] = {k, 0};
        kthSmallest_(root, arr);
        return arr[1];
    }
    
    public void kthSmallest_(TreeNode root,int[] k) {
        
        if(root == null || k[0] < 0) return;
        
        kthSmallest_(root.left, k);
        
        k[0]--;
        if(k[0] == 0) {
            k[1] =  root.val;
            return;
        }
        
        
        kthSmallest_(root.right, k);   
    }

    // 2. Level UP Approach
    // o(log (h) + k)
    int kthSmallestAns = -1;
    int kth = 0;
    public boolean kthSmallest_(TreeNode root) {
        
        if(root == null) return false;
        
        if(kthSmallest_(root.left)) return true;
        
        if(--kth == 0) {
            kthSmallestAns = root.val;
            return true;
        }
        
        if(kthSmallest_(root.right)) return false;
        
        return false;
    }
    public int kthSmallest(TreeNode root, int k) {
        
        kth = k;
        kthSmallest_(root);
        
        return kthSmallestAns;
    }

    // 3. Iteratively Using Stack
    public void pushAllLeftNodes(Stack<TreeNode> st, TreeNode node) {

        while(node != null) {
            st.push(node);
            node = node.left;
        }
    }
    
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();

        pushAllLeftNodes(st,root);
        //push all left elts for inorder
        while(--k != 0) {
            TreeNode rNode = st.pop();
            // now push right subtree elts of rNode
            pushAllLeftNodes(st,rNode.right);
        }

        return st.pop().val; // return kth element
    }

    // 450. Delete Node in a BST
    // https://leetcode.com/problems/delete-node-in-a-bst/
    public TreeNode deleteNode(TreeNode root, int key) {
        
        if(root == null) return null;
        
        if(root.val > key) root.left = deleteNode(root.left, key);
        else if(root.val < key) root.right = deleteNode(root.right, key);
        
        else if(root.val == key) {
            if(root.left == null || root.right == null) return root.left == null ? root.right : root.left;
            
            else {
                int data = findLeft(root.right);
                root.val = data;
                
                root.right = deleteNode(root.right, root.val);
            }
        }
        return root;
    }

    public int findLeft(TreeNode node) {        
        while(node.left != null) node = node.left;
        
        return node.val;
    }

    // =======================================================================
        // LEETCODE 105, 106 IN TreeConstruction FILE
    // =======================================================================

    
    // 979. Distribute Coins in Binary Tree
    // https://leetcode.com/problems/distribute-coins-in-binary-tree/
    // SS5650

    int totalCoinsMovements = 0;
    public int distributeCoins_(TreeNode node) {
        
        if(node == null) return 0;
        
        int leftDefeGain = distributeCoins_(node.left);
        int rightDefeGain = distributeCoins_(node.right);
        
        totalCoinsMovements += Math.abs(leftDefeGain) + Math.abs(rightDefeGain);
        // we will count excess coins count and also need as +ve value
        
        // Think like those child who need the coins are sending the boxes 
        // and we will fill those boxes at current point thats why we are counting abs value
        
        return (node.val - 1) + leftDefeGain + rightDefeGain;
        // return leftOut coins after fulfilling all needs
    }
    public int distributeCoins(TreeNode root) {
        
        if(distributeCoins_(root) != 0) return -1;
        // all nodes coins needs cannot be fulfilled or excess coins are present
        
        return totalCoinsMovements;
    }

    // 968. Binary Tree Cameras
    // https://leetcode.com/problems/binary-tree-cameras/

    // 0 -> child has covered themselves,think about yourself
    // 1 -> i installed camera and will cover parent and child too
    // -1 -> i need camera
    int cams = 0;
    public int coverCams(TreeNode node) {
        
        if(node == null) return 0;
        if(node.left == null && node.right == null) return -1;
        
        int left = coverCams(node.left);
        int right = coverCams(node.right);
        
        if(left == -1 || right == -1) {
            cams++;
            return 1;
            // i am installing cam 
        } else if(left == 1 || right == 1) {
            return 0;
            // child has covered me
        } else return -1;
          // i need cam
    }
    
    public int minCameraCover(TreeNode root) {
        if(root == null) return 0;
        
        if(coverCams(root) == -1) cams += 1;
        // root node needs camera
        
        return cams;
    }

    // 116. Populating Next Right Pointers in Each Node
    // https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

    // Use queue so that you can get next elt easily as it moves level wise (bfs) 
    // where we have access for next node(in right side) 
    // we will put null too at every end of level bcz we have to connect rightmost nodes next to null
    // so we will use them to connect
    public Node connect(Node root) {
        if(root == null) return null;
        
        LinkedList<Node> que = new LinkedList<> ();
        
        que.add(root);
        que.add(null);
        
        while(que.size() != 1) {    
            // != 1 bcz we have added null and at last null will be there
            
            while(que.peek() != null) {
                // if null  means we have reached to end of level 
                
                Node node = que.removeFirst();                
                node.next = que.getFirst();
                
                if(node.left != null) que.addLast(node.left);
                if(node.right != null) que.addLast(node.right);
            }
            que.removeFirst(); 
            // remove null of current level
            
            que.addLast(null);
            // add null for next level
        }
        return root;
    }

    

}