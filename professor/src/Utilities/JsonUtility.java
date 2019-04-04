package Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Questions;
//import model.QuestionImpl;

import java.io.File;
import java.io.IOException;

//import model.Question;

/**
 * Read README.md file to get rid of the errors.
 * JsonUtility class has method to convert an object to Json and store it in file.
 *
 * @author Aravinda Sai Kondamari
 * @version 1.0
 * @since 1.0
 */

public class JsonUtility {

    /**
     * This method accepts a QuestionImpl object converts it in to Json and stores
     * in a file with the name from "title" parameter.
     *
     * @param questions
     * @param title
     */
    public void writeToJson(Questions questions, String title) {
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = "quizzes/" + title + ".json";
        try {
            File newFile = new File(filePath);
            newFile.createNewFile();
            objectMapper.writeValue(newFile, questions);
        } catch (IOException e) {
            System.out.println("Exception while generating file.");
            e.printStackTrace();
        }
    }
}
