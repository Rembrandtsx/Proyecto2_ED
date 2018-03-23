package model.logic.utils;

import java.util.Comparator;

import model.data_structures.HeapBinario;
import model.data_structures.LinkedSimpleList;
import model.data_structures.SimpleNode;
import model.vo.Servicio;
import model.vo.Taxi;

public class  HeapSort<T extends Comparable<T>>{

	
	    
	    public static void heapSortAscendentemente(Taxi[] a, Comparator<Taxi> comparador){
	    	
	    	HeapBinario<Taxi> heapB= new HeapBinario(comparador);
	    	
	    	for(int j=1; j<a.length;j++){
	    		
	    		heapB.swim(a[j]);
	    		
	    	}
	    	
	    	Taxi actual2= heapB.sink();
	    	
	    	for(int i=heapB.size(); i>0;i--){
	    		
	    		a[i]=actual2;
	    		actual2= heapB.sink();
	    		
	    		
	    	}
	    	
	    	
	    }
	    
	    
	
}
