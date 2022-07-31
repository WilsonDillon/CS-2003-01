package BinaryTrees;
import java.util.ArrayList;

public class Heap<E extends Comparable<? super E>> {

	/**  ArrayList of heap items */
	private ArrayList<E> items;

	/**
	 * default constructor
	 */
	public Heap() {
		items = new ArrayList<E>();
	} // end default constructor

	// Heap operations:
	/**
	 * Determines the size of the heap
	 * @returns Returns an int value
	 */
	public int size() {
		return items.size();
	}
	/**
	 * Determines whether a heap is empty.
	 * @returns Returns true if the heap is empty; otherwise returns false.
	 */
	public boolean heapIsEmpty() {
		return size() == 0;
	} // end heapIsEmpty

	/**
	 * Inserts an item into a heap. If the heap was not full, newItem is in its
	 * proper position; otherwise HeapException is thrown.
	 * @param newItem
	 *        is the item to be inserted.
	 */
	public void heapInsert(E newItem) {	
		items.add(newItem); // place the new item at the end of the heap
		// trickle new item up to its proper position
		int place = size()-1;
		int parent = (place - 1) / 2;
		while ((parent >= 0)
				&& (items.get(place).compareTo(items.get(parent)) < 0)) {
			// swap items[place] and items[parent]
			E temp = items.get(parent);
			items.set(parent, items.get(place));
			items.set(place, temp);

			place = parent;
			parent = (place - 1) / 2;
		} // end while
	} // end heapInsert

	/**
	 * Retrieves and deletes the item in the root of a heap. This item has the
	 * largest search key in the heap. If the heap is not empty, returns the
	 * item in the root of the heap and then deletes it. However, if the heap is
	 * empty, removal is impossible and the method returns null.
	 *
	 * @throws HeapException
	 *             when trying to delete from empty heap.
	 * @return the largest item
	 */
	public E heapDelete() throws HeapException {
		if (heapIsEmpty()) {
			throw new HeapException("HeapException: Trying to delete from an empty heap!");
		}
		E rootItem = items.remove(0);
		if(size() > 1) {// if any item left in the heap
			items.add(0, items.remove(size() - 1)); // put the last thing on top
			heapRebuild(0);
		}
		return rootItem;
	} // end heapDelete

	/**
	 * if the root is not a leaf and the root's search key is larger than the
	 * smaller of the search keys in the root's children
	 * 
	 * @param root
	 */
	protected void heapRebuild(int root) {
		int child = 2 * root + 1; // index of root's left 
		// child, if any
		if (child < size()) {
			// root is not a leaf, so it has a left child at child
			int rightChild = child + 1; // index of right child, 
			// if any

			// if root has a right child, find smaller child ; SS: corrected comment 11/29/04
			if ((rightChild < size())
					&& (items.get(rightChild).compareTo(items.get(child)) < 0)) {
				child = rightChild; // index of larger child
			} // end if

			// if the root's value is smaller than the
			// value in the larger child, swap values
			if (items.get(root).compareTo(items.get(child)) > 0) {
				E temp = items.get(root);
				items.set(root, items.get(child));
				items.set(child, temp);
				// transform the new subtree into a heap
				heapRebuild(child);
			} // end if
		} // end if
		// if root is a leaf, do nothing
	} // end heapRebuild

} // end Heap
