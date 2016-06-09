import javafx.beans.property.SimpleStringProperty;

public class Courses {

	/**
	 * THIS IS A CLASS WHICH IS CREATED TO SUPPORT THE USE OF A TABLE IN COURSE TAB, TESTABLE
	 */
	private final SimpleStringProperty courseName;
	private final SimpleStringProperty gradeName;
	private final SimpleStringProperty helpName;
	
	public Courses(String fName, String lName, String email) {
		this.courseName = new SimpleStringProperty(fName);
		this.gradeName = new SimpleStringProperty(lName);
		this.helpName = new SimpleStringProperty(email);
	}
	
	public String getFirstName() {
		return courseName.get();
	}
	
	public void setFirstName(String fName) {
		courseName.set(fName);
	}
	
	public String getLastName() {
		return gradeName.get();
	}
	
	public void setLastName(String fName) {
		gradeName.set(fName);
	}
	
	public String getEmail() {
		return helpName.get();
	}
	
	public void setEmail(String fName) {
		helpName.set(fName);
	}
}
