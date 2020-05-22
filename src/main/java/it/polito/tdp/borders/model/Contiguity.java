package it.polito.tdp.borders.model;

public class Contiguity {

	
	private int cCode1;
	private int cCode2;
	private int dyad;
	private String StateAbb1;
	private String StateAbb2;
	private int year;
	private int contttype;
	
	public Contiguity(int cCode1, int cCode2, int dyad, String stateAbb1, String stateAbb2, int year, int contttype) {
		super();
		this.cCode1 = cCode1;
		this.cCode2 = cCode2;
		this.dyad = dyad;
		StateAbb1 = stateAbb1;
		StateAbb2 = stateAbb2;
		this.year = year;
		this.contttype = contttype;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cCode1;
		result = prime * result + cCode2;
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
		Contiguity other = (Contiguity) obj;
		if (cCode1 != other.cCode1)
			return false;
		if (cCode2 != other.cCode2)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contiguity [cCode1=" + cCode1 + ", cCode2=" + cCode2 + ", StateAbb1=" + StateAbb1 + ", StateAbb2="
				+ StateAbb2 + ", year=" + year + "]";
	}
	
	
	
	
	
}
