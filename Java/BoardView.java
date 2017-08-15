/**
 * Created by Marc on Tue 15/August.
 */
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class BoardView extends GridPane {
    private int gridSize;
    private Button[][] grid;
    private Board model;
    private Label dialog = new Label("Please select the starting tile.");
    private Button reset = new Button("Reset");

    public BoardView(Board b) {
        model = b;
        gridSize = model.getGridSize();

        grid = new Button[gridSize+1][gridSize+1];

        this.setMinSize(220,220);
        this.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.setPadding(new Insets(10));
        this.setHgap(1);
        this.setVgap(1);

        // Make button array
        for (int i = 1; i <= gridSize; i++) {
            this.add(new Label(String.format("%d", i)), i, 0);
            for (int j = 1; j <= gridSize; j++) {
                this.add(new Label(String.format("%d", j)), 0, j);
                grid[i][j] = new Button();
                if ((i + j) % 2 == 1) { grid[i][j].setStyle("-fx-base: #000000"); } else { grid[i][j].setStyle("-fx-base: #ffffff"); }
                grid[i][j].setMinSize(50,50);
                grid[i][j].setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
                this.add(grid[i][j], i, j);
            }
        }

        reset.setPrefSize(Integer.MAX_VALUE,50);

        this.add(dialog, 1, gridSize + 1, gridSize - 1, 1);
        this.add(reset, gridSize, gridSize + 1);
    }

    public void update() {
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                if ((i + j) % 2 == 1) { grid[i][j].setStyle("-fx-base: #000000"); } else { grid[i][j].setStyle("-fx-base: #ffffff"); }
                if (i == model.getStart().getX() && j == model.getStart().getY()) { grid[i][j].setStyle("-fx-base: #00c000"); }
                if (i == model.getEnd().getX() && j == model.getEnd().getY()) { grid[i][j].setStyle("-fx-base: #0000c0"); }
            }
        }
        if (model.getStart().getX() != 0 && model.getEnd().getX() == 0) { dialog.setText("Please select the ending tile."); }
        if (model.getStart().getX() != 0 && model.getEnd().getX() != 0) { dialog.setText("This would take " + numMoves(model.getStart(), model.getEnd()) + " moves."); }
    }

    private int numMoves(Point2D start, Point2D end) {
        ArrayList<Point2D> pass1 = listOfSpots(start);
        if (pass1.contains(end)) { return 1; }

        ArrayList<Point2D> pass2 = listOfSpotLists(pass1);
        if (pass2.contains(end)) { return 2; }

        ArrayList<Point2D> pass3 = listOfSpotLists(pass2);
        if (pass3.contains(end)) { return 3; }

        ArrayList<Point2D> pass4 = listOfSpotLists(pass3);
        if (pass4.contains(end)) { return 4; }

        ArrayList<Point2D> pass5 = listOfSpotLists(pass4);
        if (pass5.contains(end)) { return 5; }

        ArrayList<Point2D> pass6 = listOfSpotLists(pass5);
        if (pass6.contains(end)) { return 6; }

        return 0;
    }

    private ArrayList<Point2D> listOfSpots(Point2D start) {
        double a = start.getX(), b = start.getY();
        ArrayList<Point2D> list = new ArrayList();
        if (a + 2 <= 8 && b + 1 <= 8) { list.add(new Point2D(a + 2, b + 1)); }
        if (a + 2 <= 8 && b - 1 >= 1) { list.add(new Point2D(a + 2, b - 1)); }
        if (a - 2 >= 1 && b + 1 <= 8) { list.add(new Point2D(a - 2, b + 1)); }
        if (a - 2 >= 1 && b - 1 >= 1) { list.add(new Point2D(a - 2, b - 1)); }
        if (a + 1 <= 8 && b + 2 <= 8) { list.add(new Point2D(a + 1, b + 2)); }
        if (a + 1 <= 8 && b - 2 >= 1) { list.add(new Point2D(a + 1, b - 2)); }
        if (a - 1 >= 1 && b + 2 <= 8) { list.add(new Point2D(a - 1, b + 2)); }
        if (a - 1 >= 1 && b - 2 >= 1) { list.add(new Point2D(a - 1, b - 2)); }
        return list;
    }

    private ArrayList<Point2D> listOfSpotLists(ArrayList<Point2D> list) {
        ArrayList<Point2D> newList = new ArrayList();
        for (Point2D l: list) {
            newList.addAll(listOfSpots(l));
        }
        return newList;
    }

    protected Button[][] getGrid() { return grid; }
    protected Button getReset() { return reset; }
}
