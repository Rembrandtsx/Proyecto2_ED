package model.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import api.ITaxiTripsManager;
import model.data_structures.ArrayList;
import model.data_structures.HeapBinario;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.ISymbolTable;
import model.data_structures.ILinkedList;
import model.data_structures.IList;
import model.data_structures.LinkedSimpleList;
import model.data_structures.Queue;
import model.data_structures.RedBlackBST;
import model.data_structures.SeparateChainingHashST;
import model.data_structures.SimpleNodeSymbolTable;
import model.data_structures.Stack;
import model.data_structures.SymbolTableLP;
import model.data_structures.SymbolTableSC;
import model.logic.utils.ComparatorCompanhiaNumServicios;
import model.logic.utils.ComparatorServicioPorFechaHora;
import model.logic.utils.ComparatorTaxiPorIdAlfabeticamente;
import model.logic.utils.ComparatorTaxiPorPuntos;
import model.logic.utils.HeapSort;
import model.logic.utils.Merge;
import model.logic.utils.Ordenamiento;
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
import model.vo.TaxiConPuntos;
import model.vo.TaxiConServicios;
import model.vo.ZonaServicios;
import sun.text.normalizer.SymbolTable;

public class TaxiTripsManager implements ITaxiTripsManager 
{
	public static final String DIRECCION_SMALL_JSON = "./data/taxi-trips-wrvz-psew-subset-small.json";
	public static final String DIRECCION_MEDIUM_JSON = "./data/taxi-trips-wrvz-psew-subset-medium.json";
	public static final String DIRECCION_LARGE_JSON = "./data/taxi-trips-wrvz-psew-subset-large";

	private Servicio[] servicios;
	
	
	//Declaracion de estructuras de datos
	private ArrayList<TaxiConPuntos> taxis;
	
	private RedBlackBST<String, LinkedSimpleList<TaxiConServicios>> arbolCompanhias;
	
	private SymbolTableSC<Integer, Servicio> hashTableServiciosArea;
	
	private SymbolTableSC<Integer, Servicio> hashTableServiciosDuracion;
	
	private RedBlackBST<Double, LinkedSimpleList<Servicio>> arbolServiciosXDistancia;
	
	private SymbolTableLP<String, RedBlackBST<String, Servicio>> hashTableServiciosZonasXY;
	
	private SeparateChainingHashST<Double, RedBlackBST<String , ArrayList<Servicio>>> hashTable2c;
	
	private RedBlackBST<Date, LinkedSimpleList<Servicio>> arbolServiciosOrdenCrono;
	
	private HeapSort<Servicio> heapSort;
	
	private int numElementos;
	
	
	
	
	
	
	private LinkedSimpleList<Servicio> serviciosTotal;
	private double sumaLatServicios;
	private double sumaLongServicios;
	private int numServiciosTotal;
	private ArrayList<Servicio> servicios2 = new ArrayList<>();
	
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
			hashTableServiciosZonasXY= new SymbolTableLP<>(101);
		}
		if(arbolServiciosOrdenCrono==null){
			arbolServiciosOrdenCrono= new RedBlackBST<>();
		}
		if(hashTable2c==null) {
			hashTable2c = new SeparateChainingHashST<>();
		}
		if(heapSort==null){
			heapSort= new HeapSort<>();
		}
		if(taxis==null){
			taxis= new ArrayList<>();
		}
		if(serviciosTotal==null){
			serviciosTotal= new LinkedSimpleList<>();
		}
		servicios= new Servicio[101];
		
		//Variables temporales usadas al cargar
		Servicio servicioActual=null;
		Taxi taxiActual=null;
		numElementos=0;
		
		
		
		
		JsonParser parser = new JsonParser();
		int pos=0;
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
				
				
				TaxiConServicios taxiConServiciosActual= new TaxiConServicios(taxi_id, company);
				
				LinkedSimpleList<TaxiConServicios> listArbol=arbolCompanhias.get(company);
				
				
				if(listArbol==null){
					
					listArbol= new LinkedSimpleList<TaxiConServicios>();
					taxiConServiciosActual.agregarServicio(servicioActual);
					taxiConServiciosActual.setServiciosHashTable(pickup_community_area, servicioActual);
					listArbol.add(taxiConServiciosActual);
					arbolCompanhias.put(company, listArbol);
					
				}else{
					TaxiConServicios temp= estaAgregadaCompan(listArbol, taxi_id);
					
					if(temp==null){
						taxiConServiciosActual.agregarServicio(servicioActual);
						taxiConServiciosActual.setServiciosHashTable(pickup_community_area, servicioActual);
						listArbol.add(taxiConServiciosActual);
						
					}else{
						temp.agregarServicio(servicioActual);
						temp.setServiciosHashTable(pickup_community_area, servicioActual);
					}
				}
				//Estructuras parte A
				hashTableServiciosArea.put(pickup_community_area, servicioActual);
				//Se agrupan los servicios por duracion de 60 sg
				hashTableServiciosDuracion.put((trip_seconds%60==0)?((int)trip_seconds/60):((int)trip_seconds/60)+1, servicioActual);
				
				sobrePasoCarga();
				servicios[pos]=servicioActual;
				
				serviciosTotal.add(servicioActual);
				
				if(estaAgregadoTaxiConPuntos(taxis, taxi_id)==null){
					TaxiConPuntos t= new TaxiConPuntos(taxi_id, company);
					if(trip_miles>0&&trip_total>0){
					t.setMillas(trip_miles);
					t.setDinero(trip_total);
					t.aumentarNumServicios();
					taxis.add(t);
					}
				}
				else{
					TaxiConPuntos temp= (TaxiConPuntos) estaAgregadoTaxiConPuntos(taxis, taxi_id);
					if(trip_miles>0&&trip_total>0){
					temp.setDinero(trip_total);
					temp.setMillas(trip_miles);
					temp.aumentarNumServicios();
					}
				}
				
				numElementos++;
				pos++;
				
				
				//1B------------------------------------------------
				
				BigDecimal bd = new BigDecimal(trip_miles);
				bd = bd.setScale(1, RoundingMode.HALF_UP);
				
				
				Double trip_miles1b= bd.doubleValue();
								
				
				
				if(arbolServiciosXDistancia.contains(trip_miles1b)) {
					LinkedSimpleList<Servicio> lista = arbolServiciosXDistancia.get(trip_miles1b);
					lista.add(servicioActual);
				}else {
					LinkedSimpleList<Servicio> lista = new LinkedSimpleList<Servicio>();
					lista.add(servicioActual);
					arbolServiciosXDistancia.put(trip_miles1b, lista);
				}
				//--------------------------------------------------
				
				//2B------------------------------------------
				
				String keyArbol = (pickup_community_area+"-"+dropoff_community_area);
				
				if (hashTableServiciosZonasXY.contains(keyArbol)) {
					hashTableServiciosZonasXY.get(keyArbol).put(trip_start_timestamp, servicioActual);
					
					
				}else {
					RedBlackBST<String, Servicio> arbolXY = new RedBlackBST<String, Servicio>();
					arbolXY.put(trip_start_timestamp, servicioActual);
					
					hashTableServiciosZonasXY.put(keyArbol, arbolXY);
				}
				
				
				
				
				
				
				
				
				//--------------------------------------------------
				
				
				//2C------------------------------------------
				

				if(pickup_centroid_latitude != 0 && pickup_centroid_longitude != 0)
				{
					sumaLatServicios += pickup_centroid_latitude;
					sumaLongServicios += pickup_centroid_longitude;
					numServiciosTotal++;
					System.out.println(servicioActual.id);
					
					servicios2.add(servicioActual);
				}
				
				
				
				//--------------------------------------------------
				
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
			
				
				
			
			
			public TaxiConPuntos estaAgregadoTaxiConPuntos(ArrayList<TaxiConPuntos> a, String id){
				TaxiConPuntos actual;
				for(int i=1; i<a.size();i++){
					actual=  a.get(i);
					
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
			public TaxiConServicios estaAgregadaCompan(LinkedSimpleList<TaxiConServicios> p, String pId){
				
				TaxiConServicios actual;
				for(int i=0; i<p.size(); i++){
					actual= p.get(i);
					if(actual.getTaxiId().equals(pId)){
						return actual;
					}
					
				}
				return null;
			}
			

			// METODOS DE REQUERIMIENTOS
			
			@Override
			public IList<TaxiConServicios> A1TaxiConMasServiciosEnZonaParaCompania(int zonaInicio, String compania) {
				// TODO Auto-generated method stub
				compania= compania.replace(" ", "");
				
				LinkedSimpleList<TaxiConServicios> taxisCompanhia= arbolCompanhias.get(compania);
				
				TaxiConServicios actual= null;
				TaxiConServicios temporalA= null;
				int numSerActual= 0;
				
				int numSerMayor=0;
				LinkedSimpleList<TaxiConServicios> mayores= new LinkedSimpleList<TaxiConServicios>();
				
				if(taxisCompanhia!=null){
						for(int i=0; i<taxisCompanhia.size();i++){
							actual= taxisCompanhia.get(i);
							
							if(actual!=null&&actual.getMisServiciosHashTable().get(zonaInicio)!=null){
								
									numSerActual= actual.numeroServiciosXKeyHash(zonaInicio);
									
									if(numSerActual>numSerMayor){
										mayores= new LinkedSimpleList<TaxiConServicios>();
										temporalA= new TaxiConServicios(actual.getTaxiId(), actual.getCompania());
										temporalA.setServicios((LinkedSimpleList<Servicio>) actual.getMisServiciosHashTable().get(zonaInicio));
										mayores.add(temporalA);
										numSerMayor=numSerActual;
										numSerActual=0;
									}
									else if(numSerActual==numSerMayor){
										
										temporalA= new TaxiConServicios(actual.getTaxiId(), actual.getCompania());
										temporalA.setServicios((LinkedSimpleList<Servicio>) actual.getMisServiciosHashTable().get(zonaInicio));
										mayores.add(temporalA);
										
									}
							}
						}
				
				}
				return mayores;
			
			
				
			
			}




			@Override
			public IList<Servicio> A2ServiciosPorDuracion(int duracion) {
				// TODO Auto-generated method stub
				return hashTableServiciosDuracion.getList((duracion%60==0)?((int)duracion/60):((int)duracion/60)+1);
				
			}

			@Override
			public LinkedSimpleList<Servicio> B1ServiciosPorDistancia(double distanciaMinima, double distanciaMaxima) {
				
				BigDecimal bd = new BigDecimal(distanciaMaxima);
				bd = bd.setScale(1, RoundingMode.HALF_UP);
				distanciaMaxima= bd.doubleValue();
				
				BigDecimal dd = new BigDecimal(distanciaMinima);
				dd = dd.setScale(1, RoundingMode.HALF_UP);				
				distanciaMinima= dd.doubleValue();
				
				LinkedSimpleList<Double> keys = (LinkedSimpleList<Double>) arbolServiciosXDistancia.keys(distanciaMinima, distanciaMaxima);
				
				LinkedSimpleList<Servicio> listaRetorno = new LinkedSimpleList<Servicio>();
				for(int i=0 ; i <keys.size();i++) {
					
					listaRetorno.addAList(arbolServiciosXDistancia.get((Double) keys.get(i)));
				}
					
				
				
				
				
				return listaRetorno;
			}


			@Override
			public IList<Servicio> B2ServiciosPorZonaRecogidaYLlegada(int zonaInicio, int zonaFinal, String fechaI, String fechaF, String horaI, String horaF) {
				LinkedSimpleList<Servicio> lista = new LinkedSimpleList<Servicio>();
				String identificador = (zonaInicio+"-"+zonaFinal);
				RedBlackBST<String, Servicio> arbolXY = hashTableServiciosZonasXY.get(identificador);
				String fechaini= fechaI+"T"+horaI;
				String fechaFini= fechaF+"T"+horaF;
				
				LinkedSimpleList<String> llaves= (LinkedSimpleList<String>) arbolXY.keys(fechaini,fechaFini);
				for(int i = 0; i<llaves.size(); i++) {
				
					
					lista.add(arbolXY.get(llaves.get(i)));
					
				}
			
			
				
				return lista;
			}


			@Override
			public TaxiConPuntos[] R1C_OrdenarTaxisPorPuntos() {
				// TODO Auto-generated method stub
				ComparatorTaxiPorPuntos comparador= new ComparatorTaxiPorPuntos();
				TaxiConPuntos[] respuesta=  new TaxiConPuntos[taxis.size()];
				
				for(int i=0; i<taxis.size(); i++){
					respuesta[i]= taxis.get(i);
				}
				heapSort.heapSortAscendentemente(respuesta, comparador);
				
				return respuesta;
			}
			//------------------------------------------------------------------
			
			
			
			
			//------------------------ 2 C----------------------------------------
			
			public double[] getDistanceOfReference()
			{
				double[] distanceRef = new double[2];
				distanceRef[0] = (sumaLatServicios/numServiciosTotal);
				distanceRef[1] = (sumaLongServicios/numServiciosTotal);
				return distanceRef;
			}

			public double getDistanceHarv (double lat1, double lon1)
			{
				DecimalFormat format = new DecimalFormat("#.0");
				final int R = 6371*1000; // Radious of the earth in meters
				Double latDistance = Math.toRadians(getDistanceOfReference()[0]-lat1);
				Double lonDistance = Math.toRadians(getDistanceOfReference()[1]-lon1);
				Double a = Math.sin(latDistance/2) * Math.sin(latDistance/2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(getDistanceOfReference()[0])) * Math.sin(lonDistance/2) * Math.sin(lonDistance/2);
				Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
				Double distance = R * c * 0.000621371;
				Double finalDistance =  (Double.parseDouble(format.format(distance).replace(",", ".")));
				return finalDistance;
			}

			public ArrayList<Servicio> setHarvesianDistancesOfServices()
			{
				for (int i = 0; i < servicios2.size(); i++) 
				{
					servicios2.get(i).setHarvesianDistance(getDistanceHarv(servicios2.get(i).pickup_centroid_latitude, servicios2.get(i).pickup_centroid_longitude));
				}
				//OrdenatorP<Servicio> ordenador = new OrdenatorP<Servicio>();
				//ComparatorHarvesianDistance comparator = new ComparatorHarvesianDistance();
				//ordenador.ordenar(Ordenamientos.MERGE, true, comparator, servicios);

				return servicios2;
			}


			public void fillHarvesianHashTable()
			{
				ArrayList<Servicio> servicios = setHarvesianDistancesOfServices();
				for (int i = 0; i < servicios.size(); i++)
				{
					RedBlackBST<String , ArrayList<Servicio>> tree = hashTable2c.get(servicios.get(i).getHarvesianDistance());
					if(tree != null)
					{
						ArrayList<Servicio> listk = tree.get(servicios.get(i).getTaxiId());
						if(listk == null)
						{
							listk = new ArrayList<Servicio>();
							tree.put(servicios.get(i).getTaxiId(), listk);
							listk = tree.get(servicios.get(i).getTaxiId());
						}
						listk.add(servicios.get(i));
					}
					else
					{
						tree = new RedBlackBST<String,ArrayList<Servicio>>();
						ArrayList<Servicio> listk = new ArrayList<Servicio>();
						listk.add(servicios.get(i));
						tree.put(servicios.get(i).getTaxiId(), listk);
						listk = tree.get(servicios.get(i).getTaxiId());
						hashTable2c.put(servicios.get(i).getHarvesianDistance(), tree);
					}
				}

			}
			
			
			
			
			
			
			
			
			
			
			
			@Override
			public ArrayList<Servicio> R2C_LocalizacionesGeograficas(String taxiIDReq2C, double millasReq2C) {
				fillHarvesianHashTable();
				try
				{
					return hashTable2c.get(millasReq2C).get(taxiIDReq2C);
				}
				catch(Exception e)
				{
					System.err.println("No existen las millas brindadas.");
					System.out.println();
					return  new ArrayList<Servicio>();
				}
			}

			//---------------------------------------------------------------------
			
			
			
			
			
			
			@Override
			public IList<Servicio> R3C_ServiciosEn15Minutos(String fecha, String hora) {
				// TODO Auto-generated method stub
			
				RedBlackBST<Date,LinkedSimpleList<Servicio>> arbolConServiciosRango= new RedBlackBST<>();
				
				Date keyActual=null;
				
				LinkedSimpleList<Servicio> listaActual=null;
				
				Servicio actual= null;
				
				for(int i=0; i<numElementos;i++){
					actual= servicios[i];
					if(actual!=null){
					
						keyActual= obtenerKeyPorRango15M(actual.trip_start_timestamp);
						listaActual= arbolConServiciosRango.get(keyActual);
					
					if(listaActual==null){
						listaActual= new LinkedSimpleList<>();
						listaActual.add(actual);
						arbolConServiciosRango.put(keyActual, listaActual);
					}else{
						listaActual.add(actual);
						arbolConServiciosRango.put(keyActual, listaActual);
					}
					}
				}
				Date keyValorActual=obtenerKeyPorRango15M(fecha+"T"+hora);
				
				return arbolConServiciosRango.get(keyValorActual);
			}

			public Date obtenerKeyPorRango15M(String fechaActual ){
				
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		        
		        	String[] fechaPS= fechaActual.split("T");
		        	String[]  horaPS=fechaPS[1].split(":");
		        	
		        	fechaActual= fechaPS[0]+ "T" + horaPS[0] + ":00:00";
		        	
		        	int actualMinutos = Integer.parseInt(horaPS[1]);
		        	
		        	Integer modulo= actualMinutos-(actualMinutos%15);
		        	
		        	String date= fechaPS[0]+ "T" + horaPS[0]+ ":"+ ((modulo==0)?("00"):(modulo)) + ":00";
		        	
		        	Date fechaDate=null;
					try {
						fechaDate = formato.parse(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
		            return fechaDate;
				
				
			}
			// 1A PROYECTO 2
			
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
