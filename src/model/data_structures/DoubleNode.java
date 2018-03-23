package model.data_structures;

public class DoubleNode<E extends Comparable<E>> extends SimpleNode<E>{

	
	private DoubleNode<E> previous;
	
	public DoubleNode(E element) {
		super(element);
		// TODO Auto-generated constructor stub
		this.previous=null;
	}
	
	public DoubleNode<E> getPrevious(){
		return this.previous;
	}
	public void modifyPrevious(DoubleNode<E> pPrevious){
		this.previous= pPrevious;
	}


	
}
