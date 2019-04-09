package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * ProfessorController class has methods to load quiz list and change to create quiz scene.
 *
 * @author Darshan Prakash
 */

public class ProfessorController implements Initializable {

    @FXML
    private ListView<String> quizList;

    private ObservableList<String> quizNames = FXCollections.observableArrayList();

    private ArrayList<String> listOfQuizNames = new ArrayList<>();

    private Scene createQuizScene;

    public void setCreateQuizScene(Scene scene) {
        createQuizScene = scene;
    }

    public void openCreateQuizScene(ActionEvent actionEvent) {
        Stage quizWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        quizWindow.setScene(createQuizScene);
    }

    public ProfessorController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadQuizzes();
    }

    private void loadQuizzes() {
        File newFile = new File("../quiz");
        File[] allFiles = newFile.listFiles((dir1, name) -> name.endsWith(".json"));

        assert allFiles != null;
        for (File file : allFiles)
            listOfQuizNames.add(file.getName().substring(0, file.getName().lastIndexOf('.')));
        Collections.sort(listOfQuizNames);
        quizList.refresh();
        quizNames.clear();
        quizNames.addAll(listOfQuizNames);
        quizList.setItems(quizNames);
        quizList.setCellFactory(param -> new XCell());
    }

    public void selectQuiz() {
        String selectedItem = quizList.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
    }

    static class XCell extends ListCell<String> {
        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button edit = new Button("Edit Quiz");
        Button delete = new Button("Delete");
        String lastItem;

        XCell() {
            super();
            hbox.getChildren().addAll(label, pane, edit, delete);
            hbox.setSpacing(30.0);
            HBox.setHgrow(pane, Priority.ALWAYS);
            edit.setOnAction(event -> System.out.println("Edit " + lastItem));
            delete.setOnAction(event -> System.out.println("Delete " + lastItem));
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
}

