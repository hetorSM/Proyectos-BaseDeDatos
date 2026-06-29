module org.example.practica4hibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens org.example.practica4hibernate to javafx.fxml, org.hibernate.orm.core;
    opens org.example.models to org.hibernate.orm.core;
    opens org.example.controllers to javafx.fxml, org.hibernate.orm.core;

    exports org.example.practica4hibernate;
    exports org.example.controllers;
    exports org.example.models;
}
