package GraphTheory;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

class BST {
  protected TreeNode root;

  public BST() {
    this.root = null;
  }

  public TreeNode getRoot() {
    return this.root;
  }

  @SuppressWarnings("all")
  public void insert(int value) {
    BiConsumer<BiConsumer, TreeNode> helper = (f, node) -> {
      if (node == null) {
        this.root = new TreeNode(value);
        return;
      }

      if (value <= node.value) {
        if (node.left == null)
          node.left = new TreeNode(value);
        else
          f.accept(f, node.left);
      } else {
        if (node.right == null)
          node.right = new TreeNode(value);
        else
          f.accept(f, node.right);
      }
    };

    helper.accept(helper, this.root);
  }

  public TreeNode delete(int value) {
    return this.deleteHelper(this.root, value);
  }

  public TreeNode deleteHelper(TreeNode node, int value) {
    if (node == null)
      return null;

    if (node.value < value) {
      node.right = deleteHelper(node.right, value);
      return node;
    } else if (node.value > value) {
      node.left = deleteHelper(node.left, value);
      return node;
    } else {
      if (node.left == null && node.right == null)
        return null;
      else if (node.left == null)
        return node.right;
      else if (node.right == null)
        return node.left;
      else {
        TreeNode predecessor = node.left;
        while (predecessor.right != null) {
          predecessor = predecessor.right;
        }
        node.value = predecessor.value;
        node.left = deleteHelper(node.left, predecessor.value);
        return node;
      }
    }
  }

  @SuppressWarnings("all")
  public TreeNode search(int value) {
    BiFunction<BiFunction, TreeNode, TreeNode> helper = (f, node) -> {
      if (node == null)
        return null;
      if (node.value == value)
        return node;

      if (value < node.value)
        return (TreeNode) f.apply(f, node.left);
      return (TreeNode) f.apply(f, node.right);

    };
    return helper.apply(helper, this.root);
  }

  @SuppressWarnings("all")
  public TreeNode getMin() {
    BiFunction<BiFunction, TreeNode, TreeNode> helper = (f, node) -> {
      if (node == null)
        return null;
      if (node.left == null)
        return node;
      return (TreeNode) f.apply(f, node.left);
    };
    return helper.apply(helper, this.root);
  }

  @SuppressWarnings("all")
  public TreeNode getMax() {
    BiFunction<BiFunction, TreeNode, TreeNode> helper = (f, node) -> {
      if (node == null)
        return null;
      if (node.right == null)
        return node;
      return (TreeNode) f.apply(f, node.right);
    };
    return helper.apply(helper, this.root);
  }

  @SuppressWarnings("all")
  public TreeNode getPredecessor(int value) {
    TreeNode node = this.search(value);
    if (node == null || node == this.getMin())
      return null;

    if (node.left != null) {
      TreeNode tmp = node.left;
      while (tmp.right != null) {
        tmp = tmp.right;
      }
      return tmp;
    }

    BiFunction<BiFunction, TreeNode, TreeNode> helper = (f, tmp) -> {
      if (tmp == null)
        return null;
      if (tmp == node)
        return tmp;

      TreeNode res = (TreeNode) f.apply(f, tmp.left);
      if (res == node)
        return tmp;
      if (res != null && res != tmp)
        return res;

      res = (TreeNode) f.apply(f, tmp.right);
      if (res == node)
        return tmp;
      if (res != null && res != tmp)
        return res;

      return null;
    };

    return helper.apply(helper, this.root);
  }

  @SuppressWarnings("all")
  public void printInOrder() {
    BiConsumer<BiConsumer, TreeNode> helper = (f, node) -> {
      if (node == null)
        return;
      f.accept(f, node.left);
      System.out.print(node.value + " ");
      f.accept(f, node.right);
    };
    helper.accept(helper, this.root);
    System.out.println();
  }

  @SuppressWarnings("all")
  public void printPreOrder() {
    BiConsumer<BiConsumer, TreeNode> helper = (f, node) -> {
      if (node == null)
        return;
      System.out.print(node.value + " ");
      f.accept(f, node.left);
      f.accept(f, node.right);
    };
    helper.accept(helper, this.root);
    System.out.println();
  }

  @SuppressWarnings("all")
  public void printPostOrder() {
    BiConsumer<BiConsumer, TreeNode> helper = (f, node) -> {
      if (node == null)
        return;
      f.accept(f, node.left);
      f.accept(f, node.right);
      System.out.print(node.value + " ");
    };
    helper.accept(helper, this.root);
    System.out.println();
  }

  @SuppressWarnings("all")
  public int getSize(int key) {
    TreeNode curNode = this.search(key);
    if (curNode == null)
      return 0;

    BiFunction<BiFunction, TreeNode, Integer> helper = (f, node) -> {
      if (node == null)
        return 0;
      return node.size = 1 + (Integer) f.apply(f, node.left) + (Integer) f.apply(f, node.right);
    };

    helper.apply(helper, this.root);
    return curNode.size;
  }

  @SuppressWarnings("all")
  public int getRank(int key) {
    TreeNode curNode = this.search(key);
    if (curNode == null)
      return 0;

    BiFunction<BiFunction, TreeNode, Integer> helper = (f, node) -> {
      if (node == null)
        return 0;
      return node.rank = 1 + (Integer) f.apply(f, node.left);
    };
    helper.apply(helper, this.root);
    return curNode.rank;
  }

  @SuppressWarnings("all")
  public TreeNode getKthSmallestNode(int k) {
    if (this.root == null)
      return null;

    BiFunction<BiFunction, TreeNode, Integer> helper = (f, node) -> {
      if (node == null)
        return 0;
      return node.rank = 1 + (Integer) f.apply(f, node.left);
    };
    helper.apply(helper, this.root);

    TreeNode tmp = this.root;
    while (tmp != null) {
      if (tmp.rank == k)
        return tmp;
      if (tmp.rank < k) {
        k = k - tmp.rank;
        tmp = tmp.right;
      } else {
        tmp = tmp.left;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    int[] nums = new int[] { 5, 3, 1, 10, 9, 4, 7};

    BST bst = new BST();
    for (int num : nums) {
      bst.insert(num);
    }

    bst.printInOrder();
    System.out.println("Smallest key in BST is " + bst.getMin().value);
    System.out.println("Largest key in BST is " + bst.getMax().value);

    System.out.println("Search for key=1 in BST " + bst.search(1));
    System.out.println("Seearch for key=4 in BST " + bst.search(4));

    System.out.println("Predecessor of key=1 in BST is " + bst.getPredecessor(1));
    System.out.println("Predecessor of key=11 in BST is " + bst.getPredecessor(11));

    System.out.println("Deleted node= " + bst.delete(5).value);
    bst.printInOrder();
  }
}