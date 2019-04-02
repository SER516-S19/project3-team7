package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    private ListView<String> quizList;

    private ObservableList quizNames =  FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       getQuizNames(); listViewEventListener();
    }

    public void getQuizNames(){
        quizNames.add("Quiz1");
        quizNames.add("Quiz2");
        quizNames.add("Quiz3");
        quizList.getItems().addAll(quizNames);
    }


    public void listViewEventListener() {

        quizList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                System.out.println("Selected item: " + newValue);
            }
        });
    }
}
