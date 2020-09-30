package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.Serializable;
/**
 *  my maze generator class
 * is base on prim algorithm to create a maze
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class MyMazeGenerator extends AMazeGenerator implements Serializable {
    protected Maze myMaze;
    private Position[][] pos;
    private List<Position> neighbors;

    /**
     * This function build maze
     * @param row, col
     * @return maze base by prim algorithm.
     */
    @Override
    public Maze generate(int row, int col) {

        myMaze=new Maze(row,col);
        if(myMaze!=null){
            if(row<1&&col<1){
                row=10;
                col=10;
            }
        pos=new Position[row][col];
        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {
                myMaze.setValue(i, j, 1);
                pos[i][j] = new Position(i, j);
            }
        }
        }
        if(myMaze.maze[0].length==1&&myMaze.maze.length==1){
            return myMaze;
        }
        neighbors=new ArrayList<Position>();
        neighbors.add(myMaze.getStartPosition());
        boolean haveNeighbors=true;
        while(neighbors.size()>0  ) {
            int indexCurrent = getRandomPosition();
            Position current = neighbors.get(indexCurrent);
            if (addNeighbors(current)) {
                myMaze.setValue(current.rowIndex, current.colIndex, 0);
            }
            neighbors.remove(indexCurrent);
        }
        myMaze.p_goal=getRandomGoal();
        return myMaze;
    }
    /**
     * This function get random goal in maze
     * @return goal position.
     */
    private Position getRandomGoal() {
        List<Position> p=new ArrayList<Position>();
        for(int i=1;i<myMaze.getRow();i++){
            Position checkPosition1=new Position(i,0);
            if(myMaze.getValue(i,0)==0){
                p.add(checkPosition1);
            }
            Position checkPosition2=new Position(i,myMaze.getCol()-1);
            if(myMaze.getValue(i,myMaze.getCol()-1)==0){
                p.add(checkPosition2);
            }
        }
        for(int i=1;i<myMaze.getCol();i++){
            Position checkPosition1=new Position(0,i);
            if(myMaze.getValue(0,i)==0){
                p.add(checkPosition1);
            }
            Position checkPosition2=new Position(myMaze.getRow()-1,i);
            if(myMaze.getValue(myMaze.getRow()-1,i)==0){
                p.add(checkPosition2);
            }
        }
        Random random=new Random();
        if(myMaze.maze[0].length==1&&myMaze.maze.length==2){
            if(myMaze.p_start.rowIndex==0&&myMaze.p_start.colIndex==0){
                myMaze.p_goal.setRowIndex(1);
                myMaze.p_goal.setColIndex(0);
            }else{
                myMaze.p_goal.setRowIndex(0);
                myMaze.p_goal.setColIndex(0);
            }
        }else if(myMaze.maze[0].length==2&&myMaze.maze.length==1) {
            if (myMaze.p_start.rowIndex == 0 && myMaze.p_start.colIndex == 0) {
                myMaze.p_goal.setRowIndex(1);
                myMaze.p_goal.setColIndex(0);
            } else {
                myMaze.p_goal.setRowIndex(0);
                myMaze.p_goal.setColIndex(0);
            }
        }else{
                int indexGoal = random.nextInt(p.size());
                return p.get(indexGoal);
            }
            return myMaze.p_goal;


    }
    /**
     * This function get position and add is neighbors
     * @param p
     * @return true if have neighbors , else return false
     */
    private boolean addNeighbors(Position p){
        boolean flag=false;
        int row= p.getRowIndex();
        int col=p.getColumnIndex();
        if((row!=myMaze.getRow()-1)&&(myMaze.getValue(row+1,col)!=0)){
            neighbors.add(new Position(row+1,col));
            flag=true;
        }
        if((col!=myMaze.getCol()-1)&&(myMaze.getValue(row,col+1)!=0)){
            neighbors.add(new Position(row,col+1));
            flag=true;
        }
        if((row!=0)&&(myMaze.getValue(row-1,col)!=0)){
            neighbors.add(new Position(row-1,col));
            flag=true;
        }
        if((col!=0)&&(myMaze.getValue(row,col-1)!=0)){
            neighbors.add(new Position(row,col-1));
            flag=true;
        }
        return  flag;
    }
    /**
     * This function get random place in array list
     * @return place in array list
     */
    private int getRandomPosition(){
        Random random=new Random();
        return random.nextInt(neighbors.size());
    }
}
