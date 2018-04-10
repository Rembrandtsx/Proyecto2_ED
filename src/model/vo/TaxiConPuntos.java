package model.vo;



public class TaxiConPuntos implements Comparable<TaxiConPuntos> {

	//Agregado para requerimientos
	private String taxi_id;
	
	private String company;
	
	private double millas;
	
	private double dinero;
	
	private int numeroServicios;
	
	public TaxiConPuntos(String pID, String pCompany) {
		taxi_id= pID;
		company= pCompany;
		// TODO Auto-generated constructor stub
		millas=0;
		dinero=0;
		numeroServicios=0;
	}
	
	public String getTaxiId(){
		return taxi_id;
	}
	
	public String getCompany(){
		return company;
	}
	
	public void setMillas(double pMillas){
		millas= millas+pMillas;
	}
	
	public void setDinero(double pDinero){
		dinero= dinero+pDinero;
	}
	
	public void aumentarNumServicios(){
		numeroServicios++;
	}
	/**
     * @return puntos - puntos de un Taxi
     */
	public double getPuntos(){
		return (dinero/millas);
	}


	@Override
	public int compareTo(TaxiConPuntos o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
