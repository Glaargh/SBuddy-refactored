import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;




public class ServerMethodsTest extends ServerMethods {
	private String fakeFile = "fake.json";
	
	@Rule 
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testSetGetDatabasePath() {
		ServerMethods svmt = new ServerMethods();
		svmt.setDatabase(fakeFile);
		assertEquals(fakeFile, svmt.getDatabase());
	}
	
	@Test
	public void testNonExistentLogin() 
	{// 1993@gmail.com doesn't exist in the database
		String Loginstring = "{\"password\":\"pass1\",\"action\":\"login\",\"id\":\"1993@gmail.com\"}";
		assertEquals(Login(Loginstring),"false");
	}

	@Test
	public void testtrueLogin() 
	{// 120567wolfert@gmail.com does exist in the database
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		assertEquals(Login(Loginstring),"true");
	}
	
	@Test
	public void testFalseLogin() 
	{// Logging in with a wrong password
		String Loginstring = "{\"password\":\"lalala\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		assertEquals(Login(Loginstring),"false");
	}

	
	@Test
	public void testRegisterusernamealreadyexist() throws ParseException 
	{// 120567wolfert@gmail.com already exist in the database
		String register = "{\"password\":\"jackson1\",\"firstname\":\"Jackson\",\"action\":\"register\",\"id\":\"michael@gmail.com\",\"lastname\":\"Tran\"}";
		//this string is made by the client methods class that will send thru the sockets into this methods when a user registers.
		//The username which the user choose already exist in the database	
		assertEquals(Register(register),"false");	
	}

	@Test
	public void testRegisterusernamenotexistyet() throws ParseException 
	{// aaaaaaaaaaaa@gmail.com does not exist in the database, so the system will allow a new one to be written to database
		String register = "{\"password\":\"jackson1\",\"firstname\":\"Jackson\",\"action\":\"register\",\"id\":\"jackson@hotmail.com\",\"lastname\":\"Tran\"}";
		assertEquals(Register(register),"true");
		remove("{\"action\":\"removeaccount\",\"id\":\"jackson@hotmail.com\"}");
		//this is to remove from database after testing
	}
	
	@Test
	public void testRegisterExeption() throws ParseException 
	{
		String register = "INCOMING-esseaaaaaaaaaa@gmail.com¿pass¿Steve¿Lei";
		thrown.expect(ParseException.class);
		Register(register);	
	}
	
	@Test
	public void testRegisterWrongCommand() throws ParseException {
		String Loginstring = "{\"password\":\"pass1\",\"action\":\"login\",\"id\":\"1993@gmail.com\"}";
		assertEquals(Register(Loginstring),"true");
		remove("{\"action\":\"removeaccount\",\"id\":\"1993@gmail.com\"}");
	}

	@Test
	public void testmodify() 
	{// in case a user is logged in
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String change = "{\"action\":\"change\",\"value\":\"Trust trust!\",\"key\":\"Description\"}";
		assertEquals(modify(change),"true");	
		
		//this is to change the database back after the test
		modify("{\"action\":\"change\",\"value\":\"I like this study and working with others\",\"key\":\"Description\"}");

	}
	

	@Test
	public void testmodify2() 
	{// in case a user is logged in
		setDatabase(fakeFile);
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String change = "INCOMING-CHANGE CurrentStudy Wiskunde";
		
		assertEquals(modify(change), "false");
		
		//this is to change the database back after the test
		setDatabase("database.json");

	}

	@Test
	public void testget() 
	{// in case a user is logged in
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		String input = "{\"action\":\"get\",\"key\":\"Firstname\"}";
		Login(Loginstring);
		
		assertEquals(get(input),"Michael");	
	}
	
	@Test
	public void testget2() 
	{// in case a user is logged in
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		String input = "{\"action\":\"get\",\"key\":\"Course list\"}";
		Login(Loginstring);
		
		assertEquals(get(input),"{\"Computer Graphics\":\"Need lots of help!\",\"Artificial Intelligence\":\"Really good at it\",\"Calculus\":\"Looking to improve ,so please help me!\",\"Web en Database\":\"I understand databases, but really struggling at web, can you help me?\"}");	
	}
	
	@Test
	public void testget3() 
	{// in case a user is logged in
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		String input = "{\"action\":\"get\",:\"Course list\"}";
		Login(Loginstring);
		assertEquals(get(input),"false");	
	}

	@Test
	public void testremove() throws ParseException 
	{//create a user first then remove it
		String register = "{\"password\":\"jackson1\",\"firstname\":\"Jackson\",\"action\":\"register\",\"id\":\"jackson@hotmail.com\",\"lastname\":\"Tran\"}";
		Register(register);
		assertEquals(remove("{\"action\":\"removeaccount\",\"id\":\"jackson@hotmail.com\"}"), "true");
	}

	@Test
	public void testRemoveNotExist() 
	{//remove a user that does not exist in the database
		assertEquals(remove("{\"action\":\"removeaccount\",\"id\":\"jackson@hotmail.com\"}"), "false");
	}
	
	@Test
	public void testAddOrRemoveCourse() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String change = "{\"head\":\"OOP\",\"action\":\"changecourse\",\"description\":\"total noob here\"}";
		assertEquals(addormodifycourse(change),"true");
		
		removecourse("{\"action\":\"removecourse\",\"course\":\"OOP\"}");
	}
	
	@Test
	public void testAddOrRemoveCourse2() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String change = "{\"head\":\"OOP\",\"action\":\"changecourse\",\"description\":\"total noob here\"}";
		String change2 = "{\"head\":\"OOP\",\"action\":\"changecourse\",\"description\":\"total noob\"}";
		addormodifycourse(change);
		assertEquals(addormodifycourse(change2),"true");
		
		removecourse("{\"action\":\"removecourse\",\"course\":\"OOP\"}");
	}
	
	@Test
	public void testAddOrRemoveCourse3() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String change = "{\"action\":\"changecourse\",\"description\":\"total noob here\"}";
		assertEquals(addormodifycourse(change),"true");
		removecourse("{\"action\":\"removecourse\",\"course\":\"null\"}");	
	}
	
	
	@Test
	public void testRemoveCourse() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String remove = "{\"course\":\"Computer Graphics\"}";
		assertEquals(removecourse(remove), "true");
		addormodifycourse("{\"head\":\"Computer Graphics\",\"action\":\"changecourse\",\"description\":\"Need lots of help!\"}");
	}
	
	@Test
	public void testRemoveCourse2() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String remove = "{\"course\":\"Linear Algebra\"}";
		assertEquals(removecourse(remove), "true");
		
	}
	
	@Test
	public void testRemoveCourse3() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String remove = "{\"course\":\"null\"}";
		assertEquals(removecourse(remove), "true");
		
	}
	
	
/*	
	

	
	@Test
	public void testMatchEngine() 
	{
		Register("INCOMING-REGISTER¿wolfertvanborselen@gmail.com¿pass¿Steve¿Lei");
		Login("INCOMING-LOGIN¿wolfertvanborselen@gmail.com¿pass");
		modify("INCOMING-CHANGE CurrentStudy teststudy");
		modify("INCOMING-CHANGE CurrentUniversity testuniversity");
		modify("INCOMING-CHANGE CityOfResidence teststad");
		modify("INCOMING-CHANGE Available true");
		String command = "INCOMING-MATCH¿teststudy¿testuniversity¿teststad";
		assertEquals(MatchEngine(command), "[wolfertvanborselen@gmail.com]");
		remove("INCOMING-REMOVE¿wolfertvanborselen@gmail.com¿pass");
		
	}
	@Test
	public void testMatchEngine1() 
	{//remove a user that does not exist in the database
		String command = "INCOMING-MATCH¿dadwwaawd¿awwadwdawd¿awdawdawdawd";
		assertEquals(MatchEngine(command), "[]");
	}
	
	@Test (expected=Exception.class)
	public void testMatchEngine2() 
	{
		String command = "INCOMING-MATCH¿JUNITTEST";
		assertEquals(MatchEngine(command), "[test@gmail.com]");
	}
	@Test
	public void testMatchEngine3() 
	{
		Register("INCOMING-REGISTER¿wolfertvanborselen@gmail.com¿pass¿Steve¿Lei");
		Login("INCOMING-LOGIN¿wolfertvanborselen@gmail.com¿pass");
		modify("INCOMING-CHANGE CurrentStudy teststudy");
		modify("INCOMING-CHANGE CurrentUniversity testuniversity");
		modify("INCOMING-CHANGE CityOfResidence teststad");
		modify("INCOMING-CHANGE Available true");
		String command = "INCOMING-MATCH¿notexist¿testuniversity¿notexist";
		assertEquals(MatchEngine(command), "[wolfertvanborselen@gmail.com]");
		remove("INCOMING-REMOVE¿wolfertvanborselen@gmail.com¿pass");
	}
	
	
	@Test
	public void testMatchEngine4() 
	{//remove a user that does not exist in the database
		Register("INCOMING-REGISTER¿wolfertvanborselen@gmail.com¿pass¿Steve¿Lei");
		Login("INCOMING-LOGIN¿wolfertvanborselen@gmail.com¿pass");
		modify("INCOMING-CHANGE CurrentStudy teststudy");
		modify("INCOMING-CHANGE CurrentUniversity testuniversity");
		modify("INCOMING-CHANGE CityOfResidence teststad");
		modify("INCOMING-CHANGE Available true");
		String command = "INCOMING-MATCH¿notexist¿notexist¿teststad";
		assertEquals(MatchEngine(command), "[wolfertvanborselen@gmail.com]");
		remove("INCOMING-REMOVE¿wolfertvanborselen@gmail.com¿pass");
	}
	

	@Test
	public void testSearchEngine() 
	{//remove a user that does not exist in the database
		
		String command = "INCOMING-SEARCH¿Firstname¿Luat";
		assertEquals(SearchEngine(command), "[120567wolfert@gmail.com]");
	}
		
	@Test (expected=Exception.class)
	public void testSearchEngine1() 
	{//remove a user that does not exist in the database
		String command = "INCOMING-SEARCH¿Firstname";
		assertEquals(SearchEngine(command), "[test@gmail.com]");
	}
	

	
	@Test
	public void testSearchEngineTRUEFALSE1() 
	{//remove a user that does not exist in the database
	/*	Register("INCOMING-REGISTER¿wolfertvanborselen@gmail.com¿pass¿Steve¿Lei");
		Login("INCOMING-LOGIN¿wolfertvanborselen@gmail.com¿pass");
		modify("INCOMING-CHANGE CurrentUniversity testuniversity");
		modify("INCOMING-CHANGE Available false");*/
		//assertTrue(SearchEngineTRUEFALSE1("120567wolfert@gmail.com","Firstname","Luat"));
	//	assertFalse(SearchEngineTRUEFALSE1("120567wolfert@gmail.com","CurrentUniversity","testuniversity"));
		//remove("INCOMING-REMOVE¿wolfertvanborselen@gmail.com¿pass");
//	}
	
	

	
	
	
	
}


/*


@Test
public void testwrite1() throws IOException 
{
	assertEquals(write(Database,"database.json"),"true");
}





*/