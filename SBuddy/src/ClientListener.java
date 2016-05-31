

import java.io.*;
import java.net.*;
public class ClientListener implements Runnable {
	private Socket connection;
	PrintWriter out;
    BufferedReader in;
    ServerMethods srvmt = new ServerMethods();
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
				System.out.println(connection.getInetAddress().getHostName() 
						+ "> "  + connection.getPort() + "> "   + message);
				if (message.contains("INCOMING-REGISTER"))
				{

					sendMessage(srvmt.Register(message)); 
				}
				else if (message.contains("INCOMING-LOGIN"))
				{

					sendMessage(srvmt.Login(message));
					
				}
			   else if (message.contains("INCOMING-GET"))
				  {

				     	sendMessage(srvmt.get(message));
			     }
		       else if (message.contains("INCOMING-CHANGE"))
			   {
			     	sendMessage(srvmt.modify(message));
		     	}
				///////////////////////NEW////
				
		       else if (message.contains("INCOMING-FROMOTHERSGET"))
			   {//this can be used without being logged in*****************
		    	   //INCOMING-FROMOTHERSGET¿pimdhn@gmail.com¿Lastname
			     	sendMessage(srvmt.getothers(message));
		     }
		       else if (message.contains("INCOMING-COURSECHANGE"))
			   {
			     	sendMessage(srvmt.addormodifycourse(message));
		     	}
		       else if (message.contains("INCOMING-COURSEREMOVE"))
			   {//INCOMING-COURSEREMOVE¿Computer Organization
			     	sendMessage(srvmt.removecourse(message));
		     	}
		       else if (message.contains("INCOMING-MATCH"))
			   {//INCOMING-MATCH¿Computer Science¿Technical University of Delft¿Rotterdam
		    	 //this can be used without being logged in*****************
			     	sendMessage(srvmt.MatchEngine(message));
		     	}
		       else if (message.contains("INCOMING-SEARCH"))
			   {//INCOMING-SEARCH¿Email¿pimdhn@gmail.com
		    	   //this can be used without being logged in*****************
			     	sendMessage(srvmt.SearchEngine(message));
		     	}
		       else if (message.contains("INCOMING-ACCREMOVE"))
			   {//INCOMING-ACCREMOVE¿jUnittest@gmail.com
		    	   //this can be used without being logged in*****************
			     	sendMessage(srvmt.remove(message));
		     	}
				/////////////////NEW/////
				else 
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

