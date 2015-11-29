import java.io.*;
import java.net.*;

public class Client
{
	
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		Client socketFrame2 = new Client();
		socketFrame2.startClient();
	}
	public void startClient() throws UnknownHostException, IOException{
	
		while (true){
			Socket socket = new Socket("localhost", 988);
			System.out.println("Connected to " + socket.getInetAddress() + " on port "
			          + socket.getPort() + " from port " + socket.getLocalPort() + " of "
			          + socket.getLocalAddress());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			System.out.println("Server says: " + in.readLine());
			
			//this is for the user input (will be sent to server)
			BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
			String userInput = userInputBR.readLine();
			out.println(userInput);
			out.flush();
			System.out.println("Server says: " + in.readLine());

			if ("exit".equalsIgnoreCase(userInput)) 
			{
				socket.close();
				break;
			}
	}

	
	
}}
