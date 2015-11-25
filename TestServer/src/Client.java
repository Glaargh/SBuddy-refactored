import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Client extends JFrame implements ActionListener{
	private JTextField nummer1 = new JTextField(3);
	private JLabel plus = new JLabel("+");
	private JTextField nummer2 = new JTextField(3);
	private JLabel equals = new JLabel("=");
	private JLabel sum = new JLabel();
	private JTextField msg = new JTextField(20);
	private JButton addButton = new JButton("Voeg toe");
	
	private BufferedReader in;
	
	private PrintWriter out;
	
	private Socket socket;
	
	public Client(){
		
		
		add(nummer1);
		add(plus);
		add(nummer2);
		add(equals);
		add(sum);
		add(msg);
		add(addButton);
		
		setLayout(new FlowLayout());
		setTitle("Addition Client");
		msg.setHorizontalAlignment(JLabel.CENTER);
		addButton.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 150); setLocation(300, 300);
		
	}
	
	public void startClient(){
		try
		{
			socket =new Socket("localhost", 4321);
			System.out.println(socket.isConnected());
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		
		catch (UnknownHostException e){
			msg.setText("Unknown host");
		}
		catch (IOException except){
			msg.setText("Network exception");
		}
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("test");
		try{
			String n1 = nummer1.getText();
			String n2 = nummer2.getText();
			out.println(n1);
			out.println(n2);
			out.flush();
			int result = Integer.parseInt(in.readLine());
			System.out.println(result);
			sum.setText("" + result);
		}
		catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	
}
