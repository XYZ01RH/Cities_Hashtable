/**
 * 
 * @author rileyZ
 *
 * @param <K>
 * @param <V>
 */
public class Entry<K, V> {

	K key;
	V value;
	Entry<K, V> next = null;
	
	public Entry(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public V getValue() {
		return value;
	}
	
	public K getKey() {
		return key;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	public void setNext(Entry<K, V> next) {
		this.next = next;
	}
	
	public Entry<K, V> getNext() {
		return next;
	}

}
