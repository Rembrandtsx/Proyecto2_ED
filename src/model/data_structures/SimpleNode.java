package model.data_structures;


public class SimpleNode <T extends Comparable<T>>{

	private T element;
	
	private SimpleNode<T> next;
	
	
	
	public SimpleNode(T pElement) {
		// TODO Auto-generated constructor stub
		this.element=pElement;
		this.next=null;
		
	}
	public void modifyElement(T pElement){
		
		this.element=pElement;
	}
	public void modifyNext(SimpleNode<T> pNext){
		
		this.next=pNext;
	}
	
	public T getElement(){
		return this.element;
	}
	public SimpleNode<T> getNext(){
		return this.next;
	}

	
}
