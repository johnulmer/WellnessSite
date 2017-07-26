package com.lmig.application.restControllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lmig.application.entities.WellnessEvent;

import org.apache.commons.io.IOUtils;


@Component
public class ConsumeResults {
	
	
//	private static final Logger log = LoggerFactory.getLogger(Results.class);
 	
//	CODE for consuming rest API using rest template
//	public static void main(String args[]) {
//		SpringApplication.run(Results.class);
//	}
 	
 	//@Bean
 	//public RestTemplate restTemplate(RestTemplateBuilder builder)  {
	//	return builder.build();
 	
//	}
 	
 	//@Bean
 //	//public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
 //		return args -> {
//			address = restTemplate.getForObject(
 //					"https://maps.googleapis.com/maps/api/geocode/json?address=ADDRESS&key=AIzaSyBbSfE0t3tuzdiOtarHlBrkgHNMjeOQGEY", Address.class);
 //			log.info(address.toString());
//		};
//	}
/*Geocode request URL. Here see we are passing "json" it means we will get the output in JSON format.
 * You can also pass "xml" instead of "json" for XML output.
 * For XML output URL will be "http://maps.googleapis.com/maps/api/geocode/xml"; 
 */
 
 private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json"; 

 /*
 * Here the fullAddress String is in format like "address,city,state,zipcode". Here address means "street number + route" .
 *
 */
 public WellnessEvent getLngLatFromGoogle(WellnessEvent addr) {

	 String fullAddress = addr.getStreetAddress() + " "+ addr.getCity() 
		+ " , " + addr.getState() + " , " + addr.getZipCode(); 
	 
	 //Gson gson = new Gson();
	 
	 URL url = null;
	 try {
		 url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8")+ "&sensor=false");
	 } catch (MalformedURLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	 } catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	 }
 
	 // Open the Connection 
	 URLConnection conn = null;
	 try {
		 conn = url.openConnection();
	 } catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 
 //This is Simple a byte array output stream that we will use to keep the output data from google. 
 ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
 
 // copying the output data from Google which will be either in JSON or XML depending on your request URL that in which format you have requested.
 try {
	IOUtils.copy(conn.getInputStream(), output);
	System.out.println(output.toString());
	
	String myJSONString = output.toString();
	
	JsonObject jobi = new Gson().fromJson(myJSONString, JsonObject.class);

	JsonObject jobj = (JsonObject) jobi.getAsJsonArray("results").getAsJsonArray().get(0);
		
	//System.out.println("jobArray: " + jobj.get("results").getAsString());
	
	Double lat = jobj.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat").getAsDouble();
	Double lng = jobj.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng").getAsDouble();
	
	addr.setLatitude(lat);
	addr.setLongitude(lng);
	
//	addr.setLongitude(output.);
//	String lng = 
	
//	 String jsonString = "{\"geometry\": ,\"location\":,\"lat\":,lng\"}";
//	
//	Address address = new Address();
//	address
	 
//	Address address = gson.fromJson(jsonString, Address.class);
//	System.out.println("address, lat, lng: " + address.getStreetAddress()+ address.getCity() + address.getState() + address.getZipCode() + address.getLatitude() + address.getLongitude());
	
	
	//((Object) System.out).output(address);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 
 //close the byte array output stream now.
 try {
	output.close();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 return addr;
 //return output.toString(); // This returned String is JSON string from which you can retrieve all key value pair and can save it in POJO.
 }
 } 