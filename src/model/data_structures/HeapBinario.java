package model.data_structures;

import java.util.Comparator;

import model.logic.utils.HeapSort;
import model.vo.Taxi;

public class HeapBinario<T extends Comparable<T>> implements IHeapBinario<T>,Comparable<HeapBinario<T>>{

	private Object[] temp;
	private int numElementos;
	private Comparator<T> comparador;
	private HeapSort<T> heapSort;
	private int capacidad;
	
	public HeapBinario(Comparator<T> pComparador) {
		// TODO Auto-generated constructor stub
		temp=  new Object[100];
		numElementos=0;
		comparador= pComparador;
		heapSort= new HeapSort<T>();
		capacidad=100;
	}
	@Override 
	public void swim(T element) {
		// TODO Auto-generated method stub
		
		if(element!=null){
		this.sobrePasoCarga();
			numElementos= (numElementos==0)?1:numElementos;
			
			temp[numElementos]= element;
			
	    int pos= numElementos-1;
		
	    numElementos++;
		this.heapSortInsertHeap((pos), comparador);
		
		}
	}
	
	@Override
	public T sink() {
		// TODO Auto-generated method stub
		int pos=0;
		if(temp[1]!=null){
			T rta;
				if(numElementos!=2){
				rta= (T)  temp[1];
				pos= numElementos-1;
				temp[1]=temp[pos];
				temp[pos]=null;
				numElementos= pos;
				this.heapSortDelHeap( temp, 1, comparador);	
				}
				else{
					rta= (T)  temp[1];
					temp[1]=null;
					
				}
				
			return rta;
		}
		return null;
	}
	
	

	
	
	
	@Override
	public int compareTo(HeapBinario<T> o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numElementos;
	}

	
	
	public void heapSortDelHeap(Object[] temp2, int pPadre, Comparator<T> pComparador)
    {
		
    	int posPadre=pPadre;
    	T padre=null;
    	if(posPadre<capacidad){
    	padre= (T) temp2[posPadre];
    	}
    	if(padre==null){
    		return;
    	}
    	
    	int posHijo1=(posPadre*2);
    	int posHijo2=((posPadre*2)+1);
    	
    	T hijo1=null;
    	if(posHijo1<capacidad){
    	hijo1= (T) temp2[posHijo1];
    	}
    	T hijo2=null;
    	if(posHijo2<capacidad){
    	hijo2= (T) temp2[posHijo2];
    	}
    	
    	int compPadreHijo1= 0;
    	int compPadreHijo2=0;
    	
    	
    	if(hijo1!=null){
    		compPadreHijo1= pComparador.compare(padre, hijo1);
		}
    	if(hijo2!=null){
    		compPadreHijo2= pComparador.compare(padre, hijo2);
    		
		}
    	
		if(compPadreHijo1<0&&compPadreHijo2>=0){
			temp2[posPadre]= hijo1;
			temp2[posHijo1]= padre;
			
		}
		else if(compPadreHijo2<0&&compPadreHijo1>=0){
			
			temp2[posPadre]=hijo2;
			temp2[posHijo2]= padre;
				        		
		}
		else if(compPadreHijo1<0&&compPadreHijo2<0){
			
			if(pComparador.compare(hijo1, hijo2)>=0){
				
				temp2[posPadre]= hijo1;
				temp2[posHijo1]=padre;
				}
			else{
				
				temp2[posPadre]=hijo2;
				temp2[posHijo2]=padre;
				}
				        		
		}
		
		if(posHijo1<capacidad||posHijo2<capacidad){
		this.heapSortDelHeap(temp2, posHijo1, pComparador);
		this.heapSortDelHeap(temp2, posHijo2, pComparador);
		
		}
	
       
    }

	    public void heapSortInsertHeap(int pHijo, Comparator<T> pComparador)
	    {
	    	int posHijo=pHijo;
	    	
	    	
	    	if(posHijo==1||posHijo==0){
	    		return;
	    	}
	    	
	    	int posPadre= (int) (posHijo/2);
	    	T padre= (T) temp[posPadre];
	    	T hijo= (T) temp[posHijo];
	    	
	    	
	    	
	    	int compPadreHijo=  pComparador.compare(padre, hijo);
	    	
	    	if(compPadreHijo<0){
				
				temp[posPadre]= hijo;
				temp[posHijo]= padre;
			}
			
				
			this.heapSortInsertHeap(posPadre, pComparador);
			
	       
	    }
	 
	    
	    public void sobrePasoCarga() {
			// TODO Auto-generated method stub
			double porcentaje= (numElementos*100)/capacidad;
			
			if(porcentaje>80){
				Object[] temp2= temp;
				capacidad= capacidad+3000;
				
				temp= new Object[capacidad];
				for(int i=1; i<=numElementos;i++){
					temp[i]= (T) temp2[i];
				}
				
			}
			
			
		}
		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			
			return (temp[1]==null);
		}
		@Override
		public T get(int position) {
			// TODO Auto-generated method stub
			return (T) temp[position];
		}
	
	
	


}
