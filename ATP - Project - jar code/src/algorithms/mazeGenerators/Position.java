package algorithms.mazeGenerators;
import java.io.Serializable;
/**
 * position class
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class Position implements Serializable{
    protected int rowIndex;
    protected int colIndex;
    /**
     * constructor position
     * @param rowIndex, colIndex
     */
    public Position(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }
    /**
     * This function get row index in position
     * @return row in maze
     */
    public int getRowIndex() {
        return rowIndex;

    }
    /**
     * This function get col index in position
     * @return col in maze
     */
    public int getColumnIndex() {
        return colIndex;
    }
    /**
     * This function set row index in position
     * @param rowIndex
     */
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
    /**
     * This function set col index in position
     * @param colIndex
     */
    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }
    /**
     * This function print the position
     * @return string
     */
    @Override
    public String toString() {
        return "{"+rowIndex+","+colIndex+"}";
    }
}
