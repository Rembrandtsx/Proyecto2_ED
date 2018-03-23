package model.data_structures;

public class Stack<T extends Comparable<T>> implements IStack<T>{

	
	private SimpleNode<T> end;
	
	private int size;
	
	
	public Stack() {
		// TODO Auto-generated constructor stub
		end = null;
	    size = 0;

	}
	
	public int getSize(){
		return size;
	}
	
	public T getEndElement(){
		
		if(end!=null){
			return end.getElement();
		}
		return null;
			
	}
	
	@Override
	public void push(T item) {
		// TODO Auto-generated method stub
		SimpleNode<T> node= new SimpleNode<T>(item);
	    
		if (end == null)
	      end = node;
	    
		else {
	      node.modifyNext(end);
	      end = node;
	    }
		
	    size++;

	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		if (end == null){
		      return null;
		}else{
		    T element = end.getElement();
		    end = end.getNext();
		    size--;
		    
		    return element;
		    
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
	

}
