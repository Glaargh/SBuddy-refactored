import java.awt.MouseInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Font;

/**
 * @author C0d3Bust3rs Revision: C0d3Bust3rs 2.0 Date: 11-06-2016 READ JAVADOC
 *         CAREFULY WHILE ATTEMPTING TO MODIFY CODE. ANY RECKLESS MODIFICATION
 *         MIGHT RESULT IN HIDDEN FAILURES OF THIS PROGRAM WHICH ARE COSTLY TO
 *         FIX.
 * 
 *         TO SEE ALL THE GENERAL EXPLANATION OF THE PROGRAM, COLLAPSE ALL BY
 *         PUSHING: CTRL + SHIFT + DIVIDE KEY ( this key: / )
 * 
 *         DUE TO THE CODE'S COMPLEXITY & VAGUENESS, IT'S BEST TO KEEP TAB CODES
 *         CLOSED AND EXPAND THEM ONLY IF YOU ARE GOING TO WORK WITH THEM. USE
 *         TEST COVERAGE TO SEE EXACTLY WHERE CODES ARE TESTABLE (SUCH AS
 *         PARSING CODE) SO YOU CAN TRANSFER THEM TO SEPERATE METHODS.
 */
@SuppressWarnings("deprecation")
public class Design extends Application {

	/**
	 * VARIABLES, LABELS INITIALIZATION OF THE PROGRAM.
	 */
	private Label lb_text;
	private boolean urgency = false;
	private Client client;
	private Parser parser;

	/**
	 * Where the Design scene gets initially constructed
	 * 
	 * @param args
	 *            Where the first argument is the IP and the second argument the
	 *            port of the server.
	 */
	public Design(Client clientSocket) {
		client = clientSocket;
		parser = new Parser();
	}

	/**
	 * LOGIN TAB, MOSTLY JAVAFX BUT THERE ARE A FEW CODE GROUPS WHICH CAN BE PUT
	 * INTO THEIR OWN METHODS SO IT CAN BE TESTED. RUN TEST COVERAGE AND RETURN
	 * TO THIS METHOD TO SEE WHERE EXACTLY THE CODE IS NEEDED TO BE TAKEN OUT.
	 */
	public void start(final Stage primaryStage) throws Exception {

		Label labels;
		Label labelss;

		/**
		 * .setOnCloseRequest will terminate the server session when exiting
		 * GUI.
		 */
		primaryStage.setOnCloseRequest(event -> {
			System.out.println("Stage is closing");
			try {
				client.toServer("exit");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		});

		// HERE ALL THE PANES FOR ALL THE TABS ARE MADE:
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

		// HERE ALL THE SCENES FOR ALL THE TABS ARE MADE:
		final Scene scenetest = new Scene(root, 1300, 800);
		final Scene sceneProfileTabe = new Scene(rootProfileTabe, 1600, 900);
		final Scene sceneMatchTab = new Scene(rootMatchTab, 1600, 900);
		final Scene CourseScene = new Scene(rootCourseTab, 1800, 900);
		final Scene helpScene = new Scene(rootHelp, 1600, 900);

		// **********************************************************PANE OF
		// PROFILE TAB*********************

		// Placeholder for logo. Hier wordt naam gegeven.
		Image logo = new Image("log.jpg");
		ImageView imgview = new ImageView(logo);

		lb_text = new Label();
		labels = new Label();
		labels.getStyleClass().add("label12");
		labels.setLayoutX(730);
		labels.setLayoutY(120);
		labelss = new Label();

		// Making of email form. Showing pre text with
		// setPromptText("Email")
		final TextField email = new TextField();
		email.setPromptText("Email");
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(email);
		hb1.setSpacing(10);

		// Making of password form. Showing pre text with
		// setPromptText("Password")
		final PasswordField password = new PasswordField();
		password.setPromptText("Password");
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(password);
		hb2.setSpacing(13);
		labels.setVisible(false);

		/**
		 * WE GET MAIL AND PASSWORD BY .getText().
		 */
		Button button1 = new Button("Log in");
		button1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				if (!(email.getText().isEmpty() || password.getText().isEmpty())) {
					try {

						if (client.toServer(ClientMethods.Login(email.getText().trim(), password.getText().trim()))
								.equals("true")) {// return true if person is in
													// database and correct
													// password. IF THE TEXTS
													// FIELDS ARE NOT EMPTY AND
													// THE USER EXISTS:
							// EXECUTE profileTab method to open profile tab.
							profileTab(primaryStage, scenetest, rootProfileTabe, sceneProfileTabe, sceneMatchTab,
									rootMatchTab, rootCourseTab, CourseScene);
							email.clear();
							password.clear();

						} else {// UNSUCCESSFULL LOGIN
							labels.setText("Sorry, your combination is not valid. Please contact admin.");
							labels.setVisible(true);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {// EMPTY TEXT FIELDS
					labels.setText("Please fill in both fields. Email must be valid.");
					labels.setVisible(true);
				}
			}
		});

		button1.setLayoutX(500);
		button1.setLayoutY(1000);

		// Style of the button on .my_customLabel from css.
		button1.getStyleClass().add("my_customLabel");

		// Same for TextFields met .topFields.
		password.getStyleClass().add("topFields");
		email.getStyleClass().add("topFields");

		// The login form and button in one HBox so we can move it nicely.
		HBox LoginHolder = new HBox();
		LoginHolder.getStyleClass().add("lgin");
		LoginHolder.getChildren().addAll(hb1, hb2, button1);

		Button button2 = new Button("Forgot your password?");

		/**
		 * Placeholder function for the button which should send the password to
		 * the email adres if clicked.
		 */
		button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				lb_text.setText("Your password will be sent to your emailaddress shortly.");
			}
		});
		button2.setVisible(false);

		// Giving of style .ForgotPassword from css.
		button2.getStyleClass().add("ForgotPassword");

		// Making of text above the form and giving it the right styles
		Label find = new Label("Find a study buddy");
		find.getStyleClass().add("find");
		Label free = new Label("get started - it's ");
		Label free2 = new Label("free!");
		free2.getStyleClass().add("free2bolding");
		HBox freeholder = new HBox();
		freeholder.getChildren().addAll(free, free2);
		freeholder.getStyleClass().add("freeholder");

		// Making of form for registreren (voornaam).
		Label label1 = new Label("First name:");
		label1.getStyleClass().add("firstname");
		final TextField firstname = new TextField();
		HBox hb3 = new HBox();
		hb3.getChildren().addAll(label1, firstname);
		hb3.getStyleClass().add("first");
		hb3.setSpacing(10);

		// Making of form for achternaam.
		Label label2 = new Label("Last name:");
		label2.getStyleClass().add("lastname");
		final TextField lastname = new TextField();
		HBox hb4 = new HBox();
		hb4.getChildren().addAll(label2, lastname);
		hb4.getStyleClass().add("second");
		hb4.setSpacing(14);

		// Making of form for email.
		Label label3 = new Label("Email:");
		label3.getStyleClass().add("email");
		final TextField emailaddress = new TextField();
		HBox hb5 = new HBox();
		hb5.getChildren().addAll(label3, emailaddress);
		hb5.getStyleClass().add("third");
		hb5.setSpacing(58);

		// Making of form for passwoord.
		Label label4 = new Label("Password:");
		label4.getStyleClass().add("passwd");
		final TextField passwords = new TextField();
		HBox hb6 = new HBox();
		hb6.getChildren().addAll(label4, passwords);
		hb6.getStyleClass().add("fourth");
		hb6.setSpacing(15);

		/**
		 * JOIN NOW BUTTON WHICH ALSO USES .getText() to retrieve email and
		 * password info.
		 */
		Button button3 = new Button("Join now");
		button3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				// LOGICAL CHECKS IF TEXTS FIELDS AREN'T EMPTY AND WE USE
				// METHOD: "client
				// .toServer(ClientMethods.Register(firstname etc.." to register
				// user on database.
				if (!(emailaddress.getText().isEmpty() || firstname.getText().isEmpty() || lastname.getText().isEmpty()
						|| (passwords.getText().length() < 6))) {
					try {
						if (client.toServer(ClientMethods.Register(firstname.getText().trim(),
								lastname.getText().trim(), emailaddress.getText().trim(), passwords.getText().trim()))
								.equals("true")) {
							client.toServer(
									ClientMethods.Login(emailaddress.getText().trim(), passwords.getText().trim()));
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

		// Making HBox so you can transfer easily and giving the style to the
		// relevant parts
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

		// Placing everything in VBox for display
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

		// Scene maken en zetten als de scene voor primaryStage.
		primaryStage.setScene(scenetest);

		// add css to scene
		scenetest.getStylesheets().add("Voorpagina.css");

		// show the scene
		primaryStage.show();
	}

	/**
	 * LOTS OF JAVAFX AND NOT ALLOT OF PARSING, CHECK IF IT IS NECESSARY TO
	 * CHANGE (GIVE OTHER CODE PRIORITY). THERE IS NOT ALLOT OF CODE WHICH YOU
	 * CAN PLACE OUTSIDE GUI, BUT A DIFFERENT APPROACH (GUI CODE REFACTOR) TO
	 * GIVE DESIRED FUNCTIONALITY OF PROFILE TAB IS RECOMMENDED. THIS IS THE TAB
	 * WITH THE MOST "TESTABLE CODES". SEE COVERAGE FOR MORE INSTRUCTION
	 */
	@SuppressWarnings("deprecation")
	public void profileTab(final Stage primaryStage, final Scene scenetest, Pane rootProfileTabee,
			Scene sceneProfileTabee, final Scene sceneMatchTab, Pane rootMatchTab, Pane rootCourseTab,
			Scene CourseScene) throws IOException {

		int countProfile = 0;

		/**
		 * HELP BUTTON, WILL OPEN HELP SCENE
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

		// Button for logout. IT WILL OPEN INITIAL LOGIN SCENE
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

		// Help and logout in one box
		HBox helpout = new HBox();
		helpout.getChildren().addAll(help, logout);
		helpout.getStyleClass().add("helpoutbox");

		// A button to delete all the input data in the profiletab
		Button deleteall = new Button("Delete all");
		deleteall.setMinSize(70, 30);
		deleteall.getStyleClass().add("deleteall");

		// A picture for profile
		Image prfoto = new Image(getClass().getResourceAsStream("huisteken.jpg"));
		ImageView profileimage = new ImageView(prfoto);
		Button profile = new Button("Profile", profileimage);
		profile.getStyleClass().add("profile");

		/**
		 * course button which will redirect to courses tab
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
		 * MATCH button which will redirect to MATCH tab
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
		 * SETTINGS button which will redirect to SETTINGS tab
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

		// A VBox for all the buttons on the page
		VBox overzicht = new VBox();
		overzicht.getChildren().addAll(profile, courses, match, settings);
		overzicht.getStyleClass().add("overzicht");
		overzicht.setMinHeight(630);

		/**
		 * A USERS INFORMATION IS DISPLAYED BY LABELS. TO RETRIEVE THE INFO WE
		 * USE method .toServer. for example: client.toServer(
		 * "INCOMING-GET Firstname") Like this all name,age,nationality etc.
		 * labels get display their info.
		 **/
		String Vorn = client.toServer(ClientMethods.get("Firstname"));
		final Label Voornaam = new Label(Vorn);
		Voornaam.getStyleClass().add("labelqqq");

		String Lstn = client.toServer(ClientMethods.get("Lastname"));
		final Label Lastname = new Label(Lstn);
		Lastname.getStyleClass().add("labelqqq");

		HBox bovennaam = new HBox();
		bovennaam.getChildren().addAll(Voornaam, Lastname);
		bovennaam.getStyleClass().add("bovennaam");
		bovennaam.setSpacing(8);

		ArrayList<Node> clickableNodes = new ArrayList<Node>();
		clickableNodes.add(Voornaam);

		String VNaamDesc = "Your firstname \nPress \"Enter\" when finished. ";
		String VNaamKey = "Firstname";
		String standardVNaam = "None";

		Voornaam.setOnMouseClicked(e -> constructEdit(VNaamDesc, standardVNaam, VNaamKey, Voornaam, primaryStage));

		clickableNodes.add(Lastname);

		String lnameDesc = "Your lastname\nPress \"Enter\" when finished. ";
		String lnameKey = "Lastname";
		String standardLName = "None";

		Lastname.setOnMouseClicked(e -> constructEdit(lnameDesc, standardLName, lnameKey, Lastname, primaryStage));

		// A textfield where you should give a small description about yourself.
		// This should not be a text field, but just a String. If you click on
		// it
		// a text field will appear to edit.
		String programa = client.toServer(ClientMethods.get("CurrentStudy"));
		Label student = new Label("Student " + programa);
		student.getStyleClass().add("labelSSS4");
		String univeristy = client.toServer(ClientMethods.get("CurrentUniversity"));
		Label at = new Label(" at " + univeristy);
		at.getStyleClass().add("labelSSS4");

		if (programa.trim().equals("Click to modify")) {
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

		// This text field gives the user the chance to give a bigger
		// description about himself
		String descr = client.toServer(ClientMethods.get("Description"));
		final Label descriptn = new Label(descr);
		descriptn.getStyleClass().add("labelSSS1");
		descriptn.setWrapText(true);
		HBox summy = new HBox();
		summy.setMaxWidth(1300);
		summy.setMinHeight(200);
		summy.getChildren().addAll(descriptn);
		summy.setSpacing(10);
		summy.getStyleClass().add("summy");

		// All .setOnMouseEntered and .setOnMouseExited are for the cursor to
		// change style on hover:
		clickableNodes.add(descriptn);
		String descDesc = "Tell us about yourself \nWhat do you like? \nWhat are you interested in? \nPress \"Enter\" when finished. ";
		String descKey = "Description";
		String standardDesc = "None";
		descriptn.setOnMouseClicked(e -> constructEdit(descDesc, standardDesc, descKey, descriptn, primaryStage));
		Label career = new Label("Career information");
		career.getStyleClass().add("summary");

		
		//A label and a textfield where you should write what your current study is 
		Label curs = new Label("Current Study: ");
		curs.getStyleClass().add("curslabel");
		String study = client.toServer(ClientMethods.get("CurrentStudy"));
		final Label currentstud = new Label(study);
		currentstud.getStyleClass().add("labelSSS");
		HBox curstud = new HBox();
		curstud.getChildren().addAll(curs, currentstud);
		curstud.getStyleClass().add("curstudybox");
		curstud.setSpacing(46);

		ArrayList<Label> clickSizableLabels = new ArrayList<Label>();
		clickSizableLabels.add(currentstud);

		String studyDesc = "Your study program in English."
				+ "\nEx: Computer Science \nPress \"Enter\" when finished. ";
		String studyKey = "CurrentStudy";
		String standardStudy = "None";

		currentstud.setOnMouseClicked(e -> constructEdit(studyDesc, standardStudy, studyKey, currentstud, primaryStage));

	
		//A label and a textfield to write what your study period is 
		Label studyper = new Label("Study period: ");
		studyper.getStyleClass().add("curslabel");
		String studyp = client.toServer(ClientMethods.get("StudyPeriod"));
		final Label stper = new Label(studyp);
		stper.getStyleClass().add("labelSSS");
		HBox stpbox = new HBox();
		stpbox.getChildren().addAll(studyper, stper);
		stpbox.getStyleClass().add("genderbox");
		stpbox.setSpacing(55);

		clickSizableLabels.add(stper);

		String stpdDesc = "Your study period with start year and end year."
				+ "\nEx: 2013-2016 \nPress \"Enter\" when finished. ";
		String stpdKey = "StudyPeriod";
		String standardStpd = "None";

		stper.setOnMouseClicked(e -> constructEdit(stpdDesc, standardStpd, stpdKey, stper, primaryStage));

		//A label and a textfield to fill in which university you currently are studying
		Label university = new Label("Current University: ");
		university.getStyleClass().add("curslabel");
		String un = client.toServer(ClientMethods.get("CurrentUniversity"));
		final Label uni = new Label(un);
		uni.getStyleClass().add("labelSSS");
		HBox unibox = new HBox();
		unibox.getChildren().addAll(university, uni);
		unibox.getStyleClass().add("genderbox");
		unibox.setSpacing(10);
		clickSizableLabels.add(uni);

		String uniDescription = "Your full university name in English \nPress \"Enter\" when finished. ";
		String uniKey = "CurrentUniversity";
		String standardUni = "None";
		uni.setOnMouseClicked(e -> constructEdit(uniDescription, standardUni, uniKey, uni, primaryStage));

		Label basicinfo = new Label("Basic information");
		basicinfo.getStyleClass().add("summary");

		// A label and a textfield to fill in your age
		Label agelable = new Label("Age :");
		agelable.getStyleClass().add("curslabel");
		String ag = client.toServer(ClientMethods.get("Age"));
		final Label age = new Label(ag);
		age.getStyleClass().add("labelSSS");
		HBox agebox = new HBox();
		agebox.getChildren().addAll(agelable, age);
		agebox.getStyleClass().add("genderbox");
		agebox.setSpacing(15);

		clickSizableLabels.add(age);

		String ageDescription = "Please fill in you age in numbers \nPress \"Enter\" when finished. ";
		String standardAge = "None";
		// key: Age
		age.setOnMouseClicked(e -> constructEdit(ageDescription, standardAge, "Age", age, primaryStage));

		//A label and a textfield to field in your gender
		Label gender = new Label("Gender: ");
		gender.getStyleClass().add("curslabel");
		// Info get from server is placed in geslacht label:
		String userGender = client.toServer(ClientMethods.get("Gender"));
		final Label geslacht = new Label(userGender);
		geslacht.getStyleClass().add("labelSSS");
		HBox genbox = new HBox();
		genbox.getChildren().addAll(gender, geslacht);
		genbox.getStyleClass().add("genderbox");
		genbox.setSpacing(15);

		clickSizableLabels.add(geslacht);

		String geslachtDescription = "Please fill in only: Male/Female \nPress \"Enter\" when finished. ";
		String standardGeslacht = "None";
		// key: Gender

		geslacht.setOnMouseClicked(
				e -> constructEdit(geslachtDescription, standardGeslacht, "Gender", geslacht, primaryStage));

		// A label and a textfield to fill in your country of residence
		Label country = new Label("Country: ");
		country.getStyleClass().add("curslabel");
		// 1)Change inital text field to label:
		final Label countryof = new Label();
		String userCountry = client.toServer(ClientMethods.get("CountryOfResidence"));
		// 2)Give it value of user.
		countryof.setText(userCountry);
		// 3)Labels of common size can use same style, like City, Country use
		// labelSSS
		countryof.getStyleClass().add("labelSSS");
		HBox countrybox = new HBox();
		countrybox.getChildren().addAll(country, countryof);
		countrybox.getStyleClass().add("countrybox");
		countrybox.setSpacing(11);

		clickSizableLabels.add(countryof);

		String countryDescription = "Your country name in English  \nPress \"Enter\" when finished. ";
		String countryKey = "CountryOfResidence";
		String standardCountry = "None";

		countryof.setOnMouseClicked(
				e -> constructEdit(countryDescription, standardCountry, countryKey, countryof, primaryStage));

		// A label and a textfield to fill in your city of residence
		Label city = new Label("City: ");
		city.getStyleClass().add("curslabel");
		final Label cit = new Label();
		String userCity = client.toServer(ClientMethods.get("CityOfResidence"));
		cit.setText(userCity);
		cit.getStyleClass().add("labelSSS");
		HBox citybox = new HBox();
		citybox.getChildren().addAll(city, cit);
		citybox.getStyleClass().add("citybox");
		citybox.setSpacing(47);

		clickSizableLabels.add(cit);

		String cityDesciption = "Your city name in English  \nPress \"Enter\" when finished. ";
		String standardCity = "None";
		// key = "CityOfResidence"

		cit.setOnMouseClicked(e -> constructEdit(cityDesciption, standardCity, "CityOfResidence", cit, primaryStage));

		Label contact = new Label("Contact information");
		contact.getStyleClass().add("summary");

		// A label and a textfield to fill in your Email
		Label eml = new Label("Email: ");
		eml.getStyleClass().add("curslabel");
		String el = client.toServer(ClientMethods.get("Email"));
		final Label email = new Label(el);
		email.getStyleClass().add("labelSSS");
		HBox mailbox = new HBox();
		mailbox.getChildren().addAll(eml, email);
		mailbox.getStyleClass().add("mailbox");
		mailbox.setSpacing(5);

		clickSizableLabels.add(email);

		String emailDescription = "Your e-mail address \nPress \"Enter\" when finished. ";
		String standardMail = "None";
		// key = "Email"
		email.setOnMouseClicked(e -> constructEdit(emailDescription, standardMail, "Email", email, primaryStage));

		//A label and a textfield to fill in your phone number
		Label phone = new Label("Tel: ");
		phone.getStyleClass().add("curslabel");
		String tel = client.toServer(ClientMethods.get("Phone"));
		final Label phonenumber = new Label(tel);
		phonenumber.getStyleClass().add("labelSSS");
		HBox phonebox = new HBox();
		phonebox.getChildren().addAll(phone, phonenumber);
		phonebox.getStyleClass().add("phonebox");
		phonebox.setSpacing(10);

		clickSizableLabels.add(phonenumber);

		String description = "Your telephone/Whatsapp number." + "\nPress \"Enter\" when finished. ";
		phonenumber.setOnMouseClicked(e -> constructEdit(description, "None", "Phone", phonenumber, primaryStage));

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
		String userURL = client.toServer(ClientMethods.get("Piclink"));
		ImageView picview = ImageViewBuilder.create().image(new Image("http://i68.tinypic.com/2vjt0xz.jpg")).build();
		try {
			picview = ImageViewBuilder.create().image(new Image(userURL)).build();
		} catch (Exception e) {
			picview = ImageViewBuilder.create().image(new Image("http://i68.tinypic.com/2vjt0xz.jpg")).build();
		}

		picview.getStyleClass().add("picuser");
		picview.setFitHeight(200);
		picview.setFitWidth(348);
		picview.setLayoutX(300);
		picview.setLayoutY(100);
		clickableNodes.add(picview);

		String picDescription = "Please add an URL link for you profile picture. \n Upload an picture on the internet and get the URL \nWe recommend www.tinypic.com \n Your picture will be changed the next time you logs in \nPress \"Enter\" when finished. ";
		String standardPic = "http://i67.tinypic.com/2ug08eu.jpg";
		// key: "Piclink"
		// standard:
		picview.setOnMouseClicked(e -> constructEdit(picDescription, standardPic, "Piclink", null, primaryStage));

		/**
		 * Foto van TU Delft, ook dit kan je veranderen naar een foto naar keus.
		 */
		String userPlacePic = client.toServer(ClientMethods.get("Placepic"));
		ImageView delftview = ImageViewBuilder.create().image(new Image("http://i68.tinypic.com/2vjt0xz.jpg")).build();
		try {
			delftview = ImageViewBuilder.create().image(new Image(userPlacePic)).build();
		} catch (Exception e) {
			delftview = ImageViewBuilder.create().image(new Image("http://i68.tinypic.com/2vjt0xz.jpg")).build();
		}

		delftview.getStyleClass().add("picuser");
		delftview.setFitHeight(200);
		delftview.setFitWidth(348);
		delftview.setLayoutX(1200);
		delftview.setLayoutY(100);

		clickableNodes.add(delftview);

		String delftDescription = "Please add an URL link for you university or secondary picture. \n Upload an picture on the internet and get the URL \nWe recommend www.tinypic.com  \n Your picture will be changed the next time you logs in \nPress \"Enter\" when finished. ";
		String standardDelft = "http://i67.tinypic.com/15fje5g.jpg";
		// key: "Placepic"
		delftview
				.setOnMouseClicked(e -> constructEdit(delftDescription, standardDelft, "Placepic", null, primaryStage));

		for (Label l : clickSizableLabels) {
			l.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					sceneProfileTabee.setCursor(Cursor.HAND);
					l.setScaleX(1.3);
					l.setScaleY(1.3);
				}
			});
			l.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					l.setScaleX(1);
					l.setScaleY(1);
					sceneProfileTabee.setCursor(Cursor.DEFAULT);
				}
			});
		}

		for (Node n : clickableNodes) {
			n.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					sceneProfileTabee.setCursor(Cursor.HAND);

				}
			});
			n.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					sceneProfileTabee.setCursor(Cursor.DEFAULT);
				}
			});
		}

		//A pic of the logo
		Image logo = new Image("log.jpg");
		ImageView imgview = new ImageView(logo);
		Image motto = new Image("motto.jpg");
		ImageView picaview = new ImageView(motto);

		HBox boven = new HBox();
		boven.setMinSize(1550, 85);
		boven.getChildren().addAll(picaview, imgview);
		boven.getStyleClass().add("bovenstuk");

		//Black line that separates career information and basic information
		Line line = new Line();
		line.setStartX(750);
		line.setStartY(500);
		line.setEndX(750);
		line.setEndY(727);

		//Black line that separates basic information and contact information
		Line line2 = new Line();
		line2.setStartX(1150);
		line2.setStartY(500);
		line2.setEndX(1150);
		line2.setEndY(727);

		Pane rootProfileTabe = new Pane();
		rootProfileTabe.setId("pane");

		rootProfileTabe.getChildren().addAll(boven, delftview, picaview, imgview, helpout, overzicht, bovennaam, desci,
				summary, summy, career, carinfo, basicinfo, basinfo, contact, contactinfo, line, line2, picview);

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
	 * Allot of parsing which should be done outside of GUI is in courses tab,
	 * try change
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void coursesTab(final Stage primaryStage, final Scene sceneMatchTab, Pane rootMatchTab,
			Scene sceneProfileTabe, final Scene scenetest, Pane rootProfileTabe, Pane rootCourseTab,
			Scene CourseScene) {

		int countCourse = 0;
		HBox hb = new HBox();

		// These are the tables storing course info
		TableView<Courses> table = new TableView<Courses>();

		// WE USE variable data, which is the data of the table, to change and
		// add info to our table
		try {
			String incomingCourses = client.toServer(ClientMethods.get("Course list"));

			ObservableList<Courses> data = FXCollections.observableArrayList();

			parser.parseCourses(incomingCourses, data);

			primaryStage.setTitle("Courses");
			primaryStage.setWidth(1600);
			primaryStage.setHeight(900);

			final Label label = new Label("Course Overview");
			label.setFont(new Font("Arial", 20));
			table.setEditable(true);

			@SuppressWarnings("rawtypes")
			TableColumn courseCol = new TableColumn("Course");
			courseCol.setMinWidth(300);
			courseCol.setCellValueFactory(new PropertyValueFactory<Courses, String>("firstName"));

			//HERE WE DEFINE THE COLUMN FOR COURSE DESCRIPTION.
			TableColumn gradeCol = new TableColumn("Description");
			gradeCol.setMinWidth(800);
			gradeCol.setCellValueFactory(new PropertyValueFactory<Courses, String>("lastName"));
			gradeCol.setCellFactory(TextFieldTableCell.forTableColumn());
			/**
			 * Allows for changing of cell info (.setOnEditCommit) as well as
			 * the user info in database with
			 * (client.toServer("INCOMING-COURSECHANGE¿"+h.getFirstName()+ "¿"+
			 * changedValue); ):
			 */
			gradeCol.setOnEditCommit(new EventHandler<CellEditEvent<Courses, String>>() {
				@Override
				public void handle(CellEditEvent<Courses, String> t) {

					// Choose selected (clicked on cell) item on table and edit
					// that specific info:
					Courses h = table.getSelectionModel().getSelectedItem();
					System.out.println(h.getLastName());

					String changedValue = t.getNewValue();
					System.out.println(changedValue);

					try {
						client.toServer(ClientMethods.courseChange(h.getFirstName(), changedValue));
					} catch (IOException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < data.size(); i++) {
						if (data.get(i).getLastName().equals(h.getLastName())) {
							data.get(i).setLastName(changedValue);
						}
					}
				}
			});

			// Table gets the info from observiable list of strings data:
			table.setItems(data);
			// Add columns to table
			table.getColumns().addAll(courseCol, gradeCol);
			table.getStyleClass().add("table");

			final TextField addCourse = new TextField();
			addCourse.setPromptText("Course");
			final TextField addGrade = new TextField();
			addGrade.setPromptText("Description");
			final TextField addHelp = new TextField();
			addHelp.setPromptText("Offer or need help?");

			addCourse.setMaxWidth(200);
			addGrade.setMaxWidth(200);
			addHelp.setMaxWidth(200);

			/**
			 * HERE WE DEFINE HOW TO ADD INFO TO TABLE AND USER DATABASE:
			 */
			Button addButton = new Button("Add");
			addButton.getStyleClass().add("toevoeg");
			addButton.setOnAction(e -> {
				// GET info to be added from textbox:
				String addCour = addCourse.getText().trim();
				String addDesc = addGrade.getText().trim();
				if (addCour.equals("") || addDesc.equals("")) {
					System.out.println("Empty ");
				} else {
					try {
						// IF THE INPUT ISNT EMPTY, THE WE USE
						// client.toServer("INCOMING-COURSECHANGE¿"etc. to
						// change a users info in database.
						client.toServer(ClientMethods.courseChange(addCour, addDesc));
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					// We add to table
					data.add(new Courses(addCourse.getText(), addGrade.getText(), ""));

					table.setItems(data);
					System.out.println(table.getItems());
					table.visibleProperty().set(true);

					addCourse.clear();
					addGrade.clear();
					addHelp.clear();
				}
			});

			/**
			 * Here we can delete a course from table and user database with
			 * client.toServer("INCOMING-COURSEREMOVE¿"+deleteCourse); and using
			 * selectors of table to select which item to delete.
			 */
			final Button deleteButton = new Button("Delete");
			deleteButton.getStyleClass().add("toevoeg");
			deleteButton.setOnAction(e -> {
				Courses selectedItem = table.getSelectionModel().getSelectedItem();
				table.getItems().remove(selectedItem);
				String deleteCourse = selectedItem.getFirstName();

				System.out.println(deleteCourse);

				try {
					client.toServer(ClientMethods.courseRemove(deleteCourse));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});

			hb.getChildren().clear();
			hb.getChildren().addAll(addCourse, addGrade, addButton, deleteButton);

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
			 * A help button. The set action must be linked to a new page.
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
			 * A logout button. 
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


			// A box for help and logout buttons.
			HBox helpout = new HBox();
			helpout.getChildren().addAll(help, logout);
			helpout.getStyleClass().add("helpoutbox");

			/**
			 * A picture for profile page.
			 */
			Image prfoto = new Image(getClass().getResourceAsStream("huisteken.jpg"));
			ImageView profileimage = new ImageView(prfoto);
			Button profile = new Button("Profile", profileimage);
			profile.getStyleClass().add("profile");
			profile.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					try {
						profileTab(primaryStage, scenetest, rootProfileTabe, sceneProfileTabe, sceneMatchTab,
								rootMatchTab, rootCourseTab, CourseScene);
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			});
			
			//A picture that belongs to course tab.
			Image crfoto = new Image(getClass().getResourceAsStream("courseteken.jpg"));
			ImageView coursefoto = new ImageView(crfoto);
			Button courses = new Button("Courses", coursefoto);
			courses.getStyleClass().add("courses");

			/**
			 * A picture that belong to match button.
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
			 * A picture that belongs to the settings button
			 */
			Image setfoto = new Image(getClass().getResourceAsStream("settingsteken.jpg"));
			ImageView settingsfoto = new ImageView(setfoto);
			Button settings = new Button("Settings", settingsfoto);
			settings.getStyleClass().add("settings");
			settings.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {

					settingsTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
							rootCourseTab, CourseScene);
				}
			});

			//A VBox for all the button links on the page.
			VBox overzicht = new VBox();
			overzicht.getChildren().addAll(profile, courses, match, settings);
			overzicht.getStyleClass().add("overzicht");
			overzicht.setMinHeight(630);

			//A picture with the logo. 
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

			rootCourse.getChildren().addAll(label, courseoverview, table, hb, boven, picaview, imgview, helpout,
					overzicht);

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

			Scene CourseScenee = new Scene(rootCourse, 1600, 900);
			primaryStage.setScene(CourseScenee);
			CourseScenee.getStylesheets().add("design.css");
			primaryStage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * MATCH TAB, THIS IS WHERE THE ESSENTIAL TRANSLATION CODE (JSON DATA FROM
	 * SERVER TO DISPLAY VARIABLES) WHICH ARE TESTABLE ARE. LOADS OF PARSING
	 * CODES, THESE SHOULD BE PUT INTO THEIR OWN PARSING METHODS WHICH ARE
	 * CALLED FROM THE MATCH TAB. 
	 */
	public void matchTab(final Stage primaryStage, final Scene sceneMatchTab, Pane rootMatchTab, Scene sceneProfileTabe,
			final Scene scenetest, Pane rootProfileTabe, Pane rootCourseTab, Scene CourseScene) {

		Label noResults = new Label("No results were found.");
		Button matchButton = new Button("Make match with selected");
		CheckBox UrgentCheck = new CheckBox("Urgent?");
		int countMatch = 0;
		ListView<String> SearchResultList = new ListView<String>();
		ListView<String> filterTable = new ListView<String>();

		// here a few operations are performed for positioning/visibility of
		// button and tables:
		filterTable.getItems().clear();
		SearchResultList.getItems().clear();
		filterTable.setMinHeight(450);
		SearchResultList.setMinHeight(450);

		TextField enterSearch = new TextField();
		enterSearch.setLayoutX(1125);
		enterSearch.setLayoutY(180);
		enterSearch.getStyleClass().add("label1");

		SearchResultList.setMinWidth(540);
		matchButton.setVisible(false);
		matchButton.getStyleClass().add("buttonspecific");

		// Hbox for urgent checkbox
		HBox UrgentBox = new HBox();
		UrgentBox.getChildren().addAll(UrgentCheck);
		UrgentBox.setSpacing(10);
		UrgentBox.getStyleClass().add("checkboxBox");
		UrgentBox.setVisible(false);

		/**
		 * Mark checkbox on and of (available and unavailable)
		 */
		UrgentCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				urgency = newValue;
			}
		});
		noResults.getStyleClass().add("noResults");

		ArrayList<String> SearchResultInput = new ArrayList<String>();

		//Initializing the results when not visible.
		noResults.setVisible(false);

		SearchResultList.getStyleClass().add("SearchResults");

		// If user is available it displays accordingly.
		boolean userAvailability = false;
		try {
			String tempA = client.toServer(ClientMethods.get("Available"));
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
		 * A button help.
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
		 * A button logout.
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
		
		// A box for help and logout 
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
				try {
					profileTab(primaryStage, scenetest, rootProfileTabe, sceneProfileTabe, sceneMatchTab, rootMatchTab,
							rootCourseTab, CourseScene);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		/**
		 * A picture that belongs to course button
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
		 * A picture that belongs to match button
		 */
		Image mfoto = new Image(getClass().getResourceAsStream("matchteken.jpg"));
		ImageView matchfoto = new ImageView(mfoto);
		Button match = new Button("Match", matchfoto);
		match.getStyleClass().add("match");

		/**
		 * A picture that belongs to settings button
		 */
		Image setfoto = new Image(getClass().getResourceAsStream("settingsteken.jpg"));
		ImageView settingsfoto = new ImageView(setfoto);
		Button settings = new Button("Settings", settingsfoto);
		settings.getStyleClass().add("settings");
		settings.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				settingsTab(primaryStage, sceneMatchTab, rootMatchTab, sceneProfileTabe, scenetest, rootProfileTabe,
						rootCourseTab, CourseScene);
			}
		});

		// A Box for all the buttons on the page
		VBox overzicht = new VBox();
		overzicht.getChildren().addAll(profile, courses, match, settings);
		overzicht.getStyleClass().add("overzicht");
		overzicht.setLayoutX(50);
		overzicht.setLayoutY(100);
		overzicht.setMinHeight(630);

		// add bovenstuk
		Image logos = new Image("log.jpg");
		ImageView imgviews = new ImageView(logos);
		Image mottos = new Image("motto.jpg");
		ImageView picaviews = new ImageView(mottos);

		HBox bovens = new HBox();
		bovens.setMinSize(1550, 85);
		bovens.getStyleClass().add("bovenstuk");

		bovens.setLayoutX(50);
		picaviews.setLayoutX(730);
		picaviews.setLayoutY(30);

		imgviews.setLayoutX(50);
		imgviews.setLayoutY(25);

		/**
		 * VIEW PROFILE BUTTON, CLICK A USER YOU MATCH WITH THEN PRESS BUTTON
		 * FOR POP UP WITH RESPECTIVE INFO
		 **/
		Button viewProfile2 = new Button("View Selected Profile");
		viewProfile2.getStyleClass().add("viewProfile");
		viewProfile2.setLayoutX(1020);
		viewProfile2.setLayoutY(700);
		viewProfile2.setVisible(false);
		/**
		 * VIEW PROFILE BUTTON THIS NEEDS REFACTORING! WE PARSE THE INCOMING
		 * MATCHES INFO INTO RESPECTIVE PLACES FOR DISPLAY. THE PARSING MUST BE
		 * DONE OUTSIDE GUI.
		 **/
		viewProfile2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				// DETERMINE WHO IS LOGGED IN WITH GETTING CURRENT EMAIL
				String curEmail = "";
				try {
					curEmail = client.toServer(ClientMethods.get("Email"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// GET THE NAME OF THE PROFILE WE WANT TO VIEW
				// (.getSelectedItem() from table)
				String emailSelect = filterTable.getSelectionModel().getSelectedItem();

				if (emailSelect.equals("No results..") || filterTable.getSelectionModel().getSelectedItem().isEmpty()) {
					System.out.println("No results");
				} else {
					// WE USE A SCANNER TO GRAB THE NAME, FOR EXAMPLE TO GET RID
					// OF 'NAME' IN "NAME:PIM" AND TO JUST HAVE "PIM"
					Scanner sc = new Scanner(emailSelect);
					sc.useDelimiter("\n");
					emailSelect = sc.next().substring(7);

					String[] userInfo = parser.parseUserInfo(client, emailSelect);

					// WE GET ALL OTHER INFO OF SELECTED USER WITH
					// (client.toServer("INCOMING-FROMOTHERSGET¿) LIKE BELOW:
					String n = userInfo[0];
					String sur = userInfo[1];
					String a = userInfo[2];
					String c = userInfo[3];
					String co = userInfo[4];
					String un = userInfo[5];
					String st = userInfo[6];
					String incomingCourses = userInfo[7];
					String num = userInfo[8];
					String email = userInfo[9];
					String piclink = userInfo[10];

					// PLACE THE INFO IN THE LABELS:
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
					Label description = new Label(userInfo[11]);
					description.getStyleClass().add("e");
					description.setWrapText(true);

					ListView<String> tempC = new ListView<String>();
					tempC.setMaxHeight(130);
					if (incomingCourses.equals("{}")) {
						incomingCourses = "No results..";
						tempC.getItems().add(incomingCourses);
					} else {
						parser.parseCourses(incomingCourses, tempC);
					}

					Label courses = new Label();
					courses.setText("Courses:            ");
					courses.getStyleClass().add("e");

					// CREATE RESPECTIVE LABELS AND BOXES FOR DISPLAY PURPOSES

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
					view.getChildren().addAll(view1, description, courses, tempC);
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

					// PUT ALL ABOVE LABELS IN A POPUP
					Popup pop = PopupBuilder.create().content(view).y(250).x(850).width(0).height(0).build();

					closePop.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent args) {
							pop.hide();
						}
					});

					pop.show(primaryStage);
					// WE SEND A REQUEST TO SERVER ASKING FOR OUR OWN (THE ONE
					// LOGGED IN) EMAIL, THIS RETURNS CONTROL TO THE USER BEING
					// LOGGED IN SO WE
					// DO FURTHER REQUEST OUT OF USERS PERSPECTIVE.
					try {
						client.toServer(ClientMethods.getOther(curEmail, "Piclink"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		/**
		 * READ comments by viewProfile2 for explanation what this button does,
		 * as they both are similar: NEEDS REFACTORING SAME WAY AS viewProfile2
		 * button
		 */
		Button viewProfile = new Button("View Selected Profile");
		viewProfile.getStyleClass().add("viewProfile");
		viewProfile.setLayoutX(350);
		viewProfile.setLayoutY(700);
		viewProfile.setVisible(false);
		viewProfile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				String curEmail = "";
				try {
					curEmail = client.toServer(ClientMethods.get("Email"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// Get name of person to view profile of.
				String emailSelect = SearchResultList.getSelectionModel().getSelectedItem();
				System.out.println("No results....." + emailSelect);
				if (emailSelect.equals("No results..")
						|| SearchResultList.getSelectionModel().getSelectedItem().isEmpty()) {
					System.out.println("No results....." + emailSelect);
				} else {
					Scanner sc = new Scanner(emailSelect);
					sc.useDelimiter("\n");
					emailSelect = sc.next().substring(7);
					String[] userInfo = parser.parseUserInfo(client, emailSelect);

					// WE GET ALL OTHER INFO OF SELECTED USER WITH
					// (client.toServer("INCOMING-FROMOTHERSGET¿) LIKE BELOW:
					String n = userInfo[0];
					String sur = userInfo[1];
					String a = userInfo[2];
					String c = userInfo[3];
					String co = userInfo[4];
					String un = userInfo[5];
					String st = userInfo[6];
					String incomingCourses = userInfo[7];
					String num = userInfo[8];
					String email = userInfo[9];
					String piclink = userInfo[10];

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
					Label description = new Label(userInfo[11]);
					description.getStyleClass().add("e");
					description.setWrapText(true);
					ListView<String> tempC = new ListView<String>();
					tempC.setMaxHeight(130);
					if (incomingCourses.equals("{}")) {
						incomingCourses = "No results..";
						tempC.getItems().add(incomingCourses);
					} else {
						parser.parseCourses(incomingCourses, tempC);
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
					view.getChildren().addAll(view1, description, courses, tempC);
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
					// Return control
					try {
						client.toServer(ClientMethods.getOther(curEmail, "Piclink"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		/**
		 * FIND MATCHES BY SEARCHING BUTTON. WILL DISPLAY MATCHES ACCORDING TO
		 * SEARCH PREFERENCES. NEEDS REFACTORING FOR HOW WE PARSE INCOMING INFO
		 * FROM SERVER:
		 */
		Button searchCourse = new Button("Search");
		searchCourse.getStyleClass().add("courseSearch");
		searchCourse.setLayoutX(1309);
		searchCourse.setLayoutY(180);
		searchCourse.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				filterTable.getItems().clear();
				if (!(enterSearch.getText().equals(""))) {
					// DECIDE WHAT OPTION/PREFERENCES WE CHOSE TO SEARCH WITH:
					String currentOption = SearchOptions.getValue();
					String currentValue = enterSearch.getText();

					if (currentOption.equals("City")) {
						currentOption = "CityOfResidence";
					} else if (currentOption.equals("Institutes")) {
						currentOption = "CurrentUniversity";
					} else {
						currentOption = "Course list";
					}

					// set up variables for parsing of temp:
					String temp = "";
					String results = "";
					String curEmail = "";
					ArrayList<String> matchEmails = new ArrayList<String>();
					ArrayList<String> matchInfo = new ArrayList<String>();

					// HERE WE RECEIVE ALL MATCHES BY SEARCH USING: "
					// client.toServer("INCOMING-SEARCH¿" + currentOption + "¿"
					// + currentValue);"
					try {
						curEmail = client.toServer(ClientMethods.get("Email"));
						temp = client.toServer(ClientMethods.search(currentOption, currentValue));
					} catch (IOException e) {
						e.printStackTrace();
					}

					/**
					 * MAIN TIP FOR REFACTORING, THE temp VARIABLE above
					 * receives all people and their details as JSON OBJECT.
					 * Below with use of scanner and primitive parsing
					 * technqiues we grab all emails of matches out of that JSON
					 * String for display. Look at what temp looks like and
					 * implement a parsing method elsewhere or refactor code.
					 * 
					 */
					if (temp.equals("[]")) {
						System.out.println("No Results..");
					} else {

						parser.parseMatchedEmails(temp, matchEmails, curEmail);

						// Get info of matched emails using the command to get
						// other peoples info
						// client.toServer("INCOMING-FROMOTHERSGET¿" + s +
						// "¿Email")
						// + "\nName: " +
						// client.toServer("INCOMING-FROMOTHERSGET
						for (String s : matchEmails) {
							String userCourses = "";
							try {
								userCourses = client.toServer(ClientMethods.getOther(s, "Course list"));
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							if (userCourses.equals("{}")) {

								results = parser.parseMatches(client, s, "", "");
								matchInfo.add(results);
							} else {
								// PARSE MATCHES COURSES:

								String displayCourses = (String) parser.parseCourses(userCourses.toString(), "");
								String available = parser.parseAvailability(client, s);

								results = parser.parseMatches(client, s, displayCourses, available);
								matchInfo.add(results);
							}
						}

						// Display THE INFO OF MATCHES IN THE TABLE:
						for (String j : matchInfo) {
							if (j.contains(curEmail)) {
								System.out.println(curEmail + "Exists");
							} else {
								filterTable.getItems().add(j);
							}
						}

						// RETURN CONTROL TO USER LOGGED IN:
						try {
							curEmail = client.toServer(ClientMethods.getOther(curEmail, "Firstname"));
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

		// MATCHNOW BUTTON, FINDS AND DISPLAYS ALL MATCHES 
		Button matchNow = new Button("Match Now!");
		matchNow.getStyleClass().add("matchbut");
		matchNow.setLayoutX(470);
		matchNow.setLayoutY(130);
		/**
		 * WHEN WE CLICK ON MATCHNOW:
		 */
		matchNow.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent args) {
				SearchResultList.getItems().clear();
				String usersMatch = "";
				String curEmail = "";
				String results = "";
				try {
					// GET USER LOGGED IN INFO TO SEARCH FOR SIMILAR MATCHES
					curEmail = client.toServer(ClientMethods.get("Email"));
					String userStudy = client.toServer(ClientMethods.get("CurrentStudy"));
					String userUni = client.toServer(ClientMethods.get("CurrentUniversity"));
					String userCity = client.toServer(ClientMethods.get("CityOfResidence"));
					/**
					 * WE USE client.toServer("INCOMING-MATCH" method to get all
					 * matches . variable usersMatch will contain all other
					 * matches info which we parse below.
					 */
					usersMatch = client.toServer(ClientMethods.match(userStudy, userUni, userCity));

				} catch (IOException e) {
					e.printStackTrace();
				}
				ArrayList<String> matchEmails = new ArrayList<String>();
				ArrayList<String> matchNames = new ArrayList<String>();

				if (usersMatch.equals("[]")) {
					System.out.println("No Results#####");
					SearchResultList.getItems().add("No Results..");
				} else {

					parser.parseMatchedEmails(usersMatch, matchEmails, curEmail);

					for (String s : matchEmails) {
						// GET INFO OF USERS TO DISPLAY USING:
						// client.toServer("INCOMING-FROMOTHERSGET
						String userCourses = "";
						try {
							userCourses = client.toServer(ClientMethods.getOther(s, "Course list"));

						} catch (IOException e1) {
							e1.printStackTrace();
						}
						if (userCourses.equals("{}")) {
							System.out.println("empty");
							results = parser.parseMatches(client, s, "", "");
							matchNames.add(results);
						} else {
							String displayCourses = (String) parser.parseCourses(userCourses.toString(), "");
							String available = parser.parseAvailability(client, s);

							results = parser.parseMatches(client, s, displayCourses, available);

							matchNames.add(results);
						}
					}

					// Reset login, RETURN CONTROL TO USER
					try {
						curEmail = client.toServer(ClientMethods.getOther(curEmail, "Firstname"));
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

		/**
		 * A Checkbox for availability
		 */
		CheckBox SearchAvailableNow = new CheckBox("Available now?");
		SearchAvailableNow.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				try {
					client.toServer(ClientMethods.change("Available", String.valueOf(newValue)));
					System.out.println(newValue);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		SearchAvailableNow.setSelected(userAvailability);

		// Giving locations
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

		rootMatchTabe.getChildren().addAll(checkLabel, checkLabel2, checkLabel3, expl2, expl, viewProfile2, filterTable,
				SearchAvailableNow, enterSearch, line2, searchCourse, viewProfile, line, UrgentBox, SearchOptions,
				/* SearchOptionsBox, SearchBox, */ matchButton, /* SearchButton, */noResults, SearchResultList,
				overzicht, bovens, picaviews, imgviews, helpout, matchNow);
		countMatch++;

		Scene sceneeMatchTab = new Scene(rootMatchTabe, 1600, 900);
		primaryStage.setScene(sceneeMatchTab);
		sceneeMatchTab.getStylesheets().add("Match.css");
		primaryStage.show();
	}

	/**
	 * SETTING TAB. NOT VERY IMPORTANT.
	 */
	public void settingsTab(final Stage primaryStage, final Scene sceneMatchTab, Pane rootMatchTab,

			Scene sceneProfileTabe, final Scene scenetest, Pane rootProfileTabe, Pane rootCourseTab,
			Scene CourseScene) {
		
		/**
		 * A help button.
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
		 * A logout button.
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
		 * A box for help and logout buttons.
		 */
		HBox helpout = new HBox();
		helpout.getChildren().addAll(help, logout);
		helpout.getStyleClass().add("helpoutbox");
		helpout.setLayoutX(1410);
		helpout.setLayoutY(25);

		/**
		 * A picture that belongs to profile button.
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
		 * A picture that belongs to course button. 
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
		 * A picture that belongs to match button.
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

		// A picture for settings
		Image setfoto = new Image(getClass().getResourceAsStream("settingsteken.jpg"));
		ImageView settingsfoto = new ImageView(setfoto);
		Button settings = new Button("Settings", settingsfoto);
		settings.getStyleClass().add("settings");

		//A Vbox for all the button on the page
		VBox overzicht = new VBox();
		overzicht.getChildren().addAll(profile, courses, match, settings);
		overzicht.getStyleClass().add("overzicht");
		overzicht.setLayoutX(50);
		overzicht.setLayoutY(100);
		overzicht.setMinHeight(630);

		// Add bovenstuk
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
				if (!(checkOld.equals("") || neww.equals(""))) {

					String old = null;
					try {
						old = client.toServer(ClientMethods.get("Password"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					if (old.equals(checkOld)) {
						try {
							client.toServer(ClientMethods.change("Password", neww));
						} catch (IOException e) {
							e.printStackTrace();
						}
						fail.setVisible(false);
						oldPasswordField.clear();
						newPasswordField.clear();
						success.setText("Password Changed!");
						success.setVisible(true);
					} else {
						fail.setVisible(true);
					}

				} else {
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
				if (!checkCur.equals("")) {

					String old = null;
					String curEmail = null;
					try {
						old = client.toServer(ClientMethods.get("Password"));
						curEmail = client.toServer(ClientMethods.get("Email"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					if (old.equals(checkCur)) {
						try {
							client.toServer(ClientMethods.removeAccount(curEmail));
						} catch (IOException e) {
							e.printStackTrace();
						}
						fail.setVisible(false);
						try {
							start(primaryStage);
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else {
						fail.setVisible(true);
					}
				} else {
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
		root.getChildren().addAll(enter, success, fail, line, old, neww, passwordField, changePassword, deleteAccount,
				deleteAccount1, oldPasswordField, newPasswordField, confirmPasswordChange, deleteAccountBttn, overzicht,
				bovens, picaviews, imgviews, helpout);
		root.getStyleClass().add("Background");
		Scene OptiesScene = new Scene(root, 1600, 900);
		primaryStage.setScene(OptiesScene);

		OptiesScene.getStylesheets().add("Opties.css");
		primaryStage.show();
	}

	/**
	 * HELP TAB. NOT VERY IMPORTANT
	 */
	public void helpTab(final Stage primaryStage, final Scene sceneMatchTab, Pane rootMatchTab, Scene sceneProfileTabe,
			final Scene scenetest, Pane rootProfileTabe, Pane rootCourseTab, Scene CourseScene) {

		int countHelp = 0;
		TreeView<String> totLijst;

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
		 * A box for help and logout buttons.
		 */
		HBox helpout = new HBox();
		helpout.getChildren().addAll(help, logout);
		helpout.getStyleClass().add("helpoutbox");

		/**
		 * A picture that belongs to profile button.
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
		 * A picture that belongs to course button
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
		 * A picture that belongs to match button.
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
		 * A picture that belongs to settings button.
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
		 * A Vbox for all the buttons on the page.
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

		//A picture with logo
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
	 * THIS IS AN EXAMPLE OF A METHOD WHICH IS NOT INTERGRATED INTO THE TABS'S
	 * CODES. THIS IS WHAT YOU NEED TO GET OUT FROM THE TABS SO YOU CAN TEST
	 * THEM.
	 */
	public TreeItem<String> onderdeel(String string, TreeItem<String> ouder) {

		TreeItem<String> item = new TreeItem<>(string);
		item.setExpanded(true);
		ouder.getChildren().add(item);
		return item;

	}

	public void constructEdit(String desc, String standard, String key, Label edited, Stage primary) {
		try {
			TextField temp = new TextField();
			temp.getStyleClass().add("fillbox");
			Label description = new Label(desc);
			description.getStyleClass().add("description");
			Popup pop = PopupBuilder.create().content(temp).y(MouseInfo.getPointerInfo().getLocation().getY() - 10)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			Popup pop2 = PopupBuilder.create().content(description)
					.y(MouseInfo.getPointerInfo().getLocation().getY() + 30)
					.x(MouseInfo.getPointerInfo().getLocation().getX() - 20).width(0).height(0).build();
			temp.setText(client.toServer(ClientMethods.get(key)));
			temp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent args) {
					try {
						// newinfo is variable with edited info
						String newInfo = temp.getText();
						if (newInfo.trim().isEmpty()) {
							if (edited != null)
								edited.setText(standard);
							temp.setText(standard);
							client.toServer(ClientMethods.change(key, standard));
						} else {
							if (edited != null)
								edited.setText(newInfo);
							temp.setText(newInfo);
							client.toServer(ClientMethods.change(key, newInfo));
						}
						pop.hide();
						pop2.hide();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			pop.show(primary);
			pop2.show(primary);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}