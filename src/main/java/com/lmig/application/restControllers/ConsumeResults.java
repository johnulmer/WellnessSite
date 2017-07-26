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
 
 private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json"; 

 public WellnessEvent getLngLatFromGoogle(WellnessEvent addr) {

	 String fullAddress = addr.getStreetAddress() + " "+ addr.getCity() 
		+ " , " + addr.getState() + " , " + addr.getZipCode(); 
	 
//	 Gson gson = new Gson();
	 
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
 
 ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
 
 try {
	IOUtils.copy(conn.getInputStream(), output);
	System.out.println(output.toString());
	
	String myJSONString = output.toString();
	
	JsonObject jobi = new Gson().fromJson(myJSONString, JsonObject.class);

	JsonObject jobj = (JsonObject) jobi.getAsJsonArray("results").getAsJsonArray().get(0);
	Double lat = jobj.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat").getAsDouble();
	Double lng = jobj.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng").getAsDouble();
	
	addr.setLatitude(lat);
	addr.setLongitude(lng);

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