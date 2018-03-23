package model.data_structures;

public class LinkedDoubleList<T extends Comparable<T>> extends LinkedSimpleList<T>{

	
	private DoubleNode<T> first;
	
	private DoubleNode<T> actual;
	
	private int size;
	
	
	public LinkedDoubleList() {
		// TODO Auto-generated constructor stub
		first= null;
		actual=null;
		size=0;
	}
	@Override
	public void add(T element) {
		// TODO Auto-generated method stub
		if(element==null){
			throw new NullPointerException();
		}
		boolean respuesta=false;
		if(first!=null){
			actual=first;
			while(actual!=null&&respuesta==false){
				
				if(actual.getNext()==null){
					
					DoubleNode<T> addNode= new DoubleNode<T>(element);
					actual.modifyNext(addNode);
					addNode.modifyPrevious(actual);
					respuesta=true;
					size++;
				}
				this.next();
				
			}
		}
		else{
			first= new DoubleNode<T>(element);
			size++;
			
		}
	}

	@Override
	public boolean delete(T element) {
		// TODO Auto-generated method stub
		boolean respuesta=false;
		
		DoubleNode<T> ant=null;
		DoubleNode<T> siguiente=null;
		if(first!=null&&first.getElement()==element){
			first= (DoubleNode<T>) first.getNext();
			size--;
			respuesta=true;
		}
		else{
			while(actual!=null&&!respuesta){
				if(actual.getElement()==element){
					ant=actual.getPrevious();
					siguiente=(DoubleNode<T>) actual.getNext();
					ant.modifyNext(siguiente);;
					siguiente.modifyPrevious(ant);;
					size--;
					respuesta=true;
				}
				this.next();
			}
		}
		return respuesta;
	}

	@Override
	public T get(T element) {
		// TODO Auto-generated method stub
		if(first!=null){
			
			actual=first;
			while(actual!=null){
				if(actual.getElement()!=null&&actual.getElement().compareTo(element)==0){
					return actual.getElement();
				}
				next();
			}
		}
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public T get(int position) {
		// TODO Auto-generated method stub
		int count=0;
		if(first!=null){
			
			actual=first;
			while(actual!=null){
				if(count==position){
					return actual.getElement();
				}
				else{
					count++;
					next();
				}
			}
		}
		
		return null;
	}

	@Override
	public T[] listing(T[] list) {
		// TODO Auto-generated method stub
		int count=0;
		actual=first;
		
		while(actual!=null){
			list[count]=this.actual.getElement();
			count++;
			this.next();
			System.out.println(count);
		}
			return list;
	}

	@Override
	public T getCurrent() {
		// TODO Auto-generated method stub
		if(actual!=null){
			return this.actual.getElement();}
			else{
				return null;
			}
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		actual= (DoubleNode<T>) actual.getNext();
		
		if(actual!=null){
			return actual.getElement();
		}
		else
			return null;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(actual==null){
			
			return false;
		}
		else{
			boolean rta=false;
			if(actual.getNext()!=null){
				rta=false;
			}
			return rta;
		}
	}

	
}
