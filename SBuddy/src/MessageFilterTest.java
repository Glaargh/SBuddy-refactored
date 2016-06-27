import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
	
	@Rule 
	public ExpectedException thrown = ExpectedException.none();
	
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
		outcome = myMFilter.filterMessage(incomingRequest,"{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}");
		assertEquals(expectedTrue, outcome);
	}
	
	/*TODO: Incoming message must be username,password,firstname,lastname combo. now its  only username*/
	@Test
	public void testRegister() {
		incomingRequest = "register";
		outcome = myMFilter.filterMessage(incomingRequest, "{\"password\":\"jackson1\",\"firstname\":\"Jackson\",\"action\":\"register\",\"id\":\"jackson@hotmail.com\",\"lastname\":\"Tran\"}");
		/*Should not be able to register*/
		assertEquals(expectedTrue, outcome);
		
		myMFilter.filterMessage("removeaccount", "{\"id\":\"jackson@hotmail.com\"}");
		
	}
	
	@Test
	public void testRegister2() {
		incomingRequest = "register";
		outcome = myMFilter.filterMessage(incomingRequest, "{\"password\":\"jackson1\",\"firstname\":\"Jackson\",\"action\":\"register\",\"id\":\"pimdhn@gmail.com\",\"lastname\":\"Tran\"}");
		/*Should not be able to register*/
		assertEquals(expectedFalse, outcome);
		
		
	}
	
	
	/*TODO: Should be fine, but not sure how String must look (style was, json etc..) cause
	 * luat parses the string to json then goes get("Key")*/
	@Test
	public void testGet() {
		/*You have to login user*/
		myMFilter.filterMessage("login","{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}");
		
		incomingRequest = "get";
		outcome = myMFilter.filterMessage(incomingRequest, "{\"action\":\"get\",\"key\":\"CurrentStudy\"}");
		assertEquals(expectedComputerScience, outcome);
	}
	
	/*TODO: Need key:Value pair for incoming message*/
	@Test
	public void testChange() {
		/*You have to login user*/
		
		myMFilter.filterMessage("login","{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}");
		
		incomingRequest = "change";
		outcome = myMFilter.filterMessage(incomingRequest, "{\"action\":\"change\",\"value\":\"Trust trust!\",\"key\":\"Description\"}");
		
		/*Should return true unless an exception is thrown, check modify method.*/
		assertEquals(expectedTrue, outcome);
		
		myMFilter.filterMessage(incomingRequest, "{\"action\":\"change\",\"value\":\"I like this study and working with others\",\"key\":\"Description\"}");
		
	}
	
	/*TODO: The problem is where do we tell the server methods about the other user,
	 * check how string should look*/
	@Test
	public void testGetOther() {
		/*You have to login user*/
		myMFilter.filterMessage("login","{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}");
			
		/*The problem is where do we tell the server methods about the other user*/
		
		incomingRequest = "getother";
		outcome = myMFilter.filterMessage(incomingRequest,
				"{\"action\":\"getother\",\"id\":\"dario@gmail.com\",\"key\":\"CurrentStudy\"}");/*Control is now with other email*/
		
		/*Should also just be computer science*/
		assertEquals(expectedComputerScience, outcome);
	}
	
	/*TODO: */
	@Test
	public void testChangeCourse() {
		/*You have to login user*/
		myMFilter.filterMessage("login","{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}");
		
		
		incomingRequest = "changecourse";
		outcome = myMFilter.filterMessage(incomingRequest, "{\"head\":\"OOP\",\"action\":\"changecourse\",\"description\":\"total noob here\"}");
		
		assertEquals(expectedTrue, outcome);
		myMFilter.filterMessage("removecourse", "{\"action\":\"removecourse\",\"course\":\"OOP\"}");
		
	}
	
	/*TODO: */
	@Test
	public void testRemoveCourse() {
		/*You have to login user*/
		myMFilter.filterMessage("login","{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}");
		
		
		incomingRequest = "removecourse";
		outcome = myMFilter.filterMessage(incomingRequest, "{\"course\":\"Computer Graphics\"}");
		
		assertEquals(expectedTrue, outcome);
		myMFilter.filterMessage("changecourse", "{\"head\":\"Computer Graphics\",\"action\":\"changecourse\",\"description\":\"Need lots of help!\"}");
	}
	
	/*TODO: */
	@Test
	public void testMatch() {
		/*You have to login user*/
		
		incomingRequest = "match";
		outcome = myMFilter.filterMessage(incomingRequest, "{\"uni\":\"Technical University of Delft\",\"study\":\"Computer Science\",\"city\":\"The Hague\"}");
		
		assertEquals("[120567wolfert@gmail.com, pimdhn@gmail.com, naqib@hotmail.com, lufther@gmail.com, michael@gmail.com]", outcome);
	}
	
	/*TODO: */
	@Test
	public void testSearch() {
		/*You have to login user*/
		
		incomingRequest = "search";
		outcome = myMFilter.filterMessage(incomingRequest, "{\"action\":\"search\",\"value\":\"pim\",\"option\":\"Email\"}");
		
		assertEquals("[pimdhn@gmail.com]", outcome);
	}
	
	/*TODO: */
	@Test
	public void testRemoveAccount() {
		myMFilter.filterMessage("register", "{\"password\":\"jackson1\",\"firstname\":\"Jackson\",\"action\":\"register\",\"id\":\"jackson@hotmail.com\",\"lastname\":\"Tran\"}");
		
		
		incomingRequest = "removeaccount";
		outcome = myMFilter.filterMessage(incomingRequest, "{\"id\":\"jackson@hotmail.com\"}");
		
		assertEquals(expectedTrue, outcome);
	}
	
	/*TODO: */
	@Test
	public void testDefault() {
		incomingRequest = "whatever it is default, noobz, it should not recognise this string";
		outcome = myMFilter.filterMessage(incomingRequest, expectedDefault);
		assertEquals(expectedDefault, outcome);
	}

}
