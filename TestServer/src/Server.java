import java.net.*;
import java.io.*;
import javax.swing.*;


public class Server extends JFrame{

	public static void main(String[] args) throws NumberFormatException, IOException{

		Server socketFrame = new Server();
		socketFrame.setVisible(false);
		socketFrame.startServer();
	}
	public void startServer() throws IOException{
		
			
			ServerSocket listenSocket = new ServerSocket(50);
			System.out.println("Port Created: "+ listenSocket.isBound());
			Socket connection = listenSocket.accept();
	
		
	
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
	

		String message = in.readLine();
		System.out.println(message);
		if(message != null)
		{
			out.println("We have received your message!");
		}	
		else 
		{
			out.println("Sorry, there is an error.");
		}
		
		listenSocket.close();
	}
	
	
}
