package de.unipassau.disambiguation.stargraph_client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.unipassau.disambiguation.ResolvedEntity;

public class StarGraphDisambiguation {

	public StarGraphDisambiguation() {

	}
	
	public JSONObject resolveEntities(JSONObject inputJSON) throws Exception{
		
		QueryTemplate queryTemplate = parseJSONInput(inputJSON);
		
		List<QueryTerm> instances = new ArrayList<QueryTerm>();
		List<QueryTerm> properties = new ArrayList<QueryTerm>();
		List<QueryTerm> classes = new ArrayList<QueryTerm>();
		
		List<Mapping> mappings = new ArrayList<>();
		for(QueryTerm queryTerm : queryTemplate.getQueryTerms()){
			if(queryTerm.getType().equals(EntityType.INSTANCE))
				instances.add(queryTerm);
			else if(queryTerm.getType().equals(EntityType.PROPERTY))
				properties.add(queryTerm);
			else if(queryTerm.getType().equals(EntityType.CLASS)){
				classes.add(queryTerm);
			}
		}
		
		List<Mapping> resolvedPivots = new ArrayList<>();		
		resolvedPivots = resolvePivots(instances);
		
		List<PredicateAndPivot> predicateAndPivots = new ArrayList<PredicateAndPivot>();
		predicateAndPivots = getPredicateAndPivots(resolvedPivots, properties);
		
		List<Mapping> resolvedProperties = new ArrayList<>();		
		resolvedProperties = resolveProperties(predicateAndPivots);
		
		List<Mapping> resolvedClasses = new ArrayList<>();		
		resolvedClasses = resolveClasses(classes);
		
		mappings.addAll(resolvedPivots);
		mappings.addAll(resolvedProperties);
		mappings.addAll(resolvedClasses);
		return generateJSONOutput(queryTemplate, mappings);
	}
	
	private List<PredicateAndPivot> getPredicateAndPivots(List<Mapping> resolvedPivots, List<QueryTerm> properties){
		
		List<PredicateAndPivot> predicateAndPivots = new ArrayList<PredicateAndPivot>();
		
		for(Mapping resolvedPivot : resolvedPivots){
			for(ResolvedEntity resolvedEntity : resolvedPivot.getMappedEntities()){
				for(QueryTerm queryTerm : properties){
					PredicateAndPivot predicateAndPivot = new PredicateAndPivot(queryTerm.getTerm(), queryTerm.getVariable(), resolvedEntity.getUri());
					predicateAndPivots.add(predicateAndPivot);
				}
			}
		}
		return predicateAndPivots;
	}

	private List<Mapping> resolvePivots(List<QueryTerm> queryTerms) throws Exception{
		
		StarGraphClient sgClient = new StarGraphClient();
		
		List<Mapping> mappings = new ArrayList<>();
		for(QueryTerm queryTerm : queryTerms){
			mappings.add(sgClient.instanceSearch(queryTerm.getTerm(), queryTerm.getVariable()));
		}
		
		return mappings;
	}
	
	private List<Mapping> resolveProperties(List<PredicateAndPivot> predicateAndPivots) throws Exception{
		
		StarGraphClient sgClient = new StarGraphClient();
		
		List<Mapping> mappings = new ArrayList<>();
		for(PredicateAndPivot predicateAndPivot :  predicateAndPivots){
			mappings.add(sgClient.propertySearch(predicateAndPivot.getPivot(), predicateAndPivot.getProperty(), predicateAndPivot.getPropertyVariable()));
		}
		
		return mappings;
	}

	private List<Mapping> resolveClasses(List<QueryTerm> queryTerms) throws Exception{
		
		StarGraphClient sgClient = new StarGraphClient();
		
		List<Mapping> mappings = new ArrayList<>();
		for(QueryTerm queryTerm : queryTerms){
			mappings.add(sgClient.classSearch(queryTerm.getTerm(), queryTerm.getVariable()));
		}
		
		return mappings;
	}
	
//	private List<Mapping> resolveClassesUsingPivots(List<PredicateAndPivot> predicateAndPivots) throws Exception{
//		
//		StarGraphClient sgClient = new StarGraphClient();
//		
//		List<Mapping> mappings = new ArrayList<>();
//		for(PredicateAndPivot predicateAndPivot :  predicateAndPivots){
//			mappings.add(sgClient.classSearch(predicateAndPivot.getPivot(), predicateAndPivot.getProperty(), predicateAndPivot.getPropertyVariable()));
//		}
//		
//		return mappings;
//	}
	
	private QueryTemplate parseJSONInput(JSONObject inputJSON){
		
		QueryTemplate template = new QueryTemplate();
		template.loadFromJSON(inputJSON);
		return template;	
	}

	private JSONObject generateJSONOutput(QueryTemplate queryTemplate, List<Mapping> mappings){
				
        JSONObject object = new JSONObject();
        object.put("question", queryTemplate.getQuestion());

        JSONArray instances = generateJSONMappings(queryTemplate, mappings, EntityType.INSTANCE);
        JSONArray properties = generateJSONMappings(queryTemplate, mappings, EntityType.PROPERTY);
        JSONArray classes = generateJSONMappings(queryTemplate, mappings, EntityType.CLASS);
        
        JSONArray ned = new JSONArray();
        JSONObject nedBody = new JSONObject();
        nedBody.put("score", 1);
        nedBody.put("entities", instances);
        nedBody.put("properties", properties);
        nedBody.put("classes", classes);
        ned.add(nedBody);
        object.put("ned", ned);
        return object;
	}
	
	private JSONArray generateJSONMappings(QueryTemplate queryTemplate, List<Mapping> mappings, EntityType type){
		
        JSONArray resources = new JSONArray();
		for(Mapping mapping: mappings){
			if(mapping.getEntityType().equals(type)){
	            for(ResolvedEntity resolvedEntity : mapping.getMappedEntities()){
	            	JSONObject entry = new JSONObject();          
	            	entry.put("var", mapping.getVariable());
	            	entry.put("value", resolvedEntity.getUri());
	            	entry.put("score", resolvedEntity.getScore());  	
	            	resources.add(entry);
	            }
			}
		}
		
		return resources;
	}
	
    public JSONArray getJSONArray(Map<String, String> var2String) {
        JSONArray resources = new JSONArray();
        for (String var : var2String.keySet()) {
            JSONObject entry = new JSONObject();
            entry.put("var", var);
            entry.put("value", var2String.get(var));
            entry.put("score", 1);
            resources.add(entry);
        }
        return resources;
    }
	
}
