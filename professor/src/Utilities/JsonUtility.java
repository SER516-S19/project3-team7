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

    /*
    public static void main(String a[]){
        Question q1 = new Question();
        q1.setTitle("How are you?");
        q1.setOption("Good");
        q1.setOption("Awesome");
        q1.setOption("Bad");
        q1.setOption("Not Sure");
        q1.setCorrectAnswer("Awesome");

        Question q2 = new Question();
        q2.setTitle("Are you asurite?");
        q2.setOption("Yes");
        q2.setOption("No");
        q2.setOption("Not sure");
        q2.setOption("I like to not share");
        q2.setCorrectAnswer("Yes");

        QuestionImpl questions = new QuestionImpl();
        questions.setQuestion(q1);
        questions.setQuestion(q2);
        new JsonUtility().writeToJson(questions, "SampleQuiz");
    }
    */
}
