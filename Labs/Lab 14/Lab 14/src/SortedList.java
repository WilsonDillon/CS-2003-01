import java.util.ArrayList;
public class SortedList<E extends Comparable <? super E>>
    implements SortedListInterface<E>{

    private ArrayList<E> list;
	
    public SortedList () {
        list = new ArrayList<E>();
    }

    public SortedList (E item) {
        list = new ArrayList<E>();
        list.add(item);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void removeAll() {
        list.clear();
    }

    public E get(int index) throws ListIndexOutOfBoundsException {
        return list.get(index);
    }

    public void sortedAdd(E newItem) throws ListIndexOutOfBoundsException, ListException {
        list.add(locateIndex(newItem), newItem);
    }

    public void sortedRemove(E anItem) {
        list.remove(anItem);
    }

    public int locateIndex(E anItem) {
        int low = 0;
        int high = list.size()-1;
        if (list.isEmpty())
        while (low <= high) {
            int mid = low + (high-low) / 2;
            if (list.get(mid).compareTo(anItem) == 0) {
                return mid;
            }
            else if (list.get(mid).compareTo(anItem) > 0){
                high = mid-1;
            }
            else low = mid + 1;
        }
        return low;
    }

    public void display() {
        for (E item : list) {
            System.out.print(item);
        }
    }

}  // end class