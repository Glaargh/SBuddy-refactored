import java.io.*;
import java.net.*;
public class ClientListener implements Runnable {
	private Socket connection;
	PrintWriter out;
    BufferedReader in;
    
    public ClientListener(Socket connectionIn){
    	this.connection = connectionIn;
    	
    }
    
    public void run()
    {
        String message;
    	try{
            
            //3. get Input and Output streams
            out = new PrintWriter(connection.getOutputStream(), true);
            //out.flush();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            sendMessage("Connection successful");
            
            do{
                message = (String)in.readLine();
				System.out.println("client>" + message);
				if (message.contains("INCOMING-REGISTER"))
				{

					sendMessage(ServerMethods.Register(message)); 
				}
				else if (message.contains("INCOMING-LOGIN"))
				{

					sendMessage(ServerMethods.Login(message));
				}
				else if(message.equals(message))
				{sendMessage(message);}
				
				
				if (message.equals("exit"))
				{
				    sendMessage("exit");
				}
            }while(!message.equals("exit"));
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
    void sendMessage(String msg)
    {
        out.println(msg);
		out.flush();

    }
}
