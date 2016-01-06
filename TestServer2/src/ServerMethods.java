import java.io.FileReader;
import java.io.FileWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ServerMethods {
	//Put your database path under here(Change \ to / if you gonna copy and paste the path)
static String DatabasePath ="C:/Users/12056/workspace/Copy of TestServer2/database.json";	

static user User = new user(null);
	public static String Login(String LoginCredentials) 
	{
		String loginrespond = null;
		JSONParser parser = new JSONParser();
		 try 
	        {
			  	Object x = parser.parse(new FileReader(DatabasePath));
			  	JSONArray Database = (JSONArray) x;
			  	String[] arr = LoginCredentials.split("          ");  
			  	String UserID = arr[0].substring(14);
			  	String Password = arr[1];
			  	if(StringExist(Database,UserID) && StringExist(Database,Password))
			  	{
			  		User = new user(UserID);
			  		loginrespond= "Welcome back! " + User.toString();

			  		
			  	}
			  	else 
			  	{
			  		loginrespond=  "Sorry, your email or password doesn't match. Try again.";
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
		 Object x = parser.parse(new FileReader(DatabasePath));
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
             
             try (FileWriter file = new FileWriter(DatabasePath, false)) 
             {
                 file.write(prettyJsonString);
             }
             user usr = new user (JsonObjectFromClient.substring(2, 2+IDLength).trim());
             usr.modify("JoinDate", ZonedDateTime.now().format(DateTimeFormatter.ofPattern("h:m:a:d:M:y")));
    	 		}
    	
	        } catch (Exception e) {e.printStackTrace();}
		  
		  return loginrespond;
	}
	public static String modify(String modifycommand)
	{
		modifycommand = modifycommand.substring(modifycommand.indexOf(" ")).trim();
		String key = modifycommand.substring(0, modifycommand.indexOf(" ")).trim();
		String value = modifycommand.substring(modifycommand.indexOf(" ")).trim();
		user.modify(key,value);
		return "Successfully changed value";
	}
	
	
    private static boolean StringExist(JSONArray jsonArray, String usernameToFind) 
    {//method to find out if the inserted string (email) already exist in the system.

        return jsonArray.toString().contains(usernameToFind);
    }
}
