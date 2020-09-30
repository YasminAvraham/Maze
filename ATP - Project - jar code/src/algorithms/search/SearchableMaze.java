package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
/**
 * class that solve the maze
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class SearchableMaze implements ISearchable {
    protected Maze maze;
    protected MazeState start;
    protected MazeState goal;
    private int[][] visit;

    /**
     * constructor.
     */
    public SearchableMaze(Maze maze) {
        this.maze = maze;
        this.start = new MazeState(maze.getStartPosition());
        this.goal = new MazeState(maze.getGoalPosition());
        this.visit = new int[maze.getRow()][maze.getCol()];
        for (int i = 0; i < maze.getRow(); i++) {
            for (int j = 0; j < maze.getCol(); j++) {
                visit[i][j] = 0;
            }
        }
    }

    /**
     * This function get maze
     *
     * @return get maze
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * This function start maze state
     *
     * @return start maze stat
     */
    public MazeState getStart() {
        return start;
    }

    /**
     * This function goal maze state
     *
     * @return goal maze stat
     */
    public MazeState getGoal() {
        return goal;
    }


    /**
     * This function start astate
     *
     * @return start astat
     */
    @Override
    public AState getStartState() {
        return start;
    }

    /**
     * This function goal astate
     *
     * @return goal astat
     */
    @Override
    public AState getGoalState() {
        return goal;
    }

    /**
     * This function get all possible state
     *
     * @param state
     * @return array list that contain all the possible state
     */
    @Override
    public ArrayList getAllPossibleState(AState state) {
        ArrayList<MazeState> legalState = new ArrayList<MazeState>();
        if (state != null && state instanceof MazeState) {
            MazeState m_state = (MazeState) state;
            legalState = findSimpleState(legalState, m_state);
            legalState = findSlantState(legalState, m_state);
            return legalState;
        }
        return null;
    }

    /**
     * This function find Simple State
     *
     * @param legal s
     * @return find all Simple State
     */
    private ArrayList findSimpleState(ArrayList<MazeState> legal, MazeState s) {
        int row = s.currPosition.getRowIndex();
        int col = s.currPosition.getColumnIndex();
        if (legal(row - 1, col) && visit[row - 1][col] != 1) {//up
            legal.add(new MazeState(new Position(row - 1, col), 10));
            visit[row - 1][col] = 1;
        }
        if (legal(row + 1, col) && visit[row + 1][col] != 1) {//down
            legal.add(new MazeState(new Position(row + 1, col), 10));
            visit[row + 1][col] = 1;
        }
        if (legal(row, col - 1) && visit[row][col - 1] != 1) {//left
            legal.add(new MazeState(new Position(row, col - 1), 10));
            visit[row][col - 1] = 1;
        }
        if (legal(row, col + 1) && visit[row][col + 1] != 1) {//right
            legal.add(new MazeState(new Position(row, col + 1), 10));
            visit[row][col + 1] = 1;
        }
        return legal;
    }

    /**
     * This function find Slant State
     *
     * @param legal s
     * @return find all Slant State
     */
    private ArrayList findSlantState(ArrayList<MazeState> legal, MazeState s) {
        int row = s.currPosition.getRowIndex();
        int col = s.currPosition.getColumnIndex();
        if (legal(row + 1, col + 1) && (visit[row][col + 1] == 1 || visit[row + 1][col] == 1) && visit[row + 1][col + 1] != 1) {
            legal.add(new MazeState(new Position(row + 1, col + 1), 15));
            visit[row + 1][col + 1] = 1;

        }
        if (legal(row - 1, col - 1) && (visit[row - 1][col] == 1 || visit[row][col - 1] == 1) && visit[row - 1][col - 1] != 1) {
            legal.add(new MazeState(new Position(row - 1, col - 1), 15));
            visit[row - 1][col - 1] = 1;

        }
        if (legal(row - 1, col + 1) && (visit[row - 1][col] == 1 || visit[row][col + 1] == 1) && visit[row - 1][col + 1] != 1) {
            legal.add(new MazeState(new Position(row - 1, col + 1), 15));
            visit[row - 1][col + 1] = 1;

        }
        if (legal(row + 1, col - 1) && (visit[row + 1][col] == 1 || visit[row][col - 1] == 1) && visit[row + 1][col - 1] != 1) {
            legal.add(new MazeState(new Position(row + 1, col - 1), 15));
            visit[row + 1][col - 1] = 1;

        }
        return legal;
    }

    /**
     * This function check if row col are legal
     *
     * @param row col
     * @return true if legal else- false
     */
    private boolean legal(int row, int col) {
        if (row < 0 || row > maze.getRow() - 1)
            return false;
        if (col < 0 || col > maze.getCol() - 1)
            return false;
        if (maze.getValue(row, col) == 1)
            return false;
        //if(visit[row][col]==1)
        //    return false;
        return true;
    }

    /**
     * This function change the state that visit
     *
     * @param s
     */
    public void isVisit(AState s) {
        MazeState mazeS = (MazeState) s;
        visit[mazeS.currPosition.getRowIndex()][mazeS.currPosition.getColumnIndex()] = 1;
    }

    /**
     * This function check if s1 s2 are equal
     *
     * @param s1 s2
     * @return true if equal else-false
     */
    public boolean isEqual(AState s1, AState s2) {
        MazeState mazeS1 = (MazeState) s1;
        MazeState mazeS2 = (MazeState) s2;
        if (mazeS1.currPosition.getRowIndex() == mazeS2.currPosition.getRowIndex()
                && mazeS1.currPosition.getColumnIndex() == mazeS2.currPosition.getColumnIndex())
            return true;
        return false;
    }

    /**
     * This function clear the maze
     */
    public void isClear() {
        for (int i = 0; i < maze.getRow(); i++) {
            for (int j = 0; j < maze.getCol(); j++) {
                visit[i][j] = 0;
            }
        }
    }

    public Solution checkIfIsSmall() {
        Solution sol = null;
        if (maze.getRow() == 1 && maze.getCol() == 2) {
            if (maze.getStartPosition().getRowIndex() == 0 && maze.getStartPosition().getColumnIndex() == 0) {
                Solution sol2=new Solution();
                MazeState start= new MazeState(maze.getStartPosition());
                MazeState end= new MazeState(maze.getGoalPosition());
                end.pervState=start;
                sol2.addAState(new MazeState(maze.getStartPosition()));
                sol2.addAState(end);
                return sol2;

            } else {
                Solution sol2=new Solution();
                MazeState start= new MazeState(maze.getStartPosition());
                MazeState end= new MazeState(maze.getGoalPosition());
                end.pervState=start;
                sol2.addAState(new MazeState(maze.getStartPosition()));
                sol2.addAState(end);
                return sol2;

            }
        } else if (maze.getRow() == 2 && maze.getCol() == 1) {
            if (maze.getStartPosition().getRowIndex() == 0 && maze.getStartPosition().getColumnIndex() == 0) {
                Solution sol2=new Solution();
                MazeState start= new MazeState(maze.getStartPosition());
                MazeState end= new MazeState(maze.getGoalPosition());
                end.pervState=start;
                sol2.addAState(new MazeState(maze.getStartPosition()));
                sol2.addAState(end);
                return sol2;

            } else {
                Solution sol2=new Solution();
                MazeState start= new MazeState(maze.getStartPosition());
                MazeState end= new MazeState(maze.getGoalPosition());
                end.pervState=start;
                sol2.addAState(new MazeState(maze.getStartPosition()));
                sol2.addAState(end);
                return sol2;

            }
        }
        return sol;
    }

}
