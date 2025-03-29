package Stack;

import java.util.List;
import java.util.Arrays;
import java.util.Stack;

/*
 * A data structure to keep track of the current min by using stack
 */
public class MinStack {
    private List<Integer> stack;
    private List<Integer> minStack;

    public MinStack() {
        // stack and minStack always have the same length
        this.stack = new Stack<Integer>();
        this.minStack = new Stack<Integer>();
    }

    /*
     * Add an int to a stack and add updated min to minStack
     */
    public void push(int num) {
        if (this.stack.isEmpty()){
            this.stack.add(num);
            this.minStack.add(num);
            return;
        }

        int lastIndex = this.minStack.size() - 1;
        int newMin = Math.min(num, this.minStack.get(lastIndex));
        this.minStack.add(Math.min(num, newMin));
        this.stack.add(num);
    }

    /*
     * Pop the last element out of the stack, so does minStack
     */
    public int pop() {
        if (this.stack.isEmpty()) {
            return -1;
        }
        int lastIndex = this.minStack.size() - 1;
        this.minStack.remove(lastIndex);
        return this.stack.remove(lastIndex);
    }

    /*
     * Track the min value of the current stack
     */
    public int peek() {
        if (this.stack.isEmpty()) {
            return -1;
        }
        int lastIndex = this.minStack.size() - 1;
        return this.minStack.get(lastIndex);
    }

    @Override
    public String toString() {
        return Arrays.toString(stack.toArray());
    }

    public static void main(String[] agrs) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(4);
        minStack.push(3);
        minStack.push(5);
        minStack.push(2);
        System.out.println(minStack);
        System.out.println(minStack.peek());
        System.out.println(minStack.pop());
        System.out.println(minStack.pop());
        System.out.println(minStack.peek());
    }
}
