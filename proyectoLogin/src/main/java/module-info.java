module org.example.proyectoshector {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.proyectoshector to javafx.fxml;
    exports org.example.proyectoshector;
}