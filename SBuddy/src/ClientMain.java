import javafx.application.Application;
import javafx.stage.Stage;

public class ClientMain extends Application {

	/**
	 * The main execution of the client side of the program
	 * 
	 * @param args
	 */

	private Client client;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Starts the execution of the program by creating a GUI and starting it.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		String[] args = new String[2];
		args[0] = "localhost";
		args[1] = "8080";

		connect(args);

		Design GUI = new Design(client);
		GUI.start(primaryStage);
	}

	public void connect(String[] connection) {
		client = new Client(connection);
		Thread ClientSocket = new Thread(client);
		ClientSocket.start();

	}

}
