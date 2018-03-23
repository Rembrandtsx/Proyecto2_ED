package model.vo;


import model.data_structures.LinkedSimpleList;

public class CompaniaServicios implements Comparable<CompaniaServicios> {
	
	private String nomCompania;
	
	private LinkedSimpleList<Servicio> servicios;

	public String getNomCompania() {
		return nomCompania;
	}

	public void setNomCompania(String nomCompania) {
		this.nomCompania = nomCompania;
	}

	public LinkedSimpleList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(LinkedSimpleList<Servicio> servicios) {
		this.servicios = servicios;
	}

	
	/**
	 * Compara las companiaServicios segun la cantidad de servicios que tienen
	 * @return 0 si la cantidad de servicios de ambas companias es la misma
	 * 			1 si this tiene mas servicios que companiaServicio o
	 * 		   -1 si this tiene menos servicios que companiaServicio o
	 */
	@Override
	public int compareTo(CompaniaServicios o) {
		if(this.servicios.size() > o.servicios.size())
			return -1;
		else if(this.servicios.size() < o.servicios.size())
			return 1;
		else
			return 0;
		
	}
	
	

}