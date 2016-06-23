import static org.junit.Assert.*;
import org.junit.Test;

public class ClientMethodsTest extends ClientMethods {

	
	

	@Test
	public void testLogin() {
		assertEquals("{\"password\":\"naqib1\",\"action\":\"login\",\"id\":\"naqib@hotmail.com\"}", Login("naqib@hotmail.com",  "naqib1"));
	}
	
	
	
//	@Test
//	public void testRegister() {
//		assertEquals(Register("Andy","Zaidman","1444@gmail.com","trololo"),"INCOMING-REGISTER¿1444@gmail.com¿trololo¿Andy¿Zaidman");
//	}
}
