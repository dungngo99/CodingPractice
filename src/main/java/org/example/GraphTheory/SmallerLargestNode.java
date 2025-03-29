package GraphTheory;

public class SmallerLargestNode {
    public static int find(TreeNode node, int key) {
        if (node == null) return Integer.MIN_VALUE;

        if (node.value < key) return Math.max(node.value, find(node.right, key));
        return find(node.left, key);
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 5, 6, -10, 10, 9 };
        BST tree = new BST();
        for (int num : nums) {
            tree.insert(num);
        }
        tree.printInOrder();
        System.out.println("Smaller largest node is " + SmallerLargestNode.find(tree.getRoot(), 8));
        System.out.println("Smaller largest node is " + SmallerLargestNode.find(tree.getRoot(), -10));
        System.out.println("Smaller largest node is " + SmallerLargestNode.find(tree.getRoot(), 12));
    }
}