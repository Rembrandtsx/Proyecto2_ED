package model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Servicio implements Comparable<Servicio>{
	
	public String id;
	
	public double dropoff_census_tract;
	public double dropoff_centroid_latitude;
	public String dropoff_type;
	public double droplat;
	public double droplong;
	public double dropoff_centroid_longitude;
	public int dropoff_community_area;
	private String companhiaTaxi;
	public double extras;
	public double fare;
	public String payment_type;
	
	public double pickup_census_tract;
	public double pickup_centroid_latitude;
	public String pickup_type;
	public double picklat;
	public double picklong;
	public double pickup_centroid_longitude;
	public int pickup_community_area;
	
	/**id del taxi que llevó a cabo el servicio*/
	public String taxiAutor;
	public double tips;
	public double tolls;
	
	/**Millas recorridas en el servicio*/
	public double trip_miles;
	/**Tiempo que duro el recorrido*/
	public int trip_seconds;
	/**Fecha y hora del inicio del servicio*/
	public String trip_start_timestamp;
	/**Fecha y hora del final del servicio*/
	public String trip_end_timestamp;
	/**Ganancia por el servicio (lo que el usuario paga por la carrera)*/
	public double trip_total;
	
	/**
	 * @param id
	 * @param dropoff_census_tract
	 * @param dropoff_centroid_latitude
	 * @param dropoff_type
	 * @param droplat
	 * @param droplong
	 * @param dropoff_centroid_longitude
	 * @param dropoff_community_area
	 * @param extras
	 * @param fare
	 * @param payment_type
	 * @param pickup_census_tract
	 * @param pickup_centroid_latitude
	 * @param pickup_type
	 * @param picklat
	 * @param picklong
	 * @param pickup_centroid_longitude
	 * @param pickup_community_area
	 * @param taxiAutor
	 * @param tips
	 * @param tolls
	 * @param trip_end_timestamp
	 * @param trip_miles
	 * @param trip_seconds
	 * @param trip_start_timestamp
	 * @param trip_total
	 */
	public Servicio(String id, double dropoff_census_tract, double dropoff_centroid_latitude, String dropoff_type,
			double droplat, double droplong, double dropoff_centroid_longitude, int dropoff_community_area,
			double extras, double fare, String payment_type, double pickup_census_tract,
			double pickup_centroid_latitude, String pickup_type, double picklat, double picklong,
			double pickup_centroid_longitude, int pickup_community_area, String taxiAutor, double tips, double tolls,
			String trip_end_timestamp, double trip_miles, int trip_seconds, String trip_start_timestamp,
			double trip_total, String companhiaTaxis) {
		this.id = id;
		this.dropoff_census_tract = dropoff_census_tract;
		this.dropoff_centroid_latitude = dropoff_centroid_latitude;
		this.dropoff_type = dropoff_type;
		this.droplat = droplat;
		this.droplong = droplong;
		this.dropoff_centroid_longitude = dropoff_centroid_longitude;
		this.dropoff_community_area = dropoff_community_area;
		this.extras = extras;
		this.fare = fare;
		this.payment_type = payment_type;
		this.pickup_census_tract = pickup_census_tract;
		this.pickup_centroid_latitude = pickup_centroid_latitude;
		this.pickup_type = pickup_type;
		this.picklat = picklat;
		this.picklong = picklong;
		this.pickup_centroid_longitude = pickup_centroid_longitude;
		this.pickup_community_area = pickup_community_area;
		this.taxiAutor = taxiAutor;
		this.tips = tips;
		this.tolls = tolls;
		this.trip_end_timestamp = trip_end_timestamp;
		this.trip_miles = trip_miles;
		this.trip_seconds = trip_seconds;
		this.trip_start_timestamp = trip_start_timestamp;
		this.trip_total = trip_total;
		this.companhiaTaxi = companhiaTaxis;
	}
	
	public Servicio(String pTripId, String pTaxiId,String pTaxiCompanhia, int ptripSeconds, double ptripMiles, double pTripTotal, int pArea, String pStart, String pEnd) {
		// TODO Auto-generated constructor stub
		this.id = pTripId;
		this.dropoff_census_tract = 0;
		this.dropoff_centroid_latitude = 0;
		this.dropoff_type = "";
		this.droplat = 0;
		this.droplong = 0;
		this.dropoff_centroid_longitude = 0;
		this.dropoff_community_area = 0;
		this.extras = 0;
		this.fare = 0;
		this.payment_type = "";
		this.pickup_census_tract = 0;
		this.pickup_centroid_latitude = 0;
		this.pickup_type = "";
		this.picklat = 0;
		this.picklong = 0;
		this.pickup_centroid_longitude = 0;
		this.pickup_community_area = pArea;
		this.taxiAutor = "";
		this.tips = 0;
		this.tolls = 0;
		this.trip_end_timestamp = pEnd;
		this.trip_miles = ptripMiles;
		this.trip_seconds = ptripSeconds;
		this.trip_start_timestamp = pStart;
		this.trip_total = pTripTotal;
		this.companhiaTaxi = pTaxiCompanhia;
	}
	
	public Servicio(String startTime, String endTime)
	{
		trip_start_timestamp = startTime;
		trip_end_timestamp = endTime;
	}
	
	public Servicio(String horaInicial, String horaFinal, double distancia, int duracion, double total)
	{
		this.trip_start_timestamp = horaInicial;
		this.trip_end_timestamp= horaFinal;
		this.trip_miles = distancia;
		this.trip_seconds = duracion;
		this.trip_total = total;
	}
	
	/**
	 * @return id - Trip_id
	 */
	public String getTripId(){
		return id;
	}	
	
	/**
	 * @return id - Taxi_id
	 */
	public String getTaxiId() {
		return taxiAutor;
	}	
	
	/**
	 * @return time - Time of the trip in seconds.
	 */
	public int getTripSeconds() {
		return trip_seconds;
	}

	/**
	 * @return miles - Distance of the trip in miles.
	 */
	public double getTripMiles() {
		return trip_miles;
	}
	
	/**
	 * @return total - Total cost of the trip
	 */
	public double getTripTotal() {
		return trip_total;
	}
	
	/**
	 * TODO Tal vez no lo usaré
	 * Retorna el inicial del servicio como Date
	 * @return Fecha de inicio del servicio
	 * 		null si la fecha esta en formato incorrecto
	 * @throws java.text.ParseException 
	 */
	public Date getStartTime() throws java.text.ParseException
	{
		Date end = null;
		try {
			end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS" ).parse(this.trip_start_timestamp);
		} catch (ParseException e) {
			//Exception si el string no pudo pasarse al formato de la fecha
			e.printStackTrace();
		}
		return end;
	}
	
	/**
	 * TODO Tal vez no lo usaré
	 * Retorna el final del servicio como Date
	 * @return Fecha de inicio del servicio
	 * 		null si la fecha esta en formato incorrecto
	 * @throws java.text.ParseException 
	 */
	public Date getEndTime() throws java.text.ParseException
	{
		Date end = null;
		try {
			end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS" ).parse(this.trip_end_timestamp);
		} catch (ParseException e) {
			//Exception si el string no pudo pasarse al formato de la fecha
			e.printStackTrace();
		}
		return end;
	}
	

	public int compareTo(Servicio o) {
		// TODO Auto-generated method stub
		try {
			return this.getStartTime().compareTo(o.getStartTime());
			
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		}
		
	}

	public String getTaxiCompanhia() {
		// TODO Auto-generated method stub
		return this.companhiaTaxi;
	}
}
