package model.vo;

import model.data_structures.ILinkedList;

/**
 * VO utilizado en Req 5A, tiene el rango de distancia y la lista de servicios cuya distancia recorrida 
 * pertenece a dicho rango
 */
public class RangoDistancia implements Comparable<RangoDistancia>
{
	//ATRIBUTOS
	
    /**
     * Modela el valor minimo del rango
     */
	private double limiteSuperior;
	
	/**
	 * Modela el valor m�ximo del rango
	 */
	private double limineInferior;
	
	/**
	 * Modela la lista de servicios cuya distancia recorrida esta entre el l�mite inferior y el l�mite superior
	 */
	private ILinkedList<Servicio> serviciosEnRango;

	//M�TODOS
	
	/**
	 * @return the limiteSuperior
	 */
	public double getLimiteSuperior()
	{
		return limiteSuperior;
	}

	/**
	 * @param limiteSuperior the limiteSuperior to set
	 */
	public void setLimiteSuperior(double limiteSuperior) 
	{
		this.limiteSuperior = limiteSuperior;
	}

	/**
	 * @return the limineInferior
	 */
	public double getLimineInferior() 
	{
		return limineInferior;
	}

	/**
	 * @param limineInferior the limineInferior to set
	 */
	public void setLimineInferior(double limineInferior) 
	{
		this.limineInferior = limineInferior;
	}

	/**
	 * @return the serviciosEnRango
	 */
	public ILinkedList<Servicio> getServiciosEnRango() 
	{
		return serviciosEnRango;
	}

	/**
	 * @param serviciosEnRango the serviciosEnRango to set
	 */
	public void setServiciosEnRango(ILinkedList<Servicio> serviciosEnRango)
	{
		this.serviciosEnRango = serviciosEnRango;
	}

	@Override
	public int compareTo(RangoDistancia o)
	{
		return 0;
	}
}
