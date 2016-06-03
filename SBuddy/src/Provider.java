import java.io.*;
import java.net.*;
public class Provider
{
	
	private ServerSocket providerSocket;
    private Socket connection = null;
    private ClientListener listener;
    private int port;
    private boolean running = false;
    
    /**
     * Construct the server
     * @param portNumber The port to listen to
     */
    public Provider(int portNumber) {
    	port = portNumber;
    }
    
    /**
     * Start listening to client sockets
     */
    public void run()
    {
        try{
        	//1. creating a server socket
        	providerSocket = new ServerSocket(port);
        	//2. Wait for connection
        	System.out.println("Waiting for connection");
        	running = true;
	        while(running){
	        	connection = providerSocket.accept();
	        	System.out.println("Connected to " + connection.getInetAddress() + " on port "
				         + connection.getPort() + " FROM port " + connection.getLocalPort() + " of "
				         + connection.getLocalAddress());
	            	        		//3. Creating a thread for incoming client
	        	listener = new ClientListener(connection);
	        	Thread t = new Thread(listener);
	        	t.start();
	        }
	    } catch(BindException e){
	    	System.out.println("Server already launched");
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
    }
    
    /**
     * Terminate the server
     */
    public void terminate() {
    	running = false;
    }
}