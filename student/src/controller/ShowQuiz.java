package controller;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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

    public Text questionTitle;
    public Text questionNumber;

    public RadioButton option1;
    public RadioButton option2;
    public RadioButton option3;
    public RadioButton option4;


    String questionTitleText;

    String option1Text;
    String option2Text;
    String option3Text;
    String option4Text;

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
        questionTitleText = quiz.getQuestions().get(index).getTitle();
        option1Text = quiz.getQuestions().get(index).getOptions().get(0);
        option2Text = quiz.getQuestions().get(index).getOptions().get(1);
        option3Text = quiz.getQuestions().get(index).getOptions().get(2);
        option4Text = quiz.getQuestions().get(index).getOptions().get(3);

        try {
            questionNumber.setText(Integer.toString(index + 1));
            questionTitle.setText(questionTitleText);
            option1.setText(option1Text);
            option2.setText(option2Text);
            option3.setText(option3Text);
            option4.setText(option4Text);
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

