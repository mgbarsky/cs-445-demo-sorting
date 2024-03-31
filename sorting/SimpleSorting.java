/**
   Class to demo of simple sorting algorithms:
   selection sort, insertion sort, bubble sort.
*/
package sorting;

public class SimpleSorting {
	public static int comparisons = 0;
	
	/** Sorts the first n objects in an array into ascending order.
    @param a  an array of integers (for simplicity of the demo)
    Can be easily replaced with comparable generics types
    */
	public static void bubbleSort(int[] A, int n) {
		comparisons = 0;
		for (int i=0; i<n-1; i++){
			boolean swapped = false; 
			for (int j=0; j<(n-1)-i; j++) {			 
				comparisons++;
				if (A[j] > A[j+1]) {
					swap (A, j, j+1); 
					swapped = true;
				}
			}
			if (!swapped) return; 
		}
	} 
	
	public static void selectionSort(int[] A, int n) {
		comparisons = 0;
		for (int i=0; i< n-1; i++) {
			int minIndex = i;
		
			// finds the smallest value in the portion A[i:n-1]
			for (int j=i+1; j<n; j++) {
				comparisons ++;
				if (A[minIndex] > A[j])
					minIndex = j;
			}
			swap (A, minIndex, i);
		}

	} 	

	public static void insertionSort(int [] A, int n) {
		 comparisons = 0;
		 for (int unsortedIndex = 1; unsortedIndex < n; unsortedIndex++) {
			 // take next unsorted number and insert it 
			 // into its proper position in the sorted part A[0:unsortedIndex-1]
			 int element = A[unsortedIndex];
			 int index = unsortedIndex-1;			 
			 
			 while (index >= 0) {
				comparisons++;
				if (element < A[index]) {			 
					A[index+1]=A[index];  // shift to the right 
					index--;
				}
				else {					
					break;
				}					 
			 }	
			 A[index+1] = element;  // insert
		 }
	 }
	 
	/** Swaps the array entries a[i] and a[j].
    @param a  an array of integers
    @param i  an integer >= 0 and < a.length
    @param j  an integer >= 0 and < a.length */
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
	} // end swap

	public static void shellSort(int[] A, int n) {	
		int end = n-1;
		
		comparisons = 0;
		
		int gap = n / 2;	// initial gap is n/2
		if (gap % 2 == 0)	// or the closest odd number
			gap ++;
	
		// Continue sorting sub arrays until the gap is zero
		while (gap > 0) {
			System.out.println("Current gap = " + gap);
			// for each position inside the gap
			//do insertion sort on the corresponding subarray 
			for (int start = 0; start < gap; start++){
				insertionSortSubarray(A, start, end, gap);
			}

		    gap = gap / 2;	// reduce gap
		    if (gap > 0 && gap % 2 == 0)
		    	gap ++;
		} 
	} 
	
	private static void insertionSortSubarray (int [] A, int start, int end, int gap) {
		int unsortedIndex, index;
		// go through all elements in the subarray starting from the second
		for (unsortedIndex = start + gap; unsortedIndex <= end; unsortedIndex+=gap){
			int element = A[unsortedIndex];
			index = unsortedIndex - gap; // one before element
			while ((index >= start)) {
				comparisons ++;			
				if (element < A[index]){
					A[index + gap] = A[index]; 
					index = index - gap;
				}
				else {					
					break;
				}
			} 
			A[index+gap] = element; 
		} 
	}
	
	public static void main (String [] args) {
		int n = 10;
		int [] a = DataGenerator.getRandIntArray(n);
		if (n<=20) System.out.println("Input array of size  " + n + ": "+ DataGenerator.arrToString(a));
		
		selectionSort(a, n);
		System.out.println("Sorted with SELECTION sort with total " + comparisons +" comparisons.");
		if (n<=20)System.out.println("Output array: " + DataGenerator.arrToString(a));
		
		a = DataGenerator.getRandIntArray(n);
		if (n<=20)System.out.println("\nInput array of size  " + n + ": "+ DataGenerator.arrToString(a));
		bubbleSort(a, n);
		System.out.println("Sorted with BUBBLE sort with total " + comparisons +" comparisons.");
		if (n<=20)System.out.println("Output array: " + DataGenerator.arrToString(a));
		
		a = DataGenerator.getRandIntArray(n);
		if (n<=20)System.out.println("\nInput array of size  " + n + ": "+ DataGenerator.arrToString(a));
		insertionSort(a, n);
		System.out.println("Sorted with INSERTION sort with total " + comparisons +" comparisons.");
		if (n<=20)System.out.println("Output array: " + DataGenerator.arrToString(a));
		
		a = DataGenerator.getRandIntArray(n);
		if (n<=20)System.out.println("\nInput array of size  " + n + ": "+ DataGenerator.arrToString(a));
		shellSort(a, n);
		System.out.println("Sorted with SHELL sort with total " + comparisons +" comparisons.");
		if (n<=20)System.out.println("Output array: " + DataGenerator.arrToString(a));		
	}

}
