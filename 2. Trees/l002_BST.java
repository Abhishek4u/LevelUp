import java.util.ArrayList;
import java.util.ArrayDeque;
public class l002_BST {

    public static class Node {

        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }
    
    public static void main(String[] args) {

        // solve();
        BSTUsingPreOrder_runner();
    }

    public static void solve() {

        ArrayList<Integer> arr = new ArrayList<> ();

        for(int i = 0; i <= 15; i++) {

            arr.add(i*10);
        }

        Node root = constructBST(arr, 0, arr.size() - 1);

        // display(root);
        System.out.println("----------------------------------");
        // System.out.println(size(root));
        // System.out.println(find(root, 80));

        // int lvlOrder[] = {7,3,12,1,6,9,13,0,2,4,8,11,15,5,10,14};
        // Node level = BSTUsingLevelOrder(lvlOrder);
        // display(level);

        PredSucc(root,80);
    }

    public static void BSTUsingPreOrder_runner() {

        int[] arr = {7,3,1,0,2,6,4,5,12,9,8,11,10,13,15,14};

        display(BSTUsingPreOrder(arr, -(int) 1e8, (int) 1e8));
        System.out.println( BSTPreOrderHeight(arr, -(int)1e8, (int) 1e8) );
    }

    //-----------------------------BASIC----------------------------

    public static Node constructBST(ArrayList<Integer> arr, int si, int ei) {

        if(si > ei) {
            return null;
        }
        int mid = (si + ei) >> 1; // divide by 2

        Node node = new Node(arr.get(mid));

        node.left = constructBST(arr,si, mid-1);
        node.right = constructBST(arr, mid+1, ei);

        return node;
    }

    public static void display(Node node) {

        if(node == null ) return;

        StringBuilder sb = new StringBuilder();

        sb.append(node.left != null ? node.left.data + " " : " . ");
        sb.append(" <- " + node.data + " -> ");
        sb.append(node.right != null ? node.right.data + " " : " . ");

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    public static int size(Node node) {

        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public static int height(Node node) {

        return node == null ? -1 : Math.max( height(node.left), height(node.right) ) + 1;
    }

    public static boolean find(Node node, int data) {

        // iteratively 
        Node curr = node;

        while(curr != null) {

            if(curr.data == data) return true;
            
            else if( data > curr.data) curr = curr.right;

            else if( data < curr.data) curr = curr.left;
        }

        return false;
    }

    public static int minimum(Node node) {
        Node curr = node;

        while(curr.left != null) curr = curr.left;

        return curr.data;
    }

    public static int maximum(Node node) {
        Node curr = node;

        while(curr.right != null) curr = curr.right;

        return curr.data;
    }

    static int idx = 0;
    // use left range and right range to attach the elts
    public static Node BSTUsingPreOrder(int[] arr, int lRange,int rRange) {

        if(idx >= arr.length || arr[idx] < lRange || arr[idx] > rRange) {
            return null;
        }
        
        Node node = new Node(arr[idx++]);

        // for left sub tree range will be between leftRange and node.data
        node.left = BSTUsingPreOrder(arr,lRange, node.data);

         // for left sub tree range will be between leftRange and node.data
        node.right = BSTUsingPreOrder(arr, node.data, rRange);

        return node;
    }

    public static int  BSTPreOrderHeight(int[] arr,int lRange, int rRange){
        if(idx >= arr.length || arr[idx] < lRange  ||  arr[idx] > rRange) return -1;

        int ele = arr[idx++];
        int lh = BSTPreOrderHeight(arr,lRange,ele);
        int rh = BSTPreOrderHeight(arr,ele,rRange);

        return Math.max(lh,rh) + 1;
    }


    static int idx2;// = arr.length - 1; 
    // for post order start from right to left and rest logic is same as preorder
    public static Node BSTUsingPostOrder(int[] arr, int lRange, int rRange) {

        if(idx2 < 0 || arr[idx2] < lRange || arr[idx2] > rRange) return null;

        Node node = new Node(arr[idx2--]);

        node.left = BSTUsingPostOrder(arr,lRange, node.data);
        node.right = BSTUsingPostOrder(arr,node.data,rRange);

        return node;
    }

    public static class lPair{

        Node node;
        int lRange;
        int rRange;
        boolean isLeft;
    
        lPair( Node node, int left, int right, boolean value) {

            this.node = node;
            this.lRange = left;
            this.rRange = right;
            this.isLeft = value;
        }
    }

    // Tree generation from level order of tree
    public static Node BSTUsingLevelOrder(int[] arr) {

        ArrayDeque<lPair> que = new ArrayDeque<>();

        Node node = new Node(arr[idx++]);

        // add for its left subrtree
        que.add( new lPair( node, - (int) 1e8, node.data, true));
        // add for its left subrtree
        que.add( new lPair( node, node.data, (int) 1e8,  false));

        while(que.size() > 0) {

            if(idx == arr.length) break;

            Node newNode = new Node(arr[idx]);

            lPair remNode = que.removeFirst();
            Node curr = remNode.node;

            int lRange = remNode.lRange;
            int rRange = remNode.rRange;

            boolean isLeft = remNode.isLeft;

            // if value is within the range then only attach the elt or otherwise just move to next node
            if( newNode.data > lRange && rRange > newNode.data ) {

                idx++; // if elt is attachable to tree only then move to next elt

                if(isLeft == true) {
                    // if left side then attach in left side of tree
                    curr.left = newNode;
                } else {
                    // if left side then attach in left side of tree
                    curr.right = newNode;
                }

                // for left subtree
                que.add( new lPair( newNode, lRange, newNode.data,  true));
                // for right subtree
                que.add( new lPair( newNode, newNode.data, rRange, false));
            }
        
        }
        return node;        
    }
    // IN BST CIEL AND FLOOR ARE PREDECESSOR AND SUCCESSOR
    // predecessor and successor
    public static void PredSucc ( Node node, int data ) {

        Node curr = node;
        Node pred = null, succ = null;

        while(curr != null) {

            if(data > curr.data) {

                pred = curr;
                curr = curr.right;

            } else if(data < curr.data) {

                succ = curr;
                curr = curr.left;
            } 
            else break;
            // else curr is data
        }

        if(curr.left != null) {

            pred = curr.left;

            while(pred.right != null) {
                pred = pred.right;
            }   
        }
        if(curr.right != null) {

            succ = curr.right;

            while(succ.left != null) {
                succ = succ.left; 
            }   
        }

        System.out.println( (pred != null ? pred.data : "Not Found") +
         " " + data + " " + (succ != null ? succ.data : "Not Found") );
    }

    // to add a node in bst
    public static addNode(Node node, int data) {

        if(node == null) return new Node(data);

        if(data > node.data) {
            node.right = addNode(node.right,data);

        } else if(data < node.data) {
            node.left = addNode(node.left, data);

        } 
    }

    // to find the minimum elt
    public static int findMinimum(Node node)  {
        while(node.left != null) node = node.left;

        return node;
    }

    // TO remove the node 

    public static Node removeData(Node node,int data) {

        if(data < node.data) {
            node.left = removeData(node.left,data);

        } else if(data > node.data) {
            node.right = removeData(node.right,data);

        } else if(node.data == data) {

            if(node.left == null || node.right  == null )  return node.left != null ? node.left : node.right;

            else {
                // both left and right are present
                // so you need to remove this and place left subtree's max elt or right subtree's min elt
                // and remove that elt after this operation

                // Here i will do it with right subtree min elt
                int minElt = findMinimum(node.right);
                node.data = minElt;

                node.right = removeData(node.right,minElt); 
                // now remove that elt in subtree from where it came
                // do not call with node bcz it will remove itself then as we swapped node's value

            }
        }
        return node;
    }

}