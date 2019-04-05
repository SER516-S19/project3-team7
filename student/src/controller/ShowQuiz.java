package controller;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import model.Question;
import model.QuizDetails;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class to render Question and Answer from the Json file
 */
public class ShowQuiz {

    @FXML
    public Label QuizName;
    
    private QuizDetails quiz;
    
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

    
    private int currentQuestionNumber = 1;

    public void fetchQuizDetails(String selectedQuiz){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            quiz = objectMapper.readValue(new File("quiz/"+selectedQuiz+".json"), QuizDetails.class);
            setQuestions();
            System.out.println(quiz);
        }
        catch (JsonParseException e) { e.printStackTrace();}
        catch (JsonMappingException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void endQuiz(){}

	public void showNextQuestion() {
		if (currentQuestionNumber <= quiz.getQuestions().size()) {
			setQuestions();
		} else {
			
		}
	}

    public void setQuizTitleLabel(String selectedQuiz) {

        QuizName.setText(selectedQuiz);
    }
    
    
	public void setQuestions() {
		Question question = quiz.getQuestions().get(currentQuestionNumber - 1);
		questionNumber.setText(currentQuestionNumber + ")");
		questionTitle.setText(question.getTitle());
		currentQuestionNumber = currentQuestionNumber + 1;
		List<String> options = question.getOptions();

		option1.setText(options.get(0));
		option2.setText(options.get(1));
		option3.setText(options.get(2));
		option4.setText(options.get(3));
	}


}

