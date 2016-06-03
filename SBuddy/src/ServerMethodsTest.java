import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

//public class ServerMethodsTest extends ServerMethods {
	/*
	@Test
	public void testfalseLogin() 
	{// 1993@gmail.com doesn't exist in the database
		String Loginstring = "INCOMING-LOGIN¿1993@gmail.com¿pass1";
		assertEquals(Login(Loginstring),"false");
	}

	@Test
	public void testtrueLogin() 
	{// 120567wolfert@gmail.com does exist in the database
		String Loginstring = "INCOMING-LOGIN¿120567wolfert@gmail.com¿iamironman96";
		assertEquals(Login(Loginstring),"true");
	}

	@Test
	public void testLoginException() 
	{// 120567wolfert@gmail.com does exist in the database
		String Loginstring = "INCOMING-LOGIN";
		assertEquals(Login(Loginstring),"false");
	}

	@Test
	public void testRegisterusernamealreadyexist() 
	{// 120567wolfert@gmail.com already exist in the database
		String register = "INCOMING-REGISTER¿120567wolfert@gmail.com¿pass¿Steve¿Lei";
		//this string is made by the client methods class that will send thru the sockets into this methods when a user registers.
		//The username which the user choose already exist in the database	
		assertEquals(Register(register),"false");	
	}
	
	@Test
	public void testRegisterusernamenotexistyet() 
	{// aaaaaaaaaaaa@gmail.com does not exist in the database, so the system will allow a new one to be written to database
		String register = "INCOMING-REGISTER¿jUnittest@gmail.com¿pass¿Steve¿Lei";
		assertEquals(Register(register),"true");
		remove("INCOMING-REMOVE¿jUnittest@gmail.com¿pass");
		//this is to remove from database after testing
	}
	
	@Test
	public void testRegisterExeption() 
	{
		String register = "INCOMING-esseaaaaaaaaaa@gmail.com¿pass¿Steve¿Lei";
		assertEquals(Register(register),"false");	
	}


	@Test
	public void testmodify() 
	{// in case a user is logged in
		String Loginstring = "INCOMING-LOGIN¿120567wolfert@gmail.com¿iamironman96";
		Login(Loginstring);
		String change = "INCOMING-CHANGE CurrentStudy Wiskunde";
		assertEquals(modify(change),"true");	
		
		//this is to change the database back after the test
		modify("INCOMING-CHANGE CurrentStudy Computer Science");
	}
	@Test
	public void testget() 
	{// in case a user is logged in
		String Loginstring = "INCOMING-LOGIN¿120567wolfert@gmail.com¿iamironman96";
		Login(Loginstring);
	
		assertEquals(get("INCOMING-GET CurrentStudy"),"Computer Science");	
	}
	@Test
	public void testremove() 
	{//create a user first then remove it
		String register = "INCOMING-REGISTER¿jUnittest@gmail.com¿pass¿Steve¿Lei";
		Register(register);
		assertEquals(remove("INCOMING-REMOVE¿jUnittest@gmail.com¿pass"), "true");
	}
	@Test
	public void testremovenotexist() 
	{//remove a user that does not exist in the database
		
		assertEquals(remove("INCOMING-REMOVE¿jUnittest@gmail.com¿pass"), "false");
	}
	
	
	

	
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
	
	

	
	
	
	
//}


/*


@Test
public void testwrite1() throws IOException 
{
	assertEquals(write(Database,"database.json"),"true");
}





*/