package pl.mo.window;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class MainWindow extends Application {

    private static final Logger log = Logger.getLogger(MainWindow.class);

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
            new Alert(AlertType.ERROR, ex.getClass().getTypeName() + System.lineSeparator() + ex.getMessage()).showAndWait();
        }

        log.debug("Application exited.");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow/MainWindow.fxml"));
            Parent root = loader.load();
            MainWindowController controller = loader.getController();
            primaryStage.setTitle("Metody optymalizacji");
            primaryStage.setScene(new Scene(root));
            primaryStage.setOnShown(event -> controller.setUiControlsDefaultSettings());
            primaryStage.show();
        } catch (IllegalStateException | NullPointerException | IOException ex) {
            log.error(ex.getMessage());
            new Alert(AlertType.ERROR, ex.getClass().getTypeName() + System.lineSeparator() + ex.getMessage()).showAndWait();
        }
    }

}
