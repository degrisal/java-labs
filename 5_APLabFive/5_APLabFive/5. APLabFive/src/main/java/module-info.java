module com.kami.study.apfivethlab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires mysql.connector.java;


    opens com.kami.study.apfivethlab to javafx.fxml;
    exports com.kami.study.apfivethlab;
    exports com.kami.study.apfivethlab.model;
}