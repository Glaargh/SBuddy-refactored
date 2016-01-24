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
			  	String[] arr = LoginCredentials.split("¿");  
			  	User = new user(arr[1]);
			
			  	if(User.get("Password").equals(arr[2]))
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
	
	
	public  String Register(String Stringreg) 
	{// INCOMING-REGISTER¿120567@wolfert.nl¿pass¿Steve¿Lei 
		String respond = "false";
		String[] regarray = Stringreg.split("¿");
		JSONParser parser = new JSONParser();
		JSONArray Database = read(pathdatabase);
    	 if(StringExist(Database,regarray[1]))
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
             obj.put("Firstname", regarray[3]);
             obj.put("Lastname", regarray[4]);
             obj.put("Email", regarray[1]);
             obj.put("Password", regarray[2]);
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
             
          /*   //chat client
             JSONObject chat = new JSONObject();
             String time =  ZonedDateTime.now().format(DateTimeFormatter.ofPattern("E h:ma"));
             JSONArray SBuddybot = new JSONArray();
             SBuddybot.add("SBuddybot"+"("+time+"): " +"Welcome to SBuddy, you have any trouble using this app, please click on 'Help'. "
             		+ "Happy Study!");
             chat.put("SBuddybot", SBuddybot);
             obj.put("ChatDatabase", chat);*/
             
             //Here things are added together
             userinfo.add(obj);
             mainobj.put(regarray[1], userinfo);
             ///////////////////////////////////
             
        	 Database.add(mainobj);
    		 try {
				write(Database, pathdatabase);
			} catch (IOException e) {
				respond = "false";
			}
             user usr = new user (regarray[1]);
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
			modifycommand = modifycommand.substring(modifycommand.indexOf(" ")).trim();
			String key = modifycommand.substring(0, modifycommand.indexOf(" ")).trim();
			String value = modifycommand.substring(modifycommand.indexOf(" ")).trim();
			
			try {
				User.modify(key,value);
			} catch (IOException e) {
				response = "false";
			}
		return response;
		
	}
	public  String get(String modifycommand)
	{
		String key = modifycommand.substring(modifycommand.indexOf(" ")).trim();
		return User.get(key);
	}
	
	public  String addormodifycourse(String modifycommand) throws IOException 
    {
        String[] course = modifycommand.split("¿");
        return User.addormodifycourse(course[1], course[2]);
    }
	public  String removecourse(String modifycommand) throws IOException
	{  String[] course = modifycommand.split("¿");
		return User.removecourse(course[1]);
	}
	public  String getothers(String modifycommand)
    {//INCOMING-FROMOTHERSGET¿pimdhn@gmail.com¿Lastname
        String[] arr = modifycommand.split("¿");
        user otheruser = new user(arr[1]);
        return otheruser.get(arr[2]);
    }
	
    private  boolean StringExist(JSONArray jsonArray, String usernameToFind) 
    {//method to find out if the inserted string (email) already exist in the system.
        return jsonArray.toString().contains(usernameToFind);
    }
    public  String remove(String removecommand)
    {//INCOMING-REMOVE¿jUnittest@gmail.com
    	JSONArray Database = read(pathdatabase);
    	String[] arr = removecommand.split("¿");
    	 String response = "false";
    	 JSONObject mainobj = new JSONObject();
				 	for(int i = 0; i<Database.size(); i++)
				 	{
				 		try {
							mainobj= (JSONObject) new JSONParser().parse(Database.get(i).toString());
						} catch (ParseException e) {
							response = "false";
						}	
				 		if(mainobj.containsKey(arr[1]))
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
		}
		else
		{
			 response = false;
		}		 	
		 return response;
    }
    public  String SearchEngine(String removecommand)
    {//INCOMING-SEARCH¿Email¿pimdhn@gmail.com
    	String[] arr = removecommand.split("¿");
    	JSONArray databaseupdated = read(pathdatabase);
    	ArrayList<String> matchlist = new ArrayList<String>();
    	 String response = "false";
    	 JSONObject mainobj = new JSONObject();
    	JSONArray userinfo = new JSONArray();
    	 JSONObject obj = new JSONObject();
				 	for(int i = 0; i<databaseupdated.size(); i++)
				 	{
				 		try {
							mainobj= (JSONObject) new JSONParser().parse(databaseupdated.get(i).toString());
							userinfo =(JSONArray) new JSONParser().parse(mainobj.get(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1)).toString());
						  	obj = (JSONObject) new JSONParser().parse(userinfo.get(0).toString());	
						} catch (ParseException e) {
							response = "false";
						}
				 		if(SearchEngineTRUEFALSE1(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1),arr[1],arr[2]))
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
		{
				response = true;
		}
		 	
		 return response;
    }
    public  String MatchEngine(String removecommand)
    {//INCOMING-MATCH¿Computer Science¿Technical University of Delft¿Rotterdam
    	String[] arr = removecommand.split("¿");
    	JSONArray databaseupdated = read(pathdatabase);
    	ArrayList<String> matchlist = new ArrayList<String>();
    	 String response = "false";
    	 JSONObject mainobj = new JSONObject();
    	JSONArray userinfo = new JSONArray();
    	 JSONObject obj = new JSONObject();
				 	for(int i = 0; i<databaseupdated.size(); i++)
				 	{
				 		try {
							mainobj= (JSONObject) new JSONParser().parse(databaseupdated.get(i).toString());
							userinfo =(JSONArray) new JSONParser().parse(mainobj.get(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1)).toString());
						  	obj = (JSONObject) new JSONParser().parse(userinfo.get(0).toString());	
						} catch (ParseException e) {
							response = "false";
						}
				 			
				 		if(SearchEngineTRUEFALSE(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1),arr[1])||SearchEngineTRUEFALSE(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1),arr[2])||SearchEngineTRUEFALSE(mainobj.keySet().toString().substring(1,mainobj.keySet().toString().length()-1),arr[3]))
				 		{
				 			matchlist.add(obj.get("Email").toString());
				 		}
				 	}
		 return matchlist.toString();
    }
  
  
}
/*public  JSONObject createjson(String ID, String pass, String first, String last)
{
	JSONArray userinfo = new JSONArray();
    JSONObject obj = new JSONObject();
    JSONObject mainobj = new JSONObject();
    
    //information for register phase
    obj.put("Firstname", first);
    obj.put("Lastname",last);
    obj.put("Email", ID);
    obj.put("Password", pass);
    //information for profile tab
    obj.put("Piclink", "http://www.theblaze.com/wp-content/uploads/2011/10/Facebook-no-profile-picture-icon-620x389.jpg");
    obj.put("Placepic", "http://image.shutterstock.com/display_pic_with_logo/2678914/287804864/stock-vector-school-building-icon-287804864.jpg");
    obj.put("Gender", "Please fill in");
    obj.put("Age", "Please fill in");
    obj.put("Description", "Please fill in");
    obj.put("CountryOfResidence", "The Netherlands");
    obj.put("CityOfResidence", "Please fill in");
    //contact information
    obj.put("Phone", "Please fill in");
    //career information
    obj.put("CurrentUniversity", "Please fill in");
    obj.put("CurrentStudy", "Please fill in");
    obj.put("StudyPeriod", "Please fill in");
    obj.put("JoinDate", "null");
    //Interested courses
    JSONArray courses = new JSONArray();
    courses.add(null);
    obj.put("Course list", courses);        
    //chat client
    JSONObject chat = new JSONObject();
    String time =  ZonedDateTime.now().format(DateTimeFormatter.ofPattern("E h:ma"));
    JSONArray SBuddybot = new JSONArray();
    SBuddybot.add("SBuddybot"+"("+time+"): " +"Welcome to SBuddy, you have any trouble using this app, please click on 'Help'. "
    		+ "Happy Study!");
    chat.put("SBuddybot", SBuddybot);
    obj.put("ChatDatabase", chat);
    //Here things are added together
    userinfo.add(obj);
    mainobj.put(ID, userinfo);
    
    return mainobj;
}*/