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
	
	public static int comparisonsCount = 0;
	/**
	 * Merge sort algorithm demo
	 * @param a -- integer array to be sorted
	 * Sorting is not in-place - uses additional tempArray to store results of merge
	 */
	public static void mergeSort(int[] a) {
		comparisonsCount = 0;
		mergeSort(a, 0, a.length - 1);
	} // end mergeSort

	public static void mergeSort(int[] a, int first, int last) {
	  int[] tempArray = new int[a.length];
	  mergeSort(a, tempArray, first, last);
	} // end mergeSort
	
	private static void mergeSort(int[] a, int[] tempArray, int first, int last) {
	   if (first < last)  {  // sort each half there is an interval to be sorted
		   int mid = (first + last)/2;// index of midpoint
		   mergeSort(a, tempArray, first, mid);  // sort left half array[first..mid]
		   mergeSort(a, tempArray, mid + 1, last); // sort right half array[mid+1..last]

		   if (a[mid]>a[mid + 1])      //See Chapter 9
	     	 	merge(a, tempArray, first, mid, last); // merge the two halves
		   //else skip merge step: max of the left < min of the right
	   }  // end if
	}  // end mergeSort
	
	private static void merge(int[] a, int[] tempArray, int first, int mid, int last) {
		// Two adjacent subarrays are a[first..mid] and a[mid+1..last].
		// set the reading pointers to the first item in the corresponding interval
		int i = first;		
		int j = mid + 1;
		
		// while both subarrays are not empty, copy the
	   // smaller item into the temporary array
		int index = first; // next available location in tempArray
		
		while (i <= mid && j <=last) { 		
			comparisonsCount++;
	      if (a[i] <= a[j]) {  
	      	tempArray[index] = a[i];
	        i++;
	      }
	      else
	      {  
	      	tempArray[index] = a[j];
	        j++;
	      }  // end if
	      index ++;
	   }  // end for

	   // finish off the nonempty subarray

	   // finish off the first subarray, if necessary
	   while (i <= mid)	     
	      tempArray[index++] = a[i++];
	   
	   // finish off the second subarray, if necessary
	   while (j <= last)	      
	      tempArray[index++] = a[j++];
		
	   // copy the result back into the original array
	   for (index = first; index <= last; index++)
	      a[index] = tempArray[index];
	}  // end merge


	/**
	 * Quick sort demo
	 * @param a an array to be sorted
	 * 
	 */
	public static void quickSort(int[] a) {
		int n = a.length;
		comparisonsCount = 0;
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
	} // end quickSort

	// 
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
		  comparisonsCount++;
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
		comparisonsCount = 0;
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
		int n = 100;
		int [] a = DataGenerator.getRandIntArray(n);
		System.out.println("Input array of size  " + n + ": "+ DataGenerator.arrToString(a));
		
		mergeSort(a);
		System.out.println("Sorted with merge sort with total " + comparisonsCount +" comparisons.");
		System.out.println("Output array: " + DataGenerator.arrToString(a));
		
		a = DataGenerator.getRandIntArray(n);
		System.out.println("\nInput array of size  " + n + ": "+ DataGenerator.arrToString(a));
		
		quickSort(a);
		System.out.println("Sorted with quick sort (first is pivot) with total " + comparisonsCount +" comparisons.");
		System.out.println("Output array: " + DataGenerator.arrToString(a));		
		
		System.out.println("\nInput array (already sorted) of size  " + n + ": "+ DataGenerator.arrToString(a));
		quickSort(a);
		System.out.println("Sorted with quick sort (first is pivot) with total " + comparisonsCount +" comparisons.");
		System.out.println("Output array: " + DataGenerator.arrToString(a));
		
		System.out.println("\nInput array (already sorted) of size  " + n + ": "+ DataGenerator.arrToString(a));
		randQuickSort(a);
		System.out.println("Sorted with RANDOMIZED quick sort  with total " + comparisonsCount +" comparisons.");
		System.out.println("Output array: " + DataGenerator.arrToString(a));
		
		
	}
}
