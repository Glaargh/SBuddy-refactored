import java.io.IOException;

import org.json.simple.parser.ParseException;

public class MessageFilter {
	
	   private ServerMethods srvmt = new ServerMethods();
	
	public String filterMessage(String actionIn, String messageIn){
	    	String message = null;
	    	try{
	    		switch (actionIn) {
	    		case "login":
	    			message = srvmt.Login(messageIn);
	    			break;
	    		case "register":
	    			message = srvmt.Register(messageIn); 
	    			break;
	    		case "get":
	    			message = (srvmt.get(messageIn));
	    			break;
	    		case "change":
	    			message = (srvmt.modify(messageIn));
	    			break;
	    		case "getother":
	    			message = (srvmt.getothers(messageIn));
	    			break;
	    		case "changecourse":
	    			message = (srvmt.addormodifycourse(messageIn));
	    			break;
	    		case "removecourse":
	    			message = (srvmt.removecourse(messageIn));
	    			break;
	    		case "match":
	    			message = (srvmt.MatchEngine(messageIn));
	    			break;
	    		case "search":
	    			message = (srvmt.SearchEngine(messageIn));
	    			break;
	    		case "removeaccount":
	    			message = (srvmt.remove(messageIn));
	    			break;
	    		default:
	    			message = messageIn;
	    			break;
	    		}
	    	}catch (ParseException e) {
				e.printStackTrace();
			}catch(IOException ioException){
	            ioException.printStackTrace();
	        }
	    	return message;
	    }
}
