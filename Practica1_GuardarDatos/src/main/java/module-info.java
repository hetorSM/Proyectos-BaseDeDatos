module com.example.colegiohectorsm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.colegiohectorsm to javafx.fxml;
    exports com.example.colegiohectorsm;
}