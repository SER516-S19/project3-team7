package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Utilities.JsonUtility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Questions;

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
	private RadioButton radioOption1;
	@FXML
	private RadioButton radioOption2;
	@FXML
	private RadioButton radioOption3;
	@FXML
	private RadioButton radioOption4;
	@FXML
	private Label errorQuizName;
	@FXML
	private Label errorQuestion;

	private List<Questions> questions = new ArrayList<Questions>();
	model.Quiz new_quiz = new model.Quiz();
	private ToggleGroup option = new ToggleGroup();

	private Scene professorScene;

	public void setProfessorScene(Scene scene) {
		professorScene = scene;
	}

	public void openProfessorScene(javafx.event.ActionEvent actionEvent) throws IOException {
		if (!quizName.getText().trim().isEmpty() && !question.getText().trim().isEmpty()) {
			addQuestion();
			JsonUtility file = new JsonUtility();
			file.writeToJson(new_quiz, quizName.getText());
			quizName.clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/professor.fxml"));
			Parent root = loader.load();
			quizName.getScene().setRoot(root);

		} else if(quizName.getText().trim().isEmpty()) {
			errorQuizName.setText("Please enter the quiz name.");
		} else if(question.getText().trim().isEmpty()) {
			errorQuestion.setText("Please enter at least a question.");
		}
	}

	public void home(javafx.event.ActionEvent actionEvent) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/professor.fxml"));
		Parent root = loader.load();
		quizName.getScene().setRoot(root);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		radioOption1.setToggleGroup(option);
		radioOption2.setToggleGroup(option);
		radioOption3.setToggleGroup(option);
		radioOption4.setToggleGroup(option);
	}

	/**
	 * Add questions in the quiz
	 */
	public void addQuestion() {
		if (quizName.getText().trim().isEmpty()) {
			errorQuizName.setText("Please enter the quiz name.");
			return;
		} else if (question.getText().trim().isEmpty()) {
			errorQuestion.setText("Please enter at least a question.");
			return;
		} else {
			List<String> options = new ArrayList<>();
			String correctAnswer = new String();
			Questions new_question = new Questions();
			if (!question.getText().isEmpty()) {
				new_question.setTitle(question.getText().trim());
				if (radioOption1.isSelected())
					correctAnswer = option1.getText().trim();
				else if (radioOption2.isSelected())
					correctAnswer = option2.getText().trim();
				else if (radioOption3.isSelected())
					correctAnswer = option3.getText().trim();
				else if (radioOption4.isSelected())
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
			radioOption1.setSelected(false);
			radioOption2.setSelected(false);
			radioOption3.setSelected(false);
			radioOption4.setSelected(false);
		}
	}
}