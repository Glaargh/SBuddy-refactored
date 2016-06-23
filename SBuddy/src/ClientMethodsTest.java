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
		System.out.println(change("Available", "Yes, contact me now"));
		assertEquals("{\"action\":\"change\",\"value\":\"Yes, contact me now\",\"key\":\"Available\"}" ,  change("Available", "Yes, contact me now"));
		assertNotEquals("{\"action\":\"change\",\"value\":\"Yes, contact me now\",\"key\":\"Available\"}" ,  change("No  :(  Please check back later.", "Yes, contact me now"));
	}
	
	@Test
	public void testGetOther(){
	//	System.out.println(getOther(Firstname, "Naqib"));
	}
	
	
	
	
//	@Test
//	public void testRegister() {
//		assertEquals(Register("Andy","Zaidman","1444@gmail.com","trololo"),"INCOMING-REGISTER¿1444@gmail.com¿trololo¿Andy¿Zaidman");
//	}
}
