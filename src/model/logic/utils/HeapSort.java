package model.logic.utils;

import java.util.Comparator;

import model.data_structures.ArrayList;
import model.data_structures.HeapBinario;

import model.vo.Taxi;
import model.vo.TaxiConPuntos;

public class  HeapSort<V extends Comparable<V>>{

	
	    
	    public static void heapSortAscendentemente(ArrayList<TaxiConPuntos> a, ComparatorTaxiPorPuntos comparador){
	    	
	    	HeapBinario<TaxiConPuntos> heapB= new HeapBinario(comparador);
	    	
	    	for(int j=1; j<a.size();j++){
	    		
	    		heapB.swim(a.get(j));
	    		
	    	}
	    	
	    	TaxiConPuntos actual2= heapB.sink();
	    	
	    	for(int i=(0); i>=0;i--){
	    		
	    		a.addIn(i, actual2);
	    		actual2= heapB.sink();
	    		
	    		
	    	}
	    	
	    	
	    }
	    public static void heapSortAscendentemente(TaxiConPuntos[] a, Comparator<TaxiConPuntos> comparador){
	    	
	    	HeapBinario<TaxiConPuntos> heapB= new HeapBinario(comparador);
	    	
	    	for(int j=1; j<a.length;j++){
	    		
	    		heapB.swim(a[j]);
	    		
	    	}
	    	
	    	TaxiConPuntos actual2= heapB.sink();
	    	
	    	for(int i=heapB.size(); i>0;i--){
	    		
	    		a[i]=actual2;
	    		actual2= heapB.sink();
	    		
	    		
	    	}
	    	
	    	
	    }
	    
	    public void heapSortAscende(V[] a, Comparator<V> comparador){
	    	
	    	HeapBinario<V> heapB= new HeapBinario(comparador);
	    	
	    	for(int j=1; j<a.length;j++){
	    		
	    		heapB.swim(a[j]);
	    		
	    	}
	    	
	    	V actual2= heapB.sink();
	    	
	    	for(int i=heapB.size(); i>0;i--){
	    		
	    		a[i]=actual2;
	    		actual2= heapB.sink();
	    		
	    		
	    	}
	    	
	    	
	    }
	
}
