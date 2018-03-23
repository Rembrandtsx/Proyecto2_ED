package model.logic.utils;
import java.util.Comparator;

import model.vo.Servicio;
import model.vo.Taxi;

public class ComparatorTaxiPorIdAlfabeticamente implements Comparator<Taxi>
{
	@Override
	public int compare(Taxi o1, Taxi o2) {
		if(o1!=null&&o2!=null){
			
			return o1.getTaxiId().compareTo(o2.getTaxiId());}
		else{
			return 0;
		}
	}
}