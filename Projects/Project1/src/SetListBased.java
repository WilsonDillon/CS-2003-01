import java.util.Iterator;
/*
   Implementation of the Set ADT that uses a ListReferenceBased private variable to store data
*/ 
public class SetListBased<E> implements SetInterface<E>{
    
    // A reference-based list that is used in various methods throughout
	private ListReferenceBased<E> L;

/*  Provide code for the following methods:
-------------------------------------------------------------------------------
Constructors: a default constructor, a constructor that takes a
single argument of type E and initializing the set to contain
that object, and a copy constructor.

- size: a method that returns the number of elements in the
set as an int value,
- display: a method that prints out the contents of the set,
- toString: a method that returns a string representation of the set,
- add: a method that takes an element of type E and inserts
it into the set,
- addAll: a method that takes an array of elements of type
E and inserts them into the set,
- union: a method that takes a Set as an argument and returns a
new Set that is the union of the current set and the argument (provided below), 
- intersection: a method that takes a Set as an argument and
returns a new Set that is the intersection of the current set and the
argument,
- difference: a method that takes a Set as an argument and
returns a new Set that is the difference of the current set and the
argument,
- contains: a method that takes an element of type E and returns a
boolean value depending on whether the given E is contained in
the current set or not. 

-------------------------------------------------------------------------------
*/

// Default constructor
public SetListBased() {
    L = new ListReferenceBased<E>();
} // end default constructor

// Constructor that creates a set containing the specified element
// @param item of type E that will be added to an empty set
public SetListBased(E item) {
    L = new ListReferenceBased<E>();
    L.append(item);
} // end constructor

// Copy constructor that creates a set that is a copy of the specified set
// @param lst a set that is copied into the new set N that is being constructed
public SetListBased(SetListBased<E> lst) {
    L = new ListReferenceBased<E>();
	for (E item : lst) {
        add(item);
    }
} // end copy constructor

// Returns the size of the list
// @return int that is the size of the list
public int size() {
    return L.size();
} // end size

// Prints the contents of the list
public void display() {
    System.out.println(toString());
} // end display

// Returns the contents of the list in a String
// @return a String that is the contents of the entire list
public String toString() {
    return L.toString();
} // end toString

// Adds an item of type E at the end of the list
// @param item that is the item of type E that is to be 
// added to the list
public void add(E item) {
	if (!contains(item))
    L.append(item);
} // end add

// Adds an array with items of type E into the list
// @param that is an array of type E that is to be added into 
//the list
public void addAll(E [] array) {
    for ( E item : array ) {
    	if (!contains(item))
        L.append(item);
    }
} // end addAll

// Returns a set that is the union of the current set
// and the set that is passed as the argument
// @param setList a set of type E that will be added to the current set
// @return a set that is the union of the param setList and the 
// current set
public SetInterface<E> union(SetInterface<E> setList) {
    SetListBased<E> N = new SetListBased<E>();
    for (E item : setList) {
        N.add(item);
    }
    for (E item : L) {
    	N.add(item);
    }
    return N;
} // end union

// Returns a set that is the intersection of the current set
// and the set that is passed as the argument
// @param setList a set of type E that will be intersected with the 
// current set
// @return a set that is the intersection of the param setList and the 
// current set
public SetInterface<E> intersection(SetInterface<E> setList) {
    SetListBased<E> N = new SetListBased<E>();
    for (E item : setList) {
        if (setList.contains(item) && contains(item)) {
            N.add(item);
        }
    }
    return N;
} // end intersection

// Returns a set that is the difference of the current set
// and the set that is passed as the argument
// @param setList a set of type E that will be subtracted (as a set operation) 
// from the current set
// @return a set that is the difference of the param setList and the 
// current set
public SetInterface<E> difference(SetInterface<E> setList) {
    SetListBased<E> N = new SetListBased<E>();
    for (E item : L) {
        if (!setList.contains(item)) {
            N.add(item);
        }
    }
    return N;
} // end difference

// Takes an argument of type E and returns a boolean value
// based on whether or not that item is contained within the set
// @param item of type E that will be scanned for in the set
// @return boolean that represents whether or not item is contained 
// in the set
public boolean contains( E item ) {
    return L.indexOf(item) != -1;
} // end contains

/**
 * This method implements the iterator required by the Iterable interface,
 * with SetInterface inherits from.  It enables the list to be iterable, 
 * which allows the use of enhanced for loops.
 *
 * @param       none
 * @return      an iterator
 */
	public Iterator<E> iterator(){
		return (L.iterator());
	}

}