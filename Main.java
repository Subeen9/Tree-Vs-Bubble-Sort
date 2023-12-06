import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            BinaryTree tree = buildTreeFromDataFile("C:\\Users\\subin\\IdeaProjects\\java\\src\\number.txt");
            System.out.println("Recursive In-Order Traversal:");
            tree.printInOrder(tree.getRoot());
            System.out.println("Number of comparisons during recursive in-order traversal: " + tree.getNumberOfComparisons());
            System.out.println("\nIterative In-Order Traversal:");
            tree.printInOrderIterative();
            System.out.println("Number of comparisons during iterative in-order traversal: " + tree.getNumberOfComparisons());

            writeTreeToFile(tree, "C:\\Users\\subin\\IdeaProjects\\java\\src\\output.txt");

            int[] numbersForBubbleSort = readNumbersFromFile("C:\\Users\\subin\\IdeaProjects\\java\\src\\number.txt");

            BubbleSort bubbleSorter = new BubbleSort(numbersForBubbleSort);
            bubbleSorter.sort();

            int[] sortedNumbers = bubbleSorter.getSortedArray();
            int bubbleSortComparisonCount = bubbleSorter.getSwaps();
            writeSortedArrayToFile(sortedNumbers, "C:\\Users\\subin\\IdeaProjects\\java\\src\\output.txt", bubbleSortComparisonCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BinaryTree buildTreeFromDataFile(String filePath) throws IOException {
        Scanner scanner = new Scanner(new File(filePath));
        BinaryTree tree = new BinaryTree();

        while (scanner.hasNextLine()) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                tree.buildTree(number);
            } else {
                scanner.nextLine();
            }
        }

        return tree;
    }
    private static void writeTreeToFile(BinaryTree tree, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        tree.writeInOrderToFile(tree.getRoot(), fileWriter);
        fileWriter.write("\nNumber of comparisons during tree traversal: " + tree.getNumberOfComparisons());
        fileWriter.close();
    }
    private static int[] readNumbersFromFile(String filePath) throws IOException {
        Scanner scanner = new Scanner(new File(filePath));
        int[] numbers = new int[1000];
        int i = 0;
        while (scanner.hasNextLine() && scanner.hasNextInt()) {
            numbers[i] = scanner.nextInt();
            i++;
        }
        scanner.close();
        return numbers;
    }
    private static void writeSortedArrayToFile(int[] numbers, String filePath, int comparisonCount) throws IOException {
        FileWriter writeOnFile = new FileWriter(filePath);
        writeOnFile.write("BUBBLE_SORT\n");

        int numbersPerRow = 10;
        int count = 0;

        for (int number : numbers) {
            writeOnFile.write(number + " ");
            System.out.print(number + " ");

            count++;
            if (count == numbersPerRow) {
                writeOnFile.write("\n");
                System.out.println();
                count = 0;
            }
        }

        writeOnFile.write("\nNumber of comparisons: " + comparisonCount);
        writeOnFile.close();
        System.out.println("\nNumber of comparisons during bubble sort: " + comparisonCount);
    }
}