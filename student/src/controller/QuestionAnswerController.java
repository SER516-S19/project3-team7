package controller;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.QuizDetails;
import java.io.File;
import java.io.IOException;

/**
 * Class to render Question and Answer from the Json file
 */
public class QuestionAnswerController {

    public void fetchQuizDetails(String selectedQuiz){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            QuizDetails quiz = objectMapper.readValue(new File("quiz/"+selectedQuiz+".json"), QuizDetails.class);
            System.out.println(quiz);
        }
        catch (JsonParseException e) { e.printStackTrace();}
        catch (JsonMappingException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

    }
}

