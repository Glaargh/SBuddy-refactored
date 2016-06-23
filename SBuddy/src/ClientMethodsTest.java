import static org.junit.Assert.*;
import org.junit.Test;

public class ClientMethodsTest extends ClientMethods {

	

	@Test
	public void testLogin() {
		assertEquals("{\"password\":\"naqib1\",\"action\":\"login\",\"id\":\"naqib@hotmail.com\"}", Login("naqib@hotmail.com",  "naqib1"));
		assertNotEquals("{\"password\":\"naqib1\",\"action\":\"login\",\"id\":\"naqib@hotmail.com\"}", Login("naqib@hotmail.com", "fatima"));
		assertNotEquals("{\"password\":\"naqib1\",\"action\":\"login\",\"id\":\"naqib@hotmail.com\"}", Login("fatima@gmail.com", "naqib1"));
		assertNotEquals("{\"password\":\"naqib1\",\"action\":\"login\",\"id\":\"naqib@hotmail.com\"}", Login("fatima@gmail.com", "fatima"));
	}
	
	
	@Test
	public void testRegister(){
		//System.out.println(Register("Naqib",  "Zarin", "naqib@hotmail.com", "naqib1"));
		assertEquals("{\"password\":\"naqib1\",\"firstname\":\"Naqib\",\"action\":\"register\",\"id\":\"naqib@hotmail.com\",\"lastname\":\"Zarin\"}", Register("Naqib", "Zarin", "naqib@hotmail.com", "naqib1"));
		assertNotEquals("{\"password\":\"naqib1\",\"firstname\":\"Naqib\",\"action\":\"register\",\"id\":\"naqib@hotmail.com\",\"lastname\":\"Zarin\"}", Register("Fatima", "Zarin", "naqib@hotmail.com", "naqib1"));
		assertNotEquals("{\"password\":\"naqib1\",\"firstname\":\"Naqib\",\"action\":\"register\",\"id\":\"naqib@hotmail.com\",\"lastname\":\"Zarin\"}", Register("Naqib", "Fatima", "naqib@hotmail.com", "naqib1"));
		assertNotEquals("{\"password\":\"naqib1\",\"firstname\":\"Naqib\",\"action\":\"register\",\"id\":\"naqib@hotmail.com\",\"lastname\":\"Zarin\"}", Register("Fatima", "", "naqib@hotmail.com", "naqib1"));
		assertNotEquals("{\"password\":\"naqib1\",\"firstname\":\"Naqib\",\"action\":\"register\",\"id\":\"naqib@hotmail.com\",\"lastname\":\"Zarin\"}", Register("Naqib", "Zarin", "fatima@gmail.com", "naqib1"));
		assertNotEquals("{\"password\":\"naqib1\",\"firstname\":\"Naqib\",\"action\":\"register\",\"id\":\"naqib@hotmail.com\",\"lastname\":\"Zarin\"}", Register("Naqib", "Zarin", "naqib@hotmail.com", "fatima1"));
		assertNotEquals("{\"password\":\"naqib1\",\"firstname\":\"Naqib\",\"action\":\"register\",\"id\":\"naqib@hotmail.com\",\"lastname\":\"Zarin\"}", Register("Fatima", "", "fatima@gmail.com", "fatima"));
	}
	
	@Test
	public void testGet(){
		//System.out.println(get("Naqib"));
		assertEquals("{\"action\":\"get\",\"key\":\"Naqib\"}", get("Naqib"));
		assertNotEquals("{\"action\":\"get\",\"key\":\"Naqib\"}", get("Zarin"));
		assertEquals("{\"action\":\"get\",\"key\":\"Zarin\"}", get("Zarin"));
	}
	
	@Test
	public void testChange(){
		//System.out.println(change("Available", "Yes, contact me now"));
		assertEquals("{\"action\":\"change\",\"value\":\"Yes, contact me now\",\"key\":\"Available\"}" ,  change("Available", "Yes, contact me now"));
		assertNotEquals("{\"action\":\"change\",\"value\":\"Yes, contact me now\",\"key\":\"Available\"}" ,  change("No  :(  Please check back later.", "Yes, contact me now"));
	}
	
	@Test
	public void testGetOther(){
		//System.out.println(getOther("Firstname", "Naqib"));
		assertEquals("{\"action\":\"getother\",\"id\":\"Firstname\",\"key\":\"Naqib\"}", getOther("Firstname", "Naqib"));
		assertNotEquals("{\"action\":\"getother\",\"id\":\"Firstname\",\"key\":\"Naqib\"}", getOther("Lastname", "Naqib"));
		assertNotEquals("{\"action\":\"getother\",\"id\":\"Firstname\",\"key\":\"Naqib\"}", getOther("Firstname", "Zarin"));
		assertEquals("{\"action\":\"getother\",\"id\":\"Lastname\",\"key\":\"Zarin\"}", getOther("Lastname", "Zarin"));
		}
	
	@Test 
	public void testCourseChange(){
		//System.out.println(courseChange("Calculus", "It is terrible"));
		assertEquals("{\"head\":\"Calculus\",\"action\":\"changecourse\",\"description\":\"It is terrible\"}", courseChange("Calculus", "It is terrible"));
		assertNotEquals("{\"head\":\"Calculus\",\"action\":\"changecourse\",\"description\":\"It is terrible\"}", courseChange("Calculus", "Still awesome"));
		assertEquals("{\"head\":\"OOP Project\",\"action\":\"changecourse\",\"description\":\"Refactored a lot\"}", courseChange("OOP Project", "Refactored a lot"));
		assertNotEquals("{\"head\":\"OOP Project\",\"action\":\"changecourse\",\"description\":\"Refactored a lot\"}", courseChange("Calculus", "Refactord a lot"));
	}
	
	@Test
	public void testCourseRemove(){
		//System.out.println(courseRemove("Calculus"));
		assertEquals("{\"action\":\"removecourse\",\"course\":\"Calculus\"}", courseRemove("Calculus"));
		assertNotEquals("{\"action\":\"removecourse\",\"course\":\"Calculus\"}", courseRemove("OOP Project"));
		assertNotEquals("{\"action\":\"removecourse\",\"course\":\"OOP Project\"}", courseRemove("Calculus"));
	}
	
	@Test 
	public void testMatch(){
		//System.out.println(match("Computer Science", "Technical University of Delft", "Rotterdam"));
		assertEquals("{\"uni\":\"Technical University of Delft\",\"study\":\"Computer Science\",\"city\":\"Rotterdam\",\"action\":\"match\"}", match("Computer Science", "Technical University of Delft", "Rotterdam"));
		assertNotEquals("{\"uni\":\"Technical University of Delft\",\"study\":\"Computer Science\",\"city\":\"Rotterdam\",\"action\":\"match\"}", match("Computer Science", "University of Amsterdam", "Rotterdam"));
		assertNotEquals("{\"uni\":\"Technical University of Delft\",\"study\":\"Computer Science\",\"city\":\"Amsterdam\",\"action\":\"match\"}", match("Computer Science", "Technical University of Delft", "Rotterdam"));
	}
	
	@Test
	public void testSearch(){
		//System.out.println(search("CityOfResidence", "Rotterdam"));
		assertEquals("{\"action\":\"search\",\"value\":\"Rotterdam\",\"option\":\"CityOfResidence\"}", search("CityOfResidence", "Rotterdam"));
		assertNotEquals("{\"action\":\"search\",\"value\":\"Amsterdam\",\"option\":\"CityOfResidence\"}", search("CityOfResidence", "Rotterdam"));
		assertNotEquals("{\"action\":\"search\",\"value\":\"Rotterdam\",\"option\":\"CityOfResidence\"}", search("CityOfResidence", "Amsterdam"));
	}
	
	@Test
	public void testRemoveAccount(){
		//System.out.println(removeAccount("naqib@hotmail.com"));
		assertEquals("{\"action\":\"removeaccount\",\"id\":\"naqib@hotmail.com\"}", removeAccount("naqib@hotmail.com"));
		assertNotEquals("{\"action\":\"removeaccount\",\"id\":\"naqib@hotmail.com\"}", removeAccount("fatima@gmail.com"));
		assertNotEquals("{\"action\":\"removeaccount\",\"id\":\"fatima@gmail.com\"}", removeAccount("naqib@hotmail.com"));
	}
	
}
