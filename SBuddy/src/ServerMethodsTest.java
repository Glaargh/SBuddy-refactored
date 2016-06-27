import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;




public class ServerMethodsTest extends ServerMethods {
	private String fakeFile = "fake.json";
	
	@Rule 
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Tests if the path to the json file is returned correctly.
	 */
	@Test
	public void testSetGetDatabasePath() {
		ServerMethods svmt = new ServerMethods();
		svmt.setDatabase(fakeFile);
		assertEquals(fakeFile, svmt.getDatabase());
	}
	
	/**
	 * Login method returns false if u try to log in with an unknown username.
	 */
	@Test
	public void testNonExistentLogin() 
	{// 1993@gmail.com doesn't exist in the database
		String Loginstring = "{\"password\":\"pass1\",\"action\":\"login\",\"id\":\"1993@gmail.com\"}";
		assertEquals(Login(Loginstring),"false");
	}
	
	/**
	 * Login method returns true if u try to log in with a known username.
	 */
	@Test
	public void testtrueLogin() 
	{// 120567wolfert@gmail.com does exist in the database
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		assertEquals(Login(Loginstring),"true");
	}
	
	/**
	 * Login method returns false if u try to log in with a known username, but wrong password.
	 */
	@Test
	public void testFalseLogin() 
	{// Logging in with a wrong password
		String Loginstring = "{\"password\":\"lalala\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		assertEquals(Login(Loginstring),"false");
	}

	/**
	 * Register method returns false if u try to register with an already known username/mail.
	 * @throws ParseException
	 * 				creates a ParseException object by failure.
	 */
	@Test
	public void testRegisterusernamealreadyexist() throws ParseException 
	{// 120567wolfert@gmail.com already exist in the database
		String register = "{\"password\":\"jackson1\",\"firstname\":\"Jackson\",\"action\":\"register\",\"id\":\"michael@gmail.com\",\"lastname\":\"Tran\"}";
		//this string is made by the client methods class that will send thru the sockets into this methods when a user registers.
		//The username which the user choose already exist in the database	
		assertEquals(Register(register),"false");	
	}
	
	/**
	 * Returns true if u try to register with an unknown username/mail.
	 * @throws ParseException
	 * 				creates a ParseException object by failure.
	 */
	@Test
	public void testRegisterusernamenotexistyet() throws ParseException 
	{// aaaaaaaaaaaa@gmail.com does not exist in the database, so the system will allow a new one to be written to database
		String register = "{\"password\":\"jackson1\",\"firstname\":\"Jackson\",\"action\":\"register\",\"id\":\"jackson@hotmail.com\",\"lastname\":\"Tran\"}";
		assertEquals(Register(register),"true");
		remove("{\"action\":\"removeaccount\",\"id\":\"jackson@hotmail.com\"}");
		//this is to remove from database after testing
	}
	
	/**
	 * Tests if a ParseException is thrown by use of nonsense input.
	 * @throws ParseException
	 * 				creates a ParseException object by failure.
	 */
	@Test
	public void testRegisterExeption() throws ParseException 
	{
		String register = "INCOMING-esseaaaaaaaaaa@gmail.com¿pass¿Steve¿Lei";
		thrown.expect(ParseException.class);
		Register(register);	
	}
	
	/**
	 * As long as a password and id is given, this method will work for any seletced action.
	 * @throws ParseException
	 * 				creates a ParseException object by failure.
	 */
	@Test
	public void testRegisterWrongCommand() throws ParseException {
		String Loginstring = "{\"password\":\"pass1\",\"action\":\"login\",\"id\":\"1993@gmail.com\"}";
		assertEquals(Register(Loginstring),"true");
		remove("{\"action\":\"removeaccount\",\"id\":\"1993@gmail.com\"}");
	}

	/**
	 * When a user is logged in and makes a change and a key and value is given, the method returns true.
	 */
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
	
	/**
	 * modify method returns false if no password and/or id is given or input is nonsense.
	 */
	@Test
	public void testmodify2() 
	{// in case a user is logged in
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String change = "INCOMING-CHANGE CurrentStudy Wiskunde";		
		assertEquals(modify(change), "false");
	}
	
	/**
	 * modify returns a NullPointerException if the use is not logged in when attempting to make a change.
	 * 
	 * @throws NullPointerException
	 */
	@Test
	public void testmodify3() 
	{// in case a user is not logged in
	
		String change = "{\"action\":\"change\",\"value\":\"Trust trust!\",\"key\":\"Description\"}";
		thrown.expect(NullPointerException.class);
		modify(change);	
		
		
	}
	 /**
	  * Tests if the correct value corresponding with the key is returned.
	  */
	@Test
	public void testget() 
	{// in case a user is logged in
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		String input = "{\"action\":\"get\",\"key\":\"Firstname\"}";
		Login(Loginstring);
		
		assertEquals(get(input),"Michael");	
	}
	
	/**
	 * Returns false if the input is nonsense or relevant key is missing in the input
	 */
	@Test
	public void testget2() 
	{// in case a user is logged in
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		String input = "{\"action\":\"get\",:\"Course list\"}";
		Login(Loginstring);
		assertEquals(get(input),"false");	
	}
	
	/**
	 * Returns false if the user is not logged in when using the get method.
	 */
	@Test
	public void testget4() 
	{// in case a user is not logged in		
		String input = "{\"action\":\"get\",:\"Course list\"}";
		assertEquals(get(input),"false");	
	}
	
	/**
	 * Tests if the remove method succeeds and returns true when using a known username as input.
	 * @throws ParseException
	 */
	@Test
	public void testremove() throws ParseException 
	{//create a user first then remove it
		String register = "{\"password\":\"jackson1\",\"firstname\":\"Jackson\",\"action\":\"register\",\"id\":\"jackson@hotmail.com\",\"lastname\":\"Tran\"}";
		Register(register);
		assertEquals(remove("{\"id\":\"jackson@hotmail.com\"}"), "true");
	}
	
	/**
	 * Tests if the method returns false by use of nonsense as input.
	 * @throws ParseException
	 */
	@Test
	public void testremove2() throws ParseException 
	{//create a user first then remove it
		String register = "{\"password\":\"jackson1\",\"firstname\":\"Jackson\",\"action\":\"register\",\"id\":\"jackson@hotmail.com\",\"lastname\":\"Tran\"}";
		Register(register);
		assertEquals(remove("djbcwj vw"), "false");
		remove("{\"action\":\"removeaccount\",\"id\":\"jackson@hotmail.com\"}");
	}
	
	/**
	 * Returns false if the remove method is called with an unknown user as inpt.
	 */
	@Test
	public void testRemoveNotExist() 
	{//remove a user that does not exist in the database
		assertEquals(remove("{\"action\":\"removeaccount\",\"id\":\"jackson@hotmail.com\"}"), "false");
	}
	
	/**
	 * Returns true if the method succeeds in adding or changing an existent course.
	 * @throws IOException
	 */
	@Test
	public void testAddOrModifyCourse() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String change = "{\"head\":\"OOP\",\"action\":\"changecourse\",\"description\":\"total noob here\"}";
		assertEquals(addormodifycourse(change),"true");
		
		removecourse("{\"action\":\"removecourse\",\"course\":\"OOP\"}");
	}
	

	/**
	 * Method succeeds and returns true if no head is given.
	 * @throws IOException
	 */
	@Test
	public void testAddOrModifyCourse2() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String change = "{\"action\":\"changecourse\",\"description\":\"total noob here\"}";
		assertEquals(addormodifycourse(change),"true");
		removecourse("{\"action\":\"removecourse\",\"course\":\"null\"}");	
	}
	
	/**
	 * Returns false if nonsense input or no head and no description is given.
	 * @throws IOException
	 */
	@Test
	public void testAddOrModifyCourse3() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String change = "cwvw";
		assertEquals(addormodifycourse(change),"false");	
	}
	
	/**
	 * Returns true if a existent course in the user's course list is removed.
	 * @throws IOException
	 */
	@Test
	public void testRemoveCourse() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String remove = "{\"course\":\"Computer Graphics\"}";
		assertEquals(removecourse(remove), "true");
		addormodifycourse("{\"head\":\"Computer Graphics\",\"action\":\"changecourse\",\"description\":\"Need lots of help!\"}");
	}
	
	/**
	 * Returns true if the method is called while the course does not exist in the user's course list.
	 * @throws IOException
	 */
	@Test
	public void testRemoveCourse2() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String remove = "{\"course\":\"Linear Algebra\"}";
		assertEquals(removecourse(remove), "true");
		
	}
	
	/**
	 * Returns true if the delete button is pressed while no course is selected.
	 * @throws IOException
	 */
	@Test
	public void testRemoveCourse3() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String remove = "{\"course\":\"null\"}";
		assertEquals(removecourse(remove), "true");
		
	}
	
	/**
	 * Returns false if nonsense is used as input.
	 * @throws IOException
	 */
	@Test
	public void testRemoveCourse4() throws IOException {
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String remove = "wbcjwv";
		assertEquals(removecourse(remove), "false");
		
	}
	
	/**
	 * Tests if a JSONArray is correctly returned.
	 */
	@Test
	public void testRead() {
		JSONArray read = read("database.json");
		assertEquals(read, read("database.json"));
	}
	
	/*
	@Test
	public void testRead2() {
		thrown.expect(FileNotFoundException.class);
		read(fakeFile);
	}
	
	*/
	
	/**
	 * Tests if the getothers method returns the correct value with it's corresponding key.
	 */
	@Test
	public void testGetOthers(){
		String command = "{\"action\":\"getother\",\"id\":\"dario@gmail.com\",\"key\":\"Firstname\"}";
		assertEquals(getothers(command), "Dario");
	}
	
	/**
	 * Returns false if no key is given when the method fails in it's execution.
	 */
	@Test
	public void testGetOthers2(){
		String command = "{\"action\":\"getother\",\"id\":\"dario@gmail.com\"";
		assertEquals(getothers(command), "false");
	}
	
	/**
	 * When searching for people by their city, the method returns a list with known people living in that city.
	 */
	@Test
	public void testSearchEngine(){
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String command = "{\"action\":\"search\",\"value\":\"Delft\",\"option\":\"CityOfResidence\"}";
		assertEquals(SearchEngine(command), "[pimdhn@gmail.com, dario@gmail.com]");
	}
	
	/**
	 * Returns a empty list if no people is found living in the requested city.
	 */
	@Test
	public void testSearchEngine2(){
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String command = "{\"action\":\"search\",\"value\":\"Alkmaar\",\"option\":\"CityOfResidence\"}";
		assertEquals(SearchEngine(command), "[]");
	}
	
	/**
	 * Correctly returns a list of all usernames with a certain common cource in their course list.
	 */
	@Test
	public void testSearchEngine3(){
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String command = "{\"action\":\"search\",\"value\":\"Calculus\",\"option\":\"Course list\"}";
		assertEquals(SearchEngine(command), "[120567wolfert@gmail.com, pimdhn@gmail.com, SteveJobs@gmail.com, Steveemail@gmail.com, naqib@hotmail.com, dario@gmail.com, lufther@gmail.com, michael@gmail.com, josie@gmal.com]");
	}
	
	/**
	 * Returns a list with only the user's username when seaching for people with a course nobody else has in
	 * in their course list.
	 */
	@Test
	public void testSearchEngine4(){
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String command = "{\"action\":\"search\",\"value\":\"Computer graphics\",\"option\":\"Course list\"}";
		assertEquals(SearchEngine(command), "[michael@gmail.com]");
	}
	
	/**
	 * Correctly returns a list of usenames that has a certain string as part in their username.
	 */
	@Test
	public void testSearchEngine5(){
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String command = "{\"action\":\"search\",\"value\":\"pim\",\"option\":\"Email\"}";
		assertEquals(SearchEngine(command), "[pimdhn@gmail.com]");
	}
	
	/**
	 * Returns false for nonsense input or no option and value is given.
	 */
	@Test
	public void testSearchEngine6(){
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		String command = "{\"action\":\"search\"";
		assertEquals(SearchEngine(command), "false");
	}
	
	/**
	 * Correctly returns a list of usernames when searching for people even when the user is not logged in.
	 */
	@Test
	public void testSearchEngine7(){
		
		String command = "{\"action\":\"search\",\"value\":\"pim\",\"option\":\"Email\"}";
		assertEquals(SearchEngine(command), "[pimdhn@gmail.com]");
	}
	
	/**
	 * Returns true if a certain username exists in the database.
	 */
	@Test
	public void testStringExist(){
		ServerMethods svmt = new ServerMethods();
		JSONArray Database = read(getDatabase());
		assertTrue(svmt.StringExist(Database, "michael@gmail.com"));
	}
	
	/**
	 * Returns false if a certain username does not exists in the database.
	 */
	@Test
	public void testStringExist2(){
		ServerMethods svmt = new ServerMethods();
		JSONArray Database = read(getDatabase());
		assertFalse(svmt.StringExist(Database, "mke@gmail.com"));
	}
/*
	@Test
	public void testStringExist3(){
		ServerMethods svmt = new ServerMethods();
		svmt.setDatabase(fakeFile);
		JSONArray Database = svmt.read(svmt.getDatabase());
		
		thrown.expect(FileNotFoundException.class);
		svmt.StringExist(Database, "michael@gmail.com");
		
		svmt.setDatabase("database.json");
	}
*/

	

	/**
	 * Correctly returns a list of usernames that are available.
	 */
	@Test
	public void testMatchEngine() 
	{
		String command = "{\"uni\":\"Technical University of Delft\",\"study\":\"Computer Science\",\"city\":\"The Hague\"}";
		assertEquals(MatchEngine(command), "[120567wolfert@gmail.com, pimdhn@gmail.com, naqib@hotmail.com, lufther@gmail.com, michael@gmail.com]");
	}
	
	/**
	 * Returns false if nonsense input is given.
	 */
	@Test
	public void testMatchEngine2() 
	{
		String command = "cwo";
		assertEquals(MatchEngine(command), "false");
	}
	
	/**
	 * When a user logs in, changes his availability and then calls the method, the method will correctly returns
	 * a list containing all available people and the user's username is not part of the list.
	 */
	@Test
	public void testMatchEngine3() 
	{
		String Loginstring = "{\"password\":\"michael1\",\"action\":\"login\",\"id\":\"michael@gmail.com\"}";
		Login(Loginstring);
		modify("{\"action\":\"change\",\"value\":\"false\",\"key\":\"Available\"}");
		String command = "{\"uni\":\"Technical University of Delft\",\"study\":\"Computer Science\",\"city\":\"The Hague\"}";
		assertEquals(MatchEngine(command), "[120567wolfert@gmail.com, pimdhn@gmail.com, naqib@hotmail.com, lufther@gmail.com]");
		modify("{\"action\":\"change\",\"value\":\"true\",\"key\":\"Available\"}");
	}
	
	/**
	 * When only a university is given, the method will return a list of only people that are studying
	 * at the requested university.
	 */
	@Test
	public void testMatchEngine4() 
	{
		
		String command = "{\"uni\":\"Technical University of Delft\",\"study\":\"cwm\",\"city\":\"kcw\"}";
		assertEquals(MatchEngine(command), "[120567wolfert@gmail.com, pimdhn@gmail.com, naqib@hotmail.com, lufther@gmail.com, michael@gmail.com]");
		
	}
	
	/**
	 * When only the name of a city is given, the method will return a list of only people that
	 * are residing in the requested city.
	 */
	@Test
	public void testMatchEngine5() 
	{
		
		String command = "{\"uni\":\"dcne\",\"study\":\"wkmck\",\"city\":\"Amsterdam\"}";
		assertEquals(MatchEngine(command), "[Steveemail@gmail.com, lufther@gmail.com, josie@gmal.com]");
		
	}
	
}







