package jsontotxt;

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

public class JSONtotxt
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException 
    {
        JSONParser parser = new JSONParser();
        try 
        {
            Object x = parser.parse(new FileReader(
                    "D:/Users/12056/Desktop/GitHub/SBuddy/Luat Pim JSON Java Codes/database.json"));

            JSONArray ALL = (JSONArray) x;
            JSONObject mainobj = new JSONObject();
            JSONArray userinfo = new JSONArray();
            JSONObject obj = new JSONObject();
            
            //fill in your email here instead (you can only use this email once because it will be added into the system)
            String UserID = "pimdrhn@gmail.com";
            Boolean CheckIfExists = new Boolean(userexists(ALL, UserID));
            
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
            ALL.add(mainobj);

            // this will check for whether the given email (user) is already in the system, before creating new user.
            if (CheckIfExists) 
            {
                System.out.print("Sorry, but your email already exist in our system");
            } 
            else 
            {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser jp = new JsonParser();
                JsonElement je = jp.parse(ALL.toJSONString());
                String prettyJsonString = gson.toJson(je);
                try (FileWriter file = new FileWriter("D:/Users/12056/Desktop/GitHub/SBuddy/Luat Pim JSON Java Codes/database.json", false)) 
                {
                    file.write(prettyJsonString);
                    System.out.println("Successfully Copied JSON Object to File...");
                    System.out.println("\nJSON Object: " + mainobj);
                }
            }

        } catch (Exception e) 
        {
            e.printStackTrace();
        }

    }

    private static boolean userexists(JSONArray jsonArray, String usernameToFind) 
    {//method to find out if the inserted string (email) already exist in the system.

        return jsonArray.toString().contains(usernameToFind);
    }

}
