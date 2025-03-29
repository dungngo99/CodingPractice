package org.example.GraphTheory;

import java.util.Map;

public class TreeNode {
  protected TreeNode left;
  protected TreeNode right;
  protected int value;
  protected int size;
  protected int rank;

  public TreeNode(int value) {
    this.left = null;
    this.right = null;
    this.value = value;
    this.size = -1;
    this.rank = -1;
  }

  public static int diameter(TreeNode node, Map<TreeNode, Integer> map) {
    if (node == null)
      return -1;
    if (map.containsKey(node))
      return map.get(node);

    int depth = 1 + Math.max(diameter(node.left, map), diameter(node.right, map));
    map.put(node, depth);
    return depth;
  }
}