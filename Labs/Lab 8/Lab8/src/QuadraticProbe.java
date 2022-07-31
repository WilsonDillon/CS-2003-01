
/**
 * Quadratic probing class
 */
public class QuadraticProbe<E> {
	// status contain 1 if an item is present, 0 if not
	private int[] status;
	// stores the elements of type KeyValuePair<E>
	private KeyValuePair<E>[] hTable;
	
	// constructor
	public QuadraticProbe(int lengthOfTable) {
		status = new int[lengthOfTable];
		hTable = new KeyValuePair[lengthOfTable];		
	}
	
	public int[] getStatus() {
		return status;
	}
	
	public KeyValuePair<E>[] getTable() {
		return hTable;
	}
	
	/** 
	 * hashing function
	 * @param str
	 * @return int: hashed value of string str
	 */
	public int hash(String str) {
		return Math.abs(str.hashCode())%status.length;
	}

	/**
	 * TO BE COMPLETED
	 * method to add an item of type KeyValuePair<E> 
	 * @param kv of type KeyValuePair<E>
	 * @return int: number of collisions due to addition of kv
	 */
	public int add(KeyValuePair<E> kv) {
        int tmp = hash(kv.getKey());
        int numCol = 0;
		int incr = 1;
		if (status[tmp] == 0) {
			hTable[tmp] = kv;
			status[tmp] = 1;
		}
		else {
			while (status[tmp] == 1) {
				tmp = (tmp + incr * incr) % status.length;
				numCol++;
				incr++;
			}
			hTable[tmp] = kv;
			status[tmp] = 1;
		}
        return numCol;
	}

	/**
	 * TO BE COMPLETED
	 * method to retrieve an item from the hashtable 
	 * given the key to look for
	 * @param String:  key
	 * @return   KeyValuePair<E>: item 
	 */
	public KeyValuePair<E> retrieve(String key){
        int tmp = hash(key);
        int incr = 1;
        KeyValuePair<E> tmpkv = hTable[tmp];
        while (status[tmp] == 1) {
            if (hTable[tmp].getKey() == key) {
                tmpkv = hTable[tmp];
            }
            tmp = (tmp + incr * incr) % status.length;
			incr++;
        }
        return tmpkv;
	}
}