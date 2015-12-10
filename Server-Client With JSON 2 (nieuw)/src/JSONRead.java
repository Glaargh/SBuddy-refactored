import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONRead {

	//***READ--->*** MOET een USER returnen, arraylist momenteel
	public static ArrayList<JSONArray> read() {
		// Create arraylist to put all objects(persons) from file in
		ArrayList<JSONArray> JSONres = new ArrayList<JSONArray>();

		JSONParser parser = new JSONParser();

		try {
			
			
			// Parse the file to an object
			Object allPersons = parser.parse(new FileReader("database.json"));

			// Make (cast to) a JSON array, mainarray is an array with all users
			JSONArray mainArray = (JSONArray) allPersons;
		
			for (int i = 0; i < mainArray.size(); i++) {

				//Maak een JSONobject van elk user (Met for loop)
				JSONObject userObject = (JSONObject) mainArray.get(i);

				//Paar simpel trukjes om de email van een user te krijgen
				String email = userObject.toString().substring(2);
				
				Scanner sc = new Scanner(email);
				sc.useDelimiter(":");
		
				String emailname = sc.next();

				//Email name bevat nu de email van (user id ) van een user
				emailname = emailname.substring(0, emailname.length() - 1);
				
				//Maak een user array van user object, met de huidige email van for loop.
				JSONArray userArray = (JSONArray)userObject.get(emailname);
				
				//Maak nog een JSON object van de user zodat we toegang krijgen tot all informatie
				JSONObject insideUser = (JSONObject)userArray.get(0);
				
				String countryOfRes = (String)insideUser.get("CountryOfResidence");
				String currentStudy = (String)insideUser.get("CurrentStudy");
				String picLink = (String)insideUser.get("Piclink");
				String description = (String)insideUser.get("Description");
				String studyPeriod = (String)insideUser.get("StudyPeriod");
				String gender = (String)insideUser.get("Gender");
				String firstname = (String)insideUser.get("Firstname");
				int phone = (int)insideUser.get("Phone");
				String cityResidence = (String)insideUser.get("CityOfResidence");
				String currentUni = (String)insideUser.get("CurrentUniversity");
				String age = (String)insideUser.get("Age");
				String password = (String)insideUser.get("Password");
			
				//COURSE LIST MOET NOG GEIMPLENTEERD WORDEN, hangt er vanaf hoe we courses gaan tonen in uml
				
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONres;
	}

}