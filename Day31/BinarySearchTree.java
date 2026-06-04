/**
 * Implementation of a Binary Search Tree (BST).
 * Provides operations to Insert, Search, and check if it is a Valid BST.
 */
public class BinarySearchTree {
    
    public static class BSTNode {
        String word;
        BSTNode left;
        BSTNode right;
        BSTNode(String word) { this.word = word; }
    }

    private BSTNode root;

    public void insert(String word) {
        root = insertRec(root, word);
    }

    private BSTNode insertRec(BSTNode root, String word) {
        if (root == null) {
            return new BSTNode(word);
        }
        if (word.compareTo(root.word) < 0) {
            root.left = insertRec(root.left, word);
        } else if (word.compareTo(root.word) > 0) {
            root.right = insertRec(root.right, word);
        }
        return root;
    }

    public boolean search(String word) {
        return searchRec(root, word) != null;
    }

    private BSTNode searchRec(BSTNode root, String word) {
        if (root == null || root.word.equals(word)) {
            return root;
        }
        if (word.compareTo(root.word) < 0) {
            return searchRec(root.left, word);
        }
        return searchRec(root.right, word);
    }

    public boolean isValidBST() {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(BSTNode node, String min, String max) {
        if (node == null) return true;
        if ((min != null && node.word.compareTo(min) <= 0) || (max != null && node.word.compareTo(max) >= 0)) {
            return false;
        }
        return isValidBST(node.left, min, node.word) && isValidBST(node.right, node.word, max);
    }
}
