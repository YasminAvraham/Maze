package algorithms.mazeGenerators;
/**
 * interface to create new maze
 * have all the function thar maze generate
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public interface IMazeGenerator {
    public Maze generate(int row, int col);
    public long measureAlgorithmTimeMillis(int row,int col);

}
