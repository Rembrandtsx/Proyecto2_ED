package controller;

import api.ITaxiTripsManager;
import model.data_structures.IList;
import model.logic.TaxiTripsManager;
import model.vo.Servicio;
import model.vo.Taxi;
import model.vo.TaxiConPuntos;
import model.vo.TaxiConServicios;

public class Controller 
{
	/**
	 * modela el manejador de la clase l�gica
	 */
	private static ITaxiTripsManager manager =new TaxiTripsManager();

	//Carga El sistema
	public static boolean cargarSistema(String direccionJson)
	{
		return manager.cargarSistema(direccionJson);
	}
	//1A
	public static IList<TaxiConServicios> R1A(int zonaInicio, String compania)
	{
		return manager.A1TaxiConMasServiciosEnZonaParaCompania(zonaInicio, compania);
	}

	//2A
	public static IList<Servicio> R2A(int duracion)
	{
		return manager.A2ServiciosPorDuracion(duracion);
	}

	//1B
	public static IList<Servicio> R1B(double distanciaMinima, double distanciaMaxima)
	{
		return manager.B1ServiciosPorDistancia(distanciaMinima, distanciaMaxima);
	}

	//2B
	public static IList<Servicio> R2B(int zonaInicio, int zonaFinal, String fechaI, String fechaF, String horaI, String horaF)
	{
		return manager.B2ServiciosPorZonaRecogidaYLlegada(zonaInicio, zonaFinal, fechaI, fechaF, horaI, horaF);
	}	
	//1C
	public static TaxiConPuntos[] R1C()
	{
		return manager.R1C_OrdenarTaxisPorPuntos();
	}	
	//2C
	public static IList<Servicio> R2C(String taxiIDReq2C, double millas, double latitud, double longitud)
	{
		return manager.R2C_LocalizacionesGeograficas(taxiIDReq2C, millas, latitud, longitud);
	}
	//3C
	public static IList<Servicio> R3C(String fecha, String hora) 
	{
		return manager.R3C_ServiciosEn15Minutos(fecha, hora);
	}	
}
