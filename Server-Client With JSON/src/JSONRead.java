import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONRead {

	public static ArrayList<JSONObject> read(){
		//Create arraylist to put all objects(persons) from file in
		ArrayList<JSONObject>JSONres = new ArrayList<JSONObject>();
		
		JSONParser parser = new JSONParser();
	
	try{
		 //Parse the file to an object
		 Object allPersons = parser.parse(new FileReader("C:/Users/Pim Dhaen/Desktop/SBuddy/Server-Client With JSON/database.json"));
		 //Make an array of each person in the file
		 JSONArray userArray = (JSONArray) allPersons;
		 
		 
		 for(int i = 0 ; i < userArray.size() ; i ++){
			 
			 JSONres.add((JSONObject)(userArray.get(i)));
		 }
		 
				 
	}catch(Exception e){
		e.printStackTrace();
	}
		return JSONres;
	}
	
	}