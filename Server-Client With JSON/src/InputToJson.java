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
            String UserID = "1pimwwwhn@gmail.com";
            //this is for check if user exist at the end of the server
            int IDLength = UserID.length();

            
            //information for register phase
            obj.put("Firstname", "Pim");
            obj.put("Lastname", "Dhaen");
            obj.put("Email", UserID);
            obj.put("Password", "wordpassd1");
            
            //information for profile tab
            obj.put("Piclink", "https://www.science.unsw.edu.au/files/news/527C868C9284958A22F9E4D448BDDA12.JPG");
            obj.put("Gender", "Male");
            obj.put("Age", "18");
            obj.put("Description", "I love to study with other people!");
            obj.put("CountryOfResidence", "The Netherlands");
            obj.put("CityOfResidence", "Delft");
            
            //contact information
            obj.put("Phone", "0647673112");
            
            //career information
            obj.put("CurrentUniversity", "Technical University of Delft");
            obj.put("CurrentStudy", "Computer Science");
            obj.put("StudyPeriod", "2015-2018");
            
            //Interested courses
            JSONArray courses = new JSONArray();
            courses.add("Redeneren en Logica : 5");
            courses.add("Computer Organization : 3");
            courses.add("Object-Oriented Programming : 3");
            courses.add("OOP Project : 3");
            courses.add("Calculus : 3");
            obj.put("Course list", courses);
            
            //Here things are added together
            userinfo.add(obj);
            mainobj.put(UserID, userinfo);
            
            //String yo = mainobj.toJSONString() +"   "+ IDLength;
            //int foo = Integer.parseInt(yo.substring( yo.lastIndexOf(" ")+1));
            //System.out.println(foo);
            
            return mainobj.toJSONString() +"   "+ IDLength;

    }


}
