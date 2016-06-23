import static org.junit.Assert.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class is the test class of the class user.
 * 
 */
public class userTest {

	@Test
	public void testUserExist() {
		User testuser = new User("pimdhn@gmail.com");
		assertFalse(testuser.equals(null));
		assertTrue(testuser instanceof User);
		assertTrue(testuser.getExistStatus());
		User testuser2 = new User("Luat");
		assertFalse(testuser2.equals(""));
		assertFalse(testuser2.getExistStatus());
		
	}
	@Test
	public void testUserNotExist()
	{
		User testuser = new User("thisuserdoesnotexist@gmail.com");
		assertFalse(testuser.getExistStatus());
	}


	@Test
	public void testGet(){
		User testuser = new User("pimdhn@gmail.com");
		String attribute = "Firstname";
		assertEquals("Pim",testuser.get(attribute));
		assertEquals("Pim",testuser.get(attribute));
	}

	@Test
	public void testGet2(){
		User testuser = new User("120567wolfert@gmail.com");
		String attribute = "Firstname";
		assertEquals("Luat",testuser.get(attribute));
	}

	@Test
	public void testGet3(){
		User testuser = new User("pimdhn@gmail.com");
		String attribute = "Lastname";
		assertEquals("Dhaen",testuser.get(attribute));
	}
	
	@Test
	public void testGet4(){
		User testuser = new User("120567wolfert@gmail.com");
		String attribute = "Lastname";
		assertEquals("Nguyen",testuser.get(attribute));
	}

	
	@Test
	public void getDatabaseFileTest(){
		User testuser = new User("pimdhn@gmail.com");
		assertEquals("database.json", testuser.getDatabaseFile());
	}
	
	@Test
	public void changeDatabaseTest(){
		User testuser = new User("test");
		String file = "deomes.json";
		testuser.changeDatabase(file);
		assertEquals(file, testuser.getDatabaseFile());
		testuser.changeDatabase("database.json");
	}
	

}
/*	@Test
public void modifyTest(){
	user testuser = new user("test");
	String attribute = "Firstname";
	String value = "TestFirstName#2";
	try{
	user.modify(attribute, value);
	}
	catch(IOException e){
		
	}
	assertEquals(user.get(attribute), value);
}*/