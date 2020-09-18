import java.util.*;

public class questionsNormalApproaches {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 863. All Nodes Distance K in Binary Tree
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

        List<TreeNode> path = new ArrayList<>();
        rootToNodePath(root, target, path);

        List<Integer> ans = new ArrayList<>();

        int n = path.size();
        TreeNode prev = null;
        for (int i = 0; i < n; i++) {

            kFar(path.get(i), prev, K - i, ans);
            prev = path.get(i);
        }

        return ans;
    }

    public static void kFar(TreeNode node, TreeNode blocker, int k, List<Integer> ans) {

        if (node == null || node == blocker || k < 0) {
            return;
        }

        if (k == 0) {
            ans.add(node.val);
            return;
        }

        kFar(node.left, blocker, k - 1, ans);
        kFar(node.right, blocker, k - 1, ans);
    }

    public static boolean rootToNodePath(TreeNode node, TreeNode target, List<TreeNode> path) {

        if (node == null)
            return false;

        if (node == target) {
            path.add(node);
            return true;
        }

        boolean res = false;

        res = res || rootToNodePath(node.left, target, path);
        res = res || rootToNodePath(node.right, target, path);
        // if found on one side or condition does not check other one

        if (res) {
            path.add(node);
            return true;
        }

        return false;
    }
}
