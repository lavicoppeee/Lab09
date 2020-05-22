package it.polito.tdp.borders.model;

public class Country {

	private int cCode;
	private String StateAbb;
	private String StateNme;
	
	public Country(int cCode, String stateAbb, String stateNme) {
		super();
		this.cCode = cCode;
		StateAbb = stateAbb;
		StateNme = stateNme;
	}

	public int getcCode() {
		return cCode;
	}

	public void setcCode(int cCode) {
		this.cCode = cCode;
	}

	public String getStateAbb() {
		return StateAbb;
	}

	public void setStateAbb(String stateAbb) {
		StateAbb = stateAbb;
	}

	public String getStateNme() {
		return StateNme;
	}

	public void setStateNme(String stateNme) {
		StateNme = stateNme;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cCode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (cCode != other.cCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return StateNme+" "+"( "+StateAbb+" )";

	}
	
	
	
	
}
