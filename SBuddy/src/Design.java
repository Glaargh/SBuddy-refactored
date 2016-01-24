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
						Client.toServer("INCOMING-COURSECHANGE¿"+changeName+ "¿"+ changedValue);						
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
	
}
	
