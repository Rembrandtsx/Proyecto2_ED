package model.logic.utils;
import java.util.Comparator;

import model.vo.Compania;
import model.vo.Servicio;
import model.vo.Taxi;

public class ComparatorPorNumeros implements Comparator<Integer>
{
	

	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}
	
}