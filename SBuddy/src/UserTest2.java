import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.JSONArray;
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

}
