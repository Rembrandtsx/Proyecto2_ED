package model.data_structures;

import java.util.Iterator;

public class LinkedSimpleList<T extends Comparable<T>> implements ILinkedList<T>,Comparable<LinkedSimpleList<T>>, Iterator<T>{

	private SimpleNode<T> first;
	private SimpleNode<T> actual;
	private SimpleNode<T> lastNode;
	private int size;
	private int p;
	
	public LinkedSimpleList() {
		// TODO Auto-generated constructor stub
		first=null;
		actual=null;
		lastNode=null;
		size=0;
		p=0;
	}
	@Override 
	public void add(T element) {
		// TODO Auto-generated method stub
		
		
		if(element==null){
			throw new NullPointerException();
		}
		
		if(first==null){
			first= new SimpleNode<T>(element);
			lastNode= first;
			size++;
			
		}
		else{
			lastNode.modifyNext(new SimpleNode<T>(element));
			lastNode= lastNode.getNext();
			size++;
			
		}
		
	}
	public void addFirst(T element) {
		// TODO Auto-generated method stub
		
		
		if(element==null){
			throw new NullPointerException();
		}
		
		if(first==null){
			first= new SimpleNode<T>(element);
			lastNode= first;
			size++;
			
		}
		else{
			SimpleNode<T> temp= first;
			first=new SimpleNode<T>(element);
			first.modifyNext(temp);
			size++;
		}
		
	}

	@Override 
	public boolean delete(T element) {
		// TODO Auto-generated method stub
		
		
		if(first!=null&&first.getElement()!=null&&first.getElement().compareTo(element)==0){
			
			if(first.getNext()!=null){
				SimpleNode<T> next=first.getNext();
				first=next;
			}
			else{
				first=null;
			}
			this.size--;
			
				return true;
		}
		else{
			
			actual=first;
			
			while(actual!=null){
				
					if(actual.getNext()!=null&&actual.getNext().getElement().compareTo(element)==0){
						
						SimpleNode<T> next= actual.getNext();
						actual.modifyNext(next);
						this.size--;
						return true;
					}
					
				else{
					this.avanzar();
				}
				
			}
			return false;
		}
		
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
				this.avanzar();
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
					this.avanzar();
				}
			}
		}
		
		return null;
	}
	public void addAList(LinkedSimpleList<T> list){
			
		if(list.size()==0) {
			
			return;
		
		}
		else {
			if(lastNode!=null){
			lastNode.modifyNext(list.getFirst());
			lastNode = list.getLastNode();
			size=size+list.size;
			}
			else{
				first= list.getFirst();
				lastNode = list.getLastNode();
				size=size+list.size;
				
			}
		}
		
		
		
	}
	public SimpleNode<T> getLastNode(){
		
		return this.lastNode;
	}
	

	@Override
	public T[] listing(T[] list) {
		// TODO Auto-generated method stub
		int count=0;
		actual=first;
		
		while(actual!=null){
			list[count]=this.actual.getElement();
			count++;
			this.avanzar();
			
		}
			return list;
			
	}
	
	public void listing() {
		// TODO Auto-generated method stub
		p=0;
		actual = first;
	}
	public SimpleNode<T> getCurrentNode() {
		// TODO Auto-generated method stub
		if(actual!=null){
		return actual;}
		else{
			return null;
		}
	}
	public SimpleNode<T> getFirst(){
		return first;
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
	
	public void avanzar()
	{
		actual = actual.getNext();
	}

	
	public void setFirst(SimpleNode<T> pfirst)
	{
		first= pfirst;
	}
	public void setSize(int pSize)
	{
		size=pSize;
	}
	
	public void setLast(SimpleNode<T> pLast)
	{
		lastNode= pLast;
	}
	
	public SimpleNode<T> removeFirst()
	{
		SimpleNode<T> oldFirst=first;
		first=first.getNext();
		oldFirst.modifyNext(null);
		return oldFirst;
	}
	public SimpleNode<T> getNode(int pos){
		int count=0;
		if(first!=null){
			
			actual=first;
			while(actual!=null){
				if(count==pos){
					return actual;
				}
				else{
					count++;
					this.avanzar();
				}
			}
		}
		
		return null;
		
	}
	public boolean modifyElement(int position, T element) {
		// TODO Auto-generated method stub
		int count=0;
		if(first!=null){
			
			actual=first;
			while(actual!=null){
				if(count==position){
					actual.modifyElement(element);
					return true;
				}
				else{
					count++;
					this.avanzar();
				}
			}
			
		}
		if(first==null&&position==0){
			first= new SimpleNode<T>(element);
			size++;
		}
		return false;
	}
	
	@Override
	public int compareTo(LinkedSimpleList<T> o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public T next() {
		// TODO Auto-generated method stub
		if(p==0){
			p++;
			return actual.getElement();
		}
		p++;
		actual= actual.getNext();
		
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
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return (Iterator<T>) this;
	}


}
