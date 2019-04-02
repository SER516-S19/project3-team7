package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ProfessorController implements Initializable {

    @FXML
    private ListView<String> quizList;

    private ObservableList<Object> quizNames =  FXCollections.observableArrayList();

    private ArrayList<String> listOfQuizNames;

    public ProfessorController() {
        listOfQuizNames = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String cwd = System.getProperty("user.dir").replaceFirst("professor","quiz");
        File dir = new File(cwd);
        File[] allFiles = dir.listFiles((dir1, name) -> name.startsWith("quiz") && name.endsWith(".json"));

        assert allFiles != null;
        for (File file : allFiles)
            listOfQuizNames.add(file.getName().substring(0, file.getName().toString().lastIndexOf('.')));
        Collections.sort(listOfQuizNames);

    }

    public void loadQuizzes(){
        quizList.getItems().clear();
        quizNames.clear();

        quizNames.addAll(listOfQuizNames);
        quizList.getItems().addAll(String.valueOf(quizNames));
    }

    public void createQuiz(ActionEvent actionEvent) {
        //code to change scene to create quiz
    }
}
