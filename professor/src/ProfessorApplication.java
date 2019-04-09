import controller.ProfessorController;
import controller.QuizController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * ProfessorApplication class has method to facilitate change of scenes.
 *
 * @author Darshan Prakash
 */

public class ProfessorApplication extends Application {

    @Override
    public void start(Stage quizWindow) throws Exception {

        quizWindow.setTitle("Hello Professor");

        FXMLLoader professorPaneLoader = new FXMLLoader(getClass().getResource("view/professor.fxml"));
        Parent professorPane = professorPaneLoader.load();
        Scene professorScene = new Scene(professorPane, 800, 600);

        FXMLLoader createQuizPaneLoader = new FXMLLoader(getClass().getResource("view/CreateQuiz.fxml"));
        Parent createQuizPane = createQuizPaneLoader.load();
        Scene createQuizScene = new Scene(createQuizPane, 800, 600);

        ProfessorController professorPaneController = professorPaneLoader.getController();
        professorPaneController.setCreateQuizScene(createQuizScene);

        QuizController createQuizPaneController = createQuizPaneLoader.getController();
        createQuizPaneController.setProfessorScene(professorScene);

        quizWindow.setScene(professorScene);
        quizWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
