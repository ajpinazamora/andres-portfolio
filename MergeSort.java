/**
 * MergeSort.java
 * 
 * This class implements the Merge Sort algorithm to sort an array of strings
 * in lexicographic order. It counts the number of string comparisons during sorting.
 *
 * The algorithm follows the classic divide-and-conquer approach:
 * 1. Divide the array into two halves.
 * 2. Recursively sort each half.
 * 3. Merge the sorted halves into a single sorted array.
 */

 import java.util.*;

 public class MergeSort {
 
     private long comparison = 0;  // Tracks the number of comparisons made
 
     /**
      * Sorts the given array using merge sort and a custom comparator that counts comparisons.
      * @param array The array of strings to be sorted.
      */
     public void sort(String[] array) {
         comparison = 0;  // Reset comparison count
         mergeSort(array, new StringComparator());
     }
 
     /**
      * Recursive merge sort method (generic version).
      * Splits the array and sorts each half, then merges them.
      * @param S The array to be sorted
      * @param comp The comparator used for comparing elements
      */
     public static <K> void mergeSort(K[] S, Comparator<K> comp) {
         int n = S.length;
         if (n < 2) return;  // Base case: already sorted
 
         int mid = n / 2;
         K[] S1 = Arrays.copyOfRange(S, 0, mid);  // Left half
         K[] S2 = Arrays.copyOfRange(S, mid, n);  // Right half
 
         mergeSort(S1, comp);  // Recursively sort left
         mergeSort(S2, comp);  // Recursively sort right
 
         merge(S1, S2, S, comp);  // Merge sorted halves
     }
 
     /**
      * Merges two sorted arrays S1 and S2 into array S.
      * @param S1 First sorted array
      * @param S2 Second sorted array
      * @param S Result array
      * @param comp Comparator for comparing elements
      */
     public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp) {
         int i = 0, j = 0;
         while (i + j < S.length) {
             if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) <= 0)) {
                 S[i + j] = S1[i++];  // Take from S1
             } else {
                 S[i + j] = S2[j++];  // Take from S2
             }
         }
     }
 
     /**
      * Returns the number of comparisons made during the sort.
      * @return long - total comparisons
      */
     public long getComparisons() {
         return comparison;
     }
 
     /**
      * Inner class that compares two strings and increments the comparison counter.
      */
     private class StringComparator implements Comparator<String> {
         public int compare(String a, String b) {
             comparison++;
             return a.compareTo(b);
         }
     }
 }
 