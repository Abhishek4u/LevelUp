import java.util.Stack;
import java.util.ArrayList;
// SS5816
public class l004GT {

    public static void main(String[] args) {

        solve();      
    }

    public static class Node {

        int data;
        ArrayList<Node> childs = new ArrayList<> ();
        
        Node(int data) {
            this.data = data;
        }
    }

    public static Node constructGTTree(int arr[]) {

        Stack<Node> st = new Stack<> ();

        // Run loop till 2nd last number (last number is -1 which states that tree is finished) 
        for(int i = 0;i < arr.length - 1; i++) {

            if(arr[i] != -1)
                st.push(new Node(arr[i]));
            else {
                Node child = st.pop();
                Node parent = st.peek();
                
                parent.childs.add(child);
            }
        }
        return st.peek();
    }

    public static void display(Node node) {
        StringBuilder sb = new StringBuilder();

        sb.append(node.data + " -> ");
        for(Node child : node.childs) {
            sb.append(child.data + ", ");
        }

        System.out.println(sb);

        for(Node child : node.childs) {
            display(child);
        }
    }

    public static int size(Node node) {

        int size = 0;
        for(Node child : node.childs) size += size(child);
        
        return size+1;
    }

    public static int height(Node node) {
        int ht = -1;
        // Height according to edges(-1)

        for(Node child : node.childs) ht = Math.max(ht, height(child));

        return ht + 1;
    }

    public static boolean find(Node node, int data) {

        boolean found = false;
        if(node.data == data) return true;

        for(Node child : node.childs) found = found || find(child, data);

        return found;
    }

    public static int max(Node node) {

        int max = node.data;

        for(Node child : node.childs) max = Math.max(max, max(child));

        return max;
    }

    public static boolean NodeToRootPath(Node node, int data, ArrayList<Node> path) {

        if(node.data == data) {
            path.add(node);
            return true;
        }

        boolean res = false;
        for(Node child : node.childs) {
            res = res || NodeToRootPath(child, data, path);
        }

        if(res) path.add(node);
        return res;
    }

    // ======================================LCA====================================

    // O(2n)
    public static void LCA(Node node, int a, int b) {

        ArrayList<Node> path1 = new ArrayList<> ();
        ArrayList<Node> path2 = new ArrayList<> ();
        NodeToRootPath(node, a, path1);
        NodeToRootPath(node, b, path2);

        Node lca = null;
        int i = path1.size() - 1;
        int j = path2.size() - 1;

        while( i >= 0 && j >= 0) {
            if(path1.get(i) != path2.get(j)) break;
            lca = path1.get(i);

            i--;
            j--;
        }
        System.out.println(lca.data);

    }
    
    // O(n)
    static Node LCANode;
    public static boolean LCA2(Node node, int a, int b) {

        boolean oneDone = false,twoDone = false;
        if(node.data == a  || node.data == b) oneDone = true;


        for(Node child : node.childs) {

            if(LCANode != null) {
                return true;
                // LCANode is already set
            }

            if(oneDone) twoDone = LCA2(child, a, b);
            else oneDone = LCA2(child, a, b);

            if(oneDone && twoDone) {
                LCANode = node;
                return true;
            }
        }
        return oneDone;
    }

    static Node lca = null;
    public static boolean lca(Node node, int a,int b) {

        boolean selfCheck = node.data == a ? true : node.data == b ? true : false;

        boolean oneCheck = false,twoCheck = false;

        for(Node child : node.childs) {

            if(!oneCheck) oneCheck = lca(child,a,b);
            else twoCheck = lca(child,a,b);

            if(lca != null) return true;

        }

        if(oneCheck && twoCheck || oneCheck && selfCheck || selfCheck && twoCheck) 
        {
            lca = node;
        }

        return selfCheck || oneCheck || twoCheck;
    }

    // ==============================================================================

    // Using 1 while loop
    public static void zig_zag(Node node) {

        Stack<Node> st1 = new Stack<> ();
        Stack<Node> st2 = new Stack<> ();

        st1.add(node);
        boolean right = true;

        while(st1.size() != 0) {
            Node remNode = st1.pop();

            System.out.print(remNode.data + " ");

            if(right) {
                for(Node child: remNode.childs) {
                    st2.add(child);
                }
            } else {
                for(int i = remNode.childs.size() - 1; i >= 0; i--) {
                    st2.add(remNode.childs.get(i));
                }
            }

            if(st1.size() == 0) {
                System.out.println();
                right = !right;
                st1 = st2;
                st2 = new Stack<> ();
            }
        }
    }

    // Using 2 loops
    public static void zig_zag2(Node node) {

        Stack<Node> one = new Stack<> ();
        Stack<Node> two = new Stack<> ();

        one.add(node);

        boolean flag = true; // by dafault right side

        while(one.size() != 0) {

            int size = one.size();

            while(size-- > 0) {

                Node remNode = one.pop();

                System.out.print(remNode.data + " ");

                if(flag) {
                    for(Node child : remNode.childs) two.add(child);

                } else {
                    for(int i = remNode.childs.size() - 1; i >= 0; i--) {
                        two.add(remNode.childs.get(i));
                    }
                }
            }

            System.out.println();
            flag = !flag;
            one = two;
            two = new Stack<> ();
        }
    }

    //=================================================================================


    public static Node getTail(Node node) {
        
        while(node.childs.size() != 0) {
            node = node.childs.get(0);
        }

        return node;
    }

    // O(n*n) for every node we are calling getTail node 
    // move right to left to avoid shifting and also easy to do
    public static void linearize(Node node) {

        for(Node child : node.childs) linearize(child);
        // first linearize all childs then parent
        // will connect them in linearize way

        
        int n = node.childs.size();

        // starting from n-2 bcz we are working for secondLastChild Node and we will add lastChild node in secondLast's tail
        for(int i = n - 2; i >= 0; i--) {

            Node lastNode = node.childs.get(i+1); // get lastNode
            Node secondLastNode = node.childs.get(i); // get secondLast node

            Node sLastTail = getTail(secondLastNode); // get tail of secondLastNode

            sLastTail.childs.add(lastNode); // linearize the last node by adding in tail node of secondlast node

            node.childs.remove(i+1); // remove last Node from parent as we have added that in secondLastNode's tail
        }
        
    }

    // O(n)
    public static Node linearize(Node node) {

        if(node.childs.size() == 0) {
            return node;
            // as in above conditions we linearize last child without checking that last child is there or not
            // which can give error so we have to return node (as leaf node is always linearized) 
        }
        
        int n = node.childs.size();
        Node oTail = linearize(node.childs.get(n-1)); // will get tail of lastChild
        // this will work as overallTail of current node after linearizing
        // and if we have calculated this in next loop then at the time of return 
        // we have to get again tail which will make O(n*n) so we are calling outside

        // starting from n-2 bcz we are working for secondLastChild Node and we will add lastChild node in secondLast's tail
        for(int i = n-2; i >= 0; i--) {

            Node secondLast = node.childs.get(i);
            Node secondLastTail = linearize(secondLast); 
            // it will linearize too and will give tail of secondLastnode which will save O(n) time

            secondLastTail.childs.add(node.childs.remove(i+1)); 
            // detach last node from parent and add in tail of secondLastNode
        }

        return oTail; // return Overall tail so if in parent recursion we have right child then that child will be added in this node
    }

    // ===================================================================================================

    public static void solve() {
        int arr[] = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, 120, 140, -1, 150, -1, -1, -1,-1};
        
        Node root = constructGTTree(arr);
        // display(root);

        // System.out.println(size(root));
        // System.out.println(height(root));
        // System.out.println(find(root, 110));
        // System.out.println(max(root));

        // LCA(root, 30, 50);
        LCANode = null;
        LCA2(root, 60, 20);

        // System.out.println(LCANode.data);

        zig_zag(root);
        zig_zag2(root);
    }

}