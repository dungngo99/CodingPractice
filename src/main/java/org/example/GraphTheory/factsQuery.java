
/**
 * example
 *  m = 3.28 ft
 *  ft = 12 in
 *  hr = 60 min
 *  min = 60 sec
 * question
 *  2 m = ? in
 * 2 steps: 
 *    -> construct the graph<node=unit,edge=constant>
 *    -> use the graph to traverse through nodes and find the values of all edges (aka distances) between any 2 nodes
 *    -> graph = directed acyclic graph
 */

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class FactQuery {
  public static class Graph {
    protected Map<String, Node> data;
  }

  public static class Node {
    protected String name;
    protected Map<String, Node> nextNode;
    protected Map<String, Double> nextValue;

    public Node() {
      nextNode = new HashMap<>();
      nextValue = new HashMap<>();
    }
  }

  public static void main(String[] args) {
    String[][] facts = {
        { "m", "ft", "3.28" },
        {"ft", "m", "12"},
        {"hr", "min", "60"},
        {"min", "sec", "60"}
    };
    Graph graph = constructGraph(facts);
    double constant = query(new String[] { "m", "in" }, graph);
    System.out.println("constant " + constant);
  }

  public static Graph constructGraph(String[][] facts) {
    Graph graph = new Graph();
    graph.data = new HashMap<>();
    Map<String, Node> data = graph.data;

    for (String[] fact : facts) {
      String start = fact[0];
      String end = fact[1];
      Double constant = Double.parseDouble(fact[2]);
      if (data.containsKey(start)) {
        Node startNode = data.get(start);
        if (data.containsKey(end)) {
          Node endNode = data.get(end);
          constructNode(startNode, start, endNode, end, constant);
        } else {
          Node endNode = new Node();
          constructNode(startNode, start, endNode, end, constant);
          data.put(end, endNode);
        }
      } else {
        Node startNode = new Node();
        if (data.containsKey(end)) {
          Node endNode = data.get(end);
          constructNode(startNode, start, endNode, end, constant);
        } else {
          Node endNode = new Node();
          constructNode(startNode, start, endNode, end, constant);
          data.put(end, endNode);
        }
        data.put(start, startNode);
      }
    }
    return graph;
  }

  public static void constructNode(Node startNode, String start, Node endNode, String end, Double constant) {
    startNode.nextNode.put(end, endNode);
    startNode.nextValue.put(end, constant);
    endNode.nextNode.put(start, startNode);
    endNode.nextValue.put(start, 1 / constant);
  }

  public static double query(String[] fact, Graph graph) {
    String start = fact[0];
    String end = fact[1];
    Map<String, Node> data = graph.data;
    if (!data.containsKey(start) || !data.containsKey(end)) {
      return -1;
    }
    Map<String, Double> records = new HashMap<>();
    List<String> queue = new LinkedList<>();
    queue.add(start);
    records.put(start, 1.0);

    while (!queue.isEmpty()) {
      String cur = queue.get(queue.size() - 1);
      queue.remove(queue.size()-1);

      Node curNode = data.get(cur);
      if (curNode == null) {
        return -1;
      }

      Map<String, Double> nextValue = curNode.nextValue;
      for (Entry<String, Double> entry : nextValue.entrySet()) {
        String next = entry.getKey();
        Double value = entry.getValue();
        records.put(next, records.get(cur) * value);
        if (end.equals(next)) {
          return records.get(next);
        }
        queue.add(next);
      }
    }
    return records.get(end);
  }
}