package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    private ListView<String> quizList;

    private ObservableList quizNames =  FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       getQuizNames();
    }

    public void getQuizNames(){
        quizNames.add("Quiz1");
        quizNames.add("Quiz2");
        quizNames.add("Quiz3");
        quizList.getItems().addAll(quizNames);
    }
}
