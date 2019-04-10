package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import model.StudentModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller to handle the requests when student application launches
 */

public class Student implements Initializable {

	@FXML
	private ListView<String> quizList;

	private ObservableList quizNames = FXCollections.observableArrayList();

	private StudentModel studentModel = new StudentModel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getQuizNames();
		listViewEventListener();
	}

	private void getQuizNames() {
		quizNames.clear();
		quizNames.addAll(studentModel.getQuizNames());
		quizList.getItems().addAll(quizNames);
	}

	/**
	 * To load the Quiz in the window
	 * 
	 * @param selectedQuiz
	 * @throws IOException
	 */
	private void loadQuiz(String selectedQuiz) throws IOException {
		DisplayQuiz questionAnswerObj;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/showQuiz.fxml"));
		Parent root = loader.load();
		questionAnswerObj = loader.getController();
		questionAnswerObj.loadQuiz(selectedQuiz);
		quizList.getScene().setRoot(root);
	}

	public void listViewEventListener() {
		quizList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String selectedQuiz) {
				try {
					loadQuiz(selectedQuiz);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}