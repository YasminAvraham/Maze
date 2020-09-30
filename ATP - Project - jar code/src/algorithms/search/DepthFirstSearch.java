package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;
/**
 * class to create new search algorithm Depth First Search Algorithm
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class DepthFirstSearch extends ASearchingAlgorithm {
    private Stack<AState> temp;

    public DepthFirstSearch() {
        super();
        this.name="Depth First Search Algorithm";
        temp= new Stack<AState>();
    }

    /**
     * This function solve maze
     * @param domain
     * @return all the solution to solve maze
     */
    @Override
    public Solution solve(ISearchable domain) {
        //Solution result=new Solution();
        if(domain==null)
            return null;
        Solution s2=domain.checkIfIsSmall();
        if(s2!=null){
            domain.isClear();
            return s2;
        }
        AState curr=null;
        Solution sol = new Solution();
        temp.add(domain.getStartState());
        numOfNude++;
        domain.isVisit(domain.getStartState());
        Stack<AState> neighbors=null;
        boolean check=false;
        while(!temp.isEmpty()&&!check){
            neighbors=new Stack<AState>();
            curr=temp.pop();
            if(domain.isEqual(domain.getGoalState(),curr)){
                numOfNude++;
                sol =solutionPath(curr,sol);
                check=true;
                break;
            }
            ArrayList<AState> arrayTemp=domain.getAllPossibleState(curr);
            for(int i=0;i<arrayTemp.size();i++){
                neighbors.push(arrayTemp.get(i));
            }
            arrayTemp.clear();
            for(int i=0;i<neighbors.size();i++){
                if(domain.isEqual(domain.getGoalState(),neighbors.get(i))){
                    if( neighbors.get(i).pervState==null) {
                        neighbors.get(i).pervState = curr;
                    }
                    numOfNude++;
                    sol =solutionPath(neighbors.get(i),sol);
                    check=true;
                    break;
                }
                if( neighbors.get(i).pervState==null) {
                    neighbors.get(i).pervState = curr;
                }
                temp.add(neighbors.get(i));
                numOfNude++;
            }
        }
        domain.isClear();
        return sol;
    }


}
