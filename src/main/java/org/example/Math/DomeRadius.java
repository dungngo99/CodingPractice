package Math;

public class DomeRadius {
    public static void findRadius(int a, int h) {
        double powH = Math.pow(h, 2);
        double powA = Math.pow(a, 2);
        double res = Math.sqrt((powH * powA / 4) / (powH + powA / 4));
        System.out.printf("%.4f%n", res);
    }

    public static void main(String[] args) {
        DomeRadius.findRadius(15, 10);
    }
}