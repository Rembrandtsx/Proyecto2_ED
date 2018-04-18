package model.vo;

import java.text.ParseException;

import model.data_structures.IList;
import model.data_structures.LinkedSimpleList;
import model.data_structures.SymbolTableSC;

public class TaxiConServicios implements Comparable<TaxiConServicios>{

    private String taxiId;
    private String compania;
    private IList<Servicio> servicios;
    private SymbolTableSC<Integer, Servicio> misServicios;


    public TaxiConServicios(String taxiId, String compania){
        this.taxiId = taxiId;
        this.compania = compania;
        this.servicios= new LinkedSimpleList<>();
        misServicios = new SymbolTableSC<>(59); //Numero primo
		
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
    public void setServicios(LinkedSimpleList<Servicio> pServicios)
    {
    	servicios=pServicios;
    }
    public void agregarServicio(Servicio servicio){
    	
        servicios.add(servicio);
        
        
        
        
    }
    public SymbolTableSC getMisServiciosHashTable() {
		return misServicios;
	}
    public void setServiciosHashTable(Integer i, Servicio actual) {
		misServicios.put(i, actual);
	}
    @Override
    public int compareTo(TaxiConServicios o) {
        return taxiId.compareTo( o.getTaxiId());
    }

    public void print(){
        System.out.println(Integer.toString(numeroServicios())+" servicios "+" Taxi: "+taxiId);
        
        
        
        for(Servicio s : servicios){
        	//Agregado try para requerimientos
            try {
				System.out.println("\t"+s.getStartTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.println("___________________________________");;
    }
}
