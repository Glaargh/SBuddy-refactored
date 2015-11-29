import java.io.*;
import java.net.*;
public class Client{
	
	 public static void main(String args[])
	    {
		 	Client client = new Client();
	        client.run();
	    }
	
	
    Socket requestSocket;
    PrintWriter out;
    BufferedReader in;
    String message;
    Client(){}
    void run()
    {
        try{
            //1. creating a socket to connect to the server
            requestSocket = new Socket("localhost", 2055);
            System.out.println("Connected to " + requestSocket.getInetAddress() + " on port "
			          + requestSocket.getPort() + " from port " + requestSocket.getLocalPort() + " of "
			          + requestSocket.getLocalAddress());
            //2. get Input and Output streams
            
            out = new PrintWriter(requestSocket.getOutputStream(), true);
            out.flush();
            in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
            BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
            //3: Communicating with the server
            do{
                message = (String)in.readLine();
				System.out.println("server>" + message);
				String userInput = userInputBR.readLine();
				message = userInput;
				sendMessage(message);
            }while(!message.equals("bye"));
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
    void sendMessage(String msg)
    {
        out.println(msg);
		out.flush();

    }
   
}