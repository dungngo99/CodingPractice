package Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Heap {
  private List<Integer> heap;

  public Heap() {
    this.heap = new ArrayList<>();
  }

  /*
   * Get the index of the parent node given the curren index pos
   */
  private int parent(int pos) {
    return (pos - 1) / 2;
  }

  /*
   * Get the index of the left child given the current index pos
   */
  private int leftChild(int pos) {
    return 2 * pos + 1;
  }

  /*
   * Get the index of the left child given the current index pos
   */
  private int rightChild(int pos) {
    return 2 * pos + 2;
  }

  /*
   * Check if the node at index pos is a leaf
   * True if indexes of both left and right child are out of index of the list
   */
  private boolean isLeaf(int pos) {
    return rightChild(pos) >= this.heap.size() && leftChild(pos) >= this.heap.size();
  }

  /*
   * Swap the values at index fpos and spos
   */
  private void swap(int fpos, int spos) {
    Collections.swap(this.heap, fpos, spos);
  }

  /*
   * Maintain the heap property (parent <= leftChild && parent <= rightChild)
   * by recursively checking values of the current index pos with its children
   * index
   */
  private void heapifyDown(int pos) {
    if (isLeaf(pos) || pos >= this.heap.size())
      return;

    int leftChildIndex = leftChild(pos);
    int rightChildIndex = rightChild(pos);
    int curVal = this.heap.get(pos);
    int l = this.heap.size();

    if (leftChildIndex < l && rightChildIndex < l) {
      int rightChild = this.heap.get(rightChildIndex);
      int leftChild = this.heap.get(leftChildIndex);
      int minVal = Collections.min(List.of(rightChild, leftChild, curVal));

      if (minVal == leftChild) {
        swap(leftChildIndex, pos);
        heapifyDown(leftChildIndex);
      } else if (minVal == rightChild) {
        swap(rightChildIndex, pos);
        heapifyDown(rightChildIndex);
      }
    } else if (leftChildIndex < l) {
      int leftChild = this.heap.get(leftChildIndex);
      if (leftChild < curVal)
        swap(leftChildIndex, pos);
    } else if (rightChildIndex < l) {
      int rightChild = this.heap.get(rightChildIndex);
      if (rightChild < curVal)
        swap(rightChildIndex, pos);
    }
  }

  /*
   * Maintain the heap property (parent <= its both children)
   * by recursively checking the current value is larger than its parent's value
   */
  private void heapifyUp(int pos) {
    if (pos == 0)
      return;

    int par = parent(pos);
    int parVal = this.heap.get(par);
    int curVal = this.heap.get(pos);
    if (curVal < parVal) {
      swap(par, pos);
      heapifyUp(par);
    }
  }

  /*
   * Insert a new element at the end and maintain the heap property
   */
  public void insert(int element) {
    this.heap.add(element);
    heapifyUp(this.heap.size() - 1);
  }  

  /*
   * Extract the top value from the heap and maintain the heap property
   */
  public int extractTop() {
    swap(0, this.heap.size()-1);
    int popped = this.heap.remove(this.heap.size()-1);
    heapifyDown(0);
    return popped;
  }

  /*
   * Find the kth smallest element, given the current heap's size
   */
  public int select(int k) {
    if (k > this.heap.size()) return -1;

    Heap anotherHeap = new Heap();
    for (int element: this.heap){
      anotherHeap.insert(element);
    }
    
    int result = 0;
    for (int i=0; i<k; i++){
      result = anotherHeap.extractTop();
    }

    return result;
  }

  /*
   * Get the top value of the heap. It locates at root with index=0
   */
  public int peekTop() {
    return this.heap.isEmpty() ? -1 : this.heap.get(0);
  }

  public int size() {
    return this.heap.size();
  }

  /*
   * print the contents of the heap
   */
  public void print() {
    for (int i = 0; i < this.heap.size(); i++) {
      int leftChildIndex = leftChild(i);
      int rightChildIndex = rightChild(i);
      int parentIndex = parent(i);
      int l = this.heap.size();

      String string = String.format(
          "current: %d -> parent: %d; left: %d; right: %d\n",
          this.heap.get(i),
          leftChildIndex < l ? this.heap.get(leftChildIndex) : -1,
          rightChildIndex < l ? this.heap.get(rightChildIndex) : -1,
          parentIndex >= 0 ? this.heap.get(parentIndex) : -1);

      System.out.println(string);
    }
  }

  @Override
  public String toString(){
    return this.heap.toString();
  }

  // Driver code
  public static void main(String[] argv) {
    int[] array = new int[]{5, 3, 17, 10, 84, 19, 6, 22, 9};
    
    Heap minHeap = new Heap();
    for (int num: array){
      minHeap.insert(num);
    }
    
    minHeap.print();
    System.out.println(minHeap);
    for (int i = 0; i < 3; i++) {
      System.out.println(minHeap.extractTop() + " is extracted from the heap");
    }
    System.out.println(minHeap);
  }
}