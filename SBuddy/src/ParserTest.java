import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import com.sun.javafx.application.PlatformImpl;

//import com.sun.javafx.application.PlatformImpl;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ParserTest {
	Parser testParser;

	@Before
	public void setUp() throws Exception {
		testParser = new Parser();
	}

	@Test
	public void testParser() {
		assertNotNull(testParser);
	}

	@Test
	public void testParse() {
		//Test that inputting a string that is not in the right format returns null.
		assertNull(testParser.parse(""));
		//Test that inputting a string that is in the right format returns a proper JSON object.
		assertNotNull(testParser.parse("{\"pimdhn@gmail.com\":[{\"CountryOfResidence\":\"The Netherlands\",\"CurrentStudy\":\"Computer Science\",\"Piclink\":\"https:\\/\\/scontent.cdninstagram.com\\/hphotos-xft1\\/t51.2885-15\\/s320x320\\/e35\\/11809967_1104443242917133_1328169645_n.jpg\",\"Email\":\"pimdhn@gmail.com\",\"Description\":\"I am an ethusiastic student looking to help and work with others :)\",\"Placepic\":\"http:\\/\\/www.tudelft.nl\\/uploads\\/RTEmagicC_comp.jpg.jpg\",\"Lastname\":\"Dhaen\",\"StudyPeriod\":\"2015-2018\",\"Gender\":\"Male\",\"Firstname\":\"Pim\",\"Phone\":\"0647673112\",\"Course list\":{\"oop project\":\"hell yeah good at it:D!\",\"redeneren en logica\":\"quite good\",\"web en database\":\"pretty good\",\"computer organisation\":\"Really need help, please help1\",\"calculus\":\"I have a good feeling for it, but could use help\"},\"CityOfResidence\":\"Delft\",\"Available\":\"true\",\"CurrentUniversity\":\"Technical University of Delft\",\"Age\":\"19\",\"JoinDate\":\"10:53:PM:3:11:2015\",\"Password\":\"easy\"}]}"));
		assertEquals(JSONObject.class,testParser.parse("{\"pimdhn@gmail.com\":[{\"CountryOfResidence\":\"The Netherlands\",\"CurrentStudy\":\"Computer Science\",\"Piclink\":\"https:\\/\\/scontent.cdninstagram.com\\/hphotos-xft1\\/t51.2885-15\\/s320x320\\/e35\\/11809967_1104443242917133_1328169645_n.jpg\",\"Email\":\"pimdhn@gmail.com\",\"Description\":\"I am an ethusiastic student looking to help and work with others :)\",\"Placepic\":\"http:\\/\\/www.tudelft.nl\\/uploads\\/RTEmagicC_comp.jpg.jpg\",\"Lastname\":\"Dhaen\",\"StudyPeriod\":\"2015-2018\",\"Gender\":\"Male\",\"Firstname\":\"Pim\",\"Phone\":\"0647673112\",\"Course list\":{\"oop project\":\"hell yeah good at it:D!\",\"redeneren en logica\":\"quite good\",\"web en database\":\"pretty good\",\"computer organisation\":\"Really need help, please help1\",\"calculus\":\"I have a good feeling for it, but could use help\"},\"CityOfResidence\":\"Delft\",\"Available\":\"true\",\"CurrentUniversity\":\"Technical University of Delft\",\"Age\":\"19\",\"JoinDate\":\"10:53:PM:3:11:2015\",\"Password\":\"easy\"}]}").getClass());
	}

	@Test
	public void testParseCourses() throws ParseException {
		//To prevent errors because of a lack of toolkit initialization
		PlatformImpl.startup(() -> {});
		
		String teststring = "";
		List list = new ArrayList();
		ObservableList<Courses> inputlist = FXCollections.observableList(list);
		ListView<String> listview = new ListView<String>();
		User testuser = new User("");
		
		//Test using example courses list.
		String inputString = "{\"Computer Graphic\":\"I need help and if you need some money, please contact me.\",\"Calculus\":\"***Freshmen Only*** I can offer help for a small fee.\",\"Computer Organization\":\"Anyone available on friday and need a study buddy just contact me.\",\"Lineaire Algebra\":\"***Second year*** Would you like to have an awesome study buddy? Message me!\",\"Object Oriented Programming\":\"***Freshmen Only*** Are you bad at it? You need some help? I\'m the right person!\"}";
		
		//Test that it returns an instance of ObservableList when given an observablelist as input.
		assertTrue(testParser.parseCourses(inputString, inputlist) instanceof ObservableList);
		//Since a correct list of courses can't be generated easily, comparing the result with anything is nigh impossible.
		//Test that it returns an instance of listview if given a listview as input.
		assertTrue(testParser.parseCourses(inputString, listview) instanceof ListView);
		//Test that it returns an instance of string if given a string as input.
		assertTrue(testParser.parseCourses(inputString, teststring) instanceof String);
		//Attempt with non-string object.
		assertNull(testParser.parseCourses(inputString, testuser));
		
		//Test using empty courses list.
		String empty = "{}";
		
		//Test that it returns the object it is given as input if the courses list is empty (and the object type is acceptable).
		assertEquals(testParser.parseCourses(empty, empty),empty);
		assertEquals(testParser.parseCourses(empty, listview),listview);
		assertEquals(testParser.parseCourses(empty, inputlist),inputlist);
	}

	//Proved untestable due to difficulties involved in creating a provider
	/*@Test
	public void testParseAvailability() {
		//Duplicate Provider code.
		//int port = 8080;
		//Provider server = new Provider(port);
		//server.run();
		(new Thread(new ParserTest())).start();
		String[] args = {"localhost", "8080"};
		Client client = new Client(args);
		System.out.println(testParser.parseAvailability(client , "blegh"));
		//server.terminate();
	}

	@Test
	public void testParseUserInfo() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testParseMatchedEmails() {
		//Create Strings and ArrayList<String> as input.
		String search = "[120567wolfert@gmail.com, pimdhn@gmail.com, SteveJobs@gmail.com, Steveemail@gmail.com, naqib@gmail.com, dario@gmail.com, lufther@gmail.com, michael@gmail.com, josie@gmail.com]";
		ArrayList<String> matchEmails = new ArrayList<String>(); 
		String curEmail = "120567wolfert@gmail.com";
		
		//Create ArrayList<String> whose contents are
		//consistent with the expected effect of the parseMatchedEmails method upon the arraylist used as input.
		ArrayList<String> matchEmails2 = new ArrayList<String>(); 
		matchEmails2.add("pimdhn@gmail.com");
		matchEmails2.add("SteveJobs@gmail.com");
		matchEmails2.add("Steveemail@gmail.com");
		matchEmails2.add("naqib@gmail.com");
		matchEmails2.add("dario@gmail.com");
		matchEmails2.add("lufther@gmail.com");
		matchEmails2.add("michael@gmail.com");
		matchEmails2.add("josie@gmail.com");
		
		testParser.parseMatchedEmails(search, matchEmails, curEmail);
		assertEquals(matchEmails,matchEmails2);
	}

	//Proved untestable due to issues with creating a provider
/*	//@Test
	public void testParseMatches() {
		(new Thread(new ParserTest())).run();
		String[] args = {"localhost", "8080"};
		Client client = new Client(args);
		client.run();
		//client.sendMessage(msg);
		Parser parser = new Parser();
		parser.parseMatches(client, "pimdhn@gmail.com", "Calculus", "true");
		
		
	}

	@Override
	public void run() {
		//Make a server
		//Duplicate Provider code.
		System.out.println("Server is running");
		Provider server = new Provider(8080);
		String[] a = {"localhost","8080"};
		server.run();
	}*/

}
