package de.unipassau.disambiguation;

import de.unipassau.disambiguation.stargraph_client.EntityType;

public class QueryTerm {

	private String variable;
	private String term;
	private EntityType type;
	
	public QueryTerm() {

	}
	
	public QueryTerm(String term, EntityType type) {
		this.term = term;
		this.type = type;
	}
	
	public QueryTerm(String variable, String term, EntityType type) {
		this.variable = variable;
		this.term = term;
		this.type = type;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public EntityType getType() {
		return type;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	@Override
	public String toString() {
		return "QueryTerm [variable=" + variable + ", term=" + term + ", type="
				+ type + "]";
	}
	

}
