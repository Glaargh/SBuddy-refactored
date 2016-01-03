import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class makes an user object out of an emailadress(username)
 * It does not matter if you don't know how it works, it matters that you know how to use it.
 * 
 * How to initialize: 
 * user usr = new user ("youemailadress");
 * 
 * How to access values: 
 * usr.get("attributekey");
 * 
 * How to get courselist(as JSONArray): 
 * usr.getCourseList();
 * 
 * How to get chatlist(as JSONObject): 
 * usr.getChatList();
 * 
 * How to modify a value:
 * usr.modify("key", "value");
 * (all changes will be saved to the database, only the modified key will be changed)
 * 
 * REMEMBER: JSON is different than Json!!!!
 * Do no use JsonObject instead of JSONObject.
 */

public class user {
	//change this path before you use this class!
	static String databasepath = "C:/Users/12056/workspace/Copy of TestServer2/database.json";
	
	static String username;
	static JSONArray Database = new JSONArray();
	static JSONObject mainobj = new JSONObject();
  	static JSONArray userinfo = new JSONArray();
  	static JSONObject obj = new JSONObject();
  	static int position= -1;
  	static String time =  ZonedDateTime.now().format(DateTimeFormatter.ofPattern("E h:ma"));
  	public user(String usern)
  	{
  		username = usern;
  		JSONParser parser = new JSONParser();
		 try 
	        {
			  	Object x = parser.parse(new FileReader(databasepath));
			  	Database = (JSONArray) x;
			  	//Here it starts to search for the index of the provided username
			
				 	for(int i = 0; i<Database.size(); i++)
				 	{
				 		mainobj= (JSONObject) new JSONParser().parse(Database.get(i).toString());	
				 		if(mainobj.containsKey(username))
				 		{
				 			position = i;
				 			userinfo =(JSONArray) new JSONParser().parse(mainobj.get(username).toString());
				 			obj = (JSONObject) new JSONParser().parse(userinfo.get(0).toString());	
				 			break;
				 		}
				 	}
				 	//return position = -1 if not found
				 if(position == -1)
				 {
						System.out.println("Username not exist in database.");
				 }	
	        } catch (Exception e) {e.printStackTrace();}
  	}
  	
  	public static String get(String key)
  	{
  		String value = obj.get(key).toString();
  		return value;
  	}
  	public static JSONObject getChatList()
  	{
  		JSONObject chatobj= null;
		try {
			chatobj = (JSONObject) new JSONParser().parse(obj.get("ChatDatabase").toString());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chatobj;
  	}
  	public static JSONArray getCourseList()
  	{
  		JSONArray coursearray=null;
		try {
			coursearray = (JSONArray) new JSONParser().parse(obj.get("Course list").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return coursearray;
  	}
  	public static void modify(String key, String value)
  	{
  		obj.put(key, value);
  		user.writechanges();
  	}
  		public static void addmessage(String recipent, String message)
  	{
  		try {
			JSONObject chat = (JSONObject)new JSONParser().parse(obj.get("ChatDatabase").toString());
			JSONArray history = (JSONArray)new JSONParser().parse(chat.get(recipent).toString());
			history.add(obj.get("Firstname")+ "("+time+"): " +message);
			chat.put(recipent, history);
			JsonObject chatmodified = (JsonObject) new JsonParser().parse(chat.toString());
			obj.put("ChatDatabase", chatmodified);
			user.writechanges();
	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}

  	public static void writechanges()
  	{
  		userinfo.set(0, obj);
  		mainobj.put(username, userinfo);
  		Database.set(position, mainobj);
  		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(Database.toJSONString());
        String prettyJsonString = gson.toJson(je);
         
         try (FileWriter file = new FileWriter(databasepath, false))
         {
		file.write(prettyJsonString);
         } catch (IOException e) {
        e.printStackTrace();
         }
  	}
  	
  	
  	
  	
}
