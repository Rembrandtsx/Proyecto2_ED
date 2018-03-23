package api;




import model.data_structures.HeapBinario;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.ISymbolTable;
import model.data_structures.ILinkedList;
import model.data_structures.LinkedSimpleList;
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

/**
 * API para la clase de logica principal  
 */
public interface ITaxiTripsManager 
{
	//1A
	
	public LinkedSimpleList<Servicio> darServiciosEnAreaOrdenCronologico(int pArea);
		
	//2A
	
	public LinkedSimpleList<Servicio> darServiciosPorDistanciaRecorridaMillas(String r);
	
	//1C
		/**
		 * Dada la direcci�n del json que se desea cargar, se generan VO's, estructuras y datos necesarias
		 * @param direccionJson, ubicaci�n del json a cargar
		 * @return true si se lo logr� cargar, false de lo contrario
		 */
	public boolean cargarSistema( String direccionJson);

		
	
}
