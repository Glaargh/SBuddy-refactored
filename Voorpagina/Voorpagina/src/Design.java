import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Design extends Application{
	Label lb_text;
	Label labels = new Label();
	
	public static void main(String[] args){
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Label labelLogo = new Label("S Buddy");
		
		lb_text = new Label();
		labels = new Label();
		Label labelmin2 = new Label("Email:");
		TextField email = new TextField ();
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(labelmin2, email);
		hb1.setSpacing(10);
		
		Label labelmin1 = new Label("Password:");
		TextField password = new TextField ();
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(labelmin1, password);
		hb2.setSpacing(10);
		
		Button button1 = new Button("Log in");
		button1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent args){
				labels.setText("We are unable to process your request at this time.");
			}
		});
		
		Button button2 = new Button("Forgot your password?");
		button2.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent args){
				lb_text.setText("We'll recover your password for you shortly");
			}
		});
		
		Label find = new Label("Find a study buddy");
		Label free = new Label("get started - ");
		Label free2 = new Label("it's free.");	
		
		Label label1 = new Label("First name:");
		TextField firstname = new TextField ();
		HBox hb3 = new HBox();
		hb3.getChildren().addAll(label1, firstname);
		hb3.setSpacing(10);
		
		Label label2 = new Label("Last name:");
		TextField lastname = new TextField ();
		HBox hb4 = new HBox();
		hb4.getChildren().addAll(label2, lastname);
		hb4.setSpacing(10);

		Label label3 = new Label("Email:");
		TextField emailaddress = new TextField ();
		HBox hb5 = new HBox();
		hb5.getChildren().addAll(label3, emailaddress);
		hb5.setSpacing(10);
		
		Label label4 = new Label("Password:");
		TextField passwords = new TextField ();
		HBox hb6 = new HBox();
		hb6.getChildren().addAll(label4, passwords);
		hb6.setSpacing(10);
		
//		Label displaytext = new Label("");
		Button button3 = new Button("Join now");
		button3.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent args){
//				displaytext.setText("I'm sorry, we can't process your application right now.");
			}
		});
		
		VBox root = new VBox();
		root.getChildren().addAll(labelLogo,hb1,hb2,button1,labels,button2,lb_text,find,free,free2,hb3,hb4,hb5,hb6,button3);
		
		Scene scenetest = new Scene(root, 700, 700);
		primaryStage.setScene(scenetest);
		
		primaryStage.show();
	}
}
