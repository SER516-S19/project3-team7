package Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Questions;
import model.Quiz;

import java.io.File;
import java.io.IOException;
import java.util.List;


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
        String filePath = "quiz/" + title + ".json";
        try {
            File newFile = new File(filePath);
            newFile.createNewFile();
            objectMapper.writeValue(newFile, quiz);
        } catch (IOException e) {
            System.out.println("Exception while generating file.");
            e.printStackTrace();
        }
    }
}
