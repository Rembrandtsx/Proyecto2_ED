package model.vo;

import java.text.ParseException;

import model.data_structures.ILinkedList;
import model.data_structures.LinkedSimpleList;

public class Compania implements Comparable<Compania> {
	
	public String nombre;
	
	public LinkedSimpleList<Taxi> taxiList;
	
	public LinkedSimpleList<Servicio> servicioList;
	
	
	public Compania(String pNombre){
		nombre = pNombre;
		taxiList = new LinkedSimpleList<Taxi>();
		servicioList = new LinkedSimpleList<Servicio>();
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LinkedSimpleList<Taxi> getTaxisInscritos() {
		return taxiList;
	}

	public void setTaxisInscritos(LinkedSimpleList<Taxi> taxisInscritos) {
		this.taxiList = taxisInscritos;
	}
	
	public LinkedSimpleList<Taxi> getMisTaxis(){
		return taxiList;
	}
	public LinkedSimpleList<Servicio> getMisServicios(){
		return servicioList;
	}
	/**
	 * Busca un taxi en la compania segun el id
	 * @param id El id del taxi que se busca
	 * @return El taxi de la compania con el id dado por parametro
	 */
	public Taxi buscarTaxi(String id)
	{
		Taxi buscado = null;
		taxiList.listing();
		while(true)
		{
			if(taxiList.getCurrent().getTaxiId().equals(id))
			{
				buscado = taxiList.getCurrent();
				break;
			}
			taxiList.avanzar();
			try 
			{
				taxiList.getCurrent();
			} 
			catch (Exception e) 
			{
				break;
			}
		}
		return buscado;
		
	}
	
	/**
	 * Busca el taxi mas rentable de la compania, es decir aquel en el que la relacion ganancia/distancia es mayor
	 * Rentable= ganancia/recorrido total. La retabilidad esta dada en $/millas
	 * @return Taxi mas rentable
	 */
	public Taxi masRetable()
	{
		Taxi masRentable = null;
		taxiList.listing();
		double mayorRentabilidad=0;
		while(true)
		{
			double rentabilidadCurrent = taxiList.getCurrent().rentabilidad(); 
			if(rentabilidadCurrent >= mayorRentabilidad)
			{
				mayorRentabilidad=rentabilidadCurrent;
				masRentable=taxiList.getCurrent();
			}
			taxiList.avanzar();
			try {
				taxiList.getCurrent();
			} catch (Exception e) {
				// TODO: handle exception
				break;
			}
		}
		
		return masRentable;
	}
	
	
	/**
	 * Lista de servicios prestados en un rango dado por parametro
	 * @param rango Fecha y hora de inicio y fin en la que se encuentran los servicios
	 * @return Cola con los servicios prestados en el rango dado
	 * @throws ParseException 
	 */
	public LinkedSimpleList<Servicio> serviciosEnRango(RangoFechaHora rango) throws ParseException
	{
		LinkedSimpleList<Servicio> enRango= new LinkedSimpleList<Servicio>();

		String inicio= rango.getFechaInicial()+"T"+rango.getHoraInicio();
		String fin= rango.getFechaFinal()+"T"+rango.getHoraFinal();

		Servicio compararRango = new Servicio(inicio, fin);

		boolean empezo = false;
		servicioList.listing();
		while(servicioList.getCurrent()!=null)
		{
			Servicio actual=servicioList.getCurrent();
			if(empezo)
			{
				//comparar fecha inicial del servicio con fecha final del rango
				if(!actual.getStartTime().before(compararRango.getEndTime()))
					break;
			}
			int temp=actual.compareTo(compararRango);
			if(temp == -1 || temp == 0 )
			{
				empezo=true;
				if(actual.getEndTime().before(compararRango.getEndTime()))
					enRango.add(servicioList.getCurrent());
			}
			servicioList.avanzar();
		}
		return enRango;
	}

	@Override
	public int compareTo(Compania o) {
		// TODO Auto-generated method stub
		String i = ""+nombre.charAt(0);
		String j = ""+o.nombre.charAt(0);
		if(nombre.equals(o.nombre))
			return 0;
		else if(i.compareTo(j) >= 0)
			return -1;
		return 1;
	}
	
}