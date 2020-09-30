package algorithms.mazeGenerators;
/**
 *  simple maze generator class
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    protected Maze spmMaze;

    /**
     * This function build simple maze
     * @param row, col
     * @return simple maze
     */
    @Override
    public Maze generate(int row, int col) {

        spmMaze= new Maze(row, col);
        if(spmMaze!=null) {
            if(row<1&&col<1){
                row=10;
                col=10;
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    spmMaze.setValue(i, j, 1);
                }
            }
        }

       int rowIndex=0,colIndex=0;
        spmMaze.setValue(0,0,0);

        while(checkWhileFirst(rowIndex,colIndex,row-1,col-1)==false){
          boolean flag=false;
          int  random_num=5;
          while (flag==false){
              random_num= (int) (Math.random()*100%4);
              flag=checkPosition(row,col,rowIndex,colIndex,random_num);
          }

            if(random_num==0){//up
                colIndex--;
            }else if(random_num==1){//left
                rowIndex--;
            }else if(random_num==2){//down
                colIndex++;
            }else{//right
                rowIndex++;
            }
            spmMaze.setValue(rowIndex,colIndex,0);
        }
        return spmMaze;
    }
    /**
     * This function check that the row and col is not the first position in maze
     * @param row, col, rowIndex,colIndex
     * @return true if is the first position in maze, else-false
     */
    public boolean checkWhileFirst(int rowIndex,int colIndex, int row, int col){
        if(row==rowIndex){
            if(col==colIndex){
                return true;
            }
        }
        return  false;
    }
    /**
     * This function check if the posion is legal
     * @param  l_row, l_col, rowIndex,colIndex, state
     * @return trueif the posion is legal, else-false
     */
    public boolean checkPosition(int l_row,int l_col,int rowIndex,int colIndex,int state){
        if(state==0){//up
            if (colIndex==0)
                return false;
        }else if(state==1){//left
            if(rowIndex==0)
                return false;
        }else if(state==2){//down
            if(l_col-1==colIndex)
                return false;
        }else{//right
            if(l_row-1==rowIndex)
                return false;
        }
        return true;
    }
}
