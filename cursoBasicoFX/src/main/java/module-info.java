module org.example.cursobasicofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.cursobasicofx to javafx.fxml;
    exports org.example.cursobasicofx;
}