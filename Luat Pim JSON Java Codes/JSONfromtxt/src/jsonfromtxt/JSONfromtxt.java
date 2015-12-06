package jsonfromtxt;

import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONfromtxt 
{

    @SuppressWarnings("unchecked")

    public static void main(String[] args) 
    {
        JSONParser parser = new JSONParser();

        try 
        {

            Object obj = parser.parse(new FileReader("C:/Users/Pim Dhaen/Desktop/SBuddy/Server-Client With JSON/database.json"));
    

            JSONArray ALL = (JSONArray) obj;
            System.out.println(userexists(ALL, "pimdhn@gmail.com"));

            /*
                String Firstname = (String) jsonObject.get("Firstname");
                String Lastname = (String) jsonObject.get("Lastname");
                String Email = (String) jsonObject.get("Email");

                System.out.println("Firstname: " + Firstname);
                System.out.println("Lastname: " + Lastname);
                System.out.println("Email: " + Email);
             */
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    private static boolean userexists(JSONArray jsonArray, String usernameToFind) 
    {// return true if the searched string exists in the database
        return jsonArray.toString().contains(usernameToFind);
    }
}
