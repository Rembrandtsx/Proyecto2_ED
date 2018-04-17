package view;

import java.io.File;
import java.text.ParseException;
import java.util.Scanner;

import controller.Controller;
import model.data_structures.HeapBinario;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.ISymbolTable;
import model.data_structures.ILinkedList;
import model.data_structures.IList;
import model.data_structures.LinkedSimpleList;
import model.logic.TaxiTripsManager;
import model.vo.Compania;
import model.vo.CompaniaServicios;
import model.vo.CompaniaTaxi;
import model.vo.InfoTaxiRango;
import model.vo.RangoDistancia;
import model.vo.RangoFechaHora;
import model.vo.Servicio;
import model.vo.ServicioResumen;
import model.vo.ServiciosValorPagado;
import model.vo.Taxi;
import model.vo.TaxiConPuntos;
import model.vo.TaxiConServicios;
import model.vo.ZonaServicios;

/**
 * view del programa
 */
public class TaxiTripsManagerView 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		while(!fin)
		{
			//imprime menu
			printMenu();

			//opcion req
			int option = sc.nextInt();

			switch(option)
			{
			//1C cargar informacion dada
			case 1:

				
				//imprime menu cargar
				printMenuCargar();

				//opcion cargar
				int optionCargar = sc.nextInt();

				//directorio json
				String linkJson = "";
				switch (optionCargar)
				{
				//direccion json pequeno
				case 1:

					linkJson = TaxiTripsManager.DIRECCION_SMALL_JSON;
					break;

					//direccion json mediano
				case 2:

					linkJson = TaxiTripsManager.DIRECCION_MEDIUM_JSON;
					break;

					//direccion json grande
				case 3:

					linkJson = TaxiTripsManager.DIRECCION_LARGE_JSON;
					break;
				}

				//Memoria y tiempo
				long memoryBeforeCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long startTime = System.nanoTime();

				if(linkJson.equals(TaxiTripsManager.DIRECCION_LARGE_JSON))
				{
					System.out.println(linkJson);
					File arch = new File(linkJson);
					if ((arch.exists())) 
					{
						File[] files = arch.listFiles();
						for (File file : files) 
						{
							if(file.isFile())
							{
								System.out.println("./data/taxi-trips-wrvz-psew-subset-large/"+file.getName());
								Controller.cargarSistema("./data/taxi-trips-wrvz-psew-subset-large/"+file.getName());
							}
						}
					}
				}
				else
				{
					Controller.cargarSistema(linkJson);
				}

				//Tiempo en cargar
				long endTime = System.nanoTime();
				long duration = (endTime - startTime)/(1000000);

				//Memoria usada
				long memoryAfterCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println("Tiempo en cargar: " + duration + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase1 - memoryBeforeCase1)/1000000.0) + " MB");

				break;

				//1A	
case 2: //1A
				
				//id zona
				System.out.println("Ingrese el id de la zona");
				String zonaIDReq1A = sc.next();
				int idZona = 0;
				try {
					idZona = Integer.parseInt(zonaIDReq1A);
				}
				catch (Exception e) {
					System.err.println("id invalido");
					break;
				}

				//nombre compania
				System.out.println("Ingrese el nombre de la compania");
				sc.nextLine();
				String companiaReq1A = sc.nextLine();
				
				//Req 1A
				// Mostrar el Id del (de los) taxi(s) y fecha/hora de inicio de sus servicios iniciando en la zona dada
				LinkedSimpleList<TaxiConServicios> listaTaxis = (LinkedSimpleList<TaxiConServicios>) Controller.R1A(idZona, companiaReq1A);
				TaxiConServicios actual1A= null;
				for(int i=0; i<listaTaxis.size();i++){
					actual1A= listaTaxis.get(i);
					actual1A.print();
					
				}
				
				break;

			case 3: //2A

				//Duracion de la consulta
				System.out.println("Ingrese la duracion de consulta (segundos)");
				String duracionS = sc.next();
				int duracion = 0;
				try {
					duracion = Integer.parseInt(duracionS);
				} catch (Exception e) {
					System.err.println("Duracion invalida");
					break;
				}

				// Req 2A
				LinkedSimpleList<Servicio> listaServicios = (LinkedSimpleList<Servicio>) Controller.R2A(duracion);
				Servicio actual2A= null;
				for(int i=0; i<listaServicios.size();i++){
					actual2A= listaServicios.get(i);
					System.out.println("IdTaxi: " + actual2A.getTaxiId()
			         + "  IdServicio: "+ actual2A.getTripId()
			         + "  Duracion: "+ actual2A.getTripSeconds());
					
				}
				
			
				break;

			case 4: //1B

				//Distancia minima
				double distanciaMinima = 0;
				System.out.println("Ingrese la distancia minima (millas)");
				String distMinReq1B = sc.next();
				
				try {
					distanciaMinima = Double.parseDouble(distMinReq1B);
				} catch (Exception e) {
					System.err.println("Distancia invalida");
					break;
				}

				//Distancia maxima
				double distanciaMaxima = 0;
				System.out.println("Ingrese la distancia maxima (millas)");
				String distMaxReq1B= sc.next();
				
				try {
					distanciaMaxima = Double.parseDouble(distMaxReq1B);
				} catch (Exception e) {
					System.err.println("Distancia invalida");
					break;
				}

				if ( distanciaMinima > distanciaMaxima ) {
					System.err.println("Rango de distancias invalido");
					break;
				}

				// Req 1B
				LinkedSimpleList<Servicio> listaServicios2 = (LinkedSimpleList<Servicio>) Controller.R1B(distanciaMinima, distanciaMaxima);
				for(int i = 0; i<listaServicios2.size() ;i++){
					System.out.println("IdTaxi: " + listaServicios2.get(i).getTaxiId()
							         + "  IdServicio: "+ listaServicios2.get(i).getTripId()
							         + "  Distancia: "+ listaServicios2.get(i).getTripMiles());
				}

				break;

			case 5: //2B

				//idZonaRecogida
				System.out.println("Ingrese el id de la zona de recogida");
				String idZonaRecogidaReq2B = sc.next();
				int idZonaRecogida = 0;
				try {
					idZonaRecogida = Integer.parseInt(idZonaRecogidaReq2B);
				}
				catch (Exception e) {
					System.err.println("id invalido");
					break;
				}

				//idZonaTerminacion
				System.out.println("Ingrese el id de la zona de terminacion");
				String idZonaTerminacionReq2B = sc.next();
				int idZonaTerminacion = 0;
				try {
					idZonaTerminacion = Integer.parseInt(idZonaTerminacionReq2B);
				}
				catch (Exception e) {
					System.err.println("id invalido");
					break;
				}
				
				//Fecha Inicial
				System.out.println("Ingrese la fecha inicial (Ej : 2017-02-01)");
				String fechaInicialReq2B = sc.next();
				
				//Hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00.000)");
				String horaInicialReq2B = sc.next();
				
				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 2017-02-01)");
				String fechaFinalReq2B = sc.next();
				
				//hora final
				System.out.println("Ingrese la hora final (Ej: 09:00:00.000)");
				String horaFinalReq2B = sc.next();

				// Req 2B
				IList<Servicio> listaServicios3 = Controller.R2B(idZonaRecogida, idZonaTerminacion, fechaInicialReq2B, fechaFinalReq2B, horaInicialReq2B, horaFinalReq2B);
				for(Servicio s : listaServicios3){
					System.out.println("Id Trip: "+ s.getTripId());
					System.out.println("  Zona recogida: " + s.getPickupZone());
					System.out.println("  Zona terminacion: " + s.getDropOffZone());
					try {
						System.out.println("  Hora inicial: " + s.getStartTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				break;

			case 6: //1C
				
				TaxiConPuntos[] taxis = Controller.R1C();
				
				// TODO Si el conjunto ordenado tiene menos de 10 taxis, hay que mostrar todos taxis en orden ascendente de puntos. 
				//      Por cada taxi mostrar su Id y sus puntos
				//
				
				//      Si el conjunto ordenado tiene 10 taxis o mas, hay que mostrar los 5 primeros taxis y los 5 ultimos taxis resultado del ordenamiento. 
				//      Por cada taxi mostrar su Id y sus puntos
				TaxiConPuntos actual1C= null;
				if(taxis.length<10){
					
					for(int i=0; i<taxis.length;i++){
						actual1C= taxis[i];
						System.out.println("IdTaxi: " + actual1C.getTaxiId()
				         + "  PuntosTaxi: "+ actual1C.getPuntos());
				    }
				}else{
					for(int i=0; i<5;i++){
						actual1C= taxis[i];
						if(actual1C!=null){
						System.out.println("IdTaxi: " + actual1C.getTaxiId()
				         + "  PuntosTaxi: "+ actual1C.getPuntos());
						}
				    }
					for(int i=taxis.length-1; i>taxis.length-6;i--){
						actual1C= taxis[i];
						if(actual1C!=null){
							System.out.println("IdTaxi: " + actual1C.getTaxiId()
					         + "  PuntosTaxi: "+ actual1C.getPuntos());
						}
				    }
				}
				break;

			case 7: //2C
				
				System.out.println("Ingrese el id del taxi");
				String taxiIDReq2C = sc.next();

				
				System.out.println("Ingrese la cantidad X de millas");
				String millasReq2C = sc.next();
				double millas = 0;
				try
				{
					millas = Double.parseDouble(millasReq2C);
				}
				catch(Exception e)
				{
					System.out.println("Cantidad invalida");
				}

				
				System.out.println("Ingrese la latitud de referencia");
				String latitudReq2C = sc.next();
				double latitud = 0;
				try
				{
					 latitud = Double.parseDouble(latitudReq2C);
				}
				catch(Exception e)
				{
					System.out.println("Latitud invalida");
				}
				
				System.out.println("Ingrese la longitud de referencia");
				String longitudReq2C = sc.next();
				double longitud = 0;
				try
				{
					 longitud = Double.parseDouble(longitudReq2C);
				}
				catch(Exception e)
				{
					System.out.println("Latitud invalida");
				}

				// Req 2C
				IList<Servicio> servicios2C = Controller.R2C(taxiIDReq2C, millas, latitud, longitud);
     			
				for(Servicio s : servicios2C)
				{
					System.out.println();
					System.out.println("Servicio: " + s.getTripId());
					//TODO imprimir la latitud y la longitud de los servicios
					System.out.println("  (Lat: " + s.getPickupLatitud() + ", Long: " + s.getPickupLongitud() + " )");
					System.out.println("  Distancia (millas) a la referencia: " + "�<calcular>?");
				}
			
				break;

			case 8: //3C
				
				
				//fecha 
				System.out.println("Ingrese la fecha (Ej : 2017-02-01)");
				String fecha = sc.next();

				//hora 
				System.out.println("Ingrese la hora de inicio con minutos arbitrarios en rango [0, 59] (Ej: 09:25:00.000)");
				String hora = sc.next();

				// Req 3C
				LinkedSimpleList<Servicio> servicios3C = (LinkedSimpleList<Servicio>) Controller.R3C(fecha, hora);
     			Servicio s=null;
				for(int i=0; i< servicios3C.size();i++)
				{
					s= servicios3C.get(i);
					System.out.println();
					System.out.println("Servicio: " + s.getTripId());
					System.out.println("  Taxi: " + s.getTaxiId());
					System.out.println("  Zona recogida: " + s.getPickupZone());
					System.out.println("  Zona terminacion: " + s.getDropOffZone());
					try {
						System.out.println("  Fecha/Hora inicial: " + s.getStartTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				break;

			case 9: 
				fin=true;
				sc.close();
				break;

			}
		}
	}
	/**
	 * Menu 
	 */
	private static void printMenu() //
	{
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Proyecto 2----------------------");
		System.out.println("Iniciar la Fuente de Datos a Consultar :");
		System.out.println("1. Cargar toda la informacion del sistema de una fuente de datos (small, medium o large).");

		System.out.println("\nParte A:\n");
		System.out.println("2. Obtener el taxi con mas servicios en una zona dada para una determinada compania (1A)");
		System.out.println("3. Obtener los servicios con duracion en el minuto al que corresponde una duracion de consulta (en segundos) (2A)");
		
		System.out.println("\nParte B:\n");
		System.out.println("4. Obtener los servicios cuya distancia recorrida esta en un rango de distancia en millas (1B)");
		System.out.println("5. Obtener los servicios que iniciaron en una zona de recogida y terminaron en una zona de terminacion entre una fecha/hora inicial y una fecha/Hora final (2B)");
		

		System.out.println("\nParte C:\n");
		System.out.println("6. Ordenamiento de taxis utilizando un sistema de puntos (1C)");
		System.out.println("7. Informar las localizaciones geograficas (latitud,longitud) de los servicios iniciados por un taxi que estan dentro de la zona a X millas de una localizacion geografica (latitud, longitud) dada (2C)");
		System.out.println("8. Obtener los servicios que inician en el rango de 15 minutos mas cercano a una fecha/hora dadas (con minutos arbitrarios en rango [0, 59]), saliendo de una zona y terminado en otra zona (3C)");
		System.out.println("9. Salir");
		System.out.println("Ingrese el numero de la opcion seleccionada y presione <Enter> para confirmar: (e.g., 1):");

	}

	private static void printMenuCargar()
	{
		System.out.println("-- Que fuente de datos desea cargar?");
		System.out.println("-- 1. Small");
		System.out.println("-- 2. Medium");
		System.out.println("-- 3. Large");
		System.out.println("-- Ingrese el numero de la fuente a cargar y presione <Enter> para confirmar: (e.g., 1)");
	}

}