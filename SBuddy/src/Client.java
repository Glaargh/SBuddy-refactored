import java.io.*;
import java.net.*;
public class Client implements Runnable
{

	/**
	 * Base Client class for connecting to the Provider
	 */
	
    private Socket requestSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String message;
    private String serverIP;
    private int serverPort;
    
    /**
     * Construct the class by passing on arguments for the IP and the port of the server
     * @param args
     */
    public Client(String[] args) {
    	serverIP = args.length >= 1 ? args[0] : "";
    	serverPort = args.length == 2 ? Integer.parseInt(args[1]): 0;
    }
    
    /**
     * The run method of the Client class
     */
    public void run()
    {
        try{
            //1. creating a socket to connect to the server
        	System.out.println("Connecting...");
            requestSocket = new Socket(serverIP, serverPort);
            System.out.println("Connected to " + requestSocket.getInetAddress() + " on port "
			          + requestSocket.getPort() + " from port " + requestSocket.getLocalPort() + " of "
			          + requestSocket.getLocalAddress());
           
            //2. get Input and Output streams
            out = new PrintWriter(requestSocket.getOutputStream(), true);
            out.flush();
            in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
            BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
            
            //3: Communicating with the server
            while(!(message=getMessage()).equals("exit")) {
                //message to ensure Connection is established.
				System.out.println("server>" + message);
				
				String userInput = userInputBR.readLine();
				message = userInput;
				
				sendMessage(message);
            }
            
          
        }
        catch(UnknownHostException unknownHost){
            System.err.println("You are trying to connect to an unknown host!");
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                requestSocket.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
    
    /**
     * Send a message msg to the server and directly flush the stream after.
     * @param msg
     */
    public void sendMessage(String msg)
    {
        out.println(msg);
		out.flush();
    }
    
    /**
     * Returns a String message as a response from the server
     * @return String
     * @throws IOException
     */
    public String getMessage() throws IOException
    {
    	String msg = (String) in.readLine();
    	return msg;
    }
    
    /**
     * Sends a message and returns its corresponding response from the server
     * @param to The request
     * @return String The response
     * @throws IOException
     */
    public String toServer(String to) throws IOException
    {
    	sendMessage(to);
    	return getMessage();
    }
}