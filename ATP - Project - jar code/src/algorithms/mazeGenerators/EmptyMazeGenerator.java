package algorithms.mazeGenerators;
/**
 * empty maze class
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class EmptyMazeGenerator extends AMazeGenerator {
   protected Maze emMaze;
    public EmptyMazeGenerator() {

    }

    /**
     * This function build empty maze
     * @param row, col
     * @return maze empty maze.
     */
    @Override
    public Maze generate(int row, int col) {
        emMaze= new Maze(row, col);
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++) {
                emMaze.setValue(i,j,0);
            }
        }
        return  emMaze;
    }


}
