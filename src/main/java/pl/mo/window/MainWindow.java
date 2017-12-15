package pl.mo.window;

import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import pl.mo.strings.MainWindowBundle;

public class MainWindow extends Application {

    private static final Logger log = Logger.getLogger(MainWindow.class);
    private static MainWindowBundle bundle;

    public static void main(String[] args) {
        log.info("Application started.");

        try {
            Locale.setDefault(new Locale("en", "US"));
            bundle = new MainWindowBundle();
            launch(args);
        } catch (RuntimeException ex) {
            log.error(ex.toString());
        }

        log.info("Application exited.");
    }

    @Contract(pure = true)
    public static MainWindowBundle getBundle() {
        return bundle;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(bundle.getFXMLResourcePath()));
        Parent root = loader.load();
        MainWindowController controller = loader.getController();
        primaryStage.setTitle(bundle.getAppTitle());
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnShown(event -> controller.setUiControlsDefaultSettings());
        primaryStage.show();
    }

}
