module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens TJ to javafx.fxml;
    exports TJ;
}