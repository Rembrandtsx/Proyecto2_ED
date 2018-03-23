package model.vo;

import model.data_structures.ILinkedList;
import model.data_structures.Queue;

public class InfoTaxiRango 
{
	//ATRIBUTOS
	
	/**
	 * Modela el id del taxi 
	 */
	private String idTaxi;
	
	/**
	 * Modela el rango de fechas y horas (iniciales y finales)
	 */
	private RangoFechaHora rango;
	
	/**
	 * modela el nombre de la compa�ia del taxi
	 */
	private String company;
	
	/**
	 * modela el dinero que gano el taxi en el rango
	 */
	private double plataGanada;
	
	/**
	 * Modela la lista de servicios que presto el taxi en el rango
	 */
	private Queue<Servicio> serviciosPrestadosEnRango; 
	
	/**
	 * modela la distancia recorrida por el taxi en el rango
	 */
	private double distanciaTotalRecorrida; 
	
	/**
	 * modela el tiempo total de servicios
	 */
	private String tiempoTotal;

	//M�TODOS
	
	/**
	 * @return the idTaxi
	 */
	public String getIdTaxi()
	{
		return idTaxi;
	}

	/**
	 * @param idTaxi the idTaxi to set
	 */
	public void setIdTaxi(String idTaxi)
	{
		this.idTaxi = idTaxi;
	}

	/**
	 * @return the rango
	 */
	public RangoFechaHora getRango()
	{
		return rango;
	}

	/**
	 * @param rango the rango to set
	 */
	public void setRango(RangoFechaHora rango)
	{
		this.rango = rango;
	}

	/**
	 * @return the company
	 */
	public String getCompany()
	{
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) 
	{
		this.company = company;
	}

	/**
	 * @return the plataGanada
	 */
	public double getPlataGanada() 
	{
		return plataGanada;
	}

	/**
	 * @param plataGanada the plataGanada to set
	 */
	public void setPlataGanada(double plataGanada) 
	{
		this.plataGanada = plataGanada;
	}

	/**
	 * @return the serviciosPrestadosEnRango
	 */
	public Queue<Servicio> getServiciosPrestadosEnRango()
	{
		return serviciosPrestadosEnRango;
	}

	/**
	 * @param serviciosPrestadosEnRango the serviciosPrestadosEnRango to set
	 */
	public void setServiciosPrestadosEnRango(Queue<Servicio> serviciosPrestadosEnRango)
	{
		this.serviciosPrestadosEnRango = serviciosPrestadosEnRango;
	}

	/**
	 * @return the distanciaTotalRecorrida
	 */
	public double getDistanciaTotalRecorrida()
	{
		return distanciaTotalRecorrida;
	}

	/**
	 * @param distanciaTotalRecorrida the distanciaTotalRecorrida to set
	 */
	public void setDistanciaTotalRecorrida(double distanciaTotalRecorrida) 
	{
		this.distanciaTotalRecorrida = distanciaTotalRecorrida;
	}

	/**
	 * @return the tiempoTotal
	 */
	public String getTiempoTotal()
	{
		return tiempoTotal;
	}

	/**
	 * @param tiempoTotal the tiempoTotal to set
	 */
	public void setTiempoTotal(String tiempoTotal) 
	{
		this.tiempoTotal = tiempoTotal;
	}
	
	
}
