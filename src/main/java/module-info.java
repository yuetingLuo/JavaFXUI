module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
//    requires javafx.fxml;


    opens groupwork to javafx.fxml;
    exports groupwork;
}