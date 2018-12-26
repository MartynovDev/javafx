package app.gaussian.exception_handlers;

import javafx.scene.control.Alert;

public class TextFieldHandler {

    /**
     * Show alert with ERROR information about NumberFormatException
     * @param textFieldLabel field name
     */
    public static void numberFormatExceptionHandler(String textFieldLabel){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(String.format("Please check '%s' input field!", textFieldLabel));
        alert.showAndWait();
    }

    /**
     * Show alert with INFO information for repeating data
     * @param data repeating data
     */
    public static void repeatingDataHandler(String data){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(String.format("Line with '%s' data is shown.\nPlease, change input fields.", data));
        alert.showAndWait();
    }
}
