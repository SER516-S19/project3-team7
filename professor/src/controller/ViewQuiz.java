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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import model.Questions;
import model.Quiz;

/**
 * Controller to display and modify existing quiz
 */

public class ViewQuiz implements Initializable{
	
	@FXML
	public Label quizName;

	@FXML
    private TextArea question;
    
    @FXML
    private RadioButton option1;
    
    @FXML
    private RadioButton option2;
    
    @FXML
    private RadioButton option3;
    
    @FXML
    private RadioButton option4;

	private String correctOption;
    
    @FXML
	private Button prevQuestion;
    
    @FXML
	private Button nextQuestion;

    @FXML
	private Button exitQuiz;


    private Quiz quizDetails;
    
    public static String quiz;

	private static Questions currentQuestion;
    
    private int questionNumber;

    model.Quiz new_quiz = new model.Quiz();
    private List<Questions> questions = new ArrayList<Questions>();
    
	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}

    @Override
	public void initialize(URL location, ResourceBundle resources) {
		JsonUtility jsonUtility = new JsonUtility();
		String quizPath;
    	if(null != this.quiz) {
    		quizPath = "quiz/"+quiz+".json";
    		System.out.print("Quiz Path:"+quizPath);
    		quizDetails = jsonUtility.getAllQuestionsFromFile(quizPath);
    		questionNumber = 0;
    		new_quiz = quizDetails;
			System.out.println(new_quiz);
    		currentQuestion = quizDetails.getQuestions().get(questionNumber);
    		initializeNextAndPrev();
    		loadDataOnUI(currentQuestion);
    	}

	}

	/**
	 * Set next and previous buttons
	 */
	private void initializeNextAndPrev() {
		int totalSize = quizDetails.getQuestions().size();
		if(totalSize == 1) {
			this.prevQuestion.setDisable(true);
			this.nextQuestion.setDisable(true);
		}else {
			this.prevQuestion.setDisable(true);
		}
	}

	private void loadDataOnUI(Questions question) {
		setQuizName(quiz);
		setQuestion(question.getTitle());
		this.correctOption = question.getCorrectAnswer();
		setOption1(question.getOptions().get(0));
		setOption2(question.getOptions().get(1));
		setOption3(question.getOptions().get(2));
		setOption4(question.getOptions().get(3));
	}
	
	private void setQuizName(String quizName) {
		this.quizName.setText(quizName);;
	}
	
	private void setQuestion(String title) {
		this.question.setText(title);
	}

	private void setOption1(String option) {
		this.option1.setText(option);
		if(option.equals(correctOption)) {
			option1.setSelected(true);
		}
	}

	private void setOption2(String option) {
		this.option2.setText(option);
		if(option.equals(correctOption)) {
			option2.setSelected(true);
		}
	}

	private void setOption3(String option) {
		this.option3.setText(option);
		if(option.equals(correctOption)) {
			option3.setSelected(true);
		}
	}

	private void setOption4(String option) {
		this.option4.setText(option);
		if(option.equals(correctOption)) {
			option4.setSelected(true);
		}
	}

	/**
	 * Display next question
	 * @param actionEvent
	 */
    public void nextQuestion(javafx.event.ActionEvent actionEvent) {
    	questionNumber += 1;
    	Questions question = null;
    	int totalQuestions = quizDetails.getQuestions().size();
    	
    	if(questionNumber < totalQuestions) {
    		question = quizDetails.getQuestions().get(questionNumber);
    		if(questionNumber == totalQuestions-1)
    				this.nextQuestion.setDisable(true);
    		this.prevQuestion.setDisable(false);
    		loadDataOnUI(question);	
    	}	
	}

	/**
	 * Display previous question
	 * @param actionEvent
	 */
	public void previousQuestion(javafx.event.ActionEvent actionEvent) {
    	questionNumber -= 1;
    	Questions question = null;
    	
    	if(questionNumber >= 0) {
    		question = quizDetails.getQuestions().get(questionNumber);
    		if(questionNumber == 0)
				this.prevQuestion.setDisable(true);
    		this.nextQuestion.setDisable(false);
    		loadDataOnUI(question);	
    	}
	}

    public void editQuestion(){
        question.setEditable(true);
    }

	/**
	 * Store the modified quiz information
	 */
	public void saveQuiz() {
		quizDetails.getQuestions().get(questionNumber).setTitle(question.getText().trim());
        if (option1.isSelected())
            correctOption = option1.getText().trim();
        else if (option2.isSelected())
            correctOption = option2.getText().trim();
        else if (option3.isSelected())
            correctOption = option3.getText().trim();
        else if (option4.isSelected())
            correctOption = option4.getText().trim();

        quizDetails.getQuestions().get(questionNumber).setCorrectAnswer(correctOption);

    }

	/**
	 * Update the json file and exit from modify window
	 * @throws IOException
	 */
	public void exitQuiz() throws IOException{
        JsonUtility file = new JsonUtility();
        file.writeToJson(quizDetails, quizName.getText());
		FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/professor.fxml"));
		Parent root = loader.load();
		exitQuiz.getScene().setRoot(root);
    }

}
