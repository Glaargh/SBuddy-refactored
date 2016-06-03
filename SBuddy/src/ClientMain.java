import javafx.application.Application;
import javafx.stage.Stage;

public class ClientMain extends Application {

	/**
	 * The main execution of the client side of the program
	 * @param args
	 */
	
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
		
		Design GUI = new Design(args);
		GUI.start(primaryStage);
	}
}
