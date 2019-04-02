package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ProfessorController implements Initializable {

    @FXML
    private ListView<String> quizList;

    private ObservableList<String> quizNames =  FXCollections.observableArrayList();

    private ArrayList<String> listOfQuizNames = new ArrayList<>();

    private String selectedItem;

    static class XCell extends ListCell<String> {
        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button button = new Button("Edit Quiz");
        String lastItem;

        XCell() {
            super();
            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(event -> System.out.println(lastItem + " : " + event));
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
                label.setText(item!=null ? item : "<null>");
                setGraphic(hbox);
            }
        }
    }

    public ProfessorController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String cwd = System.getProperty("user.dir").replaceFirst("professor","quiz");
        File dir = new File(cwd);
        File[] allFiles = dir.listFiles((dir1, name) -> name.startsWith("quiz") && name.endsWith(".json"));

        assert allFiles != null;
        for (File file : allFiles)
            listOfQuizNames.add(file.getName().substring(0, file.getName().lastIndexOf('.')));
        Collections.sort(listOfQuizNames);

    }

    public void loadQuizzes(){
        quizList.getItems().clear();
        quizNames.clear();
        quizNames.addAll(listOfQuizNames);
        quizList.setItems(quizNames);
        quizList.setCellFactory(param -> new XCell());
    }

    public void selectedQuiz(MouseEvent mouseEvent) {
        selectedItem = quizList.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
    }

    public void createQuiz(ActionEvent actionEvent) {
        //code to change scene to create quiz
    }


}

