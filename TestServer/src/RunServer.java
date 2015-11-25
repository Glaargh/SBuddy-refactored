
public class RunServer {
	public static void main(String[] args){

		Server socketFrame = new Server();
		socketFrame.setVisible(true);
		socketFrame.startServer();
		
		
		Client socketFrame2 = new Client();
		socketFrame2.setVisible(true);
		socketFrame2.startClient();
	}
}
