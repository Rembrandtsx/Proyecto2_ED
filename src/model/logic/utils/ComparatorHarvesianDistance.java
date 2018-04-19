package model.logic.utils;



import model.vo.Servicio;

public class ComparatorHarvesianDistance
{

	public <T> int compare(T t, T pivot)
	{
		if(t != null && pivot != null)
		{
			int comparar = (int)( ((Servicio) t).getHarvesianDistance()- ((Servicio) pivot).getHarvesianDistance());

			if(comparar > 0)
			{
				return 1;
			}
			else if(comparar < 0)
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
		return -2;
	}
}