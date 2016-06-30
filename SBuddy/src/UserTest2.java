import static org.junit.Assert.*;

/*import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/
import org.junit.Before;
import org.junit.Test;

public class UserTest2 {
	User testuser;
	User testuser2;
	User testuser3;
	User testuser4;

	@Before
	public void setUp() throws Exception {
		testuser = new User("pimdhn@gmail.com");
		testuser2 = new User("Luat");
		testuser3 = new User("thisuserdoesnotexist@gmail.com");
		testuser4 = new User("120567wolfert@gmail.com");
	}

	@Test
	public void testUser() {
		assertFalse(testuser.equals(null));
		assertTrue(testuser instanceof User);
		assertTrue(testuser.getExistStatus());
		
		assertFalse(testuser2.equals(""));
		assertFalse(testuser2.getExistStatus());
		
		assertFalse(testuser3.getExistStatus());
	}

	@Test
	public void testToString() {
		assertEquals(testuser.toString(),"{\"pimdhn@gmail.com\":[{\"CountryOfResidence\":\"The Netherlands\",\"CurrentStudy\":\"Computer Science\",\"Piclink\":\"https:\\/\\/scontent.cdninstagram.com\\/hphotos-xft1\\/t51.2885-15\\/s320x320\\/e35\\/11809967_1104443242917133_1328169645_n.jpg\",\"Email\":\"pimdhn@gmail.com\",\"Description\":\"I am an ethusiastic student looking to help and work with others :)\",\"Placepic\":\"http:\\/\\/www.tudelft.nl\\/uploads\\/RTEmagicC_comp.jpg.jpg\",\"Lastname\":\"Dhaen\",\"StudyPeriod\":\"2015-2018\",\"Gender\":\"Male\",\"Firstname\":\"Pim\",\"Phone\":\"0647673112\",\"Course list\":{\"oop project\":\"hell yeah good at it:D!\",\"redeneren en logica\":\"quite good\",\"web en database\":\"pretty good\",\"computer organisation\":\"Really need help, please help1\",\"calculus\":\"I have a good feeling for it, but could use help\"},\"CityOfResidence\":\"Delft\",\"Available\":\"true\",\"CurrentUniversity\":\"Technical University of Delft\",\"Age\":\"19\",\"JoinDate\":\"10:53:PM:3:11:2015\",\"Password\":\"easy\"}]}");
	}

	@Test
	public void testGetExistStatus() {
		assertTrue(testuser.getExistStatus());
		assertFalse(testuser2.getExistStatus());
		assertFalse(testuser3.getExistStatus());
	}

	@Test
	public void testChangeDatabase() {
		User testuser5 = new User("test");
		String file = "deomes.json";
		testuser5.changeDatabase(file);
		assertEquals(file, testuser5.getDatabaseFile());
		testuser5.changeDatabase("database.json");
	}

	@Test
	public void testGetDatabaseFile() {
		assertEquals("database.json", testuser.getDatabaseFile());
	}

	@Test
	public void testGet() {
		String attribute = "Firstname";
		assertEquals("Pim",testuser.get(attribute));
		assertEquals("Luat",testuser4.get(attribute));
		
		String attribute2 = "Lastname";
		assertEquals("Dhaen",testuser.get(attribute2));
		assertEquals("Nguyen",testuser4.get(attribute2));
	}

	//The methods these commented-out methods are supposed to test
	//were deemed untestable without refactoring/extensive (100+ lines) code duplication.
	//As these to-test methods involved no branches, this was deemed acceptable.
	//The problem with testing these methods is that for these methods to work,
	//functionality implemented by ServerMethods is necessary, but accessing the user involved is not possible using these methods.
	//As such, calling the to-test methods is not possible when trying to use the ServerMethods.
/*	@Test
	public void testAddormodifycourse() {
		User testuser6 = new User("test");
		try {
			testuser6.addormodifycourse("Calculus", "Difficult");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRemovecourse() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCourseList() {
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		JSONArray readin = null;
		try {
			Object x = parser.parse(new FileReader("database.json"));
			readin = (JSONArray) x;
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		JSONArray Database = readin;
        JSONObject mainobj = new JSONObject();
        JSONObject courses = new JSONObject();   
		JSONArray userinfo = new JSONArray();
        obj.put("Course list", courses);        
        
        userinfo.add(obj);
        String id = "test";
        mainobj.put(id, userinfo);
        Database.add(mainobj);
        User usr = new User (id);
		System.out.println(usr.getCourseList().toString());
		//JSONArray array = new JSONArray();
		//array.add
		fail("Not yet implemented");
	}

	@Test
	public void testModify() {
		User testuser6 = new User("test");
		String key = "Firstname";
		String value = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("h:m:a:d:M:y"));
		try {
			testuser6.modify(key, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String key = "Firstname";
		//String value = "TestFirstName#2";
		//String file = "deomes.json";
		//testuser6.changeDatabase(file);
		//try{
		//	testuser6.modify(key, value);
		//}
		//catch(IOException e){
		//	fail("IOException encountered");
		//}
		//assertEquals(testuser6.get(key), value);
		//testuser6.changeDatabase("database.json");
	}

	//@Test
	//public void testWritechanges() {
	//	fail("Not yet implemented");
	//}
*/
}
