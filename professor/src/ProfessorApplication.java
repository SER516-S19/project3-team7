import controller.Professor;
import controller.ViewQuiz;
import controller.CreateQuiz;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launches the professor application
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
        
        FXMLLoader viewQuizPaneLoader = new FXMLLoader(getClass().getResource("view/viewQuiz.fxml"));
        Parent viewQuizPane = viewQuizPaneLoader.load();
        Scene viewQuizScene = new Scene(viewQuizPane, 800, 600);

        Professor professorPaneController = professorPaneLoader.getController();
        professorPaneController.setCreateQuizScene(createQuizScene);
        professorPaneController.setViewQuizScene(viewQuizScene);

        CreateQuiz createQuizPaneController = createQuizPaneLoader.getController();
        createQuizPaneController.setProfessorScene(professorScene);
        
        ViewQuiz viewQuizPaneController = viewQuizPaneLoader.getController();
        viewQuizPaneController.setProfessorScene(professorScene);

        quizWindow.setScene(professorScene);
        quizWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
