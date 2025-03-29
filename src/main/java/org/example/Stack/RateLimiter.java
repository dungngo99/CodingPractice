package Stack;

import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

public class RateLimiter {
    public static class MyObject{
        public Queue<Integer> timestamps;
        public int ipCover;
        public MyObject(){
            this.timestamps = new LinkedList<>();
            this.ipCover = 0;
        }
    }

    public int[] solution(String[] timestamps, String[] ipAddress, int limit, int window ){
        // idea: have bucket, key=ipAddress, value=(coverTime, queue of timestamps)
        // coverTime is the time spanned by the first value in queue.
        // There are 2 cases: next timestamp is in range of cover or not.
        // if it is in, add to queue (if still have limit). otherwise drop it
        // if it is not, remove the first timestamp until cover the current timestamp
        int[] res = new int[ipAddress.length];
        HashMap<String, MyObject> store = new HashMap<>();

        for (int i=0; i<timestamps.length; i++){
            int time = Integer.parseInt(timestamps[i]);
            String ip = ipAddress[i];

            if (!store.containsKey(ip)){
                MyObject obj = new MyObject();
                obj.timestamps.add(time);
                obj.ipCover = time + window;

                store.put(ip, obj);
                res[i] = 1;
            }else{
                MyObject obj = store.get(ip);
                if (obj.ipCover >= time){
                    if (obj.timestamps.size() < limit){
                        obj.timestamps.add(time);
                        res[i] = 1;
                    }else{
                        res[i] = 0;
                    }
                }else{
                    obj.timestamps.add(time);
                    int nextTimestamp = obj.ipCover;
                    obj.timestamps.poll();
                    while (nextTimestamp < time && !obj.timestamps.isEmpty()){
                        nextTimestamp = obj.timestamps.poll() + window;
                    }
                    res[i] = 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        RateLimiter solution = new RateLimiter();
        String[] timestamps = new String[]{"7957", "7957", "7958"};
        String[] ipAddress = new String[]{"49", "38", "38"};
        int[] res = solution.solution(timestamps, ipAddress, 2, 10);
        System.out.println(Arrays.toString(res));
    }
}