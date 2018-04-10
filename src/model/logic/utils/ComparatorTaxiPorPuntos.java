package model.logic.utils;
import java.util.Comparator;

import model.vo.Servicio;
import model.vo.Taxi;
import model.vo.TaxiConPuntos;

public class ComparatorTaxiPorPuntos implements Comparator<TaxiConPuntos>
{
	

	@Override
	public int compare(TaxiConPuntos o1, TaxiConPuntos o2) {
		// TODO Auto-generated method stub
		if(o1!=null&&o2!=null){
			
			return ((Double)o1.getPuntos()).compareTo(((Double)o2.getPuntos()));
		}
		else{
			return 0;
		}
	}
}