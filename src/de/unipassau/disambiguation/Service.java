package de.unipassau.disambiguation;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.unipassau.disambiguation.stargraph_client.StarGraphDisambiguation;

/**
 * Root resource (exposed at "stargraph " path)
 */
@Path("run")
public class Service {


    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type. Assume input in the format
     * specified at
     * https://github.com/okbqa/disambiguation/wiki/IO-Specification Splits the
     * input into classes, properties and resources. Disambiguates using
     * STARGRAPH service in case of resources and dictionary lookups in case of
     * classes and properties.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getIt(@QueryParam("data") String data) {

        if (data.length() <= 1) {
            LOGGER.log(Level.WARNING, "Input {0} is too short", data);
            return "{\"message\": \"This is the disambiguation service based on STARGRAPH\" }";
        }
		StarGraphDisambiguation starGraphDisambiguation = new StarGraphDisambiguation();
		
		JSONObject output = new JSONObject();

		JSONParser parser = new JSONParser();
		
        try {
			
			Object obj = parser.parse(data);
			
			JSONArray jsonArray= (JSONArray) obj;
			Iterator i = jsonArray.iterator();
				        
			while(i.hasNext()) {
				JSONObject jsonObject = (JSONObject) i.next();
				output = starGraphDisambiguation.resolveEntities(jsonObject);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        LOGGER.log(Level.INFO, "Output for" + data + " is " + output.toJSONString(), data);
        return output.toJSONString();
    }


}
