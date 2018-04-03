package model.data_structures;

import java.util.Iterator;

public class Queue<T extends Comparable<T>> implements IQueue<T>, Iterable<T>{

	
	private SimpleNode<T> end;
	
	private SimpleNode<T> first;
	
	
	private int size;
	
	
	
	
	public Queue() {
		
		first=null;
		end=null;
		
		size=0;
	}
	
	@Override
	public void enqueue(T item) {
		// TODO Auto-generated method stub
		SimpleNode<T> node= new SimpleNode<T>(item);
		
		if (first != null) {
			
			end.modifyNext(node);
		    end = node;
		} else {
	    	first = node;
	    	
		    end = node;
	    }
	    size++;

	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		if (first != null){
		    T element= first.getElement();
		    first = first.getNext();
		    
		    size--;
		    return element;
		}
		    else{

		    	return null;
		    	
		    }
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size==0){
			return true;
		}
		return false;
	}
	
	public int size(){
		
		return this.size;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return (Iterator<T>) this;
	}

	
	
	
	

}
