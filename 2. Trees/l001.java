import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

// Tree ScreenShots Starts from 4677
public class l001 {
    public static void main(String[] args) {

        solve();
        // solve2();

    }

    public static void solve() {

        int arr[] = { 10, 20, 30, -1, -1, 40, -1, -1, 50, 60, 70, -1, 80, -1, -1, -1, 90, 100, -1, 120, -1, -1, 110, -1,
                -1 };

        Node root = constructTree(arr);
        // display(root);
        // preOrder(root);
        // System.out.println();

        // System.out.println(size(root));
        // System.out.println(diameter(root));
        // System.out.println(diameter2(root)[0]);
        // diameter3(root);
        // System.out.println(dia);

        // System.out.println(width(root));
        // ArrayList<Integer> kDownAns = new ArrayList<>();
        // kDown(root, 3, kDownAns);
        // System.out.println(kDownAns);

        // BFS_01(root);
        // BFS_02(root);
        // BFS_03(root);

        zigzag(root);
        zigzag2(root);
    }

    public static void solve2() {
        int arr[] = { 10, 20, 30, -1, -1, 40, -1, -1, 50, 60, 70, -1, 80, -1, -1, -1, 90, 100, -1, 120, -1, -1, 110, -1,
                -1 };

        Node root = constructTree(arr);
        
        // ArrayList<Node> ans = new ArrayList<>();
        // rootToNodePath(root, 120, ans);

        ArrayList<ArrayList<Integer>> al = new ArrayList<>();
        burningTree(root, 60, al);

        for (ArrayList<Integer> a : al) {

        System.out.println(a + " ");
        }
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

    // -------------------------------------DIAMETER---------------------------------

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

    public static int width(Node root) {

        int minMax[] = { 0, 0 };

        width_(root, 0, minMax);

        return minMax[1] - minMax[0];
    }

    public static void width_(Node node, int level, int minMax[]) {

        if (node == null)
            return;

        width_(node.left, level - 1, minMax);
        width_(node.right, level + 1, minMax);

        minMax[0] = Math.min(minMax[0], level);
        minMax[1] = Math.max(minMax[1], level);

    }

    public static void kDown(Node node, int k, ArrayList<Integer> ans) {

        if (node == null)
            return;

        if (k == 0) {
            ans.add(node.data);
            return;
        }

        kDown(node.left, k - 1, ans);
        kDown(node.right, k - 1, ans);

    }

    public static boolean rootToNodePath(Node node, int data, ArrayList<Node> path) {

        if (node == null)
            return false;

        if (node.data == data) {
            path.add(node);
            return true;
        }

        path.add(node);

        boolean res = false;

        res = res || rootToNodePath(node.left, data, path);
        res = res || rootToNodePath(node.right, data, path);
        // if found on one side or condition does not check other one

        if (res)
            return true;

        path.remove(path.size() - 1);

        return false;

    }

    // GFG Burning Tree Quen (Imp for Amazon)

    public static void BurningNodeskDown(Node node, int time, ArrayList<ArrayList<Integer>> ans) {

        if (node == null) {
            return;
        }

        if (time == ans.size()) {
            // if no arraylist is present at this time then add new
            ans.add(new ArrayList<>());
        }

        ans.get(time).add(node.data);
        // add curr burning node at this time

        BurningNodeskDown(node.left, time + 1, ans);
        BurningNodeskDown(node.right, time + 1, ans);
    }

    public static int burningTree(Node node, int data, ArrayList<ArrayList<Integer>> ans) {

        if (node == null)
            return -1;

        if (node.data == data) {

            BurningNodeskDown(node, 0, ans);
            return 1;
        }

        int ld = burningTree(node.left, data, ans);

        if (ld != -1) {
            if (ld == ans.size())
                ans.add(new ArrayList<Integer>());
            // if no list is there then add new list

            ans.get(ld).add(node.data);
            // add self

            BurningNodeskDown(node.right, ld + 1, ans);
            // for without block node send ld + 1
            return ld + 1;
        }

        int rd = burningTree(node.right, data, ans);
        if (rd != -1) {
            if (rd == ans.size())
                ans.add(new ArrayList<Integer>());
            // if no list is there then add new list
            ans.get(rd).add(node.data);
            // add self

            BurningNodeskDown(node.right, rd + 1, ans);
            return rd + 1;
        }

        return -1;
    }

    // O(n) time and O(n) space // Do this approach only if it comes in test
    public static Node lowestCommonAncestor(Node root, int p, int q) {

        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        rootToNodePath(root, p, path1);
        rootToNodePath(root, q, path2);

        int i = path1.size() - 1;
        int j = path2.size() - 1;

        Node LCA = null;

        while (i >= 0 && j >= 0) {

            if (path1.get(i) != path2.get(j))
                break;

            LCA = path1.get(i);
            i--;
            j--;
        }
        return LCA;
    }

    public static void BFS_01(Node node) {

        LinkedList<Node> que = new LinkedList<>();
        que.add(node);

        while (que.size() != 0) {

            Node rvtx = que.removeFirst();
            System.out.print(rvtx.data + " ");

            if (rvtx.left != null)
                que.addLast(rvtx.left);
            if (rvtx.right != null)
                que.addLast(rvtx.right);
        }
        System.out.println();

    }

    public static void BFS_02(Node node) {

        LinkedList<Node> que = new LinkedList<>();
        que.add(node);
        que.add(null);

        while (que.size() != 1) {
            // 1 bcz of extra null added always

            Node rvtx = que.removeFirst();
            System.out.print(rvtx.data + " ");

            if (rvtx.left != null)
                que.addLast(rvtx.left);
            if (rvtx.right != null)
                que.addLast(rvtx.right);

            if (que.getFirst() == null) {
                System.out.println();
                que.removeFirst();
                que.addLast(null);
            }
        }
    }

    public static void BFS_03(Node node) {

        LinkedList<Node> que = new LinkedList<>();
        que.add(node);
        int level = 0;

        while (que.size() != 0) {

            int size = que.size();
            System.out.print("Level " + level + " : ");

            while (size-- > 0) {

                Node rvtx = que.removeFirst();
                System.out.print(rvtx.data + " ");

                if (rvtx.left != null)
                    que.addLast(rvtx.left);
                if (rvtx.right != null)
                    que.addLast(rvtx.right);
            }
            level++;
            System.out.println();

        }
    }
    // Using linkedlist
    public static void zigzag(Node node) {

        LinkedList<Node> st1 = new LinkedList<>();
        LinkedList<Node> st2 = new LinkedList<>();

        st1.addFirst(node);
        int level = 0;

        while (st1.size() != 0) {
            int size = st1.size();

            while (size-- > 0) {
                Node rvtx = st1.remove();
                System.out.print(rvtx.data + " ");

                if ((level & 1) == 0) {
                    // even
                    if (rvtx.left != null)
                        st2.addFirst(rvtx.left);
                    if (rvtx.right != null)
                        st2.addFirst(rvtx.right);

                } else {
                    // add first so list can be reversed
                    if (rvtx.right != null)
                        st2.addFirst(rvtx.right);
                    if (rvtx.left != null)
                        st2.addFirst(rvtx.left);
                }
            }

            st1 = st2;
            st2 = new LinkedList<>();

            System.out.println();
            level++;
        }
    }
    // Using stack
    public static void zigzag2(Node node) {

        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();

        st1.add(node);
        int level = 0;
        while (st1.size() != 0) {
            int size = st1.size();

            while (size-- > 0) {

                Node rvtx = st1.pop();
                System.out.print(rvtx.data + " ");

                if (level % 2 == 0) {

                    if (rvtx.left != null)
                        st2.push(rvtx.left);
                    if (rvtx.right != null)
                        st2.push(rvtx.right);
                } else {

                    if (rvtx.right != null)
                        st2.push(rvtx.right);
                    if (rvtx.left != null)
                        st2.push(rvtx.left);
                }
            }

            System.out.println();
            level++;

            st1 = st2;
            st2 = new Stack<> ();
        }
    }
}
