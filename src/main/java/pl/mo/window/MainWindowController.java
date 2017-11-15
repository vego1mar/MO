package pl.mo.window;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import pl.mo.general.PrimitivesParser;
import pl.mo.general.ReflectionHelper;
import pl.mo.gs.GridSearch;

public class MainWindowController {

    private static final Logger log = Logger.getLogger(MainWindowController.class);

    @FXML private TextField gsLeftArgument;
    @FXML private TextField gsRightArgument;
    @FXML private TextField gsAccuracy;
    @FXML private TextField gsLocalMinimum;

    @FXML private Label statusBarInfo;

    @FXML
    private void countUserDefinedLocalMinimum() {
        statusBarInfo.setText(ReflectionHelper.getCurrentMethodName() + "()... executing");
        Double leftArgument = PrimitivesParser.getDouble(gsLeftArgument.getText());
        Double rightArgument = PrimitivesParser.getDouble(gsRightArgument.getText());
        Double accuracy = PrimitivesParser.getDouble(gsAccuracy.getText());

        if (leftArgument == null || rightArgument == null || accuracy == null) {
            statusBarInfo.setText(ReflectionHelper.getCurrentMethodName() + "()... parsing fail");
            new Alert(AlertType.ERROR, "Improper value." + System.lineSeparator() + PrimitivesParser.getErrorMessage()).showAndWait();
            return;
        }

        GridSearch gridSearch = new GridSearch();
        Double localMinimum = gridSearch.getLocalMinimumArgument(leftArgument, rightArgument, accuracy);

        if (localMinimum == null) {
            gsLocalMinimum.setText("N/A");
            statusBarInfo.setText(ReflectionHelper.getCurrentMethodName() + "()... counting stop");
            new Alert(AlertType.ERROR, "The value has not been found.").showAndWait();
            return;
        }

        gsLocalMinimum.setText(localMinimum.toString());
        statusBarInfo.setText("Ready.");
        log.info(ReflectionHelper.getCurrentMethodName() + "(" + leftArgument + ", " + rightArgument + ", " + accuracy + ") = " + localMinimum);
    }

}
