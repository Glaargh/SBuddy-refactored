import java.io.*;
import java.net.*;
public class Provider{
    ServerSocket providerSocket;
    Socket connection = null;
    private ClientListener listener;
    Provider(){}
    void run()
    {
        try{
            //1. creating a server socket
            providerSocket = new ServerSocket(2055);
            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            System.out.println("Connected to " + connection.getInetAddress() + " on port "
			          + connection.getPort() + " FROM port " + connection.getLocalPort() + " of "
			          + connection.getLocalAddress());
            
            //3. Creating a thread for incoming client
            listener = new ClientListener(connection);
            Thread t = new Thread(listener);
            t.start();
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    
    public static void main(String args[])
    {
        Provider server = new Provider();
        while(true){
            server.run();
        }
    }
}