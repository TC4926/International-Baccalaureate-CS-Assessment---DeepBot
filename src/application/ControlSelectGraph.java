package application;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.MouseInfo;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class ControlSelectGraph implements Initializable{
	
	Connection con; //remove if didn't work
	PreparedStatement pst; //remove if didn't work
	
	private PreparedStatement store, retrieve;
	
	int index = 0;
	
	ArrayList<Integer> IDList = new ArrayList<Integer>();

	private String storeStatement = "INSERT INTO photos(_id, image) VALUES(?,?)";
	private String retrieveStatement = "select _id,image from photos";
	
	@FXML
    private FlowPane FlowPaneChart;
	
	@FXML
    private MenuItem ReturnButton;
	
	@FXML
    private Label text;
	
	@FXML
    private Label GraphIndex;
	
	@FXML
    private Button DeleteButton;
	
	@FXML
    private ImageView ImageViewInPane;
	
    @FXML
    private MenuItem AreaChart;

    @FXML
    private MenuItem BarChart;

    @FXML
    private MenuItem LineChart;

    @FXML
    private MenuItem PieChart;
    
    @FXML
    private ScrollPane ScrollPaneGraph;
    
    @FXML
    private VBox VboxGraph;
    
    @FXML
    private Button SaveGraph;

   
    @FXML
    void Return(ActionEvent event) throws IOException {
    	Main n = new Main();
    	n.changeScene("ChoosePath.fxml");
    }
  
    
    @FXML
    void AddGraph(ActionEvent event) throws SQLException, IOException {
    	AddGraphToVbox();
    }
   

	@FXML
    void AddAreaChartData(ActionEvent event) throws IOException {
    	AreaChartInformation();
    }

    @FXML
    void AddBarChartData(ActionEvent event) throws IOException {
    	BarChartInformation();
    }

    @FXML
    void AddLineChartData(ActionEvent event) throws IOException {
    	LineChartInformation();
    }

    @FXML
    void AddPieChartData(ActionEvent event) throws IOException {
    	PieChartInformation();
    }


    public void AddGraphToVbox() throws SQLException, IOException {
    	if(FlowPaneChart.getChildren().isEmpty()) {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deepbot");
            alert.setHeaderText("Deletion module");
            alert.setContentText("Please Add An Chart Before Saving");
            alert.showAndWait();
            
    	} else {
    		
    	Connect();
    	  
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WritableImage image = FlowPaneChart.snapshot(new SnapshotParameters(), null);
        	final DropShadow shadow = new DropShadow();
        	shadow.setColor(Color.FORESTGREEN);
        	final Glow glow = new Glow();
        	glow.setInput(shadow);
            ImageView imageView = new ImageView(image);
           

            imageView.setPreserveRatio(true);
            imageView.setOnMouseClicked(event -> {
            	if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    // Double-click action (you can customize this)
            		FlowPaneChart.getChildren().clear();
                	ImageViewInPane.setImage(image);
             
                }
            });
            imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                  imageView.setEffect(glow);
                }
              });
              imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                  imageView.setEffect(null);
                }
              });
            Image ImageToBeSaved = imageView.getImage();
            String home = System.getProperty("user.home");
            File f = new File(home + File.separator + "Desktop" + File.separator + "ChartImage.png");
            try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(ImageToBeSaved, null), "png", f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            VboxGraph.getChildren().add(imageView);
            ScrollPaneGraph.setContent(VboxGraph);
            
            ScrollPaneGraph.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            ScrollPaneGraph.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

           
            imageView.fitWidthProperty().bind(Bindings.createDoubleBinding(() -> {

  
                Optional<ScrollBar> verticalScrollBarOpt = ScrollPaneGraph.lookupAll(".scroll-bar").stream()
                        .filter(node -> node instanceof ScrollBar)
                        .map(node -> (ScrollBar) node)
                        .filter(scrollBar -> scrollBar.getOrientation() == Orientation.VERTICAL).findAny();

                if (verticalScrollBarOpt.isPresent() && verticalScrollBarOpt.get().isVisible())
                    return ScrollPaneGraph.getWidth() - verticalScrollBarOpt.get().getWidth();
                else
                    return ScrollPaneGraph.getWidth();

            }, ScrollPaneGraph.widthProperty()));
            
            IDList.add(Integer.parseInt(GraphIndex.getText()));
            
            imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            	    public void handle(MouseEvent mouseEvent) {
            	    Object selectedNode = mouseEvent.getSource();
            	    int selectedIdx  = VboxGraph.getChildren().indexOf(selectedNode);
            	 
            
            	    int ID = IDList.get(selectedIdx);
            	    GraphIndex.setText(String.valueOf(ID));
            	
            	  }
            	});
            
            try {
				FileInputStream fileInputStream = new FileInputStream(f);
				store.setInt(1, Integer.parseInt(GraphIndex.getText()));
				store.setBinaryStream(2, fileInputStream, fileInputStream.available());
				store.execute();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
    	}
    }
    
    @FXML
    void Delete(ActionEvent event) {
    	
    	
    	
    	int myIndex = Integer.parseInt(GraphIndex.getText());

    	
        try 
        {
            pst = con.prepareStatement("delete from photos where _id = ?");
            pst.setInt(1, myIndex);
            pst.executeUpdate();
            for(int i = 0; i < IDList.size(); i++) {
            	if(IDList.get(i) == myIndex) {
            		IDList.remove(i);
            	}
            }
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deepbot");
            alert.setHeaderText("Deletion module");
        	alert.setContentText("Notification successfully Deleted!");
        	ImageViewInPane.setImage(null);
        	alert.showAndWait();
        	
                  loadChart();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    
    
    public void loadChart() {
    	try {
    		ScrollPaneGraph.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            ScrollPaneGraph.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
			ResultSet rs = retrieve.executeQuery();
			VboxGraph.getChildren().clear();
			IDList.clear();
			while(rs.next()){/////result set loop, iterating over database
	            InputStream is = rs.getBinaryStream("image"); // image from database
	            int DBID = rs.getInt("_id");
	            IDList.add(DBID);
	            BufferedImage imBuff = ImageIO.read(is);  //converting to buffered image
	            Image image = SwingFXUtils.toFXImage(imBuff, null);  //converting to  
	            ImageView DBImage = new ImageView(image);
	            DBImage.setPreserveRatio(true);
	            VboxGraph.getChildren().add(DBImage);
	            ScrollPaneGraph.setContent(VboxGraph);
	            
	            DBImage.setOnMouseClicked(event -> {
	            	if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
	                    // Double-click action (you can customize this)
	            		FlowPaneChart.getChildren().clear();
	                	ImageViewInPane.setImage(image);
	                }
	            	final DropShadow shadow = new DropShadow();
	            	shadow.setColor(Color.FORESTGREEN);
	            	final Glow glow = new Glow();
	            	glow.setInput(shadow);
	      
	 
	                DBImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
	                    public void handle(MouseEvent mouseEvent) {
	                      DBImage.setEffect(glow);
	                    }
	                  });
	                  DBImage.setOnMouseExited(new EventHandler<MouseEvent>() {
	                    public void handle(MouseEvent mouseEvent) {
	                      DBImage.setEffect(null);
	                    }
	                  });	
	            });
	            DBImage.fitWidthProperty().bind(Bindings.createDoubleBinding(() -> {

	                // Find the vertical scroll bar of the scroll pane:
	                Optional<ScrollBar> verticalScrollBarOpt = ScrollPaneGraph.lookupAll(".scroll-bar").stream()
	                        .filter(node -> node instanceof ScrollBar)
	                        .map(node -> (ScrollBar) node)
	                        .filter(scrollBar -> scrollBar.getOrientation() == Orientation.VERTICAL).findAny();

	                if (verticalScrollBarOpt.isPresent() && verticalScrollBarOpt.get().isVisible())
	                    return ScrollPaneGraph.getWidth() - verticalScrollBarOpt.get().getWidth();
	                else
	                    return ScrollPaneGraph.getWidth();

	            }, ScrollPaneGraph.widthProperty()));
	            
	            DBImage.setOnMousePressed(new EventHandler<MouseEvent>() {
	            	public void handle(MouseEvent mouseEvent) {
	            	final Object selectedNode = mouseEvent.getSource();
	            	final int    selectedIdx  = VboxGraph.getChildren().indexOf(selectedNode);
	            	int ID = IDList.get(selectedIdx);
            	    GraphIndex.setText(String.valueOf(ID));
            	  }
            	});
	           
	         
			
	         	}
		

            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }



	public void AreaChartInformation() throws IOException {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PromptSpecifications.fxml"));
    	Parent root1 = (Parent) fxmlLoader.load();
    	ControlPromptSpecifications controlpromptspecification = fxmlLoader.getController();
    	controlpromptspecification.setParentController(this);
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root1));  
    	stage.show();  	
    }
    
    public void BarChartInformation() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BarChartPromptSpecifications.fxml"));
    	Parent root1 = (Parent) fxmlLoader.load();
    	ControlBarPromptSpecifications controlbarpromptspecification = fxmlLoader.getController();
    	controlbarpromptspecification.setParentController(this);
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root1));  
    	stage.show();  	
    }
    
    public void LineChartInformation() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LineChartPromptSpecifications.fxml"));
    	Parent root1 = (Parent) fxmlLoader.load();
    	ControlLinePromptSpecifications controllinepromptspecification = fxmlLoader.getController();
    	controllinepromptspecification.setParentController(this);
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root1));  
    	stage.show();  	
    }
    
    public void PieChartInformation() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PieChartPromptSpecifications.fxml"));
    	Parent root1 = (Parent) fxmlLoader.load();
    	ControlPiePromptSpecifications controlpiepromptspecification = fxmlLoader.getController();
    	controlpiepromptspecification.setParentController(this);
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root1));  
    	stage.show();  	
    }
    

    

    
    public void CreateAreaChart(File file, String TitleXAxis, String TitleYAxis, String TitleChart, String ChartNumber) {
    	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            NumberAxis yAxis = new NumberAxis();
            CategoryAxis xAxis = new CategoryAxis();
            AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
            GraphIndex.setText(ChartNumber);
    
            yAxis.setLabel(TitleYAxis);
            xAxis.setLabel(TitleXAxis);
            areaChart.setTitle(TitleChart);
            yAxis.forceZeroInRangeProperty().set(true);
            areaChart.setLegendVisible(false);
            areaChart.setStyle("-fx-background-color: BEIGE");
            areaChart.setCacheShape(true);
            // Read CSV file and update chart data
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Assuming the first column is the X-axis data and the second column is the Y-axis data
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(String.valueOf(data[0]), Integer.parseInt(data[1])));
                areaChart.getData().add(series);
                
            }
            areaChart.setPrefSize(430, 302);
            
            FlowPaneChart.getChildren().clear();
            ImageViewInPane.imageProperty().set(null);
            FlowPaneChart.getChildren().add(areaChart); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void CreateBarChart(File file, String TitleXAxis, String TitleYAxis, String TitleChart, String ChartNumber) {
    	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            NumberAxis yAxis = new NumberAxis();
            CategoryAxis xAxis = new CategoryAxis();
            StackedBarChart<String, Number> barChart = new StackedBarChart<>(xAxis, yAxis);
            GraphIndex.setText(ChartNumber);
    
            yAxis.setLabel(TitleYAxis);
            xAxis.setLabel(TitleXAxis);
            barChart.setTitle(TitleChart);
            yAxis.forceZeroInRangeProperty().set(true);
            barChart.setStyle("-fx-background-color: YELLOW");
            barChart.setLegendVisible(false);
          
            // Read CSV file and update chart data
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Assuming the first column is the X-axis data and the second column is the Y-axis data
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(String.valueOf(data[0]), Integer.parseInt(data[1])));
                barChart.getData().add(series);
                
            }
            barChart.setPrefSize(430, 302);
            FlowPaneChart.getChildren().clear();
            ImageViewInPane.imageProperty().set(null);
            FlowPaneChart.getChildren().add(barChart);     
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void CreateLineChart(File file, String TitleXAxis, String TitleYAxis, String TitleChart, String ChartNumber) {
    	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            NumberAxis yAxis = new NumberAxis();
            NumberAxis xAxis = new NumberAxis();
            LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
            GraphIndex.setText(ChartNumber);
    
            yAxis.setLabel(TitleYAxis);
            xAxis.setLabel(TitleXAxis);
            lineChart.setTitle(TitleChart);
            yAxis.forceZeroInRangeProperty().set(true);
            lineChart.setStyle("-fx-background-color: RED");
            lineChart.setLegendVisible(false);

          
            // Read CSV file and update chart data
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Assuming the first column is the X-axis data and the second column is the Y-axis data
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(Double.parseDouble(data[0]), Integer.parseInt(data[1])));
                lineChart.getData().add(series);
            }
            lineChart.setPrefSize(430, 302);
            FlowPaneChart.getChildren().clear();
            ImageViewInPane.imageProperty().set(null);
            FlowPaneChart.getChildren().add(lineChart);     
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void CreatePieChart(File file, String TitleXAxis, String TitleYAxis, String TitleChart, String ChartNumber) {
    	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            PieChart pieChart = new PieChart();
            GraphIndex.setText(ChartNumber);
    
            
            pieChart.setTitle(TitleChart);
            pieChart.setStyle("-fx-background-color: Purple");

          
            // Read CSV file and update chart data
            String line;
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Assuming the first column is the X-axis data and the second column is the Y-axis data
                
                PieChart.Data pieData = new PieChart.Data(String.valueOf(data[0]), Integer.parseInt(data[1]));
                pieChartData.add(pieData);
            }
            pieChart.setData(pieChartData);
            pieChart.setPrefSize(430, 302);
            FlowPaneChart.getChildren().clear();
            ImageViewInPane.imageProperty().set(null);
            FlowPaneChart.getChildren().add(pieChart);     
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
   public void Connect()//remove if didn't work
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB","root","Ab0928121510");
  
            store = con.prepareStatement(storeStatement);
            retrieve = con.prepareStatement(retrieveStatement);
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
   public void initialize(URL arg0, ResourceBundle arg1) {
		Connect();
		loadChart();
	}
 
}
	 
