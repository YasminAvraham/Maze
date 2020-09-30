package algorithms.mazeGenerators;

/**
 * abstract class to create new maze
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public abstract class  AMazeGenerator implements IMazeGenerator {

    /**
     * This function measure time to build maze
     * @param row, col
     * @return long time.
     */
    @Override
    public long measureAlgorithmTimeMillis(int row, int col) {
        long firstMeasure=0;
        long lastMeasure=0;
        if(row<1&&col<1){
            firstMeasure=System.currentTimeMillis();
            generate(10,10);
            lastMeasure=System.currentTimeMillis();
        }else {
            firstMeasure = System.currentTimeMillis();
            generate(row, col);
            lastMeasure = System.currentTimeMillis();
        }
        return lastMeasure-firstMeasure;
    }
}
