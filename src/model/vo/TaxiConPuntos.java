package model.vo;

public class TaxiConPuntos extends Taxi {

	//Agregado para requerimientos
	public TaxiConPuntos(String pID, String pCompany) {
		super(pID, pCompany);
		// TODO Auto-generated constructor stub
	}

	/**
     * @return puntos - puntos de un Taxi
     */
	public double getPuntos(){
		return 0.0;
	}
}
