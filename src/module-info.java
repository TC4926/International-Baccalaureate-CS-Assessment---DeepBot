module Please {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires java.base;
	requires controlsfx;
	requires TrayTester;
	requires com.opencsv;

	  opens application to javafx.graphics, javafx.fxml;
}
