import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
public class Client 
{
	//Run the Server class and then the Client Class. Go to re Client Class, fill in your credentials below. 
	//Type "Login" to login, if you want to login but haven't registered yet it will refuse. 
	//Type "Register" to create and account, if the entered email is already taken it will refuse. After register you can login.
	String Firstname = "Jake";
	String Lastname = "Perrella";
	String Email = "12345@gmail.com";
	String Password = "helloworld";
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
        	System.out.println("Connecting...");
            requestSocket = new Socket("localhost", 8080);
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
				
				if (message.equals("Register"))
				{//Json String will be send here to the server if "Submit" is entered. At the server it will be saved to a file
						sendMessage("INCOMING-REGISTER" + ClientMethods.Register(Firstname, Lastname, Email,Password));
				}
				else if (message.equals("Login"))
				{
						sendMessage("INCOMING-LOGIN" + ClientMethods.Login(Email,Password));
				}
				else if(message.equals(message))
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