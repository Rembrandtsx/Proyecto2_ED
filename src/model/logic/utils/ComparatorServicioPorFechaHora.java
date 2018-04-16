package model.logic.utils;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import model.vo.Servicio;
import model.vo.Taxi;
import model.vo.TaxiConPuntos;

public class ComparatorServicioPorFechaHora implements Comparator<Servicio>
{
	

	@Override
	public int compare(Servicio o1, Servicio o2) {
		// TODO Auto-generated method stub
		if(o1!=null&&o2!=null){
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
	        try {
	            Date fechaDate = formato.parse(o1.trip_start_timestamp);
	            Date fechaDate2= formato.parse(o2.trip_start_timestamp);
	            
	            return fechaDate.compareTo(fechaDate2);
	        } 
	        catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return 0;
	}
}