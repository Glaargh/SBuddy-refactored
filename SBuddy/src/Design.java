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
import javafx.event.ActionEvent;
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
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;




public class Design extends Application {
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void coursesTab(final Stage primaryStage, final Scene sceneMatchTab, Pane rootMatchTab, Scene sceneProfileTabe,final Scene scenetest, Pane rootProfileTabe, Pane rootCourseTab, Scene CourseScene){
	   
			TableView<Courses> table = new TableView<Courses>();

		
		  /* String[] crse = courselist.keySet().toString().substring(1,courselist.keySet().toString().length()-1).split(",");
		   for (int i =0 ; i<crse.length; i++)
		   {
			   crse[i]= crse[i].trim() +"Â¿"+courselist.get(crse[i].trim());
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
        /*courseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Courses, String>>() {
                @Override
                public void handle(CellEditEvent<Courses, String> t) {
                 	String changeName = ((Courses) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).getFirstName();
                	
                 	String changedValue = t.getNewValue();
                	try {
						Client.toServer("INCOMING-COURSECHANGEÂ¿"+changeName+ "Â¿"+ changedValue);						
					} catch (IOException e) {
						e.printStackTrace();
					}
             
     
                    ((Courses) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setFirstName(t.getNewValue());
                }
            }
        );*/
 
 
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
						Client.toServer("INCOMING-COURSECHANGEÂ¿"+h.getFirstName()+ "Â¿"+ changedValue);						
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
     					Client.toServer("INCOMING-COURSECHANGEÂ¿"+addCour+ "Â¿"+ addDesc);
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
				Client.toServer("INCOMING-COURSEREMOVEÂ¿"+deleteCourse);
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
	
	public TreeItem<String> onderdeel(String string, TreeItem<String> ouder) {
		TreeItem<String> item = new TreeItem<>(string);
		item.setExpanded(true);
		ouder.getChildren().add(item);
		return item;

	}
	
	// TODO Auto-generated method stub
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
	
}
	
