import java.util.ArrayList;
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

        solve();
    }

    public static void solve() {

        ArrayList<Integer> arr = new ArrayList<> ();

        for(int i = 0; i <= 15; i++) {

            arr.add(i*10);

        }

        Node root = constructBST(arr, 0, arr.size() - 1);

        display(root);
        System.out.println(size(root));
        System.out.println(find(root, 80));
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
}