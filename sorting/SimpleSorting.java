/**
   Class to demo of simple sorting algorithms:
   selection sort, insertion sort, bubble sort.
*/
package sorting;

public class SimpleSorting {
	public static int comparisons = 0;
	
	/** Sorts the first n objects in an array into ascending order.
    @param a  an array of integers (for simplicity of the demo)
    Can be easily rteplaced with generics and comparable
    */

	public static void selectionSort(int[] a) {
		int n = a.length;
		comparisons = 0;
		for (int index = 0; index < a.length - 1; index++)  {
			int indexOfNextSmallest = getIndexOfSmallest(a, index, n - 1);
			swap(a, index, indexOfNextSmallest);
			// Maintaining Invariant: a[0] <= a[1] <= . . . <= a[index] <= all other a[i]
		} // end for
	} // end selectionSort

	/** Finds the index of the smallest value in a portion of an array.
    @param a      an array of integers
    @param first  an index >= 0 and < a.length that is the index of 
                  the first array entry to consider
    @param last   an integer >= first and < a.length that is the index 
                  of the last array entry to consider
    @return the index of the smallest value among a[first], a[first + 1], . . . , a[last] */
	private static int getIndexOfSmallest(int[] a, int first, int last) {
		int min = a[first];
		int indexOfMin = first;
		for (int index = first + 1; index <= last; index++) {
			comparisons++;
			if (a[index]<min) {
				min = a[index];
				indexOfMin = index;
			} // end if
			// Assertion: min is the smallest of a[first] through a[index].
		} // end for

		return indexOfMin;
	} // end getIndexOfSmallest

	 public static void insertionSort(int [] a) {
		 int n = a.length;
		 comparisons = 0;
		 insertionSort(a, 0, n - 1);
	 } // end insertionSort

	 public static void insertionSort(int[] a, int first, int last) {
		 int unsortedIndex;  // each element of a becomes the first of unsorted in turn
	
		 for (unsortedIndex = first + 1; unsortedIndex <= last; unsortedIndex++) {   
			 // Assertion: a[first] <= a[first + 1] <= ... <= a[unsorted - 1]
	
			 int unsorted = a[unsortedIndex];
		
			 insertInOrder(unsorted, a, first, unsortedIndex - 1);
		 } // end for
	 } // end insertionSort

	 private static void insertInOrder(int element, int[] a, int begin, int end) {
		 int index;	
		 // searching for the correct placve for element in the sorted part
		 //shifting values to the right
		 for (index = end; (index >= begin) && (element < a[index]); index--)	{
			 comparisons ++;
			 a[index + 1] = a[index]; // make room
		 } // end for	
		 
		 a[index + 1] = element;  // insert
	 } // end insertInOrder
	 
	/** Swaps the array entries a[i] and a[j].
    @param a  an array of integers
    @param i  an integer >= 0 and < a.length
    @param j  an integer >= 0 and < a.length */
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
	} // end swap

	public static void shellSort(int[] a) {
		int n = a.length;
		int first = 0;
		int last = n-1;
		
		comparisons = 0;
		
		int gap = n / 2;	// initial gap is n/2
		if (gap % 2 == 0)
			gap ++;
	
		// Continue until the gap is zero
		while (gap > 0) {
			System.out.println("Current gap = " + gap);
			// for each position inside the gap
			//do insertion sort on the corresponding subarray 
			for (int begin = first; begin < first + gap; begin++){
				insertionSortSubarray(a, begin, last, gap);
			}

		    gap = gap / 2;	// reduce gap
		    if (gap > 0 && gap % 2 == 0)
		    	gap ++;
		} // end for
	} // end shellSort
	
	private static void insertionSortSubarray (int [] a, int start, int end, int gap) {
		int unsortedIndex, index;
		// go through all elements in the subarray starting from the second
		for (unsortedIndex = start + gap; unsortedIndex <= end; 
				unsortedIndex=unsortedIndex+gap){
			int unsorted = a[unsortedIndex];
			index = unsortedIndex - gap;
			while ((index >= start) && 
					unsorted < a[index]){
				comparisons ++;
				a[index + gap] = a[index]; 
				index = index - gap;
			} // end while

			a[index + gap] = unsorted; 
		} // end for
	}
	
	public static void main (String [] args) {
		int n = 10;
		int [] a = DataGenerator.getRandIntArray(n);
		System.out.println("Input array of size  " + n + ": "+ DataGenerator.arrToString(a));
		
		selectionSort(a);
		System.out.println("Sorted with selection sort with total " + comparisons +" comparisons.");
		System.out.println("Output array: " + DataGenerator.arrToString(a));
		
		a = DataGenerator.getRandIntArray(n);
		System.out.println("\nInput array of size  " + n + ": "+ DataGenerator.arrToString(a));
		insertionSort(a);
		System.out.println("Sorted with insertion sort with total " + comparisons +" comparisons.");
		System.out.println("Output array: " + DataGenerator.arrToString(a));
		
		a = DataGenerator.getRandIntArray(n);
		System.out.println("\nInput array of size  " + n + ": "+ DataGenerator.arrToString(a));
		shellSort(a);
		System.out.println("Sorted with shell sort with total " + comparisons +" comparisons.");
		System.out.println("Output array: " + DataGenerator.arrToString(a));
		
	}

}
