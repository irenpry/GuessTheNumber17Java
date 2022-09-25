module com.example.guessthenumber17java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.guessthenumber17java to javafx.fxml;
    exports com.example.guessthenumber17java;
}