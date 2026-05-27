/**
 * SelectionSort.java
 * 
 * This class implements the Selection Sort algorithm using a list of strings.
 * It counts the number of comparisons made and sorts the words in lexicographic order.
 * 
 * The algorithm works by selecting the minimum element from the unsorted part of the list,
 * removing it, and adding it to the result list in order.
 */

 import java.util.*;

 public class SelectionSort {
 
     private long comparison = 0; // Tracks the number of comparisons made
 
     /**
      * Sorts the list of strings using selection sort and updates the comparison count.
      * @param data The list of strings to be sorted.
      */
     public void sort(List<String> data) {
         comparison = 0;
 
         // Create a linked list S from input data
         LinkedList<String> S = new LinkedList<>(data);
 
         // Create a temporary list P to hold elements during sorting
         List<String> P = new ArrayList<>();
 
         // Move all elements from S to P (initial preparation)
         while (!S.isEmpty()) {
             String e = S.removeFirst();
             P.add(e);
         }
 
         // Perform selection sort: repeatedly find and remove the smallest element
         while (!P.isEmpty()) {
             int minIndex = 0;
 
             // Find the index of the smallest element in P
             for (int i = 1; i < P.size(); i++) {
                 comparison++;
                 if (P.get(i).compareTo(P.get(minIndex)) < 0) {
                     minIndex = i;
                 }
             }
 
             // Remove the minimum element and add it to the end of S
             String e = P.remove(minIndex);
             S.addLast(e);
         }
 
         // Copy sorted result from S back to data
         data.clear();
         data.addAll(S);
     }
 
     /**
      * Returns the number of comparisons made during the sort.
      * @return long - comparison count
      */
     public long getComparisons() {
         return comparison;
     }
 }
 