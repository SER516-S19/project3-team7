package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller to handle exit quiz requests from the student
 */

public class ExitQuiz {

    @FXML
    private Button HomeButton;

    @FXML
    private Button ExitButton;

    /**
     * Function to exit the quiz
     * @param actionEvent
     */
    public void exitQuiz(ActionEvent actionEvent) {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Function to redirect to home page
     * @param actionEvent
     * @throws IOException
     */
    public void goBackHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/student.fxml"));
        Parent root = loader.load();
        HomeButton.getScene().setRoot(root);
    }
}
