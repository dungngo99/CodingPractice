package Heap;

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

/*
 * A class to implement the "Median Maintenance" algorithm
 * Input: The text file contains a list of the integers from 1 to 10000 in unsorted 
 * order. You should treat this as a stream of numbers, arriving one by one.
 * Output: return the modulo of sum of these 10000 medians
 */
public class MedianMaintainance {
    Heap smallHeap;
    Heap largeHeap;
    private static final int MAX_SIZE = 10000;

    public MedianMaintainance() {
        smallHeap = new Heap();
        largeHeap = new Heap();
    }

    /*
     * Insert a new value to heaps and keep track the median between two heaps
     */
    public void insert(int element) {
        smallHeap.insert(-element);

        if (smallHeap.size() == largeHeap.size() + 2) {
            int tmp = -smallHeap.extractTop();
            largeHeap.insert(tmp);
        }
    }

    /*
     * Get the median of the streaming array 
     */
    public int getMedian() {
        if (smallHeap.size() == largeHeap.size()) {
            int topSmall = -smallHeap.peekTop();
            int topLarge = largeHeap.peekTop();
            return (topSmall + topLarge) / 2;
        }
        return -smallHeap.peekTop();
    }

    /*
     * Read the file and keep track the median of the streaming values
     */
    public int readFile(String path) throws IOException {
        FileReader file = null;
        Scanner scan = null;
        int total = 0;

        try {
            file = new FileReader(path);
            scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                int newElement = Integer.parseInt(line);
                this.insert(newElement);
                total = total + this.getMedian();
            }
        } finally {
            if (file != null)
                file.close();
            if (scan != null)
                scan.close();
        }

        return total % MedianMaintainance.MAX_SIZE;
    }

    public static void simpleTest() {
        int[] array = new int[] {
                6331, 2793, 1640, 9290,
                225, 625, 6195, 2303,
                5685, 1354, 4292, 7600,
                6447, 4479, 9046, 729 };

        MedianMaintainance medianHeap = new MedianMaintainance();
        for (int num : array) {
            medianHeap.insert(num);
            System.out.print(medianHeap.getMedian() + " ");
        }
    }

    public static void main(String[] args) {
        // MedianMaintainance.simpleTest();
        try {
            MedianMaintainance medianMaintainance = new MedianMaintainance();
            int result = medianMaintainance.readFile("src/Data/Median.txt");
            System.out.println("The result of \"Median Maintainance\" problem is " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}