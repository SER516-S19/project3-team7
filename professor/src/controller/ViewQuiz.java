package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import model.Questions;
import model.ViewQuizDetails;


public class ViewQuiz implements Initializable{
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
    
    public static String quiz;
    
    public static Questions currentQuestions;
    
	private Scene professorScene;
		
    
    public void setProfessorScene(Scene scene) {
		professorScene = scene;
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
    	if(null != quiz) {
    		quizPath = "project3-team7/quiz/"+quiz+".json";
    		System.out.print("Quiz Path:"+quizPath);
    		ViewQuizDetails allQuestions  = getAllQuestions(quizPath);
    		currentQuestions = allQuestions.getQuestions().get(0);
    		loadDataOnUI(currentQuestions);
    	}
	}
	
	public void loadDataOnUI(Questions question) {
		setQuestion(question.getTitle());
		this.correctOption = question.getCorrectAnswer();
		setOption1(question.getOptions().get(0));
		setOption2(question.getOptions().get(1));
		setOption3(question.getOptions().get(2));
		setOption4(question.getOptions().get(3));
	}

	public void setOption1(String option) {
		option1.setText(option);
		if(option.equals(correctOption)) {
			option1.setSelected(true);
		}
	}

	public void setOption2(String option) {
		option2.setText(option);
		if(option.equals(correctOption)) {
			option1.setSelected(true);
		}
	}

	public void setOption3(String option) {
		option3.setText(option);
		if(option.equals(correctOption)) {
			option1.setSelected(true);
		}
	}

	public void setOption4(String option) {
		option4.setText(option);
		if(option.equals(correctOption)) {
			option1.setSelected(true);
		}
	}
    
	public void setQuestion(String title) {
		question.setText(title);
	}

	public void nextQuestion(){

    }

    public void deleteQuestion(){

    }

    public void editQuestion(){
        question.setEditable(true);
    }
   
    public void nextQuestion(javafx.event.ActionEvent actionEvent) {
		/*Stage quizWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		quizWindow.setScene(professorScene); */
	}
    
}
