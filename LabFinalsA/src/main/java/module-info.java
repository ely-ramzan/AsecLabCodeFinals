module com.example.labfinalsa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.labfinalsa to javafx.fxml;
    exports com.example.labfinalsa;
}