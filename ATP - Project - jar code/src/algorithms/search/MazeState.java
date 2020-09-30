package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.io.Serializable;

/**
 * class to show state in maze
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class MazeState extends AState implements Serializable {
    Position currPosition;
    /**
     * constructor
     */
    public MazeState(Position pos) {
        super();
        currPosition=new Position(pos.getRowIndex(),pos.getColumnIndex());
    }
    /**
     * constructor
     */
    public MazeState(Position pos,int cost) {
        super(cost);
        currPosition=new Position(pos.getRowIndex(),pos.getColumnIndex());
    }
    /**
     * This function override to string function
     * @return override to string function
     */
    @Override
    public String toString() {
        return this.currPosition.toString();
    }
}
