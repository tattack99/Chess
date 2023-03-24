package TJ;
import TJ.Controller.Controller;
import TJ.View.*;
import TJ.Model.*;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Board model = new Board();
        View view = new View(model, primaryStage);
        Controller controller = new Controller(model, view);
        controller.init();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
