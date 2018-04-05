package model.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import api.ITaxiTripsManager;
import model.data_structures.HeapBinario;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.ISymbolTable;
import model.data_structures.ILinkedList;
import model.data_structures.LinkedSimpleList;
import model.data_structures.Queue;
import model.data_structures.RedBlackBST;
import model.data_structures.SimpleNodeSymbolTable;
import model.data_structures.Stack;
import model.data_structures.SymbolTableLP;
import model.data_structures.SymbolTableSC;
import model.logic.utils.ComparatorCompanhiaNumServicios;
import model.logic.utils.ComparatorTaxiPorIdAlfabeticamente;
import model.logic.utils.HeapSort;
import model.logic.utils.Merge;
import model.vo.Compania;
import model.vo.CompaniaServicios;
import model.vo.CompaniaTaxi;
import model.vo.FechaServicios;
import model.vo.InfoTaxiRango;
import model.vo.RangoDistancia;
import model.vo.RangoFechaHora;

import model.vo.Servicio;
import model.vo.ServiciosValorPagado;
import model.vo.Taxi;
import model.vo.ZonaServicios;
import sun.text.normalizer.SymbolTable;

public class TaxiTripsManager implements ITaxiTripsManager 
{
	public static final String DIRECCION_SMALL_JSON = "./data/taxi-trips-wrvz-psew-subset-small.json";
	public static final String DIRECCION_MEDIUM_JSON = "./data/taxi-trips-wrvz-psew-subset-medium.json";
	public static final String DIRECCION_LARGE_JSON = "./data/taxi-trips-wrvz-psew-subset-large";

	private Servicio[] servicios;
	
	
	//Declaracion de estructuras de datos
	
	private RedBlackBST<String, LinkedSimpleList<Taxi>> arbolCompanhias;
	
	private SymbolTableSC<Integer, Servicio> hashTableServiciosArea;
	
	private SymbolTableSC<RangoFechaHora, Servicio> hashTableServiciosDuracion;
	
	private RedBlackBST<Integer, LinkedSimpleList<Servicio>> arbolServiciosXDistancia;
	
	private SymbolTableSC<String, Servicio> hashTableServiciosZonasXY;
	
	private RedBlackBST<Date, LinkedSimpleList<Servicio>> arbolServiciosOrdenCrono;
	
	private HeapSort<Servicio> heapSort;
	
	private int numElementos;
	
	
	
	@Override //1C
	public boolean cargarSistema(String direccionJson) 
	{
		//Inicializa las estructuras
		
		if(arbolCompanhias==null){
			arbolCompanhias= new RedBlackBST<>();
		}
		if(hashTableServiciosArea==null){
			hashTableServiciosArea= new SymbolTableSC<>(101);
		}
		if(hashTableServiciosDuracion==null){
			hashTableServiciosDuracion= new SymbolTableSC<>(101);
		}
		if(arbolServiciosXDistancia==null){
			arbolServiciosXDistancia= new RedBlackBST<>();
		}
		if(hashTableServiciosZonasXY==null){
			hashTableServiciosZonasXY= new SymbolTableSC<>(101);
		}
		if(arbolServiciosOrdenCrono==null){
			arbolServiciosOrdenCrono= new RedBlackBST<>();
		}
		if(heapSort==null){
			heapSort= new HeapSort<>();
		}
		servicios= new Servicio[101];
		
		//Variables temporales usadas al cargar
		Servicio servicioActual=null;
		Taxi taxiActual=null;
		numElementos=0;
		
		
		
		
		JsonParser parser = new JsonParser();
		int pos=1;
		int suma = 0;
		try {
			
			/* Cargar todos los JsonObject (servicio) definidos en un JsonArray en el archivo */
			JsonArray arr= (JsonArray) parser.parse(new FileReader(direccionJson));
			/* Tratar cada JsonObject (Servicio taxi) del JsonArray */
			for (int i = 0; arr != null && i < arr.size(); i++)
			{
				JsonObject obj= (JsonObject) arr.get(i);
				
				String company = obj.get("company") == null? "Independent Owner": obj.get("company").getAsString();
				company = company.replaceAll("\\P{L}+", "");

				/* Obtener la propiedad taxi_id de un taxi (String)*/
				String taxi_id = obj.get("taxi_id") == null? "NaN": obj.get("taxi_id").getAsString();


				String trip_id = obj.get("trip_id") == null? "NaN": obj.get("trip_id").getAsString();
				double dropoff_census_tract = obj.get("dropoff_census_tract") == null? 0: obj.get("dropoff_census_tract").getAsDouble();
				double dropoff_centroid_latitude = obj.get("dropoff_centroid_latitude") == null? 0: obj.get("dropoff_centroid_latitude").getAsDouble();

				JsonElement drop = obj.get("dropoff_centroid_location");
				String dropoff_type = drop == null? "NaN": drop.getAsJsonObject().get("type").getAsString();
				double droplat = drop == null? 0: drop.getAsJsonObject().get("coordinates").getAsJsonArray().get(0).getAsDouble();
				double droplong = drop == null? 0: drop.getAsJsonObject().get("coordinates").getAsJsonArray().get(1).getAsDouble();

				double dropoff_centroid_longitude = obj.get("dropoff_centroid_longitude") == null? 0: obj.get("dropoff_centroid_longitude").getAsDouble();
				int dropoff_community_area = obj.get("dropoff_community_area") == null? 0: obj.get("dropoff_community_area").getAsInt();

				double extras = obj.get("extras") == null? 0: obj.get("extras").getAsDouble();
				double fare = obj.get("fare") == null? 0: obj.get("fare").getAsDouble();
				String payment_type = obj.get("payment_type") == null? "NaN": obj.get("payment_type").getAsString();

				double pickup_census_tract = obj.get("pickup_census_tract") == null? 0: obj.get("pickup_census_tract").getAsDouble();
				double pickup_centroid_latitude = obj.get("pickup_centroid_latitude") == null? 0: obj.get("pickup_centroid_latitude").getAsDouble();

				JsonElement pick = obj.get("pickup_centroid_location");
				String pickup_type = pick == null? "NaN": pick.getAsJsonObject().get("type").getAsString();
				double picklat = pick == null? 0: pick.getAsJsonObject().get("coordinates").getAsJsonArray().get(0).getAsDouble();
				double picklong = pick == null? 0: pick.getAsJsonObject().get("coordinates").getAsJsonArray().get(1).getAsDouble();

				double pickup_centroid_longitude = obj.get("pickup_centroid_longitude") == null? 0: obj.get("pickup_centroid_longitude").getAsDouble();
				int pickup_community_area = obj.get("pickup_community_area") == null? 0: obj.get("pickup_community_area").getAsInt();

				String taxiAutor = taxi_id;
				double tips = obj.get("tips") == null? 0: obj.get("tips").getAsDouble();
				double tolls = obj.get("tolls") == null? 0: obj.get("tolls").getAsDouble();

				String trip_end_timestamp = obj.get("trip_end_timestamp") == null? "0000-00-00T00:00:00.000": obj.get("trip_end_timestamp").getAsString();
				double trip_miles = obj.get("trip_miles") == null? 0: obj.get("trip_miles").getAsDouble();
				int trip_seconds = obj.get("trip_seconds") == null? 0: obj.get("trip_seconds").getAsInt();
				String trip_start_timestamp = obj.get("trip_start_timestamp") == null? "0000-00-00T00:00:00.000": obj.get("trip_start_timestamp").getAsString();
				double trip_total = obj.get("trip_total") == null? 0: obj.get("trip_total").getAsDouble();
				
				
				servicioActual= new Servicio(trip_id, dropoff_census_tract, dropoff_centroid_latitude, dropoff_type, droplat, droplong, dropoff_centroid_longitude, dropoff_community_area, extras, fare, payment_type, pickup_census_tract, pickup_centroid_latitude, pickup_type, picklat, picklong, pickup_centroid_longitude, pickup_community_area, taxiAutor, tips, tolls, trip_end_timestamp, trip_miles, trip_seconds, trip_start_timestamp, trip_total, company);
				taxiActual= new Taxi(taxi_id, company);
				
				
				
				LinkedSimpleList<Taxi> listArbol=arbolCompanhias.get(company);
				
				if(listArbol==null){
					listArbol= new LinkedSimpleList<>();
					listArbol.add(taxiActual);
					arbolCompanhias.put(company, listArbol);
				}else{
					Taxi temp= estaAgregadaCompan(listArbol, taxi_id);
					if(temp==null){
					listArbol.add(taxiActual);
					}
				}
				
				hashTableServiciosArea.put(pickup_community_area, servicioActual);
				
				sobrePasoCarga();
				servicios[pos]=servicioActual;
				numElementos++;
				pos++;
				
				
	}
			
			
		}
		catch (JsonIOException e1 ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (JsonSyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		System.out.println("Se Cargaron :"+ numElementos + " servicios.");
		
		for(int i = 1; i <= numElementos; i+=100) {
			
				System.out.println("ID SERVICE " +i  +"  : "+ servicios[i].getTripId());
			
		}
			

			
			
			
	return true;
	
	}
	
	// METODOS PARA CARGAR LA INFORMACIÓN
	
	
			public void sobrePasoCarga() {
				// TODO Auto-generated method stub
				double porcentaje= (numElementos*100)/servicios.length;
				if(porcentaje>80){
					int sizeTemp= servicios.length;
					Servicio[] temp2= servicios;
					int size= sizeTemp+1000;
					
					servicios=  new Servicio[size];
					for(int i=1; i<sizeTemp;i++){
						servicios[i]= temp2[i];
					}
					
				}
				
				
			}
			
			
			public Taxi estaAgregadoTaxi(Taxi[] a, String id){
				Taxi actual;
				for(int i=1; i<a.length;i++){
					actual= (Taxi) a[i];
					
					if(actual!=null&&actual.getTaxiId().equals(id)){
						return actual;
					}
				}
				return null;
			}
			
			
			
			
			
			public Taxi estaAgregadoTaxi(LinkedSimpleList<Taxi> a, String id){
				
				Taxi actual= a.get(0);
				
				while(actual!=null){
					if(actual.getTaxiId().equals(id)){
						return actual;
					}
					actual= a.next();
				}
				return null;
			}
			
			public Compania estaAgregadaCompanEnHeap(HeapBinario<Compania> p, String pCompanhia){
				
				Compania actual;
				for(int i=1; i<p.size(); i++){
					actual=p.get(i);
					if(actual.getNombre().equals(pCompanhia)){
						return actual;
					}
					
				}
				return null;
			}
			//Proyecto 2
			public Taxi estaAgregadaCompan(LinkedSimpleList<Taxi> p, String pId){
				
				Taxi actual;
				for(int i=0; i<p.size(); i++){
					actual= p.get(i);
					if(actual.getTaxiId().equals(pId)){
						return actual;
					}
					
				}
				return null;
			}
			

			// METODOS DE REQUERIMIENTOS
			
			// 1A PROYECTO 2
			@Override
			public LinkedSimpleList<Servicio> darServiciosEnAreaOrdenCronologico(int pArea) {
				// TODO Auto-generated method stub
				//Crea la tabla Hash y se adicionan elementos
				Servicio temp=null;
				Servicio actual;
				
				SymbolTableSC<Integer, Servicio> serviciosReq1b= new SymbolTableSC(101);
				
				int keyCurrent= 0;
			
				for(int i=0;i<servicios.length;i++){
					actual= servicios[i];
					
					if(actual!=null){
						
						//Obtiene llave por la distancia.
						keyCurrent= (int) actual.pickup_community_area;
						serviciosReq1b.put(keyCurrent,actual);
						
					}
					
				}
				LinkedSimpleList<Servicio> rta= new LinkedSimpleList<>();
				
				LinkedSimpleList<SimpleNodeSymbolTable<Integer, Servicio>> temp2=(LinkedSimpleList<SimpleNodeSymbolTable<Integer, Servicio>>) serviciosReq1b.getNode(pArea);
				
				if(temp2!=null&&temp2.size()>0){
				SimpleNodeSymbolTable<Integer, Servicio> actual2=null;
				
					for(int i=0;i<temp2.size();i++){
						actual2= temp2.get(i);
						
						rta.add(actual2.getElement());
						
					}
				}
				//Retorna de la tabla Hash el conjunto de consulta.
				return rta;
			}

			// 1B
			@Override
			public LinkedSimpleList<Servicio> darServiciosPorDistanciaRecorridaMillas(String dis){
				
				
				//Crea la tabla Hash CON LINEAR PROBING y se adicionan elementos
				Servicio actual;
				LinkedSimpleList<Servicio> temp;
				ISymbolTable<Integer, LinkedSimpleList<Servicio>> serviciosReq1b= new SymbolTableLP(101);
				
				double keySearch= (Double.parseDouble(dis));
				int keyCurrent= 0;
				
				for(int i=0;i<servicios.length;i++){
					actual= servicios[i];
					
					if(actual!=null){
						//Obtiene llave por la distancia.
						keyCurrent= obtenerKeyAPartirDeRangoPeqDistancia(actual.getTripMiles());
						temp= serviciosReq1b.get(keyCurrent);
					
						if(temp==null){
								temp= new LinkedSimpleList<>();
								temp.add(actual);
								serviciosReq1b.put(keyCurrent,temp);
							}
						else{
							temp.add(actual);
						}
					}
					
				}
				//Retorna de la tabla Hash el conjunto de consulta.
				return serviciosReq1b.get((int) keySearch);
			}
			
			public int obtenerKeyAPartirDeRangoPeqDistancia(double dis){
				
				if((dis%1.0)==(0.0)&&dis!=0){
					return (int) (dis-0.1);
				}
				return (int) dis;
				
			}
			//Retorna la key, cuando el requerimiento 2, pedia que fueran por grupos de 10.
			public int obtenerKeyAPartirDeDistancia(int dis){
				
				if(String.valueOf(dis).length()==1){
					return 1;
				}
				else{
					int temp= dis;
					int cont=1;
					
					while(temp>=10){
						cont++;
						temp=temp-10;
					}
					if(temp==0){
						return cont-1;
					}
					else{
						return cont;
					}
					
				}
			
				
			}
			
	
	

}
