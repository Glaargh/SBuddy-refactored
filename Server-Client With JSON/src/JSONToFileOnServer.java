import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JSONToFileOnServer {
	
	@SuppressWarnings("unchecked")
	public static void TOJSONDATABASE(String JsonObject) 
	{
		JSONArray Database =null;
		JSONParser parser = new JSONParser();
		  try 
	        {
		 Object x = parser.parse(new FileReader(
                "D:/Users/12056/Desktop/GitHub/SBuddy/Server-Client With JSON/database.json"));
    	 Database = (JSONArray) x;
    	 int IDLength = Integer.parseInt(JsonObject.substring( JsonObject.lastIndexOf(" ")+1));   	 
    	 String JsonObjectFromClient = JsonObject.substring(13, JsonObject.length()- 4 );
    	 if(userexists(Database, JsonObjectFromClient.substring(2, 2+IDLength )))
    	 {
    		 System.out.print("Sorry, but your email already exist in our system");
    		 return;
    	 }
    	 JSONObject json = (JSONObject)new JSONParser().parse(JsonObjectFromClient);
    	 Database.add(json);
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
         JsonParser jp = new JsonParser();
         JsonElement je = jp.parse(Database.toJSONString());
         String prettyJsonString = gson.toJson(je);
         
         try (FileWriter file = new FileWriter("D:/Users/12056/Desktop/GitHub/SBuddy/Server-Client With JSON/database.json", false)) 
         {
             file.write(prettyJsonString);
             System.out.println("Successfully Copied JSON Object to File...");
             System.out.print("\nJSON Object: " + JsonObjectFromClient);
         }
	        } catch (Exception e) {e.printStackTrace();}
	}

    private static boolean userexists(JSONArray jsonArray, String usernameToFind) 
    {//method to find out if the inserted string (email) already exist in the system.

        return jsonArray.toString().contains(usernameToFind);
    }
}
