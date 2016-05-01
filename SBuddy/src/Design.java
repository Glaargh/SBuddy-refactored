import java.awt.MouseInfo;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import javax.imageio.ImageIO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.shape.Rectangle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Font;

/**
 * @author 		C0d3Bust3rs
 * Revision: 	C0d3Bust3rs 2.0
 * Date:		28-04-2016
 * READ JAVADOC CAREFULY WHILE ATTEMPTING TO MODIFY CODE. 
 * ANY RECKLESS MODIFICATION MIGHT RESULT IN HIDDEN FAILURES OF THIS PROGRAM WHICH ARE COSTLY TO FIX.
 * 
 * TO SEE ALL THE GENERAL EXPLANATION OF THE PROGRAM, COLLAPSE ALL BY PUSHING:
 * CTRL + SHIFT + DIVIDE KEY ( this key: / )
 * 
 * DUE TO THE CODE'S COMPLEXITY & VAGUENESS, IT'S BEST TO KEEP TAB CODES CLOSED AND EXPAND THEM ONLY IF YOU ARE GOING TO WORK WITH THEM. 
 * USE TEST COVERAGE TO SEE EXACTLY WHERE CODES ARE TESTABLE (SUCH AS PARSING CODE) SO YOU CAN TRANSFER THEM TO SEPERATE METHODS.
 */
public class Design extends Application {
	
	
	/**
	 * VARIABLES, LABELS INITIALIZATION OF THE PROGRAM. 
	 * From observation, some of the variables such as "Label PasswordDisplayLabel = new Label("Uw wachtwoord is veranderd.");" 
	 * should not be initialized as global variables, so they need to be separated and put into their own local method.
	 * This is the first step of refactorization as you don't want those methods to encounter missing variables errors.
	 * Thus the less global variables there are, the better.
	 */
		Label lb_text;
		Label labels;
		Label labelss;

		int countMatch = 0;
		int countProfile = 0;
		int countOpties = 0;
		int countCourse = 0;
		int countHelp = 0;

		Label noResults = new Label("No results were found.");
		String SearchTypeString = "Name";
		boolean AvailableNow = false;
		Button matchButton = new Button("Make match with selected");
		// Lijst maken. Eerst leeg laten.
		ListView<String> SearchResultList = new ListView<String>();

		// Results of search by filter.
		ListView<String> filterTable = new ListView<String>();

		// Checkbox urgent.
		CheckBox UrgentCheck = new CheckBox("Urgent?");
		boolean urgency = false;
		TreeView<String> totLijst;

		final HBox hb = new HBox();
		// passWord moet veranderd worden naar de werkelijke passwoord van de user.
		Label TaalDisplayLabel = new Label();
		Label PasswordDisplayLabel = new Label("Uw wachtwoord is veranderd.");
		String taalDisplay = "Nederlands";
		String passWord;
		String passwoordbutton;
		String passwoordprompt;
		String TaalStringButton;
		
	/**
	 * WHERE THE PROGRAM STARTS. NO MODIFICATION NEEDED. 
	 */
	public static void main(String[] args) {

		Thread ClientSocket = new Thread(new Client());
		ClientSocket.start();
		launch(args);
	}

	/**
	 * LOGIN TAB, MOSTLY JAVAFX BUT THERE ARE A FEW CODE GROUPS WHICH CAN BE PUT INTO THEIR OWN METHODS SO IT CAN BE TESTED.
	 * RUN TEST COVERAGE AND RETURN TO THIS METHOD TO SEE WHERE EXACTLY THE CODE IS NEEDED TO BE TAKEN OUT.
	 */
	public void start(final Stage primaryStage) throws Exception {

		// **********************************************************PANE OF
		// TABS*********************
		primaryStage.setOnCloseRequest(event -> {
		    System.out.println("Stage is closing");
		    try {
				Client.toServer("exit");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.exit(0);
		});
		
		Pane rootCourseTab = new Pane();
		rootCourseTab.getStyleClass().add("vbox");

		Pane rootMatchTab = new Pane();
		rootMatchTab.getStyleClass().add("Background");

		Pane rootProfileTabe = new Pane();
		rootProfileTabe.setId("pane");

		Pane rootHelp = new Pane();
		rootHelp.setId("pane");

		Pane root = new Pane();
		root.setId("pane");

		final Scene scenetest = new Scene(root, 1300, 800);
		final Scene sceneProfileTabe = new Scene(rootProfileTabe, 1600, 900);
		final Scene sceneMatchTab = new Scene(rootMatchTab, 1600, 900);
		final Scene CourseScene = new Scene(rootCourseTab, 1800, 900);
		final Scene helpScene = new Scene(rootHelp, 1600, 900);

		// **********************************************************PANE OF
		// PROFILE TAB*********************

		/**
		 * Placeholder voor logo. Hier wordt naam gegeven.
		 */

		Image logo = new Image("log.jpg");
		ImageView imgview = new ImageView(logo);

		lb_text = new Label();
		labels = new Label();
		labels.getStyleClass().add("label12");
		labels.setLayoutX(730);
		labels.setLayoutY(120);
		labelss = new Label();

		/**
		 * Maken van email form. Zetten van voorgeplaatste text met
		 * setPromptText("Email")
		 */
		final TextField email = new TextField();
		email.setPromptText("Email");
		// email.setText("120567wolfert@gmail.com");
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(email);
		hb1.setSpacing(10);

		/**
		 * Maken van password form. Zetten van voorgeplaatste text met
		 * setPromptText("Password")
		 */
		final PasswordField password = new PasswordField();
		password.setPromptText("Password");
		// password.setText("iamironman96");
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(password);
		hb2.setSpacing(13);

		labels.setVisible(false);
		/**
		 * Maken van Login button met placeholderfunctie, laten zien dat we met
		 * .getText() de inhoud van de forms kunnen gebruiken.
		 */
		Button button1 = new Button("Log in");
		button1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				// *******************************************************************************************************
				// ******************************READ
				// ME******************************************************************
				// HERE IT WILL CHECK IF USER AND PASSWORD IS VALID. IT GO TO
				// PROFILE PAGE IF VALID. Else readbelow
				if (!(email.getText().isEmpty() || password.getText().isEmpty())) {
					try {
						if (Client
								.toServer(ClientMethods.Login(email.getText().trim(), password.getText().trim()))
								.equals("true")) {// return true if person is in
													// database and correct
													// password

							profileTab(primaryStage, scenetest, rootProfileTabe, sceneProfileTabe, sceneMatchTab,
									rootMatchTab, rootCourseTab, CourseScene);
							email.clear();
							password.clear();
						} else {// ADD a pop up or something that will say:
							labels.setText("Sorry, your combination is not valid. Please contact admin.");
							labels.setVisible(true);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					labels.setText("Please fill in both fields. Email must be valid.");
					labels.setVisible(true);
				}
			}
		});

		button1.setLayoutX(500);
		button1.setLayoutY(1000);

		/**
		 * Zetten van style van de button op de .my_customLabel uit de css.
		 */
		button1.getStyleClass().add("my_customLabel");

		/**
		 * Zelfde doen voor TextFields met .topFields.
		 */
		password.getStyleClass().add("topFields");
		email.getStyleClass().add("topFields");

		/**
		 * De login form en button in een HBox zetten voor makkelijk
		 * verplaatsen.
		 */
		HBox LoginHolder = new HBox();
		LoginHolder.getStyleClass().add("lgin");
		LoginHolder.getChildren().addAll(hb1, hb2, button1);

		/**
		 * Placeholderfunctie voor de button die een passwoord naar de
		 * emailadres moet sturen als het geklikt wordt.
		 */
		Button button2 = new Button("Forgot your password?");
		button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				lb_text.setText("Your password will be sent to your emailaddress shortly.");
			}
		});
		button2.setVisible(false);
		/**
		 * Geven van style .ForgotPassword uit css.
		 */
		button2.getStyleClass().add("ForgotPassword");

		/**
		 * Maken van text boven de form en geven van bijbehorende styles.
		 */
		Label find = new Label("Find a study buddy");
		find.getStyleClass().add("find");
		Label free = new Label("get started - it's ");
		Label free2 = new Label("free!");
		free2.getStyleClass().add("free2bolding");
		HBox freeholder = new HBox();
		freeholder.getChildren().addAll(free, free2);
		freeholder.getStyleClass().add("freeholder");

		/**
		 * Maken van form voor registreren (voornaam).
		 */
		Label label1 = new Label("First name:");
		label1.getStyleClass().add("firstname");
		final TextField firstname = new TextField();
		HBox hb3 = new HBox();
		hb3.getChildren().addAll(label1, firstname);
		hb3.getStyleClass().add("first");
		hb3.setSpacing(10);
		/**
		 * Maken van form voor achternaam.
		 */
		Label label2 = new Label("Last name:");
		label2.getStyleClass().add("lastname");
		final TextField lastname = new TextField();
		HBox hb4 = new HBox();
		hb4.getChildren().addAll(label2, lastname);
		hb4.getStyleClass().add("second");
		hb4.setSpacing(14);

		/**
		 * Maken van form voor email.
		 */
		Label label3 = new Label("Email:");
		label3.getStyleClass().add("email");
		final TextField emailaddress = new TextField();
		HBox hb5 = new HBox();
		hb5.getChildren().addAll(label3, emailaddress);
		hb5.getStyleClass().add("third");
		hb5.setSpacing(58);

		/**
		 * Maken van form voor passwoord.
		 */
		Label label4 = new Label("Password:");
		label4.getStyleClass().add("passwd");
		final TextField passwords = new TextField();
		HBox hb6 = new HBox();
		hb6.getChildren().addAll(label4, passwords);
		hb6.getStyleClass().add("fourth");
		hb6.setSpacing(15);

		/**
		 * Maken van button om te joinen met placeholderfunctie.
		 */
		Button button3 = new Button("Join now");
		button3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {

				if (!(emailaddress.getText().isEmpty() || firstname.getText().isEmpty() || lastname.getText().isEmpty()
						|| (passwords.getText().length() < 6))) {
					try {
						if (Client
								.toServer(ClientMethods.Register(firstname.getText().trim(), lastname.getText().trim(),
												emailaddress.getText().trim(), passwords.getText().trim()))
								.equals("true")) {
							Client.toServer(ClientMethods.Login(emailaddress.getText().trim(), passwords.getText().trim()));
							profileTab(primaryStage, scenetest, rootProfileTabe, sceneProfileTabe, sceneMatchTab,
									rootMatchTab, rootCourseTab, CourseScene);
							firstname.clear();
							lastname.clear();
							emailaddress.clear();
							passwords.clear();
						} else {// ADD a pop up or something that will say:
							labels.setText("Sorry, but your email already exist in our system.");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					labels.setText("Password must be greater than 6 characters. No blank fields.");
					labels.setVisible(true);
				}
			}
		});

		/**
		 * Plaatsen in HBox voor makkelijk verplaatsen en geven van style aan de
		 * relevante onderdelen.
		 */
		VBox logInfoHolder = new VBox();
		logInfoHolder.getChildren().addAll(hb3, hb4, hb5, hb6, button3, labelss);
		hb3.getStyleClass().add("RegInfoContents");
		hb4.getStyleClass().add("RegInfoContents");
		hb5.getStyleClass().add("RegInfoContents");
		hb6.getStyleClass().add("RegInfoContents");
		button3.getStyleClass().add("RegInfoContents");
		labelss.getStyleClass().add("RegInfoContents");
		logInfoHolder.getStyleClass().add("registerBox");
		logInfoHolder.setSpacing(15);
		HBox filler = new HBox();
		filler.setOpacity(.81);
		filler.getStyleClass().add("registerBox2");
		filler.setLayoutX(725);
		filler.setLayoutY(150);
		filler.setMinHeight(447);
		filler.setMinWidth(550);
		button3.getStyleClass().add("Button3Recolor");
		hb6.setSpacing(20);
	

		/**
		 * Plaatsen van alles in een Vbox voor display.
		 */

		root.getChildren().addAll(filler, imgview, LoginHolder, labels, button2, lb_text, find, freeholder,
				logInfoHolder);
		// Van 700 naar 755
		find.setLayoutX(755);
		find.setLayoutY(150);
		// Van 750 naar 805
		freeholder.setLayoutX(805);
		freeholder.setLayoutY(220);

		button2.setLayoutX(800);
		button2.setLayoutY(65);

		LoginHolder.setLayoutX(800);
		LoginHolder.setLayoutY(20);
		// veranderd van 850 naar 905
		logInfoHolder.setLayoutX(905);
		logInfoHolder.setLayoutY(300);

		/**
		 * Scene maken en zetten als de scene voor primaryStage.
		 */

		primaryStage.setScene(scenetest);

		/**
		 * De css toevoegen aan onze scene.
		 */
		scenetest.getStylesheets().add("Voorpagina.css");

		/**
		 * De scene laten zien.
		 */
		primaryStage.show();
	}

	/**
	 * PROFILE TAB. THIS IS THE TAB WITH THE MOST TESTABLE CODES. SEE COVERAGE FOR MORE INSTRUCTION
	 */
	@SuppressWarnings("deprecation")
	public void profileTab(final Stage primaryStage, final Scene scenetest, Pane rootProfileTabee,Scene sceneProfileTabee, final Scene sceneMatchTab, Pane rootMatchTab, Pane rootCourseTab, Scene CourseScene) throws IOException {
		/**
		 * een button help. De setaction moet gelinkt worden aan een nieuwe
		 * page, maar welke? (Dario nodig)
		 */
		Button help = new Button("Help");
		help.setMinWidth(90);
		help.getStyleClass().add("help");
		help.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				helpTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabee, scenetest, rootProfileTabee,
						rootCourseTab, CourseScene);

			}
		});
		// helpTab(final Stage primaryStage, final Scene sceneMatchTab, Pane
		// rootMatchTab, Scene sceneProfileTabe,final Scene scenetest, Pane
		// rootProfileTabe, Pane rootCourseTab, Scene CourseScene,Pane
		// rootHelp,Scene helpScene){

		/**
		 * Een button voor logout. De set action moet nog gemaakt worden om
		 * terug te gaan naar de hoofdpagina? (Dario nodig)
		 */
		Button logout = new Button("Log out");
		logout.setMinWidth(80);
		logout.getStyleClass().add("logout");
		logout.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {

				try {
					start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		/**
		 * Een box voor de help en logout buttons (dario nodig)
		 */
		HBox helpout = new HBox();
		helpout.getChildren().addAll(help, logout);
		helpout.getStyleClass().add("helpoutbox");

		/**
		 * Een button voor delete all om alle ingevoerde data in de profiletab
		 * te verwijderen
		 */
		Button deleteall = new Button("Delete all");
		deleteall.setMinSize(70, 30);
		deleteall.getStyleClass().add("deleteall");

		/**
		 * Een foto die hooft bij profile button (Dario nodig)
		 */
		Image prfoto = new Image(getClass().getResourceAsStream("huisteken.jpg"));
		ImageView profileimage = new ImageView(prfoto);
		Button profile = new Button("Profile", profileimage);
		profile.getStyleClass().add("profile");

		/**
		 * Een foto die hoort bij course button (Dario nodig)
		 */
		Image crfoto = new Image(getClass().getResourceAsStream("courseteken.jpg"));
		ImageView coursefoto = new ImageView(crfoto);
		Button courses = new Button("Courses", coursefoto);
		courses.getStyleClass().add("courses");
		courses.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				coursesTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabee, scenetest, rootProfileTabee,
						rootCourseTab, CourseScene);
			}
		});

		/**
		 * Een foto die hoort bij match button (Dario nodig)
		 */
		Image mfoto = new Image(getClass().getResourceAsStream("matchteken.jpg"));
		ImageView matchfoto = new ImageView(mfoto);
		Button match = new Button("Match", matchfoto);
		match.getStyleClass().add("match");
		match.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				// final Stage primaryStage, final Scene sceneMatchTab, Pane
				// rootMatchTab, Scene sceneProfileTabe
				matchTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabee, scenetest, rootProfileTabee,
						rootCourseTab, CourseScene);
			}
		});

		/**
		 * Een foto die hoort bij settings button (dario nodig)
		 */
		Image setfoto = new Image(getClass().getResourceAsStream("settingsteken.jpg"));
		ImageView settingsfoto = new ImageView(setfoto);
		Button settings = new Button("Settings", settingsfoto);
		settings.getStyleClass().add("settings");
		settings.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				// final Stage primaryStage, final Scene sceneMatchTab, Pane
				// rootMatchTab, Scene sceneProfileTabe
				settingsTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabee, scenetest, rootProfileTabee,
						rootCourseTab, CourseScene);
			}
		});
		/**
		 * Een VBox voor alle buttons links op de pagina (Dario nodig)
		 */
		VBox overzicht = new VBox();
		overzicht.getChildren().addAll(profile, courses, match, settings);
		overzicht.getStyleClass().add("overzicht");
		overzicht.setMinHeight(630);
		/**
		 * Een textfield waar je je naam in kan voeren DIT MOET Niet een text
		 * field zijn meer maar een string waar je er op klik komt er text field
		 * om te veranderen.
		 */

		String Vorn = Client.toServer("INCOMING-GET Firstname");
		final Label Voornaam = new Label(Vorn);
		Voornaam.getStyleClass().add("labelqqq");
		String Lstn = Client.toServer("INCOMING-GET Lastname");
		final Label Lastname = new Label(Lstn);
		Lastname.getStyleClass().add("labelqqq");
		HBox bovennaam = new HBox();
		bovennaam.getChildren().addAll(Voornaam, Lastname);
		bovennaam.getStyleClass().add("bovennaam");
		bovennaam.setSpacing(8);
		Voornaam.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);

			}
		});
		Voornaam.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {

				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});

		// Textfield used in pop up is given event listener
		Voornaam.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label("Your firstname \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(Voornaam.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						Voornaam.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE Firstname None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						Voornaam.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE Firstname " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});
		Lastname.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);

			}
		});
		Lastname.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {

				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});

		// Textfield used in pop up is given event listener
		Lastname.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label("Your lastname\nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(Lastname.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						Lastname.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE Lastname None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						Lastname.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE Lastname " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});

		/**
		 * Een textfield waar je een korte beschrijving kan geven over je zelf
		 * DIT MOET Niet een text field zijn meer maar een string waar je er op
		 * klik komt er text field om te veranderen.
		 */

		
		String programa = Client.toServer("INCOMING-GET CurrentStudy");
		Label student = new Label("Student " + programa);
		student.getStyleClass().add("labelSSS4");
		String univeristy = Client.toServer("INCOMING-GET CurrentUniversity");
		 Label at = new Label(" at " + univeristy);
		at.getStyleClass().add("labelSSS4");
		
		if(programa.trim().equals("Click to modify"))
		{
			student = new Label("Please complete profile page");
			student.getStyleClass().add("labelSSS4");
			at = new Label("and relogin to see changes");
			at.getStyleClass().add("labelSSS4");
		}
		VBox desci = new VBox();
		desci.getChildren().addAll(student, at);
		desci.getStyleClass().add("desci");
		desci.setAlignment(Pos.CENTER);
		desci.setSpacing(5);
		

		Label summary = new Label("Summary");
		summary.getStyleClass().add("summary");

		/**
		 * Een textfield waar je een iets uitgebreidere samenvatting kan geven
		 * over jezelf
		 */

		String descr = Client.toServer("INCOMING-GET Description");
		final Label descriptn = new Label(descr);
		descriptn.getStyleClass().add("labelSSS1");
		descriptn.setWrapText(true);
		HBox summy = new HBox();
		summy.setMaxWidth(1300);
		summy.setMinHeight(200);
		summy.getChildren().addAll(descriptn);
		summy.setSpacing(10);
		summy.getStyleClass().add("summy");
		descriptn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);
			}
		});
		descriptn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});

		// Textfield used in pop up is given event listener
		descriptn.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			temp.setMinSize(1300, 100);
			Label description = new Label(
					"Tell us about yourself \nWhat do you like? \nWhat are you interested in? \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 60)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(descriptn.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						descriptn.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE Description None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						descriptn.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE Description " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});
		Label career = new Label("Career information");
		career.getStyleClass().add("summary");

		/**
		 * Een label en een textfield waar je in kan voeren welke studie je op
		 * dit moment doet
		 */
		Label curs = new Label("Current Study: ");
		curs.getStyleClass().add("curslabel");
		String study = Client.toServer("INCOMING-GET CurrentStudy");
		final Label currentstud = new Label(study);
		currentstud.getStyleClass().add("labelSSS");
		HBox curstud = new HBox();
		curstud.getChildren().addAll(curs, currentstud);
		curstud.getStyleClass().add("curstudybox");
		curstud.setSpacing(46);
		currentstud.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);
				currentstud.setScaleX(1.3);
				currentstud.setScaleY(1.3);
			}
		});
		currentstud.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				currentstud.setScaleX(1);
				currentstud.setScaleY(1);
				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});
		
		
		// Textfield used in pop up is given event listener
		currentstud.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label(
					"Your study program in English." + "\nEx: Computer Science \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(currentstud.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						currentstud.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE CurrentStudy None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						currentstud.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE CurrentStudy " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});

		/**
		 * Een label en een textfield waar je in kan voeren wat je
		 * waarschijnlijke studieperiode is
		 *
		 *
		 *
		 */
		Label studyper = new Label("Study period: ");
		studyper.getStyleClass().add("curslabel");
		String studyp = Client.toServer("INCOMING-GET StudyPeriod");
		final Label stper = new Label(studyp);
		stper.getStyleClass().add("labelSSS");
		HBox stpbox = new HBox();
		stpbox.getChildren().addAll(studyper, stper);
		stpbox.getStyleClass().add("genderbox");
		stpbox.setSpacing(55);
		stper.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);
				stper.setScaleX(1.3);
				stper.setScaleY(1.3);
			}
		});
		stper.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				stper.setScaleX(1);
				stper.setScaleY(1);
				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});

		// Textfield used in pop up is given event listener
		stper.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label("Your study period with start year and end year."
					+ "\nEx: 2013-2016 \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(stper.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						stper.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE StudyPeriod None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						stper.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE StudyPeriod " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});

		/**
		 * Een label en een textfield waar je in kan voeren aan welke
		 * universiteit je op dit moment studeert
		 */
		Label university = new Label("Current University: ");
		university.getStyleClass().add("curslabel");
		String un = Client.toServer("INCOMING-GET CurrentUniversity");
		final Label uni = new Label(un);
		uni.getStyleClass().add("labelSSS");
		HBox unibox = new HBox();
		unibox.getChildren().addAll(university, uni);
		unibox.getStyleClass().add("genderbox");
		unibox.setSpacing(10);
		
		uni.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);
				uni.setScaleX(1.3);
				uni.setScaleY(1.3);
			}
		});
		uni.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				uni.setScaleX(1);
				uni.setScaleY(1);
				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});
		

		// Textfield used in pop up is given event listener
		uni.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label("Your full university name in English \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 70).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 70).width(0).height(0).build();
			temp.setText(uni.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						uni.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE CurrentUniversity None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						uni.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE CurrentUniversity " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});

		Label basicinfo = new Label("Basic information");
		basicinfo.getStyleClass().add("summary");

		/**
		 * Een label en een textfield waar je in kan voeren je leeftijd
		 */
		Label agelable = new Label("Age :");
		agelable.getStyleClass().add("curslabel");
		String ag = Client.toServer("INCOMING-GET Age");
		final Label age = new Label(ag);
		age.getStyleClass().add("labelSSS");
		HBox agebox = new HBox();
		agebox.getChildren().addAll(agelable, age);
		agebox.getStyleClass().add("genderbox");
		agebox.setSpacing(15);

		age.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);
				age.setScaleX(1.3);
				age.setScaleY(1.3);
			}
		});
		age.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				age.setScaleX(1);
				age.setScaleY(1);
				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});

		// Textfield used in pop up is given event listener
		age.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label("Please fill in you age in numbers \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(age.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						age.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE Age None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						age.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE Age " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});

		/**
		 * Een label en een textfield waar je in kan voeren of je een man of een
		 * vrouw bent
		 */
		Label gender = new Label("Gender: ");
		gender.getStyleClass().add("curslabel");
		// Info get from server is placed in geslacht label:
		String userGender = Client.toServer("INCOMING-GET Gender");
		final Label geslacht = new Label(userGender);
		geslacht.getStyleClass().add("labelSSS");
		HBox genbox = new HBox();
		genbox.getChildren().addAll(gender, geslacht);
		genbox.getStyleClass().add("genderbox");
		genbox.setSpacing(15);

		geslacht.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);
				geslacht.setScaleX(1.3);
				geslacht.setScaleY(1.3);
			}
		});
		geslacht.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				geslacht.setScaleX(1);
				geslacht.setScaleY(1);
				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});

		// Textfield used in pop up is given event listener
		geslacht.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label("Please fill in only: Male/Female \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(geslacht.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						geslacht.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE Gender None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						geslacht.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE Gender " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});

		/**
		 * Een label en een textfield waar je in kan voeren waar je vandaan komt
		 * (land)
		 */
		Label country = new Label("Country: ");
		country.getStyleClass().add("curslabel");
		// 1)Change inital text field to label:
		final Label countryof = new Label();
		String userCountry = Client.toServer("INCOMING-GET CountryOfResidence");
		// 2)Give it value of user.
		countryof.setText(userCountry);
		// 3)Labels of common size can use same style, like City, Country use
		// labelSSS
		countryof.getStyleClass().add("labelSSS");
		HBox countrybox = new HBox();
		countrybox.getChildren().addAll(country, countryof);
		countrybox.getStyleClass().add("countrybox");
		countrybox.setSpacing(11);

		countryof.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);
				countryof.setScaleX(1.3);
				countryof.setScaleY(1.3);
			}
		});
		countryof.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				countryof.setScaleX(1);
				countryof.setScaleY(1);
				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});
		countryof.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label("Your country name in English  \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(countryof.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						countryof.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE CountryOfResidence None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						countryof.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE CountryOfResidence " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});

		/**
		 * Een label en een textfield waar je in kan voeren waar je vandaan komt
		 * (stad)
		 */
		Label city = new Label("City: ");
		city.getStyleClass().add("curslabel");
		final Label cit = new Label();
		String userCity = Client.toServer("INCOMING-GET CityOfResidence");
		cit.setText(userCity);
		cit.getStyleClass().add("labelSSS");
		HBox citybox = new HBox();
		citybox.getChildren().addAll(city, cit);
		citybox.getStyleClass().add("citybox");
		citybox.setSpacing(47);

		cit.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);
				cit.setScaleX(1.3);
				cit.setScaleY(1.3);
			}
		});
		cit.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				cit.setScaleX(1);
				cit.setScaleY(1);
				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});
		cit.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label("Your city name in English  \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(cit.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						cit.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE CityOfResidence None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						cit.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE CityOfResidence " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});

		Label contact = new Label("Contact information");
		contact.getStyleClass().add("summary");

		/**
		 * Een label en een textfield waar je in kan voeren wat je email adres
		 * is
		 */
		Label eml = new Label("Email: ");
		eml.getStyleClass().add("curslabel");
		String el = Client.toServer("INCOMING-GET Email");
		final Label email = new Label(el);
		email.getStyleClass().add("labelSSS");
		HBox mailbox = new HBox();
		mailbox.getChildren().addAll(eml, email);
		mailbox.getStyleClass().add("mailbox");
		mailbox.setSpacing(5);
		email.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);
				email.setScaleX(1.1);
				email.setScaleY(1.1);
			}
		});
		email.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				email.setScaleX(1);
				email.setScaleY(1);
				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});

		// Textfield used in pop up is given event listener
		email.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label("Your e-mail address \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(email.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						email.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE Email None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						email.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE Email " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});

		/**
		 * Een label en een textfield waar je in kan voeren wat je telefoon
		 * nummer is
		 */
		Label phone = new Label("Tel: ");
		phone.getStyleClass().add("curslabel");
		String tel = Client.toServer("INCOMING-GET Phone");
		final Label phonenumber = new Label(tel);
		phonenumber.getStyleClass().add("labelSSS");
		HBox phonebox = new HBox();
		phonebox.getChildren().addAll(phone, phonenumber);
		phonebox.getStyleClass().add("phonebox");
		phonebox.setSpacing(10);
		phonenumber.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);
				phonenumber.setScaleX(1.3);
				phonenumber.setScaleY(1.3);
			}
		});
		phonenumber.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				phonenumber.setScaleX(1);
				phonenumber.setScaleY(1);
				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});

		// Textfield used in pop up is given event listener
		phonenumber.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label("Your telephone/Whatsapp number." + "\nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(phonenumber.getText());
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						phonenumber.setText("None");
						temp.setText("None");
						try {
							Client.toServer("INCOMING-CHANGE Phone None");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						phonenumber.setText(newInfo);
						temp.setText(newInfo);
						try {
							Client.toServer("INCOMING-CHANGE Phone " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);
		});

		VBox carinfo = new VBox();
		carinfo.getChildren().addAll(curstud, stpbox, unibox);
		carinfo.getStyleClass().add("carinfoVBox");

		VBox basinfo = new VBox();
		basinfo.getChildren().addAll(agebox, genbox, citybox);
		basinfo.getStyleClass().add("basinfoVBox");

		VBox contactinfo = new VBox();
		contactinfo.getChildren().addAll(mailbox, phonebox, countrybox);
		contactinfo.getStyleClass().add("contactinfoVBox");

		/**
		 * De foto die je eventueel kan vervangen door een foto van jezelf
		 */

		String userURL = Client.toServer("INCOMING-GET Piclink");
		ImageView picview = ImageViewBuilder.create().image(new Image("http://i68.tinypic.com/2vjt0xz.jpg"))
				.build();
				try
				{
					picview = ImageViewBuilder.create().image(new Image(userURL))
							.build();
				}
				catch (Exception e)
				{
					picview = ImageViewBuilder.create().image(new Image("http://i68.tinypic.com/2vjt0xz.jpg"))
							.build();
				}
		
		picview.getStyleClass().add("picuser");
		picview.setFitHeight(200);
		picview.setFitWidth(348);
		picview.setLayoutX(300);
		picview.setLayoutY(100);
		picview.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);

			}
		});
		picview.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {

				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});

		// Textfield used in pop up is given event listener
		picview.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label(
					"Please add an URL link for you profile picture. \n Upload an picture on the internet and get the URL \nWe recommend www.tinypic.com \n Your picture will be changed the next time you logs in \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			try {
				temp.setText(Client.toServer("INCOMING-GET Piclink"));
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						try {
							Client.toServer("INCOMING-CHANGE Piclink http://i67.tinypic.com/2ug08eu.jpg");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							Client.toServer("INCOMING-CHANGE Piclink " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
					/*
					 * try { primaryStage.hide();
					 * profileTab(primaryStage,scenetest,rootProfileTabe,
					 * sceneProfileTabee); } catch (Exception e1) { //
					 * e1.printStackTrace(); }
					 */
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);

		});

		/**
		 * Foto van TU Delft, ook dit kan je veranderen naar een foto naar keus.
		 */

		String userPlacePic = Client.toServer("INCOMING-GET Placepic");
		ImageView delftview = ImageViewBuilder.create().image(new Image("http://i68.tinypic.com/2vjt0xz.jpg"))
		.build();
		try
		{
			delftview = ImageViewBuilder.create().image(new Image(userPlacePic))
					.build();
		}
		catch (Exception e)
		{
			delftview = ImageViewBuilder.create().image(new Image("http://i68.tinypic.com/2vjt0xz.jpg"))
					.build();
		}
		
		
		delftview.getStyleClass().add("picuser");
		delftview.setFitHeight(200);
		delftview.setFitWidth(348);
		delftview.setLayoutX(1200);
		delftview.setLayoutY(100);
		delftview.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				sceneProfileTabee.setCursor(Cursor.HAND);

			}
		});
		delftview.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {

				sceneProfileTabee.setCursor(Cursor.DEFAULT);
			}
		});

		// Textfield used in pop up is given event listener
		delftview.setOnMouseClicked(e -> {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label(
					"Please add an URL link for you university or secondary picture. \n Upload an picture on the internet and get the URL \nWe recommend www.tinypic.com  \n Your picture will be changed the next time you logs in \nPress \"Enter\" when finished. ");
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			try {
				temp.setText(Client.toServer("INCOMING-GET Placepic"));
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// newinfo is variable with edited info
					String newInfo = temp.getText();
					if (newInfo.trim().isEmpty()) {
						try {
							Client.toServer("INCOMING-CHANGE Piclink http://i67.tinypic.com/15fje5g.jpg");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							Client.toServer("INCOMING-CHANGE Placepic " + newInfo);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					pop.hide();
					pop2.hide();
					/*
					 * try { primaryStage.hide();
					 * profileTab(primaryStage,scenetest,rootProfileTabe,
					 * sceneProfileTabee); } catch (Exception e1) { //
					 * e1.printStackTrace(); }
					 */
				}
			});
			pop.show(primaryStage);
			pop2.show(primaryStage);

		});

		/**
		 * Een foto met daarin de logo.
		 */
		Image logo = new Image("log.jpg");
		ImageView imgview = new ImageView(logo);
		Image motto = new Image("motto.jpg");
		ImageView picaview = new ImageView(motto);

		HBox boven = new HBox();
		boven.setMinSize(1550, 85);
		boven.getChildren().addAll(picaview, imgview);
		boven.getStyleClass().add("bovenstuk");

		/**
		 * De zwarte lijn die career informatie scheidt van Basic Informatie.
		 */
		Line line = new Line();
		line.setStartX(750);
		line.setStartY(500);
		line.setEndX(750);
		line.setEndY(727);

		/**
		 * De zwarte lijn die basic informatie scheidt van Contact Informatie
		 */
		Line line2 = new Line();
		line2.setStartX(1150);
		line2.setStartY(500);
		line2.setEndX(1150);
		line2.setEndY(727);

		Pane rootProfileTabe = new Pane();
		rootProfileTabe.setId("pane");

		// if(countProfile==0){
		rootProfileTabe.getChildren().addAll(boven, delftview, picaview, imgview, helpout, overzicht, bovennaam, desci,
				summary, summy, career, carinfo, basicinfo, basinfo, contact, contactinfo, line, line2, picview);
		// }
		countProfile++;
		boven.setLayoutX(50);

		picaview.setLayoutX(730);
		picaview.setLayoutY(30);

		imgview.setLayoutX(50);
		imgview.setLayoutY(25);

		helpout.setLayoutX(1410);
		helpout.setLayoutY(25);

		overzicht.setLayoutX(50);
		overzicht.setLayoutY(100);

		bovennaam.setLayoutX(780);
		bovennaam.setLayoutY(125);

		desci.setLayoutX(720);
		desci.setLayoutY(200);

		summary.setLayoutX(300);
		summary.setLayoutY(300);

		summy.setLayoutX(300);
		summy.setLayoutY(350);

		career.setLayoutX(300);
		career.setLayoutY(500);

		carinfo.setLayoutX(300);
		carinfo.setLayoutY(550);

		basicinfo.setLayoutX(760);
		basicinfo.setLayoutY(500);

		basinfo.setLayoutX(760);
		basinfo.setLayoutY(550);

		contact.setLayoutX(1200);
		contact.setLayoutY(500);

		contactinfo.setLayoutX(1200);
		contactinfo.setLayoutY(550);

		final Scene sceneProfileTabe = new Scene(rootProfileTabe, 1600, 900);

		primaryStage.setScene(sceneProfileTabe);

		sceneProfileTabe.getStylesheets().add("ontwerp.css");

		primaryStage.show();

	}
	
	/**
	 * COURSE TAB, NOT MUCH CODE GROUPS WHICH CAN BE PUT INTO METHODS.
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void coursesTab(final Stage primaryStage, final Scene sceneMatchTab, Pane rootMatchTab, Scene sceneProfileTabe,final Scene scenetest, Pane rootProfileTabe, Pane rootCourseTab, Scene CourseScene){
	   
			TableView<Courses> table = new TableView<Courses>();

		
		  /* String[] crse = courselist.keySet().toString().substring(1,courselist.keySet().toString().length()-1).split(",");
		   for (int i =0 ; i<crse.length; i++)
		   {
			   crse[i]= crse[i].trim() +"¿"+courselist.get(crse[i].trim());
			   System.out.println(crse[i]);
		   }*/
			ObservableList<Courses> data = FXCollections.observableArrayList();
			Set keys = null;
			Iterator loop = null;
			JSONObject courseList = null;
			String incomingCourses = "";
			try {
				incomingCourses = Client.toServer("INCOMING-GET Course list");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
			if(!incomingCourses.equals("{}")){
				//Parse String to a JSONObject:
				try {
					courseList = (JSONObject)new JSONParser().parse(incomingCourses.toString());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				keys = courseList.keySet();
			    loop = keys.iterator();	
			    while(loop.hasNext()) {
			    	String key = (String)loop.next();
			        String value = (String)courseList.get(key);
		
			        data.add(new Courses(key,value,""));
			    }
			}else{
				System.out.println("No courses #course tab");
			}
		
			
			
		   

		
		
		primaryStage.setTitle("Courses");
        primaryStage.setWidth(1600);
        primaryStage.setHeight(900);
 
        final Label label = new Label("Course Overview");
        label.setFont(new Font("Arial", 20));
        table.setEditable(true);
 
       
		@SuppressWarnings("rawtypes")
		TableColumn courseCol = new TableColumn("Course");
        courseCol.setMinWidth(300);
        courseCol.setCellValueFactory(
            new PropertyValueFactory<Courses, String>("firstName"));

 
 
        TableColumn gradeCol = new TableColumn("Description");
        gradeCol.setMinWidth(800);
        gradeCol.setCellValueFactory(
            new PropertyValueFactory<Courses, String>("lastName"));
        gradeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        gradeCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Courses, String>>() {
                @Override
                public void handle(CellEditEvent<Courses, String> t) {
                	
                	Courses h = table.getSelectionModel().getSelectedItem();
                	System.out.println(h.getLastName());
             
                	String changedValue = t.getNewValue();
                	System.out.println(changedValue);
                	      
                	try {
						Client.toServer("INCOMING-COURSECHANGE¿"+h.getFirstName()+ "¿"+ changedValue);						
					} catch (IOException e) {
						e.printStackTrace();
					}
                	for(int i = 0; i < data.size() ; i++){
                		if(data.get(i).getLastName().equals(h.getLastName())){
                			data.get(i).setLastName(changedValue);
                		}
                	}      
                }
            }
        );
 
        table.setItems(data);
        //if(countCourse ==0){
        table.getColumns().addAll(courseCol, gradeCol);
        table.getStyleClass().add("table");
        //}
 
        final TextField addCourse = new TextField();
        addCourse.setPromptText("Course");
        final TextField addGrade = new TextField();
        addGrade.setPromptText("Description");
        final TextField addHelp = new TextField();
        addHelp.setPromptText("Offer or need help?");
 
        addCourse.setMaxWidth(200);
        addGrade.setMaxWidth(200);
        addHelp.setMaxWidth(200);
        
        
        Button addButton = new Button("Add");
        addButton.getStyleClass().add("toevoeg");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            
            	
            	String addCour = addCourse.getText().trim();

                String addDesc = addGrade.getText().trim();
                if(addCour.equals("") ||addDesc.equals("")){
                	System.out.println("Empty ");
                }else{
                	 try {
     					Client.toServer("INCOMING-COURSECHANGE¿"+addCour+ "¿"+ addDesc);
     				} catch (IOException e1) {
     					e1.printStackTrace();
     				}
                     
                     //addHelp.getText();
                     
                     data.add(new Courses(
                             addCourse.getText(),
                             addGrade.getText(),""));
                     
                     table.setItems(data);
                     System.out.println(table.getItems());
                     table.visibleProperty().set(true);
                         
                     addCourse.clear();
                     addGrade.clear();
                     addHelp.clear();
                }
               
            }
        });
        
        final Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("toevoeg");
        deleteButton.setOnAction(e -> {
            Courses selectedItem = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(selectedItem);
            String deleteCourse = selectedItem.getFirstName();
            
            System.out.println(deleteCourse);
            
            try {
				Client.toServer("INCOMING-COURSEREMOVE¿"+deleteCourse);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });
        
        hb.getChildren().clear();
        hb.getChildren().addAll(addCourse, addGrade, addButton,deleteButton);	
 
        hb.setSpacing(3);
        hb.setLayoutX(850);
        hb.setLayoutY(700);
        table.setLayoutX(350);
        table.setLayoutY(250);
        
        Label courseoverview = new Label("Course Overview");
		courseoverview.getStyleClass().add("courseview");
		courseoverview.setLayoutX(750);
		courseoverview.setLayoutY(150);
        
		/**
		 * een button help. De setaction moet gelinkt worden aan een nieuwe
		 * page, maar welke? (Dario nodig)
		 */
		Button help = new Button("Help");
		help.setMinWidth(90);
		help.getStyleClass().add("help");
		help.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				helpTab(primaryStage, sceneMatchTab,  rootMatchTab,  sceneProfileTabe,  scenetest,  rootProfileTabe,  rootCourseTab,  CourseScene);
			}
		});
		
		/**
		 * Een button voor logout. De set action moet nog gemaakt worden om
		 * terug te gaan naar de hoofdpagina? (Dario nodig)
		 */
		Button logout = new Button("Log out");
		logout.setMinWidth(80);
		logout.getStyleClass().add("logout");
		logout.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				
				try {
					start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		/**
		 * Een box voor de help en logout buttons (dario nodig)
		 */
		HBox helpout = new HBox();
		helpout.getChildren().addAll(help, logout);
		helpout.getStyleClass().add("helpoutbox");
		
		/**
		 * Een foto die hooft bij profile button (Dario nodig)
		 */
		Image prfoto = new Image(getClass().getResourceAsStream("huisteken.jpg"));
		ImageView profileimage = new ImageView(prfoto);
		Button profile = new Button("Profile", profileimage);
		profile.getStyleClass().add("profile");
		profile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
			//	profileTab(primaryStage, scenetest, rootProfileTabe, sceneProfileTabe,sceneMatchTab,rootMatchTab);
			try {
				profileTab(primaryStage,scenetest,rootProfileTabe,sceneProfileTabe,sceneMatchTab,rootMatchTab,rootCourseTab,CourseScene);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				//1final Stage primaryStage, final Scene scenetest, Pane rootProfileTabe, Scene sceneProfileTabe, final Scene sceneMatchTab, Pane rootMatchTab) throws IOException{
			
			}
		});
		
		/**
		 * Een foto die hoort bij course button (Dario nodig)
		 */
		Image crfoto = new Image(getClass().getResourceAsStream("courseteken.jpg"));
		ImageView coursefoto = new ImageView(crfoto);
		Button courses = new Button("Courses", coursefoto);
		courses.getStyleClass().add("courses");
		
		/**
		 * Een foto die hoort bij match button (Dario nodig)
		 */
		Image mfoto = new Image(getClass().getResourceAsStream("matchteken.jpg"));
		ImageView matchfoto = new ImageView(mfoto);
		Button match = new Button("Match", matchfoto);
		match.getStyleClass().add("match");
		match.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				//final Stage primaryStage, final Scene sceneMatchTab, Pane rootMatchTab, Scene sceneProfileTabe
				matchTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe,scenetest, rootProfileTabe,rootCourseTab,CourseScene);
			}
		});

		/**
		 * Een foto die hoort bij settings button (dario nodig)
		 */
		Image setfoto = new Image(getClass().getResourceAsStream("settingsteken.jpg"));
		ImageView settingsfoto = new ImageView(setfoto);
		Button settings = new Button("Settings", settingsfoto);
		settings.getStyleClass().add("settings");
		settings.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				//final Stage primaryStage, final Scene sceneMatchTab, Pane rootMatchTab, Scene sceneProfileTabe
				settingsTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe,scenetest, rootProfileTabe,rootCourseTab,CourseScene);
			}
		});
		
		/**
		 * Een VBox voor alle buttons links op de pagina (Dario nodig)
		 */
		VBox overzicht = new VBox();
		overzicht.getChildren().addAll(profile, courses, match, settings);
		overzicht.getStyleClass().add("overzicht");
		overzicht.setMinHeight(630);
	
		/**
		 * Een foto met daarin de logo.
		 */
		Image logo = new Image("log.jpg");
		ImageView imgview = new ImageView(logo);
		Image motto = new Image("motto.jpg");
		ImageView picaview = new ImageView(motto);
		
		HBox boven = new HBox();
		boven.setMinSize(1550, 85);
		boven.getChildren().addAll(picaview, imgview);
		boven.getStyleClass().add("bovenstuk");
		
       
		Pane rootCourse = new Pane();
		rootCourse.getStyleClass().add("pane");
        //if(countCourse == 0){
		rootCourse.getChildren().addAll(label, courseoverview,  table, hb, boven,  picaview, imgview, helpout, overzicht);
        //}
        countCourse++;
      
		boven.setLayoutX(50);

		picaview.setLayoutX(730);
		picaview.setLayoutY(30);

		imgview.setLayoutX(50);
		imgview.setLayoutY(25);

		helpout.setLayoutX(1410);
		helpout.setLayoutY(25);

		overzicht.setLayoutX(50);
		overzicht.setLayoutY(100);

		
		Scene CourseScenee = new Scene(rootCourse,1600,900);
        primaryStage.setScene(CourseScenee);
        CourseScenee.getStylesheets().add("design.css");
        primaryStage.show();
	}

	/**
	 * THIS IS A CLASS WHICH IS CREATED TO SUPPORT PARTS FROM COURSE TAB, TESTABLE
	 */
	public static class Courses {


		private final SimpleStringProperty courseName;
		private final SimpleStringProperty gradeName;
		private final SimpleStringProperty helpName;

		private Courses(String fName, String lName, String email) {
			this.courseName = new SimpleStringProperty(fName);
			this.gradeName = new SimpleStringProperty(lName);
			this.helpName = new SimpleStringProperty(email);
		}

		public String getFirstName() {
			return courseName.get();
		}

		public void setFirstName(String fName) {
			courseName.set(fName);
		}

		public String getLastName() {
			return gradeName.get();
		}

		public void setLastName(String fName) {
			gradeName.set(fName);
		}

		public String getEmail() {
			return helpName.get();
		}

		public void setEmail(String fName) {
			helpName.set(fName);
		}
	}
	
	/**
	 * MATCH TAB, THIS IS WHERE THE ESSENTIAL TRANSLATION CODE (JSON DATA FROM SERVER TO DISPLAY VARIABLES) WHICH ARE TESTABLE ARE. LOADS OF PARSING CODES, 
	 * THESE SHOULD BE PUT INTO THEIR OWN PARSING METHODS WHICH ARE CALLED FROM THE MATCH TAB. 
	 */
	public void matchTab(final Stage primaryStage, final Scene sceneMatchTab, Pane rootMatchTab, Scene sceneProfileTabe,
			final Scene scenetest, Pane rootProfileTabe, Pane rootCourseTab, Scene CourseScene) {

		
		filterTable.getItems().clear();
		SearchResultList.getItems().clear();
		filterTable.setMinHeight(450);
		SearchResultList.setMinHeight(450);
		/*
		 * ImageView picview = null; try { picview = ImageViewBuilder.create()
		 * .image(new Image(Client.toServer("INCOMING-GET Piclink"))) .build();
		 * } catch (IOException e1) { e1.printStackTrace(); }
		 * picview.setFitHeight(200); picview.setFitWidth(250);
		 * /*picview.setLayoutX(300); picview.setLayoutY(100);
		 */
		/*
		 * SearchResultList.setCellFactory(new Callback<ListView<String>,
		 * ListCell<String>>() {
		 * 
		 * @Override public ListCell<String> call(ListView<String> list) {
		 * return new ColorRectCell(); } } );
		 */

		TextField enterSearch = new TextField();
		enterSearch.setLayoutX(1125);
		enterSearch.setLayoutY(180);
		enterSearch.getStyleClass().add("label1");

		SearchResultList.setMinWidth(540);
		matchButton.setVisible(false);
		matchButton.getStyleClass().add("buttonspecific");

		// Hbox voor urgent checkbox
		HBox UrgentBox = new HBox();
		UrgentBox.getChildren().addAll(UrgentCheck);
		UrgentBox.setSpacing(10);
		UrgentBox.getStyleClass().add("checkboxBox");
		UrgentBox.setVisible(false);
		UrgentCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				urgency = newValue;
			}
		});

		noResults.getStyleClass().add("noResults");

		ArrayList<String> SearchResultInput = new ArrayList<String>();

		// Initialiseren van resultaten als niet zichtbaar.

		noResults.setVisible(false);

		SearchResultList.getStyleClass().add("SearchResults");

		boolean userAvailability = false;
		try {
			String tempA = Client.toServer("INCOMING-GET Available");
			if (tempA.equals("true")) {
				userAvailability = true;
			} else {
				userAvailability = false;
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// Maken van ComboBox en geven van opties en voorzetten.
		final ComboBox<String> SearchOptions = new ComboBox<String>();
		SearchOptions.getItems().addAll("Interested Courses", "Institutes", "Email", "City");
		// SearchOptions.getStyleClass().add("combobox");

		SearchOptions.setValue("Interested Courses");
	

		/**
		 * een button help. De setaction moet gelinkt worden aan een nieuwe
		 * page, maar welke? (Dario nodig)
		 */
		Button help = new Button("Help");
		help.setMinWidth(90);
		help.getStyleClass().add("help");
		help.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				helpTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
						rootCourseTab, CourseScene);
			}
		});

		/**
		 * Een button voor logout. De set action moet nog gemaakt worden om
		 * terug te gaan naar de hoofdpagina? (Dario nodig)
		 */
		Button logout = new Button("Log out");
		logout.setMinWidth(90);
		logout.getStyleClass().add("logout");
		logout.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				try {
					start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// Logout

		/**
		 * Een box voor de help en logout buttons (dario nodig)
		 */
		HBox helpout = new HBox();
		helpout.getChildren().addAll(help, logout);
		helpout.getStyleClass().add("helpoutbox");
		helpout.setLayoutX(1410);
		helpout.setLayoutY(25);

		/**
		 * Een foto die hooft bij profile button (Dario nodig)
		 */
		Image prfoto = new Image(getClass().getResourceAsStream("huisteken.jpg"));
		ImageView profileimage = new ImageView(prfoto);
		Button profile = new Button("Profile", profileimage);
		profile.getStyleClass().add("profile");
		profile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				// profileTab(primaryStage, scenetest, rootProfileTabe,
				// sceneProfileTabe,sceneMatchTab,rootMatchTab);
				try {
					profileTab(primaryStage, scenetest, rootProfileTabe, sceneProfileTabe, sceneMatchTab, rootMatchTab,
							rootCourseTab, CourseScene);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 1final Stage primaryStage, final Scene scenetest, Pane
				// rootProfileTabe, Scene sceneProfileTabe, final Scene
				// sceneMatchTab, Pane rootMatchTab) throws IOException{

			}
		});

		/**
		 * Een foto die hoort bij course button (Dario nodig)
		 */
		Image crfoto = new Image(getClass().getResourceAsStream("courseteken.jpg"));
		ImageView coursefoto = new ImageView(crfoto);
		Button courses = new Button("Courses", coursefoto);
		courses.getStyleClass().add("courses");
		courses.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				coursesTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
						rootCourseTab, CourseScene);
			}
		});

		/**
		 * Een foto die hoort bij match button (Dario nodig)
		 */
		Image mfoto = new Image(getClass().getResourceAsStream("matchteken.jpg"));
		ImageView matchfoto = new ImageView(mfoto);
		Button match = new Button("Match", matchfoto);
		match.getStyleClass().add("match");

		/**
		 * Een foto die hoort bij settings button (dario nodig)
		 */
		Image setfoto = new Image(getClass().getResourceAsStream("settingsteken.jpg"));
		ImageView settingsfoto = new ImageView(setfoto);
		Button settings = new Button("Settings", settingsfoto);
		settings.getStyleClass().add("settings");
		settings.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				// final Stage primaryStage, final Scene sceneMatchTab, Pane
				// rootMatchTab, Scene sceneProfileTabe
				settingsTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
						rootCourseTab, CourseScene);
			}
		});

		/**
		 * Een VBox voor alle buttons links op de pagina (Dario nodig)
		 */
		VBox overzicht = new VBox();
		overzicht.getChildren().addAll(profile, courses, match, settings);
		overzicht.getStyleClass().add("overzicht");
		overzicht.setLayoutX(50);
		overzicht.setLayoutY(100);
		overzicht.setMinHeight(630);

		// Invoegen van bovenstuk
		Image logos = new Image("log.jpg");
		ImageView imgviews = new ImageView(logos);
		Image mottos = new Image("motto.jpg");
		ImageView picaviews = new ImageView(mottos);

		HBox bovens = new HBox();
		bovens.setMinSize(1550, 85);
		// bovens.getChildren().addAll(picaviews, imgviews);
		bovens.getStyleClass().add("bovenstuk");

		bovens.setLayoutX(50);
		picaviews.setLayoutX(730);
		picaviews.setLayoutY(30);

		imgviews.setLayoutX(50);
		imgviews.setLayoutY(25);

		Button viewProfile2 = new Button("View Selected Profile");
		viewProfile2.getStyleClass().add("viewProfile");
		viewProfile2.setLayoutX(1020);
		viewProfile2.setLayoutY(700);
		viewProfile2.setVisible(false);
		viewProfile2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				String curEmail = "";
				try {
					curEmail = Client.toServer("INCOMING-GET Email");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// Get name of person to view profie of.
				String emailSelect = filterTable.getSelectionModel().getSelectedItem();

				if (emailSelect.equals("No results..") || filterTable.getSelectionModel().getSelectedItem().isEmpty()) {
					System.out.println("No results");
				} else {
					Scanner sc = new Scanner(emailSelect);
					sc.useDelimiter("\n");
					emailSelect = sc.next().substring(7);
					String n = null;
					String sur = null;
					String a = null;
					String c = null;
					String co = null;
					String un = null;
					String st = null;
					String num = null;
					String piclink = null;
					
					Set keys = null;
					Iterator loop = null;
					JSONObject courseList = null;
					String incomingCourses = "";
					try {
						n = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Firstname");
						sur = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Lastname");
						a = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Age");
						c = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿CityOfResidence");
						co = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿CountryOfResidence");
						un = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿CurrentUniversity");
						st = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿CurrentStudy");
						incomingCourses = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Course list");

						
						if (incomingCourses.equals("{}")) {
							incomingCourses = "No results..";
						} else {
						
							if(!incomingCourses.equals("{}")){
								//Parse String to a JSONObject:
								try {
									courseList = (JSONObject)new JSONParser().parse(incomingCourses.toString());
								} catch (ParseException e1) {
									e1.printStackTrace();
								}
								
								keys = courseList.keySet();
							    loop = keys.iterator();	
							}else{
								System.out.println("No courses #match,viewprofile2 tab");
							}
						}

						num = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Phone");
						String email = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Email");
						piclink = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Piclink");
						Label name = new Label();
						name.setText("Full name:          " + n + " " + sur);
						name.getStyleClass().add("e");
						Label age = new Label();
						age.setText("Age:                " + a);
						age.getStyleClass().add("e");
						Label city = new Label();
						city.setText("City:               " + c);
						city.getStyleClass().add("e");
						Label country = new Label();
						country.setText("Country:            " + co);
						country.getStyleClass().add("e");
						Label Uni = new Label();
						Uni.setText("University: " + un);
						Uni.getStyleClass().add("e");
						Label study = new Label();
						study.setText("Study:              " + st);
						study.getStyleClass().add("e");
						Label description = new Label(Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Description"));
						description.getStyleClass().add("e");
						description.setWrapText(true);
						ListView<String> tempC = new ListView<String>();
						tempC.setMaxHeight(130);
						if (incomingCourses.equals("No results..")) {
							tempC.getItems().add(incomingCourses);
						} else {	
							
						    while(loop.hasNext()) {
						    	String key = (String)loop.next();
						        String value = (String)courseList.get(key);
						        
								tempC.getItems().add(key + ":  " + value);
						    }			
						}

						Label courses = new Label();
						courses.setText("Courses:            ");
						courses.getStyleClass().add("e");
						
					
						
						Label number = new Label();
						number.setText("Number: " + num);
						number.getStyleClass().add("e");
						Label emailadress = new Label();
						emailadress.setText("E-mail: " + email);
						emailadress.getStyleClass().add("e");
						System.out.println(piclink);
						Image pic = new Image(piclink);
						ImageView picc = new ImageView(pic);
						picc.setFitHeight(275);
						picc.setFitWidth(350);

						Button closePop = new Button("X");
						closePop.getStyleClass().add("butonclose");

						VBox view0 = new VBox();
						view0.getChildren().addAll(closePop, name, age, city, country, Uni, study, number, emailadress);
						view0.setMinWidth(350);
						view0.setMinHeight(275);
						view0.setSpacing(10);
						view0.getStyleClass().add("popView");
						view0.getStyleClass().add("LabelPop");

						HBox view1 = new HBox();
						view1.getChildren().addAll(view0, picc);
						view1.setMinWidth(650);
						view1.setMinHeight(275);
						view1.setSpacing(15);
						view1.getStyleClass().add("popView");

						VBox view = new VBox();
						view.getChildren().addAll(view1,description, courses, tempC);
						view.setMaxWidth(825);
						view.setMinHeight(275);
						view.setSpacing(15);
						view.getStyleClass().add("popView");
						name.getStyleClass().add("LabelPop");
						age.getStyleClass().add("LabelPop");
						city.getStyleClass().add("LabelPop");
						country.getStyleClass().add("LabelPop");
						Uni.getStyleClass().add("LabelPop");
						study.getStyleClass().add("LabelPop");
						number.getStyleClass().add("LabelPop");
						emailadress.getStyleClass().add("LabelPop");
						courses.getStyleClass().add("LabelPop");

						Popup pop = PopupBuilder.create().content(view).y(250).x(850).width(0).height(0).build();

						closePop.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent args) {
								pop.hide();
							}
						});

						pop.show(primaryStage);

					} catch (IOException e) {
						e.printStackTrace();
					}
					// Return control
					try {
						Client.toServer("INCOMING-FROMOTHERSGET¿" + curEmail + "¿Piclink");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Button viewProfile = new Button("View Selected Profile");
		viewProfile.getStyleClass().add("viewProfile");
		viewProfile.setLayoutX(350);
		viewProfile.setLayoutY(700);
		viewProfile.setVisible(false);
		viewProfile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				String curEmail = "";
				try {
					curEmail = Client.toServer("INCOMING-GET Email");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// Get name of person to view profie of.
				String emailSelect = SearchResultList.getSelectionModel().getSelectedItem();
				System.out.println("No results....." + emailSelect);
				if (emailSelect.equals("No results..")
						|| SearchResultList.getSelectionModel().getSelectedItem().isEmpty()) {
					System.out.println("No results....." + emailSelect);
				} else {
					Scanner sc = new Scanner(emailSelect);
					sc.useDelimiter("\n");
					emailSelect = sc.next().substring(7);
					String n = null;
					String sur = null;
					String a = null;
					String c = null;
					String co = null;
					String un = null;
					String st = null;
					String num = null;
					String piclink = null;		
					
					Set keys = null;
					Iterator loop = null;
					JSONObject courseList = null;
					String incomingCourses = "";
					try {
						n = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Firstname");
						sur = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Lastname");
						a = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Age");
						c = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿CityOfResidence");
						co = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿CountryOfResidence");
						un = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿CurrentUniversity");
						st = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿CurrentStudy");
						incomingCourses = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Course list");

						if (incomingCourses.equals("{}")) {
							incomingCourses = "No results..";
						} else {
							if(!incomingCourses.equals("{}")){
								//Parse String to a JSONObject:
								try {
									courseList = (JSONObject)new JSONParser().parse(incomingCourses.toString());
								} catch (ParseException e1) {
									e1.printStackTrace();
								}
								
								keys = courseList.keySet();
							    loop = keys.iterator();	
							}else{
								System.out.println("No courses #match,viewprofile2 tab");
							}
							
						}

						num = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Phone");
						String email = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Email");
						piclink = Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Piclink");
						Label name = new Label();
						name.setText("Full name:          " + n + " " + sur);
						name.getStyleClass().add("e");
						Label age = new Label();
						age.setText("Age:                " + a);
						age.getStyleClass().add("e");
						Label city = new Label();
						city.setText("City:               " + c);
						city.getStyleClass().add("e");
						Label country = new Label();
						country.setText("Country:            " + co);
						country.getStyleClass().add("e");
						Label Uni = new Label();
						Uni.setText("University:         " + un);
						Uni.getStyleClass().add("e");
						Label study = new Label();
						study.setText("Study:              " + st);
						study.getStyleClass().add("e");
						Label description = new Label(Client.toServer("INCOMING-FROMOTHERSGET¿" + emailSelect + "¿Description"));
						description.getStyleClass().add("e");
						description.setWrapText(true);
						ListView<String> tempC = new ListView<String>();
						tempC.setMaxHeight(130);
						if (incomingCourses.equals("No results..")) {
							tempC.getItems().add(incomingCourses);
						} else {
							
						    while(loop.hasNext()) {
						    	String key = (String)loop.next();
						        String value = (String)courseList.get(key);
						        
								tempC.getItems().add(key + ":  " + value);
						    }		
						}

						Label courses = new Label();
						courses.setText("Courses:            ");
						courses.getStyleClass().add("e");
						
					
						
						Label number = new Label();
						number.setText("Number: " + num);
						number.getStyleClass().add("e");
						Label emailadress = new Label();
						emailadress.setText("E-mail: " + email);
						emailadress.getStyleClass().add("e");
						System.out.println(piclink);
						Image pic = new Image(piclink);
						ImageView picc = new ImageView(pic);
						picc.setFitHeight(275);
						picc.setFitWidth(350);

						Button closePop = new Button("X");
						closePop.getStyleClass().add("butonclose");

						VBox view0 = new VBox();
						view0.getChildren().addAll(closePop, name, age, city, country, Uni, study, number, emailadress);
						view0.setMinWidth(350);
						view0.setMinHeight(275);
						view0.setSpacing(10);
						view0.getStyleClass().add("popView");
						view0.getStyleClass().add("LabelPop");

						HBox view1 = new HBox();
						view1.getChildren().addAll(view0, picc);
						view1.setMinWidth(650);
						view1.setMinHeight(275);
						view1.setSpacing(15);
						view1.getStyleClass().add("popView");

						VBox view = new VBox();
						view.getChildren().addAll(view1,description, courses, tempC);
						view.setMaxWidth(825);
						view.setMinHeight(275);
						view.setSpacing(15);
						view.getStyleClass().add("popView");
						name.getStyleClass().add("LabelPop");
						age.getStyleClass().add("LabelPop");
						city.getStyleClass().add("LabelPop");
						country.getStyleClass().add("LabelPop");
						Uni.getStyleClass().add("LabelPop");
						study.getStyleClass().add("LabelPop");
						number.getStyleClass().add("LabelPop");
						emailadress.getStyleClass().add("LabelPop");
						courses.getStyleClass().add("LabelPop");

						Popup pop = PopupBuilder.create().content(view).y(250).x(850).width(0).height(0).build();

						closePop.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent args) {
								pop.hide();
							}
						});

						pop.show(primaryStage);

					} catch (IOException e) {
						e.printStackTrace();
					}
					// Return control
					try {
						Client.toServer("INCOMING-FROMOTHERSGET¿" + curEmail + "¿Piclink");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// Where all the results get decided.
		Button searchCourse = new Button("Search");
		searchCourse.getStyleClass().add("courseSearch");
		searchCourse.setLayoutX(1309);
		searchCourse.setLayoutY(180);
		searchCourse.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				filterTable.getItems().clear();
				if (!(enterSearch.getText().equals(""))) {

					String currentOption = SearchOptions.getValue();
					String currentValue = enterSearch.getText();

					if (currentOption.equals("City")) {
						currentOption = "CityOfResidence";
					}
					if (currentOption.equals("Institutes")) {
						currentOption = "CurrentUniversity";
					}
					if (currentOption.equals("Interested Courses")) {
						currentOption = "Course list";
					}
					String temp = "";
					String results = "";
					String curEmail = "";
					String course = "";
					ArrayList<String> matchEmails = new ArrayList<String>();
					ArrayList<String> matchInfo = new ArrayList<String>();
					Set keys = null;
					Iterator loop = null;
					JSONObject courseList = null;

					try {
						curEmail = Client.toServer("INCOMING-GET Email");
						temp = Client.toServer("INCOMING-SEARCH¿" + currentOption + "¿" + currentValue);
					} catch (IOException e) {
						e.printStackTrace();
					}

					System.out.println(temp + "#");
					// System.out.println(SearchAvailableNow.selectedProperty().get());

					// Grab emails through result as string:
					Scanner sc;
					if (temp.equals("[]")) {
						System.out.println("No Results..");
					} else {
						sc = new Scanner(temp);

						sc.useDelimiter(",");
						String temp2 = "";

						while (sc.hasNext()) {
							temp2 = sc.next().substring(1);
							if (!temp2.equals(curEmail)) {
								if (temp2.endsWith("]")) {
									matchEmails.add(temp2.substring(0, temp2.length() - 1));
								} else {
									matchEmails.add(temp2);
								}
							}
						}

						// Get info of matched emails
						for (String s : matchEmails) {
							try {

								String userCourses = "";
								try {
									userCourses = Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Course list");
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								if (userCourses.equals("{}")) {
									results = "Email: " + Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Email")
											+ "\nName: " + Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Firstname")
											+ "\nUni:     "
											+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CurrentUniversity")
											+ "\nStudy: "
											+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CurrentStudy")
											+ "\nCity:     "
											+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CityOfResidence");

									matchInfo.add(results);

								} else {
									if(!userCourses.equals("{}")){
										//Parse String to a JSONObject:
										try {
											courseList = (JSONObject)new JSONParser().parse(userCourses.toString());
										} catch (ParseException e1) {
											e1.printStackTrace();
										}
										
										keys = courseList.keySet();
									    loop = keys.iterator();	
									}else{
										System.out.println("No courses #match,viewprofile2 tab");
									}
									
									String displayCourses = "";
									while(loop.hasNext()) {
										String key = (String)loop.next();
								    	if (!loop.hasNext()) {
								    		displayCourses += key;
										}else{
									    	displayCourses += key  +", ";
										}
								    }
									String available = "No  :(  Please check back later.";
									if(Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Available").equals("true"))
									{
										available="Yes, contact me now!";
									}
									results = "Email: " + Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Email")
											+ "\nName: " + Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Firstname")+" "+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Lastname")
											+ "\nUni:     "
											+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CurrentUniversity")
											+ "\nStudy: "
											+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CurrentStudy")
											+ "\nCity:     "
											+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CityOfResidence")
											+ "\nAvailable Now?     "
											+ available
											+ "\nCourses: " + displayCourses;

									matchInfo.add(results);
								}

							} catch (IOException e) {
								e.printStackTrace();
							}
						}

						// Display results:
						for (String j : matchInfo) {
							if (j.contains(curEmail)) {
								System.out.println(curEmail + "Exists");
							} else {
								filterTable.getItems().add(j);
							}
						}

						// Reset current user:
						try {
							curEmail = Client.toServer("INCOMING-FROMOTHERSGET¿" + curEmail + "¿Firstname");
						} catch (IOException e) {
							e.printStackTrace();
						}

						// No results occasion, Clear enter box, and show 'show
						// profile' button
						enterSearch.clear();
						viewProfile2.setVisible(true);
						if (filterTable.getItems().isEmpty()) {
							filterTable.getItems().add("No results..");
						}

					}

				} else {
					if (filterTable.getItems().isEmpty()) {
						filterTable.getItems().add("No results..");
					}
				}
			}

		});

		Button matchNow = new Button("Match Now!");
		matchNow.getStyleClass().add("matchbut");
		matchNow.setLayoutX(470);
		matchNow.setLayoutY(130);

		matchNow.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("resource")
			public void handle(ActionEvent args) {
				SearchResultList.getItems().clear();
				String usersMatch = "";
				String curEmail = "";
				try {
					curEmail = Client.toServer("INCOMING-GET Email");
					String userStudy = Client.toServer("INCOMING-GET CurrentStudy");
					String userUni = Client.toServer("INCOMING-GET CurrentUniversity");
					String userCity = Client.toServer("INCOMING-GET CityOfResidence");
					usersMatch = Client.toServer("INCOMING-MATCH" + "¿" + userStudy + "¿" + userUni + "¿" + userCity);

				} catch (IOException e) {
					e.printStackTrace();
				}
				ArrayList<String> matchEmails = new ArrayList<String>();
				ArrayList<String> matchNames = new ArrayList<String>();
				Set keys = null;
				Iterator loop = null;
				JSONObject courseList = null;

				Scanner sc;
				if (usersMatch.equals("[]")) {
					System.out.println("No Results#####");

					SearchResultList.getItems().add("No Results..");
				} else {
					sc = new Scanner(usersMatch);

					sc.useDelimiter(",");
					String temp = "";

					while (sc.hasNext()) {
						temp = sc.next().substring(1);
						if (!temp.equals(curEmail)) {
							if (temp.endsWith("]")) {
								matchEmails.add(temp.substring(0, temp.length() - 1));
							} else {
								matchEmails.add(temp);
							}
						}
					}

					String results = "";
					for (String s : matchEmails) {
						try {

							String userCourses = "";
							try {
								userCourses = Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Course list");

							} catch (IOException e1) {
								e1.printStackTrace();
							}
							if (userCourses.equals("{}")) {
								System.out.println("empty");

								results = "Email: " + Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Email")
										+ "\nName: " + Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Firstname")
										+ "\nUni:     "
										+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CurrentUniversity")
										+ "\nStudy: " + Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CurrentStudy")
										+ "\nCity:     "
										+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CityOfResidence");

								matchNames.add(results);
							} else {

								if(!userCourses.equals("{}")){
									//Parse String to a JSONObject:
									try {
										courseList = (JSONObject)new JSONParser().parse(userCourses.toString());
									} catch (ParseException e1) {
										e1.printStackTrace();
									}
									
									keys = courseList.keySet();
								    loop = keys.iterator();	
								}else{
									System.out.println("No courses #match,viewprofile2 tab");
								}
								String displayCourses = "";
								while(loop.hasNext()) {
									
									String key = (String)loop.next();
							    	if (!loop.hasNext()) {
							    		displayCourses += key;
									}else{
								    	displayCourses += key  +", ";
									}
							    }
								String available = "No  :(  Please check back later.";
								if(Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Available").equals("true"))
								{
									available="Yes, contact me now!";
								}
								results = "Email: " + Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Email") 
										+ "\nName: " + Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Firstname")+" "+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿Lastname")
										+ "\nUniversity: "
										+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CurrentUniversity")
										+ "\nStudy: " + Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CurrentStudy")
										+ "\nCity:     "
										+ Client.toServer("INCOMING-FROMOTHERSGET¿" + s + "¿CityOfResidence")
										+ "\nAvailable Now?     "
										+ available;
								results += "\nCourses: " + displayCourses;
									

								matchNames.add(results);
							}

						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					// Reset login
					try {
						curEmail = Client.toServer("INCOMING-FROMOTHERSGET¿" + curEmail + "¿Firstname");
					} catch (IOException e) {
						e.printStackTrace();
					}

					for (String s : matchNames) {
						if (s.contains(curEmail)) {
							System.out.println("Can't Match yourself");
						} else {
							SearchResultList.getItems().add(s);
						}
					}
					viewProfile.setVisible(true);
				}
				/*
				 * SearchResultList.setVisible(true);
				 * SearchResultList.setLayoutX(480);
				 * SearchResultList.setLayoutY(250);
				 */
			}
		});

		Line line = new Line();
		line.setStartX(350);
		line.setStartY(225);
		line.setEndX(900);
		line.setEndY(225);

		Line line2 = new Line();
		line2.setStartX(1020);
		line2.setStartY(225);
		line2.setEndX(1550);
		line2.setEndY(225);
		// Maken van Button en geven van functie en text.
		final Button SearchButton = new Button();
		// Add style
		SearchButton.getStyleClass().add("buttonspecific");
		SearchButton.setText("Search");
		SearchButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				viewProfile2.setVisible(true);
			}
		});

		Pane root = new Pane();
		root.getStyleClass().add("Background");
		
		Label checkLabel = new Label("By clicking available now");
		Label checkLabel2 = new Label("you let others know you are");
		Label checkLabel3 = new Label("available to work together now.");
		
		checkLabel.getStyleClass().add("availableLabel");
		checkLabel2.getStyleClass().add("availableLabel");
		checkLabel3.getStyleClass().add("availableLabel");

		checkLabel.setLayoutX(750);
		checkLabel.setLayoutY(159);

		checkLabel2.setLayoutX(750);
		checkLabel2.setLayoutY(179);
		
		checkLabel3.setLayoutX(750);
		checkLabel3.setLayoutY(199);
		
		
		
		// Checkbox maken.
		CheckBox SearchAvailableNow = new CheckBox("Available now?");
		SearchAvailableNow.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				try {
					Client.toServer("INCOMING-CHANGE Available " + newValue);
					System.out.println(newValue);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		SearchAvailableNow.setSelected(userAvailability);

		// Locatie geven
		SearchOptions.setLayoutX(1125);
		SearchOptions.setLayoutY(110);
		SearchAvailableNow.setLayoutX(750);
		SearchAvailableNow.setLayoutY(140);
		SearchButton.setLayoutX(500);
		SearchButton.setLayoutY(400);
		noResults.setLayoutX(300);
		noResults.setLayoutY(500);
		SearchResultList.setLayoutX(350);
		SearchResultList.setLayoutY(250);
		matchButton.setLayoutX(600);
		matchButton.setLayoutY(500);
		UrgentBox.setLayoutX(800);
		UrgentBox.setLayoutY(500);
		filterTable.setLayoutX(1020);
		filterTable.setLayoutY(250);
		// filterTable.getStyleClass().add("filter");
		filterTable.setMinWidth(540);
		Label expl2 = new Label();
		expl2.setText("Search for a match by filters.");
		expl2.setLayoutX(1100);
		expl2.setLayoutY(100);
		expl2.getStyleClass().add("e");

		SearchOptions.setLayoutX(1125);
		SearchOptions.setLayoutY(145);
		
		Label expl = new Label("helo");
		expl.setText("Search for others who are available now.");
		expl.setLayoutX(451);
		expl.setLayoutY(100);
		expl.getStyleClass().add("e");

		Pane rootMatchTabe = new Pane();
		rootMatchTabe.getStyleClass().add("Background");
		// Toevoegen van SearchOptions, SearchAvailableNow,
		// SearchField,SearchButton,noResults,SearchResultList

		// if(countMatch == 0){
		rootMatchTabe.getChildren().addAll(checkLabel,checkLabel2,checkLabel3,expl2, expl, viewProfile2, filterTable, SearchAvailableNow, enterSearch,
				line2, searchCourse, viewProfile, line, UrgentBox, SearchOptions,
				/* SearchOptionsBox, SearchBox, */ matchButton, /* SearchButton, */noResults, SearchResultList,
				overzicht, bovens, picaviews, imgviews, helpout, matchNow);
		// }
		countMatch++;

		Scene sceneeMatchTab = new Scene(rootMatchTabe, 1600, 900);
		primaryStage.setScene(sceneeMatchTab);
		sceneeMatchTab.getStylesheets().add("Match.css");
		primaryStage.show();
	}
	
	/**
	 * SETTING TAB.
	 */
	public void settingsTab(final Stage primaryStage, final Scene sceneMatchTab, Pane rootMatchTab,

			Scene sceneProfileTabe, final Scene scenetest, Pane rootProfileTabe, Pane rootCourseTab,
			Scene CourseScene) {

		// Invoegen van sidebar
		/**
		 * een button help. De setaction moet gelinkt worden aan een nieuwe
		 * page, maar welke? (Dario nodig)
		 */
		Button help = new Button("Help");
		help.setMinWidth(90);
		help.getStyleClass().add("help");
		help.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				helpTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
						rootCourseTab, CourseScene);
			}
		});

		/**
		 * Een button voor logout. De set action moet nog gemaakt worden om
		 * terug te gaan naar de hoofdpagina? (Dario nodig)
		 */
		Button logout = new Button("Log out");
		logout.setMinWidth(90);
		logout.getStyleClass().add("logout");
		logout.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				try {
					start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		/**
		 * Een box voor de help en logout buttons (dario nodig)
		 */
		HBox helpout = new HBox();
		helpout.getChildren().addAll(help, logout);
		helpout.getStyleClass().add("helpoutbox");
		helpout.setLayoutX(1410);
		helpout.setLayoutY(25);

		/**
		 * Een foto die hooft bij profile button (Dario nodig)
		 */
		Image prfoto = new Image(getClass().getResourceAsStream("huisteken.jpg"));
		ImageView profileimage = new ImageView(prfoto);
		Button profile = new Button("Profile", profileimage);
		profile.getStyleClass().add("profile");
		profile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				// profileTab(primaryStage, scenetest, rootProfileTabe,
				// sceneProfileTabe,sceneMatchTab,rootMatchTab);
				try {
					profileTab(primaryStage, scenetest, rootProfileTabe, sceneProfileTabe, sceneMatchTab, rootMatchTab,
							rootCourseTab, CourseScene);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 1final Stage primaryStage, final Scene scenetest, Pane
				// rootProfileTabe, Scene sceneProfileTabe, final Scene
				// sceneMatchTab, Pane rootMatchTab) throws IOException{

			}
		});

		/**
		 * Een foto die hoort bij course button (Dario nodig)
		 */
		Image crfoto = new Image(getClass().getResourceAsStream("courseteken.jpg"));
		ImageView coursefoto = new ImageView(crfoto);
		Button courses = new Button("Courses", coursefoto);
		courses.getStyleClass().add("courses");
		courses.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				coursesTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
						rootCourseTab, CourseScene);
			}
		});

		/**
		 * Een foto die hoort bij match button (Dario nodig)
		 */
		Image mfoto = new Image(getClass().getResourceAsStream("matchteken.jpg"));
		ImageView matchfoto = new ImageView(mfoto);
		Button match = new Button("Match", matchfoto);
		match.getStyleClass().add("match");
		match.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				// final Stage primaryStage, final Scene sceneMatchTab, Pane
				// rootMatchTab, Scene sceneProfileTabe
				matchTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
						rootCourseTab, CourseScene);
			}
		});

		/**
		 * Een foto die hoort bij settings button (dario nodig)
		 */
		Image setfoto = new Image(getClass().getResourceAsStream("settingsteken.jpg"));
		ImageView settingsfoto = new ImageView(setfoto);
		Button settings = new Button("Settings", settingsfoto);
		settings.getStyleClass().add("settings");

		/**
		 * Een VBox voor alle buttons links op de pagina (Dario nodig)
		 */
		VBox overzicht = new VBox();
		overzicht.getChildren().addAll(profile, courses, match, settings);
		overzicht.getStyleClass().add("overzicht");
		overzicht.setLayoutX(50);
		overzicht.setLayoutY(100);
		overzicht.setMinHeight(630);

		// Invoegen van bovenstuk
		Image logos = new Image("log.jpg");
		ImageView imgviews = new ImageView(logos);
		Image mottos = new Image("motto.jpg");
		ImageView picaviews = new ImageView(mottos);

		HBox bovens = new HBox();
		bovens.setMinSize(1550, 85);
		// bovens.getChildren().addAll(picaviews, imgviews);
		bovens.getStyleClass().add("bovenstuk");

		bovens.setLayoutX(50);
		picaviews.setLayoutX(730);
		picaviews.setLayoutY(30);

		imgviews.setLayoutX(50);
		imgviews.setLayoutY(25);

		
		Label old = new Label();
		old.setText("Old Password:");
		old.getStyleClass().add("changeLabel");
		old.setLayoutX(300);
		old.setLayoutY(210);
		
		Label fail = new Label("Incorrect information supplied.");
		fail.setLayoutX(450);
		fail.setLayoutY(450);
		fail.getStyleClass().add("fail");
		fail.setVisible(false);
		
		Label success = new Label();
		success.setLayoutX(450);
		success.setLayoutY(450);
		success.getStyleClass().add("fail");
		success.setVisible(false);
		
		Label neww = new Label();
		neww.setText("New Password:");
		neww.getStyleClass().add("changeLabel");
		neww.setLayoutX(300);
		neww.setLayoutY(250);
		
		Label changePassword = new Label();
		changePassword.setText("Change your password:");
		changePassword.getStyleClass().add("changeLabel");
		changePassword.setLayoutX(450);
		changePassword.setLayoutY(170);
		changePassword.setVisible(true);
		

		Label enter = new Label();
		enter.setText("Enter password:");
		enter.getStyleClass().add("changeLabel");
		enter.setLayoutX(800);
		enter.setLayoutY(250);
		enter.setVisible(true);

		Label deleteAccount = new Label();
		deleteAccount.setText("Delete your account:");
		deleteAccount.getStyleClass().add("changeLabel");
		deleteAccount.setLayoutX(950);
		deleteAccount.setLayoutY(170);
		deleteAccount.setVisible(true);
		
		Label deleteAccount1 = new Label();
		deleteAccount1.setText("We and your friends will miss you...");
		deleteAccount1.getStyleClass().add("changeLabel");
		deleteAccount1.setLayoutX(800);
		deleteAccount1.setLayoutY(210);
		deleteAccount1.setVisible(true);
		
		
		
		PasswordField oldPasswordField = new PasswordField();
		oldPasswordField.setPromptText("Enter old password");
		oldPasswordField.getStyleClass().add("changeFields");
		oldPasswordField.setLayoutX(450);
		oldPasswordField.setLayoutY(210);

		
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Enter password");
		passwordField.getStyleClass().add("changeFields");
		passwordField.setLayoutX(950);
		passwordField.setLayoutY(250);


		PasswordField newPasswordField = new PasswordField();
		newPasswordField.setPromptText("Enter new password");
		newPasswordField.getStyleClass().add("changeFields");
		newPasswordField.setLayoutX(450);
		newPasswordField.setLayoutY(250);

		Button confirmPasswordChange = new Button();
		confirmPasswordChange.setText("Confirm Password Change");
		confirmPasswordChange.getStyleClass().add("settingsButtons");
		confirmPasswordChange.setLayoutX(450);
		confirmPasswordChange.setLayoutY(310);
		confirmPasswordChange.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				String checkOld = oldPasswordField.getText();
				String neww = newPasswordField.getText();
				if(!(checkOld.equals("")||neww.equals(""))){
					
					String old = null;
					try {
						old = Client.toServer("INCOMING-GET Password");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					if(old.equals(checkOld)){
						try {
							Client.toServer("INCOMING-CHANGE Password "+ neww);
						} catch (IOException e) {
							e.printStackTrace();
						}
						fail.setVisible(false);
						oldPasswordField.clear();
						newPasswordField.clear();
						success.setText("Password Changed!");
						success.setVisible(true);
					}else{
						fail.setVisible(true);
					}
					
				}else{
					fail.setVisible(true);
				}
			}
		});
		
		Button deleteAccountBttn = new Button();
		deleteAccountBttn.setText("Delete My Account");
		deleteAccountBttn.getStyleClass().add("settingsButtons");
		deleteAccountBttn.setLayoutX(950);
		deleteAccountBttn.setLayoutY(310);
		deleteAccountBttn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				String checkCur = passwordField.getText();
				if(!checkCur.equals("")){
					
					String old = null;
					String curEmail = null;
					try {
						old = Client.toServer("INCOMING-GET Password");
						curEmail = Client.toServer("INCOMING-GET Email");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					if(old.equals(checkCur)){
						try {
							Client.toServer("INCOMING-ACCREMOVE¿" + curEmail);
						} catch (IOException e) {
							e.printStackTrace();
						}
						fail.setVisible(false);
						try {
							start(primaryStage);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else{
						fail.setVisible(true);
					}
				}else{
					fail.setVisible(true);
				}
				
			}
		});

		Line line = new Line();
		line.setStartX(740);
		line.setEndX(740);
		line.setStartY(160);
		line.setEndY(350);
	
		
		Pane root = new Pane();
		root.getChildren().addAll(enter,success,fail,line,old,neww,passwordField,changePassword, deleteAccount,deleteAccount1, oldPasswordField, newPasswordField,
				confirmPasswordChange, deleteAccountBttn, overzicht, bovens, picaviews, imgviews, helpout);
		root.getStyleClass().add("Background");
		Scene OptiesScene = new Scene(root, 1600, 900);
		primaryStage.setScene(OptiesScene);

		OptiesScene.getStylesheets().add("Opties.css");
		primaryStage.show();
	}
	
	/**
	 * HELP TAB
	 */
	public void helpTab(final Stage primaryStage, final Scene sceneMatchTab, Pane rootMatchTab, Scene sceneProfileTabe,
				final Scene scenetest, Pane rootProfileTabe, Pane rootCourseTab, Scene CourseScene) {

			primaryStage.setTitle("HelpTab");

			TreeItem<String> root, match, change, login, delete, edit, settings, howdelete, report, other;

			root = new TreeItem<>();
			root.setExpanded(true);

			match = onderdeel("I lost all my matches.", root);
			onderdeel(
					"Try logging out and logging back in. As long as you haven't deleted your account you should not worry!",
					match);

			change = onderdeel("How do I change my name or age?", root);
			onderdeel(
					"You should go to your profile tab. There you can find your current name and age. By double clicking the wanted item (E.G. age) you can edit it. Fake names or ages will not be tolerated.",
					change);

			login = onderdeel("I can't log in.", root);
			onderdeel(
					"Shut everything down and try to log in again. If you were unsuccessfull again, please contact us (c0d3bust3rs@hotmail.com). ",
					login);

			delete = onderdeel("Will all my data be lost if I delete the app?", root);
			onderdeel("As long as you don't delete your account, deleting the app won't delete all the data.", delete);

			edit = onderdeel("How do I edit my profile?", root);
			onderdeel("You should go to your profile tab. By double clicking an item you can edit the data.", edit);

			settings = onderdeel("I can't change my settings.", root);
			onderdeel("Please contact us (c0d3bust3rs@hotmail.com).", settings);

			howdelete = onderdeel("How do I delete my account?", root);
			onderdeel(
					"Go to the options tab and click on delete account. Fill in your password (for savety reasons) and you can delete your account.",
					howdelete);

			report = onderdeel("How do I report someone?", root);
			onderdeel(
					"Please contact us (c0d3bust3rs@hotmail.com). Make sure you explain the situation and give us all the required information(name of the person).",
					report);

			other = onderdeel("I got another question.", root);
			onderdeel("Please contact us (c0d3bust3rs@hotmail.com).", other);

			totLijst = new TreeView<>(root);
			totLijst.setLayoutX(300);
			totLijst.setLayoutY(250);
			totLijst.setMinHeight(550);
			totLijst.setMinWidth(1300);
			totLijst.setShowRoot(false);
			totLijst.getStyleClass().add("totLijst");

			Label faq = new Label("Frequently Asked Questions");
			faq.getStyleClass().add("FAQ");
			faq.setLayoutX(750);
			faq.setLayoutY(150);

			/**
			 * een button help. De setaction moet gelinkt worden aan een nieuwe
			 * page, maar welke? (Dario nodig)
			 */
			Button help = new Button("Help");
			help.setMinWidth(90);
			help.getStyleClass().add("help");

			/**
			 * Een button voor logout. De set action moet nog gemaakt worden om
			 * terug te gaan naar de hoofdpagina? (Dario nodig)
			 */
			Button logout = new Button("Log out");
			logout.setMinWidth(80);
			logout.getStyleClass().add("logout");
			logout.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {

					try {
						start(primaryStage);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});

			/**
			 * Een box voor de help en logout buttons (dario nodig)
			 */
			HBox helpout = new HBox();
			helpout.getChildren().addAll(help, logout);
			helpout.getStyleClass().add("helpoutbox");

			/**
			 * Een foto die hooft bij profile button (Dario nodig)
			 */
			Image prfoto = new Image(getClass().getResourceAsStream("huisteken.jpg"));
			ImageView profileimage = new ImageView(prfoto);
			Button profile = new Button("Profile", profileimage);
			profile.getStyleClass().add("profile");
			profile.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// profileTab(primaryStage, scenetest, rootProfileTabe,
					// sceneProfileTabe,sceneMatchTab,rootMatchTab);
					try {
						profileTab(primaryStage, scenetest, rootProfileTabe, sceneProfileTabe, sceneMatchTab, rootMatchTab,
								rootCourseTab, CourseScene);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 1final Stage primaryStage, final Scene scenetest, Pane
					// rootProfileTabe, Scene sceneProfileTabe, final Scene
					// sceneMatchTab, Pane rootMatchTab) throws IOException{

				}
			});

			/**
			 * Een foto die hoort bij course button (Dario nodig)
			 */
			Image crfoto = new Image(getClass().getResourceAsStream("courseteken.jpg"));
			ImageView coursefoto = new ImageView(crfoto);
			Button courses = new Button("Courses", coursefoto);
			courses.getStyleClass().add("courses");
			courses.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					coursesTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
							rootCourseTab, CourseScene);
				}
			});

			/**
			 * Een foto die hoort bij match button (Dario nodig)
			 */
			Image mfoto = new Image(getClass().getResourceAsStream("matchteken.jpg"));
			ImageView matchfoto = new ImageView(mfoto);
			Button matcht = new Button("Match", matchfoto);
			matcht.getStyleClass().add("match");
			matcht.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// final Stage primaryStage, final Scene sceneMatchTab, Pane
					// rootMatchTab, Scene sceneProfileTabe
					matchTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
							rootCourseTab, CourseScene);
				}
			});

			/**
			 * Een foto die hoort bij settings button (dario nodig)
			 */
			Image setfoto = new Image(getClass().getResourceAsStream("settingsteken.jpg"));
			ImageView settingsfoto = new ImageView(setfoto);
			Button settingz = new Button("Settings", settingsfoto);
			settingz.getStyleClass().add("settings");
			settingz.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					// final Stage primaryStage, final Scene sceneMatchTab, Pane
					// rootMatchTab, Scene sceneProfileTabe
					settingsTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
							rootCourseTab, CourseScene);
				}
			});

			/**
			 * Een VBox voor alle buttons links op de pagina (Dario nodig)
			 */
			VBox overzicht = new VBox();
			overzicht.getChildren().addAll(profile, courses, matcht, settingz);
			overzicht.getStyleClass().add("overzicht");
			courses.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					coursesTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
							rootCourseTab, CourseScene);
				}
			});

			/**
			 * Een foto met daarin de logo.
			 */
			Image logo = new Image("log.jpg");
			ImageView imgview = new ImageView(logo);
			Image motto = new Image("motto.jpg");
			ImageView picaview = new ImageView(motto);

			HBox boven = new HBox();
			boven.setMinSize(1550, 85);
			boven.getChildren().addAll(picaview, imgview);
			boven.getStyleClass().add("bovenstuk");

			boven.setLayoutX(50);

			picaview.setLayoutX(730);
			picaview.setLayoutY(30);

			imgview.setLayoutX(50);
			imgview.setLayoutY(25);

			helpout.setLayoutX(1410);
			helpout.setLayoutY(25);

			overzicht.setLayoutX(50);
			overzicht.setLayoutY(100);
			overzicht.setMinHeight(630);

			Pane design = new Pane();
			// if(countHelp ==0){
			design.getChildren().addAll(totLijst, boven, picaview, imgview, helpout, overzicht, faq);
			// }
			countHelp++;
			Scene scene = new Scene(design, 1600, 900);
			design.getStylesheets().add("layout.css");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	
	
	/**
	 * THIS IS AN EXAMPLE OF A METHOD WHICH IS NOT INTERGRATED INTO THE TABS'S CODES. 
	 * THIS IS WHAT YOU NEED TO GET OUT FROM THE TABS SO YOU CAN TEST THEM.
	 */	
	public TreeItem<String> onderdeel(String string, TreeItem<String> ouder) {

		TreeItem<String> item = new TreeItem<>(string);
		item.setExpanded(true);
		ouder.getChildren().add(item);
		return item;

	}
		


	




}
	
