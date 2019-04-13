package Utilities;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Quiz;

import java.io.File;
import java.io.IOException;


/**
 * JsonUtility class has method to convert an object to Json and store it in file.
 *
 * @author Nikhila Saini
 */

public class JsonUtility {

    /**
     * This method accepts a Question object converts it in to Json and stores
     * in a file with the name from "title" parameter.
     *
     * @param quiz
     * @param title
     */
    public void writeToJson(Quiz quiz, String title) {
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = System.getProperty("user.dir")+"/quiz/" + title + ".json";
        try {
            File newFile = new File(filePath);
            newFile.createNewFile();
            objectMapper.writeValue(newFile, quiz);
        } catch (IOException e) {
            System.out.println("Exception while generating file.");
            e.printStackTrace();
        }
    }

    /**
     * Reads the Quiz json file and maps it to Quiz Class
     * @param quizPath name of Quiz file
     * @return Quiz
     */
    public Quiz getAllQuestionsFromFile(String quizPath){
        Quiz questions = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            questions = objectMapper.readValue(new File(quizPath), Quiz.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
