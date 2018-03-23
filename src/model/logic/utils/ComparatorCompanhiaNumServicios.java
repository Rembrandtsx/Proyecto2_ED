package model.logic.utils;
import java.util.Comparator;

import model.vo.Compania;
import model.vo.Servicio;
import model.vo.Taxi;

public class ComparatorCompanhiaNumServicios implements Comparator<Compania>
{
	

	@Override
	public int compare(Compania o1, Compania o2) {
		// TODO Auto-generated method stub
		Integer temp1= o1.getMisServicios().size();
		Integer temp2= o2.getMisServicios().size();
		
		return temp1.compareTo(temp2);
	}
}