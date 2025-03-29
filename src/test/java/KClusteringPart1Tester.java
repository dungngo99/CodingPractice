import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import GraphTheory.KClustering;

public class KClusteringPart1Tester {
    private List<String> inputFiles;

    @Test
    public static void KClusteringTest(String inputFile, String outputFile) {
        System.out.println("Testing with file=" + inputFile);
        KClustering kClustering = new KClustering(inputFile);
        kClustering.readContent(inputFile);

        int maxSpacing = kClustering.kCluster(4);
        int trueMaxSpacing = KClusteringPart1Tester.readOutputFile(outputFile);
        assert (maxSpacing == trueMaxSpacing);
        System.out.println("Successfully passed the test with file=" + outputFile + "\n\n");
    }

    public KClusteringPart1Tester(String folderPath) {
        this.inputFiles = new ArrayList<>();
        File folder = new File(folderPath);
        if (!folder.exists()) {
            return;
        }
        for (File file : folder.listFiles()) {
            if (file == null) {
                continue;
            }
            if (file.exists()) {
                String filePath = file.getPath();
                if (filePath.contains("input")) {
                    inputFiles.add(filePath);
                }
            }
        }
    }

    public void printFiles() {
        System.out.println("----- Input files -----");
        for (String file : this.inputFiles)
            System.out.println(file);
    }

    public List<String> getFiles() {
        return this.inputFiles;
    }

    public static int readOutputFile(String outputFile) {
        FileReader file = null;
        Scanner scanner = null;
        int output = 0;
        try {
            file = new FileReader(outputFile);
            scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                output = Integer.parseInt(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return output;
    }

    public static void main(String[] args) {
        String folderPath = "...";
        KClusteringPart1Tester tester = new KClusteringPart1Tester(folderPath);
        tester.printFiles();

        for (String inputFile : tester.getFiles()) {
            String outputFile = inputFile.replace("input", "output");
            KClusteringTest(inputFile, outputFile);
        }
    }
}
