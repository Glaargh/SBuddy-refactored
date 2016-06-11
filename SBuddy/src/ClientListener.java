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
        String message;
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
				parseMessage(message);
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
    
    public void parseMessage(String message){
    	try{
    		System.out.println(connection.getInetAddress().getHostName() 
					+ "> "  + connection.getPort() + "> "   + message);
			
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(message);
			String action = (String) json.get("action");
			filterMessage(action, message);
    	} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    public void filterMessage(String actionIn, String messageIn){
    	try{
    		switch (actionIn) {
    		case "login":
    			sendMessage(srvmt.Login(messageIn));
    			break;
    		case "register":
    			sendMessage(srvmt.Register(messageIn)); 
    			break;
    		case "get":
    			sendMessage(srvmt.get(messageIn));
    			break;
    		case "change":
    			sendMessage(srvmt.modify(messageIn));
    			break;
    		case "getother":
    			sendMessage(srvmt.getothers(messageIn));
    			break;
    		case "changecourse":
    			sendMessage(srvmt.addormodifycourse(messageIn));
    			break;
    		case "removecourse":
    			sendMessage(srvmt.removecourse(messageIn));
    			break;
    		case "match":
    			sendMessage(srvmt.MatchEngine(messageIn));
    			break;
    		case "search":
    			sendMessage(srvmt.SearchEngine(messageIn));
    			break;
    		case "removeaccount":
    			sendMessage(srvmt.remove(messageIn));
    			break;
    		default:
    			sendMessage(messageIn);
    			break;
    		}
    	}catch (ParseException e) {
			e.printStackTrace();
		}catch(IOException ioException){
            ioException.printStackTrace();
        }
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
