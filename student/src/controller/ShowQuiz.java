package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Question;
import model.QuizDetails;
import model.StudentModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to render the Quiz from the Json file
 */
public class ShowQuiz {

	@FXML
	public Label QuizName;

	private List<Question> questions = new ArrayList<>();

	StudentModel studentModel = new StudentModel();

	@FXML
	private Text questionTitle;

	@FXML
	private Text questionNumber;

	@FXML
	public RadioButton option1;

	@FXML
	public RadioButton option2;

	@FXML
	public RadioButton option3;

	@FXML
	public RadioButton option4;

	@FXML
	public Button nextButton;

	private int currentQuestionNumber = 1;

	public void loadQuiz(String selectedQuiz) {
		setQuizTitleLabel(selectedQuiz);
		QuizDetails quiz = studentModel.readQuizDetails(selectedQuiz);
		questions.addAll(quiz.getQuestions());
		setQuestions();
	}

	public void endQuiz() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/student.fxml"));
			Stage primaryStage = new Stage();
			primaryStage.setTitle("Hello Student");
			primaryStage.setScene(new Scene(root, 800, 600));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showNextQuestion() {
		int quizSize = questions.size();

		if (currentQuestionNumber <= quizSize) {
			setQuestions();
		}

		if (currentQuestionNumber - 1 == quizSize) {
			nextButton.setText("Submit");
		}
	}

	private void setQuizTitleLabel(String selectedQuiz) {

		QuizName.setText(selectedQuiz);
	}

	private void setQuestions() {
		Question question = questions.get(currentQuestionNumber - 1);
		questionNumber.setText(currentQuestionNumber + ")");
		questionTitle.setText(question.getTitle());
		currentQuestionNumber = currentQuestionNumber + 1;
		List<String> options = question.getOptions();
		ToggleGroup group = new ToggleGroup();

		option1.setToggleGroup(group);
		option2.setToggleGroup(group);
		option3.setToggleGroup(group);
		option4.setToggleGroup(group);

		option1.setSelected(false);
		option2.setSelected(false);
		option3.setSelected(false);
		option4.setSelected(false);

		option1.setText(options.get(0));
		option2.setText(options.get(1));
		option3.setText(options.get(2));
		option4.setText(options.get(3));
	}

}
