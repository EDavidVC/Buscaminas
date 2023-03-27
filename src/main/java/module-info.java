module com.davidvelz.buscaminas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.davidvelz.buscaminas to javafx.fxml;
    exports com.davidvelz.buscaminas;
}