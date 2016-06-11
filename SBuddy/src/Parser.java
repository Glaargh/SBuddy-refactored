import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class Parser {
	
	JSONParser parser;
	
	/**
	 * Initialize the parser.
	 */
	public Parser() {
		parser = new JSONParser();
	}
	
	/**
	 * The base of the parser; will parse a String to a JSonObject
	 * @param parsable
	 * @return
	 */
	public JSONObject parse(String parsable) {
		try {
			return (JSONObject) parser.parse(parsable);
		} catch (ParseException e) {
			return null;
		}
	
	}
	
	/**
	 * A more complex method; parses a string of incoming Courses for different
	 * purposes:
	 * 		- Can add the courses read in the String to the Object delivered in
	 * 			the second parameter; will only function with an ObservableList
	 * 			or a ListView
	 * 		- Returns the object passed on in the second parameter through 
	 * 			the parser
	 * @param incomingCourses The String of parsable courses
	 * @param it The object to use the String with
	 * @return Object
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object parseCourses(String incomingCourses, Object it){
		//ObservableLst<Courses> date = FXCollections.observableArrayList();
		Object data = null;
		
		if(it instanceof ObservableList<?>){
			data =(ObservableList<Courses>) it;
		} else if (it instanceof ListView<?>){
			data = (ListView<String>) it;
		}else if(it instanceof String) {
				data = (String) it;
			}
			if(!incomingCourses.equals("{}")){
			JSONObject courseList = parse(incomingCourses.toString());
			
			Set keys = courseList.keySet();
		    Iterator loop = keys.iterator();	
		    while(loop.hasNext()) {
		    	String key = (String)loop.next();
		        String value = (String)courseList.get(key);
		        
		        if (data instanceof ObservableList<?>) {	
		        	((ObservableList<Courses>) data).add(new Courses(key,value,""));
		        } else if (data instanceof ListView<?>) {	
		        	((ListView<String>) data).getItems().add(key + ":  " + value);
		        } else if (data instanceof String) {
		        	if (!loop.hasNext()) {
			    		data += key;
					}else{
				    	data += key  +", ";
					}
		        }
		    }
		} else {
			System.out.println("No courses");
		}
		return data;
	}
		
	

	
	/**
	 * Returns all user information of a passed on ID in a String[]
	 * @param client The current Client
	 * @param id The ID of the user to retrieve information from
	 * @return String[]
	 */
	public String[] parseUserInfo(Client client, String id) {
		String[] returnValues = new String[12];
		try {
			returnValues[0] = client.toServer(ClientMethods.getOther(id,"Firstname"));
			returnValues[1] = client.toServer(ClientMethods.getOther(id , "Lastname"));
			returnValues[2] = client.toServer(ClientMethods.getOther(id , "Age"));
			returnValues[3] = client.toServer(ClientMethods.getOther(id , "CityOfResidence"));
			returnValues[4] = client.toServer(ClientMethods.getOther(id , "CountryOfResidence"));
			returnValues[5] = client.toServer(ClientMethods.getOther(id , "CurrentUniversity"));
			returnValues[6] = client.toServer(ClientMethods.getOther(id , "CurrentStudy"));
			returnValues[7] = client.toServer(ClientMethods.getOther(id , "Course list"));
			returnValues[8] = client.toServer(ClientMethods.getOther(id,"Phone"));
			returnValues[9] = client.toServer(ClientMethods.getOther(id,"Email"));
			returnValues[10] = client.toServer(ClientMethods.getOther(id,"Piclink"));
			returnValues[11] = client.toServer(ClientMethods.getOther(id,"Description"));
			return returnValues;
		} catch (IOException e) {
			return new String[0];
		}
	}
	
		
	
}
