package algorithms.search;

import java.util.ArrayList;

/**
 * interface to convert the problem to general problem
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    ArrayList getAllPossibleState(AState state);
    void isVisit(AState s);
    public boolean isEqual(AState s1,AState s2);
    public void isClear();
    Solution checkIfIsSmall();
    }
