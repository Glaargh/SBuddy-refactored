import java.io.*;
import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class functions as an answer machine for the client. The client makes
 * a request and the request goes through this class to search for an answer in
 * the database.
 */

public class ClientListener implements Runnable {
	private Socket connection;
	private PrintWriter out;
    private BufferedReader in;
    private ServerMethods srvmt = new ServerMethods();
    
    
    /**
     * Construct the class with the Socket
     * @param connectionIn
     */
    public ClientListener(Socket connectionIn){
    	this.connection = connectionIn;	
    }
    
    /**
     * Will respond to the client based on its given action in its JSONObject
     */
    public void run()
    {
        String message, message2;
    	try{          
            //3. get Input and Output streams
            out = new PrintWriter(connection.getOutputStream(), true);
            //out.flush();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            sendMessage("Connection successful");          
            while(!(message=(String)in.readLine()).equals("exit")) {
				
				if (message.equals("exit")) {
					sendMessage("exit");
					break;
				}
				message2 = parseMessage(message);	
				sendMessage(filterMessage(message2, message));
			}    
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                connection.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
    
    public String parseMessage(String message){
    	String action = null;
    	try{
    		System.out.println(connection.getInetAddress().getHostName() 
					+ "> "  + connection.getPort() + "> "   + message);			
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(message);
			action = (String) json.get("action");
    	} catch (ParseException e) {
			e.printStackTrace();
		}
    	return action;
    }
    
    /**
     * Filters the message with it's corresponding action.
     * @param actionIn
     * @param messageIn
     */
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
    
    /**
     * Sends a message to the client
     * @param msg
     */
    public void sendMessage(String msg)
    {
        out.println(msg);
		out.flush();
    }
}
