package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ProfessorController implements Initializable {

    public int noOfQuestions=0;
    ArrayList<String> listOfQuizNames = new ArrayList<String>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // replace actual path of quiz directory string if it doesn't work in windows for now
        String cwd = System.getProperty("user.dir").replaceFirst("professor","quiz");
        File dir = new File(cwd);
        File[] allFiles = dir.listFiles((dir1, name) -> name.startsWith("quiz") && name.endsWith(".json"));

        for (File file : allFiles) {
            listOfQuizNames.add(file.getName().toString().substring(0,file.getName().toString().lastIndexOf('.')));
        }
        Collections.sort(listOfQuizNames);
        noOfQuestions = listOfQuizNames.size();
        if (listOfQuizNames.size() == 0) {
            System.out.println("No quizzes available");
        } else {
            for (String quiz : listOfQuizNames) {
                System.out.println(quiz);
            }
        }
    }

    public Button loadQuiz;
    public Button quizName1;
    public Button quizName2;
    public Button quizName3;
    public Button quizName4;
    public Button quizName5;
    public Button quizName6;
    public Button quizName7;
    public Button quizName8;
    public Button quizName9;
    public Button quizName10;
    public Button quizName11;
    public Button quizName12;
    public void loadQuizzes(){

    quizName1.setText(listOfQuizNames.get(0));
    quizName1.setVisible(true);
    quizName2.setText(listOfQuizNames.get(1));
    quizName2.setVisible(true);
    quizName3.setText(listOfQuizNames.get(2));
    quizName3.setVisible(true);
    quizName4.setText(listOfQuizNames.get(3));
    quizName4.setVisible(true);

    }

    public void createQuiz(ActionEvent actionEvent) {
        //code to change scene to create quiz
    }
}
