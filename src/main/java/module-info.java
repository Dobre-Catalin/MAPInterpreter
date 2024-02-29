module org.example.interpretergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.interpretergui to javafx.fxml;
    exports org.example.interpretergui;
}