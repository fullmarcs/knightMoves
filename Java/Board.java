/**
 * Created by Marc on Tue 15/August.
 */

import javafx.geometry.Point2D;

public class Board {
    private int gridSize;
    private Point2D start = new Point2D(0, 0);
    private Point2D end = new Point2D(0, 0);

    public Board(int size) {
        gridSize = size;
    }

    protected int getGridSize() { return gridSize; }
    protected Point2D getStart() { return start; }
    protected void setStart(Point2D s) { start = s; }
    protected Point2D getEnd() { return end; }
    protected void setEnd(Point2D e) { end = e; }
}
