/**
 * 
 * @author rileyZ
 *
 * @param <K>
 * @param <V>
 */


public class RH_HashMap<K, V> {

	private final int maxSize = 200;
	@SuppressWarnings("unchecked")
	Entry<K, V> entrys[] = new Entry[maxSize];

		
	public V getVal(K key) {
		int index = getHash(key);
		Entry<K, V> list = entrys[index];
		return getKeysVal(list, key);
	}
	
	private V getKeysVal(Entry<K, V> list, K key) {
		while(list != null) {
			if(list.getKey().equals(key)) {
				return list.getValue();
			}
			list = list.next;
		}
		return null;
	}

	private int getHash(K key) {
		int hash = Math.abs(key.hashCode());
		return hash % maxSize;
	}

	public void put(K key, V value) {
		int index = getHash(key);
		saveValue(index, key, value);
	}
	
	private void saveValue(int index, K key, V value) {
		Entry<K, V> list = entrys[index];
		
		if(list == null) {
			entrys[index] = new Entry<K, V> (key, value);
		} else {
			boolean done = false;
			
			while(list.next != null) {
				if(list.getKey().equals(key)) {
					list.setValue(value);
					done = true;
					break;
				}
				list = list.next;
			}
			
			if(!done) {
				list.next = new Entry<K, V>(key, value);
			}
		}	
	}

	public double getAvgSize() {
		int startIndex = 0;
		double avgSize = -1;
		int totalSize = 0;
		
		while(startIndex < maxSize) {
			Entry<K, V> list = entrys[startIndex];
			if(list != null) {
				do {
					totalSize++;
					list = list.getNext();
				} while( list != null);
			}
			startIndex++;
		}
		avgSize = (double) totalSize / maxSize;
		
		return avgSize;
	}
	
	public void remove(K key) {
		int index = getHash(key);
		Entry<K, V> list = entrys[index];
		if(list == null) {
			return;
		}
		
		if(list.getKey().equals(key)) {
			if(list.next == null) {
				entrys[index] = null;
				return;
			}
		}
		
		Entry<K, V> last = null;
		do {
			if(list.key.equals(key)) {
				if(last == null) {
					list = list.getNext();
				} else {
					last.next = list.getNext();
				}
				break;
			}
			list = list.next;
		} while(list != null);
		
		entrys[index] = list;
	}
}
