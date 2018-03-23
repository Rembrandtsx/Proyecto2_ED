package model.vo;

public class CompaniaTaxi implements Comparable<CompaniaTaxi>{
	
	private String nomCompania;
	
	private Taxi taxi;

	public String getNomCompania() {
		return nomCompania;
	}

	public void setNomCompania(String nomCompania) {
		this.nomCompania = nomCompania;
	}

	public Taxi getTaxi() {
		return taxi;
	}

	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	@Override
	public int compareTo(CompaniaTaxi o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
