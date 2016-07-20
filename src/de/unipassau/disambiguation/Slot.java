package de.unipassau.disambiguation;

public class Slot {

	private String s;
	private String p;
	private String o;
	
	public Slot(){
		
	}
	
	public Slot(String s, String p, String o) {
		this.s = s;
		this.p = p;
		this.o = o;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	@Override
	public String toString() {
		return "Slot [s=" + s + ", p=" + p + ", o=" + o + "]";
	}

}
