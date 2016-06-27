import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

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
	public void testParseCourses() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseAvailability() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseUserInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseMatchedEmails() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseMatches() {
		fail("Not yet implemented");
	}

}
