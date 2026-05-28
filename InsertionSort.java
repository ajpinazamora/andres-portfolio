/**
 * InsertionSort.java
 *
 * This class implements the Insertion Sort algorithm using a list of strings.
 * It sorts the words in lexicographic order and counts the number of comparisons.
 *
 * The algorithm works by maintaining a sorted list and inserting each element
 * into the correct position as it is removed from the original list.
 */

 import java.util.*;

 public class InsertionSort {
 
     private long comparisons = 0; // Keeps track of the number of comparisons made
 
     /**
      * Sorts a list of strings using insertion sort while counting comparisons.
      * @param data The list of strings to be sorted.
      */
     public void sort(List<String> data) {
         comparisons = 0;
 
         // Create a working copy (S) and an empty list (P) for sorted elements
         LinkedList<String> S = new LinkedList<>(data); 
         List<String> P = new LinkedList<>();            
 
         // Remove each element from S and insert into sorted list P
         while (!S.isEmpty()) {
             String e = S.removeFirst();     // Dequeue from S
             insertSorted(P, e);             // Insert in correct position in P
         }
 
         // Move sorted elements from P back to S
         while (!P.isEmpty()) {
             String e = P.remove(0);         // Remove from front of sorted list
             S.addLast(e);                   // Add to result list
         }
 
         // Replace original data with sorted result
         data.clear();
         data.addAll(S);
     }
 
     /**
      * Inserts a string e into the correct position in the sorted list P.
      * @param P The list maintaining sorted order.
      * @param e The element to be inserted.
      */
     private void insertSorted(List<String> P, String e) {
         int i = 0;
         while (i < P.size()) {
             comparisons++;
             if (e.compareTo(P.get(i)) < 0) {
                 break; // Found the correct position
             }
             i++;
         }
         P.add(i, e); // Insert e at the determined position
     }
 
     /**
      * Returns the number of comparisons made during the sort.
      * @return long - comparison count
      */
     public long getComparisons() {
         return comparisons;
     }
 }
 