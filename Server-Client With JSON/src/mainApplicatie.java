import org.json.simple.JSONObject;

/***
 * Main applicatie zorgt voor de uitvoering van de hele programma.
 * 
 * @author c0debust3rs
 * 
 */
public class mainApplicatie {

	public static void main(String[] args) {

		MatchMaker currentUsers = new MatchMaker();

		// Call class JSON READ which reads from file and gives values to
		// arraylist
		currentUsers.arrayUsers = JSONRead.read();

		// PRINTS out all users from current arraylist ****CAN'T get access to
		// Objects within array yet****Problem with arraylist, need JSON ARRAY**
		for (int i = 0; i < currentUsers.arrayUsers.size(); i++) {
			JSONObject res = currentUsers.arrayUsers.get(i);
			System.out.println(res);
		}

	}

}
