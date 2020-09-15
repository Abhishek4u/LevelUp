// Tree ScreenShots Starts from 4677
public class l001 {

    public static class Node {

        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

        solve();

    }

    public static void solve() {

        int arr[] = { 10, 20, 30, -1, -1, 40, -1, -1, 50, 60, 70, -1, 80, -1, -1, -1, 90, 100, -1, 120, -1, -1, 110, -1,
                -1 };

        Node root = constructTree(arr);
        // display(root);
        // preOrder(root);
        // System.out.println();

        // System.out.println(size(root));
        System.out.println(diameter(root));
        System.out.println(diameter2(root)[0]);
        diameter3(root);
        System.out.println(dia);
    }

    static int idx = 0;

    public static Node constructTree(int[] arr) {

        if (idx == arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }

        Node node = new Node(arr[idx++]);

        node.left = constructTree(arr);
        node.right = constructTree(arr);

        return node;
    }

    public static void display(Node node) {

        if (node == null)
            return;

        StringBuilder sb = new StringBuilder();

        sb.append(node.left != null ? node.left.data : " ");
        sb.append(" <- " + node.data + " -> ");
        sb.append(node.right != null ? node.right.data : " ");

        System.out.println(sb);

        display(node.left);
        display(node.right);

    }

    public static int size(Node node) {

        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public static int height(Node node) {

        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public static void preOrder(Node node) {

        if (node == null)
            return;

        System.out.println(node.data);

        preOrder(node.left);
        preOrder(node.right);
    }

    public static int diameter(Node node) {

        if (node == null)
            return 0;

        int ld = diameter(node.left);
        int rd = diameter(node.right);

        int lh = height(node.left);
        int rh = height(node.right);

        int dia = Math.max(Math.max(ld, rd), lh + rh + 2);
        // +2 for connecting the childs
        return dia;
    }

    public static int[] diameter2(Node node) {

        if (node == null)
            return new int[] { 0, -1 };

        int[] lr = diameter2(node.left); // left result
        int[] rr = diameter2(node.right); // right result

        int ht = Math.max(lr[1], rr[1]) + 1;
        int dia = Math.max(Math.max(lr[0], rr[0]), lr[1] + rr[1] + 2);

        return new int[] { dia, ht };
    }

    static int dia = 0;

    public static int diameter3(Node node) {

        if (node == null)
            return -1;

        int lh = diameter3(node.left); // left height
        int rh = diameter3(node.right); // right height

        dia = Math.max(dia, lh + rh + 2);

        return Math.max(lh, rh) + 1; // height
    }

    // GFG Maximum Path Sum between 2 Leaf Nodes
    // https://practice.geeksforgeeks.org/problems/maximum-path-sum/1
    int leafToLeafAns = (int) -1e8;

    int maxPathSum_(Node node) {

        if (node == null)
            return (int) -1e8;
        // return min value

        if (node.left == null && node.right == null) {
            return node.data;
            // leaf node so that it should not change value of leafToLeafAns
        }

        int lMax = maxPathSum_(node.left);
        int rMax = maxPathSum_(node.right);

        if (node.left != null && node.right != null) {

            leafToLeafAns = Math.max(leafToLeafAns, lMax + node.data + rMax);
            // leafToleafAns is possible only when you have both leftnode and rightnode
        }

        return Math.max(lMax, rMax) + node.data;
        // return max sum path bcz it can make maximum leaftoleaf answer
    }

    int maxPathSum(Node root) {
        // code here
        maxPathSum_(root);
        return leafToLeafAns;
    }

    
}