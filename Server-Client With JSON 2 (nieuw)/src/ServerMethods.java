import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ServerMethods {
	
	@SuppressWarnings("unchecked")
	public static String Login(String LoginCredentials) 
	{
		String loginrespond = null;
		JSONParser parser = new JSONParser();
		 try 
	        {
			  	Object x = parser.parse(new FileReader(
			  	"D:/Users/12056/Desktop/GitHub/SBuddy/Server-Client With JSON 2 (nieuw)/database.json"));
			  	JSONArray Database = (JSONArray) x;
			  	String[] arr = LoginCredentials.split("          ");  
			  	String UserID = arr[0].substring(14);
			  	String Password = arr[1];
			  	if(StringExist(Database,UserID) && StringExist(Database,Password))
			  	{
			  		loginrespond= "Welcome back!";
			  	}
			  	else 
			  	{
			  		loginrespond=  "Sorry, your email doesn't exist in our system";
			  	}
	        } catch (Exception e) {e.printStackTrace();}
		return loginrespond;
	}
	public static String Register(String JsonObject) 
	{
		String loginrespond = null;
		JSONParser parser = new JSONParser();
		  try 
	        {
		 Object x = parser.parse(new FileReader(
                "D:/Users/12056/Desktop/GitHub/SBuddy/Server-Client With JSON 2 (nieuw)/database.json"));
		 JSONArray Database = (JSONArray) x;
    	 int IDLength = Integer.parseInt(JsonObject.substring( JsonObject.lastIndexOf(" ")+1));   	 
    	 String JsonObjectFromClient = JsonObject.substring(17, JsonObject.length()- 4 );
    	 if(StringExist(Database, JsonObjectFromClient.substring(2, 2+IDLength )))
    	 {
    		 loginrespond = "Sorry, but your email already exist in our system";
    	 }
    	 else
    	 {
    		 loginrespond = "Thank you for joining!";
    		 JSONObject json = (JSONObject)new JSONParser().parse(JsonObjectFromClient);
        	 Database.add(json);
    		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
             JsonParser jp = new JsonParser();
             JsonElement je = jp.parse(Database.toJSONString());
             String prettyJsonString = gson.toJson(je);
             
             try (FileWriter file = new FileWriter("D:/Users/12056/Desktop/GitHub/SBuddy/Server-Client With JSON 2 (nieuw)/database.json", false)) 
             {
                 file.write(prettyJsonString);
             }
    	 		}
    	
	        } catch (Exception e) {e.printStackTrace();}
		  
		  return loginrespond;
	}

	
	
	
	
	
	
	
	
    private static boolean StringExist(JSONArray jsonArray, String usernameToFind) 
    {//method to find out if the inserted string (email) already exist in the system.

        return jsonArray.toString().contains(usernameToFind);
    }
}
