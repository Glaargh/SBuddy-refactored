import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Class MessageFilter, testing all possible 
 * switch cases in the only method filterMessage.
 * */
public class MessageFilterTest {

	private ServerMethods srvmt;
	
	/**
	 * Type of incoming message action.
	 * */
	private String incomingRequest;
	
	/**
	 * Content of message to send, not important.
	 * */
	private final String desc = "Not important";
	
	/**
	 * Main object under test.
	 * */
	private MessageFilter myMFilter;

	private String outcome;
	
	/**
	 * Setup commonly used objects. 
	 * */	
	@Before
	public void setUp() {
		srvmt = new ServerMethods();
		myMFilter = new MessageFilter();
	}

	@Test
	public void testLogin() {
		incomingRequest = "login";
		myMFilter.filterMessage(incomingRequest, desc);
		assertEquals("", outcome);
	}
	
	@Test
	public void testRegister() {
		incomingRequest = "login";
		myMFilter.filterMessage(incomingRequest, desc);
		assertEquals("", outcome);
	}
	
	@Test
	public void testGet() {
		incomingRequest = "login";
		myMFilter.filterMessage(incomingRequest, desc);
		assertEquals("", outcome);
	}
	
	@Test
	public void testChange() {
		incomingRequest = "login";
		myMFilter.filterMessage(incomingRequest, desc);
		assertEquals("", outcome);
	}
	
	@Test
	public void testGetOther() {
		incomingRequest = "login";
		myMFilter.filterMessage(incomingRequest, desc);
		assertEquals("", outcome);
	}
	
	@Test
	public void testChangeCourse() {
		incomingRequest = "login";
		myMFilter.filterMessage(incomingRequest, desc);
		assertEquals("", outcome);
	}
	
	@Test
	public void testRemoveCourse() {
		incomingRequest = "login";
		myMFilter.filterMessage(incomingRequest, desc);
		assertEquals("", outcome);
	}
	
	@Test
	public void testMatch() {
		incomingRequest = "login";
		myMFilter.filterMessage(incomingRequest, desc);
		assertEquals("", outcome);
	}
	
	@Test
	public void testSearch() {
		incomingRequest = "login";
		myMFilter.filterMessage(incomingRequest, desc);
		assertEquals("", outcome);
	}
	
	@Test
	public void testRemoveAccount() {
		incomingRequest = "login";
		myMFilter.filterMessage(incomingRequest, desc);
		assertEquals("", outcome);
	}
	
	@Test
	public void testDefault() {
		incomingRequest = "login";
		myMFilter.filterMessage(incomingRequest, desc);
		assertEquals("", outcome);
	}

}
