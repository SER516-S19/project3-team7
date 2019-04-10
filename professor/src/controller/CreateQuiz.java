package controller;

import Utilities.JsonUtility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Questions;
import model.Quiz;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller to handle the creation of the Quiz
 *
 * @author Darshan Prakash,
 */

public class CreateQuiz implements Initializable {

    @FXML
    private TextField quizName;
    @FXML
    private TextField question;
    @FXML
    private TextField option1;
    @FXML
    private TextField option2;
    @FXML
    private TextField option3;
    @FXML
    private TextField option4;
    @FXML
    private CheckBox checkOption1;
    @FXML
    private CheckBox checkOption2;
    @FXML
    private CheckBox checkOption3;
    @FXML
    private CheckBox checkOption4;
    @FXML
    private Label errorQuizName;

    private List<Questions> questions = new ArrayList<Questions>();
    model.Quiz new_quiz = new model.Quiz();

    private Scene professorScene;

    public void setProfessorScene(Scene scene) {
        professorScene = scene;
    }

    public void openProfessorScene(javafx.event.ActionEvent actionEvent) {
        Stage quizWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        if (!quizName.getText().trim().isEmpty()) {
            addQuestion();
            JsonUtility file = new JsonUtility();
            file.writeToJson(new_quiz, quizName.getText());
            quizWindow.setScene(professorScene);
        } else {
            errorQuizName.setText("Please enter the quiz name.");
        }
    }

    public void home(javafx.event.ActionEvent actionEvent) {
        Stage quizWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        quizWindow.setScene(professorScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addQuestion() {

        if (quizName.getText().trim().isEmpty()) {
            errorQuizName.setText("Please enter the quiz name.");
            return;
        } else {
            List<String> options = new ArrayList<>();
            String correctAnswer = new String();
            Questions new_question = new Questions();
            if (!question.getText().isEmpty()) {
                new_question.setTitle(question.getText().trim());
                if (checkOption1.isSelected())
                    correctAnswer = option1.getText().trim();
                else if (checkOption2.isSelected())
                    correctAnswer = option2.getText().trim();
                else if (checkOption3.isSelected())
                    correctAnswer = option3.getText().trim();
                else if (checkOption4.isSelected())
                    correctAnswer = option4.getText().trim();
                new_question.setCorrectAnswer(correctAnswer);
                options.add(option1.getText().trim());
                options.add(option2.getText().trim());
                options.add(option3.getText().trim());
                options.add(option4.getText().trim());
                new_question.setOptions(options);
                questions.add(new_question);
                new_quiz.setQuestions(questions);
            }
            question.clear();
            option1.clear();
            option2.clear();
            option3.clear();
            option4.clear();
            checkOption1.setSelected(false);
            checkOption2.setSelected(false);
            checkOption3.setSelected(false);
            checkOption4.setSelected(false);
        }
    }
}