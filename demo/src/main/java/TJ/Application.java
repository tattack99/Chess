package TJ;
import TJ.View.*;
import TJ.Model.*;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Board model = new Board();
        View view = new View(model, primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
