package org.example.GraphTheory;

interface SegmentTreeNode {
    public int[] range = new int[2];
    public int value = 0;
    public SegmentTreeNode left = null;
    public SegmentTreeNode right = null;

    public void set(int left, int right, int value);
}

public class SegmentTree {
    public class SegmentTreeNode {
        public int[] range;
        public int value;
        public SegmentTreeNode left;
        public SegmentTreeNode right;

        public SegmentTreeNode(int left, int right, int value) {
            this.range = new int[] { left, right };
            this.value = value;
        }
    }

    public SegmentTreeNode constructTree(int[] array, int left, int right) {
        if (left == right)
            return this.new SegmentTreeNode(left, right, array[left]);

        int mid = (left + right) / 2;
        SegmentTreeNode leftNode = constructTree(array, left, mid);
        SegmentTreeNode rightNode = constructTree(array, mid + 1, right);
        return this.new SegmentTreeNode(left, right, Math.min(leftNode.value, rightNode.value));
    }

    public int rangeQuery(int[] query, SegmentTreeNode node) {
        int low = query[0], high = query[1];
        int lowNode = node.range[0], highNode = node.range[1];
        if (lowNode <= low && high <= highNode)
            return node.value;
        else if (high < lowNode || low > highNode)
            return Integer.MAX_VALUE;

        return Math.min(
                rangeQuery(query, node.left),
                rangeQuery(query, node.right));
    }

    public int updateTree(int value, int index, SegmentTreeNode node) {
        if (node == null)
            return 0;

        if (node.range[0] == index && node.range[1] == index) {
            node.value = value;
            return value;
        }

        int mid = (node.range[0] + node.range[1]) / 2;
        int res = 0;

        if (index <= mid)
            res = updateTree(value, index, node.left);
        else
            res = updateTree(value, index, node.right);

        node.value = Math.min(node.value, value);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 5, 8, 6, 3, 2, 7, 2, 6, 7 };
        SegmentTree tree = new SegmentTree();
        SegmentTreeNode root = tree.constructTree(nums, 0, nums.length - 1);
        System.out.println(tree.rangeQuery(new int[] { 3, 7 }, root));

        int value = tree.updateTree(1, 4, root);
        System.out.println("Value updated for node at index=4 " + value);
        System.out.println(tree.rangeQuery(new int[] { 3, 7 }, root));
    }
}
