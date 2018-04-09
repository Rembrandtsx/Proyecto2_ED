package model.vo;

import model.data_structures.IList;

public class TaxiConServicios implements Comparable<TaxiConServicios>{

    private String taxiId;
    private String compania;
    private IList<Servicio> servicios;

    public TaxiConServicios(String taxiId, String compania){
        this.taxiId = taxiId;
        this.compania = compania;
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

    public void agregarServicio(Servicio servicio){
        servicios.add(servicio);
    }

    @Override
    public int compareTo(TaxiConServicios o) {
        return taxiId.compareTo( o.getTaxiId());
    }

    public void print(){
        System.out.println(Integer.toString(numeroServicios())+" servicios "+" Taxi: "+taxiId);
        for(Servicio s : servicios){
            System.out.println("\t"+s.getStartTime());
        }
        System.out.println("___________________________________");;
    }
}
