package Math;

public class CountSumConsecutive {
    public static int consecutive(long num){
        int count = 0;

        for (float k=2; k < Math.sqrt(num*2); k++){
            float b = num/(k+1) - k/2;
            if (b > 0 && (int) b == b) count++;
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(CountSumConsecutive.consecutive(6));
    }
}
