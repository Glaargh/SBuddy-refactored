import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
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

public class User {
	//change this path before you use this class!
	private String databasepath = "database.json";
	private boolean exists;
	private String username;
	private JSONArray Database = new JSONArray();
	private JSONObject mainobj = new JSONObject();
	private JSONArray userinfo = new JSONArray();
  	private JSONObject obj = new JSONObject();
    private int position= -1;
  	public User(String usern)
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
				 		if(mainobj.containsKey(username.trim()))
				 		{
				 			position = i;
				 			userinfo =(JSONArray) new JSONParser().parse(mainobj.get(username).toString());
				 			obj = (JSONObject) new JSONParser().parse(userinfo.get(0).toString());	
				 			exists = true;
				 			break;
				 		}
				 		
				 	}
				 	//return position = -1 if not found
			
	        } catch (Exception e) {exists = false;}
		 	
  	}
  	@Override
  	public String toString()
  	{
  		String str = "";
  		try {
  			str = new JSONParser().parse(Database.get(position).toString()).toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
  		return str;
  	}
  	public boolean getExistStatus(){
  		return exists;
  	}
  	
  	public void changeDatabase(String newFile){
  		databasepath = newFile;
  	}
  	
  	public String getDatabaseFile(){
  		return databasepath;
  	}
  	
  	public String get(String key)
  	{
  		String value = obj.get(key).toString();
  		return value;
  	}
	public String addormodifycourse(String coursename, String description) throws IOException
  	{
  			String response = "false";
  		try {
  			JSONObject courselist = (JSONObject)new JSONParser().parse(obj.get("Course list").toString());
             courselist.put(coursename, description);
             obj.put("Course list", courselist);
			writechanges();
			response = "true";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return response;
  	}
  		public String removecourse(String coursename) throws IOException
  		{
  			String response = "false";
  			try {
  	  			JSONObject courselist = (JSONObject)new JSONParser().parse(obj.get("Course list").toString());
  	             courselist.remove(coursename);
  	             obj.put("Course list", courselist);
  				writechanges();
  				response = "true";
  			} catch (ParseException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			return response;
  		}
  	public JSONArray getCourseList()
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
  	public void modify(String key, String value) throws IOException
  	{
  		obj.put(key, value);
  		writechanges();
  	}
  		

  	public  void writechanges() throws IOException
  	{
  		userinfo.set(0, obj);
  		mainobj.put(username, userinfo);
  		Database.set(position, mainobj);
  		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(Database.toJSONString());
        String prettyJsonString = gson.toJson(je);
      //  System.out.println(prettyJsonString);
         
        FileWriter file = new FileWriter(databasepath, false);
          file.write(prettyJsonString);
         file.close();
  	}
  	
  	
  	
  	
}