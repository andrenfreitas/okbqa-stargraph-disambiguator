package de.unipassau.disambiguation.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.unipassau.disambiguation.stargraph_client.StarGraphDisambiguation;

public class DisambiguationTest {

	public DisambiguationTest() {

	}

	public static void main(String[] args) {
		
		StarGraphDisambiguation starGraphDisambiguation = new StarGraphDisambiguation();
		
		try {
			for(int j = 9; j < 12 ; j++){
				JSONParser parser = new JSONParser();
				//input file name
				Object obj = parser.parse(new FileReader("/home/andfre/files/source/stargraph_disambiguation/test_data/test" + j + ".json"));
				
				JSONObject jsonObject = (JSONObject) obj;
				JSONObject jsonOutput = starGraphDisambiguation.resolveEntities(jsonObject);
				System.out.println("----------------------------------------------------------");
				System.out.println(jsonOutput.toJSONString());
//				
//				JSONArray jsonArray= (JSONArray) obj;
//				Iterator i = jsonArray.iterator();
//				
//				while(i.hasNext()) {
//					JSONObject jsonObject = (JSONObject) obj;
//					JSONObject jsonOutput = starGraphDisambiguation.resolveEntities(jsonObject);
//					System.out.println("----------------------------------------------------------");
//					System.out.println(jsonOutput.toJSONString());
//				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
