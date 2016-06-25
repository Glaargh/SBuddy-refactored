import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Class MessageFilter, testing all possible 
 * switch cases in the only method filterMessage.
 * */
public class MessageFilterTest {

	
	/**
	 * Main object under test.
	 * */
	private MessageFilter myMFilter;

	private ServerMethods srvmt;
	
	/**
	 * The response variable for what messagefilter returns.
	 * */
	private String outcome;
	
	/**
	 * Type of incoming message action.
	 * */
	private String incomingRequest;
	
	/*Commands used*//*TODO*/
	/**
	 * User credentials of user to perform actions with.
	 * */
	private final String username = "120567wolfert@gmail.com";
	private final String getAPieceOfInfo = "CurrentStudy";
	private final String otherUsername = "pimdhn@gmail.com";
	
	/*Expected Responses*//*TODO*/
	private final String expectedTrue = "true";
	private final String expectedFalse = "false";
	private final String expectedComputerScience = "Computer Science";
	private final String expectedDefault = "noob";
	
	/**
	 * Setup commonly used objects. 
	 * */	
	@Before
	public void setUp() {
		srvmt = new ServerMethods();
		myMFilter = new MessageFilter();
	}

	/*TODO: Incoming message must be username:password combo, now its only user name*/
	@Test
	public void testLogin() {
		incomingRequest = "login";
		outcome = myMFilter.filterMessage(incomingRequest,/*MUST be username:Password 
				pair*/username);
		assertEquals(expectedTrue, outcome);
	}
	
	/*TODO: Incoming message must be username,password,firstname,lastname combo. now its  only username*/
	@Test
	public void testRegister() {
		incomingRequest = "register";
		outcome = myMFilter.filterMessage(incomingRequest, username);
		/*Should not be able to register*/
		assertEquals(expectedFalse, outcome);
	}
	
	/*TODO: Should be fine, but not sure how String must look (style was, json etc..) cause
	 * luat parses the string to json then goes get("Key")*/
	@Test
	public void testGet() {
		/*You have to login user*/
		srvmt.Login(username);
		
		incomingRequest = "get";
		outcome = myMFilter.filterMessage(incomingRequest, getAPieceOfInfo);
		assertEquals(expectedComputerScience, outcome);
	}
	
	/*TODO: Need key:Value pair for incoming message*/
	@Test
	public void testChange() {
		/*You have to login user*/
		srvmt.Login(username);
		
		incomingRequest = "change";
		outcome = myMFilter.filterMessage(incomingRequest, "CHANGE THIS"/*NEED key:Value message*/);
		
		/*Should return true unless an exception is thrown, check modify method.*/
		assertEquals(expectedTrue, outcome);
	}
	
	/*TODO: The problem is where do we tell the server methods about the other user,
	 * check how string should look*/
	@Test
	public void testGetOther() {
		/*You have to login user*/
		srvmt.Login(username);
		
		
		/*The problem is where do we tell the server methods about the other user*/
		
		incomingRequest = "getother";
		outcome = myMFilter.filterMessage(incomingRequest,
				getAPieceOfInfo);/*Control is now with other email*/
		
		/*Should also just be computer science*/
		assertEquals(expectedComputerScience, outcome);
	}
	
	/*TODO: */
	@Test
	public void testChangeCourse() {
		/*You have to login user*/
		srvmt.Login(username);
		
		incomingRequest = "changecourse";
		outcome = myMFilter.filterMessage(incomingRequest, "CHANGE THIS");
		
		assertEquals("CHANGE THIS", outcome);
	}
	
	/*TODO: */
	@Test
	public void testRemoveCourse() {
		/*You have to login user*/
		srvmt.Login(username);
		
		incomingRequest = "removecourse";
		outcome = myMFilter.filterMessage(incomingRequest, "CHANGE THIS");
		
		assertEquals("CHANGE THIS", outcome);
	}
	
	/*TODO: */
	@Test
	public void testMatch() {
		/*You have to login user*/
		srvmt.Login(username);
		
		incomingRequest = "match";
		outcome = myMFilter.filterMessage(incomingRequest, "CHANGE THIS");
		
		assertEquals("CHANGE THIS", outcome);
	}
	
	/*TODO: */
	@Test
	public void testSearch() {
		/*You have to login user*/
		srvmt.Login(username);
		
		incomingRequest = "search";
		outcome = myMFilter.filterMessage(incomingRequest, "CHANGE THIS");
		
		assertEquals("CHANGE THIS", outcome);
	}
	
	/*TODO: */
	@Test
	public void testRemoveAccount() {
		/*You have to login user*/
		srvmt.Login(username);
		
		incomingRequest = "removeaccount";
		outcome = myMFilter.filterMessage(incomingRequest, "CHANGE THIS");
		
		assertEquals("CHANGE THIS", outcome);
	}
	
	/*TODO: */
	@Test
	public void testDefault() {
		incomingRequest = "whatever it is default, noobz, it should not recognise this string";
		outcome = myMFilter.filterMessage(incomingRequest, expectedDefault);
		assertEquals(expectedDefault, outcome);
	}

}
