package model.vo;

import java.text.ParseException;

import model.data_structures.IList;
import model.data_structures.LinkedSimpleList;
import model.data_structures.SymbolTableLP;
import model.data_structures.SymbolTableSC;
import model.logic.utils.ComparatorServicioPorFechaHora;
import model.logic.utils.HeapSort;

public class TaxiConServicios implements Comparable<TaxiConServicios>{

    private String taxiId;
    private String compania;
    private LinkedSimpleList<Servicio> servicios;
    private SymbolTableLP<Integer, LinkedSimpleList<Servicio>> misServicios;
    
    private HeapSort<Servicio> heapSortS;


    public TaxiConServicios(String taxiId, String compania){
        this.taxiId = taxiId;
        this.compania = compania;
        this.servicios= new LinkedSimpleList<>();
        this.misServicios = new SymbolTableLP<>(59); //Numero primo
		this.heapSortS= new HeapSort<>();
        // this.servicios = new List<Service>(); // inicializar la lista de servicios 
    }

    public String getTaxiId() {
        return taxiId;
    }

    public String getCompania() {
        return compania;
    }

    public IList<Servicio> getServicios()
    {
    	return servicios;
    }
    
    public int numeroServicios(){
        return servicios.size();
    }
    public int numeroServiciosXKeyHash(Integer pKey){
        return misServicios.get(pKey).size();
    }
    public void setServicios(LinkedSimpleList<Servicio> pServicios)
    {
    	servicios=pServicios;
    }
    public void agregarServicio(Servicio servicio){
    	
        servicios.add(servicio);
    }
    public SymbolTableLP getMisServiciosHashTable() {
		return misServicios;
	}
    public void setServiciosHashTable(Integer i, Servicio actual) {
    	
    	if("eb0f2345fdcb97ed52105cd4044ae6e3302eb4253ff9d804a0315deee7dc96462c233ee45be74d33d1560159854cef22fb70868070a162172d52876392cc948c".equals(actual.getTaxiId())&&actual.pickup_community_area==28){
			System.out.println( actual.id);
		}
    	if(misServicios.get(i)==null){
    		
    		LinkedSimpleList<Servicio> temporal= new LinkedSimpleList<>();
    		temporal.add(actual);
    		misServicios.put(i, temporal);
    	}
    	else{
    		
    	misServicios.get(i).add(actual);
		
    	
    	Servicio[] ordenar= new Servicio[misServicios.get(i).size()+1];
    	for(int j=0; j< misServicios.get(i).size();j++){
    		ordenar[j]= misServicios.get(i).get(j);
    	}
    	
    	heapSortS.heapSortAscendentemente(ordenar, new ComparatorServicioPorFechaHora());
    	
    	LinkedSimpleList<Servicio> nuevo= new LinkedSimpleList<>();
    	
    	for(int j=0; j<misServicios.get(i).size();j++){
    		
    		nuevo.add(ordenar[j]);
    	}
    	misServicios.put(i, nuevo);
    	
    	}
    	
	}
    @Override
    public int compareTo(TaxiConServicios o) {
        return taxiId.compareTo( o.getTaxiId());
    }

    public void print(){
        System.out.println(Integer.toString(numeroServicios())+" servicios "+" Taxi: "+taxiId);
        Servicio s=null;
        
        for(int i=0; i<servicios.size();i++){
        	//Agregado try para requerimientos
        	s= servicios.get(i);
        	if(s!=null){
	            try {
					System.out.println("\t"+s.getStartTime()+"    id:"+s.getTripId());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        System.out.println("___________________________________");;
    }
}
