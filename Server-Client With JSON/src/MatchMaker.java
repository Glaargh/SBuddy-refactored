import java.util.ArrayList;

import org.json.simple.JSONObject;

/**
 * Class which has an array of persons and methods to determine matches.
 * 
 * @author c0debust3rs
 * */
public class MatchMaker {

	//For now an arraylist of JSONObjects ***later will be an arraylist of person***
	public ArrayList<JSONObject>arrayUsers;
	
	public MatchMaker(){
		arrayUsers = new ArrayList<JSONObject>();
	}
	
	
}