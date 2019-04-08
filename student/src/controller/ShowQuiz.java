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
import java.util.LinkedList;
import java.util.List;

/**
 * Class to render the Quiz from the JSON file
 */
public class ShowQuiz {

	@FXML
	public Label QuizName;
	@FXML
	private Text questionTitle;
	@FXML
	private Text questionNumber;

	public RadioButton option1;

	public RadioButton option2;

	public RadioButton option3;

	public RadioButton option4;

	public Button nextButton;

	private List<Question> questions = new ArrayList<>();
	private List<Question> queWithIncorrectAns = null;
	private ToggleGroup radioButtonGroup = null;

	private String selectedAns = null;
	private int questionNo = 0;
	private int currentQuestionNumber = 1;

	StudentModel studentModel = new StudentModel();

	/**
	 * Function to load the question and options
	 * @param selectedQuiz
	 */
	public void loadQuiz(String selectedQuiz) {
		setQuizTitleLabel(selectedQuiz);
		QuizDetails quiz = studentModel.readQuizDetails(selectedQuiz);
		questions.addAll(quiz.getQuestions());
		setQuestions();
		queWithIncorrectAns = new LinkedList();
	}

	/**
	 * Function to load the wrong answered questions
	 */
	public void loadWrongAnswerdQuestions() {
		setQuizTitleLabel(QuizName.getText());
		setWrongQuestions();
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
	public void showNextQuestion() {
		int quizSize = questions.size();
		String submissionType = nextButton.getText();
		if("Submit".equalsIgnoreCase(submissionType)) {
			verifySubmittedQuiz();
		}else {
			if (currentQuestionNumber <= quizSize) {
				setQuestions();
				System.out.println(questionNo);
				Question currentQuestion = questions.get(questionNo++);
				if(selectedAns!=null && currentQuestion.getCorrectAnswer()!=null) {
					if(!selectedAns.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
						queWithIncorrectAns.add(currentQuestion);
					}
				}
			}
			if (currentQuestionNumber - 1 == quizSize) {
				nextButton.setText("Submit");	
			}
		}
	}

	public void setSelectedRadioButton() {
		if(option1.isSelected()) {
			System.out.println("Selected :"+option1.getText());
			selectedAns = option1.getText();
		}
		if(option2.isSelected()) {
			System.out.println("Selected :"+option2.getText());
			selectedAns = option2.getText();
		}
		if(option3.isSelected()) {
			System.out.println("Selected :"+option3.getText());
			selectedAns = option3.getText();
		}
		if(option4.isSelected()) {
			System.out.println("Selected :"+option4.getText());
			selectedAns = option4.getText();
		}
		
	}
	
	public void verifySubmittedQuiz() {
		System.out.println(questionNo);
		Question currentQuestion = questions.get(questionNo++);
		if(selectedAns!=null && currentQuestion.getCorrectAnswer()!=null) {
			if(!selectedAns.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
				queWithIncorrectAns.add(currentQuestion);
			}
		}
		System.out.println("Verify your submitted quiz.");
		if(queWithIncorrectAns.size()==0) {
			endQuiz();
			System.out.println("All answers are correct!!!");
		}else {
			questionNo = 0;
			nextButton.setText("Next");
			loadWrongAnswerdQuestions();
			queWithIncorrectAns.clear();
		}
	}

	private void setQuizTitleLabel(String selectedQuiz) {
		QuizName.setText(selectedQuiz);
	}

	/**
	 * To set the questions which answered wrongly
	 */
	private void setWrongQuestions() {
		Question question = queWithIncorrectAns.get(questionNo++);
		questionNumber.setText(questionNo + ")");
		questionTitle.setText(question.getTitle());
		List<String> options = question.getOptions();
		
		radioButtonGroup = new ToggleGroup();
			
		option1.setToggleGroup(radioButtonGroup);
		option2.setToggleGroup(radioButtonGroup);
		option3.setToggleGroup(radioButtonGroup);
		option4.setToggleGroup(radioButtonGroup);

		option1.setSelected(false);
		option2.setSelected(false);
		option3.setSelected(false);
		option4.setSelected(false);

		option1.setText(options.get(0));
		option2.setText(options.get(1));
		option3.setText(options.get(2));
		option4.setText(options.get(3));
	}

	/**
	 * To set the Questions and Answers
	 */
	private void setQuestions() {
		Question question = questions.get(currentQuestionNumber - 1);
		questionNumber.setText(currentQuestionNumber + ")");
		questionTitle.setText(question.getTitle());
		currentQuestionNumber = currentQuestionNumber + 1;
		List<String> options = question.getOptions();
		radioButtonGroup = new ToggleGroup();
			
		option1.setToggleGroup(radioButtonGroup);
		option2.setToggleGroup(radioButtonGroup);
		option3.setToggleGroup(radioButtonGroup);
		option4.setToggleGroup(radioButtonGroup);

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
