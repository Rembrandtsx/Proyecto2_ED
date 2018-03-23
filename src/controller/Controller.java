package controller;

import api.ITaxiTripsManager;
import model.data_structures.HeapBinario;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.ISymbolTable;
import model.data_structures.ILinkedList;
import model.data_structures.LinkedSimpleList;
import model.logic.TaxiTripsManager;
import model.vo.Compania;
import model.vo.CompaniaServicios;
import model.vo.CompaniaTaxi;
import model.vo.InfoTaxiRango;
import model.vo.RangoDistancia;
import model.vo.RangoFechaHora;
import model.vo.Servicio;
import model.vo.ServiciosValorPagado;
import model.vo.Taxi;
import model.vo.ZonaServicios;

public class Controller 
{
	/**
	 * modela el manejador de la clase l�gica
	 */
	private static ITaxiTripsManager manager = new TaxiTripsManager();

	
	//1C
	public static boolean cargarSistema(String direccionJson)
	{
		return manager.cargarSistema(direccionJson);
	}
	
	//1A
	
	public static LinkedSimpleList<Servicio> darServiciosEnAreaOrdenCronologico(int pArea) {
			
		return manager.darServiciosEnAreaOrdenCronologico(pArea);
	}

	//2A
	
	public static LinkedSimpleList<Servicio>  darServiciosPorDistanciaRecorridaMillas(String r) {
		
		return manager.darServiciosPorDistanciaRecorridaMillas(r);
	}
}
