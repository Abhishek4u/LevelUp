import java.util.LinkedList;
import java.util.ArrayList;
public class views {

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
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};

        Node root = constructTree(arr);

        leftView(root);
        rightView(root);

        verticalOrder(root);
        verticalOrderSum(root);

        BottomView_RightPref(root);
        BottomView_LeftPrefer(root);

        TopView(root);
        TopView_PrintBFSWise(root);

        diagonalViewFromLeft(root);
        diagonalViewFromRight(root);

        boundaryTraversal(root);
    }

    static int idx = 0;

    public static Node constructTree(int[] arr) {

        if (arr[idx] == -1 || idx == arr.length) {
            idx++;
            return null;
        }
        Node node = new Node(arr[idx++]);

        node.left = constructTree(arr);
        node.right = constructTree(arr);

        return node;
    }

    public static void width(Node node,int level, int[] minMax) {

        if(node == null) return;

        minMax[0] = Math.min(minMax[0], level);
        minMax[1] = Math.max(minMax[1], level);

        width(node.left,level-1,minMax);
        width(node.right,level + 1,minMax);

    }

    public static void leftView(Node node) {

        System.out.println("Left View");
        LinkedList<Node> que = new LinkedList<>();

        que.addLast(node);

        while (que.size() != 0) {
            int size = que.size();

            System.out.print(que.getFirst().data + " ");

            while (size-- > 0) {

                Node rvtx = que.removeFirst();

                if (rvtx.left != null)
                    que.addLast(rvtx.left);
                if (rvtx.right != null)
                    que.addLast(rvtx.right);

            }
        }
        System.out.println();
    }

    public static void rightView(Node node) {

        System.out.println("Right View");
        LinkedList<Node> que = new LinkedList<> ();
        que.addFirst(node);

        while(que.size() != 0) {

            int size = que.size();
            Node prev = null;
            while(size-- > 0) {

                Node rvtx = que.removeFirst();
                prev = rvtx;

                if(rvtx.left != null) que.addLast(rvtx.left);
                if(rvtx.right != null) que.addLast(rvtx.right);

            }

            System.out.print(prev.data + " ");
        }
        System.out.println();
    }

    public static class vPair {

        Node node = null;
        int level = 0;

        vPair(Node node,int level) {

            this.node = node;
            this.level = level;
        }
    }

    public static void verticalOrder(Node node) {

        System.out.println("Vertical Order");
        int minMax[] = {0,0};
        width(node,0, minMax);

        int length = minMax[1] - minMax[0] + 1;
        // positive width of tree ie. the size of Arraylist to store
        // +1 bcz 0 is also included in width
        // for making 0 based indexing
        ArrayList<Integer> [] ans = new ArrayList[length];

        for(int i = 0 ; i < length;i++) {
            ans[i] = new ArrayList<> ();
        }

        LinkedList<vPair> que = new LinkedList<>();

        que.addFirst(new vPair(node,Math.abs(minMax[0])));

        while(que.size() != 0) {

            int size = que.size();

            while(size-- > 0) {

                vPair rVtx = que.removeFirst();
                Node tempNode = rVtx.node;
                int lvl = rVtx.level;

                ans[lvl].add(tempNode.data);

                if(tempNode.left != null) que.addLast(new vPair(tempNode.left, lvl-1));

                if(tempNode.right != null) que.addLast(new vPair(tempNode.right, lvl+1));
            }

        }
        int i = 0;
        for(ArrayList<Integer> al : ans) {

            System.out.print("Level " + i++ +" ");

            for(int val: al) {
                System.out.print(val+ " ");
            }
            System.out.println();
        }

    }

    public static void verticalOrderSum(Node node) {
        
        System.out.println("Vertical Order Sum");

        int[] minMax = {0,0};
        width(node,0,minMax);

        int length = minMax[1] - minMax[0] + 1;

        int[] ans = new int[length];

        LinkedList<vPair> que = new LinkedList<> ();

        que.addFirst(new vPair(node,Math.abs(minMax[0])));

        while(que.size() != 0 ){

            int size = que.size();

            while(size-- > 0) {

                vPair rVtx = que.removeFirst();
                Node tempNode = rVtx.node;
                int lvl = rVtx.level;

                ans[lvl] += tempNode.data;

                if(tempNode.left != null) que.addLast(new vPair(tempNode.left,lvl-1));

                if(tempNode.right != null) que.addLast(new vPair(tempNode.right,lvl+1));
            }
        }

        int i = 0;
        for(int val: ans) {
            System.out.println("Level " + i++ + " "+ val + " ");
        }

    }

    // bottom view with right preference to same level elts
    public static void BottomView_RightPref(Node node) {

        System.out.println("BottomView Right Preference");

        int minMax[] = {0,0};
        width(node,0,minMax);

        int length = minMax[1] - minMax[0] + 1;

        int ans[] = new int[length];

        LinkedList<vPair> que = new LinkedList<> ();

        que.add(new vPair(node,Math.abs(minMax[0])));
        // use math.abs not -minMax[0] bcz if val is 0 then -0 can give error

        while(que.size() != 0) {

            int size = que.size();
            while(size-- > 0) {

                vPair rVtx = que.removeFirst();
                Node remNode = rVtx.node;
                int lvl = rVtx.level;

                ans[lvl] = remNode.data;

                if(remNode.left != null) que.addLast(new vPair(remNode.left,lvl - 1));
                if(remNode.right != null) que.addLast(new vPair(remNode.right,lvl + 1));

            }
        }
        for(int ele : ans) System.out.print(ele + " ");
        System.out.println();
    }

    public static class bPair{
        Node node;
        int level;
        int height;

        bPair(Node node,int level,int height) {
            this.node = node;
            this.level = level;
            this.height = height;
        }
    }

    public static void BottomView_LeftPrefer(Node node) {

        System.out.println("BottomView Left Preference");

        int[] minMax = {0,0};
        width(node,0,minMax);
        int level = minMax[1] - minMax[0] + 1;

        bPair[] ans = new bPair[level];


        LinkedList<bPair> que = new LinkedList<> ();

        que.addFirst(new bPair(node, Math.abs(minMax[0]), 0));

        while(que.size() != 0) {

            int size = que.size();

            while(size-- > 0) {

                bPair rVtx = que.removeFirst();
                Node remNode = rVtx.node;
                int lvl = rVtx.level;
                int height = rVtx.height;

                if(ans[lvl] == null) ans[lvl] = rVtx;

                else if(height > ans[lvl].height) ans[lvl] = rVtx;
                // update only when height is greater than previous one
                // at same level we will consider only elt which came first 
                // bcz we are giving priority to left 

                if(remNode.left != null) que.add(new bPair(remNode.left,lvl-1,height+1));
                if(remNode.right != null) que.add(new bPair(remNode.right,lvl+1,height+1));

            }
        }

        for(bPair ele : ans) System.out.print(ele.node.data + " ");
        System.out.println();
    }

    public static void TopView(Node node) {

        System.out.println("Top View");

        int minMax[] = {0,0};
        width(node,0,minMax);

        int level = minMax[1] - minMax[0] + 1;

        Node ans[] = new Node[level];

        LinkedList<vPair> que = new LinkedList<> ();

        que.addLast(new vPair(node, Math.abs(minMax[0])));

        while(que.size() != 0) {

            int size = que.size();

            while(size-- > 0) {

                vPair rVtx = que.removeFirst();
                Node remNode = rVtx.node;
                int lvl = rVtx.level;

                if(ans[lvl] == null) {
                    // update only once in top view
                    ans[lvl] = remNode;
                }

                if(remNode.left != null) que.add( new vPair(remNode.left,lvl-1));
                if(remNode.right != null) que.add( new vPair(remNode.right,lvl+1));
            }
        }

        for(Node ele: ans) System.out.print(ele.data + " ");
        System.out.println();
    }

    public static void TopView_PrintBFSWise(Node node) {
        // in bfs wise printing you have to print from root(middle elt) and its adjacent elts ans so on

        System.out.println("Top View BFS WISE PRINTING");

        int minMax[] = {0,0};
        width(node,0,minMax);

        int level = minMax[1] - minMax[0] + 1;

        Node ans[] = new Node[level];

        LinkedList<vPair> que = new LinkedList<> ();

        que.addLast(new vPair(node, Math.abs(minMax[0])));

        while(que.size() != 0) {

            int size = que.size();

            while(size-- > 0) {

                vPair rVtx = que.removeFirst();
                Node remNode = rVtx.node;
                int lvl = rVtx.level;

                if(ans[lvl] == null) {
                    // update only once in top view
                    ans[lvl] = remNode;
                }

                if(remNode.left != null) que.add( new vPair(remNode.left,lvl-1));
                if(remNode.right != null) que.add( new vPair(remNode.right,lvl+1));
            }
        }
        // for bfs wise printing
        int rNodeIdx = Math.abs(minMax[0]); // rootNode idx
        System.out.print(ans[rNodeIdx].data+ " ");

        // Start printing adjacent elts
        int i = rNodeIdx - 1, j = rNodeIdx + 1;

        while(i >= 0 && j < ans.length) System.out.print(ans[i--].data + " " + ans[j++].data + " ");

        // if there are any left out elts then print them
        while(i >= 0) System.out.print(ans[i--].data);
        while(j < ans.length) System.out.print(ans[j++].data);

        System.out.println();
    }

    public static void diagonalViewFromLeft(Node node) {

        System.out.println("Diagonal View From Left");

        int minMax[] = {0,0};
        width(node,0,minMax);

        int length = Math.abs(minMax[0]) + 1;
        // for left diagonal we need length of al equal to left side elts of tree
        // bcz we will see from left diagonal side

        ArrayList<Integer>[] ans = new ArrayList[length];

        for(int i = 0; i < ans.length; i++) {
            ans[i] = new ArrayList<> ();
        }

        LinkedList<vPair> que = new LinkedList<> ();

        que.addLast(new vPair(node,Math.abs(minMax[0])));

        while(que.size() > 0) {

            int size = que.size();

            while(size-- > 0) {

                vPair rVtx = que.removeFirst();
                Node remNode = rVtx.node;
                int lvl = rVtx.level;

                ans[lvl].add(remNode.data);

                if(remNode.left != null) que.addLast(new vPair(remNode.left,lvl - 1));
                if(remNode.right != null) que.addLast(new vPair(remNode.right,lvl));
                //  in diagonal traversal for right child lvl will remain same 
                // and for left child it will decrement
                // you can verify by making diagram

            }
        }

        int i = 0;
        for(ArrayList<Integer> al : ans) {

            System.out.print("Level " + i++ +" ");
            for(int val: al) {
                System.out.print(val+ " ");
            }
            System.out.println();
        }
    }

    public static void diagonalViewFromRight(Node node) {

        System.out.println("Diagonal View From Right");

        int minMax[] = {0,0};
        width(node,0,minMax);

        int length = minMax[1] + 1;
        // for right diagonal we need length of al equal to right side elts of tree
        // bcz we will see from right diagonal side

        ArrayList<Integer>[] ans = new ArrayList[length];

        for(int i = 0; i < ans.length; i++) {
            ans[i] = new ArrayList<> ();
        }

        LinkedList<vPair> que = new LinkedList<> ();

        que.addLast(new vPair(node,Math.abs(0)));
        // for right view diagonal root will come in 1st arrayList

        while(que.size() > 0) {

            int size = que.size();

            while(size-- > 0) {

                vPair rVtx = que.removeFirst();
                Node remNode = rVtx.node;
                int lvl = rVtx.level;

                ans[lvl].add(remNode.data);

                if(remNode.left != null) que.addLast(new vPair(remNode.left,lvl));
                if(remNode.right != null) que.addLast(new vPair(remNode.right,lvl+1));
                // in diagonal traversal for left child lvl will remain same 
                // and for right child it will increment
                // you can verify by making diagram

            }
        }

        int i = 0;
        for(ArrayList<Integer> al : ans) {

            System.out.print("Level " + i++ +" ");
            for(int val: al) {
                System.out.print(val+ " ");
            }
            System.out.println();
        }
    }

    public static void boundaryTraversal(Node node) {

        System.out.println("Boundary Traversal");

        // For boundary traversal
        //  1. Get top view list 
        //      1.1 -> Now print root to left elts
        //  2. Now print leaf Nodes
        //  3. Now print left out elts of top view in reverse order

        // Top view 
        int minMax[] = {0,0};
        width(node,0,minMax);

        int length = minMax[1] - minMax[0] + 1;

        Node[] ans = new Node[length];

        LinkedList<vPair> que = new LinkedList<> ();
        que.add(new vPair(node,Math.abs(minMax[0])));

        while(que.size() != 0) {

            int size = que.size();

            while(size -- > 0) {

                vPair rVtx = que.removeFirst();
                Node remNode = rVtx.node;
                int lvl = rVtx.level;

                if(ans[lvl] == null) ans[lvl] = remNode;

                if(remNode.left != null) que.addLast(new vPair(remNode.left,lvl - 1));
                if(remNode.right != null) que.addLast(new vPair(remNode.right,lvl + 1));
            }
        }

        // Child List

        Node[] ans2 = new Node[length];

        LinkedList<vPair> que2 = new LinkedList<> ();
        que2.add(new vPair(node,Math.abs(minMax[0])));

        while(que2.size() > 0) {

            int size = que2.size();

            while(size-- > 0) {

                vPair rVtx = que2.removeFirst();
                Node remNode = rVtx.node;
                int lvl = rVtx.level;

                if(remNode.left == null && remNode.right == null) {

                    ans2[lvl] = remNode;
                    // leaf node
                } else {

                    if(remNode.left != null ) que2.addLast(new vPair(remNode.left, lvl - 1));
                    if(remNode.right != null) que2.addLast(new vPair(remNode.right, lvl + 1));
                } 
            }
        }

        // Now print the elts

        // 1. print left boundary elts
        int rNodeIdx = Math.abs(minMax[0]); // rootNode index
        int idx = rNodeIdx;

        while( idx >= 0) 
            System.out.print(ans[idx--].data + " ");

        // 2. Print leaf nodes
        idx = 1; // 1 bcz we already printed 1st leafnode
        while(idx < ans2.length) {
            if(ans2[idx] == null) {
                idx++;
                continue;
            }
            System.out.print(ans2[idx++].data + " ");
        }
        // 3. Print right boundary elts in reverse order

        idx = ans2.length - 2; // -2 bcz we already printed last leaf node elt in upper traversal

        while(idx > rNodeIdx) // while idx is greater than root node elt
            System.out.print(ans[idx--].data + " ");
        
        System.out.println();
    }
    
}
