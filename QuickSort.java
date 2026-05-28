import java.util.Comparator;

/**
 * QuickSort.java
 *
 * This class implements the Quick Sort algorithm to sort an array of strings
 * in lexicographic order. It also counts the number of direct string comparisons
 * made during the sorting process.
 *
 * The implementation uses in-place quicksort with Lomuto-style partitioning logic.
 */
public class QuickSort {

    private long comparison; // Counts the number of comparisons

    /**
     * Returns the number of comparisons performed during sorting.
     * @return number of comparisons
     */
    public long getComparisons() {
        return comparison;
    }

    /**
     * Public method to start the quicksort algorithm on the given array.
     * @param array the array of strings to sort
     */
    public void sort(String[] array) {
        comparison = 0; // Reset counter
        quickSortInPlace(array, new StringComparator(), 0, array.length - 1);
    }

    /**
     * Private inner class to compare two strings and increment the comparison counter.
     */
    private class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            comparison++;
            return s1.compareTo(s2);
        }
    }

    /**
     * Performs the in-place quicksort using the provided comparator.
     *
     * @param S     the array to sort
     * @param comp  comparator to compare elements
     * @param a     start index of the segment to sort
     * @param b     end index of the segment to sort
     */
    private static <K> void quickSortInPlace(K[] S, Comparator<K> comp, int a, int b) {
        if (a >= b) return; // Base case: 0 or 1 element

        int left = a;
        int right = b - 1;
        K pivot = S[b]; // Choose last element as pivot
        K temp;

        // Partition phase
        while (left <= right) {
            while (left <= right && comp.compare(S[left], pivot) < 0) left++;
            while (left <= right && comp.compare(S[right], pivot) > 0) right--;

            if (left <= right) {
                // Swap elements at left and right
                temp = S[left];
                S[left] = S[right];
                S[right] = temp;
                left++;
                right--;
            }
        }

        // Place pivot in its final location
        temp = S[left];
        S[left] = S[b];
        S[b] = temp;

        // Recursively sort partitions
        quickSortInPlace(S, comp, a, left - 1);
        quickSortInPlace(S, comp, left + 1, b);
    }
}
