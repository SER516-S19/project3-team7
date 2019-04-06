package controller;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.QuizDetails;

import java.io.File;
import java.io.IOException;

/**
 * Class to render Question and Answer from the Json file
 */
public class ShowQuiz {

    @FXML
    public Label quizName;

    @FXML
    public Text questionTitle;


    public QuizDetails quiz;

    public void fetchQuizDetails(String selectedQuiz){
        int _INIT = 0;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            quiz = objectMapper.readValue(new File("quiz/"+selectedQuiz+".json"), QuizDetails.class);
            System.out.println(quiz);
            loadQuestionAnswer(_INIT);
        }
        catch (JsonParseException e) { e.printStackTrace();}
        catch (JsonMappingException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void endQuiz(){}

    public void loadQuestionAnswer(int index) {
        String quesTitle = quiz.getQuestions().get(index).getTitle();
        try {
            questionTitle.setText(quesTitle);
        }catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

   public void showNextQuestion(){ }

    public void submitQuiz(){
        //TODO add code for submit quiz
    }

    public void setQuizTitleLabel(String selectedQuiz) {

        quizName.setText(selectedQuiz);
    }

}

