public class TreeConstruction {

    // FOR BETTER UNSERSTANDING MAKE A TREE AND DRY-RUN THESE CODES

    // Leetcode 105. Construct Binary Tree from Preorder and Inorder Traversal
    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

    // 1. My Code
    // You can use 1 static variable  to make one node in every recursion call (using preorder and increment the pointer)
    // Start moving pIdx from 0 bcz in preorder rootnode is stored at 0th index in array
    // for left and right range find same node in inorder and
        // for left-subtree the range will be leftBoundary to index of node in inorder -1
        // for right-subtree the range will be index of node in inorder +1 to rightBoundary
    //For base case when both boundaries cross each other we will stop means there is no node that can be created 

    int pIdx = 0; // preOrder Index 
    public TreeNode construct(int[] pre, int[] in, int left, int right) {
        
        if(left > right) return null;  
        // when both boundaries will cross each other we will stop    
        
        int idx = left;        
        while(in[idx] != pre[pIdx]) idx++;
        
        TreeNode node = new TreeNode(pre[pIdx++]);     
        
        node.left = construct(pre, in, left, idx - 1);
        node.right = construct(pre, in, idx+1, right);
        
        return node;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        return construct(preorder, inorder, 0, preorder.length-1);
    }

    // ---------------------------------------------------------------------------------------

    // Sir's Code
    // in this we are using boundaries for both arrays and for range we are finding the length
    // ie. how far the elt is found in inorder[] so that will be length from idx - isi
    // node will be pre[psi] bcz in preorder (root, leftChild, rightChild)

    // psi -> preStartingIndex, pei -> preEndingIndex
    // isi -> inOrderStartingIndex, iei -> inOrderEndingIndex
    public TreeNode buildTree(int[] preorder, int psi, int pei, int[] inorder, int isi, int iei) {
        if(psi > pei) return null;
        
        int idx = isi;
        while(inorder[idx] != preorder[psi]) idx++;
        int len = idx - isi; // will use this for left childs in postorder
        
        TreeNode node = new TreeNode(preorder[psi]);
        node.left = buildTree(preorder, psi+1, psi+len,inorder, isi, idx-1 );
        node.right = buildTree(preorder, psi+len+1, pei, inorder, idx+1, iei);
        
        return node;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        if(preorder.length == 0) return null;
        int n = preorder.length;
        
        return buildTree(preorder, 0, n-1, inorder, 0, n-1);
    }

    // ============================================================================
    // ============================================================================

    // Leetcode 106. Construct Binary Tree from Inorder and Postorder Traversal
    // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/submissions/

    // 1. My Code
    // SAME AS ABOVE QUESTION APPROACH(MY-APPROACH)
    // HERE WE WILL MOVE pIdx right to left bcz postorder[] has rootnode in lastIndex  

    // we will process right child subtree first bcz right child is stored first in postorder[]
    // and all other cases are same as above question

    int pIdx ; // postOrder index
    
    public TreeNode constructTree(int[] in, int post[], int left, int right) {
        if(left > right) return null;
        // when both boundaries will cross each other we will stop    

        
        int idx = right; // in-order idx
        while(in[idx] != post[pIdx]) idx--;
        
        TreeNode node = new TreeNode(post[pIdx--]);
        
        node.right = constructTree(in, post, idx+1, right);
        // process right child first bcz in postorder[] rightchild is stored first (moving from right to left)
        node.left = constructTree(in, post, left, idx-1);
        
        return node;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        pIdx = n - 1;
        
        return constructTree(inorder, postorder, 0, n-1);
    }

    // -----------------------------------------------------------------------------

    // 2. Sir's Code
    // Same as above ques(sir's approach) 
    // but here node will be postorder[pei] bcz in postorder node is first then right child and left child(if moving from right to left) (ie. left, right, root) 
    // length will be calculated same as here we will find node in inorder and idx - isi for left childs 
    public TreeNode buildTree(int[] postorder, int psi, int pei, int[] inorder, int isi, int iei) {
        
        if(psi > pei) return null;
        
        int idx = isi;
        while(inorder[idx] != postorder[pei]) idx++;
        int len = idx - isi; // will use this for left childs in postorder
        
        TreeNode node = new TreeNode(postorder[pei]);
        node.left = buildTree(postorder, psi, psi+len-1, inorder, isi, idx - 1);
        node.right = buildTree(postorder,psi+len, pei - 1, inorder, idx+1, iei);
        
        return node;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        
        if(postorder.length == 0) return null;
        int n = postorder.length;
        
        return buildTree(postorder, 0, n -1, inorder, 0, n-1);
    }

      

}
