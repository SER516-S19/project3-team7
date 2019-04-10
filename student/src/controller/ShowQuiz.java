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
import javafx.scene.paint.Color;
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
	
	@FXML
	private Text successMessage;

	public RadioButton option1;

	public RadioButton option2;

	public RadioButton option3;

	public RadioButton option4;

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
	 * @param selectedQuiz
	 */
	public void loadQuiz(String selectedQuiz) {
		setQuizTitleLabel(selectedQuiz);
		QuizDetails quiz = studentModel.readQuizDetails(selectedQuiz);
		questions.addAll(quiz.getQuestions());
		setQuestions();
	}

	/**
	 * Function to load the wrong answered questions
	 */
	public void loadWrongAnswerdQuestions() {
		setQuizTitleLabel(QuizName.getText());
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
	public void showNextQuestion() {
		int quizSize = questions.size();
		System.out.println("quizSize : "+quizSize);
		String submissionType = nextButton.getText();
		if("Submit".equalsIgnoreCase(submissionType)) {
			verifySubmittedQuiz();
		}else {
			if (currentQuestionNumber < quizSize) {
				System.out.println("currentQuestionNumber : "+currentQuestionNumber);
				System.out.println("questions.get(currentQuestionNumber-1) : "+questions.get(currentQuestionNumber-1));
				Question currentQuestion = questions.get(currentQuestionNumber-1);
				System.out.println(selectedAns + " : "+ currentQuestion.getCorrectAnswer());
				if(selectedAns!=null && currentQuestion.getCorrectAnswer()!=null) {
					if(!selectedAns.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
						queWithIncorrectAns.add(currentQuestion);
					}else {
						if(queWithIncorrectAns.contains(currentQuestion)) {
							queWithIncorrectAns.remove(currentQuestion);
							System.out.println("showNextQuestion :"+queWithIncorrectAns);
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
		currentQuestionNumber--;
		Question currentQuestion = questions.get(currentQuestionNumber);
		if(selectedAns!=null && currentQuestion.getCorrectAnswer()!=null) {
			System.out.println(selectedAns + " : "+ currentQuestion.getCorrectAnswer());
			if(!selectedAns.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
				queWithIncorrectAns.add(currentQuestion);
				System.out.println("verifySubmittedQuiz :"+queWithIncorrectAns);
			}else {
				System.out.println("verifySubmittedQuiz : "+queWithIncorrectAns.contains(currentQuestion));
				if(queWithIncorrectAns.contains(currentQuestion)) {
					queWithIncorrectAns.remove(currentQuestion);
					System.out.println("verifySubmittedQuiz :"+queWithIncorrectAns);
				}
			}
		}
		
		
		System.out.println("Verify your submitted quiz.");
		System.out.println();
		System.out.println(queWithIncorrectAns);
		if(queWithIncorrectAns.size()==0) {
			//endQuiz();
			successMessage.setText("Woah!!! You answered all questions correctly!");
			successMessage.setFill(Color.GREEN);
			nextButton.setDisable(true);
			System.out.println("All answers are correct!!!");
		}else {
			currentQuestionNumber = 0;
			nextButton.setText("Next");
			System.out.println();
			System.out.println();
			questions = new ArrayList<>(queWithIncorrectAns);
			queWithIncorrectAns = new ArrayList<>();
			System.out.println("questions : "+ questions);
			System.out.println("queWithIncorrectAns : "+ queWithIncorrectAns);
			System.out.println();
			System.out.println();
			loadWrongAnswerdQuestions();
		}
	}

	private void setQuizTitleLabel(String selectedQuiz) {
		QuizName.setText(selectedQuiz);
	}



	/**
	 * To set the Questions and Answers
	 */
	private void setQuestions() {
		System.out.println("-----"+currentQuestionNumber);
		
		Question question = questions.get(currentQuestionNumber);
		questionNumber.setText(currentQuestionNumber+1 + ")");
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
