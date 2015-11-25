
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

                Object obj = parser.parse(new FileReader(
                        "D:/Users/12056/Desktop/S-Buddy/Java/JSONtotxt/120567wolfert@gmail.com.txt"));

                JSONObject jsonObject = (JSONObject) obj;

                String Firstname = (String) jsonObject.get("Firstname");
                String Lastname = (String) jsonObject.get("Lastname");
                String Email = (String) jsonObject.get("Email");

                System.out.println("Firstname: " + Firstname);
                System.out.println("Lastname: " + Lastname);
                System.out.println("Email: " + Email);
             

            } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
