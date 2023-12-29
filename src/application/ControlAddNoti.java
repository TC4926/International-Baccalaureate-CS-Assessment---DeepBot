package application;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import tray.notification.TrayNotification;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.net.URL;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ResourceBundle;
import java.sql.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.ListView;
import javafx.scene.control.TextFormatter;
import org.controlsfx.control.Notifications;
import javafx.scene.control.TableRow;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.Locale;



public class ControlAddNoti implements Initializable{
	
	@FXML
    private TableView<Notification> table;
	
	@FXML
	private TableColumn<Notification, Integer> TimeColumn;
	
	@FXML
	private TableColumn<Notification, String> TitleColumn;
	
	@FXML
	private TableColumn<Notification, Boolean> MondayColumn;
	
	@FXML
    private TableColumn<Notification, Boolean> TuesdayColumn;
	
	@FXML
    private TableColumn<Notification, Boolean> wednesdayColumn;
	
    @FXML
    private TableColumn<Notification, Boolean> ThursdayColumn;
    
    @FXML
    private TableColumn<Notification, Boolean> FridayColumn;
	
    @FXML
    private TableColumn<Notification, Boolean> SaturdayColumn;
    
    @FXML
    private TableColumn<Notification, Boolean> SundayColumn;
	
	@FXML
    private Button DeleteRow;
	
	@FXML
    private Button AddNotification;

	@FXML
	private RadioButton rbMonday;

    @FXML
    private TextField NotiTime;

    @FXML
    private TextField NotiTitle;

    @FXML
    private RadioButton rbTuesday;

    @FXML
    private RadioButton rbFriday;

    @FXML
    private RadioButton rbSaturday;

    @FXML
    private RadioButton rbSunday;

    @FXML
    private RadioButton rbThursday;

    @FXML
    private RadioButton rbWednesday;
    
    @FXML
    private Button UpdateRow;
    
    @FXML
    private Button UndoButton;
    
    public void ClickUndo(ActionEvent event) throws Exception{
		checkUndo();
	}
    
    private void checkUndo() throws IOException{
		Main z = new Main();
		z.changeScene("ChoosePath.fxml");
	}

Connection con; //remove if didn't work
PreparedStatement pst; //remove if didn't work
int myIndex;
int id;
    
    @FXML
    void Update(ActionEvent event) {
       
        String Title;
        
        int Time, UpdateTime;
        
        Boolean Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
        
         myIndex = table.getSelectionModel().getSelectedIndex();
         
            Time = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getTime()));
            
            UpdateTime = Integer.parseInt(NotiTime.getText());
            
            Title = NotiTitle.getText();
            Monday = rbMonday.isSelected();
            Tuesday = rbTuesday.isSelected();
            Wednesday = rbWednesday.isSelected();
            Thursday = rbThursday.isSelected();
            Friday = rbFriday.isSelected();
            Saturday = rbSaturday.isSelected();
            Sunday = rbSunday.isSelected();
        try 
        {
            pst = con.prepareStatement("update Notification set Time = ? ,Title = ?,Monday = ? ,Tuesday = ? ,Wednesday = ?,Thursday = ?,Friday = ?,Saturday = ?,Sunday = ? where Time = ? ");
            pst.setInt(1, UpdateTime);
            pst.setString(2, Title);
            pst.setBoolean(3, Monday);
            pst.setBoolean(4, Tuesday);
            pst.setBoolean(5, Wednesday);
            pst.setBoolean(6, Thursday);
            pst.setBoolean(7, Friday);
            pst.setBoolean(8, Saturday);
            pst.setBoolean(9, Sunday);
            pst.setInt(10, Time);
      
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DeepBot");
        
        alert.setHeaderText("DeepBot");
        alert.setContentText("Notification Successfully Updated!");
        alert.showAndWait();
                table();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    void Add(ActionEvent event) {
    	
    	String Title;
    	int Time;
    	boolean Monday;
    	boolean Tuesday;
    	boolean wednesday;
    	boolean Thursday;
    	boolean Friday;
    	boolean Saturday;
    	boolean Sunday;
    	
    	Time = Integer.parseInt(NotiTime.getText()); 
    	Title = NotiTitle.getText();
    	
    	Monday = rbMonday.isSelected();
    	Tuesday = rbTuesday.isSelected();
    	wednesday = rbWednesday.isSelected();
    	Thursday = rbThursday.isSelected();
    	Friday = rbFriday.isSelected();
    	Saturday = rbSaturday.isSelected();
    	Sunday = rbSunday.isSelected();
    	
    	try 
        {
            pst = con.prepareStatement("insert into notification(Time,Title,Monday,Tuesday,wednesday,Thursday,Friday,Saturday,Sunday)values(?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, Time);
            pst.setString(2, Title);
            pst.setBoolean(3, Monday);
            pst.setBoolean(4, Tuesday);
            pst.setBoolean(5, wednesday);
            pst.setBoolean(6, Thursday);
            pst.setBoolean(7, Friday);
            pst.setBoolean(8, Saturday);
            pst.setBoolean(9, Sunday);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DeepBot");
            alert.setHeaderText("Deepbot");
            alert.setContentText("Notification Setting Successfully Added.");
            alert.showAndWait();
            table();
            
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ControlAddNoti.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	 long time = Time;
    	 long hours = time / 100;
    	 long minutes = time % 100;
    	 long SetTime = (hours * 3600000) + (minutes * 60000); 
    	 
    	 TimeZone.setDefault(TimeZone.getTimeZone("Asia/Taipei"));
    	 SimpleDateFormat CurrentDay = new SimpleDateFormat("EEEE", Locale.ENGLISH);
    	 Date date = new Date();
    	 SimpleDateFormat CurrentHour = new SimpleDateFormat("HH");
    	 Date date1 = new Date();
    	 SimpleDateFormat CurrentMinute = new SimpleDateFormat("mm");
    	 Date date2 = new Date();
    	 SimpleDateFormat CurrentSecond = new SimpleDateFormat("ss");
    	 Date date3 = new Date();
    	 
    	String current_day = String.valueOf(CurrentDay.format(date));
        long current_hour = Integer.parseInt(CurrentHour.format(date1));
    	long current_minute = Integer.parseInt(CurrentMinute.format(date2));
    	long current_second = Integer.valueOf(CurrentSecond.format(date3));
    	
    	long CurrentTime = (current_hour * 3600000) + (current_minute * 60000) + (current_second * 1000);
    	
    	long ActualTime = SetTime - CurrentTime;
    	System.out.println(current_day); 
    	if(ActualTime > 0 && Monday == true && current_day.equals("Monday")){
    		Timer timer = new Timer();
        	TimerTask timertask = new TimerTask() {
        		@Override
        		public void run() {
        		Platform.runLater( () -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(NotiTitle.getText());
                    alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                    alert.showAndWait();
                });
        	}
        	};
        	timer.schedule(timertask, ActualTime);
    	}else if (ActualTime > 0 && Tuesday == true && current_day.equals("Tuesday")) {
    		Timer timer = new Timer();
        	TimerTask timertask = new TimerTask() {
        		@Override
        		public void run() {
        		Platform.runLater( () -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(NotiTitle.getText());
                    alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                    alert.showAndWait();
                });
        	}
        	};
        	timer.schedule(timertask, ActualTime);
    	
    	}else if(ActualTime > 0 && wednesday == true && current_day.equals("Wednesday")) {
    		Timer timer = new Timer();
        	TimerTask timertask = new TimerTask() {
        		@Override
        		public void run() {
        		Platform.runLater( () -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(NotiTitle.getText());
                    alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                    alert.showAndWait();
                });
        	}
        	};
        	timer.schedule(timertask, ActualTime);
        
    	}else if(ActualTime > 0 && Thursday == true && current_day.equals("Thursday")) {
    		Timer timer = new Timer();
        	TimerTask timertask = new TimerTask() {
        		@Override
        		public void run() {
        		Platform.runLater( () -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(NotiTitle.getText());
                    alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                    alert.showAndWait();
                });
        	}
        	};
        	timer.schedule(timertask, ActualTime);
    	}else if(ActualTime > 0 && Friday == true && current_day.equals("Friday")) {
    		Timer timer = new Timer();
        	TimerTask timertask = new TimerTask() {
        		@Override
        		public void run() {
        		Platform.runLater( () -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(NotiTitle.getText());
                    alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                    alert.showAndWait();
                });
        	}
        	};
        	timer.schedule(timertask, ActualTime);
    	}else if(ActualTime > 0 && Saturday == true && current_day.equals("Saturday")) {
    		Timer timer = new Timer();
        	TimerTask timertask = new TimerTask() {
        		@Override
        		public void run() {
        		Platform.runLater( () -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(NotiTitle.getText());
                    alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                    alert.showAndWait();
                });
        	}
        	};
        	timer.schedule(timertask, ActualTime);
    	}else if (ActualTime > 0 && Sunday == true && current_day.equals("Sunday")) {
    		Timer timer = new Timer();
        	TimerTask timertask = new TimerTask() {
        		@Override
        		public void run() {
        		Platform.runLater( () -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle(NotiTitle.getText());
                    alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                    alert.showAndWait();
                });
        	}
        	};
        	timer.schedule(timertask, ActualTime);
    	
    	}
    }
   	
    	

    	
    //public void setAlert() {
    
    	//Timeline alertTimer = new Timeline(new KeyFrame(Duration.seconds(5), event ->{
            //showAlert();
        //}));
    //}
    //public void showAlert(){
      //      Platform.runLater( () -> {
        //        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          //      alert.setTitle("Alert!");
            //    alert.setContentText("This is an alert");
              //  Optional<ButtonType> result = alert.showAndWait();


                //if(result.isEmpty()){
                  //  System.out.println("Alert closed");
                    
                //} else if(result.get() == ButtonType.OK){
                  //  System.out.println("OK!");
                    
                //} else if(result.get() == ButtonType.CANCEL){
                  //  System.out.println("Never!");
                    
                //}
            //});

 //       }
    
    
    @FXML
    void Delete(ActionEvent event) {
    	myIndex = table.getSelectionModel().getSelectedIndex();
        
        int Time = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getTime()));
                     
        try 
        {
            pst = con.prepareStatement("delete from Notification where Time = ? ");
            pst.setInt(1, Time);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deepbot");
        
        alert.setHeaderText("Deletion module");
        alert.setContentText("Notification successfully Deleted!");
        alert.showAndWait();
                  table();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void alertNoti() throws SQLException {
     pst = con.prepareStatement("select Time,Title,Monday,Tuesday,wednesday,Thursday,Friday,Saturday,Sunday from notification");
     ResultSet rs = pst.executeQuery();
     while(rs.next()) {
    	 int time = rs.getInt("Time");
    	 long hours = time / 100;
    	 long minutes = time % 100;
    	 long SetTime = (hours * 3600000) + (minutes * 60000); 
   	 
    	 TimeZone.setDefault(TimeZone.getTimeZone("Asia/Taipei"));
    	 SimpleDateFormat CurrentDay = new SimpleDateFormat("EEEE");
    	 Date date = new Date();
    	 SimpleDateFormat CurrentHour = new SimpleDateFormat("HH");
    	 Date date1 = new Date();
    	 SimpleDateFormat CurrentMinute = new SimpleDateFormat("mm");
    	 Date date2 = new Date();
    	 SimpleDateFormat CurrentSecond = new SimpleDateFormat("ss");
    	 Date date3 = new Date();
   	 
    	 String current_day = String.valueOf(CurrentDay.format(date));
    	 long current_hour = Integer.parseInt(CurrentHour.format(date1));
   	 	long current_minute = Integer.parseInt(CurrentMinute.format(date2));
   	 	long current_second = Integer.valueOf(CurrentSecond.format(date3));
   	
   	 	long CurrentTime = (current_hour * 3600000) + (current_minute * 60000) + (current_second * 1000);
   	
   	 	long ActualTime = SetTime - CurrentTime;
   	
   	 	if(ActualTime > 0 && rs.getBoolean("Monday") == true && current_day.equals("Monday")) {
   	 		Timer timer = new Timer();
   	 		TimerTask timertask = new TimerTask() {
   	 			@Override
   	 			public void run() {
   	 				Platform.runLater( () -> {
   	 					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
   	 					alert.setTitle(NotiTitle.getText());
   	 					alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
   	 					alert.showAndWait();
   	 				});
   	 			}
   	 		};
   	 		timer.schedule(timertask, ActualTime);
	}else if (ActualTime > 0 && rs.getBoolean("Tuesday") == true && current_day.equals("Tuesday")) {
		Timer timer = new Timer();
    	TimerTask timertask = new TimerTask() {
    		@Override
    		public void run() {
    		Platform.runLater( () -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(NotiTitle.getText());
                alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                alert.showAndWait();
            });
    	}
    	};
    	timer.schedule(timertask, ActualTime);
	}else if(ActualTime > 0 && rs.getBoolean("wednesday") == true && current_day.equals("Wednesday")) {
		Timer timer = new Timer();
    	TimerTask timertask = new TimerTask() {
    		@Override
    		public void run() {
    		Platform.runLater( () -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(NotiTitle.getText());
                alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                alert.showAndWait();
            });
    	}
    	};
    	timer.schedule(timertask, ActualTime);
	}else if(ActualTime > 0 && rs.getBoolean("Thursday") == true && current_day.equals("Thursday")) {
		Timer timer = new Timer();
    	TimerTask timertask = new TimerTask() {
    		@Override
    		public void run() {
    		Platform.runLater( () -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(NotiTitle.getText());
                alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                alert.showAndWait();
            });
    	}
    	};
    	timer.schedule(timertask, ActualTime);
	}else if(ActualTime > 0 && rs.getBoolean("Friday") == true && current_day.equals("Friday")) {
		Timer timer = new Timer();
    	TimerTask timertask = new TimerTask() {
    		@Override
    		public void run() {
    		Platform.runLater( () -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(NotiTitle.getText());
                alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                alert.showAndWait();
            });
    	}
    	};
    	timer.schedule(timertask, ActualTime);
	}else if(ActualTime > 0 && rs.getBoolean("Saturday") == true && current_day.equals("Saturday")) {
		Timer timer = new Timer();
    	TimerTask timertask = new TimerTask() {
    		@Override
    		public void run() {
    		Platform.runLater( () -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(NotiTitle.getText());
                alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                alert.showAndWait();
            });
    	}
    	};
    	timer.schedule(timertask, ActualTime);
	}else if (ActualTime > 0 && rs.getBoolean("Sunday") == true && current_day.equals("Sunday")) {
		Timer timer = new Timer();
    	TimerTask timertask = new TimerTask() {
    		@Override
    		public void run() {
    		Platform.runLater( () -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(NotiTitle.getText());
                alert.setContentText("It is time for " + NotiTitle.getText() + ". Don't Forget!");
                alert.showAndWait();
            });
    	}
    	};
    	timer.schedule(timertask, ActualTime);
	}
 }
    } 
    	
   
    public void table() throws SQLException
    {
        Connect();
        ObservableList<Notification> notification = FXCollections.observableArrayList();
     try 
     {
         pst = con.prepareStatement("select Time,Title,Monday,Tuesday,wednesday,Thursday,Friday,Saturday,Sunday from notification");  
         ResultSet rs = pst.executeQuery();
    {
      while (rs.next())
      {
          Notification st = new Notification();
          st.setTime(rs.getInt("Time"));
          st.setTitle(rs.getString("Title"));
          st.setMonday(rs.getBoolean("Monday"));
          st.setTuesday(rs.getBoolean("Tuesday"));
          st.setwednesday(rs.getBoolean("wednesday"));
          st.setThursday(rs.getBoolean("Thursday"));
          st.setFriday(rs.getBoolean("Friday"));
          st.setSaturday(rs.getBoolean("Saturday"));
          st.setSunday(rs.getBoolean("Sunday"));
          notification.add(st);
     }
  } 
             table.setItems(notification);
             TimeColumn.setCellValueFactory(f -> f.getValue().TimeProperty().asObject());
             TitleColumn.setCellValueFactory(f -> f.getValue().TitleProperty());
             MondayColumn.setCellValueFactory(f -> f.getValue().MondayProperty());
             TuesdayColumn.setCellValueFactory(f -> f.getValue().TuesdayProperty());
             wednesdayColumn.setCellValueFactory(f -> f.getValue().wednesdayProperty());
             ThursdayColumn.setCellValueFactory(f -> f.getValue().ThursdayProperty());
             FridayColumn.setCellValueFactory(f -> f.getValue().FridayProperty());
             SaturdayColumn.setCellValueFactory(f -> f.getValue().SaturdayProperty());
             SundayColumn.setCellValueFactory(f -> f.getValue().SundayProperty());
             
     }
     
     catch (SQLException ex) 
     {
         Logger.getLogger(ControlAddNoti.class.getName()).log(Level.SEVERE, null, ex);
     }
              table.setRowFactory( tv -> {
           TableRow<Notification> myRow = new TableRow<>();
           myRow.setOnMouseClicked (event -> 
           {
              if (event.getClickCount() == 1 && (!myRow.isEmpty()))
              {
                  myIndex =  table.getSelectionModel().getSelectedIndex();
       
                 NotiTime.setText(String.valueOf(table.getItems().get(myIndex).getTime()));
                 NotiTitle.setText(table.getItems().get(myIndex).getTitle());
                 rbMonday.setSelected(table.getItems().get(myIndex).getMonday());
                 rbTuesday.setSelected(table.getItems().get(myIndex).getTuesday());
                 rbWednesday.setSelected(table.getItems().get(myIndex).getwednesday());
                 rbThursday.setSelected(table.getItems().get(myIndex).getMonday());
                 rbFriday.setSelected(table.getItems().get(myIndex).getFriday());
                 rbSaturday.setSelected(table.getItems().get(myIndex).getSaturday());
                 rbSunday.setSelected(table.getItems().get(myIndex).getSunday());
                 
              }
           });
              return myRow;
                 });
         alertNoti();
  
    }
	
    
    public void Connect()//remove if didn't work
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB","root","Ab0928121510");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		Connect();
		try {
			table();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}