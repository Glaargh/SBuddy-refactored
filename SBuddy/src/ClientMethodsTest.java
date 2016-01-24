import static org.junit.Assert.*;

import org.junit.Test;

public class ClientMethodsTest extends ClientMethods {

	@Test
	public void testLogin() {
		assertEquals(Login("1444@gmail.com","trololo"),"INCOMING-LOGIN¿1444@gmail.com¿trololo");
	}
	@Test
	public void testRegister() {
		assertEquals(Register("Andy","Zaidman","1444@gmail.com","trololo"),"INCOMING-REGISTER¿1444@gmail.com¿trololo¿Andy¿Zaidman");
	}
}
