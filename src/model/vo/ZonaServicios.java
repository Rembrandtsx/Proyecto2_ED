package model.vo;

import model.data_structures.ILinkedList;

public class ZonaServicios implements Comparable<ZonaServicios>{

	private String idZona;
	
	private ILinkedList<FechaServicios> fechasServicios;
	
	

	public String getIdZona() {
		return idZona;
	}



	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}



	public ILinkedList<FechaServicios> getFechasServicios() {
		return fechasServicios;
	}



	public void setFechasServicios(ILinkedList<FechaServicios> fechasServicios) {
		this.fechasServicios = fechasServicios;
	}



	@Override
	public int compareTo(ZonaServicios o) {
		// TODO Auto-generated method stub
		if(idZona.compareTo(o.idZona) == 0)
			return 0;
		return 1;
	}
}
