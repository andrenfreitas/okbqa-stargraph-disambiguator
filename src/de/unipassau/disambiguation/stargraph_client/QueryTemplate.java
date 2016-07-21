package de.unipassau.disambiguation.stargraph_client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class QueryTemplate {

	private String question;
	private String query;
	private double score;
	
	private List<Slot> slots = new ArrayList<>();
	private List<QueryTerm> queryTerms = new ArrayList<>();
	
	public QueryTemplate() {
	
	}
	
	public void loadFromJSON(JSONObject inputJSON){
		
		if(inputJSON.containsKey("question"))
			question = (String) inputJSON.get("question");
		if(inputJSON.containsKey("query"))
			query = (String) inputJSON.get("query");
		if(inputJSON.containsKey("score"))
			score = (double) inputJSON.get("score");
		
		if(inputJSON.containsKey("slots")) {
			JSONArray slotJSONArray = (JSONArray) inputJSON.get("slots");
			Iterator i = slotJSONArray.iterator();
			
			while(i.hasNext()) {
				JSONObject slotJSONObject = (JSONObject) i.next();
				Slot tempSlot = new Slot("","","");
				tempSlot.setS((String)slotJSONObject.get("s"));
				tempSlot.setP((String)slotJSONObject.get("p"));
				tempSlot.setO((String)slotJSONObject.get("o"));
				slots.add(tempSlot);
			}
		}
		
		assembleQueryTerms(slots);
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public void addSlot(String s, String p, String o){
		this.slots.add(new Slot(s, p, o));
	}
	
	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	public List<QueryTerm> getQueryTerms() {
		return queryTerms;
	}

	public void setQueryTerms(List<QueryTerm> queryTerms) {
		this.queryTerms = queryTerms;
	}
	
	public void assembleQueryTerms(List<Slot> slots){
		
		String variable = "";
		String term = "";
		EntityType type = null;
		int i = 0;
		for(Slot slot : slots){
			variable = slot.getS();
			if(slot.getP().equals("verbalization")){
				term = slot.getO();
			}else if(slot.getP().equals("is")){
				 String typeStr = slot.getO();
				 if(typeStr.contains("rdf:Resource|rdfs:Literal") || typeStr.contains("owl:NamedIndividual"))
					 type = EntityType.INSTANCE;
				 else if(typeStr.contains("rdf:Property") || typeStr.contains("owl:DatatypeProperty"))
					 type = EntityType.PROPERTY;
				 else if(typeStr.contains("rdf:Class") || typeStr.contains("owl:Class"))
					 type = EntityType.CLASS;
			}
			if(i % 2 == 1){
				this.queryTerms.add(new QueryTerm(variable, term, type));
			}
			i++;
		}
	}
	
//	public List<PredicateAndPivot> getPredicatePivotAssociations(){
//		
//	}
//	
//	private List<String> getTriplePatterns(){
//		
//		String triplePatternsStr = this.query.substring(query.indexOf("{"), query.indexOf("}")).trim();
//		String[] triplePatternsStrArray = triplePatternsStr.split(";");
//		for(String triplePatternStr : triplePatternsStr.split(";")){
//			
//			String lastSubject = "";
//			String[] vars = triplePatternsStr.trim().split(" ");
//		}
//		
//	}
//	
//	private List<TriplePattern> getTermTriplePatterns(){
//		
//	}

	@Override
	public String toString() {
		return "QueryTemplate [question=" + question + ", query=" + query
				+ ", score=" + score + ", slots=" + slots + ", queryTerms="
				+ queryTerms + "]";
	}
	
}
