package Stack;

import java.util.Arrays;
import java.util.Random;

public class ThreeInOne {
	public static void solution1(int[] stack, int num, int limit, int[] indices) {
		Random rand = new Random();
		int r = rand.nextInt(2);
		int index = rand.nextInt(3);

		if (r == 0) {
			if (indices[index] == limit) {
				System.out.println("List is full. Can't add more");
			} else {
				if (indices[index] == -1) {
					indices[index]++;
				}
				stack[limit * index + indices[index]] = num;
				indices[index]++;
				System.out.println("Added " + num + " to list " + r);
			}
		} else {
			if (indices[index] == -1) {
				System.out.println("List is empty. Can't delete");
			} else {
				stack[limit * index + indices[index] - 1] = 0;
				indices[index]--;
				if (indices[index] == 0) {
					indices[index] = -1;
				}
				System.out.println("Deleted " + num + " from list " + r);
			}
		}
	}

	public static void main(String[] args) {
		int size = 15;
		int[] stack = new int[size];
		int limit = 5;
		int[] indices = new int[] { -1, -1, -1 };

		for (int i = 0; i < size; i++) {
			solution1(stack, i, limit, indices);
			System.out.println(Arrays.toString(stack));
		}
	}

}
