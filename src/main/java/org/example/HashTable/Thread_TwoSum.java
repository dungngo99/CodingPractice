package HashTable;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.List;

public class Thread_TwoSum implements Callable<Integer>{
  private TwoSum twoSum;
  private int[] targets;
  private int result;

  public Thread_TwoSum(int[] targets, TwoSum twoSum){
    this.twoSum = twoSum;
    this.targets = targets;
    this.result = 0;
  }

  public int getResult(){
    return this.result;
  }

  public Integer call() throws Exception{
    for (int target: this.targets){
      this.result = this.result + this.twoSum.run(target);
    }
    return this.result;
  }

  public static void main(String args[]){
    TwoSum twoSum = new TwoSum();
    twoSum.readFile("src/Data/2sum.txt");

    int targetSize = 5;
    int threadSize = 5;

    List<Thread_TwoSum> myList = new ArrayList<Thread_TwoSum>();
    for (int i=0; i<threadSize; i++){
      int noise = 1 + (int) Math.random()*10;
      int[] targets = new int[targetSize];

      for (int j=0; j<targetSize; j++){
        targets[j] = -i*j+noise;
      }

      myList.add(new Thread_TwoSum(targets, twoSum));
    }

    ExecutorService executor = null;
    try{
      executor = Executors.newFixedThreadPool(threadSize);
      List<Future<Integer>> results = executor.invokeAll(myList);
      for (int i=0; i<threadSize; i++){
        Future<Integer> result = results.get(i);
        System.out.println("Result " + result.get());
      }
    }catch (InterruptedException e){
      e.printStackTrace();
    }catch (ExecutionException e){
      e.printStackTrace();
    }finally{
      if (executor != null) executor.shutdown();
    }
  }
}