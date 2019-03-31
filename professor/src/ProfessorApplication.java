import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfessorApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/professor.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("view/CreateQuiz.fxml"));
        primaryStage.setTitle("Hello Professor");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(new Scene(root1, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
