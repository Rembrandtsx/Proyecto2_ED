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
			case 2:
				
				System.out.println("Ingrese el Id de la zona");
				String zona1A=sc.next();
				
				int area= Integer.parseInt(zona1A);
				
				LinkedSimpleList<Servicio> servicesReq1= Controller.darServiciosEnAreaOrdenCronologico(area);
					
				
				Servicio actual= null;
				if(servicesReq1!=null){
					System.out.println("Se encontraron "+servicesReq1.size()+" servicios.");
					
					for(int i=0; i<servicesReq1.size();i++){
						actual= servicesReq1.get(i);
						if(actual!=null){
							System.out.println("--------------------------------------------------------------------------------------------");
							System.out.println("Id Trip : "+ actual.getTripId());
							System.out.println("Seconds : "+ actual.getTripSeconds());
							try {
								System.out.println("Date Start : "+ actual.getStartTime().toString());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("--------------------------------------------------------------------------------------------");
						
							
						}
					}
				if(servicesReq1.size()==0){
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println("No hay servicios en esta area");
					System.out.println("--------------------------------------------------------------------------------------------");
				
					}
				}
				else{
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println("No hay servicios en esta area");
					System.out.println("--------------------------------------------------------------------------------------------");
				
				}
				
				break;
				
			case 3: //2A
				System.out.println("Ingrese la distancia de consulta.");
				String distanciaAD=sc.next();
				
				LinkedSimpleList<Servicio> req1B=Controller.darServiciosPorDistanciaRecorridaMillas(distanciaAD);
				//TODO
				//Mostrar la informacion de acuerdo al enunciado
				
				//Calcula Rango conulta
				
				double dis=Double.parseDouble(distanciaAD);
				
				int contadorDis=1;
				
				while(dis>=1.0){
					dis=dis-1;
					contadorDis++;
					if(dis<=0){
						contadorDis--;
					}
				}
				//Imprime respuesta metodo.
				if(req1B!=null){
				System.out.println("--------------------------------------------------------------------------------------------");
				System.out.println("Conjunto de Serv. distancia (millas) : Mayores a "+  (contadorDis-1) + " y menores a " + contadorDis );
				System.out.println("Numero de servicios : "+ req1B.size());
				
				req1B.listing();
				Servicio servi= req1B.getCurrent();
				
				
				for(int j=0; j<req1B.size();j++){
					
					if(servi!=null){
						
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println("Id Trip : "+ servi.getTripId());
					System.out.println("Id taxi : "+ servi.getTaxiId());
					System.out.println("Total trip : "+ servi.getTripTotal());
					System.out.println("Miles : "+ servi.getTripMiles());
					System.out.println("Seconds : "+ servi.getTripSeconds());
					System.out.println("--------------------------------------------------------------------------------------------");
					
						}
						
					servi= req1B.next();
					
				}
				System.out.println("--------------------------------------------------------------------------------------------");
				System.out.println("Conjunto de Serv. distancia (millas) : De "+  (((contadorDis*10)-9)==1?0:((contadorDis*10)-9)) + " a "+((contadorDis*10)) );
				System.out.println("Numero de servicios : "+ req1B.size());
				
				}
				else{
					System.out.println("--------------------------------------------------------------------------------------------");
					System.out.println("No hay Servicios en este rango de Distancia.");
					System.out.println("--------------------------------------------------------------------------------------------");
					
					
				}
				break;
				
			case 4: //salir
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
		System.out.println("---------------------TALLER 6----------------------");
		System.out.println("Cargar data (1C):");
		System.out.println("1. Cargar toda la informacion dada una fuente de datos (small,medium, large). Debe ingresar el rango de "+ "\n" + "fecha/hora que desea cargar.");

		
		System.out.println("2. Dada un área de comunidad, retornar los servicios que se inician en esa área ordenados cronológicamente." + "\n"
				+ " Se muestra su identificador y su tiempo de llegada. (1A) ");
		System.out.println("3. Agrupar los servicios por la distancia recorrida en millas. (2A)");
		System.out.println("4. Salir.");
	
	}

	private static void printMenuCargar()
	{
		System.out.println("-- Que fuente de datos desea cargar?");
		System.out.println("-- 1. Small");
		System.out.println("-- 2. Medium");
		System.out.println("-- 3. Large");
		System.out.println("-- Type the option number for the task, then press enter: (e.g., 1)");
	}

}
