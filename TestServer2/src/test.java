import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 12056
 *Deze klass is bedoeling om kleine dingens uit te testen, is niet belangrijk
 */
public class test{
	public static void main(String[] args) 
	{
		user testuser1 = new user("120567wolfert@gmail.com");
		

		System.out.println(testuser1.get("Lastname"));
		System.out.println(testuser1.get("JoinDate"));
	/*	System.out.println(testuser1.getCourseList());
		System.out.println();
		for(int i = 0; i<testuser1.getCourseList().size(); i++)
	 	{
			System.out.println(testuser1.getCourseList().get(i));
	 	}*/
		
		/*testuser1.modify("Lastname", "Lei");
		System.out.println(testuser1.get("Lastname"));
		System.out.println(testuser1.obj.toString());

		
		System.out.println(testuser1.getChatList().get("SBuddybot"));
		testuser1.addmessage("SBuddybot", "Hello SBuddybot, are you a real person???");
		System.out.println(testuser1.getChatList().get("SBuddybot"));
		System.out.println(testuser1.getCourseList());*/
		
		//System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("h:m:a:d:M:y")));
	
	}

}
