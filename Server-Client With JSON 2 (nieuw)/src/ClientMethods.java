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

public class ClientMethods 
{
    @SuppressWarnings("unchecked")

    public static String Login(String ID, String Pass) throws IOException  
    {
            String UserID = ID;
            String Password = Pass;
            String LoginCredentials = UserID+"          "+Password;
            return LoginCredentials;
    }
    
    public static String Register(String Firstname, String Lastname, String ID, String Pass) throws IOException  
    {
            JSONArray userinfo = new JSONArray();
            JSONObject obj = new JSONObject();
            JSONObject mainobj = new JSONObject();

            //this is for check if user exist at the end of the server
            int IDLength = ID.length();
            
            //information for register phase
            obj.put("Firstname", Firstname);
            obj.put("Lastname", Lastname);
            obj.put("Email", ID);
            obj.put("Password", Pass);
            
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
            mainobj.put(ID, userinfo);
            
            return mainobj.toJSONString() +"   "+ IDLength;

    }


}
