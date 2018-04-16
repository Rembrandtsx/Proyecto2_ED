package model.vo;

import java.text.ParseException;

import model.data_structures.LinkedSimpleList;
import model.data_structures.Queue;
import model.data_structures.SymbolTableSC;


/**
 * Representation of a taxi object
 */
public class Taxi implements Comparable<Taxi>
{
	//----------------------
	//Atributos
	//------------------------

	/**Identificador del taxi*/
	private String id;

	/**Compania a la que pertenece el taxi, si no  esta asociado a ninguna sera NaN*/
	private String company;

	/**Servicios prestados por el taxi*/
	private SymbolTableSC<Integer, Servicio> misServicios;

	/**La suma del dinero recibido por los servicios prestados */
	private double ganancia;

	/**La distancia total recorrida en todos los servicios prestados */
	private double recorrido;

	//Constructor
	public Taxi (String pID, String pCompany)
	{
		id = pID;
		company = pCompany;
		misServicios = new SymbolTableSC<>(59); //Numero primo
		ganancia = 0;
		recorrido = 0;
	}

	/**
	 * @return id - taxi_id
	 */
	public String getTaxiId() {
		return id;
	}

	/**
	 * @return company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Suma las ganancias de los servicios prestados por el taxi
	 * @return total de ganancias que ha recibido el taxi
	 */
	public void getGanancias(){
		ganancia=0;
		recorrido = 0;
		
		for(int i=0; i<misServicios.size();i++)
		{
			LinkedSimpleList<Servicio> servicios= misServicios.getList(i);
			
			Servicio actual= servicios.get(0);
			
			while(actual!=null){
			ganancia += actual.getTripTotal();
			recorrido += actual.getTripMiles();
			
			actual= servicios.get(i+1);
			}
			
		}
	}

	/**
	 * Cola de servicios prestados en un rango dado por parametro
	 * @param rango Fecha y hora de inicio y fin en la que se encuentran los servicios
	 * @return Cola con los servicios prestados en el rango dado
	 * @throws ParseException 
	 */
	public Queue<Servicio> serviciosEnRango(RangoFechaHora rango) throws ParseException
	{
		Queue<Servicio> enRango= new Queue<Servicio>();

		String inicio= rango.getFechaInicial()+"T"+rango.getHoraInicio();
		String fin= rango.getFechaFinal()+"T"+rango.getHoraFinal();

		Servicio compararRango = new Servicio(inicio, fin);

		boolean empezo = false;
		getMisServicios().listing();
		while(true)
		{
			Servicio actual=getMisServicios().getCurrent();
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
					enRango.enqueue(getMisServicios().getCurrent());
			}
			getMisServicios().avanzar();
			try {
				getMisServicios().getCurrent();
			} catch (Exception e) {
				e.printStackTrace();
				break;
				// TODO: handle exception
			}
		}
		return enRango;
	}

	/**
	 * Cuenta cuantos servicios ha prestado el taxi en un rango de tiempo dado por parametro
	 * @param rango Fecha y hora inicial y final del rango en el que ser quieren contar los servicios
	 * @return cantidad de servicios prestados por el taxi en el rango especificado
	 * @throws ParseException 
	 */
	public int cuantosServiciosEnRango(RangoFechaHora rango) throws ParseException
	{
		return serviciosEnRango(rango).size();
	}

	/**
	 * Suma las ganancias de los servicios prestados por el taxi en un rango dado por parametro
	 * @return total de ganancias de los servicios prestados por el taxi en el rango
	 * @throws ParseException 
	 */
	public double getGananciaEnRango(RangoFechaHora rango) throws ParseException{
		double gananciaRango=0;
		Queue<Servicio> enRango = serviciosEnRango(rango);
		while(!enRango.isEmpty())
		{
			gananciaRango += enRango.dequeue().getTripTotal();
		}
		return gananciaRango;
	}
	
	/**
	 * Suma el recorrido de los servicios prestados por el taxi en un rango dado por parametro
	 * @return total recorrido en millas de los servicios prestados por el taxi en el rango
	 * @throws ParseException 
	 */
	public double getRecorridoEnRango(RangoFechaHora rango) throws ParseException{
		double recorridoRango=0;
		
		Queue<Servicio> enRango = serviciosEnRango(rango);
		while(!enRango.isEmpty())
		{
			recorridoRango += enRango.dequeue().getTripMiles();
		}
		return recorridoRango;
	}
	
	/**
	 * Suma el tiempo de duracion de los servicios prestados por el taxi en un rango dado por parametro
	 * @return total de tiempo en segundos de los servicios prestados por el taxi en el rango
	 * @throws ParseException 
	 */
	public int getTiempoTotalEnRango(RangoFechaHora rango) throws ParseException{
		int tiempoTotal=0;
		Queue<Servicio> enRango = serviciosEnRango(rango);
		while(!enRango.isEmpty())
		{
			tiempoTotal += enRango.dequeue().trip_seconds;
		}
		return tiempoTotal;
	}
	
	/**
	 * Relacion entre ganancia y distancia recorrida
	 * Rentabilidad = ganancia/recorrido total. La retabilidad esta dada en $/millas
	 * @return rentabilidad del taxi
	 */
	public double rentabilidad()
	{
		getGanancias();
		return ganancia/recorrido;
	}

	public SymbolTableSC getMisServiciosHashTable() {
		return misServicios;
	}
	public LinkedSimpleList<Servicio> getMisServicios() {
		
		SymbolTableSC<Integer, Servicio> lista= getMisServiciosHashTable();
		LinkedSimpleList<Integer> listaKeys= (LinkedSimpleList<Integer>) getMisServiciosHashTable().Keys();
		LinkedSimpleList<Servicio> respuesta= new LinkedSimpleList<>();
		LinkedSimpleList<Servicio> listaActual= null;
		Servicio actual=null;
		
		
		for(int i=0; i<listaKeys.size(); i++){
			listaActual= lista.getList(listaKeys.get(i));
			
			actual= listaActual.get(0);
			int pos=0;
			while(actual!=null){
				
				actual= listaActual.get(pos);
				respuesta.add(actual);
				pos++;
			}
		}
		
		return respuesta;
	}
	
	//Comparar por ganancias
	@Override
	public int compareTo(Taxi o) {
		return this.id.equals(o.id)? 0:1;
	}
	
	//METODOS AGREGADOS PARA REQUERIMIENTOS
	
	
	public void addServicio(Integer area, Servicio servicio) {
		this.misServicios.put(area, servicio);
	}
}
