/**
 * HeapSort.java
 *
 * This class implements the Heap Sort algorithm using an array-based heap structure.
 * It sorts a list of strings in lexicographic order and keeps track of the number of comparisons.
 *
 * The sorting process builds a min-heap and repeatedly removes the minimum element.
 */

 import java.util.*;

 public class HeapSort {
 
     private long comparison = 0; // Tracks number of comparisons made
 
     /**
      * Sorts the given list of strings using heap sort.
      * @param data The list of strings to be sorted.
      */
     public void sort(List<String> data) {
         comparison = 0;
         int n = data.size();
         String[] heap = new String[n];
 
         // Build the heap using upheap
         for (int i = 0; i < n; i++) {
             heap[i] = data.get(i);
             upheap(heap, i);
         }
 
         // Extract elements from the heap in sorted order
         for (int i = n - 1; i >= 0; i--) {
             swap(heap, 0, i);            // Move smallest to end
             downheap(heap, 0, i);        // Restore heap structure
         }
 
         // Copy sorted elements back to original list
         for (int i = 0; i < n; i++) {
             data.set(i, heap[i]);
         }
     }
 
     /**
      * Performs upheap operation to restore heap after insertion.
      * @param heap The array-based heap.
      * @param index The index of the newly inserted element.
      */
     public void upheap(String[] heap, int index) {
         int parent = (index - 1) / 2;
         while (index > 0 && compare(heap[index], heap[parent]) < 0) {
             swap(heap, index, parent);
             index = parent;
             parent = (index - 1) / 2;
         }
     }
 
     /**
      * Performs downheap operation to restore heap after removal.
      * @param heap The array-based heap.
      * @param index The index of the root to start downheap.
      * @param size The effective size of the heap.
      */
     public void downheap(String[] heap, int index, int size) {
         int left = 2 * index + 1;
         while (left < size) {
             int right = 2 * index + 2;
             int smaller = left;
 
             // Choose the smaller child
             if (right < size && compare(heap[right], heap[left]) < 0) {
                 smaller = right;
             }
 
             // If parent is smaller than both children, done
             if (compare(heap[smaller], heap[index]) >= 0) break;
 
             swap(heap, index, smaller);
             index = smaller;
             left = 2 * index + 1;
         }
     }
 
     /**
      * Compares two strings and increments the comparison count.
      */
     private int compare(String a, String b) {
         comparison++;
         return a.compareTo(b);
     }
 
     /**
      * Swaps two elements in the heap.
      */
     private void swap(String[] heap, int i, int j) {
         String temp = heap[i];
         heap[i] = heap[j];
         heap[j] = temp;
     }
 
     /**
      * Returns the number of comparisons made during sorting.
      */
     public long getComparisons() {
         return comparison;
     }
 }
 