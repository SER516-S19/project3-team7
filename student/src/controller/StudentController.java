package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class to display the list of Quiz to take
 */
public class StudentController implements Initializable {

    @FXML
    private ListView<String> quizList;

    private ObservableList quizNames =  FXCollections.observableArrayList();

    ShowQuiz questionAnswerObj = new ShowQuiz();

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
            public void changed(ObservableValue<? extends String> observable, String oldValue, String selectedQuiz) {
                //System.out.println("Selected item: " + selectedQuiz);
                questionAnswerObj.fetchQuizDetails(selectedQuiz);
            }
        });
    }

}
