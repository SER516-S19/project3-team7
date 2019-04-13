package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Controller to handle the requests when professor application launches
 *
 * @author Darshan Prakash
 */

public class Professor implements Initializable {

    @FXML
    private ListView<String> quizList;

    @FXML
    private Button exitButton;

    private ObservableList<String> quizNames = FXCollections.observableArrayList();

    private ArrayList<String> listOfQuizNames = new ArrayList<>();

    private Scene createQuizScene;
        
    private Scene professorScene;
    
    public void setProfessorScene(Scene scene) {
    	professorScene = scene;
    }

    public void setCreateQuizScene(Scene scene) {
        createQuizScene = scene;
    }

    public void openCreateQuizScene(ActionEvent actionEvent) {
        Stage quizWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        quizWindow.setScene(createQuizScene);
    }
    

    public Professor() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadQuizzes();
    }

    public void loadQuizzes() {
        File newFile = new File("quiz");
        System.out.println("Absolute file path:"+newFile.getAbsolutePath());
        File[] allFiles = newFile.listFiles((dir1, name) -> name.endsWith(".json"));
        assert allFiles != null;
        for (File file : allFiles)
            listOfQuizNames.add(file.getName().substring(0, file.getName().lastIndexOf('.')));
        showHome(listOfQuizNames);

    }

    private void showHome(List listOfQuizNames) {

        Collections.sort(listOfQuizNames);
        quizList.refresh();
        quizNames.clear();
        quizNames.addAll(listOfQuizNames);
        quizList.setItems(quizNames);
        quizList.setCellFactory(param -> new XCell());
    }

    public String selectQuiz() {
        String selectedItem = quizList.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        return selectedItem;
    }

    /**
     * Function to delete the quiz and update the list of quiz
     * @param quiz
     */
    public void deleteQuiz(String quiz) {
        File file = new File("quiz/" + quiz + ".json");
        file.delete();
        listOfQuizNames.remove(quiz);
        System.out.println("Deleted" + quiz);
        showHome(listOfQuizNames);
    }

    public void loadCurrentQuiz(String quiz) throws IOException {
    	ViewQuiz.quiz = quiz;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/viewQuiz.fxml"));
        Parent root = loader.load();
        exitButton.getScene().setRoot(root);
    }

    class XCell extends ListCell<String> {
        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button modify = new Button("Modify Quiz");
        Button delete = new Button("Delete Quiz");
        String lastItem;

        XCell() {
            super();
            hbox.getChildren().addAll(label, pane, modify, delete);
            hbox.setSpacing(30.0);
            HBox.setHgrow(pane, Priority.ALWAYS);
            modify.setOnAction(event -> {
				try {
					loadCurrentQuiz(lastItem);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
            delete.setOnAction(event -> deleteQuiz(lastItem));

        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                label.setText(item != null ? item : "<null>");
                setGraphic(hbox);
            }
        }
    }

    public void exitQuiz(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}

