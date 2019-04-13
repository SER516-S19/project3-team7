package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Questions;
import model.ViewQuizDetails;


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
    
    @FXML
	private String correctOption;
    
    @FXML
	private Button prevQuestion;
    
    @FXML
	private Button nextQuestion;
    
    private ViewQuizDetails allQuestions;
    
    public static String quiz;

	public static Questions currentQuestion;
    
    private int questionNumber;
    
	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}
	
    public ViewQuizDetails getAllQuestions(String quizPath){
    	ViewQuizDetails questions = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            questions = objectMapper.readValue(new File(quizPath), ViewQuizDetails.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return questions;
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		String quizPath;
    	if(null != this.quiz) {
    		quizPath = "quiz/"+quiz+".json";
    		System.out.print("Quiz Path:"+quizPath);
    		allQuestions  = getAllQuestions(quizPath);
    		questionNumber = 0;
    		currentQuestion = allQuestions.getQuestions().get(questionNumber);
    		initializeNextAndPrev();
    		loadDataOnUI(currentQuestion);
    	}
	}
	
	private void initializeNextAndPrev() {
		int totalSize = allQuestions.getQuestions().size();
		if(totalSize == 1) {
			this.prevQuestion.setDisable(true);
			this.nextQuestion.setDisable(true);
		}else {
			this.prevQuestion.setDisable(true);
		}
	}

	public void loadDataOnUI(Questions question) {
		setQuizName(quiz.toUpperCase());
		setQuestion(question.getTitle());
		this.correctOption = question.getCorrectAnswer();
		setOption1(question.getOptions().get(0));
		setOption2(question.getOptions().get(1));
		setOption3(question.getOptions().get(2));
		setOption4(question.getOptions().get(3));
	}
	
	public void setQuizName(String quizName) {
		this.quizName.setText(quizName);;
	}
	
	public void setQuestion(String title) {
		this.question.setText(title);
	}

	public void setOption1(String option) {
		this.option1.setText(option);
		if(option.equals(correctOption)) {
			option1.setSelected(true);
		}
	}

	public void setOption2(String option) {
		this.option2.setText(option);
		if(option.equals(correctOption)) {
			option2.setSelected(true);
		}
	}

	public void setOption3(String option) {
		this.option3.setText(option);
		if(option.equals(correctOption)) {
			option3.setSelected(true);
		}
	}

	public void setOption4(String option) {
		this.option4.setText(option);
		if(option.equals(correctOption)) {
			option4.setSelected(true);
		}
	}
   
    public void nextQuestion(javafx.event.ActionEvent actionEvent) {
    	questionNumber += 1;
    	Questions question = null;
    	int totalQuestions = allQuestions.getQuestions().size();
    	
    	if(questionNumber < totalQuestions) {
    		question = allQuestions.getQuestions().get(questionNumber);
    		if(questionNumber == totalQuestions-1)
    				this.nextQuestion.setDisable(true);
    		this.prevQuestion.setDisable(false);
    		loadDataOnUI(question);	
    	}	
	}
    
    public void previousQuestion(javafx.event.ActionEvent actionEvent) {
    	questionNumber -= 1;
    	Questions question = null;
    	
    	if(questionNumber >= 0) {
    		question = allQuestions.getQuestions().get(questionNumber);
    		if(questionNumber == 0)
				this.prevQuestion.setDisable(true);
    		this.nextQuestion.setDisable(false);
    		loadDataOnUI(question);	
    	}
	}
    
    public void home(javafx.event.ActionEvent actionEvent) throws IOException {
		Stage viewQuizWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		viewQuizWindow.close();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Professor.fxml"));
		Parent root = loader.load(); 
		Stage primaryStage = new Stage();
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.show();
	}
    
    public void deleteQuestion(){

    }

    public void editQuestion(){
        question.setEditable(true);
    }
    
}
