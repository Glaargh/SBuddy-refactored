import java.net.*;
import java.io.*;
import javax.swing.*;


public class Server extends JFrame{
	Socket connection =  null;
	ServerSocket listenSocket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	
	public void startServer(){
		
		
		int first, second, sum;
		
		
		try{
			listenSocket = new ServerSocket(4321);
		}
		catch(IOException e){
			System.out.println("Port bezet");
		}
		
		try{
			connection = listenSocket.accept();
		}
		catch(IOException e){
			System.out.println("Accept failed: 4321");
			System.exit(-1);
		}
		
		try{
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			out = new PrintWriter(connection.getOutputStream(), true);
		}
		catch(IOException e){
			System.out.println("fail");
		}
		
		while(true){
			try{
				first = Integer.parseInt(in.readLine());
				second = Integer.parseInt(in.readLine());
				
				sum = first + second;
				System.out.println(sum);
				out.println(sum);
			}
			catch(IOException e){
				System.out.println(e);
			}
		}
		
		
		
	}
	
	
}
