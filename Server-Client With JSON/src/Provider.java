import java.io.*;
import java.net.*;
public class Provider extends JSONToFileOnServer{
	public static void main(String args[])
    {
        Provider server = new Provider();
        while(true)
        {
            server.run();
        }
    }
    ServerSocket providerSocket;
    Socket connection = null;
    PrintWriter out;
    BufferedReader in;
    String message;
    Provider(){}
    String UglyJson;
    String PrettyJson;
    void run()
    {
        try{
            //1. creating a server socket
            providerSocket = new ServerSocket(2000, 10);
            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            System.out.println("Connected to " + connection.getInetAddress() + " on port "
			          + connection.getPort() + " FROM port " + connection.getLocalPort() + " of "
			          + connection.getLocalAddress());
            //3. get Input and Output streams
            out = new PrintWriter(connection.getOutputStream(), true);
            out.flush();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            sendMessage("Connection successful");
            //4. The two parts communicate via the input and output streams
            do{
                message = (String)in.readLine();
				System.out.println("client>" + message);
				if (message.contains("INCOMING-JSON"))
				{

					TOJSONDATABASE(message);
				}
				
				sendMessage(message);
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
                providerSocket.close();
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