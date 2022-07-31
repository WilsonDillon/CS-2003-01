
/**
 * Double hash class
 */
public class DoubleHash<E> {
	// status contain 1 if an item is present, 0 if not
	private int[] status;
	// stores the elements of type KeyValuePair<E>
	private KeyValuePair<E>[] hTable;
	
	// constructor
	public DoubleHash(int lengthOfTable) {
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
	public int hash1(String str) {
		return Math.abs(str.hashCode())%status.length;
	}
	
	/** 
	 * hashing function # 2
	 *  TO BE COMPLETED 
	 *  must return a hash value for string str 
	 *  representing step size for double hash 
	 * @param str
	 * @return int: hashed value of string str
	 */
	public int hash2(String str) {
		return (Math.abs(str.hashCode())+1)%status.length;
	}

	/**
	 * TO BE COMPLETED
	 * method to add an item of type KeyValuePair<E> 
	 * @param kv of type KeyValuePair<E>
	 * @return int: number of collisions due to addition of kv
	 */
	public int add(KeyValuePair<E> kv) {
		int tmp = hash1(kv.getKey());
        int numCol = 0;
		if (status[tmp] == 0) {
			hTable[tmp] = kv;
			status[tmp] = 1;
		}
		else {
			tmp = (tmp + hash2(kv.getKey()))%status.length;
			hTable[tmp] = kv;
			status[tmp] = 1;
			numCol++;
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
		int tmp = hash1(key);
        KeyValuePair<E> tmpkv = hTable[tmp];
        if (hTable[tmp].getKey() == key) {
            tmpkv = hTable[tmp];
        }
        else {
			tmp = (tmp + hash2(key))%status.length;
			tmpkv = hTable[tmp];
		}
        return tmpkv;
	}
}
