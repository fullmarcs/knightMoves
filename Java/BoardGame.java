/**
 * Created by Marc on Tue 15/August.
 */
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class BoardGame extends Application {
    private BoardView view;
    private Board model = new Board(8);

    public void start(Stage primaryStage) {
        view = new BoardView(model);

        for (int i = 1; i <= model.getGridSize(); i++) {
            for (int j = 1; j <= model.getGridSize(); j++) {
                view.getGrid()[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for (int k = 1; k <= model.getGridSize(); k++) {
                            for (int l = 1; l <= model.getGridSize(); l++) {
                                if (event.getSource() == view.getGrid()[k][l]) {
                                    if (model.getStart().getX() == 0) { model.setStart(new Point2D(k, l)); }
                                    else if (model.getStart() != new Point2D(k, l)) { model.setEnd(new Point2D(k, l)); }
                                }
                            }
                        }
                        view.update();
                    }
                });
            }
        }

        view.getReset().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.setStart(new Point2D(0, 0));
                model.setEnd(new Point2D(0, 0));
                view.update();
            }
        });

        primaryStage.setTitle("Knight Moves");
        primaryStage.setScene(new Scene(view, 600, 650));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
