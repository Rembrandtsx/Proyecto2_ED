package model.data_structures;

/**
 * Abstract Data Type for a linked list of generic objects
 * This ADT should contain the basic operations to manage a list
 * add: add a new element T 
 * delete: delete the given element T 
 * get: get the given element T (null if it doesn't exist in the list)
 * size: return the the number of elements
 * get: get an element T by position (the first position has the value 0) 
 * listing: set the listing of elements at the firt element
 * getCurrent: return the current element T in the listing (return null if it doesn�t exists)
 * next: advance to next element in the listing (return if it exists)
 * @param <T>
 */
public interface ILinkedList <T extends Comparable<T>>  {
	
	
	public void add(T element);
	
	public boolean delete(T element);
	
	public T get(T element);
	
	public int size();
	
	public T get(int position);
	
	public T[] listing(T[] list);
	
	public T getCurrent();
	
	public T next();
	
	public boolean hasNext();

	
	
	

}
