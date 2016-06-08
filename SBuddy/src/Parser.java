import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Parser {
	
	JSONParser parser;
	
	public Parser() {
		parser = new JSONParser();
	}
	
	public JSONObject parse(String parsable) {
		try {
			return (JSONObject) parser.parse(parsable);
		} catch (ParseException e) {
			return null;
		}
	}
}
