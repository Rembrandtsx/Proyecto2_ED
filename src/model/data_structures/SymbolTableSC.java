package model.data_structures;

import java.util.Iterator;

public class SymbolTableSC<K extends Comparable<K>, V extends Comparable<V>> implements ISymbolTable<K, V>{

	
	
	public final static double  FACTORCARGASEPARATECHAINING= 6.0;
	
	
	private int size;
	
	private int capacity;
	
	private Object[] nodes;
	
	
	public int hash(K pKey){
		int r=0;
		
		for(int i=0;i<nodes.length;i++){
			if(nodes[i]!=null&&((LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[i]).get(0).getKey().compareTo(pKey)==0){
				return i;
			}
		}
		return r;
		
	}
	
	public void reHash(int capacity){
		int temp= this.capacity;
		this.capacity=capacity;
		
		Object[] tempNodes=  new Object[capacity];
		
		
		int hash;
		int pos;
		for(int i=0; i<temp;i++){
			LinkedSimpleList<SimpleNodeSymbolTable<K, V>> tempK=  (LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[i];
			
			if(tempK!=null){
				hash= getHashCode(tempK.get(0).getKey());
				pos= hash%capacity;
					
					if(tempNodes[pos]==null){
						tempNodes[pos]= tempK;
						
					}
				
				
			}
		}
		this.nodes=tempNodes;
		
	}
	
	public SymbolTableSC(int pCapacity) {
		
		capacity= pCapacity;
		nodes=  new Object[capacity];
		size=0;
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
	@Override
	public void put(K pkey, V value) {
		// TODO Auto-generated method stub
		if(pkey==null){
			return;
		}
		if(value==null){
			delete(pkey);
		}
		int ans= hash(pkey);
		
		LinkedSimpleList<SimpleNodeSymbolTable<K, V>> actual=null;
		SimpleNodeSymbolTable<K, V> add= new SimpleNodeSymbolTable<K, V>(pkey, value);
		
		
		if(((double) size/(double)capacity)>=FACTORCARGASEPARATECHAINING){
			reHash(obtieneNumPrimo(capacity));///////Formula que facilita obtener numeros primos.
			
		}
		
		if(nodes[ans]!=null&&((LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[ans]).get(0).getKey().compareTo(pkey)==0){
			
				((LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[ans]).add(add);
				size++;
				return;
				
			
		}
		else{

			Integer tempHash= getHashCode(pkey);
			ans= tempHash%capacity;
			
			if(nodes[ans]==null){
			LinkedSimpleList<SimpleNodeSymbolTable<K, V>> nueva= new LinkedSimpleList<>();
				nueva.add(add);
				nodes[ans]=nueva;
				size++;
			}else{
				((LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[ans]).add(add);
				size++;
				return;
			}
			
		}
		
	
	}

	@Override
	public V get(K pKey) {
		// TODO Auto-generated method stub
		int pos= hash(pKey);
		
		if(nodes[pos]!=null&&((LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[pos]).get(0).getKey().compareTo(pKey)==0){
			
			return ((LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[pos]).get(0).getElement();
		}
		return null;
	}

	@Override
	public void delete(K pKey) {
		if(pKey==null){
			return;
		}
		int pos= hash(pKey);
		
		if(nodes[pos]!=null&&((LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[pos]).get(0).getKey().compareTo(pKey)==0){
			size= size-((LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[pos]).size();
			nodes[pos]=null;
			
		}
		
	}

	@Override
	public boolean contains(K pKey) {
		// TODO Auto-generated method stub
		if(get(pKey)!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		
		if(size==0){
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public int capacity() {
		// TODO Auto-generated method stub
		return capacity;
	}
	@Override
	public Iterator<K> Keys() {
		// TODO Auto-generated method stub
		LinkedSimpleList<K> temp = new LinkedSimpleList<K>();
		K current=null;
		
		for(int i=0; i<capacity;i++){
			if(nodes[i]!=null){
				current= ((LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[i]).get(0).getKey();
				if(current!=null){
				temp.add(current);
				}
			}
		}
		
		return temp.iterator();
	}
	public LinkedSimpleList<SimpleNodeSymbolTable<K, V>> getNode(K pKey) {
		// TODO Auto-generated method stub
		int pos= hash(pKey);
		
		if(nodes[pos]!=null&&((LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[pos]).get(0).getKey().compareTo(pKey)==0){
			return ((LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[pos]);
		}
		return null;
	}
	public LinkedSimpleList<V> getList(K pKey) {
		// TODO Auto-generated method stub
		int pos= hash(pKey);
		LinkedSimpleList<SimpleNodeSymbolTable<K, V>> temp=(LinkedSimpleList<SimpleNodeSymbolTable<K, V>>) nodes[pos];
		
		if(temp!=null&&temp.get(0).getKey().compareTo(pKey)==0){
			LinkedSimpleList<V> rta= new LinkedSimpleList<>();
			for(int i=0; i<temp.size();i++ ){
				rta.add(temp.get(i).getElement());
			}
			return rta;
					
		}
		
		return null;
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
