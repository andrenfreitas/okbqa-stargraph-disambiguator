package de.unipassau.disambiguation.stargraph_client;

public class PredicateAndPivot {

	private String property;
	private String propertyVariable;
	private String pivot;
	
	public PredicateAndPivot(String property, String propertyVariable, String pivot) {
		this.property = property;
		this.propertyVariable = propertyVariable;
		this.pivot = pivot;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getPivot() {
		return pivot;
	}

	public void setPivot(String pivot) {
		this.pivot = pivot;
	}

	public String getPropertyVariable() {
		return propertyVariable;
	}

	public void setPropertyVariable(String propertyVariable) {
		this.propertyVariable = propertyVariable;
	}

	
}
