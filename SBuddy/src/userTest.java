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
		user testuser = new user("pimdhn@gmail.com");
		assertFalse(testuser.equals(null));
		assertTrue(testuser instanceof user);
		assertTrue(testuser.getExistStatus());
		user testuser2 = new user("Luat");
		assertFalse(testuser2.equals(""));
		assertFalse(testuser2.getExistStatus());
		
	}
	@Test
	public void testUserNotExist()
	{
		user testuser = new user("thisuserdoesnotexist@gmail.com");
		assertFalse(testuser.getExistStatus());
	}


	@Test
	public void testGet(){
		user testuser = new user("pimdhn@gmail.com");
		String attribute = "Firstname";
		assertEquals("Pim",testuser.get(attribute));
		assertEquals("Pim",testuser.get(attribute));
	}

	@Test
	public void testGet2(){
		user testuser = new user("120567wolfert@gmail.com");
		String attribute = "Firstname";
		assertEquals("Luat",testuser.get(attribute));
	}

	@Test
	public void testGet3(){
		user testuser = new user("pimdhn@gmail.com");
		String attribute = "Lastname";
		assertEquals("Dhaen",testuser.get(attribute));
	}
	
	@Test
	public void testGet4(){
		user testuser = new user("120567wolfert@gmail.com");
		String attribute = "Lastname";
		assertEquals("Nguyen",testuser.get(attribute));
	}

	
	@Test
	public void getDatabaseFileTest(){
		user testuser = new user("pimdhn@gmail.com");
		assertEquals("database.json", testuser.getDatabaseFile());
	}
	
	@Test
	public void changeDatabaseTest(){
		user testuser = new user("test");
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