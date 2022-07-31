import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;
//import java.util.Stack;
/* Lab designed to study and compare runtimes of iterative and recursive
 * versions of QuickSort algorithm
 */

public class QuickSortComparison{
	static int stack[];
	static int top;
	/**
	    * Partitions an array for quickSort.
	    * @param first is the index of the first element to sort with
	    * <code>first <= last</code>.
	    * @param last is the index of the last element to sort with
	    * <code>first <= last</code>.
	    * @param theArray is the array to be sorted: the element
	    * between <code>first</code> and <code>last</code> (with
	    * <code>first <= last</code>)will be sorted.
	    * @return the index of the pivot element of
	    * theArray[first..last]. Upon completion of the method, this will
	    * be the index value lastS1 such that <code>S1 =
	    * theArray[first..lastS1-1] < pivot theArray[lastS1] == pivot S2 =
	    * theArray[lastS1+1..last] >= pivot </code>
	    */
	private static <E extends Comparable<? super E>> int partition(E[] arr, int first, int last) {
    // randomly select a pivot index
    int pI = first + new Random().nextInt(last - first + 1);
    //swap the pivot with the first element in the subarray from first to last
    E tmp = arr[pI];
    arr[pI] = arr[first];
    arr[first] = tmp;
    //setting the pivot index to first, the new pivot index
    pI = first;
    //setting the index of the last element in s1 to the pivot index
    int s1 = pI;
    //  moving one item at a time until unknown region s2 is empty
    // iterating from s2 = first + 1 up to and including last in increments of 1
    for (int s2 = first + 1; s2 <= last; s2++) {
      if (arr[s2].compareTo(arr[pI]) >= 0) {
        continue;
      }
      //incrementing s1 and swapping the values at s1 and s2
      s1++;
      tmp = arr[s1];
      arr[s1] = arr[s2];
      arr[s2] = tmp;
    }
    tmp = arr[pI];
    arr[pI] = arr[s1];
    arr[s1] = tmp;
    pI = s1;
    return pI;
    }  // end partition
	
	/**
	 * Calls recursive or iterative quicksort functions based on flag variable isIterative
	 * @param array to be sorted
	 * @param low first index of array 
	 * @param high last index of array
	 * @param isIterative flag variable 
	 */
	public static <E extends Comparable<? super E>> void quickSort(E[] array, int low, int high, boolean isIterative) {
		if (isIterative) 
			quickSortIterative(array, low, high) ;
		else
			quickSortRecursive(array, low, high) ;
	}
	
	/**
	 * Performs quicksort with recursion
	 * @param array to be sorted
	 * @param low first index of array slice being sorted
	 * @param high last index of array slilce being sorted
	 */
	private  static <E extends Comparable<? super E>> void quickSortRecursive(E[] array, int low, int high) { 
        		if (low < high) { 
          		int pi = partition(array, low, high); 
            	  	quickSortRecursive(array, low, pi-1); 
            	  	quickSortRecursive(array, pi+1, high); 
        		} 
    	} 
	
	 /**
	  * pushes item into stack 
	 * @param item to be pushed
	 */
	private static void push( int item) {
		 stack[++top] = item;
	 }
	 /**
	  * pops the last item in the stack 
	 * @return last item in stack
	 */
	private static int pop() {
		 return stack[top--];
	 }
	  
	/**
	 * Performs quickSort using iteration with the help of a stack
	 * @param array to be sorted
	 * @param low first index of array slice being sorted
	 * @param high last index of array slilce being sorted
	 */
	private static <E extends Comparable<? super E>> void quickSortIterative(E[] array, int low, int high) 
	{ 
		
        // TO COMPLETE:
		// Write code to implement quicksort iteratively using following algorithm
		// using the variables: Stack and top and 
		// helper functions: push() and pop();  provided in the class
        // Initialize Stack to size high-low+1, top to -1
        // Push the range <low,high> onto the Stack (first push low, and then high)
        // do{
        // Pop the top pair <low,high> off the Stack (first pop high, then low)
        // Partition the array in the range <low, high>; obtain pI
        // Push the ranges <low,pI-1> and <pI+1,high> onto the stack
        // (push only if a range has more than one element)
        // } while (Stack is not empty)
        
        
        stack = new int[high-low+1];
        top = -1;
        push(low);
        push(high);

        do {
            int last = pop();
            int first = pop();
            int pi = partition(array, first, last);
            if (first < (pi-1)) {
                push(first);
                push(pi-1);
            }
            if ((pi+1 < last)) {
                push(pi+1);
                push(last);
            }
        } while (top != -1);


	}
	
	public static void main(String args[]) {
		int arraySizes[] = {100,1000,10000,100000,1000000};
		int nIterations = 10;
		long avgRunTimesIter[] = new long[arraySizes.length];
		long avgRunTimesRec[] = new long[arraySizes.length];
		Random randomNumberGenerator = new Random(101);
		for(int i =0; i <arraySizes.length; i++) {
			int iSize=arraySizes[i];
			Integer arrayOrig[] []= new Integer[100][iSize];
			for(int k = 0; k < nIterations; k++) {
				for(int idx = 0; idx < iSize; idx++) {
					arrayOrig[k][idx] = randomNumberGenerator.nextInt(100);
				}
				Integer arrayCopy1[] = new Integer[iSize];
				Integer arrayCopy2[] = new Integer[iSize];
				for(int idx = 0; idx < iSize; idx++) {
					arrayCopy1[idx] = arrayOrig[k][idx];
					arrayCopy2[idx] = arrayOrig[k][idx];
					
				}
				// Calculate Iterative quick sort run time
				long startTime1 = System.nanoTime();
				quickSort(arrayCopy1, 0, arrayCopy1.length-1, true);
				avgRunTimesIter[i]+=System.nanoTime() - startTime1;
				// Calculate Recursive quick sort run time
				long startTime2 = System.nanoTime();
				quickSort(arrayCopy2, 0, arrayCopy2.length-1, false);
				avgRunTimesRec[i]+=System.nanoTime() - startTime2;
			}
			System.out.println("Average Runtime for Array size: "+iSize+":");
			System.out.println("Iterative   : "+avgRunTimesIter[i]/nIterations);
			System.out.println("Recursive : "+avgRunTimesRec[i]/nIterations);
		}
	}
}