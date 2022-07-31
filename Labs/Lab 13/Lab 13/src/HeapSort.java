import java.io.*;
import java.util.ArrayList;


public class HeapSort {

	
	/** Sort the input ArrayList using in place Heap Sort
	 * @param <E> 	The generic type. Must extend comparable
	 * @param seq 	The Arraylist<E> to be sorted
	 * @param n 	The size of the ArrayList "seq"
	 */
	public static  <E extends Comparable<? super E>> void heapSort(ArrayList<E> seq, int n){
	    //  --  TO COMPLETE  --
		/*
		 * NOTE: You should not need to use the Heap class provided in the BinaryTrees package
		 * 
		 * Drecrement from n/2 to >=0 using a for loop, calling heapRebuild passing seq, i, and n
		 * 
		 * Instantiate an integer "last" as n-1
		 * 
		 * With a while loop, loop until last > 0
		 * 		swap index 0 with index last in the arraylist
		 * 		decrement last
		 * 		call heapRebuild passing seq, 0, and last
		 */
		for (int i = ((n)/2); i >= 0; i--) {
			heapRebuild(seq, i, n);
		}
		int last = n-1;
		while (last > 0) {
			E temp = seq.get(last);
			seq.set(last, seq.get(0));
			seq.set(0, temp);
			last--;
			heapRebuild(seq, 0, last+1);
		}
	}
	/**
	 * Takes an arraylist and heapify's it
	 * 
	 * @param <E> 	The generic type
	 * @param seq	The arraylist to heapify
	 * @param root	The root index
	 * @param last	The last index
	 */
	protected static <E extends Comparable<? super E>> void heapRebuild(ArrayList<E> seq, int root, int last) {
		int child = 2 * root + 1; // index of root's left 
		// child, if any
		if (child < last) {
			// root is not a leaf, so it has a left child at child
			int rightChild = child + 1; // index of right child, 
			// if any

			// if root has a right child, find smaller child ; SS: corrected comment 11/29/04
			if ((rightChild < last)
					&& (seq.get(rightChild).compareTo(seq.get(child)) < 0)) {
				child = rightChild; // index of larger child
			} // end if

			// if the root's value is smaller than the
			// value in the larger child, swap values
			if (seq.get(root).compareTo(seq.get(child)) > 0) {
				E temp = seq.get(root);
				seq.set(root, seq.get(child));
				seq.set(child, temp);
				// transform the new subtree into a heap
				heapRebuild(seq, child, last);
			} // end if
		} // end if
		// if root is a leaf, do nothing
	} // end heapRebuild
	
	public static void main(String[] args) {
		ArrayList<StudentGPA> sequence = new ArrayList<StudentGPA>();
		try {
			BufferedReader studentData = new BufferedReader(new FileReader("students.in"));
			String line = studentData.readLine();
			while (line != null) {
				String[] data = line.split(" ");
				if (data.length > 3)
					sequence.add(new GraduateStudentGPA(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]),
							data[3]));
				else
					sequence.add(new StudentGPA(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2])));
				line = studentData.readLine();
			}
			studentData.close();
		} catch (IOException e) {
			System.err.print(e);
		}
		heapSort(sequence, sequence.size());
		for (StudentGPA student : sequence) {
			System.out.println(student.toString());
		}

	} // end main

}