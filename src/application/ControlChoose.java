package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ControlChoose {
		
		@FXML
		private Button GoNotification;
		@FXML
	    private Button DirGraph;
		
		public void GoGraph(ActionEvent event) throws Exception{
			checkGraph();
		}
		
		public void ClickNoti(ActionEvent event) throws Exception{
			checkNoti();
		}
		
		private void checkNoti() throws IOException{
			Main n = new Main();
			n.changeScene("AddNotification.fxml");
		}
		
		private void checkGraph() throws IOException{
			Main b = new Main();
			b.changeScene("SelectGraph.fxml");
		}
}
