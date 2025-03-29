package org.example.Stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LimitedSizeFIFO {
    private static Queue<String> queue = new LinkedList<>();
    private static int size = 0;

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] lst = input.split(" ");
        int N = Integer.parseInt(lst[0]);
        size = Integer.parseInt(lst[1]);

        for (int i=0; i<N; i++) {
            String[] item = br.readLine().split(" ");
            String command = item[0];
            String string = item[1];

            if (command.equals("OFFER")){
                System.out.println(LimitedSizeFIFO.offer(string));
            }else if (command.equals("TAKE")){
                String res = LimitedSizeFIFO.take();
                if (res != null) System.out.println(res);
                else System.out.println("");
            }else{
                System.out.println(LimitedSizeFIFO.getSize());
            }
        }
    }

    public static int getSize(){
        return queue.size();
    }

    public static String take(){
        return queue.poll();
    }

    public static boolean offer(String string){
        if (queue.size() == size) return false;
        queue.add(string);
        return true;
    }
}