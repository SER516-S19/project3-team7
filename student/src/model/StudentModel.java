package model;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Manipulation and Business Logic for the student
 */

public class StudentModel {

    private List<String> quizNames = new ArrayList<>();

    public String getSelectedQuizName() {
        return selectedQuizName;
    }

    public void setSelectedQuizName(String selectedQuizName) {
        this.selectedQuizName = selectedQuizName;
    }

    private static String selectedQuizName;

    /**
     * Read the names of the quiz files created by the professor
     *
     * @return Quiz Names
     */
    public List<String> getQuizNames() {
        File folder = new File("quiz");
        File[] listOfFiles = folder.listFiles();
        for(int i =0;i<listOfFiles.length;i++){
            quizNames.add(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().indexOf(".")));
        }
        return quizNames;
    }

    /**
     * Read the quiz details from the json files and parse it to java object
     *
     * @param quizName
     * @return QuizDetails object
     */
    public QuizDetails readQuizDetails(String quizName){
        QuizDetails quizDetails = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            quizDetails = objectMapper.readValue(new File("quiz/" + quizName + ".json"), QuizDetails.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return quizDetails;
    }
}
