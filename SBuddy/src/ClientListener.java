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
				System.out.println(connection.getInetAddress().getHostName() 
						+ "> "  + connection.getPort() + "> "   + message);
				
				if (message.equals("exit")) {
					sendMessage("exit");
					break;
				}
				
				JSONParser parser = new JSONParser();
				
				JSONObject json = (JSONObject) parser.parse(message);
				String action = (String) json.get("action");
				
				
				switch (action) {
				case "login":
					sendMessage(srvmt.Login(message));
					break;
				case "register":
					sendMessage(srvmt.Register(message)); 
					break;
				case "get":
					sendMessage(srvmt.get(message));
					break;
				case "change":
					sendMessage(srvmt.modify(message));
					break;
				case "getother":
					sendMessage(srvmt.getothers(message));
					break;
				case "changecourse":
					sendMessage(srvmt.addormodifycourse(message));
					break;
				case "removecourse":
					sendMessage(srvmt.removecourse(message));
					break;
				case "match":
					sendMessage(srvmt.MatchEngine(message));
					break;
				case "search":
					sendMessage(srvmt.SearchEngine(message));
					break;
				case "removeaccount":
					sendMessage(srvmt.remove(message));
					break;
				default:
					sendMessage(message);
					break;
				}
            }
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        } catch (ParseException e) {
			e.printStackTrace();
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
