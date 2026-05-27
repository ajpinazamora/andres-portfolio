/**
 * Project6.java
 * This program compares five different sorting algorithms: Selection Sort, Insertion Sort,
 * Heap Sort, Merge Sort, and Quick Sort.
 * It reads a list of words from standard input, sorts them using each algorithm,
 * and reports the number of comparisons and execution time (in milliseconds).
 */

 import java.util.*;
 import java.io.*;
 
 public class Project6 {
 
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         List<String> inputList = new ArrayList<>();
 
         // Read the number of words and the words themselves from input
         if (scanner.hasNextInt()) {
             int n = scanner.nextInt();
             scanner.nextLine(); // consume newline
             for (int i = 0; i < n && scanner.hasNextLine(); i++) {
                 inputList.add(scanner.nextLine().trim());
             }
         }
 
         // Print the table header
         System.out.printf("%-15s | %12s | %20s\n", "Algorithm", "Comparisons", "Time (Milliseconds)");
         System.out.println("================+==============+======================");
 
         // Run and compare each sorting algorithm
         runSelectionSort(inputList);
         runInsertionSort(inputList);
         runHeapSort(inputList);
         runMergeSort(inputList);
         runQuickSort(inputList);
     }
 
     // Runs selection sort and reports results
     private static void runSelectionSort(List<String> input) {
         List<String> data = new ArrayList<>(input);
         SelectionSort sorter = new SelectionSort();
 
         long start = System.nanoTime();
         sorter.sort(data);
         long end = System.nanoTime();
 
         printResult("Selection Sort", sorter.getComparisons(), start, end);
     }
 
     // Runs insertion sort and reports results
     private static void runInsertionSort(List<String> input) {
         List<String> data = new ArrayList<>(input);
         InsertionSort sorter = new InsertionSort();
 
         long start = System.nanoTime();
         sorter.sort(data);
         long end = System.nanoTime();
 
         printResult("Insertion Sort", sorter.getComparisons(), start, end);
     }
 
     // Runs heap sort and reports results
     private static void runHeapSort(List<String> input) {
         List<String> data = new ArrayList<>(input);
         HeapSort sorter = new HeapSort();
 
         long start = System.nanoTime();
         sorter.sort(data);
         long end = System.nanoTime();
 
         printResult("Heap Sort", sorter.getComparisons(), start, end);
     }
 
     // Runs merge sort and reports results
     private static void runMergeSort(List<String> input) {
         String[] data = input.toArray(new String[0]);
         MergeSort sorter = new MergeSort();
 
         long start = System.nanoTime();
         sorter.sort(data);
         long end = System.nanoTime();
 
         printResult("Merge Sort", sorter.getComparisons(), start, end);
     }
 
     // Runs quick sort and reports results
     private static void runQuickSort(List<String> input) {
         String[] data = input.toArray(new String[0]);
         QuickSort sorter = new QuickSort();
 
         long start = System.nanoTime();
         sorter.sort(data);
         long end = System.nanoTime();
 
         printResult("Quick Sort", sorter.getComparisons(), start, end);
     }
 
     // Helper method to print results for each sorting algorithm
     private static void printResult(String name, long comparisons, long startTime, long endTime) {
         double milliseconds = (endTime - startTime) / 1_000_000.0;
         System.out.printf("%-15s | %12d | %20.5f\n", name, comparisons, milliseconds);
         System.out.println("----------------+--------------+----------------------");
     }
 }
 