import java.net.*;
import java.io.*;
import javax.swing.*;


public class Server 
{
	public static void main(String[] args) throws NumberFormatException, IOException{

		Server socketFrame = new Server();
		socketFrame.startServer();
	}
	public void startServer() throws IOException{
		
			
			ServerSocket listenSocket = new ServerSocket(988);
			System.out.println("Port Created: "+ listenSocket.isBound());
			System.out.println("Waiting for connection");
			while (true)
			{
				Socket connection = listenSocket.accept();
				System.out.println("Connection received from " + connection.getInetAddress().getHostName());
				PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				out.println("Whats your name?");
				String name = in.readLine();
				out.println("Hello "+ name+ ", welcome to SBuddy. Here you will meet many great study buddy.");
				out.close();
			
				connection.close();
		}
		
			}
	
	
}
