package model.data_structures;

/**
 * Abstract Data Type for a Heap Binary of generic objects
  */
public interface IHeapBinario <T extends Comparable<T>>  {
	
	
	public void swim(T element);
	
	public T sink();
	
	public int size();
	
	public boolean isEmpty();
	
	public T get(int position);
	
	

	
	
	
	
	

}
