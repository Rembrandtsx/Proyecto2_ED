package model.vo;

/**
 * Modela una rango de fechas y horas (iniciales y finales)
 *
 */
public class RangoFechaHora
{
	//ATRIBUTOS
	
    /**
     * Modela la fecha inicial del rango
     */
	private String fechaInicial; 
	
	/**
	 * Modela la fecha final del rango
	 */
	private String fechaFinal;
	
	/**
	 * modela la hora inicial del rango
	 */
	private String horaInicio; 
	
	/**
	 * modela la hora final del rango
	 */
	private String horaFinal;
	
	//CONSTRUCTOR
	/**
	 * @param pFechaInicial, fecha inicial del rango
	 * @param pFechaFinal, fecha final del rango
	 * @param pHoraInicio, hora inicial del rango
	 * @param pHoraFinal, hora final del rango
	 */
	public RangoFechaHora(String pFechaInicial, String pFechaFinal, String pHoraInicio, String pHoraFinal)
	{
		this.fechaFinal = pFechaFinal;
		this.fechaInicial = pFechaInicial;
		this.horaFinal = pHoraFinal;
		this.horaInicio = pHoraInicio;
	}
	//M�TODOS
	
	/**
	 * @return the fechaInicial
	 */
	public String getFechaInicial() 
	{
		return fechaInicial;
	}

	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(String fechaInicial)
	{
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public String getFechaFinal() 
	{
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(String fechaFinal) 
	{
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the horaInicio
	 */
	public String getHoraInicio() 
	{
		return horaInicio;
	}

	/**
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(String horaInicio) 
	{
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the horaFinal
	 */
	public String getHoraFinal() 
	{
		return horaFinal;
	}

	/**
	 * @param horaFinal the horaFinal to set
	 */
	public void setHoraFinal(String horaFinal) 
	{
		this.horaFinal = horaFinal;
	}
	
}
