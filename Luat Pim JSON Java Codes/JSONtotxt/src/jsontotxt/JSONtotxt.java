
package jsontotxt;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileWriter;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
public class JSONtotxt 
{
 
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException 
        {
                JSONObject mainobj = new JSONObject();
            
                JSONArray user = new JSONArray();
                
                JSONObject obj = new JSONObject();
                     
                 //information for register phase
		obj.put("Firstname","Luat");
                obj.put("Lastname", "Nguyen");
                obj.put("Email","120567wolfert@gmail.com");
                obj.put("Password","iamironman96");
                //information for profile tab
                obj.put("Piclink", "http://icons.iconarchive.com/icons/aha-soft/free-large-boss/512/Admin-icon.png");
                obj.put("Gender","Male");
                obj.put("Age","19");
                obj.put("Description","Highly motivated student seeking (internship) opportunities in the field of Computer Communications or Artificial Intelligence. Interested in improving and combining leadership, communications skills and interpersonal abilities to positively contribute to an organization's cooperate, while gaining valuable experience. Value a leadership with strong vision and virtue ethics. Trilingual: fluent in English, Dutch, Vietnamese and a beginner in Chinese.");
                obj.put("CountryOfResidence","The Netherlands");
                obj.put("CityOfResidence","Rotterdam");
                //contact information
                obj.put("Phone","0633680503");
                //career information
                obj.put("CurrentUniversity","Technical University of Delft");
                obj.put("CurrentStudy","Computer Science");
                obj.put("StudyPeriod","2015-2019");
                //Interested courses
                JSONArray courses = new JSONArray();
		courses.add("Calculus : 4");
		courses.add("Computer Organization : 5");
		courses.add("Object-Oriented Programming : 3");
		obj.put("Course list", courses);
                user.add(obj);
                
                mainobj.put("120567wolfert@gmail.com", user);
             
                
		
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(mainobj.toJSONString());
                String prettyJsonString = gson.toJson(je);
 
		try (FileWriter file = new FileWriter("D:/Users/12056/Desktop/GitHub/SBuddy/Luat Pim JSON Java Codes/database.json")) 
                {
			file.write(prettyJsonString);
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + mainobj);
		}
	}
}
