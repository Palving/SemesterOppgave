module hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}
