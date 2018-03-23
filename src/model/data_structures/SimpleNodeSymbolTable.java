package model.data_structures;


public class SimpleNodeSymbolTable <K extends Comparable<K>, T extends Comparable<T>> implements Comparable<SimpleNodeSymbolTable <K, T >>{

	private T element;
	
	private K key;
	
	private SimpleNodeSymbolTable<K,T> next;
	
	
	
	public SimpleNodeSymbolTable(K pKey,T pElement) {
		// TODO Auto-generated constructor stub
		this.element=pElement;
		this.key= pKey;
		this.next=null;
		
	}
	
	public void modifyElement(T pElement){
		
		this.element=pElement;
	}
	
	public void modifyNext(SimpleNodeSymbolTable<K,T> pNext){
		
		this.next=pNext;
	}
	
	public T getElement(){
		return this.element;
	}
	
	public K getKey(){
		return this.key;
	}
	
	public SimpleNodeSymbolTable<K,T> getNext(){
		return this.next;
	}

	@Override
	public int compareTo(SimpleNodeSymbolTable<K, T> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
