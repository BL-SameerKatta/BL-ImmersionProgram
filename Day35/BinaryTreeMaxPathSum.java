/**
 * Finds the maximum path sum in a binary tree.
 */
public class BinaryTreeMaxPathSum {
    private int maxPathSumGlobal;

    public int maxPathSum(TreeNode root) {
        maxPathSumGlobal = Integer.MIN_VALUE;
        calculateMaxPathDown(root);
        return maxPathSumGlobal;
    }

    private int calculateMaxPathDown(TreeNode node) {
        if (node == null) return 0;
        
        int leftPathSum = Math.max(0, calculateMaxPathDown(node.left));
        int rightPathSum = Math.max(0, calculateMaxPathDown(node.right));
        
        int currentPathSum = node.val + leftPathSum + rightPathSum;
        
        maxPathSumGlobal = Math.max(maxPathSumGlobal, currentPathSum);
        
        return node.val + Math.max(leftPathSum, rightPathSum);
    }
}
