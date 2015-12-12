import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Class which has an array of persons and methods to determine matches.
 * 
 * @author c0debust3rs
 */
public class MatchMaker {

	// **READ--->** For now an arraylist of JSONObjects ***later will be an
	// arraylist of type
	// User***
	public ArrayList<JSONArray> arrayUsers;

	/**
	 * Constructor MatchMaker, initiliseert een arraylist van type User.
	 */
	public MatchMaker() {
		arrayUsers = new ArrayList<JSONArray>();
	}

}