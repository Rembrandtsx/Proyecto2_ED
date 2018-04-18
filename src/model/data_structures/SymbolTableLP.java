package model.data_structures;

import java.util.Iterator;

public class SymbolTableLP<K extends Comparable<K>,  V > {

	
	
	public final static double  FACTORCARGALINEARPROBING= 0.5;
	
	
	private int size;
	
	private int capacity;
	
	private double overLoadFactor;
	
	private Comparable[] keys;
	
	private Object[] values;
	
	
	public int hash(K pKey){
		
		/**int start= 0;
		int end= capacity-1;
		boolean un=false;
		Integer key=hash(pKey);
		System.out.println("Temp13:" + key);
		
		while(start<=end){
			int mid=start + (end-start)/2;
			if(keys[mid]==null){
				System.out.println("Temp14:" + mid);
				
				if(start==end){
					if(un==true){
						break;
					}
					start=0;
					un=true;
				}
				start++;
				
			}
				else{
					
				int comparator= key.compareTo((Integer) keys[mid]);
				if(comparator==0){
					return mid;
				}
				else if(comparator<0){
					end=mid-1;
				}
				else{
					start=mid+1;
				}
			}
		}
		return start;
		**/
		Integer key=getHashCode(pKey);
		int pos=0;
		for(int i=0;i<capacity;i++){
			if(keys[i]!=null&&keys[i].compareTo(pKey)==0){
				return i;
			}
		}
		return pos;
	}
	
	public int getHashCode(K pkey){
		
		try{
			int temp= Integer.parseInt((String) pkey);
			return temp;
		}
		catch (Exception e) {
			// TODO: handle exception
			return pkey.hashCode();
		}
		
	}
	public void reHash(int pCapacity){
		int temp= this.capacity;
		this.capacity=pCapacity;
		
		Comparable[] tempKeys=  new Comparable[capacity];
		Object[] valuesKeys=  new Object[capacity];
		
		
		int hash;
		int pos;
		for(int i=0; i<temp;i++){
			K tempK= (K) keys[i];
			V tempV= (V) values[i];
			
			if(tempK!=null){
				hash= getHashCode(tempK);
				pos= hash%capacity;
					
					if(tempKeys[pos]==null){
						tempKeys[pos]= tempK;
						valuesKeys[pos]=tempV;
						
					}else{
						for(int j=pos;  j<capacity;j++){
							if(tempKeys[j]==null){
								tempKeys[j]= tempK;
								valuesKeys[j]=tempV;
								break;
							}
							if(j==capacity-1){
								j=0;
							}
						}
					}
			}
				
		}
		
		this.keys=tempKeys;
		this.values= valuesKeys;
	}
	
	public SymbolTableLP(int pCapacity) {
		
		keys=  new Comparable[pCapacity];
		values=  new Object[pCapacity];
		capacity= pCapacity;
		size=0;
		
	}
	
	public void put(K pkey, V value) {
		// TODO Auto-generated method stub
		if(pkey==null){
			return;
		}
		if(value==null){
			delete(pkey);
		}
		int ans= hash(pkey);
		
		if(isEmpty()==false&&keys[ans]!=null&&keys[ans].compareTo(pkey)==0){
			values[ans]= value;
		}
		else{
			
			if(((double) size/(double)capacity)>=FACTORCARGALINEARPROBING){
				reHash(obtieneNumPrimo(capacity));///////Formula que facilita obtener numeros primos.
				
			}
			
			ans= size%capacity;
			Integer tempHash= getHashCode(pkey);
			ans= tempHash%capacity;
			
			if(isEmpty()!=false&&keys[ans]==null){
				keys[ans]= pkey;
				values[ans]=value;
			}else{
				for(int i=ans;  i<capacity;i++){
					
					if(keys[i]==null){
						keys[i]= pkey;
						values[i]=value;
						
						break;
					}
					if(i==capacity-1){
						i=0;
						
						
					}
					
					
				}
			}
			
			
			
		}
		size++;
		
	
	}

	public V get(K pKey) {
		// TODO Auto-generated method stub
		int pos= hash(pKey);
		
		if(isEmpty()==false&&keys[pos]!=null&&keys[pos].compareTo(pKey)==0){
			return (V) values[pos];
		}
		return null;
	}

	
	public void delete(K pKey) {
		// TODO Auto-generated method stub
		if(pKey==null){
			return;
		}
		int pos= hash(pKey);
		
		if(keys[pos].compareTo(pKey)==0){
			keys[pos]=null;
			values[pos]=null;
			size--;
		}
	}

	
	public boolean contains(K pKey) {
		// TODO Auto-generated method stub
		if(pKey==null){
			return false;
		}
		if(this.get(pKey)!=null){
			return true;
		}
		return false;
	}

	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		
		if(size==0){
			return true;
		}
		return false;
	}

	
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	public int capacity() {
		// TODO Auto-generated method stub
		return capacity;
	}

	
	public Iterator<K> Keys() {
		// TODO Auto-generated method stub
		LinkedSimpleList<K> temp= new LinkedSimpleList<>();
		K current=null;
		for(int i=0;i<capacity;i++){
			current= (K) keys[i];
			if(current!=null){
				temp.add(current);
			}
		}
		Iterator<K> tempK= temp.iterator();
		
	
		return tempK;
	}
	public int obtieneNumPrimo(int cap){
		int numPosPrimo= (cap*2)+1;
		
		boolean esPrimo=true;
		while(esPrimo==true){
			
			for(int i=2; i<numPosPrimo;i++){
					if(numPosPrimo%i==0.0){
						esPrimo=false;
					}
			}
			if(esPrimo==true){
				return numPosPrimo;
			}
			else{
				numPosPrimo++;
				esPrimo=true;
			}
		}
		return numPosPrimo;
	}

}
