// Name: Adam Tang
// Edited by Abraham Habib 2/12/21
// For use in CodePost

import java.util.*;
import java.io.IOException;
import java.io.File;

public class Lab5 {
    /**
     * Partitions an array for quickSort.
     *
     * @param first    is the index of the first element to sort with
     *                 <code>first <= last</code>.
     * @param last     is the index of the last element to sort with
     *                 <code>first <= last</code>.
     * @param theArray is the array to be sorted: the element between
     *                 <code>first</code> and <code>last</code> (with
     *                 <code>first <= last</code>)will be sorted.
     * @return the index of the pivot element of theArray[first..last]. Upon
     *         completion of the method, this will be the index value lastS1 such
     *         that <code>S1 =
     * theArray[first..lastS1-1] < pivot theArray[lastS1] == pivot S2 =
     * theArray[lastS1+1..last] >= pivot </code>
     */
    private static <E extends Comparable<? super E>> int partition(E[] theArray, int first, int last) {
        // tempItem is used to swap elements in the array
        E tempItem;
        E pivot = theArray[first]; // reference pivot
        // initially, everything but pivot is in unknown
        int lastS1 = first; // index of last item in S1
        // move one item at a time until unknown region is empty
        for (int firstUnknown = first + 1; firstUnknown <= last; ++firstUnknown) {
            // Invariant: theArray[first+1..lastS1] < pivot
            // theArray[lastS1+1..firstUnknown-1] >= pivot
            // move item from unknown to proper region
            if (theArray[firstUnknown].compareTo(pivot) < 0) {
                // item from unknown belongs in S1
                ++lastS1;
                tempItem = theArray[firstUnknown];
                theArray[firstUnknown] = theArray[lastS1];
                theArray[lastS1] = tempItem;
            } // end if
            // else item from unknown belongs in S2
        } // end for
        // place pivot in proper position and mark its location
        tempItem = theArray[first];
        theArray[first] = theArray[lastS1];
        theArray[lastS1] = tempItem;
        return lastS1;
    } // end partition

    public static <E extends Comparable<? super E>> E kSmall(int k, E[] array, int first, int last) {
        /* TODO: Complete */
        int pI = partition(array, first, last);
        if (k == (pI-first+1)) {
            return array[pI];
        }
        else {
            if (k < (pI-first+1)) {
                return kSmall(k, array, first, pI-1);
            }
            else 
            return kSmall(k-(pI-first+1), array, pI+1, last);
        }
    }

    public static void main(String[] args){
        try{
            Scanner console = new Scanner(System.in);
            String filename = null;
            boolean argsb = false;
            int argsl = args.length;

            if (args.length > 2) {
                filename = args[0];
                argsb = true;
            } else {
                System.out.println("Key in the file name: ");
                filename = console.next();
            }

            ArrayList<Integer> vec = new ArrayList<Integer>();

            Scanner scanData = new Scanner(new File(filename));
            while(scanData.hasNext()) {
                vec.add(scanData.nextInt());
            }
            scanData.close();

            Integer[] myArray = new Integer[vec.size()];
            int count=0;
            System.out.println("The integers in the file "+filename +" are: ");

            for (Integer val:vec){
                myArray[count]=val;
                System.out.print(val+" ");
                count++;
            }

            /* TO COMPLETE */
            // code to ask user for an index, k, between 1 and the number of integers read in, N
            // if number entered is outside the [1,N] range, exit loop
            // otherwise call ksmall to find the kth smallest element, write out the value with
            // an appropriate message, and loop for the next input
            System.out.println("Please enter a valid index for k.");
            int input;
            while (console.hasNext()) {    
                input = console.nextInt();
                if (input >=1 && input <= argsl) {
                    System.out.println("The " + input + " smallest element is "+ kSmall(input, myArray, 1, myArray.length-1));
                }
                else System.out.println("Index out of bounds");
            }

            console.close();
        }
        catch(IOException e){
            System.err.println("IOError!!!\n" + e);
            System.exit(9);
        }

    }
}// end class