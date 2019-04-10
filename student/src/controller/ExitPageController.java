package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ExitPageController {

    @FXML
    private Button HomeButton;

    @FXML
    private Button ExitButton;

    public void exitQuiz(ActionEvent actionEvent) {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }

    public void goBackHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/student.fxml"));
        Parent root = loader.load();
        HomeButton.getScene().setRoot(root);
    }
}
