import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class, testing all methods of class Courses.
 * */
public class CoursesTest {

	private String name;
	private String lastName;
	private String email;
	
	/**
	 * The main object under test.
	 * */
	private Courses myCourses;
	
	private final String expectedName = "Pim";
	private final String expectedLastName = "OOP";
	private final String expectedEmail = "Hi";
	
	private final String expectedSetName = "OtherThanPim";
	private final String expectedSetLastName = "OtherThanOOP";
	private final String expectedSetEmail = "OtherThanHi";
	
	/**
	 * Setup commonly used objects. 
	 * */
	@Before
	public void setUp() {
		name = "Pim";
		lastName = "OOP";
		email = "Hi";
		myCourses = new Courses(name, lastName, email);
	}

	/**
	 * Test object can be built correctly.
	 * */
	@Test
	public void testConstructor() {
		assertTrue(myCourses instanceof Courses);
	}
	
	/**
	 * Correct Name instantiated?
	 * */
	@Test
	public void testConstructorAndGetFirstName() {
		assertEquals(expectedName, myCourses.getFirstName());
	}
	
	/**
	 * Correct Grade Name instantiated?
	 * */
	@Test
	public void testConstructorAndGetGradeName() {
		assertEquals(expectedLastName, myCourses.getLastName());
	}
	
	/**
	 * Correct Email instantiated?
	 * */
	@Test
	public void testConstructorAndGetEmail() {
		assertEquals(expectedEmail, myCourses.getEmail());
	}
	
	/**
	 * Sets the correct name?
	 * */
	@Test
	public void testSetName() {
		myCourses.setFirstName("OtherThanPim");
		assertEquals(expectedSetName, myCourses.getFirstName());
	}
	
	/**
	 * Sets the correct grade name?
	 * */
	@Test
	public void testSetLastName() {
		myCourses.setLastName("OtherThanOOP");
		assertEquals(expectedSetLastName, myCourses.getLastName());
	}
	
	/**
	 * Sets the correct Email?
	 * */
	@Test
	public void testSetEmail() {
		myCourses.setFirstName("OtherThanHi");
		assertEquals(expectedSetEmail, myCourses.getEmail());
	}
}
