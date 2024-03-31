package sorting;

import java.util.Arrays;

/**
 * Demo of MergeSort and QuickSort
 * Simplified for readability 
 * Might be not the most efficient
 * @author MGBar
 *
 */
public class AdvancedSorting {
	
	public static int comparisons = 0;
	/**
	 * Merge sort algorithm demo
	 * @param A -- integer array to be sorted
	 * Sorting is not in-place - uses additional arrays to store results of merge
	 */
	
	private static int [] mergeSort(int[] A) {
		if (A.length <= 1)
	        return A;

	    // Finding the mid of the array
	    int mid = A.length/2;	    

	    // Find sizes of two subarrays to be merged
        int n1 = mid;
        int n2 = A.length - mid;

        // Create temp sub-arrays
        int B[] = new int[n1];
        int C[] = new int[n2];

        // Copy data to temp sub-arrays
        for (int i = 0; i < n1; ++i)
            B[i] = A[i];
        for (int j = 0; j < n2; ++j)
            C[j] = A[mid + j];
        
        B = mergeSort(B);
        C = mergeSort(C);
        
	    int [] A_sorted = merge(B, C);

	    return A_sorted;
	}  // end mergeSort
	
	private static int[] merge(int[] B, int[] C) {
		int n1 = B.length;
        int n2 = C.length;
		int [] D = new int [n1+n2];
		
		// Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray D
        int k = 0;
        
        while (i < n1 && j < n2) {
        	comparisons++;
            if (B[i] <= C[j]) {
                D[k] = B[i];
                i++;
            }
            else {
                D[k] = C[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of B[] if any
        while (i < n1) {
            D[k] = B[i];
            i++;
            k++;
        }

        // Copy remaining elements of C[] if any
        while (j < n2) {
            D[k] = C[j];
            j++;
            k++;
        }
        return D;
	}  


	/**
	 * Quick sort demo
	 * @param a an array to be sorted
	 * 
	 */
	public static void quickSort(int[] a) {
		int n = a.length;		
		quickSort(a, 0, n-1);
	} // end quickSort
	
	/** Sorts an array into ascending order. Uses quick sort with
	 *  first element selected as a pivot	 *  
	 */
	public static void quickSort(int[] a, int first, int last)	{
		if (first >= last)
			return;
	    // create the partition: Smaller | Pivot | Larger
	    int pivotPosition = partition(a, first, last);
	    
	    // recursively sort subarrays Smaller and Larger
	    quickSort(a, first, pivotPosition - 1);
	    quickSort(a, pivotPosition + 1, last);	  
	} 
 
	/** Task: Partitions an array as part of quick sort into two subarrays
	 *        called Smaller and Larger that are separated by a single
	 *        element called the pivot. 
	 *        Elements in Smaller are <= pivot and appear before the 
	 *        pivot in the array.
	 *        Elements in Larger are >= pivot and appear after the 
	 *        pivot in the array.
	 *  @param a      an array of Comparable objects
	 *  @param first  the integer index of the first array element; 
	 *                first >= 0 and < a.length 
	 *  @param last   the integer index of the last array element; 
	 *                last - first >= 3; last < a.length
	 *  @return the index of the pivot */
	private static int partition(int[] a, int first, int last) {
	  int pivot = a[first];	
	  
	  // distribute elements of a into  subarrays Smaller = a[first..endSmaller]
	  // and Larger  = a[endSmaller+1..last-1]
	  // such that elements in Smaller are <= pivot and 
	  // elements in Larger are >= pivot; initially, these subarrays are empty	  
	 
	  int k = first; 
	  for (int i=first+1; i <= last; i++) {	  
		  // starting at beginning of array, leave elements that are < pivot;
		  // keep the divider between small and large in variable k
		  comparisons++;
		  if (a[i] <= pivot)  { // k is the index of the last in smaller part
			  k++;  // the smaller partition getting bigger
			  swap (a, i, k);	    	
		  }	    
	  }
	  
	  // place pivot into its final position between Smaller and Larger subarrays
	  swap(a, first, k);
	  return k;
	} // end partition
	
	public static void randQuickSort(int[] a) {
		int n = a.length;
		comparisons = 0;
		randQuickSort(a, 0, n-1);
	} // end quickSort
	
	/** Sorts an array into ascending order. Uses quick sort with
	 *  first element selected as a pivot	 *  
	 */
	public static void randQuickSort(int[] a, int first, int last)	{
		if (first >= last)
			return;
		
		//swap the element found at a random index into position first
		int randPos = first + Integer.valueOf((int) (Math.random() * (last - first)));
		swap(a, first, randPos);
		
	    // create the partition: Smaller | Pivot | Larger
	    int pivotPosition = partition(a, first, last);
	    
	    // recursively sort subarrays Smaller and Larger
	    randQuickSort(a, first, pivotPosition - 1);
	    randQuickSort(a, pivotPosition + 1, last);	  
	} // end quickSort
  	
	/** Swaps the array entries a[i] and a[j].
  	@param a  an array of integers
  	@param i  an integer >= 0 and < a.length
  	@param j  an integer >= 0 and < a.length */
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
	} // end swap
	
	public static void main (String [] args) {
		int n = 1000;
		int [] a = DataGenerator.getRandIntArray(n);
		if (n < 30)
			System.out.println("Input array of size  " + n + ": "+ DataGenerator.arrToString(a));
		else
			System.out.println("Input array of size  " + n);
		
		comparisons = 0;
		a = mergeSort(a);
		System.out.println("Sorted with merge sort with total " + comparisons +" comparisons.");
		if (n < 30) System.out.println("Output array: " + DataGenerator.arrToString(a));
		
		a = DataGenerator.getRandIntArray(n);
		
		if (n < 30)
			System.out.println("\nInput array of size  " + n + ": "+ DataGenerator.arrToString(a));
		else
			System.out.println("\nInput array of size  " + n);
		comparisons = 0;
		quickSort(a);		
		
		System.out.println("Sorted with quick sort (first is pivot) with total " + comparisons +" comparisons.");
		if (n < 30) System.out.println("Output array: " + DataGenerator.arrToString(a));		
		
		comparisons = 0;
		if (n < 30)
			System.out.println("\nInput array (already sorted) of size  " + n + ": "+ DataGenerator.arrToString(a));
		else
			System.out.println("\nInput array (already sorted) of size  " + n );
		quickSort(a);
		
		System.out.println("Sorted with quick sort (first is pivot) with total " + comparisons +" comparisons.");
		if (n < 30) System.out.println("Output array: " + DataGenerator.arrToString(a));
		
		comparisons = 0;
		if (n < 30)
			System.out.println("\nInput array (already sorted) of size  " + n + ": "+ DataGenerator.arrToString(a));
		else
			System.out.println("\nInput array (already sorted) of size  " + n );
		randQuickSort(a);
		System.out.println("Sorted with RANDOMIZED quick sort  with total " + comparisons +" comparisons.");
		if (n < 30) System.out.println("Output array: " + DataGenerator.arrToString(a));
		
		
	}
}
