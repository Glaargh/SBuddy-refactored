import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
public class Client extends InputToJson
{
	
	 public static void main(String args[])
	    {
		 	Client client = new Client();
	        try {
				client.run();
			} catch (InterruptedException e) {
				// TODO Auto-generatesed catch block
				e.printStackTrace();
			}
	    }
	
	
    Socket requestSocket;
    PrintWriter out;
    BufferedReader in;
    String message;
    Client(){}
    void run() throws InterruptedException
    {
        try{
            //1. creating a socket to connect to the server
            requestSocket = new Socket("localhost", 2000);
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
                //message to ensure Connection is established.
				System.out.println("server>" + message);
				
				String userInput = userInputBR.readLine();
				message = userInput;
				
				if (message.equals("Submit")&& InputtoJSON()!= null)
				{//Json String will be send here to the server if "Submit" is entered. At the server it will be saved to a file
						sendMessage("INCOMING-JSON" + InputtoJSON());
				}
				else
				{sendMessage(message);}
 			
            }while(!message.equals("exit"));
            
          
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