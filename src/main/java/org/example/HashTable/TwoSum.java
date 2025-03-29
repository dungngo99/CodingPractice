package HashTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.FileReader;

/*
 * This class implements two-sum algorithm by using hash table
 * Assumption: for each trial of TwoSum, there is only 1 pair that sums up to a given sum
 * Note: Java code runs faster than in Python code (23 mins < 3 hrs). 
 * Question: What makes Python run slower (dictionary's lookup takes linear time)?
*/
public class TwoSum {
  private List<Long> list;

  public TwoSum() {
    list = new ArrayList<>();
  }

  /*
   * A function to read from file and store numbers in a list
   */
  public void readFile(String path) {
    FileReader file = null;
    Scanner scan = null;

    try {
      file = new FileReader(path);
      scan = new Scanner(file);

      while (scan.hasNextLine()) {
        String line = scan.nextLine();
        long x = Long.parseLong(line);
        list.add(x);
      }
      System.out.println("List's size: " + list.size());
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (file != null) {
        try {
          file.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (scan != null)
        scan.close();
    }
  }
  
  /*
   * A function to run the TwoSum algorithm
   */
  public int run(long sum) {
    Map<Long, Integer> keyMap = new HashMap<>();

    for (int i = 0; i < this.list.size(); i++) {
      if (keyMap.containsKey(sum - list.get(i))) {
        System.out.println(String.format(
            "Keys found: [%s, %s] for sum=%s",
            list.get(i), i, sum));
        return keyMap.get(sum - list.get(i)) * i;
      } else {
        keyMap.put(list.get(i), i);
      }
    }
    return 0;
  }

  public static void main(String args[]) {
    TwoSum twoSum = new TwoSum();
    twoSum.readFile("src/Data/2sum.txt");

    int size = 10;
    int[] sums = new int[size];
    int result = 0;

    for (int i = 0; i < size; i++) {
      sums[i] = i - size;
    }
    for (int sum : sums) {
      result = result + twoSum.run(sum);
    }

    System.out.println("The result of above trial is " + result);
  }
}
