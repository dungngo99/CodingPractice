package org.example.OOP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * <p><b>An important concept in Object-Oriented Programming is the open/closed principle</b>,
 * which means writing code that is open to extension but closed to modification.
 * In other words, new functionality should be added by writing an extension for the existing code
 * rather than modifying it and potentially breaking other code that uses it.</p>
 *
 * <p>This challenge simulates a real-life problem where the open/closed principle can and should be applied.</p>
 *
 * <p><b>A Tree class implementing a rooted tree is provided in the editor.</b>
 * It has the following publicly available methods:</p>
 *
 * <ul>
 *   <li><code>getValue()</code>: Returns the value stored in the node.</li>
 *   <li><code>getColor()</code>: Returns the color of the node.</li>
 *   <li><code>getDepth()</code>: Returns the depth of the node. The depth of a node is the number of edges
 *       between the node and the tree's root, so the tree's root has depth 0, and each descendant node's
 *       depth is equal to the depth of its parent node + 1.</li>
 * </ul>
 *
 * <p>In this challenge, we treat the internal implementation of the tree as being <b>closed to modification</b>,
 * so we cannot directly modify it. However, as with real-world situations, the implementation is written in such
 * a way that it allows external classes to extend and build upon its functionality.</p>
 *
 * <p>More specifically, it allows objects of the <b><code>TreeVis</code></b> class (a Visitor Design Pattern) to
 * visit the tree and traverse the tree structure via the <code>accept</code> method.</p>
 *
 * <h3>Part I: Implement Three Different Visitors</h3>
 * <p>Each class has three methods you must write implementations for:</p>
 *
 * <ul>
 *   <li><b><code>getResult()</code></b>: Return an integer denoting the result, which is different for each class:
 *     <ul>
 *       <li><b>SumInLeavesVisitor</b>: Return the sum of the values in the tree's leaves only.</li>
 *       <li><b>ProductRedNodesVisitor</b>: Return the product of values stored in all red nodes, including leaves,
 *           computed modulo 10<sup>9</sup> + 7. The product of zero values is 1.</li>
 *       <li><b>FancyVisitor</b>: Return the absolute difference between the sum of values stored in the tree's
 *           non-leaf nodes at even depth and the sum of values stored in the tree's green leaf nodes.</li>
 *     </ul>
 *   </li>
 *   <li><code>visitNode(TreeNode node)</code>: Implement the logic responsible for visiting the tree's non-leaf nodes.</li>
 *   <li><code>visitLeaf(TreeLeaf leaf)</code>: Implement the logic responsible for visiting the tree's leaf nodes.</li>
 * </ul>
 *
 * <h3>Part II: Read and Build the Tree</h3>
 * <p>Read the <code>n</code>-node tree, where each node is numbered from 1 to n. The tree is given as:</p>
 * <ul>
 *   <li>A list of node values (<code>v</code>).</li>
 *   <li>A list of node colors (<code>c</code>).</li>
 *   <li>A list of edges.</li>
 * </ul>
 * <p>Construct this tree as an instance of the Tree class. The tree is always rooted at node number 1.</p>
 *
 * <p>Your implementations of the three visitor classes will be tested on the tree you built from the given input.</p>
 *
 * <h3>Input Format</h3>
 * <ul>
 *   <li>The first line contains a single integer, <code>n</code>, denoting the number of nodes in the tree.</li>
 *   <li>The second line contains <code>n</code> space-separated integers describing the respective values of <code>v</code>.</li>
 *   <li>The third line contains <code>n</code> space-separated binary integers describing the respective colors of <code>c</code>.
 *       Each <code>c<sub>i</sub></code> denotes the color of the <code>i</code>th node, where 0 = red and 1 = green.</li>
 *   <li>Each of the <code>n - 1</code> subsequent lines contains two space-separated integers, <code>u</code> and <code>v</code>,
 *       describing an edge between nodes <code>u</code> and <code>v</code>.</li>
 * </ul>
 *
 * <h3>Constraints</h3>
 * <ul>
 *   <li>The tree is guaranteed to be rooted at node 1.</li>
 * </ul>
 *
 * <h3>Output Format</h3>
 * <p>Do not print anything to stdout, as this is handled by locked stub code in the editor.
 * The three <code>getResult()</code> methods must return an integer denoting the result for that class' visitor.
 * Note that the value returned by <code>ProductRedNodesVisitor</code>'s <code>getResult()</code> method must
 * be computed modulo 10<sup>9</sup> + 7.</p>
 *
 * <h3>Sample Input</h3>
 * <pre>
 * 5
 * 4 7 2 5 12
 * 0 1 0 0 1
 * 1 2
 * 1 3
 * 3 4
 * 3 5
 * </pre>
 *
 * <h3>Sample Output</h3>
 * <pre>
 * 24
 * 40
 * 15
 * </pre>
 *
 * <h3>Explanation</h3>
 * <ul>
 *   <li>SumInLeavesVisitor: Sums values of leaf nodes (2, 5, 12) → <b>24</b></li>
 *   <li>ProductRedNodesVisitor: Multiplies values of red nodes (4, 2, 5) → <b>40</b></li>
 *   <li>FancyVisitor: |(Sum of non-leaf even-depth nodes: 4 + 2) - (Sum of green leaf nodes: 12)| → <b>15</b></li>
 * </ul>
 */

public class Visitor {
    public static void main(String[] args) {
        System.out.println("Hello World");
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        List<Integer> valueList = new ArrayList<>();
        for (int i=0; i<N; i++) {
            valueList.add(scanner.nextInt());
        }
        scanner.nextLine();
        List<Integer> colorList = new ArrayList<>();
        for (int i=0; i<N; i++) {
            colorList.add(scanner.nextInt());
        }
        scanner.nextLine();
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (int i=0; i<N; i++) {
            treeNodeList.add(new TreeNode(valueList.get(i), colorList.get(i)));
        }
        scanner.nextLine();
        while (N>1) {
            int parentIdx = scanner.nextInt() - 1;
            int childIdx = scanner.nextInt() - 1;
            treeNodeList.get(parentIdx).addChild(treeNodeList.get(childIdx));
            scanner.nextLine();
            N--;
        }
        scanner.close();

        Tree tree = new Tree();
        tree.setRoot(treeNodeList.get(0));

        SumInLeavesVisitor sumInLeavesVisitor = new SumInLeavesVisitor(tree);
        System.out.println("SumInLeavesVisitor: " + sumInLeavesVisitor.getResult());

        ProductRedNodesVisitor productRedNodesVisitor = new ProductRedNodesVisitor(tree);
        System.out.println("ProductRedNodesVisitor: " + productRedNodesVisitor.getResult());

        FancyVisitor fancyVisitor = new FancyVisitor(tree);
        System.out.println("FancyVisitor: " + fancyVisitor.getResult());
    }
}

enum Color {
    RED(0),
    GREEN(1);

    private final int value;

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

abstract class Visiting {
    public static final Integer MOD = ((int) Math.pow(10, 9)) + 7;

    abstract int getInitValue();
    abstract int reduceAns(int ans, TreeNode node);
    abstract int visitNode(TreeNode node);
    abstract int visitLeaf(TreeNode node);
    abstract int getFinalAns(int ans);

    int getResult(TreeNode root) {
        int ans = getInitValue();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans = reduceAns(ans, node);
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
        }

        return getFinalAns(ans);
    }
}

class SumInLeavesVisitor extends Visiting {
    private final Tree tree;

    public SumInLeavesVisitor(Tree tree) {
        this.tree = tree;
    }

    int getResult() {
        return super.getResult(tree.getRoot());
    }

    @Override
    int getInitValue() {
        return 0;
    }

    @Override
    int reduceAns(int ans, TreeNode node) {
        if (node.isLeaf()) {
            return ans + visitLeaf(node);
        }
        return ans + visitNode(node);
    }

    @Override
    int visitNode(TreeNode node) {
        return 0;
    }

    @Override
    int visitLeaf(TreeNode node) {
        return node.getValue();
    }

    @Override
    int getFinalAns(int ans) {
        return ans;
    }
}

class ProductRedNodesVisitor extends Visiting {
    private final Tree tree;

    public ProductRedNodesVisitor(Tree tree) {
        this.tree = tree;
    }

    public int getResult() {
        return super.getResult(tree.getRoot());
    }

    @Override
    int getInitValue() {
        return 1;
    }

    @Override
    int reduceAns(int ans, TreeNode node) {
        if (node.isLeaf()) {
            return node.isRed() ? (ans * visitLeaf(node)) % MOD : ans;
        }
        return node.isRed() ? (ans * visitNode(node)) % MOD : ans;
    }

    @Override
    int visitNode(TreeNode node) {
        return node.getValue();
    }

    @Override
    int visitLeaf(TreeNode node) {
        return node.getValue();
    }

    @Override
    int getFinalAns(int ans) {
        return ans;
    }
}

class FancyVisitor extends Visiting {
    private final Tree tree;

    public FancyVisitor(Tree tree) {
        this.tree = tree;
    }

    public int getResult() {
        return super.getResult(tree.getRoot());
    }

    @Override
    int getInitValue() {
        return 0;
    }

    @Override
    int reduceAns(int ans, TreeNode node) {
        if (node.isLeaf()) {
            return !node.isRed() ? ans - visitLeaf(node) : ans;
        }
        return node.isDepthEven() ? ans + visitNode(node) : ans;
    }

    @Override
    int visitNode(TreeNode node) {
        return node.getValue();
    }

    @Override
    int visitLeaf(TreeNode node) {
        return node.getValue();
    }

    @Override
    int getFinalAns(int ans) {
        return Math.abs(ans);
    }
}

class Tree {
    private TreeNode root;

    public Tree() {
        this.root = null;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return this.root;
    }
}

class TreeNode {
    private final int value;
    private int depth;
    private final int color;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value, int color) {
        this.value = value;
        this.color = color;
        this.left = null;
        this.right = null;
    }

    public void addChild(TreeNode child) {
        child.setDepth(this.depth + 1);
        if (this.left == null) {
            this.left = child;
            return;
        }
        if (this.right == null) {
            this.right = child;
            return;
        }
        throw new RuntimeException("this node is full, cannot add more child.");
    }

    public int getValue() {
        return value;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getColor() {
        return color;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public boolean isRed() {
        return this.color == Color.RED.getValue();
    }

    public boolean isDepthEven() {
        return this.depth % 2 == 0;
    }
}