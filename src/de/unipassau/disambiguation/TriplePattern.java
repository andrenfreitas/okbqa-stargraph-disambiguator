package de.unipassau.disambiguation;
 
public class TriplePattern {

	private QueryTerm s;
	private QueryTerm p;
	private QueryTerm o;
	
	public TriplePattern() {

	}
	
	public TriplePattern(QueryTerm s, QueryTerm p, QueryTerm o) {
		this.s = s;
		this.p = p;
		this.o = o;
	}

	public QueryTerm getS() {
		return s;
	}

	public void setS(QueryTerm s) {
		this.s = s;
	}

	public QueryTerm getP() {
		return p;
	}

	public void setP(QueryTerm p) {
		this.p = p;
	}

	public QueryTerm getO() {
		return o;
	}

	public void setO(QueryTerm o) {
		this.o = o;
	}

	@Override
	public String toString() {
		return "TriplePattern [s=" + s + ", p=" + p + ", o=" + o + "]";
	}
	
}
