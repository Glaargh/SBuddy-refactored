import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.beans.binding.BooleanBinding;

public class Options extends Application{
	
	Label TaalDisplayLabel = new Label();
	Label PasswordDisplayLabel = new Label();
	String taalDisplay = "Nederlands";
	String passWord;
	String passwoordchangetext1;
	String passwoordchangetext2;
	String passwoordbutton;
	String passwoordprompt;
	String TaalStringButton;
	
	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		TaalStringButton = "Verander taal";
		
		//Alle strings moeten voor de taalveranderingsbutton gezet worden op standaard, declareren bij labelss.
		passwoordprompt = "Nieuw wachtwoord";
		
		passwoordchangetext1 = "Uw nieuw wachtwoord is '";
		passwoordchangetext2 = "'";
		passwoordbutton = "Verander uw wachtwoord";
		
		final TextField passwoord = new TextField ();
		passwoord.setPromptText(passwoordprompt);
		
		Button passwoordchange = new Button(passwoordbutton);
		passwoordchange.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent args){
				PasswordDisplayLabel.setText(passwoordchangetext1 + passwoord.getText() + passwoordchangetext2);
				passWord = passwoord.getText();
			}
		});
		//Disabled de button
		BooleanBinding passwoordchangeStopper = new BooleanBinding() {
		    {
		       super.bind(passwoord.textProperty());
		    }
		    @Override
		    protected boolean computeValue() {
		    	//Minimale lengte 6 karakters.
		    	return (passwoord.getText().length()<6);
		       }
		 };
		passwoordchange.disableProperty().bind(passwoordchangeStopper);
		VBox passwoordHolder = new VBox();
		passwoordHolder.getChildren().addAll(passwoord,passwoordchange,PasswordDisplayLabel);
		passwoordHolder.setSpacing(10);
		passwoordHolder.getStyleClass().add("passwoordHoldR");
		passwoordHolder.setLayoutX(300);
		passwoordHolder.setLayoutY(300);
		
		PasswordDisplayLabel.getStyleClass().add("passwoordDisplayLabel");
		passwoord.getStyleClass().add("passwoord");
		
		final ComboBox<String> TaalOpties = new ComboBox<String>();
		TaalOpties.getItems().addAll(
			"Nederlands",	
			"Engels"
		);
		TaalOpties.setValue(taalDisplay);
		
		
		Button taalButton = new Button(TaalStringButton);
		taalButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent args){
				taalDisplay = (String) TaalOpties.getValue();
				switch(taalDisplay){
					case "Dutch":
					case "Nederlands" : passwoordchangetext1 = "Uw nieuw wachtwoord is '";
					passwoordchangetext2 = "'.";
					passwoordbutton = "Verander uw wachtwoord";
					passwoordprompt = "Nieuw wachtwoord";
					TaalStringButton = "Verander taal";
					taalDisplay = "Nederlands";
					TaalOpties.getItems().retainAll();
					TaalOpties.getItems().addAll("Nederlands","Engels");
					break;
					case "English" :
					case "Engels" : passwoordchangetext1 = "Your new password is '";
					passwoordchangetext2 = "'.";
					passwoordbutton = "Change your password";
					passwoordprompt = "New password";
					TaalStringButton = "Change language";
					taalDisplay = "English";
					TaalOpties.getItems().retainAll();
					TaalOpties.getItems().addAll("Dutch","English");
					break;
				}
				//Om te testen, verwijderen zodra mogelijk.
				TaalDisplayLabel.setText(taalDisplay);
				passwoord.setPromptText(passwoordprompt);
				taalButton.setText(TaalStringButton);
				passwoordchange.setText(passwoordbutton);
				TaalOpties.setValue(taalDisplay);
			}
		});
		TaalDisplayLabel.getStyleClass().add("taalDisplayLabel");
		VBox TaalOptieHolder = new VBox();
		TaalOptieHolder.getChildren().addAll(TaalOpties,taalButton,TaalDisplayLabel);
		TaalOptieHolder.setSpacing(10);
		TaalOptieHolder.setLayoutX(100);
		TaalOptieHolder.setLayoutY(100);
		/**
		 * 			switch(taalDisplay){
					case "Nederlands" : passwoordchangetext1 = "Uw nieuw wachtwoord is '";
					passwoordchangetext2 = "'.";
					passwoordbutton = "Verander uw wachtwoord";
					passwoordprompt = "Nieuw wachtwoord";
					TaalStringButton = "Verander taal";
					TaalOpties.getItems().retainAll();
					TaalOpties.getItems().addAll("Nederlands","Engels");
					break;
					case "English" : passwoordchangetext1 = "Your new password is '";
					passwoordchangetext2 = "'.";
					passwoordbutton = "Change your password";
					passwoordprompt = "New password";
					TaalStringButton = "Change language";
					TaalOpties.getItems().retainAll();
					TaalOpties.getItems().addAll("Dutch","English");
					break;
				}
		 */
		
		Pane root = new Pane();
		root.getChildren().addAll(TaalOptieHolder,passwoordHolder);
		root.getStyleClass().add("Background");
		Scene OptiesScene = new Scene(root, 1600, 900);
		primaryStage.setScene(OptiesScene);
		
		OptiesScene.getStylesheets().add("Opties.css");
		primaryStage.show();
	}
}
