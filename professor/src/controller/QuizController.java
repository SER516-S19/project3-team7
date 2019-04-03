package controller;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizController implements Initializable {

    private Scene professorScene;

    public void setProfessorScene(Scene scene) {
        professorScene = scene;
    }

    public void openProfessorScene(javafx.event.ActionEvent actionEvent) {
        Stage quizWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        quizWindow.setScene(professorScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addQuiz() {

    }

    public void addQuestion() {
        System.out.println("Add question");
    }

    public void saveAndExit() {
        System.out.println("Save and exit");
    }


}