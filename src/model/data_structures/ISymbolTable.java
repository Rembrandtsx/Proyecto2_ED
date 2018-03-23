package model.data_structures;

import java.util.Iterator;

/**
 * Abstract Data Type for a Symbol Table of generic objects
 */
public interface ISymbolTable <Key extends Comparable<Key>,Value extends Comparable<Value>>  {
	
	
	public void put(Key pkey, Value value);
	
	public Value get(Key pKey);
	
	public void delete(Key pKey);
	
	public boolean contains(Key pKey);
	
	public boolean isEmpty();
	
	public int size();
	
	public Iterator<Key> Keys();
	
	

	
	
	
	
	

}
