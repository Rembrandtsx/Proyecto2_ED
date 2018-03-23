package model.logic.utils;

import model.data_structures.LinkedSimpleList;
import model.data_structures.SimpleNode;
import model.vo.Servicio;

public class  Merge<T extends Comparable<T>>{

	
	     
	   
	 
	    public static void merge(LinkedSimpleList<Servicio> a,LinkedSimpleList<Servicio> aux,  int lo, int mid, int hi)
	    {
	        for(int k=lo; k<=hi;k++){
	        	aux.modifyElement(k,a.get(k));
	        }
	       
	        int i=lo;
	        int j=mid+1;
	        for(int k=lo; k<=hi;k++){
	        	 
	        	if(i>mid){
	        		a.modifyElement(k, aux.get(j++));
	        	}
	        	else if(j>hi){
	        		a.modifyElement(k, a.get(i++));
	        	}
	        	
	        	else if(aux.get(j).compareTo(aux.get(i))<0){
	        		a.modifyElement(k, aux.get(j++));
	        	}
	        	else{
	        		a.modifyElement(k, aux.get(i++));
	        	}
	        }
	    }
	 
	    
	    public static void sorted(LinkedSimpleList<Servicio> a,LinkedSimpleList<Servicio> aux,  int lo, int hi) 
	    {
	    	

	    	if (hi <= lo)
	            return;
	    	
	    	int mid= lo +(hi-lo)/2;
	    	
	        sorted(a, aux, lo, mid);
	        sorted(a, aux, mid+1, hi);
	        
	        merge(a, aux, lo,mid,hi);
	 
	    }
	    public static void sorted(LinkedSimpleList<Servicio> a) 
	    {
	    	LinkedSimpleList<Servicio> aux= new LinkedSimpleList<Servicio>();
	    	for(int k=0; k<a.size();k++){
	        	aux.add(a.get(k));
	        }
	    	sorted(a, aux, 0, a.size()-1);
	        
	 
	    }
	 
	 
	    
	
}
