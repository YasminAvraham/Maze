package algorithms.mazeGenerators;
import java.io.Serializable;
/**
 * maze class
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class Maze implements Serializable {
    protected int[][] maze;
    protected Position p_start;
    protected Position p_goal;
    private  int row;
    private int col;
    /**
     * constructor maze
     * @param row, col
     */
    public Maze(int row,int col) {
        if(row<1 && col<1){
            row=10;
            col=10;
            this.maze=new int[10][10];
            p_start = new Position(0, 0);
            p_goal = new Position(9, 9);
            this.row = 10;
            this.col = 10;
        }else {
            this.maze = new int[row][col];
            p_start = new Position(0, 0);
            p_goal = new Position(row - 1, col - 1);
            this.row = row;
            this.col = col;
        }
    }
    public Maze(byte[]m){
        this.row=m[0]*128+m[1];
        this.col=m[2]*128+m[3];
        p_start = new Position(0, 0);
        p_goal = new Position(m[4]*128+m[5], m[6]*128+m[7]);
        this.maze=new int[row][col];

        int index=8;
           for (int i=0; i < row ; i++) {
               for (int j=0; j < col; j++) {
                   maze[i][j] =m[index];
                   index++;

               }
           }

    }




    /**
     * constructor maze
     * @param row, col, start row, start col, goal row, goal col
     */
    public Maze(int row,int col,int s_row,int s_col,int g_row,int g_col) {
        this.maze = new int[row][col];
        p_start=new Position(s_row,s_col);
        p_goal=new Position(g_row,g_col);

        this.row=row;
        this.col=col;
    }
    /**
     * This function get value in position
     * @param r, c
     * @return value in maze
     */
    public int getValue(int r,int c){
        return maze[r][c];
    }
    /**
     * This function set value in position at r c to be v
     * @param r, c,v
     */
    public void setValue(int r,int c,int v){
        this.maze[r][c]=v;
    }
    /**
     * This function get position in maze
     * @return position in maze
     */
    public Position getStartPosition(){
        return p_start;
    }
    /**
     * This function get row in maze
     * @return row in maze
     */
    public int getRow(){return row;}
    /**
     * This function get col in maze
     * @return col in maze
     */
    public int getCol(){return col;}
    /**
     * This function get position goal in maze
     * @return position goal in maze
     */
    public Position getGoalPosition(){////////////////////////////////check if its true
        return p_goal;
    }

    /**
     * This function print the maze
     */
    public void print(){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(i==p_start.getRowIndex() &&j==p_start.getColumnIndex())
                    System.out.print("S ");
                else if(i==p_goal.getRowIndex() &&j==p_goal.getColumnIndex())
                    System.out.print("E ");
                else System.out.print(this.getValue(i,j)+" ");
            }
            System.out.print('\n');
        }
    }
    public byte[] toByteArray(){
        byte[] mazeByte=new byte[row*col+8];

        mazeByte[0]=(byte)(this.row/128);
        mazeByte[1]=(byte)(this.row%128);
        mazeByte[2]=(byte)(this.col/128);
        mazeByte[3]=(byte)(this.col%128);
        mazeByte[4]=(byte)(this.getGoalPosition().getRowIndex()/128);
        mazeByte[5]=(byte)(this.getGoalPosition().getRowIndex()%128);
        mazeByte[6]=(byte)(this.getGoalPosition().getColumnIndex()/128);
        mazeByte[7]=(byte)(this.getGoalPosition().getColumnIndex()%128);
        int m=8;
        for(int i=0; i<maze.length;i++){
            for(int j=0;j<maze[i].length;j++){
                mazeByte[m]=(byte) maze[i][j];
                m++;
            }
        }
        //print();
        return mazeByte;
    }
//    public byte[] toByteArrayCompress(byte[] b){//////////////////////////////////////////////////////////////////////
//        int size=(b.length-8)/8;
//        if((b.length-8)%8!=0){
//            size+=1;
//        }
//        byte[] compressMaze=new byte[size];
//        int bsize=b.length;
//        int indexCompMaze=0;
//        int i=8;
//        for(;i<b.length;i++){
//            byte[] tempArr;
//            int index=0;
//            if(bsize>=8) {
//                tempArr=new byte[8];
//                while (index < 8&& i<b.length) {
//                    tempArr[index] = b[i];
//                    index++;
//                    i++;
//                }
//            }else {
//                tempArr=new byte[size];
//                while (index < size && i<b.length) {
//                    tempArr[index] = b[i];
//                    index++;
//                    i++;
//                }
//            }
//            i--;
//            compressMaze[indexCompMaze]=convertToByte(tempArr);
//            indexCompMaze++;
//            bsize=bsize-8;
//            if(indexCompMaze>=compressMaze.length||i>=b.length)
//                break;
//        }
//        return compressMaze;
//    }

    @Override
    public String toString() {
        String name=row+col+p_start.toString()+p_goal.toString();
        return name;
    }

//    private byte[] convertToByte(Byte num){
//        int newNum=(int)num;
//        if(newNum<0){
//            newNum+=256;
//        }
//        byte[] temp=new byte[8];
//        for(int i=0;i<8;i++){
//            temp[i]=(byte) (newNum%2);
//            newNum=newNum/2;
//        }
//        return temp;
//    }
//    private byte convertToByte(byte[] b){
//        byte sol=0;
//        int p=0;
//        for(int i=0;i<b.length;i++){
//            sol+=(byte)(b[i]*(int)Math.pow(2,p));
//            p++;
//        }
//        return sol;
//    }
}
