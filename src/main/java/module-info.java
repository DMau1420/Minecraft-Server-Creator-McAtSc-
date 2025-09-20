module com.servercreator.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;
    requires com.google.gson;

    opens com.servercreator.demo to javafx.fxml;
    exports com.servercreator.demo;
}