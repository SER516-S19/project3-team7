package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Question;
import model.QuizDetails;
import model.StudentModel;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Class to render the Quiz from the JSON file
 */

public class DisplayQuiz implements Initializable {

	@FXML
	public Label QuizName;

	@FXML
	private Text questionTitle;

	@FXML
	private Text questionNumber;

	@FXML
	private Text quizMessage;

	@FXML
	private Button giveupButton;

	public RadioButton answerOption1;

	public RadioButton answerOption2;

	public RadioButton answerOption3;

	public RadioButton answerOption4;

	public Button nextButton;

	private List<Question> questions = new LinkedList();
	private List<Question> queWithIncorrectAns = new ArrayList<>();
	private ToggleGroup radioButtonGroup = null;

	private String selectedAns = null;
	private int currentQuestionNumber = 0;

	private StudentModel studentModel = new StudentModel();

	private String selectedQuiz = studentModel.getSelectedQuizName();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadQuiz();
	}

	/**
	 * Function to load the question and options
	 */
	public void loadQuiz() {
		setQuizTitleLabel(selectedQuiz);
		QuizDetails quiz = studentModel.readQuizDetails(selectedQuiz);
		questions.addAll(quiz.getQuestions());
		quizMessage.setText("Good Luck with the Quiz!!");
		setQuestions();
	}

	/**
	 * Function to load the wrong answered questions
	 */
	public void loadWrongAnswerdQuestions() {
		setQuizTitleLabel(QuizName.getText());
		quizMessage.setText("You couldn't complete the quiz successfully. Keep Trying!!!");
		setQuestions();
	}

	/**
	 * Function to end or give up the quiz
	 */
	public void endQuiz() {
		try {
			FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/Student.fxml"));
			Parent root = loader.load();
			giveupButton.getScene().setRoot(root);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function for next button click
	 */
	public void showNextQuestion() throws IOException {
		int quizSize = questions.size();
		String submissionType = nextButton.getText();
		if ("Submit".equalsIgnoreCase(submissionType)) {
			verifySubmittedQuiz();
		} else {
			if (currentQuestionNumber < quizSize) {
				Question currentQuestion = questions.get(currentQuestionNumber - 1);
				if (selectedAns != null && currentQuestion.getCorrectAnswer() != null) {
					if (!selectedAns.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
						queWithIncorrectAns.add(currentQuestion);
					} else {
						if (queWithIncorrectAns.contains(currentQuestion)) {
							queWithIncorrectAns.remove(currentQuestion);
						}
					}
				}
				setQuestions();
			}
			if (currentQuestionNumber == quizSize) {
				nextButton.setText("Submit");
			}
		}
	}

	/**
	 * Store student response for the Question
	 */
	public void setSelectedRadioButton() {
		if (answerOption1.isSelected()) {
			selectedAns = answerOption1.getText();
		}
		if (answerOption2.isSelected()) {
			selectedAns = answerOption2.getText();
		}
		if (answerOption3.isSelected()) {
			selectedAns = answerOption3.getText();
		}
		if (answerOption4.isSelected()) {
			selectedAns = answerOption4.getText();
		}
	}

	/**
	 * Check student submission of the Quiz
	 *
	 * @throws IOException
	 */
	public void verifySubmittedQuiz() throws IOException {
		currentQuestionNumber--;
		Question currentQuestion = questions.get(currentQuestionNumber);
		if (selectedAns != null && currentQuestion.getCorrectAnswer() != null) {
			if (!selectedAns.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
				queWithIncorrectAns.add(currentQuestion);
			} else {
				if (queWithIncorrectAns.contains(currentQuestion)) {
					queWithIncorrectAns.remove(currentQuestion);
				}
			}
		}

		if (queWithIncorrectAns.size() == 0) {
			showCongrats();
		} else {
			currentQuestionNumber = 0;
			nextButton.setText("Next");
			questions = new ArrayList<>(queWithIncorrectAns);
			queWithIncorrectAns = new ArrayList<>();
			loadWrongAnswerdQuestions();
		}
	}

	/**
	 * Function to display the Success message
	 * @throws IOException
	 */
	private void showCongrats() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/exitPage.fxml"));
		Parent root = loader.load();
		Text text = (Text) root.getChildrenUnmodifiable().get(0);
		text.setFill(Color.GREEN);
		text.setFont(Font.font("Edwardian Script ITC", 16)); 
		giveupButton.getScene().setRoot(root);
	}

	/**
	 * Function to set the Quiz title
	 * @param selectedQuiz
	 */
	private void setQuizTitleLabel(String selectedQuiz) {
		QuizName.setText(selectedQuiz);
	}

	/**
	 * Set the Questions and Answers
	 */
	private void setQuestions() {
		Question question = questions.get(currentQuestionNumber);
		questionNumber.setText(currentQuestionNumber + 1 + ")");
		questionTitle.setText(question.getTitle());
		currentQuestionNumber = currentQuestionNumber + 1;
		List<String> options = question.getOptions();
		radioButtonGroup = new ToggleGroup();
		answerOption1.setToggleGroup(radioButtonGroup);
		answerOption2.setToggleGroup(radioButtonGroup);
		answerOption3.setToggleGroup(radioButtonGroup);
		answerOption4.setToggleGroup(radioButtonGroup);
		answerOption1.setSelected(false);
		answerOption2.setSelected(false);
		answerOption3.setSelected(false);
		answerOption4.setSelected(false);
		answerOption1.setText(options.get(0));
		answerOption2.setText(options.get(1));
		answerOption3.setText(options.get(2));
		answerOption4.setText(options.get(3));
	}
}
