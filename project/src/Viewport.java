import processing.core.PApplet;
import processing.event.MouseEvent;

public final class Viewport
{
    public int row;
    public int col;
    public final int numRows;
    public final int numCols;

    //GETTERS

    public int getNumCols() {
        return this.numCols;
    }

    public int getNumRows() {
        return this.numRows;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }


    //FUNCTIONS

    public Viewport(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
    }

    public void shift(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public boolean contains(Point p) {
        return p.y >= this.row && p.y < this.row + this.numRows
                && p.x >= this.col && p.x < this.col + this.numCols;
    }

    public Point viewportToWorld(int col, int row) {
        return new Point(col + this.col, row + this.row);
    }

    public Point worldToViewport(int col, int row) {
        return new Point(col - this.col, row - this.row);
    }


}
