package Array;

public class ReverseWord {
    public String reverseStringExtraSpace(String word) {
        int i = 0;
        int j = word.length() - 1;
        char[] lst = new char[word.length()];

        while (i < j) {
            lst[i] = word.charAt(j);
            lst[j] = word.charAt(i);
            i += 1;
            j -= 1;
        }

        return new String(lst);
    }

    public String reverseStringConstantSpace(String word) {
        char[] chars = word.toCharArray();
        reverseChars(chars, 0, chars.length - 1);

        int start = 0;
        int end = 0;
        while (end < chars.length) {
            if (chars[end] == ' ' || end == chars.length - 1) {
                reverseChars(chars, start, end - 1);
                end += 1;
                start = end;
            } else
                end += 1;
        }
        return new String(chars);
    }

    public void reverseChars(char[] chars, int start, int end) {
        while (start < end) {
            char t = chars[start];
            chars[start] = chars[end];
            chars[end] = t;
            start += 1;
            end -= 1;
        }
    }

    public static void main(String[] args) {
        ReverseWord solution = new ReverseWord();
        System.out.println(solution.reverseStringExtraSpace("hello world"));
        System.out.println(solution.reverseStringConstantSpace("I love java"));
    }
}
