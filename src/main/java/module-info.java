module com.example.bowlinggamekata {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bowlinggamekata to javafx.fxml;
    exports com.bowlinggamekata;
}