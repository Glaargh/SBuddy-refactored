import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InputToJson 
{

	
	static String STRING;
    @SuppressWarnings("unchecked")
    //This methods return the Input information from the user as a Json String.
    public static String InputtoJSON() throws IOException  
    {
            JSONArray userinfo = new JSONArray();
            JSONObject obj = new JSONObject();
            JSONObject mainobj = new JSONObject();
            //fill in your email here instead (you can only use this email once because it will be added into the system)
            String UserID = "pieemdhn@gmail.com";
            //this is for check if user exist at the end of the server
            int IDLength = UserID.length();
            
            //information for register phase
            obj.put("Firstname", "Pim");
            obj.put("Lastname", "Dhaen");
            obj.put("Email", UserID);
            obj.put("Password", "wordpassd1");
            
            //information for profile tab
            obj.put("Piclink", "null");
            obj.put("Gender", "null");
            obj.put("Age", "null");
            obj.put("Description", "null");
            obj.put("CountryOfResidence", "The Netherlands");
            obj.put("CityOfResidence", "null");
            
            //contact information
            obj.put("Phone", "null");
            
            //career information
            obj.put("CurrentUniversity", "null");
            obj.put("CurrentStudy", "null");
            obj.put("StudyPeriod", "null");
            
            //Interested courses
            JSONArray courses = new JSONArray();
            courses.add(null);
            obj.put("Course list", courses);
            
            //Here things are added together
            userinfo.add(obj);
            mainobj.put(UserID, userinfo);
            
      
            return mainobj.toJSONString() +"   "+ IDLength;

    }


}
