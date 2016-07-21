package de.unipassau.disambiguation.test;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IndraClient {

	public IndraClient() {

	}
	
	public void indraExample(){
		
		OkHttpClient client = new OkHttpClient();
	    String content = "{\"corpus\": \"wiki-2014\", \"model\": \"W2V\", \"language\": \"EN\", \"scoreFunction\": \"COSINE\", \"pairs\": [{\"t1\": \"wife\", \"t2\": \"mother\"}]}";

	    MediaType mediaType = MediaType.parse("application/json");

	    RequestBody body = RequestBody.create(mediaType, content);
	    Request request = new Request.Builder()
	      .url("http://indra.amtera.net:80/indra/v1/relatedness")
	      .post(body)
	      .addHeader("accept", "application/json")
	      .addHeader("content-type", "application/json")
	      .addHeader("authorization", "Basic aW5kcmE6UVk4SDVkcm9ZODQ9")
	      .addHeader("cache-control", "no-cache")
	      .build();

	    Response response = null;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(response.body().toString());
		
	}

	public void sgExample(){
		
		   OkHttpClient client = new OkHttpClient();
		    String content = "{'corpus': 'wiki-2014'," +
		                        "'model': 'W2V'," +
		                        "'language': 'EN'," +
		                        "'scoreFunction': 'COSINE'," +
		                        "'pairs': [{'t2': 'Give me all cosmonauts'," +
		                                    "'t1': 'space shuttle'}," +
		                                      " {'t2': 'car', " +
		                                    "'t1': 'engine'}]}";

		    MediaType mediaType = MediaType.parse("application/json");
		    RequestBody body = RequestBody.create(mediaType, content);
		    Request request = new Request.Builder()
		      .url("http://indra.amtera.net:80/indra/v1/relatedness")
		      .post(body)
		      .addHeader("accept", "application/json")
		      .addHeader("content-type", "application/json")
		      .addHeader("authorization", "Basic aW5kcmE6UVk4SDVkcm9ZODQ9")
		      .addHeader("cache-control", "no-cache")
		      .build();

		    try {
				Response response = client.newCall(request).execute();
				System.out.println(response.body().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	
	public static void main(String[] args) {

		IndraClient client = new IndraClient();
		client.indraExample();
	   
	}

}
