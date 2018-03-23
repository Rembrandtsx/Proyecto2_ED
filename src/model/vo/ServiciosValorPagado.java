package model.vo;

import model.data_structures.ILinkedList;

public class ServiciosValorPagado {
	
	private ILinkedList<Servicio> serviciosAsociados;
	private double valorAcumulado;
	public ILinkedList<Servicio> getServiciosAsociados() {
		return serviciosAsociados;
	}
	public void setServiciosAsociados(ILinkedList<Servicio> serviciosAsociados) {
		this.serviciosAsociados = serviciosAsociados;
	}
	public double getValorAcumulado() {
		return valorAcumulado;
	}
	public void setValorAcumulado(double valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}
	
	

}
