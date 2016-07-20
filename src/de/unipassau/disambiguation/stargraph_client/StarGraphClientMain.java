package de.unipassau.disambiguation.stargraph_client;

import java.util.ArrayList;
import java.util.List;

import de.unipassau.disambiguation.ResolvedEntity;

public class StarGraphClientMain {

	public StarGraphClientMain() {

	}
	
    static void printEntities(List<ResolvedEntity> list){
    	
        for(ResolvedEntity entityScore : list)
        	System.out.println(entityScore.getUri() + " = " + entityScore.getScore());
    }

	public static void main(String[] args) {
		
		StarGraphClient client = new StarGraphClient();
		
		try {
			
			System.out.println("------------------ searchEntity ----------------------");
			printEntities(client.searchEntity("Barack Obama", 3));

			System.out.println("------------------ searchEntity ----------------------");
			printEntities(client.searchEntity("wife", EntityType.PROPERTY, 50, RankModel.W2V));
			
			System.out.println("\n------------------- pivotedSearch ----------------------");			
			printEntities(client.pivotedSearch("http://dbpedia.org/resource/Barack_Obama", "wife", EntityType.PROPERTY,  2, RankModel.W2V));
			
			System.out.println("\n------------------- filteredSearch --------------------");
			List<String> entityURIs = new ArrayList<>();
			entityURIs.add("http://dbpedia.org/resource/Barack_Obama");
			entityURIs.add("http://dbpedia.org/resource/Korea");
			client.filteredSearch(entityURIs, "Country");
			
			System.out.println("\n----------------- graphSearch --------------------");
			List<Entity> entities = new ArrayList<>();
			entities = client.graphSearch( "http://dbpedia.org/resource/New_Zealand_DSG_class_locomotive", "http://dbpedia.org/property/builddate", "");
			for(Entity entity : entities){
				System.out.println(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
