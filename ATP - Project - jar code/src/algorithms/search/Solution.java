package algorithms.search;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * class solution
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class Solution implements Serializable {
    protected ArrayList<AState> solution;
    /**
     * constructor
     */
    public Solution() {
        solution=new ArrayList<AState>();
    }
    /**
     * This function add state to the array list
     * @param newState
     * */
    public void addAState(AState newState){
        if(newState!=null)
            solution.add(newState);
    }
    /**
     * This function show all the path in solution
     * @return all the path in solution
     */
    public ArrayList<AState> getSolutionPath(){
        return this.solution;
    }


    @Override
    public String toString() {
       String temp="";
       for(int i=0;i<solution.size();i++){
           temp= temp +" "+ solution.get(i).toString();
       }
       return temp;
    }
}
