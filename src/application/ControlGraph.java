package application;


import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class ControlGraph implements Initializable{
		
	final NumberAxis yAxis = new NumberAxis(0, 5000000, 1);
    final CategoryAxis xAxis = new CategoryAxis();
	
	int[] Xdatapoints = new int[1000];
	int[] Ydatapoints = new int[1000];
	int breakpoint;
	String[] checkNull;
	 XYChart.Series<Integer, Integer> series = new XYChart.Series<Integer, Integer>();
	 FileChooser fileChooser = new FileChooser();
	
	 @FXML
	 private Button DeleteFile;

	 @FXML
	 private Button UploadFile;
	 
	 @FXML
	 private LineChart<Integer, Integer> LineChart;
	 


	 @FXML
	 void getText(ActionEvent event) throws CsvValidationException, NumberFormatException, IOException {
		 SetFilePath();
	 }
	 
	 public void SetFilePath() throws IOException, CsvValidationException, NumberFormatException {
		 File file = fileChooser.showOpenDialog(new Stage());
		 try (CSVReader dataReader = new CSVReader(new FileReader(file))) {
			while((checkNull = dataReader.readNext())!= null) {				
				int sum = 0;
				if(sum%2 == 0) {
					Xdatapoints[sum] = Integer.parseInt(checkNull[sum]);
				}else if(sum%2 == 1) {
					Ydatapoints[sum] = Integer.parseInt(checkNull[sum]);
				}				
				sum = sum + 1;					
			}
			while(breakpoint < Xdatapoints.length){
				int DataIndex = 0;
				series.getData().add(new Data<Integer, Integer>(Xdatapoints[DataIndex], Ydatapoints[DataIndex]));
				DataIndex = DataIndex + 1;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LineChart.getData().add(series);

	
		
	}


}


