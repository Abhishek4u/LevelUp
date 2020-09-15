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
            // if you return -(int) 1e8 then it is also valid because we compared values in  conditions
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
}