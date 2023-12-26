package application;


import java.io.IOException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class ControlLinePromptSpecifications{
	
	private ControlSelectGraph controlSelectGraph;
	
	
	
	
    @FXML
    private TextField ChartTitle;
    
    @FXML
    private Label ChartID;
    
    @FXML
    private TextField IDChart;
    
    @FXML
    private Button FinishButton;
    
    @FXML
    private Button UploadCSV;

    @FXML
    private TextField XAxisTitle;

    @FXML
    private TextField YAxisTitle;
    
    @FXML
    void ClickFinish(ActionEvent event) {
    	FinishPrompt();
    }
    
    @FXML
    void UploadCSvFile(ActionEvent event) {
    	FileUploader();
    }
   
    
    
    public void FileUploader() {

    }
    
    public void setParentController(ControlSelectGraph controlselectgraph) {
    	this.controlSelectGraph = controlselectgraph;
    }
    

    
    public void FinishPrompt() {
    
    	if (ChartTitle.getText().trim().isEmpty() || XAxisTitle.getText().trim().isEmpty() || YAxisTitle.getText().trim().isEmpty() || IDChart.getText().matches("\\D*")) {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deepbot");
            alert.setHeaderText("Deletion module");
            alert.setContentText("Please Enter Some Text for Titles OR that you have entered non-integer values for ChartID's");
            alert.showAndWait();
        } else {
        	String TitleYAxis = YAxisTitle.getText();
        	String TitleXAxis = XAxisTitle.getText();
        	String TitleChart = ChartTitle.getText();
        	String ChartNumber = IDChart.getText();
        	FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open CSV File");
            File file = fileChooser.showOpenDialog(new Stage());
                
            controlSelectGraph.CreateLineChart(file, TitleXAxis, TitleYAxis, TitleChart, ChartNumber);
            Stage stage = (Stage) ChartTitle.getScene().getWindow();
            stage.close();
        	}
        	
        }
   
}
    
	 
