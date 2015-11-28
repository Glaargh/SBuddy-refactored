import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame
{
	
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		Client socketFrame2 = new Client();
		socketFrame2.setVisible(true);
		socketFrame2.startClient();
		
	}
	
	private JLabel sum = new JLabel();

	public Client(){
		
		add(sum);
		setLayout(new FlowLayout());
		setTitle("Addition Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 150); setLocation(300, 300);
		
	}
	
	public void startClient() throws UnknownHostException, IOException{
	
			Socket socket =new Socket("localhost", 50);
			System.out.println("Connected: "+ socket.isConnected());
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("Hello FROM CLIENT!!!");
			out.flush();
			String message = in.readLine();
			System.out.println(message);
			sum.setText(message);
			socket.close();
	}

	
	
}
