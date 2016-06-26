import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
public class ServerMethods {
	 //Put your database path under here(Change \ to / if you gonna copy and paste the path)
private  String pathdatabase = "database.json";
private  user User;
	
	public void setDatabase(String input){
		pathdatabase = input;
	}
	
	public String getDatabase(){
		return pathdatabase;
	}

	public  JSONArray read(String path)
	{
		JSONParser parser = new JSONParser();
		JSONArray readin = null;
		try {
			Object x = parser.parse(new FileReader(path));
			readin = (JSONArray) x;
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return readin;
	}
	public  String write(JSONArray out, String path) throws IOException
	{
		String response = null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(out.toJSONString());
        String prettyJsonString = gson.toJson(je);
      FileWriter file = new FileWriter(path, false);
        
            file.write(prettyJsonString);
            response= "true";
            file.close();
        
        return response;
	}
	public  String Login(String LoginCredentials) 
	{
		String loginrespond = null;
		try 
	        {
				JSONParser parser = new JSONParser();
				
				JSONObject json = (JSONObject) parser.parse(LoginCredentials);
				String id = (String) json.get("id");
				String password = (String) json.get("password");
				
			  	User = new user(id);
			
			  	if(User.get("Password").equals(password))
			  	{
			  		loginrespond= "true";
			  	}
			  	else 
			  	{
			  		loginrespond=  "false";
			  	}
	        } catch (Exception e) {return "false";}
		return loginrespond;
	}
	
	
	public  String Register(String Stringreg) throws ParseException 
	{// INCOMING-REGISTER¿120567@wolfert.nl¿pass¿Steve¿Lei 
		String respond = "false";
		JSONParser parser = new JSONParser();

		JSONObject json = (JSONObject) parser.parse(Stringreg);
		
		String id = (String) json.get("id");
		String password = (String) json.get("password");
		String fName = (String) json.get("firstname");
		String lName = (String) json.get("lastname");
			
		JSONArray Database = read(pathdatabase);
    	 if(StringExist(Database,id))
    	 {
    		 respond = "false";
    	 }
    	 else
    	 {
    		 respond = "true";
    		 //////////HERE A NEW JSON IS CREATED FROM THE REGISTER INFO\\\\\\\\\\
    		 
    		 JSONArray userinfo = new JSONArray();
             JSONObject obj = new JSONObject();
             JSONObject mainobj = new JSONObject();
             
             //information for register phase
             try {
             obj.put("Firstname",fName);
             obj.put("Lastname", lName);
             obj.put("Email", id);
             obj.put("Password",password);
         	} catch (Exception e) {
				respond = "false";
			}
             //information for profile tab
             obj.put("Piclink", "http://i67.tinypic.com/2ug08eu.jpg");
             obj.put("Placepic", "http://i67.tinypic.com/15fje5g.jpg");
             obj.put("Gender", "Click to modify");
             obj.put("Age", "Click to modify");
             obj.put("Description", "Click to modify");
             obj.put("CountryOfResidence", "Click to modify");
             obj.put("CityOfResidence", "Click to modify");
             //contact information
             obj.put("Phone", "Click to modify");
             //career information
             obj.put("CurrentUniversity", "Click to modify");
             obj.put("CurrentStudy", "Click to modify");
             obj.put("StudyPeriod", "Click to modify");
             obj.put("JoinDate", "null");
             obj.put("Available", "false");
             //Interested courses
             JSONObject courses = new JSONObject();    
             obj.put("Course list", courses);        
             
             userinfo.add(obj);
             mainobj.put(id, userinfo);
             ///////////////////////////////////
             
        	 Database.add(mainobj);
    		 try {
				write(Database, pathdatabase);
			} catch (IOException e) {
				respond = "false";
			}
             user usr = new user (id);
             try {
				usr.modify("JoinDate", ZonedDateTime.now().format(DateTimeFormatter.ofPattern("h:m:a:d:M:y")));
            	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				respond = "false";
			}
    	 		}
	       
		  
		  return respond;
	}
	
	public  String modify(String modifycommand)
	{
		String response = "true";
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(modifycommand);
			String key = (String) json.get("key");
			String value = (String) json.get("value");
			User.modify(key,value);
		} catch (ParseException|IOException e1) {
			response = "false";
		}
		return response;
		
	}
	public  String get(String modifycommand)
	{
		JSONParser parser = new JSONParser();
		JSONObject json;
		
		try {
			json = (JSONObject) parser.parse(modifycommand);
			String key = (String) json.get("key");
			return User.get(key);
		} catch (ParseException e) {
			return "false";
		}
		
	}
	
	public  String addormodifycourse(String modifycommand) throws IOException 
    {
		
		JSONParser parser = new JSONParser();
		JSONObject json;
		
		try {
			json = (JSONObject) parser.parse(modifycommand);
			String head = (String) json.get("head");
			String description = (String) json.get("description");
			return User.addormodifycourse(head, description);
		} catch (ParseException e) {
			return "false";
		}
    }
	
	public  String removecourse(String modifycommand) throws IOException
	{  
		JSONParser parser = new JSONParser();
		JSONObject json;
		
		try {
			json = (JSONObject) parser.parse(modifycommand);
			String course = (String) json.get("course");
			return User.removecourse(course);
		} catch (ParseException e) {
			return "false";
		}
		
	}
	public  String getothers(String modifycommand)
    {//INCOMING-FROMOTHERSGET¿pimdhn@gmail.com¿Lastname
		JSONParser parser = new JSONParser();
		JSONObject json;
		
		try {
			json = (JSONObject) parser.parse(modifycommand);
			String key = (String) json.get("key");
			String id = (String) json.get("id");
			System.out.println(key);
	        user otheruser = new user(id);
	        return otheruser.get(key);
		} catch (ParseException e) {
			return "false";
		}
    }
	
	
	
    private  boolean StringExist(JSONArray jsonArray, String usernameToFind) 
    {//method to find out if the inserted string (email) already exist in the system.
        return jsonArray.toString().contains(usernameToFind);
    }
 
    public  String remove(String removecommand)
    {//INCOMING-REMOVE¿jUnittest@gmail.com
    	JSONArray Database = read(pathdatabase);
    	String response = "false";
    	JSONObject mainobj = new JSONObject();
    	for(int i = 0; i<Database.size(); i++)
    	{
    		String id = "";
    		try {
    			JSONObject json = (JSONObject) new JSONParser().parse(removecommand);
    			id = (String) json.get("id");
    			
    			mainobj= (JSONObject) new JSONParser().parse(Database.get(i).toString());
    		} catch (ParseException e) {
    			response = "false";
    		}	
    		if(mainobj.containsKey(id))
    		{
    			Database.remove(i);	
    			try {
    				write(Database, pathdatabase);
    			} catch (IOException e) {
    				response = "false";
    			} 			
    			response= "true";
    			break;
    		}
    	}
    	return response;
    }
    
    
    public  boolean SearchEngineTRUEFALSE1(String username, String key, String tosearch)
    {
    	JSONArray databaseupdated = read(pathdatabase);
    	JSONObject mainobj = new JSONObject();
    	JSONArray userinfo = new JSONArray();
    	JSONObject obj = new JSONObject();
    	boolean response = false;
    	user usertosearch = new user(username);
    	
		if((usertosearch.get(key).toLowerCase().contains(tosearch.toLowerCase().trim())))
		{
			response = true;
		} else {
			response = false;
		}		 	
		return response;
    }
    
    public  String SearchEngine(String removecommand)
    {//INCOMING-SEARCH¿Email¿pimdhn@gmail.com
    	JSONArray databaseupdated = read(pathdatabase);
    	ArrayList<String> matchlist = new ArrayList<String>();
    	JSONObject mainobj = new JSONObject();
    	JSONArray userinfo = new JSONArray();
    	JSONObject obj = new JSONObject();
    	for(int i = 0; i<databaseupdated.size(); i++)
    	{
    		String option = "";
    		String value = "";
    		try {
    			JSONObject json = (JSONObject) new JSONParser().parse(removecommand);
    			option = (String) json.get("option");
    			value = (String) json.get("value");
    			mainobj= (JSONObject) new JSONParser().parse(databaseupdated.get(i).toString());
    			userinfo =(JSONArray) new JSONParser().parse(mainobj.get(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1)).toString());
    			obj = (JSONObject) new JSONParser().parse(userinfo.get(0).toString());	
    		} catch (ParseException e) {
    			return "false";
    		}
    		if(SearchEngineTRUEFALSE1(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1),option,value))
    		{
    			matchlist.add(obj.get("Email").toString());
    		}
    	}
    	return matchlist.toString();
    }
    
    public  boolean SearchEngineTRUEFALSE(String username, String tosearch)
    {
    	JSONArray databaseupdated = read(pathdatabase);
    	JSONObject mainobj = new JSONObject();
    	JSONArray userinfo = new JSONArray();
    	JSONObject obj = new JSONObject();
    	boolean response = false;
    	user usertosearch = new user(username);
    	
    	if(usertosearch.get("Available").equals("true")&&usertosearch.toString().toLowerCase().contains(tosearch.toLowerCase()))
    		response = true;
		 	
		 return response;
    }
    
    public  String MatchEngine(String removecommand)
    {//INCOMING-MATCH¿Computer Science¿Technical University of Delft¿Rotterdam
    	
    	JSONArray databaseupdated = read(pathdatabase);
    	ArrayList<String> matchlist = new ArrayList<String>();
    	JSONObject mainobj = new JSONObject();
    	JSONArray userinfo = new JSONArray();
    	JSONObject obj = new JSONObject();
    	for(int i = 0; i<databaseupdated.size(); i++)
    	{
    		String study;
    		String uni;
    		String city;
    		try {
    			JSONParser parser = new JSONParser();
    			JSONObject json = (JSONObject) parser.parse(removecommand);
    			
    			study = (String) json.get("study");
    			uni = (String) json.get("uni");
    			city = (String) json.get("city");
				 			
    			mainobj= (JSONObject) parser.parse(databaseupdated.get(i).toString());
    			userinfo =(JSONArray) parser.parse(mainobj.get(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1)).toString());
    			obj = (JSONObject) parser.parse(userinfo.get(0).toString());	
						  	
    			if(SearchEngineTRUEFALSE(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1),study)||SearchEngineTRUEFALSE(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1),uni)||SearchEngineTRUEFALSE(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1),city))
    				matchlist.add(obj.get("Email").toString());
    		} catch (ParseException e) {
    			return "false";
    		}
    		
    		
    	}
    	return matchlist.toString();
    }	
}