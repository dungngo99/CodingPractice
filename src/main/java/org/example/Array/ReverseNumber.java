package Array;

public class ReverseNumber {
    public int solution(int number) {
        int tmp = 0;
        int l = (int) Math.log10(number);

        while (number > 0) {
            int digit = number % 10;
            tmp += (int) digit * Math.pow(10, l);
            number = (number - digit) / 10;
            l--;
        }

        return tmp < Integer.MAX_VALUE ? tmp : -1;
    }

    public static void main(String[] args) {
        ReverseNumber solution = new ReverseNumber();
        int[] nums = { 100001, 102309, 12345, 1000, 1, 0, 1234 };
        for (int num : nums) {
            System.out.println(solution.solution(num));
        }
    }
}