package application;


import java.io.IOException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

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
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.stage.Stage;

public class ControlGraph{
	

	int Xdatapoint;
	int Ydatapoint;
	int breakpoint;
	int[] Xdatapoints;
	int[] Ydatapoints;
	String[] checkNull;
	int xindex;
	int yindex;

	FileChooser fileChooser = new FileChooser();
	
	 @FXML
	 private Button DeleteFile;

	 @FXML
	 private Button UploadFile;
	 
	 @FXML
	 private LineChart<String, Number> LineChart;
	  
	 @FXML 
	 private NumberAxis NumberAxis;
	 
	 @FXML
	 private CategoryAxis CategoryAxis;
	 

	 @FXML
	 void getText(ActionEvent event) throws CsvValidationException, NumberFormatException, IOException {
		 SetFilePath();
	 }
	 
	    private void SetFilePath() {
	        
	    	FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Open CSV File");
	        File file = fileChooser.showOpenDialog(new Stage());
	        try {
				FileInputStream fileInputStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if (file != null) {
	            // Read data from the CSV file and update the chart
	            updateChart(file);
	        }
	    }


	    private void updateChart(File file) {
	        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	            // Read CSV file and update chart data
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                // Assuming the first column is the X-axis data and the second column is the Y-axis data
	                XYChart.Series<String, Number> series = new XYChart.Series<>();
	                series.getData().add(new XYChart.Data<>(data[0], Integer.parseInt(data[1])));
	                LineChart.getData().add(series);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	}
	 
/*
	 public void SetFilePath() {
		 series.setName("Glucose level");
		 File file = fileChooser.showOpenDialog(new Stage());
		 try (Scanner scanner = new Scanner(file))
		 {
			 scanner.useDelimiter("[,\n]");
			 
			 while(scanner.hasNext()) {
				 int sum =0;
				 if(sum % 2 == 0) {

					 Xdatapoints[xindex] = Integer.parseInt(scanner.next());
					 xindex = xindex + 1;
				 }
				 else if(sum % 2 == 5) {

					 Ydatapoints[yindex] = Integer.parseInt(scanner.next());
					 yindex = yindex + 1;
				 }
				 sum = sum +1;					 
			 }
			
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
			while(breakpoint < Xdatapoints.length && breakpoint < Ydatapoints.length){
				int DataIndex = 0;
				series.getData().add(new Data<Integer, Integer>(Xdatapoints[DataIndex], Ydatapoints[DataIndex]));
				DataIndex = DataIndex + 1;
				breakpoint = breakpoint + 1;
			}
	 }
*/

	 

	 
/*
	public void SetFilePath() throws CsvValidationException, NumberFormatException, IOException {
		 File file = fileChooser.showOpenDialog(new Stage());
		 try (CSVReader dataReader = new CSVReader(new FileReader(file))) {
			while((checkNull = dataReader.readNext()) != null) {
			Xdatapoint = Integer.parseInt(checkNull[0]);
			Ydatapoint = Integer.parseInt(String.valueOf(checkNull[1]));
			series.getData().add(new XYChart.Data<Integer, Integer>(Xdatapoint, Ydatapoint));					
			}
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LineChart.getData().addAll(series);
		series.setName("Glucose Level");
	    }
	    
}

*/

		 
		 
	 
	 
/*	 public void SetFilePath() throws CsvValidationException, NumberFormatException, IOException {
		 File file = fileChooser.showOpenDialog(new Stage());
		 try (CSVReader dataReader = new CSVReader(new FileReader(file))) {
			while((checkNull = dataReader.readNext())!= null) {				
				int sum = 0;
				if(sum%2 == 0) {
					int index = 0;
					Xdatapoints[index] = Integer.parseInt(checkNull[0]);
					index = index + 1;
				}else if(sum%2 == 1) {
					int index = 0;
					Ydatapoints[index] = Integer.parseInt(checkNull[1]);
					index = index + 1;
				}				
				sum = sum + 1;					
			}
			while(breakpoint < Xdatapoints.length && breakpoint < Ydatapoints.length){
				int DataIndex = 0;
				series.getData().add(new Data<Integer, Integer>(Xdatapoints[DataIndex], Ydatapoints[DataIndex]));
				DataIndex = DataIndex + 1;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	
*/	
		
	





