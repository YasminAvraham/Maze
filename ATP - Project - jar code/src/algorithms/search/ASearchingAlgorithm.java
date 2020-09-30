package algorithms.search;
import java.io.Serializable;
import java.util.Stack;
/**
 * abstract class to create new search algorithm
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected String name;
    protected int numOfNude;
    /**
     * constructor.
     */
    public ASearchingAlgorithm() {
       numOfNude=0;
    }

    /**
     * This function get name of algorithm
     * @return string name
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * This function get num of nodes evaluated
     * @return int num of nodes evaluated
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfNude;
    }
    public Solution solutionPath(AState state, Solution currSolution){
        if(state==null)
            return null;
        Stack<AState> temp=new Stack<AState>();
        while(state.pervState!=null){
            temp.push(state);
            state=state.pervState;
        }
        temp.push(state);
        while(!temp.isEmpty()){
            currSolution.addAState(temp.pop());
        }
        return currSolution;
    }

}
