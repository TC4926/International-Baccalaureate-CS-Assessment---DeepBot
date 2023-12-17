package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class NotificationControl {
		@FXML
		private Button UndoButton;
		
		@FXML
		private Button AddNotiButton;
		
		@FXML
		private TableColumn<?, ?> MondayColumn;
		
		@FXML
	    private TableColumn<?, ?> TuesdayColumn;
		
		@FXML
		 private TableColumn<?, ?> wednesdayColumn;
		
		@FXML
		 private TableColumn<?, ?> ThursdayColumn;
		
		@FXML
		private TableColumn<?, ?> FridayColumn;

		@FXML
		private TableColumn<?, ?> SaturdayColumn;

		@FXML
		private TableColumn<?, ?> SundayColumn;

	    @FXML
	    private TableColumn<?, ?> TimeColumn;

	    @FXML
	    private TableColumn<?, ?> TitleColumn;

	    @FXML
	    private TableView<?> table;
		
	     
		public void ClickUndo(ActionEvent event) throws Exception{
			checkUndo();
		}
		public void ClickAddNotiButton(ActionEvent event) throws Exception{
			checkAddNoti();
		}
		private void checkUndo() throws IOException{
			Main n = new Main();
			n.changeScene("ChoosePath.fxml");
		}
		private void checkAddNoti() throws IOException{
			Main b = new Main();
			b.changeScene("AddNotification.fxml");
		}
}


