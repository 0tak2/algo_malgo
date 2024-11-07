package io.github.otak2.leetcode.grind75;

/**
 * leetcode 226. Invert Binary Tree
 * Grind75 #6
 * https://leetcode.com/problems/invert-binary-tree/description/
 *
 * 재귀함수를 이용해 DFS로 탐색하면서 자식 노드를 스왑
 *
 * 0ms, 40.87MB
 */
public class InvertBinaryTree {
    private void invertChild(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        invertChild(node.left);
        invertChild(node.right);
    }

    public TreeNode invertTree(TreeNode root) {
        invertChild(root);
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
