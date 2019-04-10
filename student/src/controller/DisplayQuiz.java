package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Question;
import model.QuizDetails;
import model.StudentModel;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to render the Quiz from the JSON file
 */
public class DisplayQuiz {

	@FXML
	public Label QuizName;
	@FXML
	private Text questionTitle;
	@FXML
	private Text questionNumber;

	@FXML
	private Text quizMessage;

	@FXML
	private Text successMessage;

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
	private int questionNo = 0;
	private int currentQuestionNumber = 0;

	StudentModel studentModel = new StudentModel();

	/**
	 * Function to load the question and options
	 * 
	 * @param selectedQuiz
	 */
	public void loadQuiz(String selectedQuiz) {
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
			Parent root = FXMLLoader.load(getClass().getResource("../view/student.fxml"));
			Stage primaryStage = new Stage();
			primaryStage.setTitle("Hello Student");
			primaryStage.setScene(new Scene(root, 800, 600));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function for next button click
	 */
	public void showNextQuestion() throws IOException {
		int quizSize = questions.size();
		System.out.println("quizSize : " + quizSize);
		String submissionType = nextButton.getText();
		if ("Submit".equalsIgnoreCase(submissionType)) {
			verifySubmittedQuiz();
		} else {
			if (currentQuestionNumber < quizSize) {
				System.out.println("currentQuestionNumber : " + currentQuestionNumber);
				System.out.println(
						"questions.get(currentQuestionNumber-1) : " + questions.get(currentQuestionNumber - 1));
				Question currentQuestion = questions.get(currentQuestionNumber - 1);
				System.out.println(selectedAns + " : " + currentQuestion.getCorrectAnswer());
				if (selectedAns != null && currentQuestion.getCorrectAnswer() != null) {
					if (!selectedAns.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
						queWithIncorrectAns.add(currentQuestion);
					} else {
						if (queWithIncorrectAns.contains(currentQuestion)) {
							queWithIncorrectAns.remove(currentQuestion);
							System.out.println("showNextQuestion :" + queWithIncorrectAns);
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
			System.out.println("Selected :" + answerOption1.getText());
			selectedAns = answerOption1.getText();
		}
		if (answerOption2.isSelected()) {
			System.out.println("Selected :" + answerOption2.getText());
			selectedAns = answerOption2.getText();
		}
		if (answerOption3.isSelected()) {
			System.out.println("Selected :" + answerOption3.getText());
			selectedAns = answerOption3.getText();
		}
		if (answerOption4.isSelected()) {
			System.out.println("Selected :" + answerOption4.getText());
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
			System.out.println(selectedAns + " : " + currentQuestion.getCorrectAnswer());
			if (!selectedAns.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
				queWithIncorrectAns.add(currentQuestion);
				System.out.println("verifySubmittedQuiz :" + queWithIncorrectAns);
			} else {
				System.out.println("verifySubmittedQuiz : " + queWithIncorrectAns.contains(currentQuestion));
				if (queWithIncorrectAns.contains(currentQuestion)) {
					queWithIncorrectAns.remove(currentQuestion);
					System.out.println("verifySubmittedQuiz :" + queWithIncorrectAns);
				}
			}
		}

		System.out.println("Verify your submitted quiz.");
		System.out.println();
		System.out.println(queWithIncorrectAns);
		if (queWithIncorrectAns.size() == 0) {
			// endQuiz();
			successMessage.setText("Woah!!! You answered all questions correctly!");
			successMessage.setFill(Color.GREEN);
			showCongrats();
			nextButton.setDisable(true);
			giveupButton.setText("Close");
			System.out.println("All answers are correct!!!");
		} else {
			currentQuestionNumber = 0;
			nextButton.setText("Next");
			System.out.println();
			System.out.println();
			questions = new ArrayList<>(queWithIncorrectAns);
			queWithIncorrectAns = new ArrayList<>();
			System.out.println("questions : " + questions);
			System.out.println("queWithIncorrectAns : " + queWithIncorrectAns);
			System.out.println();
			System.out.println();
			loadWrongAnswerdQuestions();
		}
	}

	private void showCongrats() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/exitPage.fxml"));
		Parent root = loader.load();
		giveupButton.getScene().setRoot(root);
	}

	private void setQuizTitleLabel(String selectedQuiz) {
		QuizName.setText(selectedQuiz);
	}

	/**
	 * Set the Questions and Answers
	 */
	private void setQuestions() {
		System.out.println("-----" + currentQuestionNumber);
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
