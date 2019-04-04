package Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Questions;
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
