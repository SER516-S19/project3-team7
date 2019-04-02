package controller;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.QuizDetails;

import java.io.File;
import java.io.IOException;
public class QuestionAnswerController {

    public void fetchQuizDetails(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            QuizDetails quiz = objectMapper.readValue(new File("quiz/quiz1.json"), QuizDetails.class);
            System.out.println(quiz);
        }
        catch (JsonParseException e) { e.printStackTrace();}
        catch (JsonMappingException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }


    }
}

