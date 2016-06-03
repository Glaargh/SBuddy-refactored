import org.json.simple.JSONObject;

public class ClientMethods 
{
	
	/**
	 * Returns a JSONObject.toString() for the login credentials
	 * @param ID
	 * @param Pass
	 * @return String
	 */
    @SuppressWarnings("unchecked")
	public static String Login(String ID, String Pass) 
    {
    	JSONObject format = new JSONObject();
    	format.put("action", "login");
    	format.put("id", ID);
    	format.put("password", Pass);
    	return format.toString();
    }
    
    /**
	 * Returns a JSONObject.toString() for the registration credentials
	 * @param Firstname
	 * @param Lastname
	 * @param ID
	 * @param Pass
	 * @return String
	 */
    @SuppressWarnings("unchecked")
	public static String Register(String Firstname, String Lastname, String ID, String Pass)
    {
    	JSONObject format = new JSONObject();
    	format.put("action", "register");
    	format.put("id", ID);
    	format.put("password", Pass);
    	format.put("firstname", Firstname);
    	format.put("lastname", Lastname);
    	return format.toString();
    }
    
    /**
	 * Returns a JSONObject.toString() for a get command in the database
	 * @param key
	 * @return String
	 */
    @SuppressWarnings("unchecked")
	public static String get(String key) {
    	JSONObject format = new JSONObject();
    	format.put("action", "get");
    	format.put("key", key);
    	return format.toString();
    }
    
    /**
     * Returns JSONObject.toString() to change a key with a 
     * value to a new value in the database
     * @param key
     * @param value
     * @return String
     */
    @SuppressWarnings("unchecked")
	public static String change(String key, String value) {
    	JSONObject format = new JSONObject();
    	format.put("action", "change");
    	format.put("key", key);
    	format.put("value", value);
    	return format.toString();
    }
    
    /**
     * Returns JSONObject.toString() to get information key about a user id
     * @param id
     * @param key
     * @return String
     */
    @SuppressWarnings("unchecked")
	public static String getOther(String id,String key) {
    	JSONObject format = new JSONObject();
    	format.put("action", "getother");
    	format.put("id", id);
    	format.put("key", key);
    	return format.toString();
    }
    
    /**
     * Returns JSONObject.toString() to add or modify a course
     * @param head
     * @param desc
     * @return String
     */
    @SuppressWarnings("unchecked")
	public static String courseChange(String head, String desc) {
    	JSONObject format = new JSONObject();
    	format.put("action", "changecourse");
    	format.put("head", head);
    	format.put("description", desc);
    	return format.toString();
    }
    
    /**
     * Returns JSONObject.toString() to remove a course
     * @param course
     * @return String
     */
    @SuppressWarnings("unchecked")
	public static String courseRemove(String course) {
    	JSONObject format = new JSONObject();
    	format.put("action", "removecourse");
    	format.put("course", course);
    	return format.toString();
    }
    
    /**
     * Returns JSONObject.toString() for matching
     * @param study
     * @param uni
     * @param city
     * @return String
     */
    @SuppressWarnings("unchecked")
	public static String match(String study,String uni,String city) {
    	JSONObject format = new JSONObject();
    	format.put("action", "match");
    	format.put("study", study);
    	format.put("uni", uni);
    	format.put("city", city);
    	return format.toString();
    }
    
    /**
     * Returns JSONObject.toString() for searching
     * @param option
     * @param value
     * @return String
     */
    @SuppressWarnings("unchecked")
	public static String search(String option, String value) {
    	JSONObject format = new JSONObject();
    	format.put("action", "search");
    	format.put("option", option);
    	format.put("value", value);
    	return format.toString();
    }
    
    /**
     * Returns JSONObject.toString() for removing an account
     * @param id
     * @return String
     */
    @SuppressWarnings("unchecked")
	public static String removeAccount(String id) {
    	JSONObject format = new JSONObject();
    	format.put("action", "removeaccount");
    	format.put("id", id);
    	return format.toString();
    }
}
