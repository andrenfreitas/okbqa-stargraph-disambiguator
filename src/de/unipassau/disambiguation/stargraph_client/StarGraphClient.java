package de.unipassau.disambiguation.stargraph_client; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import de.unipassau.disambiguation.Mapping;
import de.unipassau.disambiguation.QueryTemplate;
import de.unipassau.disambiguation.QueryTerm;
import de.unipassau.disambiguation.ResolvedEntity;

public class StarGraphClient {
    ObjectMapper mapper = new ObjectMapper();

    String user = "stargraph";
    String pwd = "p6YMgQ9O2os=";


    public void setup() {
        Unirest.setTimeouts(6000000, 6000000);
        Unirest.setDefaultHeader("content-type", "application/json");
    }
    
	public Mapping instanceSearch(String entityTerm, String variable) throws Exception{
				
		List<ResolvedEntity> entities = new ArrayList<>();
		entities = searchEntity(entityTerm, 1);
		return new Mapping(entityTerm, EntityType.INSTANCE, variable, entities);
	}
	
	public Mapping propertySearch(String entityURI, String entityTerm, String variable) throws Exception{
		
		List<ResolvedEntity> entities = new ArrayList<>();
		entities = pivotedSearch(entityURI, entityTerm, EntityType.PROPERTY, 2, RankModel.W2V);
		return new Mapping(entityTerm, EntityType.PROPERTY, variable, entities);
	}

	public Mapping classSearch(String entityURI, String entityTerm, String variable) throws Exception{
		
		List<ResolvedEntity> entities = new ArrayList<>();
		entities = pivotedSearch(entityURI, entityTerm, EntityType.CLASS, 2, RankModel.W2V);
		return new Mapping(entityTerm, EntityType.CLASS, variable, entities);
	}
	
    public List<ResolvedEntity> searchEntity(String entityTerm, long topK) throws Exception {
        HttpResponse<String> response = Unirest.get(makeURL("/entity"))
                .basicAuth(user, pwd)
                .queryString("term", entityTerm)
                .queryString("topk", topK)
                .asString();

        checkStatus(response);

        return mappingEntities(mapper.readValue(response.getBody(), EntitySearchResponse.class));
    }
    
    public List<ResolvedEntity> searchEntity(String entityTerm, EntityType type, long topK, RankModel model) throws Exception {
        HttpResponse<String> response = Unirest.get(makeURL("/entity"))
                .basicAuth(user, pwd)
                .queryString("term", entityTerm)
                .queryString("type", type)
                .queryString("topk", topK)
                .queryString("model", model)
                .asString();

        checkStatus(response);

        return mappingEntities(mapper.readValue(response.getBody(), EntitySearchResponse.class));
    }

    public List<ResolvedEntity> pivotedSearch(String entityId, String relationTerm, EntityType type, long topK, RankModel model) throws Exception {
        HttpResponse<String> response = Unirest.get(makeURL("/entity/pivoted"))
                .basicAuth(user, pwd)
                .queryString("id", entityId)
                .queryString("relationTerm", relationTerm)
                .queryString("relationType", type)
                .queryString("topk", topK)
                .queryString("model", model)
                .asString();

        checkStatus(response);

        return mappingEntities(mapper.readValue(response.getBody(), EntitySearchResponse.class));
    }

    public EntitySearchResponse filteredSearch(List<String> entityIds, String term) throws Exception {
    	
        Map<String, Object> parameters = new HashMap<>();
        for(String entityId : entityIds)
        	parameters.put("id", entityId);
    	
        HttpResponse<String> response = Unirest.get(makeURL("/entity/filtered")).basicAuth(user, pwd)
        		.queryString(parameters)
                .queryString("term", term)
                .asString();

        checkStatus(response);

        return mapper.readValue(response.getBody(), EntitySearchResponse.class);
    }

    public List<Entity> graphSearch(String s, String p, String o) throws Exception {
        
        Map<String, Object> parameters = new HashMap<>();
        if(!s.isEmpty())
        	parameters.put("s", s);
        if(!p.isEmpty())
        	parameters.put("p", p);
        if(!o.isEmpty())
        	parameters.put("o", o);
        
        HttpResponse<String> response = Unirest.get(makeURL("/entity/pattern"))
                .basicAuth(user, pwd)
                .queryString(parameters)
                .asString();
       
        checkStatus(response);

        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Entity.class);
        List<Entity> res = mapper.readValue(response.getBody(), type);
        return res;
    }

    static void printEntities(EntitySearchResponse esr){
    	
        for(EntityScore entityScore : esr.entityScores)
        	System.out.println(entityScore.entity + " = " + entityScore.score);
    }
    
    static List<ResolvedEntity> mappingEntities(EntitySearchResponse esr){
    	
    	 List<ResolvedEntity> entities = new ArrayList<>();
    	
        for(EntityScore entityScore : esr.entityScores){
        	ResolvedEntity resolvedEntity = new ResolvedEntity(entityScore.entity.id, entityScore.score);
        	entities.add(resolvedEntity);
        }
        
		return entities;
    }
    
    static String makeURL(String path) {
        return String.format("http://stargraph.amtera.net/stargraph%s", path);
    }

    static void checkStatus(HttpResponse<?> r) {
        if (r.getStatus() != 200) {
            throw new RuntimeException("Status code != 200");
        }
    }


}
